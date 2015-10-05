//Let statement class
//to assign values to variables
public class LetStmt extends Statement{
	private Expression exp;
	private String variableName;
	Parser p = new Parser();
	public LetStmt(TokenScanner scanner) {
		variableName = scanner.nextToken();
		scanner.nextToken(); //pop the equation mark
		exp = p.readE(scanner, 0);
		if (scanner.hasMoreTokens()) {
			System.out.println("Extraneous token " + scanner.nextToken());
		}
	}
	
	public void execute(EvaluationContext context) {
		int value = exp.eval(context);
		context.setValue(variableName, value);
		System.out.println(value);
	}

}
