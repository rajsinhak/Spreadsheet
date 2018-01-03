package in.redmart.assignment.spreadsheet.exception;


public class SpreadSheetException extends RuntimeException {

	/**
	 * Default Serial Version ID
	 */
	private static final long serialVersionUID = 1L;
	
	private ExceptionType exceptionType;
	private ErrorMessages errorMessage;

	public SpreadSheetException(ExceptionType exceptionType,ErrorMessages errorMessage) {
		this.exceptionType = exceptionType;
		this.errorMessage = errorMessage;
	}

	public enum ExceptionType {
		FILE_FORMAT_EXCEPTION, PROCESSING_EXCEPTION, CYCLOMATIC_EXCEPTION;
	}

	/**
	 * To Get ExceptionType
	 * @return ExceptionType
	 */
	public ExceptionType getExceptionType() {
		return exceptionType;
	}

	/**
	 * To Get ErrorMessages
	 * @return ErrorMessages
	 */
	public ErrorMessages getErrorMessage() {
		return errorMessage;
	}
}
