package com.pk.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            response.sendRedirect("index.jsp");
            return;
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean rememberMe = request.getParameter("remember") != null;

        if (authenticate(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            if (rememberMe) {
                Cookie usernameCookie = new Cookie("username", username);
                Cookie passwordCookie = new Cookie("password", password);
                usernameCookie.setMaxAge(30 * 24 * 60 * 60); // 30 days
                passwordCookie.setMaxAge(30 * 24 * 60 * 60);
                response.addCookie(usernameCookie);
                response.addCookie(passwordCookie);
            }

            response.sendRedirect("index.jsp");
        } else {
            request.setAttribute("error", "username hoặc password không chính xác.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private boolean authenticate(String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lab5_java", "root", "123456");

            String query = "SELECT password FROM users WHERE username = ?";
            try (PreparedStatement pstate = conn.prepareStatement(query)) {
                pstate.setString(1, username);

                try (ResultSet rs = pstate.executeQuery()) {
                    if (rs.next()) {
                        String storedPassword = rs.getString("password");
                        return password.equals(storedPassword);
                    }
                }
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
