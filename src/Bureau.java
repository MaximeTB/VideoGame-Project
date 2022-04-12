public class Bureau extends Pole{
    private Eleve prez, trez, secr;

//le trésorier génère plus d'argent, le prez plus de pop/pv, le secrétaire + de cohesion
    //créer une méthode pour chacun

    public Bureau(String name, String color1, Eleve prez){
        super(name, color1,true);
        this.prez=prez;
    }

    public void giveRole(String role, Eleve eleve){
        if(role.equals("Trésorier")){
            trez=eleve;
        }

        if(role.equals("Président")){
            prez=eleve;
        }

        if(role.equals("Secrétaire")){
            secr=eleve;
        }

    }

    public Eleve getPrez() {
        return prez;
    }

    public Eleve getSecr() {
        return secr;
    }

    public Eleve getTrez() {
        return trez;
    }

    public Eleve findEleve(String role){
        if(role.equals("Trésorier")){
            return trez;
        }
        else if(role.equals("Président")){
            return prez;
        }
        else if(role.equals("Secrétaire")){
            return secr;
        }
        else{
            System.out.println("role invalide");
            return null;
        }

    }

    public String findRole(Eleve E){
        if(E.equals(trez)){
            return "Trésorier";
        }
        else if(E.equals(prez)){
            return "Président";
        }
        else if(E.equals(secr)){
            return "Secrétaire";
        }
        else{
            System.out.println("cet élève n'est aps au bureau");
            return null;
        }
    }


    public void addMember(Eleve E, String role) {
        this.addMember(E);
        //tester si jamais le role n a pas encore ete attribue
        this.removeMember(this.findEleve(role));
        this.giveRole(role, E);
    }


}