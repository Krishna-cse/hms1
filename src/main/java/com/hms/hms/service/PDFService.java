package com.hms.hms.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;

@Service

public class PDFService {
    public void generateBookingPDF(String filePath){
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            PdfPTable table = new PdfPTable(8);
            for(int aw = 0; aw < 16; aw++){
                table.addCell("hi");
            }
            document.add(table);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
