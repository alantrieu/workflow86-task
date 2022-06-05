import java.util.List;

public interface Module {

    /*
    * @pre ModuleID is a valid and existing module
    * @param ID of relevant module
    * @returns dependency in correct order (postorder traversal)
     */
    // public List<String> getModuleDependencies(String ModuleID);

    public List<String> getDependencies();
    public String getModuleID();
}