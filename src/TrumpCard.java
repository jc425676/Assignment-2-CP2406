//Created By Jasson Didhi Kumala
//10/09/2017
//

public class TrumpCard extends Card {
    private float hardness;
    private float gravity;
    private String cleavage;
    private int cleavageInt;
    private String crustal;
    private int crustalInt;
    private String eco;
    private int ecoInt;

    TrumpCard(String nm, float hard, float grav, String cleav, String cru, String ecoI)
    {
        super(nm);
        hardness = hard;
        gravity = grav;
        cleavage = cleav;
        crustal = cru;
        eco = ecoI;
        cleavageInt = convertCleavageInt();
        crustalInt = convertCrustal();
        ecoInt = convertEcoInt();                  //Constructor for all the variable
    }

    public int getCleavageInt() {
        return cleavageInt;
    }

    public int getCrustalInt() {
        return crustalInt;
    }

    public int getEcoInt() {
        return ecoInt;
    }

    public float getHardness() {
        return hardness;
    }

    public float getGravity() {
        return gravity;
    }

    public String getCleavage() {
        return cleavage;
    }

    public String getCrustal() {
        return crustal;
    }

    public String getEco() {
        return eco;
    }

    public int convertCleavageInt()
    {
        int cleaInt = 0;
        String x = getCleavage();
        if(x.equals("none"))
        {cleaInt = 1;}
        else if(x.equals("poor/none"))
        {cleaInt = 2;}
        else if(x.equals("1 poor"))
        {cleaInt = 3;}
        else if(x.equals("2 poor"))
        {cleaInt = 4;}
        else if(x.equals("1 good"))
        {cleaInt = 5;}
        else if(x.equals("1 good/1 poor"))
        {cleaInt = 6;}
        else if(x.equals("2 good"))
        {cleaInt = 7;}
        else if(x.equals("3 good"))
        {cleaInt = 8;}
        else if(x.equals("1 perfect"))
        {cleaInt = 9;}
        else if(x.equals("1 perfect/1 good"))
        {cleaInt = 10;}
        else if(x.equals("1 perfect/2 good"))
        {cleaInt = 11;}
        else if(x.equals("2 perfect/1 good"))
        {cleaInt = 12;}
        else if(x.equals("3 perfect"))
        {cleaInt = 13;}
        else if(x.equals("4 perfect"))
        {cleaInt = 14;}
        else if(x.equals("6 perfect"))
        {cleaInt = 15;}
        return cleaInt;
    }
    public int convertCrustal()
    {
        int cInt = 0;
        String x = getCrustal();
        if(x.equals("ultratrace"))
        {cInt=1;}
        else if(x.equals("trace"))
        {cInt=2;}
        else if(x.equals("low"))
        {cInt=3;}
        else if(x.equals("moderate"))
        {cInt=4;}
        else if(x.equals("high"))
        {cInt=5;}
        else if(x.equals("very high"))
        {cInt=6;}
        return cInt;
    }
    public int convertEcoInt()
    {
        int eInt = 0;
        String x = getEco();
        if(x.equals("trivial"))
        {eInt=1;}
        else if(x.equals("low"))
        {eInt=2;}
        else if(x.equals("moderate"))
        {eInt=3;}
        else if(x.equals("high"))
        {eInt=4;}
        else if(x.equals("very high"))
        {eInt=5;}
        else if(x.equals("I'm rich!"))
        {eInt=6;}
        return eInt;
    }
}
