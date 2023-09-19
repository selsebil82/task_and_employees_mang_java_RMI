package entities;

import java.io.Serializable;

public class Task implements Serializable  {
	private static final long serialVersionUID = 1L;
    private int id;
    private String description;
    private int employeeId;

    public Task(int id, String description, int employeeId) {
        this.id = id;
        this.description = description;
        this.employeeId = employeeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", employeeId=" + employeeId +
                '}';
    }
}

