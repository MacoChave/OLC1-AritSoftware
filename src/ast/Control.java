package ast;

/**
 * Continue
 */
public class Control implements Instruction {

	private int row, column;
	Symbol.Control control;

	public Control(int row, int column, String control) {
		this.row = row;
		this.column = column;
		if (control.equals("default")) this.control = Symbol.Control.DEFAULT;
		else if (control.equals("break")) this.control = Symbol.Control.BREAK;
		else if (control.equals("continue")) this.control = Symbol.Control.CONTINUE;
		else this.control = Symbol.Control.RETURN;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	@Override
	public Object execute(SymbolTable symbols) {
		// TODO Auto-generated method stub
		return null;
	}
    
}