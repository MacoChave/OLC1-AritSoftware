package ast;

import java.util.LinkedList;

public class Declaration implements Instruction {

    private String id;
    Symbol.Primitivo primitivo;
    Symbol.Estructura estructura;
    LinkedList<Operation> values;
    int column, row;
    Operation x, y, z;

    public Declaration() {
        this.values = new LinkedList<>();
        this.estructura = Symbol.Estructura.VECTOR;
        this.primitivo = Symbol.Primitivo.CADENA;
    }

    /* DECLARACION DE UN VECTOR DE UN VALOR */
    public Declaration(Operation value) {
        this.values = new LinkedList<>();
        this.values.add(value);
        this.estructura = Symbol.Estructura.VECTOR;
        this.primitivo = Symbol.Primitivo.CADENA;
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

    public void setPrimitivo(String primitivo) {
        switch (primitivo.toLowerCase()) {
            case "number":
                this.primitivo = Symbol.Primitivo.NUMERO;
                break;
            case "string":
                this.primitivo = Symbol.Primitivo.CADENA;
                break;
            case "double":
                this.primitivo = Symbol.Primitivo.FLOTANTE;
                break;
            case "boolean":
                this.primitivo = Symbol.Primitivo.BOOLEANO;
                break;
        }
    }

    public Symbol.Primitivo getPrimitivo() { return this.primitivo; }

    public void setValue(Operation value) {
        this.values.add(value);
    }

    public void setValues(LinkedList<Operation> values) {
        this.values.addAll(values);
    }

	public LinkedList<Operation> getValues() {
		return values;
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

	public Operation getX() {
		return this.x;
	}

	public void setX(Operation x) {
		this.x = x;
	}

	public Operation getY() {
		return this.y;
	}

	public void setY(Operation y) {
		this.y = y;
	}

	public Operation getZ() {
		return this.z;
	}

	public void setZ(Operation z) {
		this.z = z;
	}

    @Override
    public Object execute(SymbolTable symbols) {
        symbols.add(new Symbol(this.primitivo, this.estructura, this.id));
        return null;
    }

}
