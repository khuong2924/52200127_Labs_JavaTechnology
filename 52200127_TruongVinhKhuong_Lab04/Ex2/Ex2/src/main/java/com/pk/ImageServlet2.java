package com.pk;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/image2")
public class ImageServlet2 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // url to img
        String imagePath = getServletContext().getRealPath("/images/img2.png");

        File imageFile = new File(imagePath);
        response.setContentType("image/png");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + imageFile.getName() + "\"");

        try (FileInputStream in = new FileInputStream(imageFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, bytesRead);
            }
        }
    }
}

