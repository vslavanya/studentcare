package com.saas.edu.excel;

import java.io.InputStream;

public interface ExcelParsingService {
	public void parseGradeExcel(InputStream inputStream);
	
	public void parseExcel(InputStream inputStream, String requestType, String schoolCode);

}
