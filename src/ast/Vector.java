package ast;

/**
 * Vector
 */
public class Vector implements Instruction {

    private Operation index;
    private Type type;

	/**
	 * VALOR: V DENTRO DE LISTA
	 * CONTENIDO: C PRIMITIVO
	 */
    enum Type {
        VALOR, CONTENIDO
    }

	public Vector(Operation index, char type) {
        this.index = index;
        this.type = (type == 'v') ? Type.VALOR : Type.CONTENIDO;
	}

	public Operation getIndex() {
		return index;
	}

	public void setIndex(Operation index) {
		this.index = index;
	}

	public Type getType() {
		return type;
	}

	@Override
	public Object execute(SymbolTable symbols) {
        Object temp = index.execute(symbols);
        double result = (temp instanceof Double) ? (int) temp : -1;

        //Todo: Error semántico
        
        return (result > 0) ? result : null;
	};

    
}