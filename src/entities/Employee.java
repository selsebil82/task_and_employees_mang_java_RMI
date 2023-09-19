package entities;

import java.io.Serializable;

public class Employee implements Serializable {
	private static final long serialVersionUID = 1L ;
    private int id;
    private String name;
    private String surname;
    private String address;
    private String accountNumber;
    private String grade;
    private int supervisorId;

    public Employee(int id, String name, String surname, String address, String accountNumber, String grade, int supervisorId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.accountNumber = accountNumber;
        this.grade = grade;
        this.supervisorId = supervisorId;
    }

    public Employee() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getGrade() {
        return grade;
    }

    public int getSupervisorId() {
        return supervisorId;
    }
    
    public void setId(int id) {
		this.id = id;		
	}

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setSupervisorId(int supervisorId) {
        this.supervisorId = supervisorId;
    }

    // Additional methods
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", address='" + address + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", grade='" + grade + '\'' +
                ", supervisorId=" + supervisorId +
                '}';
    }

    public String getFullName() {
        return name + " " + surname;
    }

    public String getSupervisorName() {
                return Integer.toString(supervisorId);
    }

	
}

