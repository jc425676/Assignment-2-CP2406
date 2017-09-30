import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MainA2 extends JFrame implements ActionListener{
    JLabel text = new JLabel("Pick Number of player");
    JPanel panel = new JPanel();
    JPanel screen = new JPanel();
    JButton numPlayer3 = new JButton("3");
    JButton numPlayer4 = new JButton("4");
    JButton numPlayer5 = new JButton("5");
    JTextField playerName = new JTextField(30);
    JButton enter = new JButton("Enter");
    JPanel layout = new JPanel(new FlowLayout());
    JPanel cards = new JPanel(new GridLayout(2,8));
    ImageIcon deckCover = new ImageIcon("images\\slide65.jpg");
    JButton deck = new JButton(new ImageIcon(deckCover.getImage().getScaledInstance(90,100,Image.SCALE_SMOOTH)));
    JLabel lastCard = new JLabel("No Card chosen yet");
    JButton hardness = new JButton("Hardness");
    JButton gravity = new JButton("Specific Gravity");
    JButton cleavage = new JButton("Cleavage");
    JButton crustal = new JButton("Crustal Abundance");
    JButton eco = new JButton("Economic Value");
    ArrayList<JButton> buttons = new ArrayList<>();
    boolean pick = false;
    int playerTurn = 0;
    int count = 1;
    int numPlayer;
    Table game;
    ArrayList<String> listPlayer;
    Deck deckGame;
    int pass=0;
    ArrayList<String> winner;

    public MainA2(){
        super("Mineral Supertrump");
        ArrayList<Card> cardList = new ArrayList<Card>();
        winner = new ArrayList<String>();
        listPlayer = new ArrayList<String>();
        setLayout(new BorderLayout());
        screen.setLayout(new BoxLayout(screen,BoxLayout.Y_AXIS));
        cards.setMaximumSize(new Dimension(1920,720));
        add(panel,BorderLayout.CENTER);
        add(text,BorderLayout.NORTH);
        text.setHorizontalAlignment(JLabel.CENTER);
        enter.addActionListener(this);
        deck.addActionListener(this);
        hardness.addActionListener(this);
        gravity.addActionListener(this);
        cleavage.addActionListener(this);
        crustal.addActionListener(this);
        eco.addActionListener(this);
        setSize(1280,1020);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //reads the .txt file for card description and name
        String[] array;
        String string = "";
        Path file =
                Paths.get("D:\\JCU IT\\Programming 2\\Assignment 2 CP2406\\src\\card.txt");
        try
        {
            InputStream input = new BufferedInputStream(Files.newInputStream(file));
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            reader.readLine();
            int y=1;
            while ((string = reader.readLine()) != null){
                array = string.split(",");
                ImageIcon img = new ImageIcon("images\\slide"+(y)+".jpg");//inserting the card image
                //creating the trumpcard with images
                cardList.add(new TCardPict(array[0],Float.valueOf(array[1]),Float.valueOf(array[2]),array[3],array[4],array[5],new ImageIcon(img.getImage().getScaledInstance(200,300,Image.SCALE_SMOOTH)) ));
                y++;
            }
            //creating the supertrump card with images
            ImageIcon img1 = new ImageIcon("images\\Slide58.jpg");
            cardList.add(new STCardPict("The Mineralogist", "Change trump category to cleavage",new ImageIcon(img1.getImage().getScaledInstance(200,300,Image.SCALE_SMOOTH))));
            ImageIcon img2 = new ImageIcon("images\\Slide60.jpg");
            cardList.add(new STCardPict("The Geologist", "Change trump category to your choice",new ImageIcon(img2.getImage().getScaledInstance(200,300,Image.SCALE_SMOOTH))));
            ImageIcon img3 = new ImageIcon("images\\Slide59.jpg");
            cardList.add(new STCardPict("The Geophysicist","Change trump category to specific gravity or throw magnetite",new ImageIcon(img3.getImage().getScaledInstance(200,300,Image.SCALE_SMOOTH))));
            ImageIcon img4 = new ImageIcon("images\\Slide56.jpg");
            cardList.add(new STCardPict("The Petrologist","Change trump category to crustal abundance",new ImageIcon(img4.getImage().getScaledInstance(200,300,Image.SCALE_SMOOTH))));
            ImageIcon img5 = new ImageIcon("images\\Slide55.jpg");
            cardList.add(new STCardPict("The Miner","Change trump category to economic value",new ImageIcon(img5.getImage().getScaledInstance(200,300,Image.SCALE_SMOOTH))));
            ImageIcon img6 = new ImageIcon("images\\Slide57.jpg");
            cardList.add(new STCardPict("The Gemmologist","Change trump category to hardness",new ImageIcon(img6.getImage().getScaledInstance(200,300,Image.SCALE_SMOOTH))));
        }
        catch(Exception e)
        {
            text.setText("Error, please reopen the application");
        }
        //put cards into deck
        deckGame = new Deck(cardList);
        selectPlayer();  //call player number menu
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) //for all the menu button action event
    {
        if(e.getSource()==numPlayer3)  //if 3 players were chosen
        {
            numPlayer = 3;
            panel.removeAll();
            enterPlayerName();      //proceed to enter player name
        }
        else if(e.getSource()==numPlayer4)  //If 4 players were chosen
        {
            numPlayer = 4;
            panel.removeAll();
            enterPlayerName();      //proceed to enter player name
        }
        else if(e.getSource()==numPlayer5) {    //if 5 players were chosen
            numPlayer = 5;
            panel.removeAll();
            enterPlayerName();      //proceed to enter player name
        }
        else if(e.getSource()==enter)
        {
            if(count<numPlayer)
            {
                String name = playerName.getText();
                if (!(name.equals(""))){
                    listPlayer.add(name);      //add the player
                    game.addPlayer(name);
                    count+=1;
                    text.setText("Enter player "+count+" name");
                    playerName.setText("");
                }

            }
            else
            {
                String name = playerName.getText();
                if (!(name.equals(""))){
                    listPlayer.add(name);      //add the player
                    game.addPlayer(name);
                    game.shareCard();
                    pickMode();            //start trump mode selection
                }

            }
        }
        if(e.getSource()==hardness)    //for hardness mode
        {
            game.setCardMode("H");     //set trump mode to hardness
            pick = true;
            pass = 0;
            playCard();                         //enter the game to enter the card
        }
        else if(e.getSource()== gravity)
        {
            game.setCardMode("S");
            pick = true;
            pass = 0;
            playCard();
        }
        else if(e.getSource()==cleavage)
        {
            game.setCardMode("C");
            pick = true;
            pass = 0;
            playCard();
        }
        else if(e.getSource()== crustal)
        {
            game.setCardMode("A");
            pick = true;
            pass = 0;
            playCard();
        }
        else if(e.getSource()== eco)
        {
            game.setCardMode("E");
            pick = true;
            pass = 0;
            playCard();
        }
        else if(e.getSource()==deck)        //when the deck is pressed
        {
            if (game.getUsedCard().size()==0 || pick)
            {text.setText("You cannot pass, this is the first turn or you have picked the mode so you need to play a card");}  //when player had just picked the mode, player needs to enter card
            else {
                game.getPlayers().get(playerTurn % (game.getPlayers().size())).drawCard(game.getDeckCard().drawCard());
                playerTurn++;
                pass++;
                playCard();
                //adding the pass count and draw the card for the respective player, then go to next player
            }
            if(game.getDeckCard().getCards().size()==0)
            {
                game.addBackCard();        //if there are no more card left on the deck, add it back
            }
        }

        revalidate();
        repaint();          //To reload the GUI
    }

    public static void main(String[] args) {

        MainA2 app = new MainA2();  //runs the main program
    }

    public void selectPlayer()
    {
        //adding all the action listener and the buttons to the main screen
        numPlayer3.addActionListener(this);
        numPlayer4.addActionListener(this);
        numPlayer5.addActionListener(this);
        panel.add(numPlayer3);
        panel.add(numPlayer4);
        panel.add(numPlayer5);
    }

    public void pickMode()
    {   //to pick the mode
        text.setText(game.getPlayers().get(playerTurn%game.getPlayers().size()).getName()+", choose the Trump mode to be played");
        //remove previous panels and add trump mode
        panel.removeAll();
        screen.removeAll();
        layout.removeAll();
        cards.removeAll();
        panel.add(screen);
        screen.add(layout);
        screen.add(cards);
        layout.add(hardness);
        layout.add(gravity);
        layout.add(cleavage);
        layout.add(crustal);
        layout.add(eco);
        int turnNumPlayer = playerTurn%(game.getPlayers().size());
        for(int x = 0; x< game.getPlayers().get(turnNumPlayer).getHand().size();x++)
        {
            if(game.getPlayers().get(turnNumPlayer).getCard(x) instanceof TrumpCard)
            {
                JButton pressCard = new JButton(new ImageIcon(((TCardPict) game.getPlayers().get(turnNumPlayer).getCard(x)).getCardImage().getImage()));
                pressCard.setSize(150,200);
                cards.add(pressCard);

            }
            else if(game.getPlayers().get(turnNumPlayer).getCard(x) instanceof STCard)
            {
                JButton cardbtn = new JButton(new ImageIcon(((STCardPict) game.getPlayers().get(turnNumPlayer).getCard(x)).getCardImage().getImage()));
                cardbtn.setSize(150,200);
                cards.add(cardbtn);
            }
        }


    }

    public void enterPlayerName()
    {
        //to enter the player name
        game = new Table(deckGame);
        text.setText("Enter player "+count+" name");
        panel.removeAll();
        panel.add(playerName);
        panel.add(enter);
        while (text.getText().equals("")){
            text.setText("Enter player "+count+" name");
            panel.removeAll();
            panel.add(playerName);
            panel.add(enter);

        }
    }

    public void playCard()
    {
        //for the player to play accordingly
        if(game.getPlayers().get(playerTurn%game.getPlayers().size()).getName().equals(game.getLastPlayerTurn())&& pass == game.getPlayers().size()-1)
        {
            pickMode();
        }
        else {
            int turnNumPlayer = playerTurn % (game.getPlayers().size());
            text.setText(game.getPlayers().get(turnNumPlayer).getName()+ "'s turn " + ", click on the card to be played. " +
                    "Current Trump Mode = " + game.getGameMode() + " or click the deck to pass. ");
            //removing and adding all the component for the GUI
            panel.removeAll();
            layout.removeAll();
            cards.removeAll();
            buttons.clear();
            screen.add(layout);
            screen.add(cards);
            layout.add(deck);
            layout.add(lastCard);
            //changing all the card into button
            for (int x = 0; x < game.getPlayers().get(turnNumPlayer).getHand().size(); x++) {
                if (game.getPlayers().get(turnNumPlayer).getCard(x) instanceof TrumpCard) {   //for trumpcard
                    JButton cardbtn = new JButton(new ImageIcon(((TCardPict) game.getPlayers().get(turnNumPlayer).getCard(x)).getCardImage().getImage()));
                    cardbtn.setSize(200, 300);
                    buttons.add(cardbtn);
                } else {            //For supertrump card
                    JButton cardbtn = new JButton(new ImageIcon(((STCardPict) game.getPlayers().get(turnNumPlayer).getCard(x)).getCardImage().getImage()));
                    cardbtn.setSize(200, 300);
                    buttons.add(cardbtn);
                }
            }
            //adding all the button into the GUI with the actionlistener
            for (int button = 0; button < game.getPlayers().get(playerTurn % game.getPlayers().size()).getHand().size(); button++) {
                buttons.get(button).addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        JButton buttonSource = (JButton) e.getSource();
                        Card cardplayed = game.getPlayers().get(playerTurn % game.getPlayers().size()).getCard(buttons.indexOf(buttonSource));
                        boolean gameContinue = useCard(cardplayed, game.getPlayers().get(playerTurn % game.getPlayers().size()));

                        if(game.getCardMode().equals("NEW")) {
                            game.putCard(cardplayed);
                            game.getPlayers().get(playerTurn % game.getPlayers().size()).getHand().remove(buttons.indexOf(buttonSource));
                            if(game.getPlayers().get(playerTurn%game.getPlayers().size()).getHand().size()==0)
                            {
                                playerTurn = playerTurn%game.getPlayers().size();
                                winner.add(game.getPlayers().get(playerTurn%game.getPlayers().size()).getName());
                                game.getPlayers().get(playerTurn%game.getPlayers().size()).leaveGame(game);
                                pick = false;
                                playCard();
                                revalidate();
                                repaint();
                            }
                            else {
                                pick = false;
                                pickMode();
                            }
                        }
                        else if(game.getCardMode().equals("SS"))
                        {
                            if(game.getPlayers().get(playerTurn%game.getPlayers().size()).checkWinningCard())                          //checks if the player got the winning card
                            {
                                for (int x = 0; x < game.getPlayers().get(turnNumPlayer).getHand().size(); x++)
                                {
                                    game.putCard(cardplayed);
                                    game.getPlayers().get(playerTurn % game.getPlayers().size()).getHand().remove(buttons.indexOf(buttonSource));
                                    game.setLastPlayerTurn(game.getPlayers().get(playerTurn % game.getPlayers().size()).getName());
                                }
                                game.setCardMode("S");                 //returns the mode back to Specific gravity
                                if(game.getPlayers().get(playerTurn%game.getPlayers().size()).getHand().size()==0)
                                {
                                    playerTurn = playerTurn%game.getPlayers().size();
                                    winner.add(game.getPlayers().get(playerTurn%game.getPlayers().size()).getName());
                                    game.getPlayers().get(playerTurn%game.getPlayers().size()).leaveGame(game);
                                    pick = false;
                                    playCard();
                                    revalidate();
                                    repaint();
                                }
                            }
                            else
                            {game.setCardMode("S");}
                            if(gameContinue) {                              //for playing the card
                                game.putCard(cardplayed);
                                game.getPlayers().get(playerTurn % game.getPlayers().size()).getHand().remove(buttons.indexOf(buttonSource));
                                game.setLastPlayerTurn(game.getPlayers().get(playerTurn % game.getPlayers().size()).getName());
                                if(game.getPlayers().get(playerTurn%game.getPlayers().size()).getHand().size()==0)
                                {
                                    playerTurn = playerTurn%game.getPlayers().size();
                                    winner.add(game.getPlayers().get(playerTurn%game.getPlayers().size()).getName());
                                    game.getPlayers().get(playerTurn%game.getPlayers().size()).leaveGame(game);
                                    pick = false;
                                    playCard();
                                    revalidate();
                                    repaint();
                                }
                                else {
                                    playerTurn++;
                                    if (game.getLastCard() instanceof STCard) {
                                        lastCard = new JLabel(new ImageIcon(((STCardPict) game.getLastCard()).getCardImage().getImage()));
                                    } else {
                                        lastCard = new JLabel(new ImageIcon(((TCardPict) game.getLastCard()).getCardImage().getImage()));
                                    }
                                    pick = false;
                                    pass = 0;
                                    playCard();
                                    revalidate();
                                    repaint();
                                }
                            }
                        }
                        else{
                            if (gameContinue) {
                                game.putCard(cardplayed);
                                game.getPlayers().get(playerTurn % game.getPlayers().size()).getHand().remove(buttons.indexOf(buttonSource));
                                game.setLastPlayerTurn(game.getPlayers().get(playerTurn % game.getPlayers().size()).getName());
                                if(game.getPlayers().get(playerTurn%game.getPlayers().size()).getHand().size()==0)
                                {
                                    playerTurn = playerTurn%game.getPlayers().size();
                                    winner.add(game.getPlayers().get(playerTurn%game.getPlayers().size()).getName());
                                    game.getPlayers().get(playerTurn%game.getPlayers().size()).leaveGame(game);
                                    pick = false;
                                    playCard();
                                    revalidate();
                                    repaint();
                                }
                                else {
                                    playerTurn++;
                                    if (game.getLastCard() instanceof STCard) {
                                        lastCard = new JLabel(new ImageIcon(((STCardPict) game.getLastCard()).getCardImage().getImage()));
                                    } else {
                                        lastCard = new JLabel(new ImageIcon(((TCardPict) game.getLastCard()).getCardImage().getImage()));
                                    }
                                    pick = false;
                                    pass = 0;
                                    playCard();
                                    revalidate();
                                    repaint();
                                }
                            } else {
                                text.setText("Card is invalid, pick another card or pass. Game mode: " + game.getGameMode());
                                revalidate();
                                repaint();
                            }}
                        if(game.getPlayers().size()==1)
                        {
                            showWinner();
                            revalidate();
                            repaint();
                        }
                    }}
                );
                cards.add(buttons.get(button));
            }
            panel.add(screen);

        }
    }

    public boolean useCard(Card card, Player play)
    {
        boolean isHigher = false;
        int comparison = 0;
        //comparing the card if its playable
        if(pass == game.getPlayers().size()-1 || game.getUsedCard().size()==0 || pick )
        {
            if(card  instanceof STCard)
            {
                game.setCardMode(((STCard) card).function());
            }
            isHigher = true;
        }
        else {
            if(card instanceof TrumpCard) {
                if (game.getLastCard() instanceof TrumpCard) {
                    if (game.getCardMode().equals("H")) {
                        Float current = new Float(((TrumpCard) card).getHardness());
                        Float previous = new Float(((TrumpCard) game.getLastCard()).getHardness());
                        comparison = current.compareTo(previous);
                    } else if (game.getCardMode().equals("S")) {
                        Float current = new Float(((TrumpCard) card).getGravity());
                        Float previous = new Float(((TrumpCard) game.getLastCard()).getGravity());
                        comparison = current.compareTo(previous);
                    } else if (game.getCardMode().equals("C")) {
                        Float current = new Float(((TrumpCard) card).getCleavageInt());
                        Float previous = new Float(((TrumpCard) game.getLastCard()).getCleavageInt());
                        comparison = current.compareTo(previous);
                    } else if (game.getCardMode().equals("A")) {
                        Float current = new Float(((TrumpCard) card).getCrustalInt());
                        Float previous = new Float(((TrumpCard) game.getLastCard()).getCrustalInt());
                        comparison = current.compareTo(previous);
                    } else if (game.getCardMode().equals("E")) {
                        Float current = new Float(((TrumpCard) card).getEcoInt());
                        Float previous = new Float(((TrumpCard) game.getLastCard()).getEcoInt());
                        comparison = current.compareTo(previous);
                    }               //compare the card value according to the trump mode
                    if (comparison > 0) {
                        isHigher = true;
                    }
                } else {
                    isHigher = true;
                }
            }
            else
            {
                game.setCardMode(((STCard) card).function());       //change the category
                isHigher = true;
            }
        }
        return isHigher;
    }

    public void showWinner()
    {
        panel.removeAll();
        JPanel winnerList = new JPanel();
        winnerList.setLayout(new BoxLayout(winnerList,BoxLayout.Y_AXIS));
        for(int x = 0; x < winner.size();x++)
        {
            String show = "Winner "+(x+1)+ "= " + winner.get(x) +"";
            winnerList.add(new JLabel(show));
        }
        text.setText("Congratulations!");
        panel.add(winnerList);
    }
}








