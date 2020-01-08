package com.cvtopdf.service;


import com.cvtopdf.entity.User;
import com.itextpdf.text.DocumentException;
import org.springframework.http.HttpEntity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

public interface PdfService {



     public void createPdf(User user) throws IOException, DocumentException;


}
