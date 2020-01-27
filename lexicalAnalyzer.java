

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class lexicalAnalyzer {
	public String[] Tokens;
	public String currentToken;
	public int currenTokenInt;
	int curpos=-1;
	String[][][] book= {
			{},
			{{";","5"},{"+","7"},{"-","8"},{"*","9"},{"/","10"},{"=","11"},{"(","13"},{")","14"}},
			{{"if","15"},{":=","6"},{"!=","12"}},
			{{"end","4"}},
			{{"else","17"},{"then","16"}},
			{{"begin","3"},{"endif","18"}},
			{},
			{},
			{},
			{{"procedure","2"}},
			{{"ENDOFINPUT","19"}}
	};
	
	lexicalAnalyzer(String filename){        // Lex analyzer loads in String filename
		File file= new File(filename);
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {  //FileNotFoundException
			e.printStackTrace();
		}
		String temp="";
		while (sc.hasNextLine()){		// Saves the scanned file into an empty String
			temp=temp+sc.nextLine();
		}
		Tokens=temp.split("\\s+");  // Splits the file into tokens at all whitespaces
		this.lex();
	}
	
	String getCurrentToken(){			//retrieves String from currentToken and returns it
		return this.currentToken;
	}
	int getCurrentTokenInt() { 			//retrieves int from currentToken and returns it
		return this.currenTokenInt;
	}
	
	int lex(){					
		if (!(this.atEnd())){			//lex iterating through each token until end of file
			int output=-1;
			this.curpos++;
			currentToken=Tokens[curpos];
			for (int i=0;i<book[currentToken.length()].length;i++) {
				if(currentToken==book[currentToken.length()][i][0]){
					output=Integer.valueOf(book[currentToken.length()][i][1]);
				}
			}
			if (output==-1) {
				try {
					Integer.parseInt(currentToken);
					output=1;
				}
				catch (Exception e) {
					output=0;
				}
			}
			currenTokenInt=output;
			return output;
		}
		else{
			currentToken= "ENDOFINPUT";
			return 19;
		}
	}
	
	boolean atEnd(){
		return curpos==Tokens.length-1;
	}
	
}
