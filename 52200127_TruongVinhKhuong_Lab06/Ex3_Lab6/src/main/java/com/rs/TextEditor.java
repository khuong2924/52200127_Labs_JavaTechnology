package com.rs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class TextEditor {
    private String text;
    private TextWriter writer;

    public void input(String text) {
        this.text = text;
        System.out.println("Input text: " + text);
    }

    public void save(String fileName) {
        if (writer != null && text != null) {
            writer.write(fileName, text);
            System.out.println("Content saved to " + fileName);
        } else {
            System.out.println("No writer or text to save.");
        }
    }

    @Autowired
    public void setWriter(@Qualifier("pdfTextWriter") TextWriter writer) {
        this.writer = writer;
    }
}
