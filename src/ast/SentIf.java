package ast;

import java.util.LinkedList;

public class SentIf implements Instruction {

    private final LinkedList<Instruction> list_if;

    /* IF */
    public SentIf(SubIf subIf) {
        this.list_if = new LinkedList<>();
        this.list_if.add(subIf);
    }

    /* IF (ELSE IF)+ */
    public SentIf(SubIf subIf, LinkedList<Instruction> list_if) {
        this.list_if = new LinkedList<>();
        this.list_if.add(subIf);
        this.list_if.addAll(list_if);
    }

    /* IF (ELSE IF)+ ELSE */
    public SentIf(SubIf subIf, LinkedList<Instruction> list_if, SubIf subElse) {
        this.list_if = new LinkedList<>();
        this.list_if.add(subIf);
        this.list_if.addAll(list_if);
        list_if.add(subElse);
    }

    /* IF ELSE */
    public SentIf(SubIf subIf, SubIf subElse) {
        this.list_if = new LinkedList<>();
        this.list_if.add(subIf);
        this.list_if.add(subElse);
    }

    @Override
    public Object execute(SymbolTable symbols) {
        for (Instruction instruction : this.list_if) {
            instruction.execute(symbols);
            if (((SubIf)instruction).getConditionResult())
                return null;
        }
        return null;
    }
}
