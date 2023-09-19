package application;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.List;
import javafx.scene.layout.AnchorPane;

import entities.Employee;
import entities.Task;
import entitiesInterface.EmployeeDAO;
import entitiesInterfaceImplementation.EmployeeDAOJDBCImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EmployeeController {
    
    @FXML
    private TableView<Employee> employeeTable;
    
    @FXML
    private TableColumn<Employee, Integer> idColumn;
    
    @FXML
    private TableColumn<Employee, String> nameColumn;
    
    @FXML
    private TableColumn<Employee, String> surnameColumn;
    
    @FXML
    private TableColumn<Employee, String> addressColumn;
    
    @FXML
    private TableColumn<Employee, String> accountNumberColumn;
    
    @FXML
    private TableColumn<Employee, String> gradeColumn;
    
    @FXML
    private TableColumn<Employee, Integer> supervisorIdColumn;
    
    @FXML
    private TextField idField;
    
    @FXML
    private TextField nameField;
    
    @FXML
    private TextField surnameField;
    
    @FXML
    private TextField addressField;
    
    @FXML
    private TextField accountNumberField;
    
    @FXML
    private TextField gradeField;
    
    @FXML
    private TextField supervisorIdField;
    
    @FXML
    private AnchorPane anchor2;
    
    @FXML
    private Button addButton;
    
    @FXML
    private Button updateButton;
    
    @FXML
    private Button deleteButton;
    
    @FXML
    private Button btnReturn;

    @FXML
    private Button resetEmp;
    
    private EmployeeDAO employeeDAO;
    
    public EmployeeController()  throws  NotBoundException {
    	Registry registry;
		try {
			
			registry = LocateRegistry.getRegistry("localhost", 1099);
			employeeDAO = (EmployeeDAO) registry.lookup("EmployeeDAO");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	 
        
    }
    
    @FXML
    private void initialize() throws RemoteException {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        accountNumberColumn.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));
        supervisorIdColumn.setCellValueFactory(new PropertyValueFactory<>("supervisorId"));
        employeeTable.setItems(getAllEmployees());
        employeeTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
            	idField.setText(String.valueOf(newSelection.getId()));
            	nameField.setText(newSelection.getName());
            	surnameField.setText(String.valueOf(newSelection.getSurname()));
            	addressField.setText(String.valueOf(newSelection.getAddress()));
            	accountNumberField.setText(String.valueOf(newSelection.getAccountNumber()));
            	gradeField.setText(String.valueOf(newSelection.getGrade()));
            	supervisorIdField.setText(String.valueOf(newSelection.getSupervisorId())); 
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
    private void clearFields() {
    	idField.clear();
    	nameField.clear();
    	surnameField.clear();
    	addressField.clear();
    	accountNumberField.clear();
    	gradeField.clear();
    	supervisorIdField.clear();
}
    
    private void refreshTable() throws RemoteException {
        List<Employee> employees = employeeDAO.getAllEmployees();
        ObservableList<Employee> observableEmployees = FXCollections.observableArrayList(employees);
        employeeTable.setItems(observableEmployees);
    }

    @FXML
    void returnToEntities(ActionEvent event) throws Exception {
    	try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EntitiesInterface.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) anchor2.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    	 catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}}
    


    
    @FXML
    private void handleAddButtonAction(ActionEvent event) throws RemoteException {
        Employee employee = new Employee(
                Integer.parseInt(idField.getText()),
                nameField.getText(),
                surnameField.getText(),
                addressField.getText(),
                accountNumberField.getText(),
                gradeField.getText(),
                Integer.parseInt(supervisorIdField.getText()));
				employeeDAO.addEmployee(employee);
		        refreshTable();
		        clearFields();
		        employeeTable.setItems(getAllEmployees());
    }

    
    
    @FXML
    private void handleUpdateButtonAction(ActionEvent event) throws RemoteException {
        Employee employee = new Employee(
                Integer.parseInt(idField.getText()),
                nameField.getText(),
                surnameField.getText(),
                addressField.getText(),
                accountNumberField.getText(),
                gradeField.getText(),
                Integer.parseInt(supervisorIdField.getText()));
        try {
			employeeDAO.updateEmployee(employee);
	        refreshTable();
	        clearFields();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        employeeTable.setItems(getAllEmployees());
    }
    
    @FXML
    private void handleDeleteButtonAction(ActionEvent event) throws RemoteException {
        int employeeId = Integer.parseInt(idField.getText());
        try {
			employeeDAO.deleteEmployee(employeeId);
	        refreshTable();
	        clearFields();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        employeeTable.setItems(getAllEmployees());
    }
   
    @FXML
    void clearFieldsEMP(MouseEvent event) {
    	idField.clear();
    	nameField.clear();
    	surnameField.clear();
    	addressField.clear();
    	accountNumberField.clear();
    	gradeField.clear();
    	supervisorIdField.clear();
    }
    
    
    private ObservableList<Employee> getAllEmployees() throws RemoteException {
        List<Employee> employees;
		
			employees = employeeDAO.getAllEmployees();
			ObservableList<Employee> employeeList = FXCollections.observableArrayList(employees);
	        return employeeList;
		
        
    }
    
    
}

