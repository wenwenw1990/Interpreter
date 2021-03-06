//CompundExpression class for expressions like x1 + x2ß
public class CompoundExp extends Expression{
	
	private String op;
	private Expression lhs, rhs;
	
	public CompoundExp(String op, Expression lhs, Expression rhs) {
		this.op = op;
		this.lhs = lhs;
		this.rhs = rhs;
	}
	
	public int eval(EvaluationContext context) {
		int left = (lhs).eval(context);
		int right = (rhs).eval(context);
		if (op.equals("+")) {
			return left + right;
		}
		if (op.equals("-")) {
			return left - right;
		}
		if (op.equals("*")) {
			return left * right;
		}
		if (op.equals("/")) {
		    if (right == 0) {
		    	System.out.println("Division by 0");
		    }
		    return left / right;
		}
		return 0;
	}
	
	public String toString() {
		return '(' + lhs.toString() + ' ' + op + ' ' + rhs.toString() + ')';
	}
	
	public ExpressionType getType() {
		return ExpressionType.COMPOUND;
	}
	
	public String getOperator() {
		return op;
	}
	
	public Expression getLHS() {
		return lhs;
	}
	
	public Expression getRHS() {
		return rhs;
	}

}
