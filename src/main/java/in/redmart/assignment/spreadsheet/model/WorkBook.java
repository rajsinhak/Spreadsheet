package in.redmart.assignment.spreadsheet.model;

import java.util.Scanner;

import in.redmart.assignment.spreadsheet.exception.ErrorMessages;
import in.redmart.assignment.spreadsheet.exception.SpreadSheetException;
import in.redmart.assignment.spreadsheet.exception.SpreadSheetException.ExceptionType;
import in.redmart.assignment.spreadsheet.util.StringUtil;

public class WorkBook {
	
	private WorkCell[][] cells;
	private Integer columnIndex;
	private Integer rowIndex;
	
	public WorkCell[][] getCells() {
		return cells;
	}
	
	public int getcolumnIndex() {
		return columnIndex;
	}
	
	public int getrowIndex() {
		return rowIndex;
	}
	
	/**
	 * Initialize the initial WorkCell size
	 * @param indices
	 * @throws SpreadSheetException
	 */
	public void initialize(String[] indices) throws SpreadSheetException{
		
		if(!StringUtil.isNumeric(indices[0]) || !StringUtil.isNumeric(indices[1]))
			throw new SpreadSheetException(ExceptionType.PROCESSING_EXCEPTION, ErrorMessages.INDEX_NOT_NUMERIC);
		
		this.columnIndex = Integer.valueOf(indices[0]);
		this.rowIndex =  Integer.valueOf(indices[1]);
		this.cells = new WorkCell[this.rowIndex][this.columnIndex];
		
	}
	
	/**
	 * Populate the Value into the cells
	 * @param scanner
	 * @throws SpreadSheetException
	 */
	public void populateWorkBook(Scanner scanner) throws SpreadSheetException{
		int rowIndex = 0,colIndex = 0,totalCount=0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (StringUtil.isEmpty(line))
				break;
			this.cells[rowIndex][colIndex] = new WorkCell(line);
			colIndex++;
			totalCount++;
			if(colIndex==this.columnIndex)
			{
				colIndex = 0;
				rowIndex++;
			}
		}
		if (totalCount != this.rowIndex*this.columnIndex)
			throw new SpreadSheetException(ExceptionType.PROCESSING_EXCEPTION,ErrorMessages.INVALID_CELL_COUNT);
		
	}
	
	/**
	 * Method Prints the evaluated work sheet
	 */
	public void printEvaluatedWorkSheet(){
		System.out.println(this.columnIndex+" "+this.rowIndex);
		for (int i = 0; i < this.rowIndex; i++) {
			for (int j = 0; j < this.columnIndex; j++) {
				if(i==this.rowIndex-1 && j==this.columnIndex-1)
					System.out.printf("%.5f", cells[i][j].getResult());
				else
				System.out.printf("%.5f%n", cells[i][j].getResult());
			}
		}
	}
	
	/**
	 * Returns the referential cell  
	 * @param s
	 * @return WorkCell
	 */
	public WorkCell getCell(String referentialCell) {		
		
		int iRow = (int)referentialCell.charAt(0) % 65;
		int iCol = Integer.parseInt(referentialCell.substring(1,referentialCell.length()))-1;
		return this.cells[iRow][iCol];		
			
	}
	
	
	
}
