package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	@DataProvider (name = "UserData")
	public String[][] getAllData() throws IOException{
		String path = System.getProperty("user.dir")+"//testData//UserTestData.xlsx";
		ExcelUtility xl = new ExcelUtility(path);
		int rownum = xl.getRowCount("Sheet1");
		int colcount = xl.getCellCount("Sheet1", 1);
		
		String apidata[][] = new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++) {
			for(int j=0;j<colcount;j++) {
				apidata[i-1][j] = xl.getCellData("Sheet1", i, j);
			}
		}
		return apidata;
	}
	
	@DataProvider(name = "ProductData")
	public Object[][] getProductData() throws IOException {
	    String path = System.getProperty("user.dir") + "//testData//UserTestData.xlsx";
	    ExcelUtility xl = new ExcelUtility(path);
	    
	    int rowCount = xl.getRowCount("Sheet2");
	    int colCount = xl.getCellCount("Sheet2", 1); // Assuming first row is header

	    Object[][] productData = new Object[rowCount][colCount];

	    for (int i = 1; i <= rowCount; i++) {
	        for (int j = 0; j < colCount; j++) {
	            productData[i - 1][j] = xl.getCellData("Sheet2", i, j);
	        }
	    }

	    return productData;
	}
	
	@DataProvider(name = "UpdatedProductData")
	public Object[][] getUpdatedProductData() throws IOException {
	    String path = System.getProperty("user.dir") + "//testData//UserTestData.xlsx";
	    ExcelUtility xl = new ExcelUtility(path);
	    
	    int rowCount = xl.getRowCount("Sheet3");
	    int colCount = xl.getCellCount("Sheet3", 1); // Assuming first row is header

	    Object[][] productData = new Object[rowCount][colCount];

	    for (int i = 1; i <= rowCount; i++) {
	        for (int j = 0; j < colCount; j++) {
	            productData[i - 1][j] = xl.getCellData("Sheet3", i, j);
	        }
	    }

	    return productData;
	}
	
	@DataProvider(name = "GetProductNames")
	public Object[][] getNamesOfProducts() throws IOException {
	    String path = System.getProperty("user.dir") + "//testData//UserTestData.xlsx";
	    ExcelUtility xl = new ExcelUtility(path);
	    
	    int rowCount = xl.getRowCount("Sheet3");

	    Object[][] productData = new Object[rowCount][1]; // Only one column per row

	    for (int i = 1; i <= rowCount; i++) {
	        productData[i - 1][0] = xl.getCellData("Sheet3", i, 0); // Only column 0
	    }

	    return productData;
	}
	
}
