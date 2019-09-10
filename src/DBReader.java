import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class DBReader {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://raptor2:3306/terrains";
    private String username = null;
    private String password = null;

    public DBReader() {
        getUserLogin();
        loadDatabase();
    }

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

    private void loadDatabase() {
        try {
            Class.forName(DRIVER);
            System.out.println("Trying to open connection to raptor2.");
            Connection con = DriverManager.getConnection(DB_URL, username, password);

            Statement stmt = con.createStatement();
            System.out.println("Executing SQL statement.");
            String command = "SELECT * FROM terrains";

            ResultSet rs = stmt.executeQuery(command);
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }

            System.out.println("Closing connection to raptor2");
            stmt.close();
            con.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}