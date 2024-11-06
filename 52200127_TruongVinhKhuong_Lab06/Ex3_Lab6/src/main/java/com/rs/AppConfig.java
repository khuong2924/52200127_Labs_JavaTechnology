package com.rs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Qualifier;

@Configuration
public class AppConfig {

    @Bean
    public TextWriter plainTextWriter() {
        return new PlainTextWriter();
    }

    @Bean
    public TextWriter pdfTextWriter() {
        return new PdfTextWriter();
    }

    @Bean
    @Qualifier("plainTextEditor")
    public TextEditor plainTextEditor(@Qualifier("plainTextWriter") TextWriter writer) {
        TextEditor editor = new TextEditor();
        editor.setWriter(writer);
        return editor;
    }

    @Bean
    @Qualifier("pdfTextEditor")
    public TextEditor pdfTextEditor(@Qualifier("pdfTextWriter") TextWriter writer) {
        TextEditor editor = new TextEditor();
        editor.setWriter(writer);
        return editor;
    }
}
