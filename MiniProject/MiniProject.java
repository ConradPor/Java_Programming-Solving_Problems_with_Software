import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;


public class MiniProject {


    
    public void readOneFile(int year) {
        String fname = "data/yob" + year + ".txt";
        FileResource fr = new FileResource(fname);
       CSVParser parser = fr.getCSVParser(false);
       for(CSVRecord rec : parser) {
            String name = rec.get(0);
            String gender = rec.get(1);
            
       }
    }

    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
                if (numBorn <= 100) {
                    System.out.println("Name " + rec.get(0) +
                                "Gender " + rec.get(1) +
                               " Num Born " + rec.get(2));
                }
        }
    }

    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
            }
            else {
                totalGirls += numBorn;
            }
        }
        System.out.println("total births =" + totalBirths);
        System.out.println("total girls =" + totalGirls);
        System.out.println("total boys =" + totalBoys);
    }

    public void testTotalBirths () {
        FileResource fr= new FileResource();
        totalBirths(fr);
    }

    public void totalNames (FileResource fr) {
        int numOfBoys = 0;
        int numOfGirls = 0;
        int numOfNames = 0; 
        for (CSVRecord rec : fr.getCSVParser(false)) {
            String male = rec.get(1);
            if (male.contains("M")){
                numOfBoys++;
            }
            else {
                numOfGirls++;
            
            }    
            numOfNames = numOfBoys + numOfGirls;
        } 
        System.out.println("Total boys names = " + numOfBoys + " total girls names = " + numOfGirls + " total = " + numOfNames);
    }


    public void testTotalNames () {
        FileResource fr= new FileResource("us_babynames_by_year/yob1897.csv");
        totalNames(fr);
    }

    public long getRank (int year, String name, String gender) {
        long rank = -1;
        FileResource fr= new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        for(CSVRecord record : parser) {
            String currName = record.get(0);
            String currGender = record.get(1);
            if(currGender.equals(gender) && currName.equals(name)) {
                rank = record.getRecordNumber();
            }
        }
        return rank;
    }

    public void testGetRank(){
        System.out.println(getRank(1960, "Emily", "F"));    //2
        System.out.println(getRank(1971, "Frank", "M"));    //-1
        System.out.println(getRank(2012, "Ava", "F"));    //5
    }    
    
    
    public String getName(int year, int rank, String gender) {
        String name = "";
        FileResource fr = new FileResource("us_babynames_by_year/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        for(CSVRecord record : parser) {
            long currRank = record.getRecordNumber();
            String currGender = record.get(1);
            String currName = record.get(0);
                if(currRank == rank && currGender.equals(gender)) {
                    name = currName;
            }
        }

        if(name != "") {
            return name;
        } 
        else {
            return "NO NAME";
        }
    }

    public void testGetName(){
        System.out.println(getName(1980, 350,"F"));   
    }


    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
    String newName = "";
    long rank = 0;
    FileResource fr = new FileResource("us_babynames_by_year/yob" + year + ".csv");
    CSVParser parser = fr.getCSVParser(false);
    FileResource newFr= new FileResource("us_babynames_by_year/yob" + newYear + ".csv");
    CSVParser newParser = fr.getCSVParser(false);
        for(CSVRecord record : parser) {
           String currName = record.get(0);
           String currGender = record.get(1);    
           if(currGender.equals(gender) && currName.equals(name)) {
                rank = record.getRecordNumber();
           }
        }
    
        for (CSVRecord rec : newParser) {
            String currGender = rec.get(1);
            long newRank = rec.getRecordNumber();
                if(currGender.equals(gender) && rank == newRank) {
                    newName = rec.get(0);
            }
        }
        System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear);
    }

    public void testWhatIsNameInYear(){
        whatIsNameInYear("Helen", 1972, 2014, "F"); 
    }

    public int yearOfHighestRank(String name, String gender) {
        long highestRank = 0;
        int yearOfHighestRank = -1;
        String fileName = "";
        DirectoryResource dr = new DirectoryResource();
        
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);

            for(CSVRecord record : parser) {
                String currName = record.get(0);
                String currGender = record.get(1);

                if(currName.equals(name) && currGender.equals(gender)) {
                    long currRank = record.getRecordNumber();
                    
                    if(highestRank == 0) {
                        highestRank = currRank;
                        fileName = f.getName();
                    } 
                    else {
                        if(highestRank > currRank) {
                            highestRank = currRank;
                            fileName = f.getName();
                        }
                    }
                }
            }
        }

        fileName = fileName.replaceAll("[^\\d]", "");
        yearOfHighestRank = Integer.parseInt(fileName);

        return yearOfHighestRank;
    }

    public void testYearOfHighestRank(){
        
        System.out.println(yearOfHighestRank("Mich", "M"));   
    }       //-1
        
    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        double rankTotal = 0.0;
        int howMany = 0;
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);
            for(CSVRecord record : parser) {
                String currName = record.get(0);
                String currGender = record.get(1);
                if(currName.equals(name) && currGender.equals(gender)){
                    long currRank = record.getRecordNumber();
                    rankTotal += (double)currRank;
                    howMany += 1;
                }
            }
        }
        double avgRank = rankTotal / (double)howMany;
        return avgRank;
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int numBorn = 0;
        long rank = getRank(year, name, gender);
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        for(CSVRecord record : parser) {
            int currBorn = Integer.parseInt(record.get(2));
            String currGender = record.get(1);
            long currRank = record.getRecordNumber();
            if(gender.equals(currGender) && rank > currRank) {
                numBorn += currBorn;
            }
        }
        return numBorn;
    }
    
    public void testGetTotalBirthsRankedHigher(){
        System.out.println(getTotalBirthsRankedHigher(1990, "Emily", "M"));   
        System.out.println(getTotalBirthsRankedHigher(1990, "Drew", "F"));    
    }  

    public void testTotalBirth() {
        FileResource fr = new FileResource("yob1900.csv");
        totalBirths(fr);

        long rank = getRank(2012, "Mason", "M");
        System.out.println("Rank is: " + rank);

        // String name = getName(2012, 10, "M");
        // System.out.println("Name: " + name);

        // whatIsNameInYear("Isabella", 2012, 2014, "F");

        // System.out.println(yearOfHighestRank("Mason", "M"));
        
        //System.out.println(getAverageRank("Mason", "M"));
        
        // System.out.println(getTotalBirthsRankedHigher(2012, "Ethan", "M"));
    }

}



