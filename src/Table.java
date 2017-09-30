//Created By Jasson Didhi Kumala
//10/09/2017
//

import java.util.ArrayList;         //Import the necessary package
import java.util.Scanner;

public class Table {
    private ArrayList<Card> usedCard;
    private Deck deckCard;
    private ArrayList<Player> gamePlayers;
    private String cardMode;
    private String lastPlayerTurn;

    Table(Deck deckcard)
    {
        cardMode = "";
        usedCard = new ArrayList<Card>();
        gamePlayers = new ArrayList<Player>();
        deckCard = deckcard;
        lastPlayerTurn = "";
        /*Scanner sc = new Scanner(System.in);
        for(int x = 0; x < numberOfPlayers; x++)
        {
            System.out.println("Enter player " + (x+1) + " name");
            String nameOfPlayer = sc.next();
            gamePlayers.add(new Player(nameOfPlayer));              //Asking the player name and add the to the table as an arraylist
        }
                */                                           //Sharing the card to all the player
    }

    public void shareCard(){
        for(int x = 0; x<8; x++) {
            for (Player player : gamePlayers) {
                player.drawCard(deckCard.drawCard());
            }
        }
    }
    public String getCardMode() {                   //To get the trump mode
        return cardMode;
    }

    public void addPlayer(String nm) {
        gamePlayers.add(new Player(nm));
    }


    public String getGameMode()
    {
        String game = "";
        if(cardMode.equals("H"))
        {
            game = "It is a game of hardness";
        }
        else if(cardMode.equals("S"))
        {
            game = "It is a game of specific gravity";
        }
        else if(cardMode.equals("C"))
        {
            game = "It is a game of cleavage";
        }
        else if(cardMode.equals("A"))
        {
            game = "It is a game of crustal abundance";
        }
        else if(cardMode.equals("E")) {
            game = "It is a game of economic value";
        }
        return game;
    }

    public void setCardMode(String cardMode) {
        this.cardMode = cardMode;
    }



    public void addBackCard()
    {
        ArrayList<Card> reStore = new ArrayList<Card>();
        for(Card cards: usedCard)
        {
            reStore.add(cards);
        }
        setDeckCard(new Deck(reStore));
        usedCard.clear();
        usedCard.add(reStore.get(reStore.size()-1));

    }
    public ArrayList<Player> getPlayers() {
        return gamePlayers;
    }

    public Card getLastCard() {         //Method to get the last card played
        return usedCard.get(usedCard.size()-1);
    }

    public boolean anyCardPlayed()
    {
        boolean played = false;
        if(usedCard.size()>0)
        {played = true;}
        return played;
    }

    public Deck getDeckCard() {
        return deckCard;
    }


    public boolean playCard(Card card, Player play)     //Method to play the card to see whether it is allowed or not
    {
        boolean isHigher = false;
        int comparison = 0;
        if(usedCard.size()==0 || this.playerGetTurnAgain(play))     //Decision if it is the start or the player get to play again
        {
            if(card  instanceof STCard)
            {
                cardMode = ((STCard) card).function();
            }
            isHigher = true;
        }
        else {
            if(card instanceof TrumpCard) {            //Check if the card played is normal card
                if (getLastCard() instanceof TrumpCard) {          //Check if the previous card is a normal card
                    if (cardMode.equals("H")) {
                        Float current = new Float(((TrumpCard) card).getHardness());
                        Float previous = new Float(((TrumpCard) getLastCard()).getHardness());
                        comparison = current.compareTo(previous);
                    } else if (cardMode.equals("S")) {
                        Float current = new Float(((TrumpCard) card).getGravity());
                        Float previous = new Float(((TrumpCard) getLastCard()).getGravity());
                        comparison = current.compareTo(previous);
                    } else if (cardMode.equals("C")) {
                        Float current = new Float(((TrumpCard) card).getCleavageInt());
                        Float previous = new Float(((TrumpCard) getLastCard()).getCleavageInt());
                        comparison = current.compareTo(previous);
                    } else if (cardMode.equals("A")) {
                        Float current = new Float(((TrumpCard) card).getCrustalInt());
                        Float previous = new Float(((TrumpCard) getLastCard()).getCrustalInt());
                        comparison = current.compareTo(previous);
                    } else if (cardMode.equals("E")) {
                        Float current = new Float(((TrumpCard) card).getEcoInt());
                        Float previous = new Float(((TrumpCard) getLastCard()).getEcoInt());
                        comparison = current.compareTo(previous);
                    }               //Comparing the value according to the trump category
                    if (comparison > 0) {
                        isHigher = true;
                    }       //Allow to continue if the value is higher
                    else
                    {
                        System.out.println("Invalid selection, your choice don't have enough value to fight the last card");
                    }   //Show error message
                } else {        //If user played supertrump card previously
                    isHigher = true;        //Just allow
                }
            }
            else
            {           //If user play supertrump card now
                setCardMode(((STCard) card).function());       //Change the category accordingly
                isHigher = true;
            }
        }
        return isHigher;
    }

    public void putCard(Card card)
    {
        usedCard.add(card);
    }           //Method to put card to the table

    public String getLastPlayerTurn() {                 //Method to get the last player name
        return lastPlayerTurn;
    }

    public void setLastPlayerTurn(String lastPlayerTurn) {      //Method to set the last player who didn't pass
        this.lastPlayerTurn = lastPlayerTurn;
    }

    public boolean playerGetTurnAgain(Player gamePlayer)        //Method to check if all except him pass
    {
        boolean statement = false;
        if(getLastPlayerTurn().equals(gamePlayer.getName()))
        {
            statement = true;
        }
        return statement;
    }

    public void setDeckCard(Deck deckCard) {                    //Method to set the deckcard again
        this.deckCard = deckCard;
    }

    public ArrayList<Card> getUsedCard() {
        return usedCard;
    }
}
