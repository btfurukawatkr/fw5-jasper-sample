package com.sample.fw5.jasper.app.view;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.terasoluna.gfw.web.download.AbstractFileDownloadView;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRCsvDataSource;

@Component
public class JasperPdfView extends AbstractFileDownloadView {

	@Override
	protected InputStream getInputStream(Map<String, Object> model, HttpServletRequest request) throws IOException {
		byte[] report = null;
		try {
			JasperReport jasperReport = JasperCompileManager.compileReport(new ClassPathResource("/report/sample.jrxml").getInputStream());
			Map<String, Object> params = new HashMap<>();
			JRCsvDataSource csvData = new JRCsvDataSource(new ClassPathResource("/report/data/data.csv").getInputStream());
			csvData.setUseFirstRowAsHeader(true);
			JasperPrint print = JasperFillManager.fillReport(jasperReport, params, csvData);
			report = JasperExportManager.exportReportToPdf(print);
		} catch (JRException e) {
			e.printStackTrace();
		}
		return new ByteArrayInputStream(report);
	}

	@Override
	protected void addResponseHeader(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) {
		response.setHeader("Content-Disposition", "attatchment; filename=sample.pdf");
		response.setContentType("application/pdf");
	}

}
