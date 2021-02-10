package evaluator;

import parser.ArithParser;
import stack.LinkedStack;

public class InfixEvaluator extends Evaluator {
	
	private LinkedStack<String> operators = new LinkedStack<String>();
	private LinkedStack<Integer> operands  = new LinkedStack<Integer>();
	
	/** return stack object (for testing purpose) */
	public LinkedStack<String> getOperatorStack() { return operators; }
	public LinkedStack<Integer> getOperandStack()  { return operands;  }
	
	public int degree(String token) {
		if(token.equals("(")) {
			return 0;
		}
		if(token.equals("+") || token.equals("-")) {
			return 1;
		}
		else if(token.equals("*") || token.equals("/")) {
			return 2;
		}
		else if(token.equals("!")) {
			return 3;
		}
		else {
			return 4;
		}
	}
	
	/** This method performs one step of evaluation of a infix expression.
	 *  The input is a token. Follow the infix evaluation algorithm
	 *  to implement this method. If the expression is invalid, throw an
	 *  exception with the corresponding exception message.
	 */	
	public void evaluate_step(String token) throws Exception {
		if(isOperand(token)) {
			operands.push(Integer.parseInt(token));
			}
		 else {
			if(token.equals("(")) {
				operators.push(token);
			}
			else if(token.equals(")")) {
				String theTop = operators.top();
				while(!theTop.equals("(") && !operators.isEmpty()) {
					process_operator();
					theTop = operators.top();
					if(theTop == null) {
						throw new Exception("missing (");
					}
				}
				if(!theTop.equals("(")) {
					throw new Exception("missing (");
				}
				else {
					operators.pop();
				}
				
			}
			else if(token.equals("+") || token.equals("-") || token.equals("*") || 
					token.equals("/") || token.equals("!")) {
				
				if(operators.top() == null) {
					operators.push(token);
					return;
				}
				int recent = degree(operators.top());
				int tok = degree(token);
				if(tok > recent) {
					operators.push(token);
				}
				else {
					while(!(tok > recent)) {
						process_operator();
						if(operators.isEmpty()) {
							break;
						}
						recent = degree(operators.top());
//						if(operators.top().equals("(")) {
//							operators.pop();
//						}
					}
					operators.push(token);
				}
			}
			else {
				throw new Exception("invalid operator");
			}
			
			
			/* TODO: What do we do if the token is an operator?
			   If the expression is invalid, make sure you throw
			   an exception with the correct message.
			   
			   You can call precedence(token) to get the precedence
			   value of an operator. It's already defined in 
			   the Evaluator class.
			 */ 
		}	
	}
	
	public void process_operator() throws Exception{
		String token = operators.pop();
//		if(token.equals("(")) {
//			return;
//		}
		if(operands.top() == null) {
			throw new Exception("too few operands");
		}
		int one = operands.pop();
		if(token.equals("!")) {
			operands.push(one * -1);
			return;
		}
		if(operands.top() == null) {
			throw new Exception("too few operands");
		}
		int two = operands.pop();
		
		if(token.equals("+")) {
			operands.push(two + one);
		}
		else if(token.equals("-")) {
			operands.push(two - one);
		}
		else if(token.equals("*")) {
			operands.push(two * one);
		}
		else if(token.equals("/")) {
			if(one == 0) {
				throw new Exception("division by zero");
			}
			operands.push(two / one);
		}
		else {
			throw new Exception("invalid operator");
		}
	}
	
	/** This method evaluates an infix expression (defined by expr)
	 *  and returns the evaluation result. It throws an Exception object
	 *  if the infix expression is invalid.
	 */
	public Integer evaluate(String expr) throws Exception {
		
		for(String token : ArithParser.parse(expr)) {
			evaluate_step(token);
		}
		
		/* TODO: what do we do after all tokens have been processed? */
//		int startCount = 0;
//		int endCount = 0;
//		for(String token : ArithParser.parse(expr)) {
//			if(token.equals("(")) {
//				startCount++;
//			}
//			else if(token.equals(")")) {
//				endCount++;
//			}
//		}
//		if(startCount < endCount) {
//			throw new Exception("missing (");
//		}
		while(!operators.isEmpty()) {
			process_operator();
		}
		// The operand stack should have exactly one operand after the evaluation is done
		if(operands.size()>1)
			throw new Exception("too many operands");
		else if(operands.size()<1)
			throw new Exception("too few operands");
		
		return operands.pop();
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(new InfixEvaluator().evaluate("5+(5+2*(5+9))/!8"));
	}
}
