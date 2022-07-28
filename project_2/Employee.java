/**
 This class will define the common data and operations for all employee type. 
 For each employee, a profile is created that uniquely identifies the employee.
 
 @author KJ Wang, Mehdi Kamal
 */
package project_2;

public class Employee {
    public Profile profile;


    /**
     * This method is a constructor for employee and creates a profile for an employee based on the parameters
     * @param name name of the employee
     * @param department department they work in
     * @param date date they were hired
     */
    public Employee(String name, String department, String date){
        this.profile = new Profile(name, department, date); 
    }


    /**
     * This method checks if a department is valid
     * @param department department an employee works in
     * @return true if department is valid, false otherwise
     */
    public boolean checkDepartment(String department) {
        if(department.equals("CS") || department.equals("ECE") || department.equals("IT")) {
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * This method returns a textual representation of an employee's profile
     * The String is returned as "name::department::dateHired"
     * @return String of employee's profile information as a textual representation
     */
    @Override
    public String toString() {
        String textualRepresentation = (profile.toString());
        return textualRepresentation;
    }


    /**
     * This method takes in an employee and checks if it is equal
     * The method compares the profiles of two employees
     * @return true if the employees have a matching name, department, and dateHired, false otherwise
     */
    @Override
    public boolean equals(Object obj) {   
        if (obj instanceof Employee) {
            Employee employee = (Employee) obj;
            return employee.profile.equals(this.profile);
        }
        return false; 
    }

    /**
     * This method calculates the payment of an employee
     */
    @Override
    public void calculatePayment() { }
}