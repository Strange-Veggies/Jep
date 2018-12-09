package jeopardyLab;


import java.io.FileNotFoundException;
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
	
	public static void main(String[] args) throws FileNotFoundException {
		keyboard = new Scanner(System.in);
    	
		game = new Board("Questions.txt");
		blank = new Question();
		
		rules();
		
		userInput();
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
				System.out.println("Question: " + playing.getQuestion());
			}
			
			else
			{
				continuePlaying = false;
			}
			
			x = 0;
			y = 0;
	
			if(continuePlaying && playing.isUsed() != true && playing.isUsed() == false)
			{
				System.out.println(playing);
				getAns();
				playing.used();
				scoreboard();
				playing = blank;
			}
			
			System.out.println();
			
			if((playing == null || playing.isUsed()) && continuePlaying)
			{
				System.out.println("Choose a diffrent question.");
			}
			
			if(game.usedBoard)
			{
				continuePlaying = false;
			}
			
		}
		while(continuePlaying);
		scoreboard();
	}
	
	public static void getCords()
	{
		System.out.println("Enter the X cordinate of the question:");
		int tx = getInt();
		System.out.println("Enter the Y cordinate of the question:");
		int ty = getInt();
		if(tx > game.getRow()-1)
		{
			while(tx > game.getRow()-1)
			{
				System.out.println("Enter the X cordinate of the question, between the values 0 and " + (game.getRow()-1) + ":");
				tx = getInt();
			}	
		}
		
		else if(ty > game.getColumn()-1)
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
	
	public static void scoreboard()
	{
		String print = "\nScoreboard-\nName\tAmount of money:\n";
		for(int i = 0; i < numOfPlayers; i++)
		{
			print += players[i].toString() + "\n";
		}
		System.out.println(print);
	}
	
	public static void getAns()
    {		
		for(int i = 0; i < numOfPlayers; i++)
		{
			System.out.println(players[i].getName() + ", your turn. Put in an answer:");
			int ans = getInt();
			ans--;
			System.out.println(playing.checkAnswer(ans, players[i]));
		}
		
    }
	
	public static void userInput()
    {
    	System.out.println("Number of Players");
		numOfPlayers = getInt();
		
		players = new Player[numOfPlayers];
		
		for(int i = 0; i < numOfPlayers; i++)
		{
			System.out.println("\nName of Player " + 1 + ":");
			String n = keyboard.nextLine();
			players[i] = new Player(n);
		}
		
    }
	
	public static void rules()
    {
		header();
		System.out.println("Hit -1 enter key -1 enter key to quit");
		System.out.println("Enter the cordinates of the question you want to play enter question: X enter key Y enter key.");
    }
	
	public static void header()
	{
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
