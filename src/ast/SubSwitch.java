package ast;

import java.util.LinkedList;

public class SubSwitch implements Instruction {

    private Boolean isTrueCond;
    private final Operation condition;
    private Operation evaluate;
    private final LinkedList<Instruction> instructions;
    private int column, row;

    /* CONSTRUCTOR PARA CASE */
    public SubSwitch(Operation condition, LinkedList<Instruction> instructions, int row, int column) {
        this.condition = condition;
        this.instructions = instructions;
        this.row = row;
        this.column = column;
    }

    /* CONSTRUCTOR PARA DEFAULT */
    public SubSwitch(LinkedList<Instruction> instructions, int row, int column) {
        condition = null;
        this.instructions = instructions;
        this.row = row;
        this.column = column;
    }

    public void setEvaluate(Operation evaluate) {
        this.evaluate = evaluate;
    }

    public Boolean isMatchCondition (SymbolTable symbols) {
        Object evalVal = this.evaluate.execute(symbols);
        Object condVal = (this.condition == null) ? null : this.condition.execute(symbols);

        if (condVal != null) {
            if (evalVal instanceof Double && condVal instanceof Double)
                return  (Double) evalVal == (Double)condVal;
            if (evalVal instanceof String && condVal instanceof String)
                return evalVal.toString().equals(condVal);
            if (evalVal instanceof Boolean && condVal instanceof Boolean)
                return (Boolean)evalVal == (Boolean)condVal;
            else
                return false;
        }

        return true;
    }

    public Boolean getIsTrueCond() {
        return isTrueCond;
    }

    @Override
    public Object execute(SymbolTable symbols) {
        isTrueCond = isMatchCondition(symbols);

        if (isTrueCond) {
            SymbolTable localSymbols = new SymbolTable();
            localSymbols.addAll(symbols);

            for (Instruction instruction : instructions) {
                instruction.execute(localSymbols);
            }
        }
        return null;
    }
}
