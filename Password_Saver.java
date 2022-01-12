import java.sql.*;
import java.util.Scanner;

public class Password_Saver {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Would you like to store or retrieve a password? Please answer with 'S' or 'R'.");
        String response = scan.nextLine();
        boolean store = response.equalsIgnoreCase("S");
        boolean retrieve = response.equalsIgnoreCase("R");

        try {
            //1. Get a connection to database
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:port_number/database_name", "username", "password");

            //2. Create a statement
            Statement statement = connect.createStatement();

            //3. Execute SQL query
            if (store) {
                System.out.println("What is the name of the platform?");
                String service_name = scan.nextLine();
                System.out.println("What is your username?");
                String username = scan.nextLine();
                System.out.println("What is your password?");
                String passcode = scan.nextLine();

                PreparedStatement prepStmnt = connect.prepareStatement("INSERT INTO credentials VALUE(?, ?, ?)");
                prepStmnt.setString(1, service_name);
                prepStmnt.setString(2, username);
                prepStmnt.setString(3, passcode);

                int rowsAffected = prepStmnt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Your credentials have been saved.");
                }

            } else if (retrieve) {
                System.out.println("What is the name of the platform?");
                String serv_name = scan.nextLine();
                PreparedStatement prepStmnt = connect.prepareStatement("SELECT * FROM credentials WHERE service_name = ?");
                prepStmnt.setString(1, serv_name);
                ResultSet rS = prepStmnt.executeQuery();

                while (rS.next()) {
                    System.out.println("Platform: " + rS.getString(1) + "\t\t" + "Username: " + rS.getString(2) + "\t\tPassword: " + rS.getString(3));
                }
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        }

    }

}

