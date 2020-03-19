package ast;

/**
 * Ternary
 */
public class Ternary implements Instruction {
    
    private String id;
    private Operation condicion;
    private Operation entonces;
    private Operation contrario;
    Symbol.Primitivo primitivo;
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
        symbols.add(new Symbol(this.primitivo, this.estructura, this.id));
        return null;
    }

}