import java.sql.*;
import java.io.IOException;
import java.util.Scanner;

class projectDeliverable3 {
    public static void main(String[] args) throws SQLException {

        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (Exception cnfe) {
            System.out.println("Class not found");
        }

        // Postgresql connection info
        String url = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
        String usernamestring = "cs421g28";
        String passwordstring = "g28@comp421";
        Connection con = DriverManager.getConnection(url, usernamestring, passwordstring);
        Statement stmt = con.createStatement();

        // interface
        Scanner scan = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {

            System.out.println("Please choose one of the following options:");
            System.out.println("   (1) SOME QUERY 1\n" +
                    "   (2) SOME QUERY 2\n" +
                    "   (3) SOME QUERY 2\n" +
                    "   (4) SOME MODIFICATION 1\n" +
                    "   (5) SOME MODIFICATION 2\n" +
                    "   (6) Exit\n");
            System.out.println("   ENTER YOUR SELECTION HERE: ");

            int choice = 0;
            try {
                choice = Integer.parseInt(scan.next());
            } catch (Exception e) {
            }

            switch (choice) {
                case 1:
                    // execute some prepared statement
                    break;
                case 2:
                    // execute some prepared statement
                    break;
                case 3:
                    // execute some prepared statement
                    break;
                case 4:
                    // execute some prepared statement
                    break;
                case 5:
                    // execute some prepared statement
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("   ERROR: You entered an invalid input, please try again\n");
                    break;
            }
        }

    }
}