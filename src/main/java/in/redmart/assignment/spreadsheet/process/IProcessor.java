package in.redmart.assignment.spreadsheet.process;

import java.util.Scanner;

import in.redmart.assignment.spreadsheet.exception.SpreadSheetException;

@FunctionalInterface
public interface IProcessor {

	public static final String valuePatternRegex = "[+-]?\\d+";
	
	public static final String refPatternRegex = "([a-zA-Z]+)(\\d+)";
	
	
	/**
	 * Processor Method to Process the Spread Sheet Calculation
	 * @param scanner
	 * @throws SpreadSheetException
	 */
	public void process(Scanner scanner) throws SpreadSheetException;
	
}
