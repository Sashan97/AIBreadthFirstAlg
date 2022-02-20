import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Program {

    public static void main(String[] args) {
        simulationStart();
    }

    private static void simulationStart() {
        Simulation sim = new Simulation();
        sim.addInitialRelations();

        System.out.println("Enter initial position: ");
        String initialNode = nodesInput();

        System.out.println("Enter target position");
        String targetNode = nodesInput();

        if(checkNodesExist(sim, initialNode, targetNode)){
            Node goal = sim.findRoute(initialNode, targetNode);
            restoreRoute(goal);
        }
        else{
            System.out.println("Incorrect initial or target position");
        }
    }

    private static boolean checkNodesExist(Simulation sim, String initialNode, String targetNode) {
        if(sim.getRelations().checkIfAny(initialNode) && sim.getRelations().checkIfAny(targetNode)) return true;
        return false;
    }

    private static String nodesInput() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        try{
            input = reader.readLine();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return input;
    }

    private static void restoreRoute(Node goal) {
        ArrayList<String> route = new ArrayList<>();
        route.add(goal.getTitle());
        Node previous = goal.getPrevious();
        while(previous != null){
            route.add(previous.getTitle());
            previous = previous.getPrevious();
        }

        Collections.reverse(route);

        for (int i = 0; i < route.size(); i++) {
            System.out.printf(route.get(i));
            if(i < route.size() - 1) System.out.printf(" -> ");
        }
    }
}
