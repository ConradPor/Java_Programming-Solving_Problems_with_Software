import edu.duke.*;

/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1{

public int findStopCodon(String dnaStr, int startIndex, String stopCodon){
        
            
            int currIndex = dnaStr.indexOf(stopCodon, startIndex+3);       // Find stopCodon starting from (startIndex + 3), currIndex
            while (currIndex != -1) {                                      // As long as currIndex is not equal to -1
               int diff = currIndex - startIndex;                          // Check if currIndex - startIndex is a multiple of 3
               if (diff % 3 == 0) {                                        // If so, currIndex is answer, return it
                   return currIndex;                                       // If not, update currIndex, looking for stopCodon again
                }                                                          // starting from currIndex + 1
                else {
                    currIndex = dnaStr.indexOf(stopCodon, currIndex + 1);  // so return dnaStr.length() ????
                }
            }
        return dnaStr.length();                                                          //return dnaStr.length();
        

}

public String findGene(String dna, int where) {  //Where show place when we start next Gene
    
    int startIndex = dna.indexOf("ATG", where);
    if (startIndex == -1) {
        return "";
    }
    int taaIndex = findStopCodon(dna,startIndex, "TAA");
    int tagIndex = findStopCodon(dna,startIndex, "TAG");
    int tgaIndex = findStopCodon(dna,startIndex, "TGA");
    int temp = Math.min(taaIndex, tagIndex);
    
    int minIndex  = 0;
    if (taaIndex == -1 ||//int minIndex = Math.min(temp, tgaIndex);minIndex == dna.length()){return "";
       (tgaIndex != -1 && tgaIndex < taaIndex)) {
            minIndex = tgaIndex;
        }
        else {
            minIndex = taaIndex;
        }

    if (minIndex == -1 || 
    (tagIndex != -1 && tagIndex < minIndex)) {
        minIndex = tagIndex;
    }
 
    if (minIndex == dna.length()){
        return "";
    }

    return dna.substring(startIndex,minIndex + 3);
}



public void testFindGene() {
        String dna = "GTTAATGTAGCTTAAACCTTTAAAGCAAGGCACTGAAAATGCCTAGATGA";
        System.out.println("Gene: " + findGene(dna, 0));
        
        dna = "GTGAGCTCACTCCATAGACACAAAGGTTTGGTCCTGGCCTTCTTATTAGT";
        System.out.println("Gene: " + findGene(dna, 0));
        
        dna = "TTTCAGTGAGCTTACACATGCAAGTATCCGCGCGCCAGTGAAAATGCCCT";
        System.out.println("Gene: " + findGene(dna, 0));
        
        dna = "TCAAATCATTACTGACCATAAAGGAGCGGGTATCAAGCACACACCTATGT";
        System.out.println("Gene: " + findGene(dna, 0));
        
        dna = "AGCTCACAACACCTTGCTTAGCCACACCCCCACGGGATACAGCAGTGATA";
        System.out.println("Gene: " + findGene(dna, 0));
    }

public void printAllGenes(String dna) {
    int startIndex =0;
    //Set startIndex to 0
    while (true) {
    //Repeat the followin steps
    String currentGene = findGene(dna, startIndex);
    //Find the next gene after startIndex
    if (currentGene.isEmpty()) {
        break;
    }
    //If no gene was found, leave this loop
    System.out.println(currentGene);
    //Print that gene out
    startIndex = dna.indexOf(currentGene, startIndex) +
    currentGene.length();
    //Set startIndex to just past the end of the gene
}
}
public void testOn(String dna) {
    System.out.println("Testing printAllGenes on" + dna);
    printAllGenes(dna);
}

public void test() {
    testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
    testOn("");
    testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
}
}       
