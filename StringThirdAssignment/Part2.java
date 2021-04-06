import edu.duke.*;

/**
 * @Konrad
 * @04/2020
 */
public class Part2{

public double cgRatio(String dna){
    //This method return ratio occurences of c or g character to dna length
    int totalOccurences = 0;
    for (int i=0; i<dna.length(); i++){
    if (dna.toUpperCase().charAt(i) == 'C' || dna.toUpperCase().charAt(i) == 'G') {
        totalOccurences++;
    }   
}
return(double) totalOccurences / dna.length();
}



public void testcgRatio() {
        String dna = "GTTAATGTAGCTTAAACCTTTAAAGCAAGGCACTGAAAATGCCTAGATGA";
        System.out.println("cgRatio on: "+ dna + cgRatio(dna));
        
        dna = "GTGAGCTCACTCCATAGACACAAAGGTTTGGTCCTGGCCTTCTTATTAGT";
        System.out.println("cgRatio on: "+ dna + cgRatio(dna));
        
        dna = "TTTCAGTGAGCTTACACATGCAAGTATCCGCGCGCCAGTGAAAATGCCCT";
        System.out.println("cgRatio on: "+ dna + cgRatio(dna));
        
        dna = "TCAAATCATTACTGACCATAAAGGAGCGGGTATCAAGCACACACCTATGT";
        System.out.println("cgRatio on: "+ dna + cgRatio(dna));
        
        dna = "AGCTCACAACACCTTGCTTAGCCACACCCCCACGGGATACAGCAGTGATA";
        System.out.println("cgRatio on: "+ dna + cgRatio(dna));
    }

    public int countCTG(String dna) {
        //This method counts occurence of CTG in string dna 
        int totalOccurences = 0;
        int start = 0;
        while(true) {
            int index = dna.toUpperCase().indexOf("CTG", start);
            if (index == -1) {
                break;
            }
            totalOccurences++;
            start = index + 3;
        }
        return totalOccurences;
    }
    
    public void testCountCTG() {
        String dna = "CTGCCTGGCTGCTG";
        System.out.println("CTG occurences on " + dna + " = " + countCTG(dna));
    }
    
}       
