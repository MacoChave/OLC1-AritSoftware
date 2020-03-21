package ast;

import java.util.LinkedList;

public class SentFor implements Instruction {

    private Declaration iterador;
    private Operation operation;
    private LinkedList<Instruction> instructions;
    private int column, row;

    public SentFor(Declaration iterador, Operation operation, LinkedList<Instruction> instructions, int row, int column) {
        this.iterador = iterador;
        this.operation = operation;
        this.instructions = instructions;
        this.row = row;
        this.column = column;
    }

    @Override
    public Object execute(SymbolTable symbols) {
        SymbolTable localSymbol = new SymbolTable();
        localSymbol.addAll(symbols);
        iterador.execute(localSymbol);
        
        for (Instruction instruction : this.instructions) {
            Object result = instruction.execute(localSymbol);
            
            if (result != null && result instanceof Control) {
                if (result == Symbol.Control.BREAK) return null;
                if (result == Symbol.Control.CONTINUE) continue;
            }

            if (result != null)
                return result;
        }
        return null;
    }
}
