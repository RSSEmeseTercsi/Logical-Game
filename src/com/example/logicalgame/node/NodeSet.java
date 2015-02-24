package com.example.logicalgame.node;

import android.util.Log;
import com.example.logicalgame.data.DirectedGraph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Greg on 2/24/15.
 */
public class NodeSet
{
    private List<Node> mNodes;
    private DirectedGraph<Node> mNodeGraph;

    public NodeSet(){
        this.mNodes = new ArrayList<Node>();
        this.mNodeGraph = new DirectedGraph<Node>();
    }

    /**
     * Loads the level, and creates the graph
     */
    public void loadLevel()
    {
        //first, we are adding the nodes to the local list
        this.mNodes.add(new Node(0)); //A
        this.mNodes.add(new Node(1)); //B
        this.mNodes.add(new Node(1)); //C
        this.mNodes.add(new Node(3)); //D
        this.mNodes.add(new Node(4)); //E
        this.mNodes.add(new Node(5)); //F
        this.mNodes.add(new Node(3)); //F

        this.mNodeGraph.add(this.mNodes.get(0), this.mNodes.get(1));
        this.mNodeGraph.add(this.mNodes.get(1), this.mNodes.get(2));
        this.mNodeGraph.add(this.mNodes.get(2), this.mNodes.get(3));
        this.mNodeGraph.add(this.mNodes.get(2), this.mNodes.get(6));
        this.mNodeGraph.add(this.mNodes.get(3), this.mNodes.get(4));
        this.mNodeGraph.add(this.mNodes.get(4), this.mNodes.get(5));

        Log.d("NodeSet", this.mNodeGraph.toString());
    }

    public List<Node> getNodes()
    {
        return this.mNodes;
    }
    public DirectedGraph<Node> getNodeGraph()
    {
        return this.mNodeGraph;
    }
}
