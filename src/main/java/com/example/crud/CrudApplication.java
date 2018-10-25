package com.example.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.model.Customer;

@SpringBootApplication
@RestController
@RequestMapping("/")
public class CrudApplication {

	
	
	public static void main(String[] args) {
		
		SpringApplication.run(CrudApplication.class, args);
		 

	}
	@RequestMapping(value="/get", method=RequestMethod.GET)
	public List<Customer> getCustomers(){
	List<Customer> customers=new ArrayList<>();
		try
	    {
	      String myDriver = "com.mysql.jdbc.Driver";
	      String myUrl = "jdbc:mysql://localhost:3306/sba?useSSL=false";
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "admin");
	          
	      String query = "SELECT * FROM customer";

	      Statement st = conn.createStatement();
	   
	      ResultSet rs = st.executeQuery(query);
	    
	      while (rs.next())
	      {
	        int id = rs.getInt("custid");
	        String name = rs.getString("custname");
	        String doj = rs.getString("doj");
	        String city = rs.getString("city");
	        int phone = rs.getInt("contact");
	    
	       customers.add(new Customer(id,name,doj,city, phone));
	        System.out.format("%d %s, %s, %s\n", id, name, doj, city, phone);
	      }
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		return customers;
	}
	}

