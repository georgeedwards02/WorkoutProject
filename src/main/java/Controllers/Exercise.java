package Controllers;

import Server.Main;
import com.sun.jersey.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Exercise {
    @POST
    @Path("new")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String insertExercise(
            @FormDataParam("ExerciseID") Integer ExerciseID, @FormDataParam("ExerciseName") String ExerciseName, @FormDataParam("TargetArea") String TargetArea){
        try {
            if (ExerciseID == null || ExerciseName == null || TargetArea == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("/exercise/add ExerciseID=" + ExerciseID);
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Exercise (ExerciseID, ExerciseName, TargetArea) VALUES (?, ?, ?)");
            ps.setInt(1, ExerciseID);
            ps.setString(2, ExerciseName);
            ps.setString(3, TargetArea);
            ps.executeUpdate();
            return "{\"status\": \"OK\"}";

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to create new item, please see server console for more info.\"}";
        }
    }

    @POST
    @Path("delete")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteExercise (@FormDataParam("ExerciseID") Integer ExerciseID){
        try {
            if (ExerciseID == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("/exercise/delete ExerciseID =" + ExerciseID);

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Exercise WHERE ExerciseID = ?");
            ps.setInt(1, ExerciseID);
            ps.executeUpdate();

            return "{\"status\": \"OK\"}";

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to delete item, please see server console for more info.\"}";
        }

    }

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public String listExercise() {
        System.out.println("/exercise/list");
        JSONArray list = new JSONArray();

        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT ExerciseID, ExerciseName, TargetArea FROM Exercise");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                JSONObject item = new JSONObject();
                item.put("ExerciseID", results.getInt(1));
                item.put("ExerciseName", results.getString(2));
                item.put("TargetArea", results.getString(3));
                list.add(item);
            }
            return list.toString();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to list items, please see server console for more info.\"}";
        }

    }


    @POST
    @Path("update")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateExercise (
            @FormDataParam("ExerciseID") Integer ExerciseID, @FormDataParam("ExerciseName") String ExerciseName, @FormDataParam("TargetArea") String TargetArea){
        try {
            if (ExerciseID == null || ExerciseName == null || TargetArea == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("/exercise/update ExerciseID=" + ExerciseID);
            PreparedStatement ps = Main.db.prepareStatement("UPDATE Exercise SET ExerciseName = ?, TargetArea = ? WHERE ExerciseID = ?");
            ps.setString(1, ExerciseName);
            ps.setString(2, TargetArea);
            ps.setInt(3, ExerciseID);
            ps.executeUpdate();
            return "{\"status\": \"OK\"}";

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to update item, please see server console for more info.\"}";

        }

    }

}
