/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.AccountService;

/**
 *
 * @author 760483
 */
public class PasswordResetServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        if (uuid != null) {
            Logger.getLogger(AccountService.class.getName()).log(Level.INFO, uuid);
            request.setAttribute("uuid", uuid);
            getServletContext().getRequestDispatcher("/WEB-INF/resetNewPassword.jsp").forward(request, response);
            return;
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uuid = request.getParameter("userResetPassword");
         AccountService as = new AccountService();
         Logger.getLogger(AccountService.class.getName()).log(Level.INFO, uuid);
        if (uuid != null) {
            as.changePassword(uuid, request.getParameter("newPassword"));
        }
        
        String url = request.getRequestURL().toString();
        String path = getServletContext().getRealPath("/WEB-INF");
        String email = request.getParameter("resetEmail");
        
       
        as.resetPassword(email, path, url);
        
        response.sendRedirect("login");
    }


}
