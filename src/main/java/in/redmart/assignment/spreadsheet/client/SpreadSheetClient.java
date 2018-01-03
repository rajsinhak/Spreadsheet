package in.redmart.assignment.spreadsheet.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import in.redmart.assignment.spreadsheet.exception.SpreadSheetException;
import in.redmart.assignment.spreadsheet.process.IProcessor;
import in.redmart.assignment.spreadsheet.process.SpreadSheetProcessor;

/**
 * Client Class to read and evaluate the SpreadSheet Data
 * @author Raj
 * @version 1.0
 */
public class SpreadSheetClient {
	
	/**
	 * Main Method for application entry point
	 * @param strings
	 * 		  File Path
	 */
	public static void main(String...strings){
		System.out.println("SpreadSheetClient :: Executing Main Proccess");
		File dataFile = null;
		Scanner sc = null;
		IProcessor spreadSheetProcessor = new SpreadSheetProcessor();
		try {
			//dataFile = new File(strings[0]);
			dataFile = new File("src/main/resources/spreedsheet.txt");
			sc = new Scanner(dataFile);
			spreadSheetProcessor.process(sc);
					
		} catch (FileNotFoundException ex) {
			System.err.println(ex.getMessage());
		}catch (SpreadSheetException ex) {
			System.err.println(ex.getExceptionType().name() +  " ::  " + ex.getErrorMessage().getErrorMessage());
		}finally{
			System.out.println("\nSpreadSheetClient :: Exit from Main Proccess");
			
		}
	}

}
