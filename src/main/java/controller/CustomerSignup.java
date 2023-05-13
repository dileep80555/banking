package controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDao;
import dto.Customer;

@WebServlet("/customersignup")
public class CustomerSignup extends HttpServlet
{
      @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    	String name=req.getParameter("name");
//    	String mobile=req.getParameter("mobile");
//    	String email=req.getParameter("email");
//    	String pwd=req.getParameter("pwd");
//    	String gender=req.getParameter("gender");
//    	String dob=req.getParameter("dob");
    	
//    	resp.getWriter().print("<h1>"+name+"</h1>"
//    			+"<h1>"+mobile+"</h1>"
//    			+"<h1>"+email+"</h1>"
//    			+"<h1>"+pwd+"</h1>"
//    			+"<h1>"+gender+"</h1>"
//    			+"<h1>"+dob+"</h1>"
//    			);
    	  
    	  CustomerDao dao=new CustomerDao();
    	
    	  String email=req.getParameter("email");
    	long mobile=Long.parseLong(req.getParameter("mobile"));
    	
    	Date date=Date.valueOf(req.getParameter("dob"));
    	
    	Period period=Period.between(date.toLocalDate() ,LocalDate.now());
    	int age=period.getYears();
    	if(age<18)
    	{
    		resp.getWriter().print("<h1>You have to be 18+ to create a bank account</h1>");
    		req.getRequestDispatcher("Signup.html").include(req, resp);
    	}
    	else
    	{
    		if(dao.check(mobile).isEmpty()&&dao.check(email).isEmpty())
    		{
    		Customer customer=new Customer();
    		customer.setDob(date);
    		customer.setName(req.getParameter("name"));
    		customer.setGender(req.getParameter("gender"));
    		customer.setPassword(req.getParameter("pwd"));
    		customer.setEmail(email);
    		customer.setMobile(mobile);
    		
    		dao.save(customer);
    		
    		Customer customer2=dao.check(email).get(0);
    		resp.getWriter().print("<h1>Account created successfully</h1>");
    		if(customer2.getGender().equals("male"))
    			resp.getWriter().print("Hello Sir");
    		else
    			resp.getWriter().print("Hello Mam");
    		resp.getWriter().print("<h1>Your customer ID is: "+customer2.getCust_id()+"</h1>");
    		req.getRequestDispatcher("Home.html").include(req, resp);
    		}
    		else
    		{
    			
    			resp.getWriter().print("<h1>Email or phone number already exists</h1>");
        		req.getRequestDispatcher("Signup.html").include(req, resp);
    		}
    	}
    }
}
