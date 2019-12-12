package Controllers;

import Server.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class WorkoutPlan {

        public static void insertWorkoutPlan(int WorkoutPlanID, String WorkoutName){
            try {
                PreparedStatement ps = Main.db.prepareStatement("INSERT INTO WorkoutPlan (WorkoutPlanID, WorkoutName) VALUES (?, ?)");
                ps.setInt(1, WorkoutPlanID);
                ps.setString(2, WorkoutName);

                ps.executeUpdate();
                System.out.println("Record added a Workout Plan to WorkoutPlan table");

            } catch (Exception exception) {
                System.out.println(exception.getMessage());
                System.out.println("Error: Something has gone wrong. Please contact the administrator with the error code WC-WA.");
            }
        }


        public static void listWorkoutPlan() {
            try {
                PreparedStatement ps = Main.db.prepareStatement("SELECT WorkoutName, Goal.Goal1 FROM WorkoutPlan INNER JOIN Goal ON WorkoutPlan.WorkoutPlanID = Goal.WorkoutPlanID ");
                ResultSet results = ps.executeQuery();

                while (results.next()) {
                    String WorkoutName = results.getString(1);
                    String Goal1 = results.getString(2);

                    System.out.println("WorkoutName: " + WorkoutName + ", Goal: " + Goal1);
                }

            } catch (Exception exception) {
                System.out.println("Database error: " + exception.getMessage());
            }

        }


        public static void updateWorkoutPlan(int WorkoutPlanID, String WorkoutName){
            try {

                PreparedStatement ps = Main.db.prepareStatement("UPDATE WorkoutPlan SET WorkoutName = ? WHERE WorkoutPlanID = ?");
                ps.setInt(1, WorkoutPlanID);
                ps.setString(2, WorkoutName);


                ps.executeUpdate();

                System.out.println("WorkoutPlanID: " + WorkoutPlanID + ", has been updated. " + " New WorkoutName: " + WorkoutName);

            } catch (Exception e) {

                System.out.println(e.getMessage());

            }

        }

        public static void deleteWorkoutPlan(int WorkoutPlanID){
            try {

                PreparedStatement ps = Main.db.prepareStatement("DELETE FROM WorkoutPlan WHERE WorkoutPlanID = ?");
                ps.setInt(1, WorkoutPlanID);

                ps.executeUpdate();

                System.out.println("Deleted record " + WorkoutPlanID + " from WorkoutPlan table");

            } catch (Exception e) {
                System.out.println(e.getMessage());

            }

        }

}

