package expression.functions;

import expression.toplevel.Line;
import expression.variables.Variable;

import java.util.ArrayList;
import java.util.List;

public class ArgListOutput extends Line {
    public List<Variable> variables;

    public ArgListOutput(List<Variable> variables) {
        this.variables = variables;
    }

    public ArgListOutput() {
        this.variables = new ArrayList<>();
    }
}
