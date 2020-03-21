package ast;

import java.util.LinkedList;

import tad.Value;

public class Declaration implements Instruction {

    private String id;
    Symbol.Estructura estructura;
    LinkedList<Operation> operations;
    int column, row;

    public Declaration() {
        this.operations = new LinkedList<>();
        this.estructura = Symbol.Estructura.VECTOR;
    }

    /* DECLARACION DE UN VECTOR DE UN VALOR */
    public Declaration(Operation operation) {
        this.operations = new LinkedList<>();
        this.operations.add(operation);
        this.estructura = Symbol.Estructura.VECTOR;
    }
    
	public String getId() {
		return this.id;
	}

    public void setId(String id) {
        this.id = id;
    }

    public void setEstructura(String estructura) {
        switch (estructura.toLowerCase()) {
            case "vector":
                this.estructura = Symbol.Estructura.VECTOR;
                break;
            case "lista":
                this.estructura = Symbol.Estructura.LISTA;
                break;
            case "matriz":
                this.estructura = Symbol.Estructura.MATRIZ;
                break;
            case "array":
                this.estructura = Symbol.Estructura.ARRAY;
                break;
        }
    }

    public Symbol.Estructura getEstructura() { return this.estructura; }

    public void setValue(Operation operation) {
        this.operations.add(operation);
    }

    public void setValues(LinkedList<Operation> operations) {
        this.operations.addAll(operations);
    }

	public LinkedList<Operation> getValues() {
		return operations;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

    @Override
    public Object execute(SymbolTable symbols) {
        LinkedList<Value> values = operationToValue(symbols);
        Symbol symbol = new Symbol(this.estructura, this.id, values);
        symbols.add(symbol);
        return null;
    }

    private LinkedList<Value> operationToValue(SymbolTable symbols) {
        LinkedList<Value> values = new LinkedList<>();
        
        for (Operation operation : operations) {
            values.add(new Value(operation.execute(symbols)));
        }

        return values;
    }

}
