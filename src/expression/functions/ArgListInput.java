package expression.functions;

import expression.toplevel.Expr;
import expression.toplevel.Line;

import java.util.ArrayList;
import java.util.List;

public class ArgListInput extends Line {
    public List<Expr> expressions;

    public ArgListInput(List<Expr> expressions) {
        this.expressions = expressions;
    }

    public ArgListInput() {
        this.expressions = new ArrayList<>();
    }
}
