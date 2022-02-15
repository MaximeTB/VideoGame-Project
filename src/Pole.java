import java.util.ArrayList;

public class Pole {
    String name;
    ArrayList<Eleve> Member=new ArrayList<Eleve>();
    private int XP=0;
    private int level=1;
    private String color;

    public Pole(String name, String color) {
        this.name=name;
        this.color=color;

    }

    public int getLevel() {
        return level;
    }

    public ArrayList<Eleve> getMember() {
        return Member;
    }

    public void addMember(Eleve e){
        Member.add(e);
    }
    public void levelUp(){this.level++;}

    public void meeting(){
        int bonus=0;
        for (Eleve eleve : Member){
            for (Skills skill : eleve.getSkillsList()){
                if (skill.getColor().equals(color)){
                    bonus++;
                }

            }
        }
        level+=1+bonus;
    }


}
