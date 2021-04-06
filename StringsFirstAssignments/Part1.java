
/**
 * Konrad 
 * 04/2020 
 */
public class Part1 {


    public String findSimpleGene(String dna){

        String result = "";
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1)  //no ATG
        {
        return "";
        }
    
        int stopIndex = dna.indexOf("TAA", startIndex+3);
        if (stopIndex == -1) //no TAA
        {
        return "";
        }
        result = dna.substring(startIndex, stopIndex+3);
        if ((stopIndex - startIndex) % 3 == 0)
        {
            return result;
        }
        else
        {
            return "";
        }
    }

    public void testSimpleGene(){
        String dna = "ATGTGATAA";
        System.out.println("DNA strand is " + dna);
        String gene = findSimpleGene(dna);
        System.out.println("Gene is  " + gene);
        
        dna = "ATGTGATTATAA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is  " + gene);
        
        dna = "ATGTGGCGTAATTAATGGT";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is  " + gene);
        
        dna = "ATGTGGCGTGTAGTAAATAATGGT";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is  " + gene);
    }
}


    
    