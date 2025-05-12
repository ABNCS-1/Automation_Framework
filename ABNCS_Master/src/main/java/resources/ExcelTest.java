package resources;

import java.util.ArrayList;
import java.util.List;

import dataprovider.ExcelReader;

public class ExcelTest {
    // Class-level variables to store the path of the Excel file, the sheet name, and the column name
    String path;
    String sheetName;
    String columnName;

    // Constructor to initialize the ExcelTest object with the specified path, sheet name, and column name
    public ExcelTest(String path, String sheetName, String columnName) {
        this.path = path;
        this.sheetName = sheetName;
        this.columnName = columnName;
    }

    // This method retrieves a list of values from a specified column in the Excel sheet
    // For example, this can be used to fetch test case IDs or data set IDs
    public List<String> getTestCaseTagsfromExcel() {
        // Create an instance of ExcelReader to interact with the specified Excel file
        ExcelReader excelReader = new ExcelReader(path);
        // Initialize a list to hold the retrieved cell data
        List<String> li = new ArrayList<String>();
        
        // Get the total number of rows in the specified sheet
        int rowCount = excelReader.getRowCount(sheetName);
        
        // Loop through the rows starting from the second row (assuming first row is headers)
        for (int i = 2; i <= rowCount; i++) {
            // Retrieve data from the specified column in the current row
            String cellData = excelReader.getCellData(sheetName, columnName, i);
            // Check if the cell data is not blank
            if (!(cellData.isBlank())) {
                // Add non-blank cell data to the list
                li.add(cellData);
            }
        }
        // Return the list of retrieved values
        return li;
    }
}
