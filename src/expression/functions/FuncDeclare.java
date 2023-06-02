package expression.functions;

import expression.blocks.Block;
import expression.toplevel.Statement;
import org.antlr.v4.runtime.Token;


public class FuncDeclare extends Statement {
    public String id;
    public ArgListDeclare input;
    public ArgListDeclare output;
    public Block block;
    public Token token;

    public FuncDeclare(String id, ArgListDeclare input, ArgListDeclare output, Block block, Token token) {
        this.id = id;
        this.input = input;
        this.output = output;
        this.block = block;
        this.token = token;
    }
}
