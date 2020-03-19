package ast;

import java.util.LinkedList;

public class Assign implements Instruction {

    private String id;
    private LinkedList<Vector> vector;
    private Coordenate coordenate;
    private final LinkedList<Operation> values;
    private int row, column;

    public Assign() {
        vector = new LinkedList<>();
        values = new LinkedList<>();
    }

    public Assign(String id, Operation value) {
        this.id = id;
        this.values = new LinkedList<>();
        this.vector = new LinkedList<>();
        this.values.add(value);
    }

    public Assign(String id, LinkedList<Operation> values) {
        this.id = id;
        this.values = new LinkedList<>();
        this.vector = new LinkedList<>();
        this.values.addAll(values);
    }

	public String getId() {
		return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

	public LinkedList<Vector> getVector() {
		return vector;
	}

	public void setVector(LinkedList<Vector> vector) {
		this.vector.addAll(vector);
	}

	public Coordenate getCoordenate() {
		return coordenate;
	}

	public void setCoordenate(Coordenate coordenate) {
		this.coordenate = coordenate;
	}

	public LinkedList<Operation> getValues() {
		return values;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public void setCursorPos(int row, int column) {
        this.row = row;
        this.column = column;
	}

    @Override
    public Object execute(SymbolTable symbols) {
        for (Operation operation : values) {
            if (symbols.getValue(id) == null)
                symbols.add(new Symbol(id));
             symbols.setValue(id, operation.execute(symbols));
        }
        return null;
    }

}
