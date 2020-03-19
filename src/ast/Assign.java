package ast;

import java.util.LinkedList;

public class Assign implements Instruction {

    private final String id;
    private final LinkedList<Operation> values;

    public Assign(String id, Operation value) {
        this.id = id;
        this.values = new LinkedList<>();
        this.values.add(value);
    }

    public Assign(String id, LinkedList<Operation> values) {
        this.id = id;
        this.values = new LinkedList<>();
        this.values.addAll(values);
    }

    @Override
    public Object execute(SymbolTable symbols) {
        for (Operation operation : values) {
            if (symbols.getValue(id) == null)
                symbols.add(new Symbol(id));
             symbols.setValue(id, operation.execute(symbols));
        }
        return null;
    }
}
