package entitiesInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entities.Employee;

public interface EmployeeDAO extends Remote {

    void addEmployee(Employee employee) throws RemoteException;

    void updateEmployee(Employee employee) throws RemoteException;

    void deleteEmployee(int employeeId) throws RemoteException;

    Employee getEmployeeById(int employeeId) throws RemoteException;

    List<Employee> getAllEmployees() throws RemoteException;

    List<Employee> getEmployeesBySupervisorId(int supervisorId) throws RemoteException;
}

