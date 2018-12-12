package jeopardyLab;

import java.util.Random;

/**
 * @author Akash Chandra & William Spencer
 * Teacher Miss Denna PER 7
 *
 * Created Date: 11-19-2018
 */
public class Question {

	private String question, correctAns, ansD1,ansD2, ansD3;
	private int value, correctInt;
	private String[] ansArray;
	private boolean isUsed;
	
	public Question()
	{
		this.isUsed = true;
	}
	
	public Question(String question, String correctAnsa, String ansD1a, String ansD2a, String ansD3a, int value) 
	{
		this.question = question;
		this.correctAns = correctAnsa;
		this.ansD1 = ansD1a;
		this.ansD2 = ansD2a;
		this.ansD3 = ansD3a;
		this.value = value;
		this.ansArray = this.randomize(correctAns, ansD1, ansD2, ansD3);
		this.isUsed = false;
	}
		
	public String checkAnswerOld(int ans, Player p)
	{
		for(int i = 0; i < ansArray.length; i++)
		{
			if(ansArray[ans].equals(ansArray[i]) || ans == correctInt)
			{
				System.out.println(ansArray[ans] + "\t" + ansArray[i]);
				p.add(value);
				return p.getName() + "has won $" + value + " and now has $" + p.getMoney();
			}
		}
		
		p.add(-1*value);
		return p.getName() + "has lost $" + value + " and now has $" + p.getMoney();
	}
	
	public String checkAnswer(int ans, Player p)
	{
		if(ans == this.correctInt)
		{
			p.add(value);
			return p.getName() + " has won $" + value + " and now has $" + p.getMoney();
		}
		else
		{
			p.add(-1*value);
			return p.getName() + " has lost $" + value + " and now has $" + p.getMoney();
		}
	}
	
	private String[] randomize(String correctAnsa, String ansD1a, String ansD2a, String ansD3a)
	{
		Random rand=new Random();
        int play = rand.nextInt(4);
		
        String[] ansArraya=new String[4];
		
        ansArraya[play] = correctAnsa;
        this.correctInt = play;
        
		while (ansArraya[play] != null){
		    play = rand.nextInt(4);
		}
		
		ansArraya[play] = ansD1a;
		while (ansArraya[play]!=null){
    		play = rand.nextInt(4);
		}
		
		ansArraya[play] = ansD2a;
		while (ansArraya[play]!=null){
		    play = rand.nextInt(4);
		}
		
		ansArraya[play] = ansD3a;
		return ansArraya;
	}
	
	public String toString()
	{
		if (question == null)
			return "";
		String out = "Value: $" + value  + "\n" + question + "\n\n";
		for(int i = 0; i < ansArray.length;i++)
		{
			out+= i+1 + ") " + ansArray[i] + "\n";
		}
		
		return out;
	}
	
	public String toBoard()
	{
		if(!(isUsed))
		{
			return "$" + value;
		}
		else
		{
			return "N.A.";
		}
	}
	
	public void used()
	{
		this.isUsed = true;
	}

	//Pre-generated Methods
	
	/**
	 * @return the isUsed
	 */
	public boolean isUsed() {
		if (question == null)
			return true;
		else
			return isUsed;
	}
	
	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @return the correctAns
	 */
	public String getCorrectAns() {
		return correctAns;
	}
}
