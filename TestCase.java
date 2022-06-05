import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(value = Lifecycle.PER_CLASS)
public class TestCase {
    @Test
    public void testExample1() throws Exception {
        ModuleSystem moduleSystem = new ModuleSystem();
        moduleSystem.createModule("B", new ArrayList<>());
        moduleSystem.createModule("D", new ArrayList<>());
        moduleSystem.createModule("E", new ArrayList<>());
        moduleSystem.createModule("F", new ArrayList<>());
        moduleSystem.createModule("H", new ArrayList<>());
        moduleSystem.createModule("I", new ArrayList<>());

        // create some dependencies
        List<String> dependencies = Arrays.asList("H", "I");
        moduleSystem.createModule("G", dependencies);
        Assert.assertEquals(dependencies, moduleSystem.getModuleDependencies("G"));

        dependencies = Arrays.asList("E", "F", "G");
        moduleSystem.createModule("C", dependencies);
        Assert.assertEquals(Arrays.asList("E", "F", "H", "I", "G"), 
            moduleSystem.getModuleDependencies("C"));

        dependencies = Arrays.asList("B", "C", "D");
        moduleSystem.createModule("A", dependencies);
        Assert.assertEquals(Arrays.asList("B", "E", "F", "H", "I", "G", "C", "D"),
            moduleSystem.getModuleDependencies("A"));
    }

    @Test
    public void testExample2() throws Exception {
        ModuleSystem moduleSystem = new ModuleSystem();
        moduleSystem.createModule("B", new ArrayList<>());
        moduleSystem.createModule("D", new ArrayList<>());
        moduleSystem.createModule("E", new ArrayList<>());
        moduleSystem.createModule("F", new ArrayList<>());
        moduleSystem.createModule("H", new ArrayList<>());

        // create some dependencies
        List<String> dependencies = Arrays.asList("D");
        moduleSystem.createModule("I", dependencies);
        Assert.assertEquals(dependencies, moduleSystem.getModuleDependencies("I"));

        dependencies = Arrays.asList("H", "I");
        moduleSystem.createModule("G", dependencies);
        Assert.assertEquals(Arrays.asList("H", "D", "I"), moduleSystem.getModuleDependencies("G"));
        
        dependencies = Arrays.asList("E", "F", "G", "E");
        moduleSystem.createModule("C", dependencies);
        Assert.assertEquals(Arrays.asList("E", "F", "H", "D", "I", "G"),
            moduleSystem.getModuleDependencies("C"));

        dependencies = Arrays.asList("B", "C", "D");
        moduleSystem.createModule("A", dependencies);
        Assert.assertEquals(Arrays.asList("B", "E", "F", "H", "D", "I", "G", "C"),
                moduleSystem.getModuleDependencies("A"));
    }

    @Test public void testExample3() throws Exception {
        ModuleSystem moduleSystem = new ModuleSystem();
        moduleSystem.createModule("B", new ArrayList<>());
        moduleSystem.createModule("D", new ArrayList<>());
        moduleSystem.createModule("E", new ArrayList<>());
        moduleSystem.createModule("F", new ArrayList<>());
        moduleSystem.createModule("H", new ArrayList<>());
        
        // create some dependencies
        List<String> dependencies = Arrays.asList("D"); // placeholder
        moduleSystem.createModule("I", dependencies);

        dependencies = Arrays.asList("H", "I");
        moduleSystem.createModule("G", dependencies);

        dependencies = Arrays.asList("E", "F", "G", "E");
        moduleSystem.createModule("C", dependencies);

        dependencies = Arrays.asList("B", "C", "D");
        moduleSystem.createModule("A", dependencies);

        // change I to depend on C
        moduleSystem.addModule("I", "C");
        moduleSystem.removeModule("I", "D");

        Exception error = assertThrows(Exception.class, () -> {
            moduleSystem.getModuleDependencies("G");
        });

        assertEquals("Error; dependency path has cycle", error.getMessage());

        assertThrows(Exception.class, () -> {
            moduleSystem.getModuleDependencies("C");
        });

        assertThrows(Exception.class, () -> {
            moduleSystem.getModuleDependencies("A");
        });

        assertThrows(Exception.class, () -> {
            moduleSystem.getModuleDependencies("I");
        });

        assertDoesNotThrow(() -> moduleSystem.getModuleDependencies("B"));
        assertDoesNotThrow(() -> moduleSystem.getModuleDependencies("D"));
        assertDoesNotThrow(() -> moduleSystem.getModuleDependencies("E"));
        assertDoesNotThrow(() -> moduleSystem.getModuleDependencies("F"));
        assertDoesNotThrow(() -> moduleSystem.getModuleDependencies("H"));
    }

    // ADD MORE TEST CASES HERE
}
