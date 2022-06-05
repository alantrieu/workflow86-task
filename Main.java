// just here to test examples out
// ignore
public class Main {

    public static void main (String[] args) {

        LeafModule H = new LeafModule("H");
        LeafModule I = new LeafModule("I");

        CompositeModule G = new CompositeModule("G");
        G.addDependency(H);
        G.addDependency(I);

        for (String dependency : G.getDependencies()) {
            System.out.println(dependency);
        }
    }
}
