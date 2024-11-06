package com.rs;

import com.rs.TextEditor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApplication {

    public static void main(String[] args) {
        //Spring context
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        //PlainTextWriter
        TextEditor plainTextEditor = context.getBean("plainTextEditor", TextEditor.class);
        System.out.println("test PlainTextWriter:");
        plainTextEditor.input("plain text content.");
        plainTextEditor.save("plain_text_output.txt");

        //PdfTextWriter
        TextEditor pdfTextEditor = context.getBean("pdfTextEditor", TextEditor.class);
        System.out.println("\ntest PdfTextWriter:");
        pdfTextEditor.input("PDF text content.");
        pdfTextEditor.save("pdf_text_output");

        // đóng context
        context.close();
    }
}
