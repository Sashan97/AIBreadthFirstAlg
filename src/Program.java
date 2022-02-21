import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * The main class of the Breadth First search algorithm demonstration program
 *
 * @author Aleksandr Boldyšev
 * @author Tomaš Pieško
 * @author Giedrė Narbutaitė
 *
 * @version 1.2
 * @since 1.0
 */

public class Program {

    public static void main(String[] args) {
        boolean loop = true;
        Scanner input = new Scanner(System.in);
        while (loop){
            simulationStart();
            System.out.println("Continue? Y/N");
            String c = input.nextLine();
            if(c.equalsIgnoreCase("n")){
                loop =  false;
            }
        }

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
            if (goal != null) restoreRoute(goal);
            else System.out.println("Search failed.");
        }
        else{
            System.out.println("Incorrect initial or target position");
        }
    }

    private static boolean checkNodesExist(Simulation sim, String initialNode, String targetNode) {
        return sim.getRelations().checkIfAny(initialNode) && sim.getRelations().checkIfAny(targetNode);
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

    /**
     * Restores the sequence of actions that were taken to get to the target node.
     * @param goal Target node object.
     */
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
            System.out.print(route.get(i));
            if(i < route.size() - 1) System.out.print(" -> ");
        }
        System.out.println();
    }
}
