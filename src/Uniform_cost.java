import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Uniform_cost {
    Node ROOT;
    int minDepth;
    int maxDepth;
    Node bestNode;
    double goalVal;

    List<Node> queue = new ArrayList<>();

    PlanetWars pwtest;

    public Uniform_cost(PlanetWars planetWars) {
        pwtest = planetWars;
        ROOT = new Node(planetWars, 0, null, this);
        List<Node> root_children = ROOT.getChildren();
        Attack longest_attack = longestAttack(root_children);
        Attack shortest_attack = shortestAtack(root_children);

        goalVal = 1.05;
        maxDepth = longest_attack.turns;
        minDepth = shortest_attack.turns;
        queue.addAll(root_children);
    }

    public Node search() {
        long start = System.currentTimeMillis();
        while (queue.size() > 0) {

            if(System.currentTimeMillis() - start > 900){
                System.err.println("geen tijd meer");
                Node max = Collections.max(queue, new Comparator<Node>() {
                    @Override
                    public int compare(Node a, Node b) {
                        return a.value > b.value ? 1 : a.value == b.value ? 0 : -1;
                    }
                });
                System.err.println(max.first_Turn.attacks.get(0).amount);
                return max;
                //return new Node(null,0,null,null);
            }
            Node currentNode = queue.get(0);
            getBestNode(currentNode);
            queue.remove(0);
            if (currentNode.depth <= maxDepth) {
                if (currentNode.depth >= minDepth && goaltest(currentNode)) {
                    return currentNode;
                }
                for (Node child : currentNode.getChildren()) {
                    queue.add(child);
                }
                sortQueue();
            }
        }
        System.err.println(pwtest.gamestateString);
        System.err.println("empty queue");

        if(bestNode == null){
            return new Node(null,0,null,null);
        }
        return bestNode;
    }

    private void sortQueue() {
        Collections.sort(queue, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.value < o2.value) {
                    return 1;
                } else if (o1.value > o2.value) {
                    return -1;
                }
                return 0;
            }
        });
    }

    private boolean goaltest(Node node) {
        return  (node.value / goalVal) > ROOT.value;
    }


    // TODO deze fucties omschrijven voor de nieuwe Turn klasse
    private Attack longestAttack(List<Node> nodeList) {
        int longest = 0;
        Attack longest_attack = new Attack(null, null, 0, 0);
        for (Node node : nodeList) {
            Turn turn = node.first_Turn;
            for (int i = 0; i < turn.attacks.size() ; i++) {

                if(turn.attacks.get(i).turns > longest){
                    longest = turn.attacks.get(i).turns;
                    longest_attack = turn.attacks.get(i);
                }
            }
        }
        return longest_attack;
    }


    // TODO deze fucties omschrijven voor de nieuwe Turn klasse
    private Attack shortestAtack(List<Node> nodeList) {
        // TODO replace 999 with something that makes sense.
        // This is dumb
        int smallest = 999;
        Attack smallest_attack = new Attack(null, null, 0, 0);
        for (Node node : nodeList) {
            Turn turn = node.first_Turn;
            for (int i = 0; i < turn.attacks.size() ; i++) {
                if(turn.attacks.get(i).turns < smallest){
                    smallest_attack = turn.attacks.get(i);
                    smallest = smallest_attack.turns;
                }
            }
        }
        return smallest_attack;
    }

    public void getBestNode(Node node){
        if(bestNode != null){
            if(bestNode.value<node.value){
                bestNode = node;
            }
        }
        else{
            bestNode = node;
        }
    }
}
