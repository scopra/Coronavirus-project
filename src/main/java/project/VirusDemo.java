package project;

/**
 *
 * @author Selma Čopra
 */
public class VirusDemo {
    public static void main(String[] args){
        Coronavirus virus1 = new Coronavirus();
        Coronavirus virus1copy = new Coronavirus(virus1);
        System.out.println(virus1);
        System.out.println(virus1copy);

        //expected: Functionally similar.
        if(!virus1.functionallySimilar(virus1copy)){
            System.out.println("Not functionally similar.");
        } else{
            System.out.println("Functionally similar.");
        }

        //expected: Same genetic sequence.
        if(!virus1.same(virus1copy)){
            System.out.println("Different genetic sequence.");
        } else{
            System.out.println("Same genetic sequence.");
        }

        //expected: 0
        System.out.println(virus1.editDistance(virus1copy));

        virus1.mutate();

        //expected: Not functionally similar.
        if(!virus1.functionallySimilar(virus1copy)){
            System.out.println("Not functionally similar.");
        } else{
            System.out.println("Functionally similar.");
        }

        //expected: Different genetic sequence.
        if(!virus1.same(virus1copy)){
            System.out.println("Different genetic sequence.");
        } else{
            System.out.println("Same genetic sequence.");
        }

        //expected: 1
        System.out.println(virus1.editDistance(virus1copy));

        Coronavirus virus2 = new Coronavirus();

        //expected: Not functionally similar.
        System.out.println(virus2);
        if(!virus1.functionallySimilar(virus2)){
            System.out.println("Not functionally similar.");
        } else{
            System.out.println("Functionally similar.");
        }

        virus2.mutate();
        System.out.println("Index of latest mutation: " + virus2.getMutationIndex() +
                ", acid changed from " + virus2.getOriginalAcid() + " to " +
                virus2.getDNA().get(virus2.getMutationIndex()));

        System.out.println(virus1.editDistance(virus2));

    }
}
