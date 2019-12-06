package Controllers;

import Server.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class WorkoutPlan {

        public static void insertWorkoutPlan(int WorkoutPlanID, String WorkoutName, int GoalID){
            try {
                PreparedStatement ps = Main.db.prepareStatement("INSERT INTO WorkoutPlan (WorkoutPlanID, WorkoutName, GoalID) VALUES (?, ?, ?)");
                ps.setInt(1, WorkoutPlanID);
                ps.setString(2, WorkoutName);
                ps.setInt(3, GoalID);

                ps.executeUpdate();
                System.out.println("Record added a Workout Plan to WorkoutPlan table");

            } catch (Exception exception) {
                System.out.println(exception.getMessage());
                System.out.println("Error: Something has gone wrong. Please contact the administrator with the error code WC-WA.");
            }
        }


        public static void listWorkoutPlan() {
            try {
                PreparedStatement ps = Main.db.prepareStatement("SELECT WorkoutName, Exercise.ExerciseName, WPE.NoOfSets, WPE.NoOfReps FROM WorkoutPlan INNER JOIN WOPlanExercises WPE ON WorkoutPlan.WorkoutPlanID = WPE.WorkoutPlanID INNER JOIN Exercise ON Exercise.ExerciseID = WOPlanExercises.ExerciseID ");
                ResultSet results = ps.executeQuery();

                while (results.next()) {
                    String WorkoutName = results.getString(1);
                    int NoOfSets = results.getInt(2);
                    int NoOfReps = results.getInt(3);
                    String ExerciseName = results.getString(4);


                    System.out.println("WorkoutName: " + WorkoutName + ", ExerciseName: " + ExerciseName + ", No Of Reps: " + NoOfReps + ", No Of Sets: " + NoOfSets);
                }

            } catch (Exception exception) {
                System.out.println("Database error: " + exception.getMessage());
            }

        }


        public static void updateWorkoutPlan(int WorkoutPlanID, String WorkoutName){
            try {

                PreparedStatement ps = Main.db.prepareStatement("UPDATE WorkoutPlan SET WorkoutName = ?, WHERE WorkoutPlanID = ?");
                ps.setString(1, WorkoutName);
                ps.setInt(2, WorkoutPlanID);

                ps.executeUpdate();

                System.out.println(WorkoutPlanID + " has been updated. " + " New WorkoutName: "+ WorkoutName);

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

