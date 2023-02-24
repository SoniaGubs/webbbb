package com.academy;

import com.academy.model.entity.User;
import com.academy.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");

        String password = req.getParameter("password");

        User user = new User(login, password);
        PrintWriter writer = resp.getWriter();

        writer.println("<html>");
        writer.println("<body>");

        LoginService loginService = new LoginService();

        if (loginService.checkCredentials(login, password)) {
            writer.println("Hello " + login);

            HttpSession session = req.getSession();

            session.setAttribute("user", user);

        } else {
            writer.println("Access denied");
        }

        writer.println("</body>");
        writer.println("</html>");

    }
}
