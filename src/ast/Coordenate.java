package ast;

/**
 * Coordenate
 */
public class Coordenate implements Instruction {

    private Operation x, y;

	public Coordenate() {
		x = null;
		y = null;
	}

	public Operation getX() {
		return x;
	}

	public void setX(Operation x) {
		this.x = x;
	}

	public Operation getY() {
		return y;
	}

	public void setY(Operation y) {
		this.y = y;
	}

	@Override
	public Object execute(SymbolTable symbols) {
        Object tempX, tempY;
        double coordenates[] = {0, 0};

        tempX = (x != null) ? x.execute(symbols) : 0.0;
        tempY = (y != null) ? y.execute(symbols) : 0.0;

        coordenates[0] = (tempX instanceof Double) ? (int) tempX : -1;
        coordenates[1] = (tempY instanceof Double) ? (int) tempY : -1;
        
        //Todo: Error semántico

        return (coordenates[0] >= 0 && coordenates[1] >= 0) ? coordenates : null;
	}

}