package com.pk.servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.io.IOException;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");

        if (page == null || page.isEmpty()) {

            response.getWriter().write("Chào mừng bạn đến với trang web của chúng tôi");
        } else {
            String view = null;
            switch (page) {
                case "about":
                    view = "about.jsp";
                    break;
                case "contact":
                    view = "contact.jsp";
                    break;
                case "help":
                    view = "help.jsp";
                    break;
                default:
                    view = "about.jsp"; // Hoặc có thể trả về một trang lỗi 404
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(view);
            dispatcher.forward(request, response);
        }
    }
}

