import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

/**
 * @konrad
 * @04/2020
 */
public class ParsingWeatherData {
    
    public static CSVRecord coldestHourInFile(CSVParser parser){
        //This method returns the CSVRecord with the coldest temperature in the file and thus all the information about the coldest temperature
        CSVRecord lowestSoFar = null;
        for(CSVRecord currentRow: parser){
            if(lowestSoFar == null){
                lowestSoFar = currentRow;
            }
            else{
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double largest = Double.parseDouble(lowestSoFar.get("TemperatureF"));
                if(currentTemp < largest){
                    lowestSoFar = currentRow;
                }
            }
        }
        return lowestSoFar;
    }
    
    public static String fileWithColdestTemperature(){
        //This method should return a string that is the name of the file from selected files that has the coldest temperature.
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestSoFar = null;
        File fileName = null;
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            if(lowestSoFar == null){
                lowestSoFar = coldestHourInFile(fr.getCSVParser());
            }else{
                double currentLowest = Double.parseDouble(coldestHourInFile(fr.getCSVParser()).get("TemperatureF"));
                if(currentLowest!= -9999 && currentLowest < Double.parseDouble(lowestSoFar.get("TemperatureF"))){
                    lowestSoFar = coldestHourInFile(fr.getCSVParser());
                    fileName = f;
                }
            }
        }
        return fileName.getAbsolutePath();
    }
    
    public static void testFileWithColdestTemperature(){
        //This method print coldest day in file, coldest temperature and date of coldest temperature.
        String fileWithColdestTemp = fileWithColdestTemperature();
        File f = new File (fileWithColdestTemp);
        FileResource fr = new FileResource(f);
        System.out.println("Coldest day was in file " + f.getName());
        System.out.println("Coldest Temperature is : " + coldestHourInFile(fr.getCSVParser()).get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        for(CSVRecord data: fr.getCSVParser()){
            System.out.println(data.get("DateUTC") + ": " + data.get("TemperatureF"));
        }
    }
    
    public static CSVRecord lowestHumidityInFile(CSVParser parser){
        // This method returns the CSVRecord that has the lowest humidity.
        CSVRecord lowestSoFar = null;
        for (CSVRecord currentRow : parser) {
            if (lowestSoFar == null) {
                lowestSoFar = currentRow;
            }
            else {
                try{
                double currentHumi = Double.parseDouble(currentRow.get("Humidity"));
                double lowestHumi = Double.parseDouble(lowestSoFar.get("Humidity"));
                if (currentHumi < lowestHumi) {
                    lowestSoFar = currentRow;
                }
                }catch(NumberFormatException e){
                    continue;
                }
            }
        }
        return lowestSoFar;  
    }
    
   
    
    public static void testlowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity")
                + " at " + csv.get("DateUTC"));
    } 
    
    public static CSVRecord lowestHumidityInManyDays() {
        // This method returns the CSVRecord that has the lowest humidity in many days.
        CSVRecord lowestSoFar = null; 
        DirectoryResource dr = new DirectoryResource();
         for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            if (lowestSoFar ==null) {
                lowestSoFar = currentRow;
            }
            else {
                double currentHumi = Double.parseDouble(currentRow.get("Humidity"));
                double lowestHumi = Double.parseDouble(lowestSoFar.get("Humidity"));
                if (currentHumi<lowestHumi) {
                    lowestSoFar = currentRow;
                }
            }        
        }
        return lowestSoFar;
    }
    
    public static void testLowestHumidityInManyDays() {
        CSVRecord lowestHumidity = lowestHumidityInManyDays();
        System.out.println("Lowest Humidity was " + lowestHumidity.get("Humidity") + " at " + lowestHumidity.get("DateUTC"));
    }
    
    public static double avarageTemperetaureInFile(CSVParser parser) {
        // This method returns avarage temperature in file.
        double sumTemp = 0.0, countedHour = 0.0;
        for (CSVRecord record : parser) {
        double temp = Double.parseDouble(record.get("TemperatureF"));
        sumTemp += temp;
        countedHour++;
       }
       double avTemp = sumTemp / countedHour;
       return avTemp;
    }

	
    public static void testAvarageTemperatureInFile() { 
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double result = avarageTemperetaureInFile(parser);
        System.out.println ("Avarage temperatur in file is" + result);
    }
    
    public static double avarageTemperetaureWithHighHumidity(CSVParser parser, int value) {
        //This method returns an average temperature of only those temperatures when the humidity was greater than or equal to the value.
        double sum = 0.0, count = 0.0;
        for (CSVRecord record : parser) {
            double temp = Double.parseDouble(record.get("TemperatureF"));
            int humidity = Integer.parseInt(record.get("Humidity"));
            if (humidity >= value) {
                sum += temp;
                count++;
            }
        }
    double avTemp = sum / count;
    return avTemp;
    }
  

    public static void testavarageTemperetaureWithHighHumidity() { 
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        int hum = 80;
        double avr = avarageTemperetaureWithHighHumidity(parser, hum); 
        if (Double.isNaN(avr)) {
            System.out.println ("No temperatures with that humidity"); }
        else {
            System.out.println(("Avarage Temp when high humidity is equal: ") + hum +(" is ") + avr);
        }  
    }

    
}
