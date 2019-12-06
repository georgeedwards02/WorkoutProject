package Controllers;

import Server.Main;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Goal {

        public static void deleteGoal (int GoalID){
            try {

                PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Goal WHERE GoalID = ?");
                ps.setInt(1, GoalID);
                ps.executeUpdate();

                System.out.println("Deleted record " + GoalID + " from Goal table");

            } catch (Exception e) {
                System.out.println(e.getMessage());

            }

        }

        public static void insertGoal(String Goal1, String DateAchieveBy, boolean Achieved, int WorkoutPlanID, int UserID){

            try {


                PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Goal (Goal1, DateAchieveBy, Achieved, WorkoutPlanID, UserID) VALUES (?, ?, ?, ?, ?)");
                ps.setString(1, Goal1);
                ps.setString(2, DateAchieveBy);
                ps.setBoolean(3, Achieved);
                ps.setInt(4, WorkoutPlanID);
                ps.setInt(5, UserID);

                ps.executeUpdate();
                System.out.println("Record added a new goal to Goal table");

            } catch (Exception exception) {
                System.out.println(exception.getMessage());
                System.out.println("Error: Something has gone wrong. Please contact the administrator with the error code WC-WA.");
            }
        }

        public static void listGoal() {
            try {
                PreparedStatement ps = Main.db.prepareStatement("SELECT Goal1, DateAchieveBy, Achieved, WorkoutPlan.WorkoutName, UserName FROM Goal INNER JOIN WorkoutPlan ON Goal.WorkoutPlanID = WorkoutPlan.WorkoutPlanID INNER JOIN User ON Goal.UserID = User.UserID");

                ResultSet results = ps.executeQuery();

                while (results.next()) {

                    String Goal1 = results.getString(1);
                    String DateAchieveBy = results.getString(2);
                    Boolean Achieved = results.getBoolean(3);
                    String WorkoutName = results.getString(4);
                    int UserID = results.getInt(5);
                    String UserName = results.getString(6);

                    System.out.println("UserName: " + UserName + ", Goal: " + Goal1 + ", DateAchieveBy: " + DateAchieveBy + ", Achieved: " + Achieved + ", Workout name: " + WorkoutName);
                }

            } catch (Exception exception) {
                System.out.println("Database error: " + exception.getMessage());
            }

        }

        public static void updateGoal(String Goal1, Boolean Achieved){
            try {

                PreparedStatement ps = Main.db.prepareStatement("UPDATE Goal SET Achieved = ?, WHERE Goal1 = ?");
                ps.setBoolean(1, Achieved);
                ps.setString(2, Goal1);
                ps.executeUpdate();
                System.out.println(Goal1 + " has updated Achieved, to: " + Achieved);

            } catch (Exception e) {

                System.out.println(e.getMessage());

            }

        }


    }

