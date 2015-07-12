package com.eddie.project.backend;
import java.util.*;



public class ReversePolishNotation {

	public static void main(String[] args) throws OperatorSymbolException {
		
		
		String input;
		System.out.println("Please, input simple mathematical expression");
		Scanner scan = new Scanner(System.in);
		input = scan.nextLine();
		scan.close();
//		input = "(21-9)*2-1";
//		input = "30-15/(9-6)";
		isCorrectExpression(input);
		ArrayList<String> arrayInput = toSeparateArrayStrings(input);
		ArrayList<String> arrayOutput = toPolishNotation(arrayInput);
		double result = evaluatePolishNotation(arrayOutput);
		System.out.println(input + '\n' + arrayInput + '\n' + arrayOutput + '\n' + result);
	}
	
	/*
	 * Evaluate one operation
	 */
	private static double evaluateOneOperation(double value1, double value2, String operator) throws OperatorSymbolException{
		
		switch (operator) {
		case "+": return value1 + value2; 
		case "-": return value1 - value2; 
		case "*": return value1 * value2; 
		case "/": return value1 / value2;
		case "^": return Math.pow(value1, value2);
		default : throw new OperatorSymbolException();
		}
		
		
	}
	
	
	/*
	 * Evaluate Polish Notation
	 */
	
	private static double evaluatePolishNotation(List<String> arrayOutput) throws OperatorSymbolException{
		Stack<String> stack = new Stack<String>();
		double value1;
		double value2;
		
		for (String item : arrayOutput){
			
			if(item.equals("+") || item.equals("-") || item.equals("*") || item.equals("/") || item.equals("^")){
				value2 = Double.valueOf(stack.pop());
				value1 = Double.valueOf(stack.pop());
				double result = evaluateOneOperation(value1, value2, item);
				stack.add(String.valueOf(result));
			} else {
				stack.add(item);
			}
			
		}
		if(stack.size() == 1){
			return Double.valueOf(stack.pop());
		} else {
			throw new OperatorSymbolException();
		}
	}
	
	
	/*
	 * to Polish Notation
	 */
	private static ArrayList<String> toPolishNotation(List<String> arrayInput) throws OperatorSymbolException{
		ArrayList<String> arrayOutput = new ArrayList<String>();
		Stack<String> operatorStack = new Stack<String>();
		//iterate to Polish Notation
		for(String item : arrayInput){
			if(isDigit(item.charAt(0))){
				arrayOutput.add(item);
			} else 	if(!operatorStack.isEmpty()){
						if(getPriority(operatorStack.peek()) >= getPriority(item)){
							if( item.equals(")") ){
								while(!operatorStack.peek().equals("(")){
									arrayOutput.add(operatorStack.pop());
								}
								operatorStack.pop(); // delete "("
							} else if( !item.equals("(")){ // was exception 
								arrayOutput.add(operatorStack.pop());
								operatorStack.add(item);
							} else {
								operatorStack.add(item);
							}
						} else {
							operatorStack.add(item);
						}
			} else {
				operatorStack.add(item);
			}
		}
		//clear Stack
		while(!operatorStack.isEmpty()){
			arrayOutput.add(operatorStack.pop());
		}
		
		return arrayOutput;
	}
		
		
		

	
	/*
	 * priority
	 */
	private static byte getPriority(String s) throws OperatorSymbolException{
		switch (s) {
		case "(": return 0;
		case ")": return 0;
		case "+": return 1;
		case "-": return 1;
		case "*": return 2;
		case "/": return 2;
		case "^": return 3;
		default: throw new OperatorSymbolException();
		}
	}
	
	/*
	 * isDigit ?
	 */
	private static boolean isDigit(char ch){
		switch (ch) {
		case '0': return true;
		case '1': return true;
		case '2': return true;
		case '3': return true;
		case '4': return true;
		case '5': return true;
		case '6': return true;
		case '7': return true;
		case '8': return true;
		case '9': return true;
			
		
		default: return false;
		}
	}
	
	/*
	 * separate String 
	 */
	private static ArrayList<String> toSeparateArrayStrings(String input){
		char[] ch = input.toCharArray();
		ArrayList<String> arrayInput = new ArrayList<String>();
		int startIndex = 0;
		for (int i = 0; i < input.length(); i++){
			
			if(isDigit(ch[i])){
				if(i == input.length()-1){
					arrayInput.add(input.substring(i - startIndex, i) + ch[i]); 
				}
				startIndex++;
				
			} else {
				if(startIndex > 0){
					arrayInput.add(input.substring(i - startIndex, i));
					startIndex = 0;
				} 
				arrayInput.add(String.valueOf(ch[i]));

			}
			
		}
		startIndex = 0;
		return arrayInput;
		
	}
	
	/*
	 * is Correct expression?
	 */
	private static void isCorrectExpression(String input) throws OperatorSymbolException{
		char[] ch = input.toCharArray();
		for (char c : ch)
			if(!isDigit(c)) getPriority(String.valueOf(c));
	}
	
	public static String[] getResult(String input){
		
		String[] result = new String[2];
		
		try{
			isCorrectExpression(input);
			ArrayList<String> arrayInput = toSeparateArrayStrings(input);
			ArrayList<String> arrayOutput = toPolishNotation(arrayInput);
			double res = evaluatePolishNotation(arrayOutput);
			StringBuilder rpn = new StringBuilder();
			for (String arr : arrayOutput){
				rpn.append(arr + " ");
			}
			result[0] = rpn.toString();
			result[1] = String.valueOf(res);
			return result;
			
		} catch(Exception e) {
			result[0] = e.getMessage();
			result[1] = e.getMessage();
			return result;
		}
		
		
	}

}


