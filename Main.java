import java.util.ArrayList;
import java.util.List;

// just here to test examples out
public class Main {

    public static void main (String[] args) {

        LeafModule B = new LeafModule("B");
        LeafModule D = new LeafModule("D");
        LeafModule E = new LeafModule("E");
        LeafModule F = new LeafModule("F");
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
