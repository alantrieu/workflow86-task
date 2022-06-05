import java.util.ArrayList;
import java.util.List;

public class CompositeModule implements Module {

    private String ModuleID;
    private List<Module> dependencies = new ArrayList<Module>();

    public CompositeModule(String ModuleID) {
        this.ModuleID = ModuleID;
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
        for (Module child : dependencies) {
            if (child instanceof CompositeModule) { // if composite, we check their children first
                strDependencies.addAll(child.getDependencies());
            } 
            strDependencies.add(child.getModuleID());
        }
        return strDependencies;
    }

    @Override
    // getter
    public String getModuleID() {
        return this.ModuleID;
    }

    // no setter
}
