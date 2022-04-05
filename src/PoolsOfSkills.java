import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class PoolsOfSkills {
    private ArrayList<String> BlueList;
    private ArrayList<String> YellowList;
    private ArrayList<String> RedList;
    private ArrayList<String> GreyList;
    private ArrayList<String> GreenList;
    private ArrayList<String> BlackList;

    private SkillColor Colors = new SkillColor();

    public PoolsOfSkills(String filename){
        this.BlueList = new ArrayList<String>();
        this.BlackList = new ArrayList<String>();
        this.GreenList = new ArrayList<String>();
        this.YellowList = new ArrayList<String>();
        this.RedList = new ArrayList<String>();
        this.GreyList = new ArrayList<String>();

        //Skills skill;


        try{
            BufferedReader buf = new BufferedReader(new FileReader(filename));
            buf.readLine();
            String s = buf.readLine();
            while(s!=null){
                s.replaceAll("\"", "");
                String fields[] = s.split(";");
                //skill= new Skills(fields[1],fields[0]);

                switch(fields[1]){
                    //case "Blue" -> ;
                }
            }
        }
        catch(Exception e){
            System.out.println("Maybe the file isn't there ?");
            e.printStackTrace();
        }
    }

//Getter
    public ArrayList<String> getBlueList() {
        return BlueList;
    }
    public ArrayList<String> getYellowList() {
        return YellowList;
    }
    public ArrayList<String> getRedList() {
        return RedList;
    }
    public ArrayList<String> getGreyList() {
        return GreyList;
    }
    public ArrayList<String> getGreenList() {
        return GreenList;
    }
    public ArrayList<String> getBlackList() {
        return BlackList;
    }
    public SkillColor getColors() {
        return Colors;
    }
//
//Setter

    public void setBlueList(ArrayList<String> blueList) {
        BlueList = blueList;
    }
    public void setYellowList(ArrayList<String> yellowList) {
        YellowList = yellowList;
    }
    public void setRedList(ArrayList<String> redList) {
        RedList = redList;
    }
    public void setGreyList(ArrayList<String> greyList) {
        GreyList = greyList;
    }
    public void setGreenList(ArrayList<String> greenList) {
        GreenList = greenList;
    }
    public void setBlackList(ArrayList<String> blackList) {
        BlackList = blackList;
    }
    public void setColors(SkillColor colors) {
        Colors = colors;
    }
//
}
