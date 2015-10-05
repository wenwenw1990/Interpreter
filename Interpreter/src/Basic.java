import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Basic {
 
	Parser p = new Parser();
	public void processLine(String line, Program program, EvaluationContext context) {
		TokenScanner scanner = new TokenScanner(line);
		if (!scanner.hasMoreTokens()) {
			return;
		}
		//Get the first word of the statement to know the command need executed
		String firstElement = scanner.nextToken();
		if (firstElement.equals("END")) {
			quitCmd();
			return;
		}

		if (firstElement.equals("LIST")) {
			listCmd(program);
			return;
		}

		if (firstElement.equals("CLEAR")) {
			clearCmd(program);
			return;
		}

		if (firstElement.equals("RUN")) {
			runCmd(program, context);
			return;
		}
		if (firstElement.equals("DEBUG")) {
			Expression exp = p.parseExp(scanner);
			exp.eval(context);
			return;
		}
		
		if (firstElement.equals("PRINT")) {
			scanner.saveToken(firstElement);
			Statement stmt = p.parseStatement(scanner);
			PrintStmt ps = (PrintStmt) stmt;
			ps.execute(context);
			return;
		}
		if (firstElement.equals("LET")) {
			System.out.println(1);
			scanner.saveToken(firstElement);
			Statement stmt = p.parseStatement(scanner);
		    LetStmt ps = (LetStmt) stmt;
			ps.execute(context);
			return;
		}
		if (firstElement.equals("INPUT")) {
			scanner.saveToken(firstElement);
			Statement stmt = p.parseStatement(scanner);
		    InputStmt ps = (InputStmt) stmt;
			ps.execute(context);
			return;
		}

		if ((Integer.parseInt(firstElement) > 0)||(Integer.parseInt(firstElement) < 99999)) {
			int lineNumber = Integer.parseInt(firstElement);
			int numberPos = firstElement.length() + 1;
			int lineLength = line.length() - firstElement.length();
			String source = "";
			if (lineLength != 0) {
				source = line.substring(numberPos, lineLength);
			}
			editPrgCmd(program, lineNumber, source, scanner); 
			return;
		}
	}
	
	//Quit
	public void quitCmd() {
		 System.out.println("Bye bye!");
	}
	
	//List
	public  void listCmd(Program program) {
		int lineNumber = program.getFirstLineNumber();
	    if (lineNumber == -1) {
	        System.out.println("No program lines");
	    } else {
		    while (lineNumber != -1) {
			    String source = program.getSourceLine(lineNumber);
			    System.out.println(lineNumber + " " + source);
			    lineNumber = program.getNextLineNumber(lineNumber);
		    }
	    }
		
	}
	
	//Clear
	public void clearCmd(Program program) {
		program.clear();
	}
	
	//Edit
	public void editPrgCmd(Program program, int lineNumber, String lineSource, TokenScanner scanner) {
		if (lineSource.equals("")) {
			program.removeSourceLine(lineNumber);
		} else {
			program.addSourceLine(lineNumber, lineSource);
			Statement currentStmt = p.parseStatement(scanner);
			program.setParsedStatement(lineNumber, currentStmt);
		}
	}
	
	//Run
	public void runCmd(Program program, EvaluationContext context) {
		int lineNumber = program.getFirstLineNumber();
		System.out.println(lineNumber);
		while (lineNumber != -1) {
			Statement currentStmt = program.getParsedStatement(lineNumber);
			lineNumber = program.getNextLineNumber(lineNumber);
			context.setCurrentLine(lineNumber);
			currentStmt.execute(context);
			lineNumber = context.getCurrentLine();
		}
	}
	
	//Main function
	public static void main(String[] args) {
		Basic basic = new Basic();
		EvaluationContext context = new EvaluationContext();
		Program program = new Program();
		BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please write in console");
		String line = null;
		Queue<String> queue = new LinkedList<String>();
		try {
			while (true) {
				line = br.readLine();
				queue.offer(line);
				if (line.equals("RUN")) {
					break;
				}
			}
			System.out.println(queue.size());
			while (queue.size() > 1) {
				line = queue.poll();
				int i = 0;
				while (line.charAt(i) >= '0' && line.charAt(i) <= '9') {
					i++;
				}
				int lineNumber = Integer.parseInt(line.substring(0, i));
				program.addSourceLine(lineNumber, line.substring(i + 1, line.length()));
				//basic.processLine(queue.poll(), program, context);
			}
			basic.runCmd(program, context);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}