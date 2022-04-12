import java.util.ArrayList;

public class Pole {
    private String name;
    ArrayList<Eleve> Member=new ArrayList<Eleve>();
    private int XP=0;
    private int level=1;
    private boolean created; //true si le pole est déja crée, false sinon. toujour true pour le bureau
    private String color1, color2;
    private final int RequiredXp=10;


    public Pole(String name, String color1,boolean creat) {
        this.name=name;
        this.color1=color1;
        color2="Null";
        this.created=creat;

    }

    public Pole(String name, String color1, String color2,boolean creat){
        this.name=name;
        this.color1=color1;
        this.color2=color2;
        this.created=creat;
    }

    public int getLevel() {
        return level;
    }
    public ArrayList<Eleve> getMember() {
        return Member;
    }
    public boolean isCreated() {
        return created;
    }
    public String getName(){return name;}

    public void addMember(Eleve e){
        Member.add(e);
        e.setPole(this);
    }
    public void removeMember(Eleve e){Member.remove(e);}
    public void gainXP(int xp){
        this.XP+=xp;
        if(XP>=(this.level*2)*RequiredXp){
            this.level++;
        }
    }
    public void enable(){
        this.created=true;
    }

    public void meeting(){
        int bonus=0;
        for (Eleve eleve : Member){
            bonus++;
            for (Skills skill : eleve.getSkillsList()){
                if (skill.getColor().equals(color1) || skill.getColor().equals(color2)){
                    bonus++;
                }
            }
        }
        this.gainXP(1+bonus);
    }//chaque élève rapporte 1Xp, doublé pour les élèves de la bonne couleur




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

    public void displayPole(){
        System.out.println(name+" (niveau "+level+")");
        for(Eleve e : Member){
            System.out.println(e.getName());
        }
    }//affiche tout les poles disponibles
}
