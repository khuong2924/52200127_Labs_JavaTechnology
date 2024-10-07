package com.pk;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("file");
        String speedParam = request.getParameter("speed");
        int speed = (speedParam != null) ? Integer.parseInt(speedParam) : Integer.MAX_VALUE;

        if (fileName == null || fileName.isEmpty()) {
            response.getWriter().write("Không tìm thấy tệp");
            return;
        }

        File file = new File(getServletContext().getRealPath("/files/" + fileName));
        if (!file.exists()) {
            response.getWriter().write("Tệp không tồn tại");
            return;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

        // read and send to client with toc do gioi han
        try (FileInputStream in = new FileInputStream(file)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            long startTime;
            while ((bytesRead = in.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, bytesRead);

                // Gioi han toc do tai xuong
                startTime = System.currentTimeMillis();
                Thread.sleep(Math.max(0, (bytesRead * 1000 / speed) - (System.currentTimeMillis() - startTime)));
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
