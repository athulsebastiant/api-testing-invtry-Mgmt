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
	
	@DataProvider(name = "EmailPasswordData")
    public Object[][] getEmailPasswordData() throws IOException {
        String path = System.getProperty("user.dir") + "/testData/UserTestData.xlsx";
        ExcelUtility xl = new ExcelUtility(path);

        int rowCount = xl.getRowCount("Sheet1");
        int colCount = xl.getCellCount("Sheet1", 1);  // row 1 assumed to be first data row (not header)

        Object[][] data = new Object[rowCount][2]; // only email and password

        for (int i = 1; i <= rowCount; i++) {
            String email = xl.getCellData("Sheet1", i, 1);    // column 1: email
            String password = xl.getCellData("Sheet1", i, 2); // column 2: password

            data[i - 1][0] = email;
            data[i - 1][1] = password;
        }

        return data;
    }
}
