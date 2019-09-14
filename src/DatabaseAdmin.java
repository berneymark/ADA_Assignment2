import javax.xml.transform.Result;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class DatabaseAdmin {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://raptor2:3306/terrains";
    private String username = null;
    private String password = null;

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    private String[][] difficulty;
    private int rows;
    private int cols;

    private void getUserLogin() {
        String[] loginData = new String[2];
        Scanner inputFile = null;

        try {
            inputFile = new Scanner(new File("src/loginData.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int loop = 0;
        while (inputFile.hasNext()) {
            loginData[loop] = inputFile.next();
            loop++;
        }

        username = loginData[0];
        password = loginData[1];
        inputFile.close();
    }

    public void connectDatabase() {
        getUserLogin();

        try {
            Class.forName(DRIVER);
            System.out.println("Trying to open connection to raptor2.");
            connection = DriverManager.getConnection(DB_URL, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void closeAll() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connection = null;
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            statement = null;
        }

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resultSet = null;
        }
    }

    private void setRowsAndCols(String tableName) {
        switch (tableName) {
            case "illustrated":
                rows = 5;
                cols = 5;
                break;
            case "large":
                rows = 50;
                cols = 40;
                break;
            case "medium":
                rows = 30;
                cols = 20;
                break;
            case "small":
                rows = 10;
                cols = 10;
                break;
            case "tinyA":
                rows = 4;
                cols = 7;
                break;
            case "tinyB":
                rows = 3;
                cols = 3;
                break;
        }
    }

    public void setDifficulty(String tableName) {
        setRowsAndCols(tableName);
        difficulty = new String[rows][cols];

        try {
            System.out.println(tableName);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " + tableName);

            int rowCount = 0, colCount = 0;
            while(resultSet.next()) {
                difficulty[rowCount][colCount] = resultSet.getString(3);

                if (colCount == cols - 1) {
                    colCount = 0;
                    rowCount++;
                } else {
                    colCount++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public String[][] getDifficulty() {
        return difficulty;
    }
}