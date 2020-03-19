package ast;

public class Operation implements Instruction {

    public enum Type {
        SUMA,
        RESTA,
        MULTIPLICACION,
        DIVISION,
        POTENCIA,
        MODULO,
        NEGATIVO,
        NUMERO,
        ID,
        CADENA,
        DECIMAL,
        TRUE,
        FALSE,
        MENOR,
        MAYOR,
        MENOR_IGUAL,
        MAYOR_IGUAL,
        IGUAL,
        DIFERENTE,
        NOT,
        AND,
        OR,
        PARENTESIS,
        NULL
    }

    private final Type type;
    private Operation left, right;
    private Object value;
    private int column, row;

    /*
    * CONSTRUCTOR PARA OPERACIONES BINARIAS
    * SUMA, RESTA, MULTIPLICACION, DIVICION, POTENCIA, MODULO
    * MENOR, MAYOR, MENOR QUE, MAYOR QUE, IGUAL, DIFERENTE
    * AND, OR
    * */
    public Operation(Type type, Operation left, Operation right, int row, int column) {
        this.type = type;
        this.left = left;
        this.right = right;
        this.row = row;
        this.column = column;
    }

    /*
    * CONSTRUCTOR PARA OPERACIONES UNARIAS
    * NEGATIVO, NOT
    * */
    public Operation(Type type, Operation left, int row, int column) {
        this.type = type;
        this.left = left;
        this.row = row;
        this.column = column;
    }

    /*
    * CONSTRUCTOR PARA OPERACIONES UNARIAS
    * ID, CADENA, NUMERO, DECIMAL, TRUE, FALSE, NULL
    * */
    public Operation(Type type, String value, int row, int column) {
        this.type = type;
        this.value = value;
        this.row = row;
        this.column = column;
    }

    /*
     * CONSTRUCTOR PARA PARENTESIS
     * */
    public Operation(Operation left, int row, int column) {
        this.type = Type.PARENTESIS;
        this.left = left;
        this.row = row;
        this.column = column;
    }

    @Override
    public Object execute(SymbolTable symbols) {

        Object leftValue = (left == null) ? null : left.execute(symbols);
        Object rightValue = (right == null) ? null : right.execute(symbols);

        if (type == Type.SUMA) {
            if (leftValue instanceof Double && rightValue instanceof Double)
                return (Double)leftValue + (Double)rightValue;
            else if (leftValue instanceof String || rightValue instanceof String)
                return leftValue.toString() + rightValue.toString();
            else
                return null;
        } else if (type == Type.RESTA) {
            if (leftValue instanceof Double && rightValue instanceof Double)
                return (Double)leftValue - (Double)rightValue;
            else
                return null;
        } else if (type == Type.MULTIPLICACION) {
            if (leftValue instanceof Double && rightValue instanceof Double)
                return (Double)leftValue * (Double)rightValue;
            else
                return null;
        } else if (type == Type.DIVISION) {
            if (leftValue instanceof Double && rightValue instanceof Double)
                return (Double)leftValue / (Double)rightValue;
            else
                return null;
        } else if (type == Type.POTENCIA) {
            if (leftValue instanceof Double && rightValue instanceof Double)
                return Math.pow((Double)leftValue, (Double)rightValue);
            else
                return null;
        } else if (type == Type.MODULO) {
            if (leftValue instanceof Double && rightValue instanceof Double)
                return (Double)leftValue % (Double)rightValue;
            else
                return null;
        }else if (type == Type.NEGATIVO) {
            if (leftValue instanceof Double && rightValue instanceof Double)
                return (Double)leftValue * -1;
            else
                return null;
        } else if (type == Type.CADENA) {
            return value.toString();
        } else if (type == Type.DECIMAL) {
            return new Double(value.toString());
        } else if (type == Type.NUMERO) {
            return new Double(value.toString());
        } else if (type == Type.TRUE) {
            return true;
        } else if (type == Type.FALSE) {
            return false;
        } else if (type == Type.ID) {
            return symbols.getValue(value.toString());
        } else if (type == Type.PARENTESIS) {
            return leftValue;
        } else if (type == Type.NULL) {
            return null;
        } else {
            int compare = leftValue.toString().compareTo(rightValue.toString());

            if (type == Type.MENOR) {
                if (leftValue instanceof Double && rightValue instanceof Double)
                    return (Double) leftValue < (Double)rightValue;
                if (leftValue instanceof String && rightValue instanceof String)
                    return compare < 0;
                else
                    return null;
            } else if (type == Type.MAYOR) {
                if (leftValue instanceof Double && rightValue instanceof Double)
                    return (Double) leftValue < (Double)rightValue;
                if (leftValue instanceof String && rightValue instanceof String)
                    return compare > 0;
                else
                    return null;
            } else if (type == Type.MENOR_IGUAL) {
                if (leftValue instanceof Double && rightValue instanceof Double)
                    return (Double) leftValue < (Double)rightValue;
                if (leftValue instanceof String && rightValue instanceof String)
                    return compare <= 0;
                else
                    return null;
            } else if (type == Type.MAYOR_IGUAL) {
                if (leftValue instanceof Double && rightValue instanceof Double)
                    return (Double) leftValue < (Double)rightValue;
                if (leftValue instanceof String && rightValue instanceof String)
                    return compare >= 0;
                else
                    return null;
            } else if (type == Type.IGUAL) {
                if (leftValue instanceof Double && rightValue instanceof Double)
                    return (Double) leftValue == (Double)rightValue;
                if (leftValue instanceof String && rightValue instanceof String)
                    return leftValue.toString().equals(rightValue);
                if (leftValue instanceof Boolean && rightValue instanceof Boolean)
                    return (Boolean)leftValue == (Boolean)rightValue;
                else
                    return null;
            } else if (type == Type.DIFERENTE) {
                if (leftValue instanceof Double && rightValue instanceof Double)
                    return (Double) leftValue != (Double)rightValue;
                if (leftValue instanceof String && rightValue instanceof String)
                    return !(leftValue.toString().equals(rightValue));
                if (leftValue instanceof Boolean && rightValue instanceof Boolean)
                    return (Boolean)leftValue != (Boolean)rightValue;
                else
                    return null;
            } else if (type == Type.NOT) {
                if (leftValue instanceof Boolean && rightValue instanceof Boolean)
                    return !(Boolean)leftValue;
                else
                    return null;
            } else if (type == Type.AND) {
                if (leftValue instanceof Boolean && rightValue instanceof Boolean)
                    return (Boolean)leftValue && (Boolean)rightValue;
                else
                    return null;
            } else if (type == Type.OR) {
                if (leftValue instanceof Boolean && rightValue instanceof Boolean)
                    return (Boolean)leftValue || (Boolean)rightValue;
                else
                    return null;
            }
        }

        return null;
    }
}
