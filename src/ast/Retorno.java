package ast;

import java.util.LinkedList;

/**
 * Return
 */
public class Retorno implements Instruction {

    private int row, column;
    private Operation args;
	public Retorno(int row, int column, Operation args) {
		this.row = row;
		this.column = column;
		this.args = args;
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
	public Operation getArgs() {
		return args;
	}
	public void setArgs(Operation args) {
		this.args = args;
	}
	@Override
	public Object execute(SymbolTable symbols) {
        SymbolTable localSymbol = new SymbolTable();
        localSymbol.addAll(symbols);

		Object object = this.args.execute(localSymbol);
		if (object != null)
			return object;
		
			return null;
	}

}