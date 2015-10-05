//Program class
//Implemented linked list to store all the statement
public class Program {

	private SourceLine header;
	private SourceLine cursor;
	
	public Program() {
		initProgram();
		cursor = header;
	}
	
	public void clear() {
		deleteProgram();
		initProgram();
		System.out.println("Program deleted");
	}
	
	public void addSourceLine(int lineNumber, String line) {
		recInsertSorted(header, lineNumber, line);
	}
	
	public void removeSourceLine(int lineNumber) {
		recDeleteFromList(header, lineNumber);
	}
	
	public String getSourceLine(int lineNumber) {
		if (cursor.number == lineNumber) {
			return cursor.source;
		} else {
			System.out.println("cursor not in the right position");
		}
		return "";
	}
	
	public void setParsedStatement(int lineNumber, Statement stmt) {
		if (cursor.number == lineNumber) {
			cursor.lineStmt = stmt;
		} else {
			System.out.println("cursor not in the right position");
		}
	}
	
	public Statement getParsedStatement(int lineNumber) {
		if (cursor.number != lineNumber) {
			positionCursor(lineNumber);	
		}
		if (cursor != null) {
			return cursor.lineStmt;
		}

		return null;
	}
	
	public int getFirstLineNumber() {
		if (header.next != null) {
			cursor = header.next;
			return cursor.number;
	    } else {
		    return -1;
	    }
	}
	
	public int getNextLineNumber(int lineNumber) {
		if (cursor.number != lineNumber) {
			positionCursor(lineNumber);
		}

		if (cursor.next == null) {
			return -1;
		} else {
			SourceLine next = cursor.next;
			cursor = next; // Move cursor
			return cursor.number; // Hum, since the cursor has been moved to the next number
		}
	}
	
	private void recInsertSorted(SourceLine program, int lineNumber, String source) {
		if (program == null || lineNumber < program.number){
			// Create a new line
			SourceLine newLine = new SourceLine();
			newLine.number = lineNumber;
			newLine.source = source;
			newLine.next = program;
			program = newLine;
			cursor = newLine;
			System.out.println("new line added");
		} else if (lineNumber == program.number) {
			program.source = source;
			cursor = program;
			System.out.println("line modified");
		} else {
			recInsertSorted(program.next, lineNumber, source);
		}
	}
	
	private void recDeleteFromList(SourceLine program, int lineNumber) {
		if (program != null) {
			if (program.number == lineNumber) {
				program = program.next; 
				System.out.println("entry deleted");
			} else {
				recDeleteFromList(program.next, lineNumber);
			}
		}
	}
	
	private void positionCursor(int lineNumber) {
		
	}
	
	private void initProgram() {
		header = new SourceLine();
		cursor = null;
	}
	
	private void deleteProgram() {
		while (header != null) {
			SourceLine next = header.next;
			header = next;
		}
	}
	
	static class SourceLine {
		int number;
		String source;
		Statement lineStmt;
		SourceLine next;
		public  SourceLine() {
			number = 0;
			next = null;
		}
	}
	
	
}
