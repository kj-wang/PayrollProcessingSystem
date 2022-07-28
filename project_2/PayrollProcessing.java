/**
 * This class defines the PayrollProcessing, which is the user interface for the entire project.
 * 
 * The PayrollProcessing class has multiple methods that connect the other different classes together. It is the bridge between
 * the user and the platform, allowing different functions such as add, remove, print, etc. to work and 
 * accounting for other inputs that user may enter. This class also is where the input is read from the command line.
 *
 * @author KJ Wang, Mehdi Kamal
 */
package project_2;
import java.util.Scanner;
import java.util.StringTokenizer;

public class PayrollProcessing {
    Company company = new Company();
    private static final int ZERO = 0;
    private static final int WORK_HOUR_LIMIT = 100;

    /**
     * This method adds a parttime employee to the company
     * @param name the name of the employee
     * @param department the department of the employee
     * @param inputDate the date hired of the employee
     * @param hourlyPay the hourly pay rate of the employee
     */
    private void addParttime(String name, String department, String inputDate, String hourlyPay) {
        Parttime employee = new Parttime(name, department, inputDate, hourlyPay);
        double hourlyPayDouble = Double.parseDouble(hourlyPay);
        if (hourlyPayDouble < ZERO) {
            System.out.println("Pay rate cannot be negative.");
            return;
        }
        if (!employee.checkDepartment(department)) {
            System.out.println("'" + department + "' is not a valid department code.");
        }    
        else if (company.add(employee)){
            System.out.println("Employee added.");
        }
        else {
            System.out.println("Employee is already in the list.");
        }
    }

    /**
     * This method adds a fulltime employee to the company
     * @param name the name of the employee
     * @param department the department of the employee
     * @param inputDate the date hired of the employee
     * @param salary the hourly pay rate of the employee
     */
    private void addFulltime(String name, String department, String inputDate, String salary){
        Fulltime employee = new Fulltime(name, department, inputDate, salary);
        int salaryInt = Integer.parseInt(salary);
        if (salaryInt < ZERO) {
            System.out.println("Pay rate cannot be negative.");
            return;
        }
        if (!employee.checkDepartment(department)) {
            System.out.println("'" + department + "' is not a valid department code.");
        }
        else if (company.add(employee)){
            System.out.println("Employee added.");
        }
        else {
            System.out.println("Employee is already in the list.");
        }
    }

     /**
     * This method adds a management employee to the company
     * @param name the name of the employee
     * @param department the department of the employee
     * @param inputDate the date hired of the employee
     * @param salary the hourly pay rate of the employee
     * @param role the management role of the employee
     */
    private void addManagement(String name, String department, String inputDate, String salary, String role){
        int salaryInt = Integer.parseInt(salary);
        if (salaryInt < ZERO) {
            System.out.println("Pay rate cannot be negative.");
            return;
        }
        Management employee = new Management(name, department, inputDate, salary, role);
        if(!employee.checkType(role)){
            System.out.println("Invalid management code.");
        }
        else if (!employee.checkDepartment(department)) {
            System.out.println("'" + department + "' is not a valid department code.");
        }        
        else if (company.add(employee)){
            System.out.println("Employee added.");
        }
        else {
            System.out.println("Employee is already in the list.");
        }
    }

    /**
     * This method removes the employee with the indicated profile
     * @param name the name of the employee
     * @param department the department of the employee
     * @param inputDate the date hired of the employee
     */
    private void remove(String name, String department, String inputDate){
        Employee employee = new Employee(name, department, inputDate);

        if (company.remove(employee)) {
           System.out.println("Employee removed.");
        }
        else{
            System.out.println("Employee doesn't exist.");
        }
    }


    /**
     * This method is for the input "C", for calculating the payments
     */
    private void calculate() {
        company.processPayments();
        System.out.println("Calculation of employee payments is done.");
    }

    /**
     * This method is to set the hours of the employee
     * @param name the name of the employee
     * @param department the department of the employee
     * @param inputDate the date hired of the employee
     * @param hours the amount of hours the employee worked
     */
    private void setEmployeeHours(String name, String department, String inputDate, String hours) {
        Parttime employee = new Parttime(name, department, inputDate, hours);
        int hoursInt = Integer.parseInt(hours);
        if (hoursInt < ZERO) {
            System.out.println("Working hours cannot be negative.");
            return;
        } 
        else if (hoursInt > WORK_HOUR_LIMIT) {
            System.out.println("Invalid Hours: over 100.");
            return;
        }
        if (employee instanceof Parttime) {
            employee.setWorkingHours(hoursInt);
            if (company.setHours(employee)) {
                System.out.println("Working hours set.");    
            }   
            else {
                System.out.println("Employee does not exist.");
            }
        }
    }

    /**
     * This method prints the list of employees with current sequence
     */
    private void printEarnings() {
        if (company.getNumEmployee() == 0) {
            System.out.println("Employee database is empty.");
        }
        else {
            System.out.println("--Printing earning statements for all employees--");
            company.printEarnings();
        }
    }

    /**
     * This method prints the list of employees with ascending order by date
     */
    private void printByDate() {
        if (company.getNumEmployee() == 0) {
            System.out.println("Employee database is empty.");
        }
        else {
            System.out.println("--Printing earning statements for all employees--");
            company.printByDate();
        }
    }
    
    /**
     * This method prints the catalog with ascending order by number 
     */
    private void printByDepartment() {
        if (company.getNumEmployee() == 0){
            System.out.println("Employee database is empty.");
        }
        else {
            System.out.println("--Printing earning statements for all employees--");
            company.printByDepartment();
        }
    }

    /**
     * This method is the main method for running all of the functions
     */
    public void run() {
        System.out.println("Payroll Processing starts.");
        
        //Scanner
        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine()) { 
            String input = sc.nextLine();

            //Accounts for a blank input
            if (input.length() == 0) {
                continue;
            }

            StringTokenizer st = new StringTokenizer(input, " "); 
            String token = st.nextToken().trim(); 

            if(token.equals("PA")) {
                printEarnings();
            }
            else if(token.equals("PH")) {
                printByDate();
            }
            else if(token.equals("PD")) {
                printByDepartment();
            }  
            else if(token.equals("C")) { //Calculate Payment 
                int numEmployee = company.getNumEmployee();
                if(numEmployee == ZERO){
                    System.out.println("Employee database is empty.");
                    continue;
                }
                calculate();
            }
            else if(token.equals("Q")) {
                System.out.println("Payroll Processing completed.");
                break;
            }
            else if(!st.hasMoreTokens()){
                System.out.println("Command '" + token + "' not supported!");
            }
            else{
                
                String name = st.nextToken().trim();
                String department = st.nextToken().trim();
                String dateHired = st.nextToken().trim();
                Date date = new Date(dateHired);
                
                if (!date.isValid()){
                    System.out.println(dateHired + " is not a valid date!");
                }
                else if(token.equals("AP")) {       //Parttime
                    String hourlyPay = st.nextToken().trim();
                    addParttime(name, department, dateHired, hourlyPay);
                }
                else if(token.equals("AF")) {  //Fulltime
                    String salary = st.nextToken().trim();
                    addFulltime(name, department, dateHired, salary);
                }
                else if(token.equals("AM")) {  //Management
                    String salary = st.nextToken().trim();
                    String managementType= st.nextToken().trim();
                    addManagement(name, department, dateHired, salary, managementType);
                    
                }
                else if(token.equals("R")) { //Remove an employee
                    int numEmployee = company.getNumEmployee();
                    if(numEmployee == ZERO){
                        System.out.println("Employee database is empty.");
                        continue;
                    }
                    remove(name, department, dateHired);
                }
                else if(token.equals("S")) { //Set hours for Parttime       //INCOMPLETE
                    int numEmployee = company.getNumEmployee();
                    if(numEmployee == ZERO){
                        System.out.println("Employee database is empty.");
                        continue;
                    }
                    String hours = st.nextToken().trim();
                    setEmployeeHours(name, department, dateHired, hours);
                }
                else {
                    System.out.println("Command '" + token + "' not supported!");
                }
            }
        }
        sc.close();
    }  
}
