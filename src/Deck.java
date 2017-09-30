//Created By Jasson Didhi Kumala
//10/09/2017
//

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList <Card> cards;

    Deck(ArrayList <Card> cardArrayList){
        cards = cardArrayList;
    }
    public Card drawCard(){
        int x = new Random().nextInt(cards.size());
        Card retrive = cards.get(x);
        cards.remove(x);
        return retrive;

    }
    public ArrayList<Card> getCards(){
        return cards;
    }

}
