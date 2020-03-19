package ast;

import java.util.LinkedList;

/***
 * TABLA DE SÍMBOLOS
 */
public class SymbolTable extends LinkedList<Symbol> {

    public SymbolTable() {
        super();
    }

    /*
    * DEVUELVE EL VALOR DE UNA VARIABLE EN LA TABLA DE SÍMBOLOS
    * */
    Object getValue(String id) {
        for (Symbol symbol : this) {
            if (symbol.getId().equals(id))
                return symbol.getValue();
        }
        return null;
    }

    Boolean setValue(String id, Object value) {
        for (Symbol symbol : this) {
            if (symbol.getId().equals(id)) {
                symbol.setValue(value);
                return true;
            }
        }
        return false;
    }
}
