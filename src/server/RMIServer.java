package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import entitiesInterface.TaskDAO;
import entitiesInterface.EmployeeDAO;
import entitiesInterfaceImplementation.EmployeeDAOJDBCImpl;
import entitiesInterfaceImplementation.TaskDAOImpl;

public class RMIServer {
    public static void main(String[] args) {
        try {
            // Créer une instance des implémentations des interfaces distantes
            EmployeeDAOJDBCImpl employeeDAOImpl = new EmployeeDAOJDBCImpl();
     
			TaskDAOImpl taskDAOImpl = new TaskDAOImpl();
			
            // Exporter les objets distants et récupérer les stubs
            EmployeeDAO employeeDAOStub = (EmployeeDAO) UnicastRemoteObject.exportObject(employeeDAOImpl, 0);
            TaskDAO taskDAOStub = (TaskDAO) UnicastRemoteObject.exportObject(taskDAOImpl, 0);

            // Enregistrer les stubs dans le registre RMI
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("EmployeeDAO", employeeDAOStub);
            registry.rebind("TaskDAO", taskDAOStub);

            System.out.println("Serveur RMI démarré avec succès !");
        } catch (Exception e) {
            System.err.println("Erreur lors du démarrage du serveur RMI : " + e.getMessage());
            e.printStackTrace();
        }
    }
}

