import java.util.Random;

public class Population {

    public Individual[] initialPopulation;
    public Individual[] firstGeneration;
    public static final int populationLength = 10;
    public static final double mutationRate = 0.05;

    public Population() {

        initialPopulation = new Individual[populationLength];

        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < populationLength; i++) {
            int[] genes = new int[Individual.lenght];
            float currentWeight = 0;

            do {
                int currentItem = rand.nextInt(Individual.lenght);
                if (currentWeight + Individual.items[currentItem].weight > Individual.maxCapacity)
                    break;
                else {
                    genes[currentItem] = 1;
                    currentWeight += Individual.items[currentItem].weight;
                }

            } while (currentWeight < Individual.maxCapacity);
            initialPopulation[i] = new Individual(genes);
        }

        System.out.println("Initial Population:");
        for (int i = 0; i < populationLength; i++) {
            System.out.println((i + 1) + " " + initialPopulation[i]);
        }
        System.out.println();

        firstGeneration = new Individual[populationLength];
        firstGeneration = initialPopulation;

        Individual[] approximate = createNewPopulation();
        System.out.println("Approximate solution: " + firstGeneration[getIndexOfBestFit(approximate)]);
    }

    public double totalPrice(Individual[] population) {
        int total = 0;
        for (Individual i : population)
            total += i.price;
        return total;
    }

    public double[] cumulativeProbability(Individual[] population) {
        double[] cumulativeProbability = new double[population.length];
        double total = totalPrice(population);
        double cumulatedProbability = 0;
        for (int i = 0; i < population.length; i++) {
            cumulatedProbability += population[i].price;
            cumulativeProbability[i] = cumulatedProbability / total;
        }
        return cumulativeProbability;
    }

    public int getIndexOfLeastFit(Individual[] population) {
        int minimum = population[0].price;
        int index = 0;
        for (int i = 0; i < population.length; i++) {
            if (minimum > population[i].price) {
                minimum = population[i].price;
                index = i;
            }
        }
        return index;
    }

    public int getIndexOfBestFit(Individual[] population) {
        int max = population[0].price;
        int index = 0;
        for (int i = 0; i < population.length; i++) {
            if (max < population[i].price) {
                max = population[i].price;
                index = i;
            }
        }
        return index;
    }


    public Individual selectParent() {
        double[] probableParents = cumulativeProbability(firstGeneration);
        double number1 = Math.random();
        if (number1 < probableParents[0])
            return firstGeneration[0];

        else {
            for (int i = 0; i < firstGeneration.length - 1; i++) {
                if (number1 > probableParents[i] && number1 < probableParents[i + 1]) {
                    return firstGeneration[i + 1];
                }
            }
        }
        return firstGeneration[firstGeneration.length - 1];
    }

    public Individual[] selection() {
        Individual[] parents = new Individual[2];
        parents[0] = selectParent();
        do {
            parents[1] = selectParent();
        } while (parents[0] == parents[1]);
        return parents;
    }

    public Individual[] crossover(Individual[] parents) {
        Individual[] children = new Individual[2];
        int[] child1 = new int[Individual.lenght];
        int[] child2 = new int[Individual.lenght];
        int pivot = Individual.lenght / 2;
        for (int i = 0; i < Individual.lenght; i++) {
            if (i <= pivot) {
                child1[i] = parents[0].genes[i];
                child2[i] = parents[1].genes[i];
            } else {
                child1[i] = parents[1].genes[i];
                child2[i] = parents[0].genes[i];
            }
        }
        child1 = mutation(child1);
        child2 = mutation(child2);
        children[0] = new Individual(child1);
        children[1] = new Individual(child2);
        return children;
    }

    public int[] mutation(int[] individArray) {
        double randomNumber = Math.random();
        if (randomNumber <= mutationRate) {
            Random rand = new Random(System.currentTimeMillis());
            int chromosomeToBeReplaced = rand.nextInt(individArray.length);
            if (individArray[chromosomeToBeReplaced] == 0)
                individArray[chromosomeToBeReplaced] = 1;
            else
                individArray[chromosomeToBeReplaced] = 0;
        }
        return individArray;
    }

    public Individual[] createNewPopulation() {
        for (int j = 0; j < populationLength; j++) {
            for (int i = 0; i < populationLength / 2; i++) {
                Individual[] parents = selection();
                Individual[] children = crossover(parents);
                if (children[0].price > firstGeneration[getIndexOfLeastFit(firstGeneration)].price)
                    firstGeneration[getIndexOfLeastFit(firstGeneration)] = children[0];
                if (children[1].price > firstGeneration[getIndexOfLeastFit(firstGeneration)].price)
                    firstGeneration[getIndexOfLeastFit(firstGeneration)] = children[1];
            }
        }
        return firstGeneration;
    }
}