//Created By Jasson Didhi Kumala
//10/09/2017
//

public class STCard extends Card {
    private String description;
    STCard(String nm, String desc)
    {
        super(nm);
        description = desc;
    }

    public String function()
    {
        String effect = "";
        String x = getName();
        if(x.equals("The Mineralogist"))
        {effect = "C";}
        else if(x.equals("The Geologist"))
        {effect = "NEW";}
        else if(x.equals("The Geophysicist"))
        {effect = "SS";}
        else if(x.equals("The Petrologist"))
        {effect = "A";}
        else if(x.equals("The Miner"))
        {effect = "E";}
        else if(x.equals("The Gemologist"))
        {effect = "H";}
        return effect;                                     //Filling the details for the method
    }

    public String cardDesc()
    {
        return description;                                 //Filling the details for the method to describe
    }
}

