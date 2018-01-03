package in.redmart.assignment.spreadsheet.exception;

/**
 * Error Message Enum 
 * @author Raj
 *
 */
public enum ErrorMessages {

	INVALID_FILE("File is not a valid SpreadSheet"),
	BLANK_FILE("File is Blank/Empty"),
	INDEX_NOT_NUMERIC("Indexs are not numeric"),
	INVALID_CELL_COUNT("Total Cell Count Doesn't matches the given Row Count * Column Count"),
	CYCLOMATIC_ERROR("Cycle Occurred while evaluating Cell Value"),
	DIVIDEND_ZERO("Dividend is zero"),
	UNSUPPORTED_TOKEN("Token Not Supported");
	
	private String errorMessage;
	
	private ErrorMessages(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
}
