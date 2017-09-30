import javax.swing.*;

public class TCardPict extends TrumpCard {
    private ImageIcon cardImage;
    TCardPict(String nm, float hard, float speGra, String cleav, String cruAbu, String eco, ImageIcon img)
    {
        super(nm,hard,speGra,cleav,cruAbu,eco);
        cardImage = img;
    }

    public ImageIcon getCardImage() {
        return cardImage;
    }
}