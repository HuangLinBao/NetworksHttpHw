package com.example.networksiihw;

import com.example.Accounts;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init(ServletConfig config) throws ServletException {
        message = "Hello World!";
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        // Read JSON data from the request body
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode rootNode = objectMapper.readTree(request.getReader());
//
//        // Extract data for Student
//        String studentName = rootNode.path("studentName").asText();
//        int studentRollNumber = rootNode.path("studentRollNumber").asInt(); // Change here
//
//        // Extract data for Accounts
//        String accountsName = rootNode.path("accountsName").asText();
//        String accountsPassword = rootNode.path("accountsPassword").asText();
//
//        // Create new entities
//        Student student = new Student();
//        student.setName(studentName);
//        student.setStuID(studentRollNumber);
//
//        Accounts accounts = new Accounts();
//        accounts.setName(accountsName);
//        accounts.setPassword(accountsPassword);
//
//        // Save entities using DAOs
//        StudentDAO studentDAO = new StudentDAO();
//        studentDAO.addStudent(student);
//
//        AccountsDAO accountsDAO = new AccountsDAO();
//        accountsDAO.addAccounts(accounts);
//
//        // Send a success response
//        response.setContentType("application/json");
//        response.getWriter().write("{\"message\": \"Data added successfully\"}");
//    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Extract data from URL parameters
        String accountsName = request.getParameter("name");
        String accountsPassword = request.getParameter("password");
        int stuID = Integer.parseInt(request.getParameter("id"));

        // Create new Accounts entity
        Accounts accounts = new Accounts();
        accounts.setName(accountsName);
        accounts.setStudentID(stuID);
        accounts.setPassword(accountsPassword);

        // Save entity using AccountsDAO
        AccountsDAO accountsDAO = new AccountsDAO();
        accountsDAO.addAccounts(accounts);

        // Send a success response
        response.setContentType("application/json");
        response.getWriter().write("{\"message\": \"Data added successfully\"}");
    }







    public void destroy() {
    }
}