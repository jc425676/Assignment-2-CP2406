import javax.swing.*;

public class STCardPict extends STCard {
    private ImageIcon cardImage;

    STCardPict(String nm, String desc, ImageIcon img)
    {
        super(nm, desc);
        cardImage = img;
    }

    public ImageIcon getCardImage() {
        return cardImage;
    }
}
