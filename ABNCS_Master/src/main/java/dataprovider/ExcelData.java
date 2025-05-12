package dataprovider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData {

	// The sheet name from which data is to be retrieved
	private String TestDataSheetName;
	
	// The column name used for identifying the dataset (like "ID")
	private String TestDataColumnName;
	
	// ExcelReader object for handling Excel operations
	private ExcelReader reader;
	
	// The file path for the Excel file
	private String FilePath;

	/**
	 * Constructor to initialize the Excel data reader with the file path, sheet name, and test data column.
	 * @param FilePath The path to the Excel file
	 * @param TestDataSheetName The name of the sheet containing the test data
	 * @param TestDataColumnName The name of the column used to search for data sets
	 */
	public ExcelData(String FilePath, String TestDataSheetName, String TestDataColumnName) {
		this.TestDataSheetName = TestDataSheetName;
		this.TestDataColumnName = TestDataColumnName;
		this.FilePath = FilePath;
		reader = new ExcelReader(FilePath); // Initialize the ExcelReader with the file path
	}

	/**
	 * This method retrieves test data from the Excel sheet based on the given DataSetID.
	 * It uses the column count and row number to extract values, and stores them in a Map.
	 * @param DataSetID The ID of the data set to be retrieved
	 * @return A Map containing the key-value pairs of the test data
	 */
	public Map<String, String> getTestdata(String TestCaseID) {
		Map<String, String> testdata = new LinkedHashMap<String, String>();

		// Get the column count from the specified sheet
		int columnCount = reader.getColumnCount(TestDataSheetName);
		
		// Get the row number based on the TestDataColumnName and TestCase ID
		int cellRowNum = reader.getCellRowNum(TestDataSheetName, TestDataColumnName, TestCaseID);
		System.out.println("cellRowNum: "+cellRowNum);

		// Iterate over the columns to fetch data
		for (int j = 0; j <= columnCount; j++) {
			// Get the column name (header) for the current column
			String key = reader.getCellData(TestDataSheetName, j, 1);

			// Get the actual cell value from the data set row
			String value = reader.getCellData(TestDataSheetName, j, cellRowNum);
			
			// If the value contains ".0" (common with numeric data), remove it
			if (value.contains(".0")) {
				String[] split = value.split("[.]");
				value = split[0];
			}
			
			// Store the key-value pair in the map
			testdata.put(key, value);

			// Check if the value is blank or empty, and provide info if missing
			if (testdata.get(key).isBlank() || testdata.get(key).isEmpty()) {
				testdata.put(key, "SheetName: " + TestDataSheetName + " RowNum: " + cellRowNum + " ColumnNum: " + j + " ColumnName: " + key);
			}
		}
//		System.out.println("MAP Value"+testdata);
		return testdata;
	}

	/**
	 * Updates the test data in the Excel sheet based on the given DataSetID and column name.
	 * It modifies the value in the specified column and row.
	 * @param DataSetID The ID of the data set to be updated
	 * @param ColumnName The column name where the data will be updated
	 * @param TestDataValue The new value to be set in the Excel cell
	 */
	public void updateTestData(String DataSetID, String ColumnName, String TestDataValue) {
		// Get the row number based on DataSetID
		int cellRowNum = reader.getCellRowNum(TestDataSheetName, TestDataColumnName, DataSetID);
		
		// Update the cell value in the specified column and row
		reader.setCellData(TestDataSheetName, ColumnName, cellRowNum, TestDataValue);
	}

	/**
	 * This method retrieves all test data from the Excel file for all sheets.
	 * It organizes the data into a HashMap, with the structure: SheetName -> DataSetID -> ColumnName -> Value.
	 * @param ColumnName The name of the column used to search for data sets in each sheet
	 * @return A HashMap containing all the test data from the Excel file
	 * @throws IOException if there is an error while reading the file
	 */
	public HashMap<String, HashMap<String, HashMap<String, String>>> getAllTestDataFromExcel(String ColumnName) throws IOException {
		String TestDataColumnName = ColumnName;
		ExcelReader reader = new ExcelReader(FilePath); // Reinitialize the Excel reader
		HashMap<String, HashMap<String, HashMap<String, String>>> test = new HashMap<String, HashMap<String, HashMap<String, String>>>();

		// Open the Excel file as an input stream
		FileInputStream fis = new FileInputStream(FilePath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		// Get the number of sheets in the workbook
		int numberOfSheets = workbook.getNumberOfSheets();

		// Loop through each sheet in the workbook
		for (int i = 0; i < numberOfSheets; i++) {
			String sheetName = workbook.getSheetName(i); // Get the sheet name
			
			// Create a map to store test data for this sheet
			HashMap<String, HashMap<String, String>> testdata1 = new HashMap<String, HashMap<String, String>>();
			HashMap<String, String> testdata = new HashMap<String, String>();
			
			// Get the row count for the current sheet
			int rowCount = reader.getRowCount(sheetName);
			
			// Iterate through all the rows in the sheet
			for (int j = 1; j <= rowCount; j++) {
				String cellData = reader.getCellData(sheetName, TestDataColumnName, j); // Fetch data from the TestDataColumnName
				
				// Get the column count for the current sheet
				int columnCount = reader.getColumnCount(sheetName);
				
				// Get the row number for the current cell data
				int cellRowNum = reader.getCellRowNum(sheetName, TestDataColumnName, cellData);
				
				// Loop through each column in the current row
				for (int k = 1; k <= columnCount; k++) {
					// Fetch the column header (key) and the corresponding value
					String key = reader.getCellData(sheetName, j, 1);
					String value = reader.getCellData(sheetName, j, cellRowNum);
					
					// Remove any trailing ".0" from numeric values
					if (value.contains(".0")) {
						String[] split = value.split("[.]");
						value = split[0];
					}
					
					// Add the key-value pair to the test data map
					testdata.put(key, value);

					// Remove any entries with empty values
					if (testdata.containsValue("")) {
						testdata.remove(reader.getCellData(sheetName, j, 1));
					}
				}
				
				// Store the test data in the map, indexed by the dataset ID (cellData)
				testdata1.put(cellData, testdata);
			}
			
			// Add the test data for the sheet to the main map, indexed by the sheet name
			test.put(sheetName, testdata1);
		}
		
		// Close the workbook
		workbook.close();
		return test;
	}
}
