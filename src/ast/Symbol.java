package ast;

public class Symbol {

    private final String id;
    private Primitivo primitivo;
    private Estructura estructura;
    private Object value;

    public Symbol(Primitivo primitivo, Estructura estructura, String id) {
        this.primitivo = primitivo;
        this.id = id;
        this.estructura = estructura;
    }

    public String getId() {
        return id;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
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
}
