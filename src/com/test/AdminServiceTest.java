package com.test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.exception.AdminNotFoundException;
import com.exception.DatabaseConnectionException;
import com.exception.InvalidInputException;
import com.model.Admin;
import com.service.AdminService;

public class AdminServiceTest {
	AdminService as = new AdminService();

    @Test
    public void getAdminByIdTest() {
        Admin admin1 = new Admin(1, "Jermy", "John", "jermy@gmail.com", "789654123", "jermy123", "415263", "admin", LocalDate.parse("2022-02-19"));
        Admin admin2 = new Admin(2, "Jon", "Snow", "snow@gmail.com", "859654123", "snow123", "417263", "super admin", LocalDate.parse("2020-12-09"));
        Admin admin3 = new Admin(3, "Arya", "Stark", "arya@gmail.com", "419654123", "arya723", "572663", "admin", LocalDate.parse("2021-10-09"));
        
        
        List<Admin> list = new ArrayList<>();
        list.add(admin1);
        list.add(admin2);
        list.add(admin3);
        
        int id = 2;
        Admin expectedAdmin = new Admin(2, "Jon", "Snow", "snow@gmail.com", "859654123", "snow123", "417263", "super admin", LocalDate.parse("2020-12-09"));
        try {
            Assert.assertEquals(expectedAdmin, as.getAdminById(list, id));
        } catch (InvalidInputException | AdminNotFoundException e) {
            Assert.fail("Admin not found for the provided id");
        }

        
        id = 12;
        try {
            as.getAdminById(list, id);
            Assert.fail("Expected AdminNotFoundException to be thrown");
        } catch (AdminNotFoundException e) {
            String errorMessage = e.getMessage();
            if (errorMessage != null) {
                Assert.assertEquals("invalid id given".toLowerCase(), errorMessage.toLowerCase());
            }
        } catch (InvalidInputException e) {
            Assert.fail("InvalidInputException should not be thrown in this test case");
        }
    }
    @Test
    public void getAdminByUsername() {
        Admin admin1 = new Admin(1, "Jermy", "John", "jermy@gmail.com", "789654123", "jermy123", "415263", "admin", LocalDate.parse("2022-02-19"));
        Admin admin2 = new Admin(2, "Jon", "Snow", "snow@gmail.com", "859654123", "snow123", "417263", "super admin", LocalDate.parse("2020-12-09"));
        Admin admin3 = new Admin(3, "Arya", "Stark", "arya@gmail.com", "419654123", "arya723", "572663", "admin", LocalDate.parse("2021-10-09"));
        
        List<Admin> list = new ArrayList<>();
        list.add(admin1);
        list.add(admin2);
        list.add(admin3);
        String userName="Jermy";
        AdminService as=new AdminService();
        
        try {
            Admin found = as.getAdminByUsername(list, userName);
            Assert.assertEquals(userName, found.getUserName());
        } catch (InvalidInputException | AdminNotFoundException e) {
             }

        userName = "jon";
        try {
            as.getAdminByUsername(list, userName);
            Assert.fail("Expected AdminNotFoundException to be thrown");
        } catch (AdminNotFoundException e) {
        	String errorMessage = e.getMessage();
            if (errorMessage != null) 
            Assert.assertEquals("invalid name given".toLowerCase(),e.getMessage().toLowerCase());
        } catch (InvalidInputException e) {
            Assert.fail("Invalid input exception occurred");
        }
    }
    @Test
    public void updateAdminTest() {
    	    
    	    int adminIdToUpdate = 1; 
    	    String fieldToUpdate = "last_name"; 
    	    String newValue = "Boss"; 
    	    Admin admin1 = new Admin(1, "Jermy", "Boss", "jermy@gmail.com", "789654123", "jermy123", "415263", "admin", LocalDate.parse("2022-02-19"));
    	    Admin admin2 = new Admin(2, "Jon", "Snow", "snow@gmail.com", "859654123", "snow123", "417263", "super admin", LocalDate.parse("2020-12-09"));
            List<Admin> list = new ArrayList<>();
    	    list.add(admin1);
    	    try {
    	        as.updateAdmin(adminIdToUpdate, fieldToUpdate, newValue);
    	         Admin updatedAdmin = as.getAdminById(list,adminIdToUpdate);
    	         Assert.assertEquals(newValue, updatedAdmin.getLastName());
    	    } catch (InvalidInputException | SQLException | DatabaseConnectionException | AdminNotFoundException e) {
    	        Assert.fail("Unexpected exception occurred: " + e.getMessage());
    	    }
    	}
    

}
