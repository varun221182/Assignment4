import java.util.List;
import java.util.Scanner;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
	   BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        Dice d1 = new Dice();
        Dice d2 = new Dice();
        Dice d3 = new Dice();

        Scanner scan = new Scanner(System.in);
        
        
        Player player = new Player("Fred", 100);
        Game game = new Game(d1, d2, d3);
        List<DiceValue> cdv = game.getDiceValues();

        int totalWins = 0;
        int totalLosses = 0;

        while (true)
        {
            //player name
		System.out.println("Enter the name of the player");
        String playerName = scan.next();
        //player age
		System.out.println("Enter the age of the player");
        int playerAge = scan.nextInt();
        
        //Age verfication
         if(playerAge>17)
        {
            int winCount = 0;
            int loseCount = 0;
            //accepted bet amount is 5-20
            System.out.println("Enter the bet amount,minimum is 5 and maximum is 20");
            int bet = scan.nextInt();
            //invalid bet handling
            while(bet<=0)
            {
                System.out.println("Please enter a valid bet amount");
                bet = scan.nextInt();
            }
            while(bet>21)
            {
                System.out.println("Please enter a valid bet amount");
                bet = scan.nextInt();
            }
            /*while(bet<0||bet>20)
            {
                System.out.println("Please enter a valid bet amount");
                bet = scan.nextInt();
            }*/
            
            for (int i = 0; i < 100; i++)
            {
                String ans = "null";
            	String name = playerName;
            	int balance = 100;
            	int limit = 0;
                player = new Player(name, balance);
                player.setLimit(limit);
                

                System.out.println(String.format("Start Game %d: ", i));
                System.out.println(String.format("%s starts with balance %d, limit %d", 
                		player.getName(), player.getBalance(), player.getLimit()));

                int turn = 0;
                while (player.balanceExceedsLimitBy(bet) && player.getBalance() < 200)
                {
                    turn++;                    
                	DiceValue pick = DiceValue.getRandom();
                        
                   
                	System.out.printf("Turn %d: %s bet %d on %s\n",
                			turn, player.getName(), bet, pick); 
                	
                	int winnings = game.playRound(player, pick, bet);
                    cdv = game.getDiceValues();
                    
                    System.out.printf("Rolled %s, %s, %s\n",
                    		cdv.get(0), cdv.get(1), cdv.get(2));
                    
                    if (winnings > 0) {
	                    System.out.printf("%s won %d, balance now %d\n\n",
	                    		player.getName(), winnings, player.getBalance());
	                	winCount++; 
                
                    }
                    else {
	                    System.out.printf("%s lost, balance now %d\n\n",
	                    		player.getName(), player.getBalance());
	                	loseCount++;
                
                    }
                System.out.println("Do you want to continue ?");
                System.out.println("Press q to quit, Enter to continue");
                ans = console.readLine();
                if (ans.equals("q")) break;
                // comment th ebelow code if you want to run the program witout asking the player to place the bet
                System.out.println("Enter the bet amount"); 
                bet = scan.nextInt();

                    
                } //while

                System.out.print(String.format("%d turns later.\nEnd Game %d: ", turn, i));
                System.out.println(String.format("%s now has balance %d\n", player.getName(), player.getBalance()));
                if (ans.equals("q")) break;
            } //for
        
                
            System.out.println(String.format("Win count = %d, Lose Count = %d, %.2f", winCount, loseCount, (float) winCount/(winCount+loseCount)));
            totalWins += winCount;
            totalLosses += loseCount;

            String ans = console.readLine();
            //if (ans.equals("q")) break;
        }
        else System.out.println("Children below 18 are not allowed to play");         

        System.out.println(String.format("Overall win rate = %.1f%%", (float)(totalWins * 100) / (totalWins + totalLosses)));
        }//while true
        
	}
        

}
