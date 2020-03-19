package ast;

import java.util.LinkedList;

public class SentFor implements Instruction {

    private Operation iterador;
    private Instruction instruction;
    private LinkedList<Instruction> instructions;
    private int column, row;

    public SentFor(Operation iterador, Instruction instruction, LinkedList<Instruction> instructions, int row, int column) {
        this.iterador = iterador;
        this.instruction = instruction;
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
