package ast;

import java.util.LinkedList;

public class SentDo implements Instruction {

    private final Operation condition;
    private final LinkedList<Instruction> instructions;
    private int column, row;

    public SentDo(Operation condition, LinkedList<Instruction> instructions, int row, int column) {
        this.condition = condition;
        this.instructions = instructions;
        this.row = row;
        this.column = column;
    }

    @Override
    public Object execute(SymbolTable symbols) {
        do {
            SymbolTable localSymbol = new SymbolTable();
            localSymbol.addAll(symbols);
            for (Instruction instruction : instructions) {
                Object result = instruction.execute(localSymbol);
                
                if (result != null && result instanceof Control) {
                    if (result == Symbol.Control.BREAK) return null;
                    if (result == Symbol.Control.CONTINUE) continue;
                }

                if (result != null)
                    return result;
            }
        } while ((Boolean) condition.execute(symbols));
        return null;
    }
}
