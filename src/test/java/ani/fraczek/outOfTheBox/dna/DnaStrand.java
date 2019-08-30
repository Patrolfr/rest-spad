package ani.fraczek.outOfTheBox.dna;


public class DnaStrand {

    public static String makeComplement(String dna) {

        String afterReplace = dna;

        afterReplace = afterReplace.replace("A", "1");
        afterReplace = afterReplace.replace("T", "A");
        afterReplace = afterReplace.replace("1", "T");
        afterReplace = afterReplace.replace("C", "3");
        afterReplace = afterReplace.replace("G", "C");
        afterReplace = afterReplace.replace("3", "G");

        return afterReplace;
    }

    public static String makeComplementFast(String dna) {

        char[] dnaArray = dna.toCharArray();
        char[] result = new char[dnaArray.length];

        for (int i = 0; i<dnaArray.length; i++){
            if(dnaArray[i] == 'A')
                result[i] = 'T';
            if(dnaArray[i] == 'T')
                result[i] = 'A';
            if(dnaArray[i] == 'G')
                result[i] = 'C';
            if(dnaArray[i] == 'C')
                result[i] = 'G';
        }
        return new String(result);
    }


}

