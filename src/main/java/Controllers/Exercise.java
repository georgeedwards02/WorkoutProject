package Controllers;

import Server.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Exercise {

    public static void insertExercise(int ExerciseID, String ExerciseName, String TargetArea) {
        try {

            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Exercise (ExerciseID, ExerciseName, TargetArea) VALUES (?, ?, ?)");
            ps.setInt(1, ExerciseID);
            ps.setString(2, ExerciseName);
            ps.setString(3, TargetArea);
            ps.executeUpdate();
            System.out.println("Record added an Exercise called " + ExerciseName + " to Exercise table");

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());

        }
    }


    public static void deleteExercise(int ExerciseID){
        try {

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Exercise WHERE ExerciseID = ?");
            ps.setInt(1, ExerciseID);

            ps.executeUpdate();

            System.out.println("Deleted record " + ExerciseID + " from Exercise table");

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }

        public static void listExercise () {

            try {
                PreparedStatement ps = Main.db.prepareStatement("SELECT ExerciseName, TargetArea FROM Exercise");

                ResultSet results = ps.executeQuery();
                while (results.next()) {
                    String ExerciseName = results.getString(1);
                    String TargetArea = results.getString(2);

                    System.out.println("Exercise: " + ExerciseName + ", Target Area: " + TargetArea);
                }

            } catch (Exception exception) {
                System.out.println("Database error: " + exception.getMessage());
            }

    }


        public static void updateExercise (int ExerciseID, String ExerciseName, String TargetArea){
            try {

                PreparedStatement ps = Main.db.prepareStatement("UPDATE Exercise SET ExerciseName = ?, TargetArea = ? WHERE ExerciseID = ?");
                ps.setInt(1, ExerciseID);
                ps.setString(2, ExerciseName);
                ps.setString(3, TargetArea);

                ps.executeUpdate();

                System.out.println("The Exercise Name and Target Area with ExerciseID: " + ExerciseID + " has been updated");

            } catch (Exception exception) {
                System.out.println("Database error: " + exception.getMessage());

            }

        }

    }

