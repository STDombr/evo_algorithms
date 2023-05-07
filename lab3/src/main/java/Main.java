import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        float maxCapacity = 0;
        int numberOfObjects = 0;
        Element[] items = null;

        try (BufferedReader br = new BufferedReader(new FileReader("./f5_l-d_kp_15_375"))) {

            String sCurrentLine = br.readLine();
            String[] arr = sCurrentLine.split(" ");

            numberOfObjects = Integer.parseInt(arr[0]);
            maxCapacity = Float.parseFloat(arr[1]);
            items = new Element[numberOfObjects];

            for (int i = 0; i < numberOfObjects; i++) {
                sCurrentLine = br.readLine();
                arr = sCurrentLine.split(" ");
                items[i] = new Element(Float.parseFloat(arr[0]), Float.parseFloat(arr[1]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        new Individual(numberOfObjects, maxCapacity, items);

        Population getApproximateSolution = new Population();
    }

}
