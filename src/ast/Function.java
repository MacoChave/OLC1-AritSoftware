package ast;

import java.util.LinkedList;

/**
 * Function
 */
public class Function implements Instruction {

    private String id;
    private final LinkedList<Declaration> parametros;
    private final LinkedList<Instruction> sentencias;

	public Function(final LinkedList<Declaration> parametros, final LinkedList<Instruction> sentencias) {
		this.parametros = parametros;
		this.sentencias = sentencias;
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public LinkedList<Declaration> getParametros() {
		return parametros;
	}

	public LinkedList<Instruction> getInstrucciones() {
		return sentencias;
	}

	@Override
	public Object execute(final SymbolTable symbols) {
        SymbolTable localSymbols = new SymbolTable();
        localSymbols.addAll(symbols);
        
        for (Declaration declaration : parametros) {
            declaration.execute(localSymbols);
        }
        
        for (Instruction instruction : sentencias) {
            instruction.execute(localSymbols);
        }

		return null;
	}
}