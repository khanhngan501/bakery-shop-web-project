/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package bakery.control;

import bakery.dao.DAO;
import bakery.entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "LoginControl", urlPatterns = {"/signin"})
public class LoginControl extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("user");
        String password = request.getParameter("pass");
        
        DAO dao = new DAO();
        Account a = dao.login(username, password);
       
        if(a == null){
            request.setAttribute("mess", "Wrong username or password");
            request.getRequestDispatcher("signIn-signUp.jsp").forward(request, response);
        }
        else{
           HttpSession session = request.getSession();
           session.setAttribute("acc", a);
           
           session.setAttribute("userid", a.getAccountId());
           session.setAttribute("username", a.getUserName());
           session.setAttribute("pass", a.getPassword());
           session.setAttribute("fullname", a.getFullName());
           session.setAttribute("email", a.getEmail());
           session.setAttribute("phone", a.getPhone());
           session.setAttribute("address", a.getAddress());
           
           request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    
    
    

}
