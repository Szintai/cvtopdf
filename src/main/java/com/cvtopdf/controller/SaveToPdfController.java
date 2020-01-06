package com.cvtopdf.controller;

import com.cvtopdf.service.PdfServiceImp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SaveToPdfController {

    public final PdfServiceImp pdfServiceImp;

    public SaveToPdfController(PdfServiceImp pdfServiceImp) {
        this.pdfServiceImp = pdfServiceImp;
    }

    @PostMapping("/pdf")
    public String saveToPdf(){

      pdfServiceImp.createPdf();

        return "redirect:/";
    }


}
