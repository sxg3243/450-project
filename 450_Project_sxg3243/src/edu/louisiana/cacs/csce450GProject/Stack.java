package edu.louisiana.cacs.csce450GProject;

public class Stack {

	public String push(String expr, String[] stack) {
		int a=0,i;
		
		while(stack[a]!=null)
		{
			
			a++;
		}
		stack[a]=expr;
		
		return stack[a];
		// TODO Auto-generated method stub

		
		
	}

	public String[] pop(int b, String[] stack) {
		// TODO Auto-generated method stub
		int a=stack.length;
		a--;
		for(int i=0;i<b;i++){
			stack[a]=" ";
			a--;
		}
		stack[a]="0";
		return stack;
	}

	

}