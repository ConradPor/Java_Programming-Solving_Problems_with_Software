import edu.duke.*;
import java.io.File;

public class PerimeterAssigmentRunner {

    public int getNumPoints(Shape s) {  //This method  returns an integer that is the number of points in Shape s.
        int nOfPoints = 0;
        for (Point p : s.getPoints()) {
            nOfPoints += 1;
        }
        return nOfPoints;
    }

    public double getAverageLength(Shape s) { // This method returns a number of type double that is the
        // calculated average of all the sides’ lengths in the Shape S.
        double length = getPerimeter(s);
        double numSides = (double) getNumPoints(s);
        double avgLength = length / numSides;
        return avgLength;
    }

    public double getLargestSide(Shape s) {  //This method returns a number of type double
        // that is the longest side in the Shape S.
        Point lastPoint = s.getLastPoint();
        double largestSide = 0.0;
        for (Point p : s.getPoints()) {
            double dist = lastPoint.distance(p);
            if (dist > largestSide) {
                largestSide = dist;
            }
            lastPoint = p;
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {  //This method returns a number of type double
        // that is the largest x value over all the points in the Shape s.
        Point lastPoint = s.getLastPoint();
        int lastPointX = lastPoint.getX();
        double largestX = (double) lastPointX;
        for (Point p : s.getPoints()) {
            int newX = p.getX();
            if (newX > largestX) {
                largestX = (double) newX;
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {  // This method creates a DirectoryResource
        // (so you can select multiple files) and then iterates over these files.
        DirectoryResource dr = new DirectoryResource();
        double larPerim = 0.0;
        FileResource largestFile = null;

        for (File f : dr.selectedFiles()) {
            FileResource file = new FileResource(f);
            Shape shape = new Shape(file);
            double perim = getPerimeter(shape);
            if (perim > larPerim) {
                larPerim = perim;
            }
        }
        return larPerim;
    }

    public String getFileWithLargestPerimeter() {

        DirectoryResource dr = new DirectoryResource();
        double larPerim = 0.0;
        File largestFile = null;

        for (File f : dr.selectedFiles()) {
            FileResource file = new FileResource(f);
            Shape shape = new Shape(file);
            double perim = getPerimeter(shape);
            if (perim > larPerim) {
                larPerim = perim;
                largestFile = f;
            }
        }

        return largestFile.getName();
    }

    public void testPerimeterMultipleFiles() {
        double lp = getLargestPerimeterMultipleFiles();
        System.out.println("Largest perimeter is: " + lp);
    }

    public void testFileWithLargestPerimeter() {
        String file = getFileWithLargestPerimeter();
        System.out.println("Largest perimeter file is: " + file);
    }

    public double getPerimeter(Shape s) {
        double totalPerim = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            totalPerim = totalPerim + currDist;
            prevPt = currPt;
        }
        return totalPerim;
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle() {
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0, 0));
        triangle.addPoint(new Point(6, 0));
        triangle.addPoint(new Point(3, 6));
        for (Point p : triangle.getPoints()) {
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = " + peri);
    }

    public void testPerimeter() {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int numPoints = getNumPoints(s);
        double averageLength = getAverageLength(s);
        double largestSide = getLargestSide(s);
        double largestX = getLargestX(s);

        System.out.println("perimeter = " + length);
        System.out.println("Number of Points: " + numPoints);
        System.out.println("Average Length: " + averageLength);
        System.out.println("Largest Side: " + largestSide);
        System.out.println("Largest X is: " + largestX);
        testPerimeterMultipleFiles();
        testFileWithLargestPerimeter();
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main(String[] args) {
        PerimeterAssigmentRunner pr = new PerimeterAssigmentRunner();
        pr.testPerimeter();
        pr.printFileNames();
        pr.triangle();
    }
}