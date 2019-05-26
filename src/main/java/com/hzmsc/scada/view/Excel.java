package com.hzmsc.scada.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

public class Excel extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Sheet sheet = workbook.createSheet("Spring");
		sheet.setDefaultColumnWidth((short) 12);
		System.out.println(1);
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellValue(model.get("msg").toString());
		System.out.println(2);
	}

}
