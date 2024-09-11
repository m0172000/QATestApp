package CommonUtlis;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;

public class DataReadClass {
	String filename = "./TestData/TestData.xlsx";

	public String readStringData(String sheetName, int rowNumber, int cellNumber) throws Throwable {
		FileInputStream fis = new FileInputStream(filename);
		String data = new XSSFWorkbookFactory().create(fis).getSheet(sheetName).getRow(rowNumber).getCell(cellNumber)
				.getStringCellValue();
		return data;
	}

	public String readNumericalData(String sheetName, int rowNumber, int cellNumber) throws Throwable {
		FileInputStream fis = new FileInputStream(filename);
		double data = new XSSFWorkbookFactory().create(fis).getSheet(sheetName).getRow(rowNumber).getCell(cellNumber)
				.getNumericCellValue();
		return data + "";
	}

	int a[] = { 10, 20 };
}
