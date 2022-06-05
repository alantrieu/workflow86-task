import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// the main controller / interface for creating and displaying modules
public class ModuleSystem {
    private List<Module> modules = new ArrayList<Module>();

    /**
     * creates a new module using ModuleID and adds it to the existing modules list
     * if dependencies is non-empty, also assumes that all elements are valid
     * 
     * @pre ModuleID is a valid ID that is not currently being used
     * @pre if dependencies is non-empty, elements are IDs to existing modules
     * @param ModuleID
     * @param dependencies
     */
    public void createModule(String ModuleID, List<String> dependencies) {
        // normally I would do some type checking to see if ModuleID is valid, but main problem is creating getModuleDependencies(), so I won't create a rigid system

        Module newModule;
        if (dependencies.isEmpty()) {                        // i.e. leaf module
            newModule = new LeafModule(ModuleID);
        } else {                                             // otherwise composite module
            newModule = new CompositeModule(ModuleID);
        }

        // add dependencies 
        for (String dependencyID : dependencies) {
            Module module = findByID(dependencyID);
            ((CompositeModule)newModule).addDependency(module); 
        }

        modules.add(newModule);
    }

    /**
     * helper function that identifies a module via ID
     * 
     * @pre ModuleID is a valid ID to an existing module
     * @param ModuleID
     * @return Module
     */
    public Module findByID(String ModuleID) {
        for (Module module : modules) {
            if (module.getModuleID().equals(ModuleID)) {
                return module;
            }
        }

        // create a dummy module
        // due to assumptions, we should NEVER get here
        return new LeafModule(ModuleID);
    }

    /**
     * removes dependency
     * assumes valid arguments (i.e. parent is a composite module, and both exist)
     * 
     * @param parentID
     * @param childID
     */
    public void removeModule(String parentID, String childID) {
        Module parent = findByID(parentID);
        Module child = findByID(childID);

        ((CompositeModule)parent).removeDependency(child);
    }

    /**
     * adds dependency
     * assumes valid arguments (i.e. parent is a composite module, and both exist)
     * 
     * @param parentID
     * @param childID
     */
    public void addModule(String parentID, String childID) {
        Module parent = findByID(parentID);
        Module child = findByID(childID);

        ((CompositeModule) parent).addDependency(child);
    }

    // this is the main answer to the technical test 
    // everything else is just setting up for this method
    public List<String> getModuleDependencies(String ModuleID) {
        // find ModuleID
        Module rootModule = findByID(ModuleID);
        
        // ** below doesn't work; it changes the order for some reason
        // now check for duplicates by taking advantage of hashsets 
        // List<String> uniqueDependencies = new ArrayList<>(
        //     new HashSet<>(rootModule.getDependencies())
        // );

        // removing duplicates using streams instead
        // taken from
        // https://www.java67.com/2018/06/how-to-remove-duplicates-from-stream-in-java8-distinct-example.html
        List<String> uniqueDependencies = rootModule
        .getDependencies()
        .stream()
        .distinct()
        .collect(Collectors.toList());
        
        // check recursion loop


        return uniqueDependencies;
    }
}
