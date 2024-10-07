package src.main.java;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
@WebServlet("/")
public class LoginServlet extends HttpServlet {
    private HashMap<String, String> users;

    public void init() throws ServletException {
        users = new HashMap<>();
        users.put("user1", "12345");
        users.put("user2", "123456");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("index.jsp"); //return ve trang login
        response.getWriter().write("Login Page");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String msg;
        boolean isLoginAttempt = false;

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();


        if (users.containsKey(username) && users.get(username).equals(password)) {
            msg = "<h8>Xac thuc thanh cong</h8>";
        } else {
            msg ="<h8>Xac thuc khong thanh cong<h8>";
        }

        request.setAttribute("isLoginAttempt", true);
        request.setAttribute("message", msg);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }



}