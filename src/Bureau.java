public class Bureau extends Pole{
    private Eleve prez, trez, secr;


    public Bureau(String name, String color1){
        super(name, color1,true);
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
}