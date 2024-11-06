package com.rs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class TextEditor {
    private String text;
    private final TextWriter writer;

    @Autowired
    public TextEditor(@Qualifier("plainTextWriter") TextWriter writer) {
        this.writer = writer;
    }

    public void input(String text) {
        this.text = text;
    }

    public void save(String fileName) {
        writer.write(fileName, text);
    }
}