package application;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import entities.Employee;
import entities.Task;
import entitiesInterface.EmployeeDAO;
import entitiesInterface.TaskDAO;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class RMIClient extends Application {
	
    @Override
    public void start(Stage primaryStage) {
        try {
            // Obtenir le registre RMI
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

           // Obtenir le stub RMI pour l'interface EmployeeDAO
            EmployeeDAO employeeDAO = (EmployeeDAO) registry.lookup("EmployeeDAO");

            // Obtenir le stub RMI pour l'interface TaskDAO
            TaskDAO taskDAO = (TaskDAO) registry.lookup("TaskDAO");

            // Load the FXML file for the connection interface
            Parent parent = FXMLLoader.load(getClass().getResource("/application/ConnectionInterface.fxml"));

            // Create a scene with the connection interface
            Scene scene = new Scene(parent);

            // Set the scene for the primary stage
            primaryStage.setScene(scene);
            primaryStage.show();
            

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}