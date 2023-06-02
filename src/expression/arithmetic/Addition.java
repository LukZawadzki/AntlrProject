package expression.arithmetic;

import expression.toplevel.Expr;
import org.antlr.v4.runtime.Token;

public final class Addition extends Expr {
    public Expr left;
    public String operator;
    public Expr right;
    public Token token;

    public Addition(Expr left, String operator, Expr right, Token token) {
        this.left = left;
        this.right = right;
        this.operator = operator;
        this.token = token;
    }

    @Override
    public String toString() {
        return left.toString() + operator +right.toString();
    }

    @Override
    public String evaluate() { //Not used anywhere, probably useless
        System.out.println("We've got a problem");
        int result = Integer.parseInt(left.evaluate());
        result += Integer.parseInt(right.evaluate());
        return Integer.toString(result);
    }
}
