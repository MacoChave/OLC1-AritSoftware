package ast;

import java.util.LinkedList;

public class SubIf implements Instruction {

    private Boolean isTrueCond;
    private final Boolean isElse;
    private final Operation condition;
    private final LinkedList<Instruction> instructions;
    private int column, row;

    /* IF | IF ELSE */
    public SubIf(Operation condition, LinkedList<Instruction> instructions, int row, int column) {
        this.condition = condition;
        this.instructions = instructions;
        this.isElse = false;
        this.row = row;
        this.column = column;
    }

    /* ELSE */
    public SubIf(LinkedList<Instruction> instructions, int row, int column) {
        condition = null;
        this.instructions = instructions;
        this.isElse = true;
        this.row = row;
        this.column = column;
    }

    /* OBTENER EL RESULTADO DE LA CONDICION */
    public Boolean getConditionResult() {
        return isTrueCond || isElse;
    }

    @Override
    public Object execute(SymbolTable symbols) {
        isTrueCond = condition == null ? false : (Boolean) condition.execute(symbols);
        if (isTrueCond || isElse) {
            SymbolTable local_symbols = new SymbolTable();
            local_symbols.addAll(symbols);

            for (Instruction instruction : instructions) {
                instruction.execute(local_symbols);
            }
        }
        return null;
    }
}
