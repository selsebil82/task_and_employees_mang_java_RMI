package entitiesInterfaceImplementation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import entities.Employee;
import entitiesInterface.EmployeeDAO;

public class EmployeeDAOJDBCImpl implements EmployeeDAO {
    
    private Connection connection;
    
    // Constructor that sets up the database connection
    public EmployeeDAOJDBCImpl() throws SQLException {
        // Replace "database_name" with the actual name of your database
        
    	String url = "jdbc:mysql://localhost:3306/sar";
        String username = "root";
        String password = "";
        connection = DriverManager.getConnection(url, username, password);
    }

    @Override
    public void addEmployee(Employee employee) {
        String query = "INSERT INTO employees (id, name, surname, address, account_number, grade, supervisor_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, employee.getId());
            statement.setString(2, employee.getName());
            statement.setString(3, employee.getSurname());
            statement.setString(4, employee.getAddress());
            statement.setString(5, employee.getAccountNumber());
            statement.setString(6, employee.getGrade());
            statement.setInt(7, employee.getSupervisorId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        String query = "UPDATE employees SET name = ?, surname = ?, address = ?, account_number = ?, grade = ?, supervisor_id = ? " +
                "WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getSurname());
            statement.setString(3, employee.getAddress());
            statement.setString(4, employee.getAccountNumber());
            statement.setString(5, employee.getGrade());
            statement.setInt(6, employee.getSupervisorId());
            statement.setInt(7, employee.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEmployee(int employeeId) {
        String query = "DELETE FROM employees WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, employeeId);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        String query = "SELECT * FROM employees WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, employeeId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Employee employee = new Employee(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("address"),
                        resultSet.getString("account_number"),
                        resultSet.getString("grade"),
                        resultSet.getInt("supervisor_id"));
                resultSet.close();
                statement.close();
                return employee;
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
    List<Employee> employees = new ArrayList<>();
    String query = "SELECT * FROM employees";
    try {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            Employee employee = new Employee(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("surname"),
                resultSet.getString("address"),
                resultSet.getString("account_number"),
                resultSet.getString("grade"),
                resultSet.getInt("supervisor_id")
            );
            employees.add(employee);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return employees;
}
@Override
public List<Employee> getEmployeesBySupervisorId(int supervisorId) {
    List<Employee> employees = new ArrayList<>();
    String query = "SELECT * FROM employees WHERE supervisor_id = ?";
    try {
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, supervisorId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Employee employee = new Employee(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    resultSet.getString("address"),
                    resultSet.getString("account_number"),
                    resultSet.getString("grade"),
                    resultSet.getInt("supervisor_id")
            );
            employees.add(employee);
        }
    } catch (SQLException e) {
        System.err.println("Error getting employees by supervisor id: " + e.getMessage());
    }
    return employees;
}

}

