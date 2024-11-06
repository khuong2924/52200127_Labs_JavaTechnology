package com.rs;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.ApplicationContext;


public class MainApplication {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        TextEditor textEditor = context.getBean(TextEditor.class);
        textEditor.input("test hello world - Plain Text");
        textEditor.save("exampleFile");

        TextEditor pdfEditor = new TextEditor(context.getBean("pdfTextWriter", TextWriter.class));
        pdfEditor.input("test hello world - PDF Text");
        pdfEditor.save("examplePDF");
    }
}
