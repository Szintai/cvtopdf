package com.cvtopdf.service;


import org.springframework.http.HttpEntity;

public interface PdfService {



     public HttpEntity<byte[]> createPdf();


}
