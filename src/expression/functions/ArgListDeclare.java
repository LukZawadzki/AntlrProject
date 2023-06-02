package expression.functions;

import expression.toplevel.Line;
import expression.variables.VarDeclaration;

import java.util.ArrayList;
import java.util.List;

public class ArgListDeclare extends Line {
    public List<VarDeclaration> declarations;

    public ArgListDeclare(List<VarDeclaration> declarations) {
        this.declarations = declarations;
    }

    public ArgListDeclare() {
        this.declarations = new ArrayList<>();
    }
}
