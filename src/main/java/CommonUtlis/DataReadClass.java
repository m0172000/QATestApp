package CommonUtlis;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;

public class DataReadClass {
	
	public String readStringData(String sheetName, int rowNumber, int cellNumber) throws Throwable {
		FileInputStream fis = new FileInputStream("./TestData/TestData.xlsx");
		String data = new XSSFWorkbookFactory().create(fis).getSheet(sheetName).getRow(rowNumber).getCell(cellNumber).getStringCellValue();
		return data;
	}
	
	public String readNumericalData(String sheetName, int rowNumber, int cellNumber) throws Throwable {
		FileInputStream fis = new FileInputStream("./TestData/testData.xlsx");
		double data = new XSSFWorkbookFactory().create(fis).getSheet(sheetName).getRow(rowNumber).getCell(cellNumber).getNumericCellValue();
		return data+"";
	}
int a[]= {10,20};
}


