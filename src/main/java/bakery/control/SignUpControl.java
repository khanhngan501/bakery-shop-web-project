/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package bakery.control;

import Singleton.AccountSingleton;
import bakery.dao.DAO;
import bakery.entity.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SignUpControl", urlPatterns = {"/signup"})
public class SignUpControl extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        String user = request.getParameter("user");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String confirm = request.getParameter("confirm");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        if(!pass.equals(confirm)){
            response.sendRedirect("signIn-signUp.jsp");
        }
        else{
            DAO dao = new DAO();
            AccountSingleton accountsingleton = new AccountSingleton();
            Account acc = accountsingleton.checkAccountExist(user);
            
            Account accountexist = dao.checkAccountExistbyEmail(email);
            
            if (acc == null){
                if(accountexist == null){
                    if(pass.equals(confirm)){
                        dao.signup(user, pass, phone, email, phone, address);
                        response.sendRedirect("MainControl");
                    }
                    else{
                        request.setAttribute("mess", "Confirm password does not match. Please try again!");
                        request.getRequestDispatcher("signIn-signUp.jsp").forward(request, response);
                    }
                }
                else{
                        request.setAttribute("mess", "Email aldready exists! Please try again!");
                        request.getRequestDispatcher("signIn-signUp.jsp").forward(request, response);
                }
            }
            else{
               request.setAttribute("mess", "User aldready exists! If you already have an account, please login");
               request.getRequestDispatcher("signIn-signUp.jsp").forward(request, response);
            }
        }
    }
}
