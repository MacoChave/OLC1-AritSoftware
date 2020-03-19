package ast;

import java.util.LinkedList;

public class SentSwitch implements Instruction {

    private Operation evaluate;
    private LinkedList<Instruction> list_case;

    public SentSwitch(Operation evaluate, LinkedList<Instruction> list_case, Instruction case_default) {
        this.evaluate = evaluate;
        this.list_case = new LinkedList<>();
        this.list_case.addAll(list_case);
        this.list_case.add(case_default);
    }

    @Override
    public Object execute(SymbolTable symbols) {
        SymbolTable localSymbol = new SymbolTable();
        localSymbol.addAll(symbols);
        for (Instruction instruction : this.list_case) {
            ((SubSwitch) instruction).setEvaluate(evaluate);
            instruction.execute(localSymbol);
            if (((SubSwitch) instruction).getIsTrueCond()) return null;
        }
        return null;
    }
}
