package com.tsystems.javaschool.SBB;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Main {
    public static void main(String[] args) {
        Document document = new Document();
        try{
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("e-ticket.pdf"));
            document.open();
            document.add(new Paragraph("Welcome To SBB"));
            document.close();
            pdfWriter.close();

        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
    }
}
