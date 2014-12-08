package edu.louisiana.cacs.csce450GProject;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Parser {

	Stack myStack=new Stack();
	int x;
	static int n=0;
	static String file;
	static String ip;
	final static String[] input_exp={"id","+","id","*","id","$"," "};
	String fileName;
	static int last;
	static String top_of_stack;
	String[] expr;
	static int look_a_head=0;
	static String action_value;
	static String action_lookup;
	final static String[] stack=new String[40];
	static String stack_action;
	static String str1="E+T";
	static String str2="T";
	static String str3="T*F";
	static String str4="F";
	static String str5="(E)";
	static String str6="id";
	int length_of_RHS;
	static String value_of_LHS;
	static String[] exp=new String[20];
	static String goto_value;
	static String goto_lookup;
	static String temp1;
	static String below;
	public Parser(String fileName) throws IOException{
		System.out.println("File to parse : "+fileName);
		file=fileName;
		//parse();
	}
	/*public Parser(String[] in){
		input_exp=in;
	}*/
	public void parse() throws IOException{
		//Retrieves the data from file into string array and invokes the actual parsing technique
		
/*
		FileInputStream fis = null;
        BufferedReader reader = null;
      
        try {
            fis = new FileInputStream("src/sample.txt");
            reader = new BufferedReader(new InputStreamReader(fis));
          
            //System.out.println("Reading File line by line using BufferedReader");
          
            String line = reader.readLine();
            int x=0;
            while(line != null){
                
                line = reader.readLine();
                ip=line;
            }           
            input_exp[x]=(ip.toCharArray()).toString(); 
            input_exp = ip.split(" ");
        }  finally {
            try {
                reader.close();
                fis.close();
            } catch (IOException ex) {
                  }
        }   */ 
				
		top_of_stack="0";
		
		expr=input_exp;
		while(input_exp[look_a_head]!=" "){	
			
			action(top_of_stack,input_exp[look_a_head]);
			
			System.out.println(Arrays.toString(stack)+"\t"+Arrays.toString(input_exp)+"\t"+"\t"+action_value+"\t"+value_of_LHS+"\t"+"\t"+goto_lookup+"\t"+goto_value+"\t"+stack_action+"\t");
			look_a_head++;
		}
		
					
		
	}

	private void action(String top_of_stack,String input_exp) {
		// TODO Auto-generated method stub
		
		if(input_exp=="("){
			
			if(top_of_stack=="0"||top_of_stack=="4"||top_of_stack=="6"||top_of_stack=="7"){
				shift("4");
				stack_action="push"+input_exp+'4';
				action_value="s4";
				
				
			}
			else{
				System.out.println("ERROR");
			}			
		}
		else if(input_exp=="+"){
			
			if(top_of_stack=="1"){
				
				shift("6");
				stack_action="push"+input_exp+'6';
				action_value="s6";
				
			}
			else if(top_of_stack=="8"){
				shift("6");
				stack_action="push"+input_exp+'6';
				action_value="s6";
			}
			else if(top_of_stack=="2"){
				reduce("2");
				action_value="r2";
			}
			else if(top_of_stack=="3"){
				reduce("4");
				action_value="r4";
			}
			else if(top_of_stack=="5"){
				reduce("6");
				action_value="r6";
			}
			else if(top_of_stack=="9"){
				reduce("1");
				action_value="r1";
			}
			else if(top_of_stack=="10"){
				reduce("3");
				action_value="r3";
			}
			else if(top_of_stack=="11"){
				reduce("5");
				action_value="r5";
			}
			else{
				System.out.println("ERROR");
			}				
		}
		else if(input_exp.matches(".*[A-Za-z].*")){
			//action(0,id);(4,id);(6,id);(7,id)
			if(top_of_stack=="0"||top_of_stack=="4"||top_of_stack=="6"||top_of_stack=="7"){
				
				shift("5");
				stack_action="push"+input_exp+'5';
				action_value="s5";
				
			}
			else{
				System.out.println("ERROR");
			}
		}
		
		else if(input_exp=="*"){
			if(top_of_stack=="2"||top_of_stack=="9"){
				shift("7");
				action_value="s7";
				//look_a_head++;
			}
			else if(top_of_stack=="3"){
				reduce("4");
				action_value="r4";
			}
			else if(top_of_stack=="5"){
				reduce("6");
				action_value="r6";
			}
			else if(top_of_stack=="10"){
				reduce("3");
				action_value="r3";
			}
			else if(top_of_stack=="11"){
				reduce("5");
				action_value="r5";
			}
			else{
				System.out.println("ERROR");
			}
		}
		else if(input_exp==")"){
			if(top_of_stack=="2"){
				reduce("2");
				action_value="r2";
			}
			else if(top_of_stack=="3"){
				reduce("4");
				action_value="r4";
			}
			else if(top_of_stack=="5"){
				reduce("6");
				action_value="r6";
			}
			else if(top_of_stack=="8"){
				shift("11");
				action_value="s11";
				look_a_head++;
			}
			else if(top_of_stack=="9"){
				reduce("1");
				action_value="r1";
			}
			else if(top_of_stack=="10"){
				reduce("3");
				action_value="r3";
			}
			else if(top_of_stack=="11"){
				reduce("5");
				action_value="r5";
			}
			else{
				System.out.println("ERROR");
			}			
		}
		else if(input_exp=="$"){
			if(top_of_stack=="1"){
				System.out.println("ACCEPT");
			}
			else if(top_of_stack=="2"){
				reduce("2");
				action_value="r2";
			}
			else if(top_of_stack=="3"){
				reduce("4");
				action_value="r4";
			}
			else if(top_of_stack=="5"){
				reduce("6");
				action_value="r6";
			}
			else if(top_of_stack=="9"){
				reduce("1");
				action_value="r1";
			}
			else if(top_of_stack=="10"){
				reduce("3");
				action_value="r3";
			}
			else if(top_of_stack=="11"){
				reduce("5");
				action_value="r5";
			}
			else{
				System.out.println("ERROR");
			}
		}
	}

	

	private void shift(String i) {
		// TODO Auto-generated method stub
		String temp;		
		temp=input_exp[look_a_head];
		System.out.println(look_a_head);
		stack[n]=myStack.push(temp,stack);
		n++;
		top_of_stack=temp;
		stack[n]=myStack.push(i,stack);
		n++;
		top_of_stack=i;
		
	}
	private void reduce(String a) {
		// TODO Auto-generated method stub
		int b,i;
		String[] c;
		String d,temp;
		
		exp=input_exp;
		if(a=="1"){
			b=str1.length();
			length_of_RHS=b;
			b=b*2;
			c=myStack.pop(b,stack);
			temp="E";
			stack[n]=myStack.push(temp,stack);
			n++;
			top_of_stack=temp;
			top_of_stack="E";
			value_of_LHS="E";
			int y = stack.length;
			y--;
			for(i=y;i>=0;i--)
			{
				
				if(stack[i].matches(".*[0-9].*")){
					below=stack[i];
					break;			
				}
			}
			d=GOTO(below,top_of_stack);
			goto_lookup="["+below+","+temp+"]";
			stack_action="push"+value_of_LHS+d;
			look_a_head--;
		}
		else if(a=="2"){
			b=str2.length();
			length_of_RHS=b;
			b=b*2;
	 		c=myStack.pop(b,stack);
	 		temp="E";
			stack[n]=myStack.push(temp,stack);
			n++;
			top_of_stack=temp;
			top_of_stack="E";
			value_of_LHS="E";			
			int y = stack.length;
			y--;
			for(i=y;i>=0;i--)
			{
				if(stack[i].matches(".*[0-9].*")){
					below=stack[i];
					break;			
				}
			}			
			d=GOTO(below,top_of_stack);
			goto_lookup="["+below+","+temp+"]";
			stack_action="push"+value_of_LHS+d;
			look_a_head--;
		}
		else if(a=="3"){
			b=str3.length();
			length_of_RHS=b;
			b=b*2;
			c=myStack.pop(b,stack);
			temp="T";
			stack[n]=myStack.push(temp,stack);
			n--;
			top_of_stack="T";
			value_of_LHS="T";
			int y = stack.length;
			y--;
			for(i=y;i>=0;i--)
			{
				if(stack[i].matches(".*[0-9].*")){
					below=stack[i];
					break;			
				}
			}
			d=GOTO(below,top_of_stack);
			goto_lookup="["+below+","+temp+"]";
			stack_action="push"+value_of_LHS+d;
			look_a_head--;
		}
		else if(a=="4"){
			b=str4.length();
			length_of_RHS=b;
			b=b*2;
			c=myStack.pop(b,stack);
			temp="T";
			stack[n]=myStack.push(temp,stack);
			n++;
			top_of_stack=temp;
			value_of_LHS="T";
			int y = stack.length;
			y--;
			for(i=y;i>=0;i--)
			{
				if(stack[i].matches(".*\\d.*")){
					below=stack[i];
					break;			
				}
			}
			d=GOTO(below,top_of_stack);
			goto_lookup="["+below+","+temp+"]";
			stack_action="push"+value_of_LHS+d;
			look_a_head--;
		}
		else if(a=="5"){
			b=str5.length();
			length_of_RHS=b;
			b=b*2;
			c=myStack.pop(b,stack);
			temp="F";
			c[n]=myStack.push(temp,stack);
			n++;
			top_of_stack=temp;
			value_of_LHS="F";
			int y = stack.length;
			y--;
			for(i=y;i>=0;i--)
			{
				if(c[i].matches(".*\\d.*")){
					below=c[i];
					break;			
				}
			}
			d=GOTO(below,top_of_stack);
			goto_lookup="["+below+","+temp+"]";
			stack_action="push"+value_of_LHS+d;
			look_a_head--;
		}
		else if(a=="6"){
			b=str6.length();
			length_of_RHS=b;
			b=b*2;
			c=myStack.pop(b,stack);
			temp="F";
			c[n]=myStack.push(temp,stack);
			n++;
			top_of_stack=temp;
			value_of_LHS="F";
			int y = c.length;
			y--;
			for(i=y;i>=0;i--)
			{
				if(c[i]!=null){
					if(c[i].matches(".*\\d.*")){
						below=c[i];
						break;			
					}
				}
				
				
			}
			d=GOTO(below,temp);
			goto_lookup="["+below+","+temp+"]";
			stack_action="push"+value_of_LHS+d;
			look_a_head--;
		}
		else{
			System.out.println("UNGRAMMATICAL");
		}
		
	}

	private String GOTO(String a, String b) {
		// TODO Auto-generated method stub
		
		if(a=="0"){
			if(b=="E"){
				temp1="1";
				stack[n]=myStack.push(temp1,stack);				
				n++;
				top_of_stack=temp1;
				goto_value="1";
				System.out.println(top_of_stack);
				
			}
			else if(b=="T"){
				temp1="2";
				stack[n]=myStack.push(temp1,stack);
				n++;
				top_of_stack=temp1;
				goto_value="2";
			}
			else if(b=="F"){
				temp1="3";
				stack[n]=myStack.push(temp1,stack);
				n++;
				top_of_stack=temp1;
				goto_value="3";
			}
			else{
				//System.out.println("ERROR");
			}
		}
		else if(a=="4"){
			if(b=="E"){
				temp1="8";
				stack[n]=myStack.push(temp1,stack);
				n++;
				top_of_stack=temp1;
				goto_value="8";
			}
			else if(b=="T"){
				temp1="2";
				stack[n]=myStack.push(temp1,stack);
				n++;
				top_of_stack=temp1;
				goto_value="2";
			}
			else if(b=="F"){
				temp1="3";
				stack[n]=myStack.push(temp1,stack);
				n++;
				top_of_stack=temp1;
				goto_value="3";
			}
			else{
				//System.out.println("ERROR");
			}
		}
		else if(a=="6"){
			if(b=="T"){
				temp1="9";
				stack[n]=myStack.push(temp1,stack);
				n++;
				top_of_stack=temp1;
				goto_value="9";
			}
			else if(b=="F"){
				temp1="3";
				stack[n]=myStack.push(temp1,stack);
				n++;
				top_of_stack=temp1;
				goto_value="3";
			}
			else{
				//System.out.println("ERROR");
			}
		}
		else if(a=="7"){
			if(b=="F"){
				temp1="10";
				stack[n]=myStack.push(temp1,stack);
				n++;
				top_of_stack=temp1;
				goto_value="10";
			}
			else{
			//	System.out.println("ERROR");
			}
		}
		else{
			System.out.println("ERROR");
		}
		return goto_value;
	}
	/*public void printHeader() {
		// TODO Auto-generated method stub
		final String s1 = "-----------------------------------------------------------------------------------------------------------------------------------------------------------";
		System.out.format("%10s%15s%15s%15s%15s%15s%15s%15s%15s%20s",
				"stack", "	input tokens", "	action lookup", "	action value", "	value of LHS","	temp stack","	goto lookup","	goto value","	stack action","	sparse tree stack" );

		System.out.print("\n");
	}*/
}	


