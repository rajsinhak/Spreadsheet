package in.redmart.assignment.spreadsheet.process;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import in.redmart.assignment.spreadsheet.exception.ErrorMessages;
import in.redmart.assignment.spreadsheet.exception.SpreadSheetException;
import in.redmart.assignment.spreadsheet.exception.SpreadSheetException.ExceptionType;

/**
 * Class to hold all the supported operators.
 * @author Raj
 *
 */
public enum Operators {

	ADD("+"), SUB("-"), MUL("*"), DIV("/"), INC("++"), DEC("--");
	
	private final String operator;
	
	// Reverse-lookup map for getting a Operator from an using value
	private static final Map<String, Operators> lookup = new HashMap<String, Operators>();

	static {
		for (Operators op : Operators.values())
			lookup.put(op.getOperator(), op);
	}
	
	private Operators(String op) {
		this.operator = op;
	}
	
	public static Operators getOperator(String op) {
		return lookup.get(op);
	}
	
	public static boolean isValidOperator(String op) {
		return getOperator(op) != null;
	}

	public String getOperator() {
		return operator;
	}
	
	public Stack<Double> apply(Stack<Double> cellStack) throws SpreadSheetException {
		double op1, op2;
		switch (this) {
			case ADD:
				op1 = cellStack.pop();
				op2 = cellStack.pop();
				cellStack.push(op2 + op1);
				break;
			case SUB:
				op1 = cellStack.pop();
				op2 = cellStack.pop();
				cellStack.push(op2 - op1);
				break;
			case MUL:
				op1 = cellStack.pop();
				op2 = cellStack.pop();
				cellStack.push(op2 * op1);
				break;
			case DIV:
				op1 = cellStack.pop();
				op2 = cellStack.pop();
				if (op1 == 0) {
					throw new SpreadSheetException(ExceptionType.PROCESSING_EXCEPTION, ErrorMessages.DIVIDEND_ZERO);
				}
				cellStack.push(op2 / op1);
				break;
			case INC:
				op1 = cellStack.pop();
				cellStack.push(++op1);
				break;
			case DEC:
				op1 = cellStack.pop();
				cellStack.push(--op1);
				break;
		}
		return cellStack;
	}
}
