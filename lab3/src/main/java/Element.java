public class Element {

    public float weight;
    public float price;

    public Element(float weight, float price) {
        this.weight = weight;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Element{" +
                "weight=" + weight +
                ", value=" + price +
                '}';
    }
}