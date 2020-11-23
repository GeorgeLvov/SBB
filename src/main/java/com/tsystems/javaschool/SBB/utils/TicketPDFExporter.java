package com.tsystems.javaschool.SBB.utils;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.tsystems.javaschool.SBB.entities.Ticket;


import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TicketPDFExporter {

    static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm\n dd.MM.yyyy");
    static DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private Ticket ticket;

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }


    public void export(HttpServletResponse response)  {

        int  ticketId = ticket.getId();
        boolean valid = ticket.isValid();
        String trainName = ticket.getTrain().getTrainName();
        String stationFrom = ticket.getStationFrom().getTitle();
        String stationTo = ticket.getStationTo().getTitle();
        String departureTime = sdf.format(ticket.getDepartureTime());
        String arrivalTime = sdf.format(ticket.getArrivalTime());

        String firstName = ticket.getPassenger().getFirstName();
        String lastName = ticket.getPassenger().getLastName();
        String birthdate = df.format(ticket.getPassenger().getBirthDate());

        Document document = new Document(PageSize.A4);
        PdfWriter writer = null;
        try {
            writer = PdfWriter.getInstance(document, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        document.open();

        PdfPTable table1 = new PdfPTable(6);
        table1.setWidthPercentage(100);

        java.util.List<PdfPCell> cells = new ArrayList<>();
        PdfPCell cellFirst = new PdfPCell();
        Image jpg = null;

        try {
           jpg = Image.getInstance("C:\\Users\\User\\IdeaProjects\\SBB\\src\\main\\webapp\\res\\img\\sbbBadge.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        jpg.scalePercent(25f);
        cellFirst.addElement(jpg);
        cells.add(cellFirst);

        PdfPCell cell2 = new PdfPCell();
        cell2.setPaddingLeft(0);
        cell2.addElement(new Phrase("SBB CFF FFS", new Font(Font.HELVETICA, 24, 1)));
        cell2.setColspan(3);
        cells.add(cell2);

        Collections.addAll(cells, new PdfPCell(), new PdfPCell(), new PdfPCell(), new PdfPCell());

        for (PdfPCell cell : cells) {
            cell.setBorderWidthTop(0);
            cell.setBorderWidthLeft(0);
            cell.setBorderWidthRight(0);
            cell.setPaddingBottom(20);
            cell.setBorderColor(Color.lightGray);
            table1.addCell(cell);
        }

        table1.setSpacingAfter(40);
        document.add(table1);

        Paragraph paragraph1 = new Paragraph("E-TICKET #" + ticketId, new Font(Font.HELVETICA, 28));
        paragraph1.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph1);
        Paragraph paragraph2  = null;
        if(valid){
            paragraph2 = new Paragraph("VALID", new Font(Font.HELVETICA, 24, Font.NORMAL, Color.GRAY));
        }
        else paragraph2 = new Paragraph("INVALID",new Font(Font.HELVETICA, 24, Font.NORMAL, Color.lightGray));

        paragraph2.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph2);

        document.add(Chunk.NEWLINE);

        PdfPTable table2 = new PdfPTable(5);
        table2.setWidthPercentage(100);

        List<PdfPCell> cellList = new ArrayList<>();
        Font font = new Font(Font.HELVETICA, 14, Font.BOLD, Color.GRAY);

        PdfPCell trainCell = new PdfPCell(new Phrase("Train", font));

        PdfPCell fromCell = new PdfPCell(new Phrase("From", font));

        PdfPCell toCell = new PdfPCell(new Phrase("To", font));

        PdfPCell departureCell = new PdfPCell(new Phrase("Departure", font));

        PdfPCell arrivalCell = new PdfPCell(new Phrase("Arrival", font));

        Collections.addAll(cellList, trainCell, fromCell, toCell, departureCell, arrivalCell);

        for (PdfPCell cell : cellList) {
            cell.setBorderWidthTop(0);
            cell.setBorderColor(Color.lightGray);
            cell.setPaddingBottom(20);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            if(cell.getPhrase().getContent().equals("Train")){
                cell.setBorderWidthLeft(0);
            }
            if(cell.getPhrase().getContent().equals("Arrival")){
                cell.setBorderWidthRight(0);
            }
            table2.addCell(cell);
        }

        List<PdfPCell> valuesList = new ArrayList<>();

        Font font1 = new Font(Font.HELVETICA, 14);

        PdfPCell trainCellVal = new PdfPCell(new Phrase(trainName, font1));

        PdfPCell fromCellVal = new PdfPCell(new Phrase(stationFrom, font1));

        PdfPCell toCellVal = new PdfPCell(new Phrase(stationTo, font1));

        PdfPCell departureCellVal = new PdfPCell(new Phrase(departureTime, font1));

        PdfPCell arrivalCellVal = new PdfPCell(new Phrase(arrivalTime, font1));

        Collections.addAll(valuesList, trainCellVal, fromCellVal, toCellVal, departureCellVal, arrivalCellVal);

        for (PdfPCell cell : valuesList) {
            cell.setBorderWidthBottom(0);
            cell.setBorderColor(Color.lightGray);
            cell.setPaddingTop(17);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            if (cell.getPhrase().getContent().equals(departureTime) || cell.getPhrase().getContent().equals(arrivalTime)) {
                if (cell.getPhrase().getContent().equals(arrivalTime)) {
                    cell.setBorderWidthRight(0);
                }
                cell.setPaddingTop(12);
            }
            if (cell.getPhrase().getContent().equals(trainName)) {
                cell.setBorderWidthLeft(0);
            }

            table2.addCell(cell);
        }

        document.add(table2);

        document.add(Chunk.NEWLINE);

        PdfPTable table3 = new PdfPTable(3);
        table2.setWidthPercentage(100);

        java.util.List<PdfPCell> list = new ArrayList<>();

        PdfPCell nameCell = new PdfPCell(new Phrase("Name", font));

        PdfPCell surNameCell = new PdfPCell(new Phrase("Surname", font));

        PdfPCell birthCell = new PdfPCell(new Phrase("Date of Birth", font));

        Collections.addAll(list, nameCell, surNameCell, birthCell);

        for (PdfPCell cell : list) {
            cell.setBorderWidthTop(0);
            cell.setBorderColor(Color.lightGray);
            cell.setPaddingBottom(20);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            if(cell.getPhrase().getContent().equals("Name")){
                cell.setBorderWidthLeft(0);
            }
            if(cell.getPhrase().getContent().equals("Date of Birth")){
                cell.setBorderWidthRight(0);
            }
            table3.addCell(cell);
        }

        List<PdfPCell> valuesPass = new ArrayList<>();

        PdfPCell nameVal = new PdfPCell(new Phrase(firstName, font1));

        PdfPCell surNameVal = new PdfPCell(new Phrase(lastName, font1));

        PdfPCell birthVal = new PdfPCell(new Phrase(birthdate, font1));

        Collections.addAll(valuesPass, nameVal, surNameVal, birthVal);

        for (PdfPCell cell : valuesPass) {
            cell.setBorderWidthBottom(0);
            cell.setBorderColor(Color.lightGray);
            cell.setPaddingTop(17);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            if (cell.getPhrase().getContent().equals(birthdate)) {
                cell.setBorderWidthRight(0);
            }
            if (cell.getPhrase().getContent().equals(firstName)) {
                cell.setBorderWidthLeft(0);
            }

            table3.addCell(cell);
        }
        table3.setSpacingAfter(140);
        document.add(table3);

        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);

        Paragraph paragraph3  = new Paragraph("Swiss Federal Railways, 2020", new Font(Font.HELVETICA, 10,
                Font.NORMAL));
        paragraph3.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph3);

        document.close();
        writer.close();
    }
}
