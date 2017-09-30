//Created By Jasson Didhi Kumala
//10/09/2017
//

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private ArrayList<Card> hand;
    private String name;
    private Scanner sc;

    Player(String nm)
    {
        hand = new ArrayList<Card>();
        name = nm;
        sc = new Scanner(System.in);
    }

    public String getName() {
        return name;
    }

    public Card getCard(int x)
    {
        return hand.get(x);
    }

    public void drawCard(Card card)
    {
        hand.add(card);
    }

    public void getTurn(Table table)
    {
        int cardNum;
        boolean next = false;
        String lastCardDesc;                             //Defining all the variable to be used later
        if(table.anyCardPlayed() && !(table.playerGetTurnAgain(this))) {        //Conditioning to see if hedidn't get his turn again twice while in game
            if(table.getLastCard() instanceof TrumpCard){                      //COnditioning for previous card
                lastCardDesc =
                        "\nLast card = Name: " + table.getLastCard().getName() + "   " +
                                "Hardness: " + ((TrumpCard) table.getLastCard()).getHardness() + "   " +
                                "Specific Gravity: " + ((TrumpCard) table.getLastCard()).getGravity() + "   " +
                                "Cleavage: " + ((TrumpCard) table.getLastCard()).getCleavage() + "   " +
                                "Crustal Abundance: " + ((TrumpCard) table.getLastCard()).getCrustal() + "   " +
                                "Economic Value: " + ((TrumpCard) table.getLastCard()).getEco() + "\n";}          //Description for normal card if used previously
            else{
                lastCardDesc = "\nPrevious player play the " +((STCard) table.getLastCard()).getName()+ " card\n";
                //Description for supertrump card if used previously
            }
        }
        else if(table.anyCardPlayed() && table.playerGetTurnAgain(this)) {  //Conditioning to see if anyone except if have passed
            System.out.println("You are granted to choose the trump mode again");
            lastCardDesc = "You may pick again as you made them all pass";
            startGame(table);               //Allowing the player to pick the trump mode again
        }
        else                    //Conditioning if it is the start of the game
        {
            lastCardDesc = "No cards have been played";
            startGame(table);               //Allowing the first player to pick the mode
        }
        while (!next) {             //Looping till the person did action that allow the next player to move
            System.out.println(getName() + " Enter the card number");
            System.out.println(table.getGameMode());
            System.out.println(lastCardDesc);
            System.out.println(showAllCard());
            System.out.println("Simply enter PASS to pass");
            String choice = sc.next();
            if (choice.toUpperCase().equals("PASS")) {
                drawCard(table.getDeckCard().drawCard());
                next = true;                        //Draw card if the user pass
            }
            else {
                try {
                    cardNum = Integer.parseInt(choice);             //Changing the input into int
                    Card cardplayed = getCard(cardNum);             //Trying to get the card inputted
                    boolean gameContinue = table.playCard(cardplayed,this);         //Trying to put/play the card into the table
                    if(table.getCardMode().equals("NEW"))           //Decision if a special supertrump card that allow player to pick the trump mode is played
                    {   table.putCard(cardplayed);
                        hand.remove((cardNum));
                        startGame(table);                               //Repick the trump mode
                        table.setLastPlayerTurn(this.getName());
                        next = true;
                    }
                    else if(table.getCardMode().equals("SS"))     //Check if it is SS category
                    {
                        if(checkWinningCard())                          //Check if the player got the winning card
                        {
                            for(Card cardInHand : hand)
                            {
                                table.putCard(cardInHand);
                                hand.remove(cardInHand);
                                table.setLastPlayerTurn(this.getName());
                            }
                            table.setCardMode("S");                 //Return the mode back to Specific gravity
                            next = true;
                        }
                        else
                        {table.setCardMode("S");}                   //Return the mode back to Specific gravity
                        if(gameContinue) {                              //For playing the card
                            table.putCard(cardplayed);
                            hand.remove(cardNum);
                            table.setLastPlayerTurn(this.getName());
                            next = true;
                        }
                    }
                    else {                                              //For normal card play(without the supertrump) if the user enter the correct card
                        if (gameContinue) {
                            table.putCard(cardplayed);
                            hand.remove(cardNum);
                            table.setLastPlayerTurn(this.getName());
                            next = true;
                        }
                    }
                } catch (Throwable e) {                                 //For any error might occur such as index error
                    System.out.println(e.getMessage());
                }
            }
        }


    }

    public String showAllCard()
    {
        String allHand = "";
        int cardNo = 0;
        for(Card cards : hand)
        {
            String cardDesc = "";
            if(cards instanceof TrumpCard)         //For normal card
            {   cardDesc = String.format("No: %2d Name: %-17s Hardness: %5.2f Specific Gravity: %5.2f Cleavage: %-17s " +
                            "Crustal Abundance: %-11s Economic Value: %-10s \n",cardNo,cards.getName(),
                    ((TrumpCard) cards).getHardness(),((TrumpCard) cards).getGravity(),((TrumpCard) cards).getCleavage()
                    ,((TrumpCard) cards).getCrustal(),((TrumpCard) cards).getEco());
            }
            else                                //For supertrump
            {
                cardDesc = String.format("No: %2d Name: %-17s Description: %s \n",cardNo,cards.getName(),
                        ((STCard) cards).cardDesc());
            }
            cardNo+=1;
            allHand += cardDesc; //Concatenating all the card description
        }
        return allHand;
    }

    public void startGame(Table gameStarter)
    {
        System.out.println(getName());
        System.out.println("Enter the mode you want to play");
        System.out.println("H = Hardness, S = Specific Gravity," +
                " C = Cleavage, A = Crustal Abundance, E= Economic Value");
        System.out.println("YOUR CARD LIST");
        System.out.println(showAllCard());
        String mode = sc.next();
        while(!(mode.equals("H") ||mode.equals("S")||mode.equals("C")||mode.equals("A")||mode.equals("E")||
                mode.equals("h") ||mode.equals("s")||mode.equals("c")||mode.equals("a")||mode.equals("e")) )    //To error check
        {
            System.out.println("Invalid mode");
            System.out.println("Enter the mode you want to play");
            System.out.println("H = Hardness, S = Specific Gravity," +
                    " C = Cleavage, A = Crustal Abundance, E= Economic Value");
            System.out.println("YOUR CARD LIST");
            System.out.println(showAllCard());
            mode = sc.next();

        }
        gameStarter.setCardMode(mode.toUpperCase());
    }

    public boolean checkWinningCard()
    {
        boolean winningCard = false;
        for(Card cards: hand)
        {
            if(cards.getName().equals("Magnetite"))     //If the player got magnetite he will win
            {
                winningCard = true;
            }
        }
        return winningCard;
    }
    public void leaveGame(Table table)
    {
        table.getPlayers().remove(this);
        System.out.println("Player " + this.getName() + " have finished the game");

    }

    public boolean hasFinished(){
        boolean checker = false;
        if(hand.size() == 0){
            checker = true;
        }
        return checker;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }
}


