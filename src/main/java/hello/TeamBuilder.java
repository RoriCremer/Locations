package hello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TeamBuilder {

  /**
   * Helper methods
   */

  private  Map<Integer, ArrayList<Integer>>  createGraphModel(
    final String[] paths,
    final int numPathElements
  ) {
    Map<Integer, ArrayList<Integer>> graph = new HashMap<>();

    // for each String path in paths, find the index of the '1's in the string
    final String one = "1";
    for (int i = 0; i < numPathElements; ++i) {
      final String path = paths[i];
      if (path.length() != numPathElements) {
        throw new IllegalArgumentException("path of index " + i + " needs to be of length " + numPathElements);
      }

      if (!path.matches("[0-1]+")) {
        throw new IllegalArgumentException("path elements can only contains '1's and '0's");
      }
      ArrayList<Integer> pathsList = new ArrayList<>();
      int oneIndex = paths[i].indexOf(one);
      while (oneIndex >= 0) {
        // make sure the i-th element of paths will contain a zero in the i-th position
        if (oneIndex != i) { // okay since both are primitives
          pathsList.add(oneIndex);  // note example 3 breaks this rule so needs this as a workaround
        }
        oneIndex = paths[i].indexOf(one, oneIndex + 1);
      }
      graph.put(i, pathsList);
    }

    return graph;
  }

  private  Map<Integer, ArrayList<Integer>>  createFlatGraph(
    final Map<Integer, ArrayList<Integer>> graph,
    final int numPathElements
  ) {
    // create a similar version of the graph, but flattened to show full routes
    Map<Integer, ArrayList<Integer>> flatGraph = new HashMap<>();

    // cycle though all of the nodes in the graph and for each, see how deep they do
    for (int currentRootNode: graph.keySet()) {
      final ArrayList<Integer> currentPaths = graph.get(currentRootNode);
      ArrayList<Integer> allPaths = new ArrayList<>(); // all full routes will be stored here and added to flatgraph
      allPaths.addAll(currentPaths);
      allPaths.add(currentRootNode); // add current node location to see full route
      ArrayList<Integer> visited = new ArrayList<>(); // keep track of visited nodes
      visited.add(currentRootNode);
      int index = numPathElements; // index will keep track of depth
      int currentNode = currentRootNode;
      while (index > 0) { // go 1 level less deep than number of nodes since you start at a node that can reach itself
        for (Integer path: graph.get(currentNode)) { // for each possible path at a given depth
          final ArrayList<Integer> childrenNodes = graph.get(path);
          childrenNodes.stream().forEach(childNode -> {
            if (!allPaths.contains(childNode)) {
              allPaths.add(childNode);
            }
          });
          currentNode = path;
        }
        index = index - 1;
        if (visited.contains(currentNode)) { // already traversed this route
          break;
        }
        visited.add(currentNode);
      }
      flatGraph.put(currentRootNode, allPaths);
    }

    return flatGraph;
  }


  /**
   * Calculate location counts
   */

  public final int[] specialLocation(
    final String[] paths
  ){
    final int numPathElements = paths.length;
    if (numPathElements < 2 || numPathElements > 50) {
      throw new IllegalArgumentException("paths must contain 2 to 50 elements");
    }
    // create graph hashmap to model the paths
    Map<Integer, ArrayList<Integer>> graph = createGraphModel(paths, numPathElements);

    // create a similar version of the graph, but flattened to show full routes
    Map<Integer, ArrayList<Integer>> flatGraph = createFlatGraph(graph, numPathElements);

    // get number of locations that can reach all other locations
    final int motherCount = Math.toIntExact(
      flatGraph.values().stream().filter(val -> numPathElements == val.size()).count()
    );

    // get number of locations that are reachable _by_ all other locations--in all arrays in flatGraph
    final ArrayList<Integer> count = new ArrayList<>();

    // pick a primary list for comparing common values across the others
    final ArrayList<Integer> primaryNodePaths = flatGraph.get(0);

    duplicateLoop:
    // for each path of the primary node's paths
    for(int primaryNodePath: primaryNodePaths) {
      // for each of the other nodes -- to look for common paths (common destinations)
      for(int otherNodesIndex = 1; otherNodesIndex < flatGraph.size(); ++otherNodesIndex) {
        int pathIndex = 0;
        // for each path in each of the other nodes -- compare to each of primary node's path
        for (; pathIndex < flatGraph.get(otherNodesIndex).size(); ++pathIndex) {
          if (primaryNodePath == flatGraph.get(otherNodesIndex).get(pathIndex)) {
            break; // found a common destination -- so continue to the next node to check there too
          }
        }
        if (pathIndex == flatGraph.get(otherNodesIndex).size()) {
          continue duplicateLoop;   // not found in this node, so move on to the next primaryNodePath
        }
      }
      count.add(primaryNodePath);
    }

    int[] specialLocations = new int[2];
    specialLocations[0] = motherCount; // number of locations that can reach all other locations
    specialLocations[1] = count.size(); // number of locations that are reachable by all other locations
    return specialLocations;
  }
}
