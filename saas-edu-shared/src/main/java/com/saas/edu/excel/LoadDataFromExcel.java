
package com.saas.edu.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.saas.edu.dto.StudentDto;

public class LoadDataFromExcel {

	public static void main(String[] args) {
		try {
			List<StudentDto> studentDetailsList = new LoadDataFromExcel()
					.LoadStudentDetails(new FileInputStream(new File("C:\\Users\\archana\\Documents\\StudentDeatils.xlsx")));
			for (StudentDto studentDto : studentDetailsList) {
				System.out.println(studentDto.toString());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<StudentDto> LoadStudentDetails(InputStream Stdfi) {
		XSSFRow xssfRow;
		XSSFWorkbook StdWorkBook;
		XSSFSheet StdSpreadsheet;
		List StDetails = new ArrayList();
		try {
			StdWorkBook = new XSSFWorkbook(Stdfi);
			StdSpreadsheet = StdWorkBook.getSheetAt(0);
			int rowStart = Math.min(15, StdSpreadsheet.getFirstRowNum());
			int rowEnd = Math.max(1400, StdSpreadsheet.getLastRowNum());

			for (int rowNum = rowStart + 1; rowNum < rowEnd; rowNum++) {
				xssfRow = StdSpreadsheet.getRow(rowNum);
				if (xssfRow == null) {
					continue;
				}
				List StCellData = new ArrayList();
				int lastColumn = Math.max(xssfRow.getLastCellNum(), 12);
				for (int cn = 0; cn < lastColumn; cn++) {
					XSSFCell xssfCell = xssfRow.getCell(cn, Row.CREATE_NULL_AS_BLANK);
					StCellData.add(xssfCell.toString());
				}
				StDetails.add(StCellData);
			}
		} catch (Exception e) {
			System.out.println("Error in Reading StudentDetails!");
		}

		return loadStudentDetails(StDetails);

	}

	private List<StudentDto> loadStudentDetails(List StDetails) {
		// int RollNo, SiblingRollNo;
		// String StudentFirstName, StudentLastName, Sex, DivisionId,
		// ParentFirstName;
		// String ParentLastName, ParentAddress, ParentEmailId, Sibling,
		// ParentContactNo;

		List<StudentDto> studentDetailsList = new ArrayList<>();

		for (int i = 0; i < StDetails.size(); i++) {
			StudentDto studentDto = new StudentDto();
			List row = (List) StDetails.get(i);
			studentDto.setRollNum((String) row.get(ExcelConstants.STD_DET_EXCEL_ROL_NUM_INDX));
			studentDto.setFirstName((String) row.get(ExcelConstants.STD_DET_EXCEL_FRST_NME_INDX));

			// (String)row.get(ExcelConstants.STD_DET_EXCEL_ROL_NUM_INDX);
			// StudentFirstName = (String)
			// row.get(ExcelConstants.STD_DET_EXCEL_FRST_NME_INDX);
			// StudentLastName = (String) row.get(2);
			// Sex = (String) row.get(3);
			// DivisionId = (String) row.get(4);
			// ParentFirstName = (String) row.get(5);
			// ParentLastName = (String) row.get(6);
			// ParentAddress = (String) row.get(7);
			// ParentContactNo = (String) row.get(8);
			// ParentEmailId = (String) row.get(9);

			studentDetailsList.add(studentDto);
		}
		return studentDetailsList;
	}

}
