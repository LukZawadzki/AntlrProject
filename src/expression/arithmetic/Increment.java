package expression.arithmetic;

import expression.toplevel.Statement;
import org.antlr.v4.runtime.Token;

public final class Increment extends Statement {
    public String id;
    public String operator;
    public Token token;

    public Increment(String id, String operator, Token token) {
        this.id = id;
        this.operator = operator;
        this.token = token;
    }
}
