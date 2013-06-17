import java.util.Scanner;
import java.util.ArrayList;

public class GameManagement 
{
	ArrayList<Player> playerArray = new ArrayList<Player>();
	static Player currentPlayer;

	public void GameSetup()
	{	
		System.out.println("How many players are there?");
		
		String enteredNumber;
		Scanner scan = new Scanner(System.in);
		enteredNumber = scan.nextLine();
		int numberOfPlayers = Integer.parseInt(enteredNumber);
		
		System.out.println("=== Finished setting the number of players. ===\n\n");
		
		for(int i = 0; i < numberOfPlayers; i++)
		{
			System.out.println("What is player " + (i+1) + "'s name?");
			String playerName = scan.nextLine();
			
			Player newPlayer = new Player();
			newPlayer.setName(playerName);
			
			playerArray.add(newPlayer);
			
		}
		
		currentPlayer = playerArray.get(0);
		
		System.out.println("\n\n=== Finished initializing players. ===\n\n");
		
	}//GameSetup()
	
	public void ResultTest()
	{
		ArrayList<String> cardArray = new ArrayList<String>();
		cardArray.add("Knife");
		cardArray.add("Mrs. Peacock");
		cardArray.add("Billiard Room");
		Player testPlayer = (Player) playerArray.get(0);
		testPlayer.addToPlayedCards(cardArray);
		
		System.out.println("There are " + playerArray.size() + " players:\n");
		
		for(int i = 0; i < playerArray.size(); i++)
		{
			Player retreivedPlayer = (Player) playerArray.get(i);
			retreivedPlayer.getPlayedCards();
		}
		
	}//ResultTest()
	
	
	public void NextPlayer(Player cP)
	{	
		//System.out.print("Current player changed from " + currentPlayer.getName() + " to ");
		if(playerArray.indexOf(cP) == (playerArray.size() - 1))
		{
			currentPlayer = playerArray.get(0);
		}
		else
		{
			currentPlayer = playerArray.get(playerArray.indexOf(cP) + 1);
		}
		//System.out.println(currentPlayer.getName() + ".");
		
	}//NextPlayer()
	
	
	public void GameLoop()
	{	
		int indexOfCurrentPlayer;
		boolean running = true, inner;
		Scanner scan = new Scanner(System.in);
		String response = "";
		
		while(running)
		{
			//[0]	Get new suggestion for solution, suggested by the Current Player
			indexOfCurrentPlayer = playerArray.indexOf(currentPlayer);
			System.out.println(currentPlayer.getName() + " makes their suggestion.");
			inner = true;
			while(inner)
			{
				//[1] Change the selected player and get response to suggestion
				this.NextPlayer(currentPlayer);
				System.out.println("Selected player changed to " + currentPlayer.getName());
				System.out.println("Did " + currentPlayer.getName() + " Pass or Play?");
				response = scan.nextLine();
				
				//[2] 	If the selected player Passes
				if(response.equals("Pass"))
				{
					System.out.println(currentPlayer.getName() + " passed.");
					//[2a]	Remove the suggested cards from their possible cards
					System.out.println("Suggested cards removed from " + currentPlayer.getName() + "'s Possible Cards.\n");
					//[2b]	Go back to [1]
				}
				//[3]	If the selected player Plays
				else if(response.equals("Play"))
				{
					System.out.println(currentPlayer.getName() + " played.");
					//[3a]	Add the suggested cards to their Played Cards
					System.out.println("Suggested cards added to " + currentPlayer.getName() + "'s Played Cards.\n");
					//[3b]	Change the Current Player
					currentPlayer = playerArray.get(indexOfCurrentPlayer);
					this.NextPlayer(currentPlayer);
					//[3c]	Go back to [0]
					inner = false;
				}
				else if(response.equals("Quit"))
				{
					inner = false;
					running = false;
				}
				else 
				{
					System.out.println("\nUnrecognised command.\n");
					currentPlayer = playerArray.get(indexOfCurrentPlayer);
					inner = false;
				}
			}
		}
		System.out.println("\n=== Game Loop Exited ===");
		
	}//NewTurn()
	
	
	public static void main(String args[]) 
	{
		GameManagement newGame = new GameManagement();
		newGame.GameSetup();
		newGame.GameLoop();
		
	}//main()

}//GameManagement
