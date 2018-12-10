package jeopardyLab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Akash Chandra & William Spencer
 * Teacher Miss Denna PER 7
 *
 * Created Date: 11-19-2018
 */
public class Board {
	private final int ROWS = 5;
	private final int COLUMNS = 4;
	private Question[][] q = new Question[ROWS][COLUMNS];
	public int total = 0;
	private String cat1,cat2,cat3,cat4;
	
	public Board(String file) throws FileNotFoundException
	{
		int column = 0;
	
		Scanner inF = new Scanner(new File(file));
		
		cat1 = inF.nextLine();
		cat2 = inF.nextLine();
		cat3 = inF.nextLine();
		cat4 = inF.nextLine();
				
		while(inF.hasNextLine())
		{
			int money = inF.nextInt();
			inF.nextLine();
			String Question = inF.nextLine();
			String RightAnswer = inF.nextLine();
			String DummyAnswer1 = inF.nextLine();
			String DummyAnswer2 = inF.nextLine();
			String DummyAnswer3 = inF.nextLine();
			
			int row = money / 100;
			row--;
			q[row][column] = new Question(Question, RightAnswer, DummyAnswer1, DummyAnswer2, DummyAnswer3, money);
			total++;
			if(row == 4)
			{
				column++;
			}
		}
		
		inF.close();
	}
	
	public Question getQuestion(int r, int c)
	{
		//uses toString for the asking of the question
		Question out;
		out = q[r][c];
		return out;
	}
	
	//X cords
	public int getRow()
	{
		return q[0].length;
	}
	
	//Y cords
	public int getColumn()
	{
		return q.length;
	}
	
	private String[] makeCat(String cat1a, String cat2a, String cat3a, String cat4a)
	{
		String[] out = {cat1a,cat2a,cat3a,cat4a};
		return out;
	}
	
	public String toString()
	{
		// Fix this, failing that, morphine 
		String str = "";
		int i,c;
		
		str += "\t";
		
		String [] cats = makeCat(cat1, cat2, cat3, cat4);
		
		for(int a = 0; a < cats.length; a++)
		{
			if(cats[a].equals("Music"))
				str += cats[a] + "  \t";
			else
				str += cats[a] + "\t";
		}
		
		str += "\n\t";
		
		for(int s = 0; s < q[0].length; s++)
		{
			str += s + "\t" + getLineSpacing(cats[s]); //add line spacing
		}
		
		str += "\n";
		
		for(i = 0; i < q.length; i++)
		{
			str += i + "\t";
			for(c = 0; c < q[i].length; c++)
			{
				if(q[i][c] != null)
				{
					str += q[i][c].toBoard() + "\t" + getLineSpacing(cats[c]);//add line spacing use c
				}
			}
			str += "\n";
		}
		return str;
	}
	
	private String getLineSpacing(String str)
	{
		int l = str.length();
		String out = "";
		l = l-3;
		for(int i =0; i < l; i++)
		{
			out += " ";
		}
		
		return out;
	}
}