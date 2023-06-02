package expression.functions;

import expression.toplevel.Line;
import org.antlr.v4.runtime.Token;

public class FuncCall extends Line {
    public String id;
    public ArgListInput input;
    public ArgListOutput output;
    public Token token;

    public FuncCall(String id, ArgListInput input, ArgListOutput output, Token token) {
        this.id = id;
        this.input = input;
        this.output = output;
        this.token = token;
    }
}
