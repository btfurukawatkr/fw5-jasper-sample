package com.sample.fw5.jasper.app.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

@Component
public class JasperPdfView extends AbstractPdfView {

    private static final Logger logger = LoggerFactory
            .getLogger(JasperPdfView.class);

    @Override
	  protected void buildPdfDocument(Map<String, Object> model,
	          Document document, PdfWriter writer, HttpServletRequest request,
	          HttpServletResponse response) throws Exception {
    	logger.info((String) model.get("test1"));
    	document.add(new Paragraph((String) model.get("test1")));
	}
}
