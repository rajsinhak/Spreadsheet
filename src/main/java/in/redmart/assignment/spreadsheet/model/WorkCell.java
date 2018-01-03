package in.redmart.assignment.spreadsheet.model;

public class WorkCell {

	private Double result;
	private boolean cellEvaluationStatus;
	private boolean evaluated;
	private String cellValue;
	
	public WorkCell(String value){
		this.cellValue = value;
		cellEvaluationStatus = false;
		evaluated = false;
	}
	
	
	public boolean isEvaluated() {
		return evaluated;
	}
	public void setEvaluated(boolean evaluated) {
		this.evaluated = evaluated;
	}
	public String getCellValue() {
		return cellValue;
	}
	public void setCellValue(String cellValue) {
		this.cellValue = cellValue;
	}

	public boolean isCellEvaluationStatus() {
		return cellEvaluationStatus;
	}

	public void setCellEvaluationStatus(boolean cellStatus) {
		this.cellEvaluationStatus = cellStatus;
	}

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}
	
	@Override
	public String toString() {
		return "WorkCell [cellValue=" + cellValue + "]";
	}

	
	
}
