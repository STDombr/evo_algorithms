class Population {

    public static final int POPULATION_SIZE = 8;

    private Individual[] individuals = new Individual[POPULATION_SIZE];

    private float maxFx = -1000000;
    private Individual maxIndividual = new Individual();

    public void initializePopulation() {

        float temp = 0;

        for (int i = 0; i < POPULATION_SIZE; i++) {
            individuals[i] = new Individual();
            temp += individuals[i].getFx();
        }

        for (int i = 0; i < POPULATION_SIZE; i++) {
            individuals[i].calcfnorm(temp);
        }

    }

    public void initializePopulation(Individual[] individuals) {

        float temp = 0;
        this.individuals = individuals;

        for (int i = 0; i < POPULATION_SIZE; i++) {
            temp += individuals[i].getFx();
        }

        for (int i = 0; i < POPULATION_SIZE; i++) {
            individuals[i].calcfnorm(temp);
        }

    }

    public int[] findIndividual(float r) {

        float sum = 0;

        for (int i=0; i < POPULATION_SIZE; i++) {
            if (sum <= r && r <= sum + individuals[i].getFnorm()) {
                return individuals[i].getGenes();
            } else {
                sum += individuals[i].getFnorm();
            }
        }

        return individuals[POPULATION_SIZE - 1].getGenes();

    }

    public Individual[] getIndividuals() {
        return individuals;
    }

    public void setIndividuals(Individual[] individuals) {
        this.individuals = individuals;
    }

    public void calculateMaxFx() {
        for (int i = 0; i < POPULATION_SIZE; i++) {
            if (individuals[i].getFx() > maxFx) {
                maxFx = individuals[i].getFx();
                maxIndividual.setGenes(individuals[i].getGenes());
            }
        }
    }

    public Individual getMaxIndividual() {
        return maxIndividual;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("[");

        for (int i = 0; i < POPULATION_SIZE; i++) {
            builder.append('\n').append(individuals[i].toString());
        }

        builder.append("]");

        return builder.toString();
    }
}