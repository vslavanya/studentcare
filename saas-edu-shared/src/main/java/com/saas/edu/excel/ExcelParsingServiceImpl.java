package com.saas.edu.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saas.edu.service.SchoolAdminService;

@Service
public class ExcelParsingServiceImpl implements ExcelParsingService{
	@Autowired
	private SchoolAdminService schoolAdminService;
	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelParsingServiceImpl.class);
	public static void main(String[] args) {
		try {
			new ExcelParsingServiceImpl()
					.parseGradeExcel(new FileInputStream(new File("C:\\Users\\archana\\git\\school\\saas_docs\\Excl_templates\\Grd_dvsn.xlsx")));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	


	public List parseExcel(InputStream inputStream) {
		XSSFRow xssfRow;
		XSSFWorkbook StdWorkBook;
		XSSFSheet StdSpreadsheet;
		List rows = new ArrayList();
		try {
			StdWorkBook = new XSSFWorkbook(inputStream);
			StdSpreadsheet = StdWorkBook.getSheetAt(0);
			int rowStart = Math.min(15, StdSpreadsheet.getFirstRowNum());
			int rowEnd = Math.max(1400, StdSpreadsheet.getLastRowNum());
			for (int rowNum = rowStart + 1; rowNum < rowEnd; rowNum++) {
				xssfRow = StdSpreadsheet.getRow(rowNum);
				if (xssfRow == null) {
					continue;
				}
				List row = new ArrayList();
				int lastColumn = Math.max(xssfRow.getLastCellNum(), 12);
				for (int cn = 0; cn < lastColumn; cn++) {
					XSSFCell xssfCell = xssfRow.getCell(cn, Row.RETURN_NULL_AND_BLANK);
					if(xssfCell!=null){
						DataFormatter formatter = new DataFormatter();
						String cellValue = formatter.formatCellValue(xssfCell);
						row.add(cellValue);
					}
				}
				rows.add(row);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//return processRows(rows);
		
		return rows;
		
	}

	private Map <String, String[]> processGradeDivisionRows(List rows) {
		Map <String, String[]> gradeDivMap = new HashMap();
		for (int i = 0; i < rows.size(); i++) {
			List row = (List) rows.get(i);
			String grade = (String) row.get(0);
			String divisions = (String) row.get(1);
			System.out.println(divisions);
			String division[] = divisions.split(",");
			gradeDivMap.put(grade, division);
			
		}
		System.out.println(gradeDivMap.toString());
		return gradeDivMap;
	}



	@Override
	public void parseExcel(InputStream inputStream, String requestType, String schoolCode) {
		
		switch(requestType){
		
			case "GRADE-DIVISION":
				LOGGER.debug("Parsing Excel Type {}",requestType);
				List rows = parseExcel(inputStream);
				Map <String, String[]> gradeDivMap = processGradeDivisionRows(rows);
				schoolAdminService.createGradeDivisionMap(gradeDivMap, schoolCode);
			break;
		}
		
	}




	@Override
	public void parseGradeExcel(InputStream inputStream) {
		// TODO Auto-generated method stub
		
	}

}
