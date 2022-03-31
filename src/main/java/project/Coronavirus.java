package project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Selma Čopra & Adi Kalača
 */
public class Coronavirus implements Viral{

    private ArrayList<Character> DNA = null;
    private static final int DEFAULT_CAPACITY = 30000;
    private int mutationIndex = -1;
    private char originalAcid = ' ';

    public Coronavirus(){
        DNA = new ArrayList<>(DEFAULT_CAPACITY);
        for(int i=0; i<30000; i++){
            DNA.add(getRandomAminoAcid());
        }
    }

    /**
     * Copy constructor.
     * @param original DNA sequence.
     */
    public Coronavirus(Coronavirus original){
        DNA = new ArrayList<>(DEFAULT_CAPACITY);
        for(Character item : original.DNA){
            DNA.add(item);
        }
    }

    /**
     * Checks whether object was initialized.
     * @param c
     */
    private void checkInitialization(Coronavirus c){
        if(c.DNA == null){
            throw new IllegalArgumentException("Object is not initialized.");
        }
    }

    /**
     * @return random index in range 0-30000.
     */
    private int getRandomIndex(){
        int randomIndex = (int) (Math.random() * (30000 + 1));
        return randomIndex;
    }

    /**
     * @return random amino acid (A, C, T or G)
     */
    private char getRandomAminoAcid(){
        int randomNumber = (int) (Math.random() * ((4 - 1) + 1)) + 1;
        switch (randomNumber) {
            case 1:
                return 'A';
            case 2:
                return 'C';
            case 3:
                return 'T';
            default:
                return 'G';
        }
    }

    @Override
    public void mutate(){
        int randomIndex = getRandomIndex();
        char acid = getRandomAminoAcid();
        originalAcid = DNA.get(randomIndex);
        while(acid == originalAcid){
            acid = getRandomAminoAcid();
        }
        mutationIndex = randomIndex;
        DNA.set(randomIndex, acid);
    }

    /**
     * @return index of latest mutation in DNA sequence.
     */
    public int getMutationIndex(){
        if(mutationIndex == -1){
            throw new IllegalStateException("DNA has not been mutated yet.");
        }
        return mutationIndex;
    }

    /**
     * @return original acid in DNA sequence before latest mutation.
     */
    public char getOriginalAcid(){
        if(originalAcid == ' '){
            throw new IllegalStateException("DNA has not been mutated yet.");
        }
        return originalAcid;
    }

    @Override
    public boolean same(Coronavirus c){
        checkInitialization(c);
        return(this.DNA.equals(c.DNA));
    }


    @Override
    public boolean functionallySimilar(Coronavirus c){
        checkInitialization(c);
        int thisAdenosineFrequency = Collections.frequency(DNA, 'A');
        int thisGuanineFrequency = Collections.frequency(DNA, 'G');
        int thisThymineFrequency = Collections.frequency(DNA, 'T');
        int thisCytosineFrequency = Collections.frequency(DNA, 'C');

        int otherAdenosineFrequency = Collections.frequency(c.DNA, 'A');
        int otherGuanineFrequency = Collections.frequency(c.DNA, 'G');
        int otherThymineFrequency = Collections.frequency(c.DNA, 'T');
        int otherCytosineFrequency = Collections.frequency(c.DNA, 'C');

        return (thisAdenosineFrequency == otherAdenosineFrequency) &&
                (thisGuanineFrequency == otherGuanineFrequency) &&
                (thisThymineFrequency == otherThymineFrequency) &&
                (thisCytosineFrequency == otherCytosineFrequency);
    }

    @Override
    //See: https://en.wikipedia.org/wiki/Hamming_distance
    public int editDistance(Coronavirus c){
        checkInitialization(c);
        StringBuilder DNA1 = new StringBuilder();
        for(char s : DNA)
        {
            DNA1.append(s);
        }
        StringBuilder DNA2 = new StringBuilder();
        for(char s : c.DNA)
        {
            DNA2.append(s);
        }
        String thisDNA = DNA1.toString();
        String otherDNA = DNA2.toString();
        int distance = 0;
        for(int i=0; i<thisDNA.length(); i++){
            if(thisDNA.charAt(i) != otherDNA.charAt(i)){
                distance++;
            }
        }
        return distance;
    }

    /**
     * @return String representation of DNA sequence.
     */
    @Override
    public String toString(){
        return(Arrays.toString(DNA.toArray()));
    }

    public ArrayList<Character> getDNA(){
        return new ArrayList<>(DNA);
    }
}
