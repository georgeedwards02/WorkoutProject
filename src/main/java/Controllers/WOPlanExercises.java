package Controllers;

import Server.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class WOPlanExercises {

    public static void insertWOPlanExercises(int WorkoutPlanID, int ExerciseID, int NoOfSets, int NoOfReps){
        try {
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO WOPlanExercises (WorkoutPlanID, ExerciseID, NoOfSets, NoOfReps) VALUES (?, ?, ?, ?)");
            ps.setInt(1, WorkoutPlanID);
            ps.setInt(2, ExerciseID);
            ps.setInt(3, NoOfSets);
            ps.setInt(4, NoOfReps);

            ps.executeUpdate();

            System.out.println("Record added an exercise " + ExerciseID + " to WOPlanExercises table");

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            System.out.println("Error: Something has gone wrong. Please contact the administrator with the error code WC-WA.");
        }
    }

    public static void listWOPlanExercises() {
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT WorkoutPlan.WorkoutName, Exercise.ExerciseName, NoOfSets, NoOfReps FROM WOPlanExercises WPE INNER JOIN WorkoutPlan ON WPE.WorkoutPlanID = WorkoutPlan.WorkoutPlanID INNER JOIN Exercise ON WPE.ExerciseID = Exercise.ExerciseID");
            ResultSet results = ps.executeQuery();
            while (results.next()) {
                String WorkoutName = results.getString(1);
                String ExerciseName = results.getString(2);
                int NoOfSets = results.getInt(3);
                int NoOfReps = results.getInt(4);


                System.out.println("WorkoutName: " + WorkoutName + ", No Of Reps: " + NoOfReps + ", No Of Sets: " + NoOfSets + ", ExerciseName: " + ExerciseName);
            }

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }

    }

    public static void updateWOPlanExercises (int WorkoutPlanID, int ExerciseID, int NoOfSets, int NoOfReps){
        try {

            PreparedStatement ps = Main.db.prepareStatement("UPDATE WOPlanExercises SET NoOfSets = ?, NoOfReps = ? WHERE WorkoutPlanID =? AND ExerciseID = ?");

            ps.setInt(1, WorkoutPlanID);
            ps.setInt(2, ExerciseID);
            ps.setInt(3, NoOfSets);
            ps.setInt(4, NoOfReps);

            ps.executeUpdate();

            System.out.println("WorkoutPlanID: " + WorkoutPlanID + ", has been updated. Where ExerciseID is " + ExerciseID + ". No Of Sets: " + NoOfSets + ", No Of Reps: " + NoOfReps);

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

    }

    public static void deleteWOPlanExercises(int WorkoutPlanID, int ExerciseID){
        try {

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM WOPlanExercises WHERE WorkoutPlanID = ? AND ExerciseID = ?");
            ps.setInt(1, WorkoutPlanID);
            ps.setInt(2, ExerciseID);

            ps.executeUpdate();

            System.out.println("Deleted record with WorkoutPlanID: " + WorkoutPlanID + ", and ExerciseID: " + ExerciseID + " from WOPlanExercises table");

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }

}
