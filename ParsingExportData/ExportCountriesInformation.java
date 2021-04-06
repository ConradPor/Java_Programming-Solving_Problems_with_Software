
/**
 * @Konrad
 * @04/2020
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class ExportCountriesInformation {
    public String countryInfo(CSVParser parser, String countryOfInterest) {
        for (CSVRecord record : parser) {
            String country = record.get("Country");
            if (country.contains(countryOfInterest)) {
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                String all = country+ " exports: " + exports + "of whole values: "+ value;
                return all;
            }
        }
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportitem1, String exportitem2) {
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportitem1) && export.contains(exportitem2)) {
                String country = record.get("Country");
                System.out.print(country + (" "));    
               }
            
       }
       System.out.println(" are countries which exports " + exportitem1 + " and " + exportitem2);
    }

    public int numberOfExporters(CSVParser parser, String exportitem) {
        int numOfCountry = 0;
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportitem)){
                numOfCountry++;
            
            }
        }
        return numOfCountry;
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
        String avalues = record.get("Value (dollars)");
        if (avalues.length() > amount.length()) {
            String country = record.get("Country");
            System.out.println(country + ": " + avalues);
            }
      
        }
    }        
            
        
    public void infoAboutCountry() {
        FileResource fr = new FileResource("exportdata.csv");
        CSVParser parser = fr.getCSVParser();
        
        
        System.out.println(countryInfo(parser, "Germany"));  //print method country info
        
        
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "meat","gold");
        
        parser = fr.getCSVParser();
        int numOfCountry = numberOfExporters(parser, "oil");
        System.out.println("It's " + numOfCountry + " countries, which exports oil");
        
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }
}
