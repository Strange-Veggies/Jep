package jeopardyLab;


import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Akash Chandra & William Spencer
 * Teacher Miss Denna PER 7
 *
 * Created Date: 11-19-2018
 */
public class JeopardyDriver {
	
	public static Player[] players;
	public static Board game;
	public static Scanner keyboard;
	public static int numOfPlayers, x, y;
	public static boolean continuePlaying = true;
	public static Question playing, blank;
	public static boolean usedBoard = false;
	public static int used = 0;
	
	public static void main(String[] args) throws FileNotFoundException {
		keyboard = new Scanner(System.in);
    	
		try
		{
			game = new Board("Questions.txt");
		}
		
		catch (FileNotFoundException e)
		{
			continuePlaying = false;
			System.out.println("Question file not found. Please find it and put it in the right place.");
		}
		
		
		blank = new Question();
		
		if(continuePlaying == true)
		{
			rules();
			userInput();
		}
		
		do
		{
			System.out.println(game);
			
			getCords();
						
			if(x == -1 && y ==-1)
			{
				continuePlaying = false;
			}
			
						
			else if(!(x == -1 && y ==-1) && continuePlaying)
			{
				playing = game.getQuestion(y, x);
			}
			
			else
			{
				continuePlaying = false;
			}
			
			x = 0;
			y = 0;
	
			if((playing == null || playing.isUsed()) && continuePlaying && usedBoard == false)
			{
				System.out.println("Choose a diffrent question.");
			}
			
			else if(continuePlaying && playing.isUsed() != true && playing.isUsed() == false)
			{
				System.out.print("\n" + playing);
				getAns();
				playing.used();
				System.out.println("Correct Answer is: " + playing.getCorrectAns());
				scoreboard();
				playing = blank;
				used++;
			}
											
			if(usedBoard || used >= game.total)
			{
				System.out.println("The Board Has Been Used!!!");
				continuePlaying = false;
			}
			
		}
		while(continuePlaying);
		System.out.println("End Of Game:");
		scoreboard();
		
		Player winner = findHighestValue();
		System.out.println("The person with the highest value is " + winner.getName() + " with $" + winner.getMoney());
	}
	
	/**
	Gets the cordinates from the user and and deos the error checking
	@param void
	@return void
	*/
	public static void getCords()
	{
		Random ran = new Random();
		int indexRan =  ran.nextInt(players.length);
		System.out.println(players[indexRan] + " choose a question.\n\n");
		
		System.out.println("Enter the X cordinate of the question:");
		int tx = getInt();
		System.out.println("Enter the Y cordinate of the question:");
		int ty = getInt();
		if(tx > game.getRow()-1 && tx < -1)
		{
			while(tx > game.getRow()-1)
			{
				System.out.println("Enter the X cordinate of the question, between the values 0 and " + (game.getRow()-1) + ":");
				tx = getInt();
			}	
		}
		
		if(ty > game.getColumn()-1 && ty < -1)
		{
			while(ty > game.getColumn()-1)
			{
				System.out.println("Enter the Y cordinate of the question, between the values 0 and " + (game.getColumn()-1) + ":");
				ty = getInt();
			}	
		}
		
		x = tx;
		y = ty;
	}
	
	/**
	Prints the scoreboard of the current players 
	@param void
	@return void
	*/
	public static void scoreboard()
	{
		String print = "\nScoreboard-\nName\tAmount of money:\n";
		for(int i = 0; i < numOfPlayers; i++)
		{
			print += players[i].toString() + "\n";
		}
		System.out.println(print);
	}
	
	/**
	Gets the answers from the user and and checks the answers, and 
	@param void
	@return void
	*/
	public static void getAns()
    	{	
		String out ="";
		for(int i = 0; i < numOfPlayers; i++)
		{
			System.out.println("\n" + players[i].getName() + ", your turn. Put in an answer:");
			int ans = getInt();
			ans--;
			out +=  "\n" + playing.checkAnswer(ans, players[i]);
		}
		System.out.println(out);
    }
	/**
	Gets the names of the players
	@param void
	@return void
	*/
	public static void userInput()
    {
    	System.out.println("Number of Players");
		numOfPlayers = getInt();
		
		players = new Player[numOfPlayers];
		
		for(int i = 0; i < numOfPlayers; i++)
		{
			System.out.println("\nName of Player " + (i + 1) + ":");
			String n = keyboard.nextLine();
			players[i] = new Player(n);
		}
		
    }
	/**
	Prints the rules
	@param void
	@return void
	*/
	public static void rules()
    {
		header();
		System.out.println("Hit -1 enter key -1 enter key to quit");
		System.out.println("Enter the cordinates of the question you want to play enter question: X enter key Y enter key.");
    }
	/**
	Prints a header
	@param void
	@return void
	*/
	public static void header()
	{
		System.out.println("\f");
		System.out.println("    /$$$$$                                                         /$$          \r\n" + 
				"   |__  $$                                                        | $$          \r\n" + 
				"      | $$  /$$$$$$   /$$$$$$   /$$$$$$   /$$$$$$   /$$$$$$   /$$$$$$$ /$$   /$$\r\n" + 
				"      | $$ /$$__  $$ /$$__  $$ /$$__  $$ |____  $$ /$$__  $$ /$$__  $$| $$  | $$\r\n" + 
				" /$$  | $$| $$$$$$$$| $$  \\ $$| $$  \\ $$  /$$$$$$$| $$  \\__/| $$  | $$| $$  | $$\r\n" + 
				"| $$  | $$| $$_____/| $$  | $$| $$  | $$ /$$__  $$| $$      | $$  | $$| $$  | $$\r\n" + 
				"|  $$$$$$/|  $$$$$$$|  $$$$$$/| $$$$$$$/|  $$$$$$$| $$      |  $$$$$$$|  $$$$$$$\r\n" + 
				" \\______/  \\_______/ \\______/ | $$____/  \\_______/|__/       \\_______/ \\____  $$\r\n" + 
				"                              | $$                                     /$$  | $$\r\n" + 
				"                              | $$                                    |  $$$$$$/\r\n" + 
				"                              |__/                                     \\______/ ");
		System.out.println();
	}
	
	/**
	Finds the player with the most money
	@param void
	@return void
	*/
	public static Player findHighestValue()
	{
		Player winner = null;
		int max = players[0].getMoney();
		
		for(int i = 0; i < players.length; i++) 
		{
			if(max < players[i].getMoney())
			{
				winner = players[i];
			}
		}
		
		return winner;
	}
	
	/**
	 * Reads an int from the console and does the error checking
	 * @param void
	 * @return int
	 */
	public static int getInt()
	{
		int i = -1776;
		while(i == -1776)
		{
			try 
			{
				i = keyboard.nextInt();
				keyboard.nextLine();
			}
			
			catch (Exception e)
			{
				System.out.println();
				System.out.println("Please put a valid integer, not anything else: ");			
				keyboard.nextLine();
			}
		}	
		return i;
	}
}
