package in.redmart.assignment.spreadsheet.process;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Stream;

import in.redmart.assignment.spreadsheet.exception.ErrorMessages;
import in.redmart.assignment.spreadsheet.exception.SpreadSheetException;
import in.redmart.assignment.spreadsheet.exception.SpreadSheetException.ExceptionType;
import in.redmart.assignment.spreadsheet.model.WorkBook;
import in.redmart.assignment.spreadsheet.model.WorkCell;

/**
 * Processor Class to validate and process the work book
 * @author Raj
 *
 */
public class SpreadSheetProcessor implements IProcessor  {
	
	private WorkBook workbook;
	
	@Override
	public void process(Scanner scanner) throws SpreadSheetException {
		String[] indices = isSheetValid(scanner);
		workbook = new WorkBook();
		workbook.initialize(indices);
		workbook.populateWorkBook(scanner);
		evaluateWorkBook();
		workbook.printEvaluatedWorkSheet();
		
	}
	
	/**
	 * Method to check if the provided file is valid spreadsheet
	 * @param scanner
	 * @return indices
	 */
	private String[] isSheetValid(Scanner scanner){
		String[] sheetIndices  = new String[2];
		if(!scanner.hasNextLine()){
			throw new SpreadSheetException(ExceptionType.FILE_FORMAT_EXCEPTION, ErrorMessages.BLANK_FILE);
		}else{
			sheetIndices = scanner.nextLine().split(" ");
			if (sheetIndices.length < 2) {
				throw new SpreadSheetException(ExceptionType.FILE_FORMAT_EXCEPTION, ErrorMessages.INVALID_FILE);
			}
		}
		return sheetIndices;
	}
	
	/**
	 * Method to evaluate each cell
	 * @param workCell
	 *        Cell that need to evaluated
	 * @param currentWorkEvaluationStack
	 *        Current evaluation stack 
	 * @return double
	 *         Result
	 * @throws SpreadSheetException
	 */
	private Double evaluateWorkCell(WorkCell workCell,Set<WorkCell> currentWorkEvaluationStack) throws SpreadSheetException{
		
		currentWorkEvaluationStack = currentWorkEvaluationStack == null?new LinkedHashSet<WorkCell>():currentWorkEvaluationStack;
		if(!workCell.isEvaluated() && !currentWorkEvaluationStack.contains(workCell)){
			currentWorkEvaluationStack.add(workCell);
			Stream<String> fields = Stream.of(workCell.getCellValue().split(" "));
			Stack<Double> cellStack = new Stack<Double>();
			Iterator<String> iterator = fields.iterator();
			while(iterator.hasNext()){
				String field = iterator.next();
				 if(Operators.isValidOperator(field)){
					 Operators operator = Operators.getOperator(field);
					 cellStack = operator.apply(cellStack);
				 }else if(field.matches(valuePatternRegex)){
					 cellStack.push(Double.parseDouble(field));
				 }else if(field.matches(refPatternRegex)){
					 WorkCell anotherCell = workbook.getCell(field);
		             cellStack.push(evaluateWorkCell(anotherCell,currentWorkEvaluationStack));
				 }else{
					 throw new SpreadSheetException(ExceptionType.PROCESSING_EXCEPTION, ErrorMessages.UNSUPPORTED_TOKEN);
				 }
			}
			 workCell.setResult(cellStack.pop());
			 workCell.setCellEvaluationStatus(true);
			 
		}else if(workCell.isEvaluated()){
			throw new SpreadSheetException(ExceptionType.CYCLOMATIC_EXCEPTION, ErrorMessages.CYCLOMATIC_ERROR);
		}
		return workCell.getResult();
	}
	
	/**
	 * Evaluate Work Book by iterating over each cells value
	 */
	private void evaluateWorkBook(){
		WorkCell[][] cells = workbook.getCells();
		for (int iRow = 0; iRow < workbook.getrowIndex(); iRow++) {
			for (int iCol = 0; iCol < workbook.getcolumnIndex(); iCol++) {
				evaluateWorkCell(cells[iRow][iCol],null);
			}
		}
	}

}
