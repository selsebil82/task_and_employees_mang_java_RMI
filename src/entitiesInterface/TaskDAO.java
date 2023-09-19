package entitiesInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entities.Task;

public interface TaskDAO extends Remote {

    // Create a new task
    public void addTask(Task task) throws RemoteException;

    // Update an existing task
    public void updateTask(Task task) throws RemoteException;

    // Delete a task
    public void deleteTask(int taskId) throws RemoteException;

    // Get a single task by its ID
    public Task getTaskById(int taskId) throws RemoteException;

    // Get all tasks for a given employee
    public List<Task> getTasksByEmployeeId(int employeeId) throws RemoteException;

    // Get all tasks
    public List<Task> getAllTasks() throws RemoteException;

}

