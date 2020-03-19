package ast;

public interface Instruction {

    public Object execute(SymbolTable symbols);

}
