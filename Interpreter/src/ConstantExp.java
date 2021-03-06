//Constant Expression class
public class ConstantExp extends Expression {
	private int value;
	
	public ConstantExp(int value) {
		this.value = value;
	}
	
	public int eval(EvaluationContext context) {
		return value;
	}
	
	public String toString() {
		return Integer.toString(value);
	}
	
	public ExpressionType getType() {
		return ExpressionType.CONSTANT;
	}
	
	public int getConstantValue() {
		return value;
	}

}
