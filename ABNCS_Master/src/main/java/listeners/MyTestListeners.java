package listeners;

import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dataprovider.ConfigFileReader;
import dataprovider.ExcelData;
import dataprovider.ExcelReader;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.Result;
import io.cucumber.plugin.event.TestCaseFinished;
import utilities.ExtentTestManager;

public class MyTestListeners implements ConcurrentEventListener  {
	
	@Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestCaseFinished.class, event -> {
			try {
				handleTestCaseFinished(event);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
    }
	
	// this method handle null pointer exception from excel and throw user defined exception to identify sheet name column name which
	// not contains data

    private void handleTestCaseFinished(TestCaseFinished event) throws Exception {
    	 Result result = event.getResult();
    	 //System.out.println(event.getResult().getStatus().toString());
    	if (event.getResult().getStatus().toString().contains("FAILED")) {
    		 Throwable error = result.getError();
    		 System.out.println(error);
    		 System.out.println("log exception");
    		 ExtentTestManager.logException(error);
    		 ExtentTestManager.logException( getStackTraceAsString(error));
    		 ExtentTestManager.flushReport();
             //System.out.println(error.toString());
             
            if (error.toString().contains("IllegalArgumentException")||error.toString().contains("NullPointerException")||error.toString().contains("SQLSyntaxErrorException")) {
         	   List<String> tags = event.getTestCase().getTags();
            	String CurrentTag = null;
            	for (String string : tags) {
            		CurrentTag=string.replace("@", "");
        		}
            	//System.out.println(CurrentTag);
            	ConfigFileReader configFileReader = new ConfigFileReader();
            	String filePath = System.getProperty("user.dir")+configFileReader.getTestDataFileName();
            	ExcelReader reader = new ExcelReader(filePath);
            	FileInputStream fis = new FileInputStream(filePath);
        		@SuppressWarnings("resource")
     		XSSFWorkbook workbook = new XSSFWorkbook(fis);
        		int numberOfSheets = workbook.getNumberOfSheets();
        		String sheetName =null;
        		for (int i = 0; i < numberOfSheets; i++) {
        			sheetName = workbook.getSheetName(i);
        			//System.out.println(sheetName);
        			String cellData = reader.getCellData(sheetName, 0, 1);
        			//System.out.println(cellData);
        			if (cellData.contains("Test")) {
        				ExcelData excelDataForAccountCreation = new ExcelData(filePath,sheetName, cellData);
        				Map<String, String> testdata = excelDataForAccountCreation.getTestdata(CurrentTag);
        				try {
        					if (testdata.get(cellData).equalsIgnoreCase(CurrentTag)) {
        						break;
        					}
        				} catch (Exception e) {
        					
        				}
        			}
        			
        		}
     		throw new Exception("check the column name(check the exception step and find the column name) existing in excel sheetName "+sheetName);
     	}
		}
    	     

}
    private String getStackTraceAsString(Throwable throwable) {
        StringBuilder stackTrace = new StringBuilder();
        for (StackTraceElement element : throwable.getStackTrace()) {
            stackTrace.append(element.toString()).append("\n");
        }
        return stackTrace.toString();
    }
}
