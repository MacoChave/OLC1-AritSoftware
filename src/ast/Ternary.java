package ast;

import tad.Value;

/**
 * Ternary
 */
public class Ternary implements Instruction {
    
    private String id;
    private Operation condicion;
    private Operation entonces;
    private Operation contrario;
    Symbol.Estructura estructura;

    public Ternary(Operation entonces, Operation contrario) {
        this.entonces = entonces;
        this.contrario = contrario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Operation getCondicion() {
        return condicion;
    }

    public void setCondicion(Operation condicion) {
        this.condicion = condicion;
    }

    public Operation getEntonces() {
        return entonces;
    }

    public void setEntonces(Operation entonces) {
        this.entonces = entonces;
    }

    public Operation getContrario() {
        return contrario;
    }

    public void setContrario(Operation contrario) {
        this.contrario = contrario;
    }

    @Override
    public Object execute(SymbolTable symbols) {
        Object condicionResult = condicion.execute(symbols);
        
        if (!(condicionResult instanceof Boolean)) return null;
        
        Object entoncesResult = entonces.execute(symbols);
        Object contrarioResult = contrario.execute(symbols);
        
        Symbol symbol = new Symbol(id);
        if ((Boolean) condicionResult) 
            symbol.setValue(0, new Value(entoncesResult));
        else
            symbol.setValue(0, new Value(contrarioResult));
        
        return null;
    }

}