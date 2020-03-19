package ast;

import java.util.LinkedList;

public class SentFor implements Instruction {

    private final Operation iterador;
    private final LinkedList<Instruction> instructions;
    private int column, row;

    public SentFor(Operation iterador, LinkedList<Instruction> instructions, int row, int column) {
        this.iterador = iterador;
        this.instructions = instructions;
        this.row = row;
        this.column = column;
    }

    @Override
    public Object execute(SymbolTable symbols) {
        for (Instruction instruction : this.instructions) {
            SymbolTable localSymbol = new SymbolTable();
            localSymbol.addAll(symbols);
            instruction.execute(localSymbol);
        }
        return null;
    }
}
