import java.util.Random;

public class Main {

    private final Random rn = new Random();

    private Population newPopulation;

    private final int[] indexes = new int[Population.POPULATION_SIZE];

    public static void main(String[] args) {

        Main main = new Main();

        for (int i = 0; i < Population.POPULATION_SIZE; i++) {
            main.indexes[i] = i;
        }

        Population population = new Population();
        population.initializePopulation();

        System.out.println("First population: " + population);

        main.newPopulation = main.roulette(population);

        for (int i = 0; i < 500; i++) {

            main.crossover();

            if (main.rn.nextInt() % 100 == 1) {
                main.mutation();
            }

            main.updateValues();

            main.newPopulation.calculateMaxFx();
        }

        System.out.print("Result:\n");
        System.out.print(main.newPopulation.getMaxIndividual());

    }

    public Population roulette(Population firstPopulation) {

        Population secondPopulation = new Population();
        Individual[] individuals = new Individual[Population.POPULATION_SIZE];

        for (int i = 0; i < Population.POPULATION_SIZE; i++) {
            individuals[i] = new Individual();
            individuals[i].setGenes(firstPopulation.findIndividual(rn.nextFloat()));
        }

        secondPopulation.initializePopulation(individuals);

        return secondPopulation;

    }

    public void crossover() {

        for (int i = 0; i < 8; i++) {
            int index = rn.nextInt(Population.POPULATION_SIZE);
            if (i != index) {
                int temp = indexes[i];
                indexes[i] = indexes[index];
                indexes[index] = temp;
            }
        }

        for (int j = 0; j < 3; j++) {
            if (rn.nextFloat() <= 0.75) {
                int crossOverPoint = rn.nextInt(Individual.GENE_LENGTH);

                for (int i = 0; i < crossOverPoint; i++) {
                    int temp = newPopulation.getIndividuals()[indexes[j * 2]].genes[i];
                    newPopulation.getIndividuals()[indexes[j * 2]].genes[i] = newPopulation.getIndividuals()[indexes[j * 2 + 1]].genes[i];
                    newPopulation.getIndividuals()[indexes[j * 2 + 1]].genes[i] = temp;
                }
            }
        }

    }

    public void mutation() {

        int mutationPoint = rn.nextInt(Individual.GENE_LENGTH);
        int individualPoint = rn.nextInt(Population.POPULATION_SIZE);

        if (newPopulation.getIndividuals()[individualPoint].genes[mutationPoint] == 0) {
            newPopulation.getIndividuals()[individualPoint].genes[mutationPoint] = 1;
        } else {
            newPopulation.getIndividuals()[individualPoint].genes[mutationPoint] = 0;
        }

    }

    public void updateValues() {
        for (int i = 0; i < Population.POPULATION_SIZE; i++) {
            newPopulation.getIndividuals()[i].update();
        }
    }

}