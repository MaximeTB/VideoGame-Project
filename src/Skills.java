/**
 * Les Skills sont des "capacites speciales" de certains Eleves qui leur permettent d'obtenir des bonus pour certaines activite.<br/>
 * Par exemple : un Eleve qui possede le Skill "Studieu" gagnera plus de Points d'Etude en etant dans l'Amphi.<br/>
 * Certain Skills (pas tous) peuvent s'acquerir en cour de parti.<br/>
 * Par exemple : si un Eleve passe beaucoup de un Pole, il peut obtenir un Skill lui octroyant des bonus lorsque qu'il travail pour ce Pole.
 */
public abstract class Skills {
    /**
     * Les Skills sont classe en plusieurs categories, que nous symbolisons par des couleurs, en fonction de leurs effets.<br/>
     * Les Skills "Grey" sont des Skills ajoutes a l'Eleve lors de sa creation et ne pouvant pas s'acquerir ensuite.<br/>
     * Les Skills "Blue" sont des Skills influant l'action de l'Eleve dans le pole BDA et tous les Evenements lui etant lie.<br/>
     * Les Skills "Red" sont des Skills influant l'action de l'Eleve dans le pole BDS et tous les Evenements lui etant lie.<br/>
     * Les Skills "Black" sont des Skills influant l'action de l'Eleve dans le pole Cave et tous les Evenements lui etant lie.<br/>
     * Les Skills "Yellow" sont des Skills influant l'action de l'Eleve dans le pole BDLS et tous les Evenements lui etant lie.<br/>
     * Les Skills "Green" sont des Skills influant l'action de l'Eleve dans le pole BDTech et tous les Evenements lui etant lie.<br/>
     */
    String color;
    /**
     * Nom du Skill.
     */
    String name;

    /**
     *
     * @param color Couleur du Skill.
     * @param name Nom du Skill.
     */
    public Skills(String color, String name){
       this.color=color;
       this.name=name;
    }

    /**
     * Permet de modifier la couleur du Skill si besoin (en theorie il n'y en a pas besoin).
     * @param index Couleur choisie. Accepte les mots français et anglais, avec ou sans majuscule, pour chaque couleur, et le "converti" au format utilise dans le rest du code (en anglais avec majuscule).
     */
    public void setColor(String index) {
        switch (index) {
            case "Gris", "Grey","gris","grey" -> this.color = "Grey";
            case "Bleu","Blue","bleu","blue" -> this.color = "Blue";
            case "Rouge","Red","rouge","red" -> this.color = "Red";
            case "Black","Noir","black","noir" -> this.color = "Black";
            case "Jaune","Yellow","jaune","yellow" -> this.color = "Yellow";
            case "Vert","Green","vert","green" -> this.color = "Green";
        }
    }

    /*public void setRandomName(int color, int index){
        switch(color){
            case 0 : //gris
                switch(index){
                    case 1 -> this.name = "Rich";
                    case 2 -> this.name = "Carte Metro";
                    case 3 -> this.name = "Reseaux";
                    case 4 -> this.name = "Breton";
                }
                break;
            case 1 : //bleu
                switch (index) {
                    case 1 -> this.name = "Gamer";
                    case 2 -> this.name = "Creatif";
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
                    case 1 -> this.name = "Pedagogue";
                    case 2 -> this.name = "Travailleur efficace";
                    case 3 -> this.name = "Passionne";
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

    /**
     * Applique l'effet du Skill. Cette methode differera enormement en fonction du type de Skill.
     * @param E Eleve possedant le Skill.
     * @param list Joueur de la partie en cour.
     */
    abstract void ApplySkillEffect(Eleve E, Player list);

    /**
     * Methode particuliere utile pour les SkillsOnRecruit, que l'on applique sur tout les Skills lorsque du recrutement, mais qui n'aura aucun effet pour un Skill qui n'est pas un SkillOnRecruit.
     * @param E
     * @param list
     */
    abstract void OnRecruit(Eleve E, Player list);//a appeler lorsque l'elève est recrute dans la methode player.recrute()
    

    @Override
    public String toString() {
        return  name  ;
    }

}
