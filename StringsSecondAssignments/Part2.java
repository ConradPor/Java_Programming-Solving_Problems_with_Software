
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {

public Part2() {
    }

    public static void main(String[] args) {
        System.out.println(howMany("GAA", "ATGAACGAATTGAATC"));
        System.out.println(howMany("AA", "ATAAAA"));
        System.out.println(howMany("SS", "SSASSBBCCSSXXSXXSS"));
    }  
    
    public static int howMany(String a, String b) {
        int count = 0;
        
        for(int found = 0; found != -1; ++count) {
        found = b.indexOf(a, found);
        if (found == -1) {
         break;
    }
    
    found += a.length();
  }
return count;
}
  
 
}
