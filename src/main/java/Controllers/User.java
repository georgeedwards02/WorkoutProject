package Controllers;

import Server.Main;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class User {

        public static void deleteUser (int UserID){
            //"try" statement defines that this block of code will be tested for errors while it is being executed.
            try {

                PreparedStatement ps = Main.db.prepareStatement("DELETE FROM User WHERE UserID = ?");
                ps.setInt(1, UserID);

                ps.executeUpdate();
                System.out.println("Deleted " + UserID + " from User table");   //Print statement that explains which record has been deleted.

            //"catch" statement defines that this block of code will be executed if there is an error in the "try" block.
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }



        public static void insertUser(String UserName, String Password){
            try {
                PreparedStatement ps = Main.db.prepareStatement("INSERT INTO User (UserName, Password) VALUES (?, ?)");
                ps.setString(1, UserName);
                ps.setString(2, Password);  //Lines 34-36 used to define the data type for each field.

                ps.executeUpdate();
                System.out.println("Record added a user, " + UserName + " to User table");  //Print statement that says that a user with the Username displayed, has been added to the database.

            } catch (Exception exception) {
                System.out.println(exception.getMessage());
                System.out.println("Error: Something has gone wrong. Please contact the administrator with the error code WC-WA.");
            }
        }



        public static void listUser() {
            try {

                PreparedStatement ps = Main.db.prepareStatement("SELECT UserName, Goal1, DateAchieveBy, Achieved FROM User INNER JOIN Goal ON User.UserID = Goal.UserID");  //Inner Join used to join together the User and Goal table using UserID.

                ResultSet results = ps.executeQuery();

                while (results.next()) {
                    String UserName = results.getString(1);
                    String Goal1 = results.getString(2);
                    String DateAchieveBy = results.getString(3);
                    boolean Achieved = results.getBoolean(4);       //Code that gets the results in the right data type matching to the database.

                    //Print statement explaining the data for each field that has is in the database.
                    System.out.println("UserName: " + UserName + ", Goal:  " + Goal1 + ", DateAchieveBy: " + new SimpleDateFormat("yyyy-mm-dd").parse(DateAchieveBy) + ", Achieved: " + Achieved);
                                    //"new SimpleDateFormat("yyyy-mm-dd").parse(DateAchieveBy)" enables javascript to parse the date for DateAchieveBy.
                }

            } catch (Exception exception) {
                System.out.println("Database error: " + exception.getMessage());
            }

        }


        public static void updateUser (int UserID, String UserName, String Password){
            try {

                PreparedStatement ps = Main.db.prepareStatement("UPDATE User SET UserName = ?, Password = ? WHERE UserID = ?");
                ps.setString(1, UserName);
                ps.setString(2, Password);
                ps.setInt(3, UserID);

                System.out.println("The UserName and Password belonging to UserID: " + UserID + ", has been updated to UserName: " + UserName);

                ps.executeUpdate();

            } catch (Exception e) {

                System.out.println(e.getMessage());

            }

        }

    }


