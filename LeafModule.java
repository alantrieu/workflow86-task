import java.util.ArrayList;
import java.util.List;

public class LeafModule implements Module {

    private String ModuleID;

    public LeafModule (String ModuleID) {
        this.ModuleID = ModuleID;
    }

    @Override
    /*
     * simply returns an empty list, since leaf modules have no dependencies
     */
    public List<String> getDependencies() {
        return new ArrayList<String>();
    }

    @Override
    // getter
    public String getModuleID() {
        return this.ModuleID;
    }

    // no setter
}
