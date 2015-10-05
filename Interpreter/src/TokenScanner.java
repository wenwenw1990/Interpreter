//Token Scanner class
//to get input statement line and store in a stack
//get output word by word
import java.util.Deque;
import java.util.LinkedList;

public class TokenScanner {

	private String[] array;
	private Deque<String> stack = new LinkedList<String>();
	public TokenScanner(String s) {
		array = s.split(" ");
		for (String token : array) {
			stack.offerFirst(token);
		}
	}
	
	public boolean hasMoreTokens() {
		return stack.size() > 0;
	}
	
	public String nextToken() {
		return stack.pollLast();
	}
	
	public void saveToken(String token) {
		stack.offerLast(token);
	}
}
