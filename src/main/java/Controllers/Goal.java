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

        public static void insertGoal(String Goal1, String DateAchieveBy, boolean Achieved, int UserID, int WorkoutPlanID){

            try {

                PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Goal (Goal1, DateAchieveBy, Achieved, UserID, WorkoutPlanID) VALUES (?, ?, ?, ?, ?)");
                ps.setString(1, Goal1);
                ps.setString(2, DateAchieveBy);
                ps.setBoolean(3, Achieved);
                ps.setInt(4, UserID);
                ps.setInt(5, WorkoutPlanID);

                ps.executeUpdate();
                System.out.println("Record added a new goal to Goal table");

            } catch (Exception exception) {
                System.out.println(exception.getMessage());
                System.out.println("Error: Something has gone wrong. Please contact the administrator with the error code WC-WA.");
            }
        }

        public static void listGoal() {
            try {
                PreparedStatement ps = Main.db.prepareStatement("SELECT Goal1, DateAchieveBy, Achieved, User.UserName, WorkoutPlan.WorkoutName FROM Goal INNER JOIN User ON Goal.UserID = User.UserID INNER JOIN WorkoutPlan ON Goal.WorkoutPlanID = WorkoutPlan.WorkoutPlanID");

                ResultSet results = ps.executeQuery();

                while (results.next()) {

                    String Goal1 = results.getString(1);
                    String DateAchieveBy = results.getString(2);
                    Boolean Achieved = results.getBoolean(3);
                    String UserName = results.getString(4);
                    String WorkoutName = results.getString(5);

                    System.out.println("UserName: " + UserName + ", Goal: " + Goal1 + ", DateAchieveBy: " + DateAchieveBy + ", Achieved: " + Achieved + ", WorkoutPlan: " + WorkoutName);
                }

            } catch (Exception exception) {
                System.out.println("Database error: " + exception.getMessage());
            }

        }

        public static void updateGoal(int GoalID, Boolean Achieved){
            try {

                PreparedStatement ps = Main.db.prepareStatement("UPDATE Goal SET Achieved = ? WHERE GoalID = ?");
                ps.setBoolean(1, Achieved);
                ps.setInt(2, GoalID);
                ps.executeUpdate();
                System.out.println(GoalID + " has updated Achieved, to: " + Achieved);

            } catch (Exception e) {

                System.out.println(e.getMessage());

            }

        }


    }

