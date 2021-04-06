import edu.duke.*;

/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1{

public int findStopCodon(String dnaStr, int startIndex, String stopCodon){
        
            
            int currIndex = dnaStr.indexOf(stopCodon, startIndex+3);       
            while (currIndex != -1) {                                      
               int diff = currIndex - startIndex;                          
               if (diff % 3 == 0) {                                        
                   return currIndex;                                       
                }                                                          
                else {
                    currIndex = dnaStr.indexOf(stopCodon, currIndex + 1);  
                }
            }
        return dnaStr.length();                                                       
        

}

public String findGene(String dna, int where) {  //This method looks for the genome in the dna string
    
    int startIndex = dna.indexOf("ATG", where);
    if (startIndex == -1) {
        return "";
    }
    int taaIndex = findStopCodon(dna,startIndex, "TAA");
    int tagIndex = findStopCodon(dna,startIndex, "TAG");
    int tgaIndex = findStopCodon(dna,startIndex, "TGA"); 
    int minIndex  = 0;
    if (taaIndex == -1 ||
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


public StorageResource getAllGenes(String dna) {
    // This method storage all find genes
    StorageResource geneList = new StorageResource();
    int startIndex = 0;
    while (true) {
        String currentGene = findGene(dna, startIndex);
        if (currentGene.isEmpty()) {
            break;
        }
        geneList.add(currentGene);
        startIndex = dna.indexOf(currentGene, startIndex);
                     currentGene.length();
    }
    return geneList;
}



public void testOn(String dna) {
    System.out.println("Testing printAllGenes on" + dna);
    getAllGenes(dna);
}

public void test() {
    testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
    testOn("");
    testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
}
}       
