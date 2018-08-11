package com.siseth.spring.alpha2.utilities;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfDate;
import com.siseth.spring.alpha2.model.Opinion;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.siseth.spring.alpha2.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneratePdfReport {



    public static ByteArrayInputStream opinionsReport(List<Opinion> opinions,Long id,EmployeeService employeeService) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();



        try {

            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(90);
            table.setWidths(new int[]{1, 5, 2});
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");


            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("#", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Body", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Date", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            Integer i = 0;

            for (Opinion opinion:opinions){
                
                i++;
                hcell = new PdfPCell(new Phrase(i.toString()));
                hcell.setVerticalAlignment(Element.ALIGN_TOP);
                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(hcell);

                hcell = new PdfPCell(new Phrase(opinion.getBody()));
                hcell.setPaddingLeft(5);
                hcell.setVerticalAlignment(Element.ALIGN_TOP);
                hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(hcell);

                hcell = new PdfPCell(new Phrase(String.valueOf(opinion.getData().toString())));
                hcell.setVerticalAlignment(Element.ALIGN_TOP);
                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                hcell.setPaddingRight(5);
                table.addCell(hcell);
                
                /*
                PdfPCell cell;

                i++;

                cell = new PdfPCell(new Phrase(i.toString()));
                cell.setVerticalAlignment(Element.ALIGN_TOP);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(opinion.getBody()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_TOP);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(opinion.getData().toString())));
                cell.setVerticalAlignment(Element.ALIGN_TOP);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPaddingRight(5);
                table.addCell(cell);
                */
            }


            Paragraph par1 = new Paragraph(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).toString());
            Paragraph par2 = new Paragraph(employeeService.findById(id).get().getFirstName()+" "+employeeService.findById(id).get().getLastName());

            par1.setSpacingAfter(10);
            par1.setAlignment(Element.ALIGN_CENTER);

            par2.setSpacingAfter(10);
            par2.setAlignment(Element.ALIGN_CENTER);

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(par1);
            document.add(par2);
            document.add(table);
            document.close();

        } catch (DocumentException ex) {

            Logger.getLogger(GeneratePdfReport.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
    }
