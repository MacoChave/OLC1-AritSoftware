package ast;

import java.util.LinkedList;

public class SentWhile implements Instruction {

    public final Operation condition;
    public final LinkedList<Instruction> instructions;
    private int column, row;

    public SentWhile(Operation condition, LinkedList<Instruction> instructions, int row, int column) {
        this.condition = condition;
        this.instructions = instructions;
        this.row = row;
        this.column = column;
    }

    @Override
    public Object execute(SymbolTable symbols) {
        while ((Boolean) condition.execute(symbols)) {
            SymbolTable localSymbols = new SymbolTable();
            localSymbols.addAll(symbols);
            for (Instruction instruction : instructions) {
                Object result = instruction.execute(localSymbols);
                
                if (result != null && result instanceof Control) {
                    if (result == Symbol.Control.BREAK) return null;
                    if (result == Symbol.Control.CONTINUE) continue;
                }
                
                if (result != null)
                    return result;
            }
        }
        return null;
    }
}
