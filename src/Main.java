public class Main {

    public static void main(String[] args) {
        for (int i = 0 ; i < 20; i++) {
            Eleve test = new Eleve();
            //System.out.println("Studies = " + test.getStudies()); //ok
            //System.out.println("Loyalty = " + test.getLoyalty()); //ok
            int temp = 0;
            for (Skills s : test.getSkillsList()){
                System.out.println("Skill n°" + temp + " = " + s.getName());
                temp++;
            }

        }

    }
}
