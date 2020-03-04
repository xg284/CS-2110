/** NetId(s): xg284, sl3282
 * Name(s): Xinyun Guo, Siyu Liu
 */
package app;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import game.HuntState;
import game.Hunter;
import game.Node;
import game.ScramState;

/** A solution with huntOrb optimized and scram getting out as fast as possible. */
public class Pollack extends Hunter {

    /** Get to the orb in as few steps as possible. <br>
     * Once you get there, you must return from the function in order to pick it up. <br>
     * If you continue to move after finding the orb rather than returning, it will not count.<br>
     * If you return from this function while not standing on top of the orb, it will count as a
     * failure.
     *
     * There is no limit to how many steps you can take, but you will receive<br>
     * a score bonus multiplier for finding the orb in fewer steps.
     *
     * At every step, you know only your current tile's ID and the ID of all<br>
     * open neighbor tiles, as well as the distance to the orb at each of <br>
     * these tiles (ignoring walls and obstacles).
     *
     * In order to get information about the current state, use functions<br>
     * currentLocation(), neighbors(), and distanceToOrb() in HuntState.<br>
     * You know you are standing on the orb when distanceToOrb() is 0.
     *
     * Use function moveTo(long id) in HuntState to move to a neighboring<br>
     * tile by its ID. Doing this will change state to reflect your new position.
     *
     * A suggested first implementation that will always find the orb, but <br>
     * likely won't receive a large bonus multiplier, is a depth-first search. <br>
     * Some modification is necessary to make the search better, in general. */
    @Override
    public void huntOrb(HuntState state) {
        // TODO 1: Get the orb
        HashSet<Long> hashset= new HashSet<>();
        dfs(state, hashset);
    }

    boolean check= false;

    private void dfs(HuntState state, HashSet<Long> hashset) {
        if (state.distanceToOrb() == 0)
            return;
        hashset.add(state.currentLocation());
        long id= state.currentLocation();
        Heap<game.NodeStatus> heap= new Heap<>(false);
        for (game.NodeStatus ns : state.neighbors()) {
            heap.add(ns, ns.getDistanceToTarget());
        }

        while (heap.size() > 0) {
            game.NodeStatus node= heap.poll();

            if (!hashset.contains(node.getId())) {
                if (heap.size() > 0 &&
                    heap.peek().getDistanceToTarget() == node.getDistanceToTarget() &&
                    !hashset.contains(heap.peek().getId())) {
                    boolean choice= false;
                    state.moveTo(node.getId());
                    for (game.NodeStatus n : state.neighbors()) {
                        if (n.getDistanceToTarget() < state.distanceToOrb())
                            choice= true;
                    }
                    if (!choice) {
                        state.moveTo(id);
                        state.moveTo(heap.poll().getId());
                        heap.add(node, node.getDistanceToTarget());
                    }
                } else state.moveTo(node.getId());
                dfs(state, hashset);
                if (state.distanceToOrb() == 0) { return; }
                state.moveTo(id);

            }
        }

    }

    /** Get out the cavern before the ceiling collapses, trying to collect as <br>
     * much gold as possible along the way. Your solution must ALWAYS get out <br>
     * before time runs out, and this should be prioritized above collecting gold.
     *
     * You now have access to the entire underlying graph, which can be accessed <br>
     * through ScramState. <br>
     * currentNode() and getExit() will return Node objects of interest, and <br>
     * getNodes() will return a collection of all nodes on the graph.
     *
     * Note that the cavern will collapse in the number of steps given by <br>
     * getStepsRemaining(), and for each step this number is decremented by the <br>
     * weight of the edge taken. <br>
     * Use getStepsRemaining() to get the time still remaining, <br>
     * pickUpGold() to pick up any gold on your current tile <br>
     * (this will fail if no such gold exists), and <br>
     * moveTo() to move to a destination node adjacent to your current node.
     *
     * You must return from this function while standing at the exit. <br>
     * Failing to do so before time runs out or returning from the wrong <br>
     * location will be considered a failed run.
     *
     * You will always have enough time to scram using the shortest path from the <br>
     * starting position to the exit, although this will not collect much gold. <br>
     * For this reason, using Dijkstra's to plot the shortest path to the exit <br>
     * is a good starting solution */
    @Override
    public void scram(ScramState state) {

        // TODO 2: Get out of the cavern before it collapses, picking up gold along the way

        while (state.currentNode() != state.getExit()) {
            for (Node node : getPath(state)) {
                state.moveTo(node);
            }

        }
        return;
    }

    private List<Node> getPath(ScramState state) {
        List<Node> path= Path.shortest(state.currentNode(), state.getExit());
        int pathsum= Path.pathSum(path);
        if (pathsum == state.stepsLeft()) {
            path.remove(0);
            return path;
        }

        Heap<Node> helper= new Heap<>(true);

        for (Node node : state.allNodes()) {
            if (node == state.currentNode()) continue;
            double gold= node.getTile().gold();
            List<Node> goldpath= Path.shortest(state.currentNode(), node);
            double distance= Path.pathSum(goldpath);
            double goldSum= Path.goldSum(goldpath);
            helper.add(node, goldSum / distance);
        }

        Node max= helper.poll();
        List<Node> goldpathmax= Path.shortest(state.currentNode(), max);
        List<Node> exitpath= Path.shortest(max, state.getExit());
        boolean find= false;
        while (helper.size() != 0) {
            int pathsum1= Path.pathSum(goldpathmax);
            int pathsum2= Path.pathSum(exitpath);
            if (pathsum1 + pathsum2 <= state.stepsLeft()) {
                find= true;
                break;
            }
            max= helper.poll();
            goldpathmax= Path.shortest(state.currentNode(), max);
            exitpath= Path.shortest(max, state.getExit());
        }

        List<Node> res= new LinkedList<>();
        if (Path.goldSum(goldpathmax) == 0) {
            path.remove(0);
            return path;
        }

        if (find) {
            if (goldpathmax.size() <= 3) {
                goldpathmax.remove(0);
                return goldpathmax;
            }
            for (int i= 0; i < 1; i++ ) {
                res.add(goldpathmax.get(i + 1));
            }
            return res;
        }
        path.remove(0);
        return path;
    }
}
