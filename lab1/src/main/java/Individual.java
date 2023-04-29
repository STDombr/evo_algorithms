import java.util.Arrays;
import java.util.Random;

class Individual {

    public static final int GENE_LENGTH = 8;

    public int[] genes = new int[GENE_LENGTH];

    private int x;
    private float fx;
    private float fnorm;

    public Individual() {
        Random r = new Random();
        int[] temp = new int[GENE_LENGTH];

        for (int i = 0; i < GENE_LENGTH; i++) {
            temp[i] = Math.abs(r.nextInt() % 2);
        }

        setGenes(temp);
    }

    public int[] getGenes() {
        return genes;
    }

    public void setGenes(int[] genes) {
        this.genes = genes;
        update();
    }

    public float getFx() {
        return fx;
    }

    public float getFnorm() {
        return fnorm;
    }

    public void update() {
        x = Integer.parseInt(genesToString(), 2);
        fx = (float) ((2 * x * x - 5 * x + 13) ^ 2 * -1) / 256;
    }

    public void calcfnorm(float sum) {
        fnorm = fx / sum;
    }

    public String genesToString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < GENE_LENGTH; i++) {
            builder.append(genes[i]);
        }

        return builder.toString();
    }

    @Override
    public String toString() {
        return "genes=" + genesToString() + ", x=" + x + ", f(x)=" + fx;
    }
}