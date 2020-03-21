package ast;

import java.util.LinkedList;

import tad.Value;

public class Symbol {

    private final String id;
    private Primitivo primitivo;
    private Estructura estructura;
    private LinkedList<Value> values;
    private int x, y, z;

    public Symbol(Estructura estructura, String id, LinkedList<Value> values) {
        this.id = id;
        this.estructura = recalcularEstructura();
        this.values = new LinkedList<>();
        this.values.addAll(values);
    }

	public Symbol(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Object getValue(int index) {
        return values.get(index);
    }

    public void setValue(int index, Value element) {
        this.values.set(index, element);
        // calcularPrimitivo();
    }

    public void setDimension(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public static enum Primitivo {
        NUMERO,
        CADENA,
        FLOTANTE,
        BOOLEANO
    }

    public static enum Estructura {
        VECTOR,
        LISTA,
        MATRIZ,
        ARRAY
    }

    public static enum Control {
        DEFAULT, 
        CONTINUE, 
        BREAK, 
        RETURN
    }

    private Estructura recalcularEstructura() {
        if (estructura == Estructura.VECTOR) {
            Primitivo aux = values.getFirst().getPrimitivo();
            for (Value value : values) {
                if (value.getPrimitivo() != aux) return Estructura.LISTA;
            }
            this.primitivo = aux;
            return estructura;
        }
        return estructura;
    }
    
    // private Primitivo calcularPrimitivo() {
        
    // }

}
