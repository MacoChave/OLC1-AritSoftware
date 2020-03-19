package ast;

import java.util.LinkedList;

/**
 * CallId
 */
public class CallStruct implements Instruction {
    private String id;
    private LinkedList<Vector> vectors;
    private Coordenate coordenate;
    
    public CallStruct(String id, LinkedList<Vector> vectors) {
        this.id = id;
        this.vectors = new LinkedList<>();
		this.vectors = vectors;
    }
    
    private CallStruct(String id, Coordenate coordenate) {
        this.id = id;
        this.vectors = new LinkedList<>();
        this.coordenate = coordenate;
    }

	public String getId() {
		return id;
	}

	public LinkedList<Vector> getVectors() {
		return vectors;
	}

	public Coordenate getCoordenate() {
		return coordenate;
	}

	@Override
	public Object execute(SymbolTable symbols) {
		// TODO Auto-generated method stub
		return null;
	}
}