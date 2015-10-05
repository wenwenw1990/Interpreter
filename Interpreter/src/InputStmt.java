//Input statement class
//Not implemented so far
public class InputStmt extends Statement {
	private String variableName;
	
	public InputStmt(TokenScanner scanner) {
		variableName = scanner.nextToken();
		if (scanner.hasMoreTokens()) {
			System.out.println("Extraneous token " + scanner.nextToken());
		}
	}
	public void execute(EvaluationContext context) {
		//int value = ("  ?  ");
		context.setValue(variableName, -1);
	};
}
