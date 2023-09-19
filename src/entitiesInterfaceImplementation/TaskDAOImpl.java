package entitiesInterfaceImplementation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import entitiesInterface.TaskDAO;
import entities.Task;
public class TaskDAOImpl implements TaskDAO {

    private Connection connection;

    // Constructor that sets up the database connection
    public TaskDAOImpl() throws SQLException {
        // Replace "database_name" with the actual name of your database
        String url = "jdbc:mysql://localhost:3306/sar";
        String username = "root";
        String password = "";
        connection = DriverManager.getConnection(url, username, password);
    }
   

	@Override
    public void addTask(Task task) {
        String query = "INSERT INTO tasks (id,description, employee_id) VALUES (?,?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, task.getId());
            statement.setString(2, task.getDescription());
            statement.setInt(3, task.getEmployeeId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTask(Task task) {
        String query = "UPDATE tasks SET description=?, employee_id=? WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, task.getDescription());
            statement.setInt(2, task.getEmployeeId());
            statement.setInt(3, task.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTask(int taskId) {
        String query = "DELETE FROM tasks WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, taskId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Task getTaskById(int taskId) {
        Task task = null;
        String query = "SELECT * FROM tasks WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, taskId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                task = new Task(resultSet.getInt("id"),
                        resultSet.getString("description"),
                        resultSet.getInt("employee_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    @Override
    public List<Task> getTasksByEmployeeId(int employeeId) {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks WHERE employee_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, employeeId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Task task = new Task(resultSet.getInt("id"),
                        resultSet.getString("description"),
                        resultSet.getInt("employee_id"));
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Task task = new Task(resultSet.getInt("id"),
                        resultSet.getString("description"),
                        resultSet.getInt("employee_id"));
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}

