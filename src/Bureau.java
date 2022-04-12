public class Bureau extends Pole{
    private Eleve prez, trez, secr;


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

    public Eleve findRole(String role){
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


    public void addMember(Eleve E, String role) {
        this.addMember(E);
        //tester si jamais le role n a pas encore ete attribue
        this.removeMember(this.findRole(role));
        this.giveRole(role, E);
    }
}