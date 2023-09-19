package application;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import entities.Task;
import entitiesInterface.EmployeeDAO;
import entitiesInterface.TaskDAO;
import entitiesInterfaceImplementation.EmployeeDAOJDBCImpl;
import entitiesInterfaceImplementation.TaskDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class TaskController implements Initializable {

    @FXML
    private AnchorPane anchor4;

    @FXML
    private TableView<Task> taskTable;

    @FXML
    private TableColumn<Task, Integer> idCol;

    @FXML
    private TableColumn<Task, String> descriptionCol;

    @FXML
    private TableColumn<Task, Integer> empidCol;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    @FXML
    private TextField fieldId;

    @FXML
    private TextField fieldDescription;

    @FXML
    private TextField fieldEmpid;

    @FXML
    private Label labelId;

    @FXML
    private Label labelDescription;
    @FXML
    
    private Button btnReturn;
    
    @FXML
    private Button resetTask;


    @FXML
    private Label labelEmpid;
    private TaskDAO taskDAO;
    
    public TaskController() throws NotBoundException  {
    	Registry registry;
		try {
			
			registry = LocateRegistry.getRegistry("localhost", 1099);
			taskDAO = (TaskDAO) registry.lookup("TaskDAO");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        empidCol.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        taskTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                fieldId.setText(String.valueOf(newSelection.getId()));
                fieldDescription.setText(newSelection.getDescription());
                fieldEmpid.setText(String.valueOf(newSelection.getEmployeeId()));
            }
        });
        try {
			refreshTable();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void returnToEntities(ActionEvent event) throws Exception {
    	try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EntitiesInterface.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) anchor4.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
 catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}
    
    @FXML
    void clearFieldsTask(MouseEvent event) {
    	fieldId.clear();
        fieldDescription.clear();
        fieldEmpid.clear();
    }
    
    @FXML
    void addTask(ActionEvent event) throws RemoteException {
        String description = fieldDescription.getText();
        int employeeId = Integer.parseInt(fieldEmpid.getText());
        Task task = new Task(0, description, employeeId);
        taskDAO.addTask(task);
        refreshTable();
        clearFields();
    }

    @FXML
    void updateTask(ActionEvent event) throws RemoteException {
        int id = Integer.parseInt(fieldId.getText());
        String description = fieldDescription.getText();
        int employeeId = Integer.parseInt(fieldEmpid.getText());
        Task task = new Task(id, description, employeeId);
        taskDAO.updateTask(task);
        refreshTable();
        clearFields();
    }

    @FXML
    void deleteTask(ActionEvent event) throws RemoteException {
        int id = Integer.parseInt(fieldId.getText());
        taskDAO.deleteTask(id);
        refreshTable();
        clearFields();
    }

    private void refreshTable() throws RemoteException {
        List<Task> tasks = taskDAO.getAllTasks();
        ObservableList<Task> observableTasks = FXCollections.observableArrayList(tasks);
        taskTable.setItems(observableTasks);
    }

    private void clearFields() {
        fieldId.clear();
        fieldDescription.clear();
        fieldEmpid.clear();
    }
}
