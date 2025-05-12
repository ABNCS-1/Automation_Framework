package resources;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CucumberSnippetsCreator {

    // This class will create snippets from feature steps defined in a Gherkin feature file
    public static void main(String[] args) throws IOException {
        // Define the input and output file paths
        String getFilePath = System.getProperty("user.dir") + "\\FeatureSteps\\featureStepsIn";
        String outFilePath = System.getProperty("user.dir") + "\\FeatureSteps\\featureStepsOut";
        
        // Create a File object for the output file
        File outFile = new File(outFilePath);
        // Create a FileWriter to write to the output file
        FileWriter fr = new FileWriter(outFile);
        
        // Read all lines from the input feature file
        List<String> listOfFileLines = Files.readAllLines(Path.of(getFilePath));

        // Check if the feature file is empty
        if (listOfFileLines.size() == 0) {
            System.err.println("There is no content in the feature file");
        } else {
            // Process each line of the feature file
            for (String string : listOfFileLines) {
                string = string.trim(); // Trim whitespace from the line
                
                // Split the line into Gherkin keyword and the rest of the line
                String[] splitGherkinKeyword = string.split(" ");
                int length = splitGherkinKeyword[0].length(); // Get the length of the keyword
                String subString = string.substring(length).trim(); // Get the remaining part of the line
                
                // Check if the keyword is a valid Gherkin keyword
                if ((splitGherkinKeyword[0].trim()).equals("And") || 
                    (splitGherkinKeyword[0].trim()).equals("Then") ||
                    (splitGherkinKeyword[0].trim()).equals("When") ||
                    (splitGherkinKeyword[0].trim()).equals("Given") ||
                    (splitGherkinKeyword[0].trim()).equals("But")) {
                    
                    // Create a regex annotation for the step definition
                    String annotationRegEx = "@" + splitGherkinKeyword[0].trim() + "(\"^" + subString + "$\")";
                    
                    // Create the function signature for the step definition
                    String featureFunction = "public void "
                            + (((subString.replaceAll("[^a-zA-Z0-9_ ]", "")).trim())
                            .replace(" ", "_").trim()).toLowerCase() + "()throws Throwable{";
                    
                    // Write the annotation and function to the output file
                    fr.write(annotationRegEx);
                    fr.write("\n");
                    fr.write(featureFunction);
                    fr.write("\n");
                    fr.write("}"); // Close the function
                    fr.write("\n");
                } else {
                    // Print an error message for invalid Gherkin keywords
                    System.err.println(splitGherkinKeyword[0].trim() + 
                                       " is not a valid gherkin keyword " +
                                       "Line number is " + (listOfFileLines.indexOf(string) + 1));
                }
            }
            // Close the FileWriter after writing all lines
            fr.close();
        }
    }
}
