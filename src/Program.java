import java.util.ArrayList;

public class Program {

    public static void main(String[] args) {
        simulationStart();
    }

    private static void simulationStart() {
        Simulation sim = new Simulation();
        sim.addInitialRelations();
    }
}
