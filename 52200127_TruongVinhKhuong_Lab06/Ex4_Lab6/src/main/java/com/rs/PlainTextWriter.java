package com.rs;

import org.springframework.stereotype.Component;

import org.springframework.stereotype.Component;
import java.io.FileWriter;
import java.io.IOException;

@Component("plainTextWriter")
public class PlainTextWriter implements TextWriter {
    @Override
    public void write(String fileName, String text) {
        try (FileWriter writer = new FileWriter(fileName + ".txt")) {
            writer.write(text);
            System.out.println("Successfully written to " + fileName + ".txt");
        } catch (IOException e) {
            System.err.println("An error occurred while writing to " + fileName + ".txt");
            e.printStackTrace();
        }
    }
}