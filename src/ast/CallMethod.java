package ast;

import java.util.LinkedList;

/**
 * CallMethod
 */
public class CallMethod implements Instruction {

    private String id;
    private LinkedList<Operation> params;
    
    public CallMethod(String id, LinkedList<Operation> params) {
		this.id = id;
		this.params = params;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LinkedList<Operation> getParams() {
		return params;
	}

	public void setParams(LinkedList<Operation> params) {
		this.params = params;
	}

	@Override
	public Object execute(SymbolTable symbols) {
		// TODO Auto-generated method stub
		return null;
    }
    
}