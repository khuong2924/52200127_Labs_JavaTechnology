package com.rs;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.pdf.PdfDocument;

import java.io.FileNotFoundException;

public class PdfTextWriter implements TextWriter {

    @Override
    public void write(String fileName, String text) {
        String pdfFileName = fileName.endsWith(".pdf") ? fileName : fileName + ".pdf";
        try {
            PdfWriter writer = new PdfWriter(pdfFileName);

            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            document.add(new Paragraph(text));

            document.close();
            System.out.println("Text written to PDF file: " + pdfFileName);
        } catch (FileNotFoundException e) {
            System.err.println("Error: Unable to write to file " + pdfFileName);
            e.printStackTrace();
        }
    }
}
