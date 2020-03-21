package tad;

import ast.Symbol.Primitivo;

/**
 * Value
 */
public class Value {

    private Object value;
    private Primitivo primitivo;

    public Value(Object value) {
		this.value = value;
		this.primitivo = getTypePrimitivo();
	}

    public Object getValue() {
		return value;
	}

    public void setValue(Object value) {
		this.value = value;
	}

    public Primitivo getPrimitivo() {
		return primitivo;
	}

    public void setPrimitivo(Primitivo primitivo) {
		this.primitivo = primitivo;
	}

	private Primitivo getTypePrimitivo() {
		if (this.value instanceof Double) return Primitivo.FLOTANTE;
		if (this.value instanceof Integer) return Primitivo.NUMERO;
		if (this.value instanceof String) return Primitivo.CADENA;
		else return Primitivo.BOOLEANO;
	}

}