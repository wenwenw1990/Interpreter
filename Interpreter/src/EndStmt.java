//End Statement class
public class EndStmt extends Statement{

	public EndStmt(TokenScanner scanner) {
		if (scanner.hasMoreTokens()) {
			System.out.println("Extra token");
		}
	}
	
	public void execute(EvaluationContext context) {
		context.setCurrentLine(-1);
	}
}
