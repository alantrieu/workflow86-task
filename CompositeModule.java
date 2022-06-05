import java.util.ArrayList;
import java.util.List;

public class CompositeModule extends Module {

    private List<Module> dependencies = new ArrayList<Module>();

    public CompositeModule(String ModuleID) {
        super(ModuleID);
    }

    public void addDependency(Module m) {
        dependencies.add(m);
    }

    public void removeDependency(Module m) {
        dependencies.remove(m);
    }

    @Override
    /*
     * returns IDs of dependencies
     */
    public List<String> getDependencies() {
        List<String> strDependencies = new ArrayList<String>();
        try {
            for (Module child : dependencies) {
                if (child instanceof CompositeModule) { // if composite, we check their children first
                    strDependencies.addAll(child.getDependencies());
                } 
                strDependencies.add(child.getModuleID());
            }
            return strDependencies;
        } catch (StackOverflowError e) {
            System.out.println("Error");
        }
        return strDependencies;
    }

    // getter
    @Override
    public List<Module> getDependenciesList() {
        return this.dependencies;
    }
}
