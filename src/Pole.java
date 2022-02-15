import java.util.ArrayList;

public class Pole {
    String name;
    ArrayList<Eleve> Member=new ArrayList<Eleve>();
    private int XP=0;
    private int level=1;
    private String color1, color2;

    public Pole(String name, String color1) {
        this.name=name;
        this.color1=color1;
        color2="Null";
    }

    public Pole(String name, String color1, String color2){
        this.name=name;
        this.color1=color1;
        this.color2=color2;
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
                if (skill.getColor().equals(color1) || skill.getColor().equals(color2)){
                    bonus++;
                }

            }
        }
        level+=1+bonus;
    }

    public static void main(String[] args){
        Pole bouffe = new Pole("Pole Bouffe","Jaune","Bleu");

        for(Integer i=0; i<10; i++ ){
            bouffe.addMember(new Eleve());

            for(Skills skill : bouffe.getMember().get(i).getSkillsList()) {
                System.out.println(skill.getColor());
            }
        }

        System.out.println(bouffe.getLevel());

        bouffe.meeting();

        System.out.println(bouffe.getLevel());
    }


}
