import java.util.ArrayList;
import java.util.List;

public class LeafModule extends Module {

    public LeafModule (String ModuleID) {
        super(ModuleID);
    }

    @Override
    /*
     * simply returns an empty list, since leaf modules have no dependencies
     */
    public List<String> getDependencies() {
        return new ArrayList<String>();
    }

    /**
     * no dependencies; return empty list
     */
    @Override
    public List<Module> getDependenciesList() {
        return new ArrayList<>();
    }
}
