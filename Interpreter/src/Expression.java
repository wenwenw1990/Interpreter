//Abstract expression
public abstract class Expression {

	public abstract int eval(EvaluationContext context);
	
	public abstract String toString();
	
	public abstract ExpressionType getType();
	
}
