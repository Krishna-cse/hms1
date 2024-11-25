package com.hms.hms.controller;

import com.hms.hms.service.PDFService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {
    private PDFService pdfService;

    public BookingController(PDFService pdfService) {
        this.pdfService = pdfService;
    }


    @PostMapping("/create-booking")
    public String createBookings(){
        pdfService.generateBookingPDF("E:\\hms_booking\\test.pdf");
        return "PDF created successfully";
    }
}
