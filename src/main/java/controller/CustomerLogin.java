package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDao;
import dto.Customer;

@WebServlet("/clogin")
public class CustomerLogin extends HttpServlet
{
       @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	int custid=Integer.parseInt(req.getParameter("cid"));
    	String pwd=req.getParameter("pwd");
    	
    	CustomerDao customerDao=new CustomerDao();
    	Customer customer=customerDao.login(custid);
    	if(customer==null)
    	{
    		resp.getWriter().print("<h1>Invalid Customer Id</h1>");
    		req.getRequestDispatcher("Login.html").include(req, resp);
    	}
    	else
    	{
    		if(customer.getPassword().equals(pwd))
    		{
    			req.getSession().setAttribute("customer", customer);
    		resp.getWriter().print("<h1>Login success</h1>");
    		req.getRequestDispatcher("CustomerHome.html").include(req, resp);
    		}
    		else
    		{
    			resp.getWriter().print("<h1>Invalid password</h1>");
        		req.getRequestDispatcher("Login.html").include(req, resp);
    		}
    	}
    }
}
