import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class DatabaseAdmin {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://raptor2:3306/terrains";
    private String username = null;
    private String password = null;

    private Connection con = null;

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

    private void connectDatabase() {
        getUserLogin();

        try {
            Class.forName(DRIVER);
            System.out.println("Trying to open connection to raptor2.");
            con = DriverManager.getConnection(DB_URL, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void closeAll() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            con = null;
        }
    }

    public static void main(String[] args) {
        DatabaseAdmin db = new DatabaseAdmin();
        db.connectDatabase();
    }
}