package Server;

import Controllers.Exercise;
import Controllers.Goal;
import Controllers.User;
import Controllers.WorkoutPlan;
import org.sqlite.SQLiteConfig;
import java.sql.Connection;
import java.sql.DriverManager;


public class Main {


    public static Connection db = null;

    public static void main(String[] args) {
        openDatabase("CompSciProject.db");
        //Methods are called here to interact with the database

        closeDatabase();
    }

    private static void openDatabase(String dbFile) {
        try  {
            Class.forName("org.sqlite.JDBC");
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            db = DriverManager.getConnection("jdbc:sqlite:resources/" + dbFile, config.toProperties());
            System.out.println("Database connection successfully established.");
        } catch (Exception exception) {
            System.out.println("Database connection error: " + exception.getMessage());
        }

    }

    private static void closeDatabase(){
        try {
            db.close();
            System.out.println("Disconnected from database.");
        } catch (Exception exception) {
            System.out.println("Database disconnection error: " + exception.getMessage());
        }
    }

}
