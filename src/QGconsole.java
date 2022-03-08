public class QGconsole {
    String inventaire[];
    int index;

    public QGconsole(){
        index = 0;
        inventaire = new String[10];
    }

    public void liste(){
        Player list = new Player();
        list.generateList(5);
    }

    public void shop(){
        System.out.println("Que veux-tu acheter poto ?");
        System.out.println("1. Cafetière 2. Cuisine 3. Jeux gonflables 4. Louer une salle");
        System.out.println("5. Feux d'artifice 6. Inviter une célebrité 7. Quitter");
    }
    public void shopAction(int Entrée, Player player){
        switch(Entrée){
            case 1 :
                System.out.println("Achat effectué !");
                inventaire[index] = "Cafetière";
                index++;
                player.setArgent(player.getArgent()-5);
                break;
            case 2 :
                System.out.println("Achat effectué !");
                inventaire[index] = "Cuisine";
                index++;
                player.setArgent(player.getArgent()-20);
                break;
            case 3 :
                System.out.println("Achat effectué !");
                inventaire[index] = "Jeux gonflables";
                index++;
                player.setArgent(player.getArgent()-50);
                break;
            case 7 :
                break;

        }
    }

    public void inventaire() {
        if (inventaire[0]==null){
            System.out.println("Inventaire vide - Achète quelque chose :(");
        }else {
            for (int i = 0; i < index; i++) {
                System.out.println(inventaire[i]);
            }
        }
        System.out.println("1. Retour");
    }
}
