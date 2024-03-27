package com.test;

import com.model.Customer;

import com.service.CustomerService;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.exception.*;
public class CustomerServiceTest {

    CustomerService cs = new CustomerService();

    @Test
    public void getCustomerByIdTest() throws CustomerNotFoundException {
        Customer c1 = new Customer(1,"Ram","Prasad","ram@gmail.com","9024554745","Manjakupam","ramprasad","ram123",LocalDate.parse("2024-03-30"));
        Customer c2 = new Customer(2,"Sandiya","Vishwanath","sandiya@gmail.com","9174543526","main street","sandiyav","sandiya234",LocalDate.parse("2024-08-25"));
        Customer c3 = new Customer(3,"Jayanthi","Selvam","selvam@gmail.com","9082707895","anna nagar","jayanthiselvam","jayselvam456",LocalDate.parse("2023-08-25"));
        Customer c4 = new Customer(4,"Swetha","Seetharaman","swetha@gmail.com","7098645321","Ranipet","swetha01","swethaseetha",LocalDate.parse("2023-04-11"));
        Customer c5 = new Customer(5,"Divya","Dharshini","divya@gmail.com","9123765480","White Town","divya","divyadharshini789",LocalDate.parse("2024-06-14"));
        Customer c6 = new Customer(6,"Nisha","Vaithiyanathan","nisha@gmail.com","9865432178","Madagadipet","nishavaithi","nisha345",LocalDate.parse("2024-07-14"));
        Customer c7 = new Customer(7,"Darshini","Balamurali","darshnini@gmail.com","709834521","Sowkarpet","darshinibala","darshini564",LocalDate.parse("2023-07-15"));
        Customer c8 = new Customer(8,"Agalya","Shanmugam","agalya@gmail.com","8143256790","nehru nagar","agalya67","agalya@34",LocalDate.parse("2023-12-07"));
        Customer c9 = new Customer(9,"Harini","Murugavel","harini@gmail.com","9024554745","Gandhi park","harinimurugan","harini@98",LocalDate.parse("2023-12-16"));
        Customer c10 = new Customer(10,"Selva","Ramaiah","selva@gmail.com","9156473420","Semmandalam","selva1208","selva@08",LocalDate.parse("2024-03-12"));
        Customer c11 = new Customer(11,"balaji","ram","ramasamybalaji98@gmail.com","1112223334","puducherry","bala18","181102",LocalDate.parse("2023-12-18"));
        


        
        List<Customer> list = new ArrayList<>();
        list.add(c1);
        list.add(c2);
        list.add(c3);
        list.add(c4);
        list.add(c5);
        list.add(c6);
        list.add(c7);
        list.add(c8);
        list.add(c9);
        list.add(c10);
        list.add(c11);
        // Test for an existing customer
        int id = 2;
        Customer expectedAdmin = new Customer(2,"Sandiya","Vishwanath","sandiya@gmail.com","9174543526","main street","sandiyav","sandiya234",LocalDate.parse("2024-08-25"));
        try {
            Assert.assertEquals(expectedAdmin, cs.getCustomerById(list, id));
        } catch (Exception e) {
            Assert.fail("Admin not found for the provided id");
        }

        // Test for a non-existing customer
        id = 12;
        try {
            cs.getCustomerById(list, id);
            Assert.fail("Expected AdminNotFoundException to be thrown");
        } catch (InvalidInputException e) {
            Assert.fail("InvalidInputException should not be thrown in this test case");
        }
    }
    @Test
    public void getCustomerByUsername() throws CustomerNotFoundException {
    	Customer c1 = new Customer(1,"Ram","Prasad","ram@gmail.com","9024554745","Manjakupam","ramprasad","ram123",LocalDate.parse("2024-03-30"));
        Customer c2 = new Customer(2,"Sandiya","Vishwanath","sandiya@gmail.com","9174543526","main street","sandiyav","sandiya234",LocalDate.parse("2024-08-25"));
        Customer c3 = new Customer(3,"Jayanthi","Selvam","selvam@gmail.com","9082707895","anna nagar","jayanthiselvam","jayselvam456",LocalDate.parse("2023-08-25"));
        Customer c4 = new Customer(4,"Swetha","Seetharaman","swetha@gmail.com","7098645321","Ranipet","swetha01","swethaseetha",LocalDate.parse("2023-04-11"));
        Customer c5 = new Customer(5,"Divya","Dharshini","divya@gmail.com","9123765480","White Town","divya","divyadharshini789",LocalDate.parse("2024-06-14"));
        Customer c6 = new Customer(6,"Nisha","Vaithiyanathan","nisha@gmail.com","9865432178","Madagadipet","nishavaithi","nisha345",LocalDate.parse("2024-07-14"));
        Customer c7 = new Customer(7,"Darshini","Balamurali","darshnini@gmail.com","709834521","Sowkarpet","darshinibala","darshini564",LocalDate.parse("2023-07-15"));
        Customer c8 = new Customer(8,"Agalya","Shanmugam","agalya@gmail.com","8143256790","nehru nagar","agalya67","agalya@34",LocalDate.parse("2023-12-07"));
        Customer c9 = new Customer(9,"Harini","Murugavel","harini@gmail.com","9024554745","Gandhi park","harinimurugan","harini@98",LocalDate.parse("2023-12-16"));
        Customer c10 = new Customer(10,"Selva","Ramaiah","selva@gmail.com","9156473420","Semmandalam","selva1208","selva@08",LocalDate.parse("2024-03-12"));
        Customer c11 = new Customer(11,"balaji","ram","ramasamybalaji98@gmail.com","1112223334","puducherry","bala18","181102",LocalDate.parse("2023-12-18"));

        List<Customer> list = new ArrayList<>();
        list.add(c1);
        list.add(c2);
        list.add(c3);
        list.add(c4);
        list.add(c5);
        list.add(c6);
        list.add(c7);
        list.add(c8);
        list.add(c9);
        list.add(c10);
        list.add(c11);
        
        String userName="Selva1208";
        CustomerService cs=new CustomerService();
        
        try {
            Customer found = cs.getCustomerByUsername(list, userName);
            Assert.assertEquals(userName, found.getUsername());
        } catch (InvalidInputException e) {
             }

        userName = "bala18";
        try {
            cs.getCustomerByUsername(list, userName);
            Assert.fail("Expected AdminNotFoundException to be thrown");
        } catch (InvalidInputException e) {
            Assert.fail("Invalid input exception occurred");
        }
    }
        	
}