<%@page import="dto.Customer"%>
<%@page import="dto.BankTransaction"%>
<%@page import="java.util.List"%>
<%@page import="dto.BankAccount"%>
<%@page import="dao.BankDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%Customer customer=(Customer) session.getAttribute("customer");
if(customer==null)
{
	response.getWriter().print("<h1>Session Expired Login Again</h1>");
	request.getRequestDispatcher("Login.html").include(request, response);
}
else{
%>
<%
long acno=(Long) session.getAttribute("acno");
BankDao dao=new BankDao();
BankAccount account=dao.find(acno);
List<BankTransaction> list=account.getTransactions();
%>

<h1>Account Number: <%=acno %></h1><br>
<h1>Account Type: <%=account.getType() %></h1>

<table border="1">
<tr>
<th>Transaction_id</th>
<th>Deposit</th>
<th>Withdraw</th>
<th>Balance</th>
<th>Time</th>
</tr>
<%for(BankTransaction transactiom:list) {%>
<tr>
<th><%=transactiom.getId() %></th>
<th><%=transactiom.getDeposit() %></th>
<th><%=transactiom.getWithdraw() %></th>
<th><%=transactiom.getBalance() %></th>
<th><%=transactiom.getDateTime() %></th>
</tr>
<%} %>

<%} %>
</table>
<br>
<a href="AccountHome.jsp"><button>Back</button></a>

</body>
</html>