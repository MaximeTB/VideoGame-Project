import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Ces Skills ont un effet sur l'Eleve quand il est present dans un Lieu precis. Par exemple un eleve
 * Travailleur Efficace gagnera un point d'etude en plus quand il sera dans l'Amphi
 */
public class SkillsOnLieu extends Skills {
    /**
     * Liste des Lieux cible par le Skill.
     */
    private ArrayList<Lieu> LieuCible = new ArrayList<>(); //liste des lieux (event inclus)
    /**
     * Liste des Stats ciblees par le Skill.
     */
    private ArrayList<String> StatCible = new ArrayList<>();
    /**
     * Liste des Valeurs des modifications apportees par le Skill.
     */
    private ArrayList<Integer> ValueEffet = new ArrayList<>(); //valeur du buff


    /**
     * @param color Categorie du Skill.
     * @param name Nom du Skill.
     * @param LieuCible Lieu cible par le Skill.
     * @param StatCible Stat ciblee par le Skill.
     * @param ValueEffet Valeur de la modification apportee par le Skill.
     */
    public SkillsOnLieu(String color, String name,ArrayList<Lieu> LieuCible,ArrayList<String> StatCible,ArrayList<Integer> ValueEffet) {
        super(color,name);
        this.LieuCible=LieuCible;
        this.StatCible=StatCible;
        this.ValueEffet=ValueEffet;
    }


    /**
     * On verifie que l'Eleve est present dans le Lieu affecte par le Skill puis on applique (sur l'Eleve ou sur le Player) le/les Effets.
     * @param E Eleve affecte par le Skill (on se sera assure que l'Elev possede le Skill en question).
     * @param list Liste du Joueur.
     * @param type Moment de la journee concerne.
     */
    public void ApplySkillEffectOnLieu(Eleve E, Player list, String type) {
        //Type pour Jour/Nuit
        int i;

        for(i=0; i<this.getLieuCible().size(); i++){

            if(this.getLieuCible().get(i).equals(E.getLocation(type))){

                String stat = this.getStatCible().get(i);//Stat affectée par l'effect

                if(stat.equals("Argent")){
                    list.gainArgent(ValueEffet.get(i)-E.getLocation(type).getEOM());
                }
                if(stat.equals("Popularite")){
                    list.gainPopularite(ValueEffet.get(i)-E.getLocation(type).getEOP());
                }
                if(stat.equals("Admin")){
                    list.gainAdmin(ValueEffet.get(i)-E.getLocation(type).getEOA());
                }
                if(stat.equals("Cohesion")){
                    list.gainCohesion(ValueEffet.get(i)-E.getLocation(type).getEOC());
                }
                if(stat.equals("PV")){
                    list.gainPV(ValueEffet.get(i)-E.getLocation(type).getEOPV());
                }
                if(stat.equals("Etude")){
                    E.Etude(ValueEffet.get(i)-E.getLocation(type).getEOS());
                }
                if(stat.equals("Fatigue")){
                    E.Fatigue(ValueEffet.get(i)-E.getLocation(type).getEOT());
                }
                if(stat.equals("Cout")){
                    E.Cout(ValueEffet.get(i)-E.getLocation(type).getEOC());
                }
            }
        }
    }

    /**
     * Si par erreur la mauvaise fonction d'application de Skill est executee sur le Skill,
     * permet de le faire savoir sans que toute l'execution du code s'arrete.
     * @param E -
     * @param list -))
     */
    @Override
    public void ApplySkillEffect(Eleve E, Player list) {
        System.out.println("mauvaise fonction");
    }


    public void OnRecruit(Eleve E, Player list){}

//Getter
    public ArrayList<Lieu> getLieuCible() {
        return LieuCible;
    }
    public ArrayList<String> getStatCible() {
        return StatCible;
    }
    public ArrayList<Integer> getValueEffet() {
        return ValueEffet;
    }
//
//Setter
    public void setLieuCible(ArrayList<Lieu> lieuCible) {
        LieuCible = lieuCible;
    }
    public void setStatCible(ArrayList<String> statCible) {
        StatCible = statCible;
    }
    public void setValueEffet(ArrayList<Integer> valueEffet) {
        ValueEffet = valueEffet;
    }
//
}
