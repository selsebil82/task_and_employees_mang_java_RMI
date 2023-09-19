package application;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entitiesInterface.EmployeeDAO;
import entitiesInterface.TaskDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class ConnectionController {

    // Champ pour stocker la connexion à la base de données
    private Connection connection;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button connectButton;

    @FXML
    private void handleConnectButton(ActionEvent event) {

        // Récupérer les informations de connexion entrées par l'utilisateur
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Établir la connexion à la base de données
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sar", "root", "");
            System.out.println("Connexion réussie !");

            // Vérifier si l'utilisateur existe dans la base de données
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Charger l'interface de fonctionnalités et la montrer à l'utilisateur
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("EntitiesInterface.fxml"));
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                    
                    
                 // Close the connection interface
                    Stage connectionStage = (Stage) connectButton.getScene().getWindow();
                    connectionStage.close();
                    
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                // Afficher un message d'erreur si l'utilisateur n'existe pas
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de connexion");
                alert.setHeaderText(null);
                alert.setContentText("Nom d'utilisateur ou mot de passe incorrect !");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
