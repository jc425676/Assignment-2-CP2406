//Created By Jasson Didhi Kumala
//10/09/2017
//

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
/*
public class MainA1 {
    private Deck deck;
    private int numberOfPlayer;
    public static void main(String[] args)
    {
        MainA1 program = new MainA1();
        program.run();
    }

    public void run(){
        playerInput();
        deckInitializer();
        playGame();
    }

    public void playerInput (){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of players (3-5)");
        System.out.println(">>>");
        int numberOfPlayerInput = sc.nextInt();
        while (numberOfPlayerInput <3 || numberOfPlayerInput > 5) {
            System.out.println("Wrong number of players!!");
            System.out.println("Enter the number of players (3-5)");
            System.out.println(">>>");
            numberOfPlayerInput = sc.nextInt();
        }
        numberOfPlayer = numberOfPlayerInput;
    }

    public void deckInitializer(){
        ArrayList<Card> cardList = new ArrayList<Card>();
        String[] array;
        String string = "";                                         //Defining variable
        Path file =
                Paths.get("D:\\JCU IT\\Programming 2\\New folder\\Assignment 1 CP2406 Final\\src\\card.txt");     //Determining path for reading
        try
        {
            InputStream input = new BufferedInputStream(Files.newInputStream(file));
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            reader.readLine();                                                              //Reading the file
            while ((string = reader.readLine()) != null){
                array = string.split(",");
                cardList.add(new TrumpCard(array[0],Float.valueOf(array[1]),Float.valueOf(array[2]),array[3],array[4],array[5])); //Making and adding of card
            }
            cardList.add(new STCard("The Mineralogist","Change trump category to cleavage"));
            cardList.add(new STCard("The Geologist","Change trump category to your choice"));
            cardList.add(new STCard("The Geophysicist","Change trump category to specific gravity or throw magnetite"));
            cardList.add(new STCard("The Petrologist","Change trump category to crustal abundance"));
            cardList.add(new STCard("The Miner","Change trump category to economic value"));
            cardList.add(new STCard("The Gemmologist","Change trump category to hardness"));//Making and adding all the supertrump card
        }
        catch(Exception e)
        {
            System.out.println("Message: " + e.getMessage());           //To show error message
        }
        deck = new Deck(cardList);                             //Making the deck
    }

    public void playGame(){
        Table gameTable = new Table(numberOfPlayer,deck);   //Starting the game with the number fo player and the deck
        int counter = 0;
        while (gameTable.getPlayers().size()>1)                     //Looping until 1 player left
        {
            if(gameTable.getPlayers().size()==counter)
            {
                counter = 0;
            }
            if(gameTable.getDeckCard().getCards().size()==0)
            {
                gameTable.addBackCard();                                //If no more card to be drawn, put back the used card into the deck
            }
            else{
                gameTable.getPlayers().get(counter).getTurn(gameTable);    //Player using their turn
                if(gameTable.getPlayers().get(counter).hasFinished()){
                    gameTable.setLastPlayerTurn(gameTable.getPlayers().get(counter+1).getName());
                    gameTable.getPlayers().get(counter).leaveGame(gameTable);           //They leave the game
                }
                else {
                    counter+=1;
                }
            }

        }
        System.out.println("The game is over, "+ gameTable.getPlayers().get(0).getName() + " lost");
    }
}
*/