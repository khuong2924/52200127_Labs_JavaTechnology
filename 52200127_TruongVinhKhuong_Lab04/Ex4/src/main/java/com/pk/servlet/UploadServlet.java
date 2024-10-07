package com.pk.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {

    private static final String UPLOAD_DIR = "uploads";
    private static final String[] SUPPORTED_EXTENSIONS = { "txt", "doc", "docx", "img", "pdf", "rar", "zip" };

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("name");
        Part filePart = request.getPart("document");


        String fileExtension = getFileExtension(filePart.getSubmittedFileName());
        if (!isSupportedExtension(fileExtension)) {
            response.getWriter().write("Phần mở rộng tệp không được hỗ trợ");
            return;
        }


        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        File fileToSave = new File(uploadPath + File.separator + fileName);


        boolean override = request.getParameter("override") != null;
        if (fileToSave.exists() && !override) {
            response.getWriter().write("Tệp đã tồn tại");
            return;
        }


        filePart.write(fileToSave.getAbsolutePath());


        response.getWriter().write("Tệp đã tải lên. Bấm vào đây để <a href='uploads/" + fileName + "'>visit file</a>.");
    }

    private boolean isSupportedExtension(String extension) {
        for (String supported : SUPPORTED_EXTENSIONS) {
            if (supported.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }
}
