//Evaluation context class, implements a hashmap
//to store the statement and corresponding value
import java.util.HashMap;

public class EvaluationContext {
	
	private int execLine;
	
	HashMap<String, Integer> symbolTable;
	public EvaluationContext() {
		symbolTable = new HashMap<String, Integer>();
		execLine = 0;
	}
	public void setValue(String var, int value) {
		symbolTable.put(var, value);
		
	}
	
	public int getValue(String var) {
		return symbolTable.get(var);
	}

	public boolean isDefined(String var) {
		return symbolTable.containsKey(var);
	}
	
	public void setCurrentLine(int lineNumber) {
		execLine = lineNumber;
	}
	
	public int getCurrentLine() {
		return execLine;
	}
}
