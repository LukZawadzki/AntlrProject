# Welcome to the project of my own programming language, a vastly inferior version of your favourites, like C and Java! 

## Main objectives:

- Based on C-like syntax, but written by me from scratch
- Few improvements which I would like to see in C-like languages, while maintaining clarity of syntax
-  Keep in mind: it is/was an assignment for uni

## Basics:
### 4 basic variable types:
- `int`, `float`, `bool`, `string` - behaving mostly as their known counterparts
-- with the exception of `bool`, which implements three-valued logics - adding **`null`** as logic type

### Array type:
- `multiple` (TBA - maybe?) - python-like collection variables, regardless of type

- Note: variable names **must** start with lowercase letter
##
### Arithmetic operations:
- `+`, `-`, `*`, `/`, `^` (exponentiation), `%` (modulo) with inplace variants -> `+=`, `-=`, etc. 
and also increment/decrement operators `++`, `--`

### Logic operations:
- `and`, `or`, `not` (can also use `!` as equivalent), `nor`, `nand`, `xor`

	- How do they work with `null`?<br><br>
	
	|A and B|      true      |      false      |      null      |
	|---------|-------------|--------------|-------------|
	|   true   |      true      |      false      |      null      |
	|   false  |      false     |      false      |  **false**  |
	|   null    |      null      |   **false**   |      null      |
	<br>
	
	| A or B |      true      |      false      |      null      |
	|---------|-------------|-------------|-------------|
	|   true   |      true      |      true      |   **true**  |
	|   false  |      true      |      false     |  **null**   |
	|   null    |   **true**  |   **null**   |      null      |
	<br>
- Other operators can be made using these and `not`, which is too obvoius to write down :)
- Comparisons between values - `>`, `<`, `==`, `!=`, `>=`, `<=`

## Loops and conditional statements
- `while` loop - just like in C, nothing much to add
- `if` - with `true` and `false` behaving as you would expect, but with `null` values, since it represents the state of **"unknown"** value, neither true or false - it skips the conditional
	- ```
      if null {
	      print("abc");
      } else if true {
	      print("def");
	  } else {
		  print("ghi");
	  }
      ```
      Above code won't print out **anything** - since we don't know wheter first step `if null` evaluates to `true` or `false` we skip it, and because of it we also can't decide if we should execute `else`, because it only works if main condition failed! It will make sense if you think about it :)
 - `for` loops (TBA - maybe?) - implementing `foreach` loops known from Java or C#

## Functions:
Lets start with an example:
- ```
      Myfunction(int i, string s, bool p) -> (int result, float f, string response){
	      result = i + 13;
	      if p {
            response = "printed out message";
            print(s);
          } else {
            response = "didnt print out";
          }
          f = i ^ 0.5;
      }

      int a;
      Myfunction(3, "hello", true) -> (a, float f, string r);
      
      print(a);
      print(f);
      print(r);
- ```
	"hello"
	16
	1.7320508075688772
	"printed out message"
	```

Starting from the beginning:
 - No declaration of returned type, like `int f(){}`, because here we can have multiple returned values of different types! (thats what comes after the arrow `->`). That means that there is no `return` statement.
- Note: Function names **must** start with uppercase letter
- When calling function, we can both pass already declared variable (here `a`), or declare in the output (here `float f` and `string r`).
- Known issue: if we declare a variable inside the function with the same name as one declared outside of it, it will override it, which shouldn't happen. Maybe I'll fix it :)
## Blocks of code:
Created with `{}`.
**Important** note: variable scope is **only** one block up!
## Comment blocks:
Created and ended with `$` sign.
Note: for some strange Antlr reason - some special characters, like " ' " (apostrophe) and others like that can't be written in both comments and strings! I really don't know how to fix it.

## Casting values:
Casting is defined between all four basic types as:
| from\to | int | float | string | bool |
|---------|-----|-------|--------|------|
| int | == | adding coma| adding "" | 0=false, >0=true, <0=null |
| float | rounding| == | adding "" | 0=false, >0=true, <0=null |
| string | trying to parse| trying to parse| == | empty=false, else=true|
| bool | true=1, false=0, null=-1| same as to int| adding "" | == |

How to use in code: add `(@type)` to expression you want to cast. Note: brackets are needed

## Example code:
Can be found in `src/tests/` grouped by topic it covers.

## How to use:
Import project in IntelliJ, in there it should construct run config. Run it, then choose file (.txt) you want to run.
Outside IntelliJ - you're on your own to configure the `java` command properly :)

## (maybe?) Planned features:
- Type inference - like in C# `var`
- Array type - `multiple`
- `for` loops
- Multiple variable declaration and assignment, like in python (`int a, b, c = 2, 5, 1;`)
- Maybe change scope of blocks?
- Fixing issues :)
