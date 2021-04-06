import edu.duke.*;
import java.lang.Math;

public class Part3 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int index = 0;
        while(true) {
            index = dna.toUpperCase().indexOf(stopCodon, startIndex + 3);
            if (index == -1 || (index - startIndex) % 3 == 0) {
                break;
            }
            startIndex += 3;
        }
        
        if (index != -1) {
            return index;
        } else {
            return dna.length();            
        }
    }
    
    public String findGene(String dna, int start) {
        //This method looks for the genome in the dna string
        final String START_CODON = "ATG";
        int startIndex = dna.toUpperCase().indexOf(START_CODON, start);

        if (startIndex == -1) {
            return "";
        }
        
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        
        int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        
        if (minIndex == dna.length()) {
            return "";
        } else {
            return dna.substring(startIndex, minIndex + 3);
        }
    }
    
    public StorageResource getAllGenes(String dna) {
        // This method storage all find genes
        int start = 0;
        StorageResource geneList = new StorageResource();
        
        while (true) {
            String gene = findGene(dna, start);
            
            if (gene.isEmpty()) {
                break;
            }
            
            geneList.add(gene);
            
            start = dna.indexOf(gene, start) + gene.length();
        }
        
        return geneList;
    }
    
    public double cgRatio(String dna) {
        //This method return ratio occurences of c or g character to dna length
        int totalOccurences = 0;
        for (int i = 0; i < dna.length(); i++) {
            if (dna.toUpperCase().charAt(i) == 'C' || dna.toUpperCase().charAt(i) == 'G') {
                totalOccurences++;
            }
         }
         
         return (double) totalOccurences / dna.length();
    }
    
    private void processGenes(StorageResource sr) {
        //This method processes all the strings in sr to find out information about them.
        int lengthCount = 0;
        int cgRatioCount = 0;
        int longestLength = Integer.MIN_VALUE;
        
        for (String gene : sr.data()) {
            int currentLength = gene.length();
            double cgRatio = cgRatio(gene);
            
            System.out.println("CG RATIO: " + cgRatio);
            if (currentLength > 60) {
                System.out.println("Longer than 60 characters: " + gene);
                lengthCount++;
            }
            
            if (cgRatio > 0.35) {
                System.out.println("C-G ratio higher than 0.35: " + gene);
                cgRatioCount++;
            }
            
            longestLength = Math.max(longestLength, currentLength);
        }
        
        System.out.println("Total genes: " + sr.size());
        System.out.println("Total gene longer than 60 characters: " + lengthCount);
        System.out.println("Total gene with C-G ratio higher than 0.35: " + cgRatioCount);
        System.out.println("Length of longest dna: " + longestLength);
        //System.out.println("CTG in all Gene:" 
    }
    
    public void testProcessGenes() {
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        
        StorageResource geneList = getAllGenes(dna);
        processGenes(geneList);
    }
    
  
    
}