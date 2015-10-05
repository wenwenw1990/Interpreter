//print statement class
public class PrintStmt extends Statement{
	
	private Expression exp;
	Parser p = new Parser();
	public PrintStmt(TokenScanner scanner) {
		
		exp = p.readE(scanner, 0);
		if (scanner.hasMoreTokens()) {
		    System.out.println("Extranious token");
		}
	}
	
	public void execute(EvaluationContext context) {
		System.out.println(exp.eval(context));
	}
}
