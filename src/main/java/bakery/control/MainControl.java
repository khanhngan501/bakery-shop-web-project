/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package bakery.control;

import bakery.dao.DAO;
import bakery.entity.Category;
import bakery.entity.Product;
import bakery.util.MailUtilLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "MainControl", urlPatterns = {"/MainControl"})
public class MainControl extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/index.jsp";
        String action = request.getParameter("action");
        String user = request.getParameter("user");
        String email = request.getParameter("email");
        
        DAO dao = new DAO();
        List<Product> list = dao.getAllProducts();
        List<Product> listP1 = dao.getProductByCate1("1");
        List<Product> listP2 = dao.getProductByCate2("2");
        List<Product> listP3 = dao.getProductByCate3("3");
        List<Category> listC = dao.getAllCategory();
        
        request.setAttribute("ListP", list);
        request.setAttribute("ListP1", listP1);
        request.setAttribute("ListP2", listP2);
        request.setAttribute("ListP3", listP3);
        request.setAttribute("ListC", listC);
        if (action == null){
            action = "home";
        }
        if(action.equals("home")){
            url = "/index.jsp";
        }
        else if(action.equals("about")){
            url = "/about.jsp";
        }
        else if (action.equals("menu")){
            url = "/menu.jsp";
        }
        else if(action.equals("reservation")){
            url = "/reservation.jsp";
        }
        else if(action.equals("blog")){
            url = "/blog.jsp";
        }
        else if(action.equals("contact")){
            url = "/contact.jsp";
        }
        else if(action.equals("login")){
            url = "/signIn-signUp.jsp";
        }
        
        System.out.println("Action:" + action);
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
