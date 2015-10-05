
public class IfStmt extends Statement{
	private Expression exp1;
	private Expression exp2;
	private String token;
	private int nextLine;
	
	Parser p = new Parser();
	public IfStmt(TokenScanner scanner) {
		exp1 = p.readE(scanner, 0);
		token = scanner.nextToken();
		exp2 = p.readE(scanner, 0);
		if (!scanner.nextToken().equals("THEN")) {
			System.out.print("Wrong IF");
		}
		nextLine = Integer.parseInt(scanner.nextToken());
		if (scanner.hasMoreTokens()) {
			System.out.println("Wrong IF");
		}
	}

	public void execute(EvaluationContext context) {
		if (token.equals("=")) {
			if (exp1.eval(context) == exp2.eval(context)) {
				context.setCurrentLine(nextLine);
			}
			return;
		}

		if (token.equals("<")) {
			if (exp1.eval(context) < exp2.eval(context)) {
				context.setCurrentLine(nextLine);
			}
			return;
		}

		if (token.equals(">")) {
			if (exp1.eval(context) > exp2.eval(context)) {
				context.setCurrentLine(nextLine);
			}
			return;
		}
	}
	
}
