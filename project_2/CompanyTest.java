import org.junit.Test;
import static org.junit.Assert.*;
/**
 * @author KJ Wang,
 * @author Mehdi Kamal
 */

public class CompanyTest {
    @Test
    public void testAdd(){
	//Valid date
        Company testCompany = new Company();
        Date testDate = new Date("7/1/2020"); 
        Date testDatetWO = new Date("7/1/2021");
      
        Employee testOne = new Parttime("Wang, KJ", "ECE", testDate, 45.9);
        assertTrue(testCompany.add(testOne)); 
        assertFalse(testCompany.add(testOne)); 
	
	
        Employee testTwo = new Fulltime("Wang, KJ", "CS", testDate, 1800);
        assertTrue(testCompany.add(testTwo));
        assertFalse(testCompany.add(testTwo)); 

        Employee testThree = new Management("Wang, KJ", "ECE", testDate, 19000, 1);
        assertTrue(testCompany.add(testThree)); 
        assertFalse(testCompany.add(testThree)); 

	//INVALID DEPARTMENT CODE
	Employee testThree = new Management("Wang, KJ", "ece", testDate, 19000, 1);
        assertTrue(testCompany.add(testThree)); 
        assertFalse(testCompany.add(testThree)); 

	//INVALID DATE
	Employee testFour = new Management("Wang, KJ", "ece", testDateTwo, 19000, 1);
        assertTrue(testCompany.add(testFour)); 
        assertFalse(testCompany.add(testFour)); 

    }
    
    @Test
    public void testRemove(){
        Company testCompany = new Company();
        Date testDate = new Date("8/20/2020"); 

	//REMOVE A CURRENT PARTTIME
        Employee testOne = new Parttime("Wang, KJ", "CS", testDate, 28);
        assertFalse(testCompany.remove(testOne)); 
        testCompany.add(testOne);
        assertTrue(testCompany.remove(testOne));
        
	//REMOVE Non-EXISTING MANAGER
        Employee testTwo = new Management("Wang, KJ", "IT", testDate, 4200, 2);
        assertFalse(testCompany.remove(testTwo)); 

	//REMOVE EXISTING MANAGER
        testCompany.add(testTwo);
        assertTrue(testCompany.remove(testTwo)); 

	//REMOVE UNREAL FULLTIME
        Employee testThree = new Fulltime("Wang, KJ", "CS", testDate, 3200);
        assertFalse(testCompany.remove(testThree)); 

	//REMOVE AVAILABLE FULLTIM
        testCompany.add(testThree);
        assertTrue(testCompany.remove(testThree)); 
    }

    @Test 
    public void testHours(){
        Company testCompany = new Company();
        Date testDate = new Date("9/9/2020");

	//SET HOURS TO A PARTTIME EMPLOYEE, NOT POSSIBLE
        Employee testOne= new Parttime("Doe,Jane", "IT", testDate, 26);
        testCompany.add(testOne);
	
        assertTrue(testCompany.setWorkingHours(testTwo)); 
	//SET HOURS TO A  FULLTIME EMPLOYEE, NOT POSSIBLE, also innaccurate department
        Employee testTwo = new Fulltime("Wang, KJ", "it", testDate, 37000);
        testCompany.add(testTwo);
        assertFalse(testCompany.setWorkingHours(testTwo)); 
        
    }
}