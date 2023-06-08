package expression.interpreter;

import expression.arithmetic.*;
import expression.blocks.Block;
import expression.blocks.IfBlock;
import expression.blocks.WhileBlock;
import expression.functions.FuncCall;
import expression.functions.FuncDeclare;
import expression.library.*;
import expression.logic.*;
import expression.toplevel.Line;
import expression.variables.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ExpressionProcessor {

    private ExpressionProcessor parent = null;
    private final List<Line> list;
    public static final List<String> semanticErrors = new ArrayList<>();

    private final Map<String, Value> values;
    private final Map<String, String> types = new HashMap<>();
    private static Map<String, FuncDeclare> declaredFunctions = new HashMap<>();
    private boolean firstRunError = false;

    public ExpressionProcessor(List<Line> lines){
        list = lines;
        values = new HashMap<>();
    }

    public ExpressionProcessor(List<Line> lines, ExpressionProcessor parent){
        list = lines;
        values = new HashMap<>();
        this.parent = parent;
    }

    /**
     * Evaluates <i>list</i> of Lines for my language
     * @param list if list==null uses this.list
     * @return List of string evaluations
     */
    public List<String> getEvalResults(List<Line> list){
        List<String> evaluations = new ArrayList<>();

        if(list == null)list = this.list;
        for(Line l: list){
            if(l instanceof VarDeclaration v){
                if(values.containsKey(v.variable.id)) {
                    semanticErrors.add("Error: variable `" + v.variable.id + "` " +
                            "already declared (" + v.variable.token.getLine() + ")");
                    firstRunError = true;
                }
                values.put(v.variable.id, null);
                types.put(v.variable.id, v.variable.type);
            } else if(l instanceof FuncDeclare f){
                if(declaredFunctions.containsKey(f.id)) {
                    semanticErrors.add("Error: function `"+f.id+"` already declared ("+f.token.getLine()+")");
                    firstRunError = true;
                }
                List<String> usedNames = new ArrayList<>();
                for(VarDeclaration vd : f.input.declarations){
                    if(vd.variable.value != null){
                        semanticErrors.add("Error: default values not supported ("+f.token.getLine()+")");
                        firstRunError = true;
                        break;
                    }
                    if(usedNames.contains(vd.variable.id)){
                        semanticErrors.add("Error: variable name `"+vd.variable.id+"` already used in function ("+f.token.getLine()+")");
                        firstRunError = true;
                        break;
                    }
                    usedNames.add(vd.variable.id);
                }
                for(VarDeclaration vd : f.output.declarations){
                    if(vd.variable.value != null){
                        semanticErrors.add("Error: default values not supported ("+f.token.getLine()+")");
                        firstRunError = true;
                        break;
                    }
                    if(usedNames.contains(vd.variable.id)){
                        semanticErrors.add("Error: variable name `"+vd.variable.id+"` already used in function ("+f.token.getLine()+")");
                        firstRunError = true;
                        break;
                    }
                    usedNames.add(vd.variable.id);
                }
                declaredFunctions.put(f.id, f);
            }
        }
        if(!firstRunError)
            for(Line l: list){
                if(l instanceof Variable v){
                    System.out.println("OH-OH! variable value: " + v.value);
                    try {
                        values.put(v.id, eval(v.value));
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                } else if (l instanceof VarDeclaration v){
                    Value value = eval(v.variable.value);
                    //FIXME: Below is commented only because
                    // of implementation of two-run variable checking, uncomment when removed
//                if(values.containsKey(v.variable.id))
//                    semanticErrors.add("Error: variable `"+v.variable.id+"` already declared ("+v.variable.token.getLine()+")");
                    values.put(v.variable.id, value);
//                    types.put(v.variable.id, v.variable.type);
                    if(v.variable.value instanceof VarName vn && types.containsKey(vn.id) && !types.get(vn.id).equals(types.get(v.variable.id)))
                        semanticErrors.add("Error: mismatched types! (" + v.variable.token.getLine() + ")");
                        // Checks if declared types match ^
                    else if(!types.get(v.variable.id).equals(value.type) && !value.type.equals("notInit"))
                        semanticErrors.add("Error: mismatched types! (" + v.variable.token.getLine() + ")");
                } else if (l instanceof Assignment a){
                    Value value = eval(a.expr);
                    if(!values.containsKey(a.id)){
                        if(parent != null && parent.values.containsKey(a.id)) {
                            parent.values.put(a.id, value);
                            if(!parent.types.get(a.id).equals(value.type))
                                semanticErrors.add("Error: mismatched types! ("+a.token.getLine()+")");
                        }
                        else semanticErrors.add("Error: variable `"+a.id+"` not declared ("+a.token.getLine()+")");
                    } else {
                        values.put(a.id, value);
                        if (!types.get(a.id).equals(value.type))
                            semanticErrors.add("Error: mismatched types! ("+a.token.getLine()+")");
//                        else if (values.get(a.id) == null)
//                            semanticErrors.add("Error: variable `"+a.id+"` not initialized ("+a.token.getLine()+")");
                        //Why is this here?
                    }
                } else if (l instanceof Increment i) {
                    boolean fromParent = false;
                    if(!types.containsKey(i.id)) {
                        if(parent != null && parent.types.containsKey(i.id)) fromParent = true;
                        else semanticErrors.add("Error: variable `"+i.id+"` not declared ("+i.token.getLine()+")");
                    }

                    String type;
                    Value oldVal;
                    Value newVal = null;
                    if(fromParent){
                        type = parent.types.get(i.id);
                        oldVal = parent.values.get(i.id);
                    } else {
                        type = types.get(i.id);
                        oldVal = values.get(i.id);
                    }
                    if (oldVal.type.equals("notInit"))
                        semanticErrors.add("Error: variable `"+i.id+"` not initialized ("+i.token.getLine()+")");

                    else switch (type){
                        case "int" -> {
                            if(i.operator.equals("++"))newVal = eval(new Addition(oldVal, "+", new Value("1"), i.token));
                            else if(i.operator.equals("--"))newVal = eval(new Addition(oldVal, "-", new Value("1"), i.token));
                        }
                        case "float" -> {
                            if(i.operator.equals("++"))newVal = eval(new Addition(oldVal, "+", new Value("1.0"), i.token));
                            else if(i.operator.equals("--"))newVal = eval(new Addition(oldVal, "-", new Value("1.0"), i.token));
                        }
                        default -> semanticErrors.add("Error: only numeric values can be in/decremented ("+i.token.getLine()+")");
                    }
                    if(fromParent) parent.values.put(i.id, newVal);
                    else values.put(i.id, newVal);
                } else if (l instanceof Print p){
                    if(p.id != null) {
                        Value result = eval(new VarName(p.id, p.token));
                        if(result == null) semanticErrors.add("Error: variable "+p.id+" not declared ("+p.token.getLine()+")");
                        else if(result.type.equals("notInit"))
                            semanticErrors.add("Error: variable `"+p.id+"` not initialized ("+p.token.getLine()+")");
                        else evaluations.add(result.toString());
                    } else {
                        evaluations.add(eval(p.expr).toString());
                    }
                } else if (l instanceof WhileBlock w){
                    Value condition = eval(w.condition);
                    if(condition.type.equals("notInit"))
                        semanticErrors.add("Error: not initialized value ("+w.token.getLine()+")");
                    else if(!condition.type.equals("bool"))
                        semanticErrors.add("Error: can't resolve truth-value for given condition ("+w.token.getLine()+")");
                    else if(condition.value.equals("true")){
//                        try{
//                            List<Line> whileList = new ArrayList<>();
//                            whileList.add(w.block);
//                            whileList.add(w);
//                            evaluations.addAll(getEvalResults(whileList));
//                            whileList.clear();
//                        } catch (Error e){
////                            e.printStackTrace();
//                            semanticErrors.add("Error: stack overflow! (loop repeats too many times)");
//                            break;
//                        }

                        List<Line> whileList = new ArrayList<>();
                        whileList.add(w.block);
                        while(condition.value.equals("true")){
                            evaluations.addAll(getEvalResults(whileList));
                            condition = eval(w.condition);
                        }
                    }
                } else if (l instanceof IfBlock i){
                    Value condition = eval(i.condition);
                    if(condition.type.equals("notInit"))
                        semanticErrors.add("Error: not initialized value ("+i.token.getLine()+")");
                    else if(!condition.type.equals("bool"))
                        semanticErrors.add("Error: can't resolve truth-value for given condition ("+i.token.getLine()+")");
                    else if (condition.value.equals("true")){
                        ExpressionProcessor ep = new ExpressionProcessor(i.elseBlock.ifBlock.lines, this);
                        evaluations.addAll(ep.getEvalResults(null));
                    } else if (condition.value.equals("false") && i.elseBlock.elseBlock != null){
                        ExpressionProcessor ep = new ExpressionProcessor(i.elseBlock.elseBlock.lines, this);
                        evaluations.addAll(ep.getEvalResults(null));
                    } else if (condition.value.equals("false") && i.elseBlock.child != null){
                        List<Line> childList = new ArrayList<>();
                        childList.add(i.elseBlock.child);
                        evaluations.addAll(getEvalResults(childList));
                    }
                } else if (l instanceof Block b){
                    ExpressionProcessor ep = new ExpressionProcessor(b.lines, this);
                    evaluations.addAll(ep.getEvalResults(null));
                } else if (l instanceof FuncCall call){
                    boolean processingError = false;
                    if(!declaredFunctions.containsKey(call.id)) {
                        semanticErrors.add("Error: function `" + call.id + "` not declared (" + call.token.getLine() + ")");
                        processingError = true;
                    }
                    FuncDeclare function = declaredFunctions.get(call.id);
                    if(call.input.expressions.size() != function.input.declarations.size()) {
                        semanticErrors.add("Error: number of provided input arguments doesn't " +
                                "match with function declaration (" + call.token.getLine() + ")");
                        processingError = true;
                    }
                    if(call.output != null && call.output.variables.size() != function.output.declarations.size()) {
                        semanticErrors.add("Error: number of provided output arguments doesn't " +
                                "match with function declaration (" + call.token.getLine() + ")");
                        processingError = true;
                    }
                    if(call.output == null && function.output.declarations.size() != 0) {
                        semanticErrors.add("Error: not used declared function output (" + call.token.getLine() + ")");
                        processingError = true;
                    }
                    if(!processingError) {
                        int index = 0;
                        for (Line ll : call.input.expressions) { //Checking if provided input types match with declaration
                            Value checked = eval(ll);
                            VarDeclaration declared = function.input.declarations.get(index);
                            if (!checked.type.equals(declared.variable.type))
                                semanticErrors.add("Error: provided argument type doesn't " +
                                        "match with function declaration (" + call.token.getLine() + ")");
                            index++;
                        }

                        index = 0;
                        if (call.output != null) for (Variable v : call.output.variables) {
                            String type = "";
                            if (v.type == null) { //Check for declared type here or in parent
                                if (types.containsKey(v.id)) type = types.get(v.id);
                                else if (!types.containsKey(v.id) && parent != null) {
                                    if (parent.types.containsKey(v.id)) type = parent.types.get(v.id);
                                } else
                                    semanticErrors.add("Error: variable `" + v.id + "` not declared (" + call.token.getLine() + ")");
                            } else { //If var is declared inside here, then add new Declaration and process it
                                List<Line> temp = new ArrayList<>();
                                temp.add(new VarDeclaration(v.type, v.id, v.token));
                                type = v.type;
                                getEvalResults(temp);
                            }
                            if (!type.equals(function.output.declarations.get(index).variable.type))
                                semanticErrors.add("Error: output types doesn't match with declaration (" + call.token.getLine() + ")");
                            index++;
                        }

                        ExpressionProcessor ep = new ExpressionProcessor(new ArrayList<>(function.block.lines), null); // To process function body

                        List<Line> bodyVariables = new ArrayList<>();
                        for(int i = 0; i<call.input.expressions.size(); i++){
                            // Add variables from func declaration with assigned values from function call
                            Variable funcVariable = function.input.declarations.get(i).variable;
                            bodyVariables.add(new VarDeclaration(funcVariable.type, funcVariable.id,
                                    eval(call.input.expressions.get(i)), funcVariable.token));
                        }
                        bodyVariables.addAll(function.output.declarations);
                        ep.list.addAll(0, bodyVariables);
                        evaluations.addAll(ep.getEvalResults(null)); // Evaluate function body
                        for (String id : ep.values.keySet()) {
                            // Get all evaluated values and assign them
                            // But only if they are declared in our program
                            //FIXME: differentiate between vars declared in function that have the same id as declared outside
                            if (values.containsKey(id)) values.put(id, ep.values.get(id));
                        }
                        for (int i = 0; i < function.output.declarations.size(); i++) {
                            // To translate declared name of output variable into provided id from funcCall
                            // E.g. if declared output is (int a, string b),
                            // but we provide func() -> (int x1, string x2);
                            // it has to translate a->x1, b->x2 to properly add it to our values
                            values.put(call.output.variables.get(i).id,
                                    ep.values.get(function.output.declarations.get(i).variable.id));
                        }
                    }
                }
            }
        return evaluations;
    }

    private Value eval(Line l){
        Value result = new Value("");

        if(l instanceof Value v){
            result = new Value(v.value);
        } else if (l instanceof VarName v){
            if (values.containsKey(v.id)) result = values.get(v.id);
            else if(parent != null && parent.values.containsKey(v.id)) result = parent.values.get(v.id);
            else semanticErrors.add("Error: variable `"+v.id+"` not declared ("+v.token.getLine()+")");
        } else if (l instanceof Addition a){
            Value left = eval(a.left);
            Value right = eval(a.right);
            if(left.type.equals("notInit") || right.type.equals("notInit"))
                semanticErrors.add("Error: value not initialized! ("+a.token.getLine()+")");
            if(left.type.equals(right.type)){
                if(a.operator.equals("+")){
                    switch (left.type) {
                        case "int" -> {
                            int leftInt = Integer.parseInt(left.value);
                            int rightInt = Integer.parseInt(right.value);
                            result = new Value(Integer.toString(leftInt + rightInt));
                        }
                        case "float" -> {
                            float leftFloat = Float.parseFloat(left.value);
                            float rightFloat = Float.parseFloat(right.value);
                            result = new Value(Float.toString(leftFloat + rightFloat));
                        }
                        case "string" -> result = new Value(left.value.substring(0, left.value.length()-1) + right.value.substring(1));
                        case "bool" -> semanticErrors.add("Error: So far - can't add booleans! ("+a.token.getLine()+")");
                    }
                } else if(a.operator.equals("-")){
                    switch (left.type) {
                        case "int" -> {
                            int leftInt = Integer.parseInt(left.value);
                            int rightInt = Integer.parseInt(right.value);
                            result = new Value(Integer.toString(leftInt - rightInt));
                        }
                        case "float" -> {
                            float leftFloat = Float.parseFloat(left.value);
                            float rightFloat = Float.parseFloat(right.value);
                            result = new Value(Float.toString(leftFloat - rightFloat));
                        }
                        case "string" -> semanticErrors.add("Error: Can't subtract strings! ("+a.token.getLine()+")");
                        case "bool" -> semanticErrors.add("Error: Can't subtract booleans! ("+a.token.getLine()+")");
                    }
                }
            } else semanticErrors.add("Error: Types don't match! ("+a.token.getLine()+")");

        } else if (l instanceof Multiplication m){
            Value left = eval(m.left);
            Value right = eval(m.right);
            if(left.type.equals("notInit") || right.type.equals("notInit"))
                semanticErrors.add("Error: value not initialized! ("+m.token.getLine()+")");
            if(left.type.equals(right.type)){
                if(m.operator.equals("*")){
                    switch (left.type) {
                        case "int" -> {
                            int leftInt = Integer.parseInt(left.value);
                            int rightInt = Integer.parseInt(right.value);
                            result = new Value(Integer.toString(leftInt * rightInt));
                        }
                        case "float" -> {
                            float leftFloat = Float.parseFloat(left.value);
                            float rightFloat = Float.parseFloat(right.value);
                            result = new Value(Float.toString(leftFloat * rightFloat));
                        }
                        case "string" -> semanticErrors.add("Error: Can't multiply strings! ("+m.token.getLine()+")");
                        case "bool" -> semanticErrors.add("Error: Can't multiply booleans! ("+m.token.getLine()+")");
                    }
                } else if(m.operator.equals("/")){
                    switch (left.type) {
                        case "int" -> {
                            int leftInt = Integer.parseInt(left.value);
                            int rightInt = Integer.parseInt(right.value);
                            try {
                                result = new Value(Integer.toString(leftInt / rightInt));
                            } catch (ArithmeticException e) {
                                semanticErrors.add("Error: dividing by zero! ("+m.token.getLine()+")");
                            }
                        }
                        case "float" -> {
                            float leftFloat = Float.parseFloat(left.value);
                            float rightFloat = Float.parseFloat(right.value);
                            try {
                                result = new Value(Float.toString(leftFloat / rightFloat));
                            } catch (ArithmeticException e){
                                semanticErrors.add("Error: dividing by zero! ("+m.token.getLine()+")");
                            }
                        }
                        case "string" -> semanticErrors.add("Error: Can't divide strings! ("+m.token.getLine()+")");
                        case "bool" -> semanticErrors.add("Error: Can't divide booleans! ("+m.token.getLine()+")");
                    }
                }
            } else semanticErrors.add("Error: Types don't match! ("+m.token.getLine()+")");
        } else if (l instanceof Power p){
            Value left = eval(p.left);
            Value right = eval(p.right);
            String leftType = left.type;
            String rightType = right.type;
            if(left.type.equals("notInit") || right.type.equals("notInit"))
                semanticErrors.add("Error: value not initialized! ("+p.token.getLine()+")");
            if(leftType.equals(rightType)){
                switch(leftType){
                    case "int" -> {
                        int leftInt = Integer.parseInt(left.value);
                        int rightInt = Integer.parseInt(right.value);
                        int power = (int) Math.pow(leftInt, rightInt);
                        result = new Value(Integer.toString(power));
                    }
                    case "float" -> {
                        float leftFloat = Float.parseFloat(left.value);
                        float rightFloat = Float.parseFloat(right.value);
                        double power = Math.pow(leftFloat, rightFloat);
                        result = new Value(Double.toString(power));
                    }
                    default -> semanticErrors.add("Error: only numeric variables can be exponentiatied ("+p.token.getLine()+")");
                }
            } else if((left.type.equals("float") || left.type.equals("int"))
                    && (right.type.equals("float") || right.type.equals("int"))) {
                double power = Math.pow(Float.parseFloat(left.value), Float.parseFloat(right.value));
                result = new Value(Double.toString(power));
            } else semanticErrors.add("Error: only numeric variables can be exponentiatied ("+p.token.getLine()+")");
        } else if (l instanceof Modulo m) {
            Value left = eval(m.dividend);
            Value right = eval(m.divisor);

            if(left.type.equals("notInit") || right.type.equals("notInit"))
                semanticErrors.add("Error: value not initialized! ("+m.token.getLine()+")");

            if(left.type.equals(right.type)){
                switch(left.type){
                    case "int" -> {
                        Integer leftInt = Integer.parseInt(left.value);
                        Integer rightInt = Integer.parseInt(right.value);
                        int modulo = leftInt%rightInt;
                        return new Value(Integer.toString(modulo));
                    }
                    case "float" -> {
                        Float leftFloat = Float.parseFloat(left.value);
                        Float rightFloat = Float.parseFloat(right.value);
                        float modulo = leftFloat%rightFloat;
                        return new Value(Float.toString(modulo));
                    }
                    default -> semanticErrors.add("Error: modulo works only for numeric values ("+m.token.getLine()+")");
                }
            } else semanticErrors.add("Error: types don't match ("+m.token.getLine()+")");
        } else if (l instanceof Combination cb) {
            Value left = eval(cb.left);
            Value right = eval(cb.right);
            if(left.type.equals("notInit") || right.type.equals("notInit"))
                semanticErrors.add("Error: value not initialized! ("+cb.token.getLine()+")");
            if(!(left.type.equals("bool") && right.type.equals("bool")))
                semanticErrors.add("Error: combining non-bool types! ("+cb.token.getLine()+")");
            else {
                String operator = cb.operator;
                String b1 = left.value;
                String b2 = right.value;
                switch (operator) {
                    case "and" -> {
                        if (!(b1.equals("null") || b2.equals("null"))) {
                            boolean B1 = Boolean.parseBoolean(b1);
                            boolean B2 = Boolean.parseBoolean(b2);
                            result = new Value(Boolean.toString(B1 && B2));
                        } else if (b1.equals("false") || b2.equals("false")) result = new Value("false");
                        else result = new Value("null");
                    }
                    case "nand" -> {
                        if (!(b1.equals("null") || b2.equals("null"))) {
                            boolean B1 = Boolean.parseBoolean(b1);
                            boolean B2 = Boolean.parseBoolean(b2);
                            result = new Value(Boolean.toString(!(B1 && B2)));
                        } else if (b1.equals("false") || b2.equals("false")) result = new Value("true");
                        else result = new Value("null");
                    }
                    case "or" -> {
                        if (!(b1.equals("null") || b2.equals("null"))) {
                            boolean B1 = Boolean.parseBoolean(b1);
                            boolean B2 = Boolean.parseBoolean(b2);
                            result = new Value(Boolean.toString(B1 || B2));
                        } else if (b1.equals("true") || b2.equals("true")) result = new Value("true");
                        else result = new Value("null");
                    }
                    case "nor" -> {
                        if (!(b1.equals("null") || b2.equals("null"))) {
                            boolean B1 = Boolean.parseBoolean(b1);
                            boolean B2 = Boolean.parseBoolean(b2);
                            result = new Value(Boolean.toString(!(B1 || B2)));
                        } else if (b1.equals("true") || b2.equals("true")) result = new Value("false");
                        else result = new Value("null");
                    }
                    case "xor" -> {
                        if (!(b1.equals("null") || b2.equals("null"))) {
                            boolean B1 = Boolean.parseBoolean(b1);
                            boolean B2 = Boolean.parseBoolean(b2);
                            result = new Value(Boolean.toString(B1 ^ B2));
                        } else result = new Value("null");
                    }
                }
            }
        } else if (l instanceof Negation n) {
            Value val = eval(n.expr);
            if(val.type.equals("notInit"))
                semanticErrors.add("Error: value not initialized! ("+n.token.getLine()+")");
            if(!val.type.equals("bool")) semanticErrors.add("Error: Can't negate non boolean values! ("+n.token.getLine()+")");
            else switch (val.value) {
                case "true" -> result = new Value("false");
                case "false" -> result = new Value("true");
                case "null" -> result = new Value("null");
            }
        } else if (l instanceof Comparison co){
            Value left = eval(co.left);
            Value right = eval(co.right);
            String operator = co.operator;
            List<String> arithmetic = new ArrayList<>();
            arithmetic.add(">"); arithmetic.add("<"); arithmetic.add(">="); arithmetic.add("<=");
            if(left.type.equals("notInit") || right.type.equals("notInit"))
                semanticErrors.add("Error: value not initialized ("+co.token.getLine()+")");
            if(left.type.equals(right.type)){
                switch(left.type){
                    case "bool" -> {
                        if(arithmetic.contains(operator))
                            semanticErrors.add("Error: boolean values can't be compared arithmetically ("+co.token.getLine()+")");
                        else if(operator.equals("==")) {
                            if(!(left.value.equals("null") || right.value.equals("null"))) {
                                boolean b1 = Boolean.parseBoolean(left.value);
                                boolean b2 = Boolean.parseBoolean(right.value);
                                result = new Value(Boolean.toString(b1 == b2));
                            } else result = new Value("null");
                        }
                        else if(operator.equals("!=")) {
                            if(!(left.value.equals("null") || right.value.equals("null"))) {
                                boolean b1 = Boolean.parseBoolean(left.value);
                                boolean b2 = Boolean.parseBoolean(right.value);
                                result = new Value(Boolean.toString(b1 != b2));
                            } else result = new Value("null");
                        }
                    }
                    case "string" -> {
                        if(arithmetic.contains(operator))
                            semanticErrors.add("Error: so far strings can't be compared arithmetically ("+co.token.getLine()+")");
                        else if(operator.equals("==")) result = new Value(Boolean.toString(left.value.equals(right.value)));
                        else if(operator.equals("!=")) result = new Value(Boolean.toString(!left.value.equals(right.value)));
                    }
                    case "int" -> {
                        switch (operator) {
                            case "==" ->
                                    result = new Value(Boolean.toString(Integer.parseInt(left.value)
                                            == Integer.parseInt(right.value)));
                            case "!=" ->
                                    result = new Value(Boolean.toString(Integer.parseInt(left.value)
                                            != Integer.parseInt(right.value)));
                            case ">" ->
                                    result = new Value(Boolean.toString(Integer.parseInt(left.value)
                                            > Integer.parseInt(right.value)));
                            case "<" ->
                                    result = new Value(Boolean.toString(Integer.parseInt(left.value)
                                            < Integer.parseInt(right.value)));
                            case ">=" ->
                                    result = new Value(Boolean.toString(Integer.parseInt(left.value)
                                            >= Integer.parseInt(right.value)));
                            case "<=" ->
                                    result = new Value(Boolean.toString(Integer.parseInt(left.value)
                                            <= Integer.parseInt(right.value)));
                        }
                    }
                    case "float" -> {
                        switch (operator) {
                            case "==" ->
                                    result = new Value(Boolean.toString(Float.parseFloat(left.value)
                                            == Float.parseFloat(right.value)));
                            case "!=" ->
                                    result = new Value(Boolean.toString(Float.parseFloat(left.value)
                                            != Float.parseFloat(right.value)));
                            case ">" ->
                                    result = new Value(Boolean.toString(Float.parseFloat(left.value)
                                            > Float.parseFloat(right.value)));
                            case "<" ->
                                    result = new Value(Boolean.toString(Float.parseFloat(left.value)
                                            < Float.parseFloat(right.value)));
                            case ">=" ->
                                    result = new Value(Boolean.toString(Float.parseFloat(left.value)
                                            >= Float.parseFloat(right.value)));
                            case "<=" ->
                                    result = new Value(Boolean.toString(Float.parseFloat(left.value)
                                            <= Float.parseFloat(right.value)));
                        }
                    }
                }
            } else if((left.type.equals("float") || left.type.equals("int"))
                    && (right.type.equals("float") || right.type.equals("int"))) {
                switch (operator) {
                    case "==" ->
                            result = new Value(Boolean.toString(Float.parseFloat(left.value)
                                    == Float.parseFloat(right.value)));
                    case "!=" ->
                            result = new Value(Boolean.toString(Float.parseFloat(left.value)
                                    != Float.parseFloat(right.value)));
                    case ">" ->
                            result = new Value(Boolean.toString(Float.parseFloat(left.value)
                                    > Float.parseFloat(right.value)));
                    case "<" ->
                            result = new Value(Boolean.toString(Float.parseFloat(left.value)
                                    < Float.parseFloat(right.value)));
                    case ">=" ->
                            result = new Value(Boolean.toString(Float.parseFloat(left.value)
                                    >= Float.parseFloat(right.value)));
                    case "<=" ->
                            result = new Value(Boolean.toString(Float.parseFloat(left.value)
                                    <= Float.parseFloat(right.value)));
                }
            } else semanticErrors.add("Error: comparing non-comparable types! ("+co.token.getLine()+")");
        }
        return result;
    }
}