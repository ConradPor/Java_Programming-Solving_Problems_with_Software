

/**
 Konrad
 03/2021
 */
public class Part3 {
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
    
    
      public String findGene(String dna, int where) {  
            
            int startIndex = dna.indexOf("ATG", where);
            if (startIndex == -1) {
                return "";
            }
            int taaIndex = findStopCodon(dna,startIndex, "TAA");
            int tagIndex = findStopCodon(dna,startIndex, "TAG");
            int tgaIndex = findStopCodon(dna,startIndex, "TGA");
            int temp = Math.min(taaIndex, tagIndex);
            
            int minIndex  = 0;
            if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)) {
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
  
     
        
        public int countGenes (String dna) {
            // This method returns an integer indicating how many times dna appears in string dna
        int count = 0;
        int startIndex =0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            count = 1 + count;
            startIndex = dna.indexOf(currentGene, startIndex) +
                    currentGene.length();
        }
        return count;
    }

    public void testCountGenes () {

        String dna = "ATGATCATAAGAAGATAATAGAGGGCCATGTAAATGATCATAAGAAGATAATAGAGGGCCATGT" +
                "AAATGATCATAAGAAGATAATAGAGGGCCATGTAAATGATCATAAGAAGATAATAGAGGGCCATGTAA";
        int cg  = countGenes(dna);
        System.out.println(cg);
    }
}
    

