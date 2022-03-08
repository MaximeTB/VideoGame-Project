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
        Bureau bureau = new Bureau("Bureau","Jaune");

        for(Integer i=0; i<10; i++ ){
            bureau.addMember(new Eleve());

            for(Skills skill : bureau.getMember().get(i).getSkillsList()) {
                System.out.println(skill.getColor());
            }
        }

        bureau.giveRole("Président",bureau.getMember().get(0));
        bureau.giveRole("Trésorier",bureau.getMember().get(1));
        bureau.giveRole("Secrétaire",bureau.getMember().get(2));

        System.out.println(bureau.getLevel());

        bureau.meeting();

        System.out.println(bureau.getLevel());

        System.out.println(bureau.getPrez());
        System.out.println(bureau.getTrez());
        System.out.println(bureau.getSecr());
    }


}
