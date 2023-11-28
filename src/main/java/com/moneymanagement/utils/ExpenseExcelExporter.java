package com.moneymanagement.utils;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.moneymanagement.dto.ExpenseDTO;

public class ExpenseExcelExporter {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<ExpenseDTO> listExpense;

	public ExpenseExcelExporter(List<ExpenseDTO> listExpense) {
		this.listExpense = listExpense;
		workbook = new XSSFWorkbook();
	}

	private void writeHeaderLine() {
		sheet = workbook.createSheet("Expense");

		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		createCell(row, 0, "#", style);
		createCell(row, 1, "Created Date", style);
		createCell(row, 2, "Modified Date", style);
		createCell(row, 3, "Amount", style);
		createCell(row, 4, "Description", style);
		createCell(row, 5, "Category", style);
		

	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Long) {
			cell.setCellValue((Long) value);
		} else if (value instanceof Double) {
			cell.setCellValue((Double) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		}else if (value instanceof Date) {
			cell.setCellValue((Date) value);
		} 
		else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}
	
	private void writeDataLines() {
        int rowCount = 1;
 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
                 
        for (ExpenseDTO dto : listExpense) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            int number = 1;
             
            createCell(row, columnCount++, number++, style);
            createCell(row, columnCount++, dto.getCreatedDate(), style);
            createCell(row, columnCount++, dto.getModifiedDate(), style);
            createCell(row, columnCount++, dto.getAmount(), style);
            createCell(row, columnCount++, dto.getDescription(), style);
            createCell(row, columnCount++, dto.getCategoryDTO().getName(), style);
            
            
             
        }
    }
	public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }

}
