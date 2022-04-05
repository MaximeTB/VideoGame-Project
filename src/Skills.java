public abstract class Skills {
    String color;
    String name;
    String type;


    public Skills(String type,String color, String name){
       this.color=color;
       this.name=name;
       this.type=type;
    }

    public void setRandomColor(int index) {
        switch (index) {
            case 0 -> this.color = "Gris";
            case 1 -> this.color = "Bleu";
            case 2 -> this.color = "Rouge";
            case 3 -> this.color = "Noir";
            case 4 -> this.color = "Jaune";
            case 5 -> this.color = "Vert";
        }
    }

    /*public void setRandomName(int color, int index){
        switch(color){
            case 0 : //gris
                switch(index){
                    case 1 -> this.name = "Rich";
                    case 2 -> this.name = "Carte Métro";
                    case 3 -> this.name = "Réseaux";
                    case 4 -> this.name = "Breton";
                }
                break;
            case 1 : //bleu
                switch (index) {
                    case 1 -> this.name = "Gamer";
                    case 2 -> this.name = "Créatif";
                    case 3 -> this.name = "Acteur";
                    case 4 -> this.name = "Danseur";
                }
                break;
            case 2 : //rouge
                switch (index) {
                    case 1 -> this.name = "Endurant";
                    case 2 -> this.name = "Capitaine";
                    case 3 -> this.name = "Mascotte";
                    case 4 -> this.name = "Marathonien";
                }
                break;
            case 3 : //noir
                switch (index) {
                    case 1 -> this.name = "Musicien";
                    case 2 -> this.name = "Foie d'acier";
                    case 3 -> this.name = "Baby Caveux";
                    case 4 -> this.name = "noir4";
                }
                break;
            case 4  : //jaune
                switch (index) {
                    case 1 -> this.name = "Cuisinier";
                    case 2 -> this.name = "Bilingue";
                    case 3 -> this.name = "Solidaire";
                    case 4 -> this.name = "jaune4";
                }
                break;
            case 5 : //vert
                switch (index) {
                    case 1 -> this.name = "Pédagogue";
                    case 2 -> this.name = "Travailleur efficace";
                    case 3 -> this.name = "Passionné";
                    case 4 -> this.name = "Photographe";
                }
                break;
        }
    }*/


    public String getColor() {
        return color;
    }
    public String getName(){
        return this.name;
    }

    // fait correspondre l'effet au skill
    abstract void ApplySkillEffect(Eleve E, Player list);

    abstract void ApplySkillEffectOnLieu(Eleve E, Player list, String type);

    @Override
    public String toString() {
        return  name  ;
    }

}
