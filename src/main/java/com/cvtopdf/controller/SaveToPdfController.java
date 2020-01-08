package com.cvtopdf.controller;

import com.cvtopdf.entity.User;
import com.cvtopdf.service.PdfService;
import com.cvtopdf.service.UserDetailsImpl;
import com.cvtopdf.service.UserService;
import com.itextpdf.text.DocumentException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;


@Controller
public class SaveToPdfController {

    public final PdfService pdfService;

    private final UserService userService;

    private User authorizedUser;

    public SaveToPdfController(PdfService pdfService, UserService userService) {
        this.pdfService = pdfService;
        this.userService = userService;
    }

    @GetMapping("/pdf")
    public String saveToPdf() throws IOException, DocumentException {


        authorizedUser=((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        User user= userService.findById(authorizedUser.getId());


      pdfService.createPdf(user);

        return "redirect:/profile/cv";
    }


}
