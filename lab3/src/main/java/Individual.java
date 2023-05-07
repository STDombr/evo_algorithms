import java.util.Arrays;

public class Individual {

    public int[] genes;

    public static int lenght;
    public int price;
    public float weightOfSolution;
    public static float maxCapacity;
    public static Element[] items;

    public Individual(int lenght, float maxCapacity, Element[] items) {
        Individual.maxCapacity = maxCapacity;
        Individual.lenght = lenght;
        Individual.items = items;
    }

    public Individual(int[] individ) {
        this.genes = individ;
        int currentPrice = 0;
        float currentWeight = 0;
        for (int i = 0; i < individ.length; i++) {
            if (genes[i] == 1) {
                currentPrice += items[i].price;
                currentWeight += items[i].weight;
            }
        }
        if (currentWeight > maxCapacity) {
            price = 0;
            weightOfSolution = currentWeight;
        } else {
            price = currentPrice;
            weightOfSolution = currentWeight;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Individual individ = (Individual) o;
        return Arrays.equals(genes, individ.genes);
    }

    @Override
    public String toString() {
        return "genes=" + Arrays.toString(genes) +
                ", price=" + price +
                ", weightOfSolution=" + weightOfSolution +
                '}';
    }
}