//Identifier Expression class
//object of variables in the program
public class IdentifierExp extends Expression{
	
	private String name;
	
	public IdentifierExp(String name) {
		this.name = name;
	}
	
	public int eval(EvaluationContext context) {
		if (!context.isDefined(name)) {
			System.out.println(name + "is undefined");
		}
		return context.getValue(name);
		
	}
	
	public String toString() {
		return name;
	}
	
	public ExpressionType getType() {
		return ExpressionType.IDENTIFIER;
	}
	
	public String getIdentifierName() {
		return name;
	}

}
