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

    private String[][] coords;
    private String[] difficulty;

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
    }

    public void setTable(String tableName) {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " + tableName);

            while(resultSet.next()) {
                System.out.print(resultSet.getString(1) + "  ");
                System.out.print(resultSet.getString(2) + "  ");
                System.out.println(resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}