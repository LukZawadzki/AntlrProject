package expression.variables;

import expression.toplevel.Expr;
import org.antlr.v4.runtime.Token;

public final class Casting extends Expr {

    public Token token;
    public String type;
    public Expr expr;

    public Casting(Token token, String type, Expr expr) {
        this.token = token;
        this.type = type;
        this.expr = expr;
    }

    @Override
    public String evaluate() {
        return null;
    }
}
