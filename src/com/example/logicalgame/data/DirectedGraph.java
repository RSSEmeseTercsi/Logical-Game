package com.example.logicalgame.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Fodor.István on 2015.02.24..
 */
public class DirectedGraph<V> {

        public class GraphItem<V>{
            private V containedObject;

            public GraphItem(V containedObject){
                this.containedObject = containedObject;
            }

            public V getContainedObject() {
                return containedObject;
            }

            @Override
            public String toString() {
                return "[" + containedObject +"]";
            }
        }

    /**
     * A Map is used to map each graph item to its list of adjacent items.
     */

    private Map<V, List<GraphItem<V>>> neighbors = new HashMap<V, List<GraphItem<V>>>();

    /**
     * String representation of graph.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (V v : neighbors.keySet())
            stringBuffer.append("\n    " + v + " -> " + neighbors.get(v));
        return stringBuffer.toString();
    }

    /**
     * Add a item to the graph. Nothing happens if item is already in graph.
     */
    public void add(V graphItem) {
        if (neighbors.containsKey(graphItem))
            return;
        neighbors.put(graphItem, new ArrayList<GraphItem<V>>());
    }

    /**
     * True iff graph contains vertex.
     */
    public boolean contains(V graphItem) {
        return neighbors.containsKey(graphItem);
    }

    /**
     * Add an edge to the graph; if either vertex does not exist, it's added.
     * This implementation allows the creation of multi-edges and self-loops.
     */
    public void add(V from, V to) {
        this.add(from);
        this.add(to);
        neighbors.get(from).add(new GraphItem<V>(to));
    }

    public int outDegree(V graphItem) {
        return neighbors.get(graphItem).size();
    }

    public int inDegree(V graphItem) {
        return inboundNeighbors(graphItem).size();
    }

    public List<V> outboundNeighbors(V graphItem) {
        List<V> list = new ArrayList<V>();
        for(GraphItem<V> currentItem: neighbors.get(graphItem))
            list.add(currentItem.containedObject);
        return list;
    }

    public List<V> inboundNeighbors(V inboundVertex) {
        List<V> inList = new ArrayList<V>();
        for (V to : neighbors.keySet()) {
            for (GraphItem currentItem : neighbors.get(to))
                if (currentItem.containedObject.equals(inboundVertex))
                    inList.add(to);
        }
        return inList;
    }

    public boolean isEdge(V from, V to) {
        for(GraphItem<V> currentItem :  neighbors.get(from)){
            if(currentItem.containedObject.equals(to))
                return true;
        }
        return false;
    }

}
