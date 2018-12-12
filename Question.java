 

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
    
    /**
     * This is a constructor for the Question class.  
     * It just generates a decoy question that we use to make sure players
     *  can't answer answered questions.
     * @param: none
     */
    public Question()
    {
        this.isUsed = true;
    }
    
    /**
     * This is the real constructor for the Question. 
     * It takes a question, answer, and dummy answers, as well as the 
     *  question's value.
     * 
     * @param: String question
     * @param: String correctAnsa
     * @param: String ansD1a
     * @param: String ansD2a
     * @param: String ansD3a
     * @param: int value
     */
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
        
    /**
     * This method checks to make sure that the player answered correctly.
     * It checks their inputted integer and if it's the same, it gives
     *  them money.  If it's not, it subtracts their money.
     * 
     * @param: int ans
     * @param: Player p
     * @return: a String
     */
    public String checkAnswer(int ans, Player p)
    {
        if(ans == this.correctInt || correctAns.equals(ansArray[ans]))
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
    
    /**
     * This method randomizes the answers to the questions. 
     * It then returns a String array of the answers all jumbled up.
     * 
     * @param: String correctAnsa
     * @param: String ansD1a
     * @param: String ansD2a
     * @param: String ansD3a
     * @return: a String array
     */
    private String[] randomize(String correctAnsa, String ansD1a, String ansD2a, String ansD3a)
    {
        Random rand=new Random();
        int play = rand.nextInt(4);
        
        String[] ansArraya=new String[4];
        
        ansArraya[play] = correctAnsa;
        this.correctInt = play;
        
        while (ansArraya[play] != null)
        {
            play = rand.nextInt(4);
        }
        
        ansArraya[play] = ansD1a;
        while (ansArraya[play]!=null)
        {
            play = rand.nextInt(4);
        }   
        
        ansArraya[play] = ansD2a;
        while (ansArraya[play]!=null)
        {
            play = rand.nextInt(4);
        }
        
        ansArraya[play] = ansD3a;
        return ansArraya;
    }
    
    /**
     * This is the toString for the Question class. 
     * It generates a String that has the value, actual question,
     *  and the answers to the question.
     * 
     * @return: a String
     */
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
    
    /**
     * This generates the value numbers on the board.
     * 
     * @return: a String
     */
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
    
    /**
     * This just sets the value of the current question's isUsed
     *  to true. 
     */
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
