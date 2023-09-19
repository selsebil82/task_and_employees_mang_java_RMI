package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;



   

public class EntitiesController {
	@FXML
    private Button LogoutButton;

	 @FXML
	    private AnchorPane anchor3;

	    @FXML
	    private Button emplbtn;

	    @FXML
	    private Button taskbtn;

	   
	    @FXML
	    void handleConnectButton1(ActionEvent event) {
	    	try {
	    		
	            
	   		 FXMLLoader loader = new FXMLLoader(getClass().getResource("TaskGui.fxml"));
	   	     Parent root;
	   	   	 root = loader.load();
	          Stage stage = new Stage();
	          stage.setScene(new Scene(root));
	          stage.show();
	          
	            // Close the current window
	            Stage currentStage = (Stage) anchor3.getScene().getWindow();
	            currentStage.close();
	            
	   	} catch (IOException e) {
	   		// TODO Auto-generated catch block
	   		e.printStackTrace();
	   	}
	    	
	    }
	    
	    

	    @FXML
	    void handleConnectButton2(ActionEvent event) {
	    	try {
	    		
	           
	            
				 FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeGui.fxml"));
			     Parent root;
				root = loader.load();
		       Stage stage = new Stage();
		       stage.setScene(new Scene(root));
		       stage.show();
		       
		       // Close the current window
	            Stage currentStage = (Stage) anchor3.getScene().getWindow();
	            currentStage.close();
	            
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    @FXML
	    public void onLogoutClicked(ActionEvent event) {
	        // Fermer la fenêtre de l'interface EntitiesInterface.fxml
	        Stage stage = (Stage) LogoutButton.getScene().getWindow();
	        stage.close();
	        
	        // Afficher un message de déconnexion réussie dans la console
	        System.out.println("Déconnexion réussie");
	    }
	  
	}
