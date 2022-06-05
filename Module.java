import java.util.List;

public abstract class Module {

    private String ModuleID;
    private boolean visiting;
    private boolean visited;

    public Module(String ModuleID) {
        this.ModuleID = ModuleID;
    }

    /*
    * @pre ModuleID is a valid and existing module
    * @param ID of relevant module
    * @returns dependency in correct order (postorder traversal)
    */
    public abstract List<String> getDependencies();

    // getters and setters

    public String getModuleID() {
        return this.ModuleID;
    }

    // no setter for moduleID

    public boolean getVisiting() {
        return this.visiting;
    }

    public void setVisiting(boolean visiting) {
        this.visiting = visiting;
    }

    public boolean getVisited() {
        return this.visited;
    }
    
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public abstract List<Module> getDependenciesList();

}