package com.redsteedstudios.logicalgame.node;

import android.content.Context;
import android.util.Log;
import com.redsteedstudios.logicalgame.data.DirectedGraph;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
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
     * TODO: load from a json string
     */
    public String getJSONString(Context context, String fileInAssets){
        try
        {
            InputStream inputStream = context.getAssets().open(fileInAssets);
            int bufferSize = inputStream.available();
            byte[] buffer = new byte[bufferSize];
            inputStream.read(buffer);
            inputStream.close();

            return new String(buffer);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return "";
    }

    public void loadLevel(Context context, String fileInAssets)
    {
        try
        {
            JSONObject rootObject = new JSONObject(getJSONString(context, fileInAssets));

            JSONArray  nodesArray = rootObject.getJSONArray("nodes");
            for (int c = 0; c < nodesArray.length(); c++)
            {
                Node       node     = new Node(0);
                JSONObject jsonNode = nodesArray.getJSONObject(c);

                node.setID(c);
                node.setRow(jsonNode.getInt("layer"));
                node.setShape(Node.ENodeShape.valueOf(jsonNode.getString("shape")));
                node.setOwner(Node.ENodeOwner.valueOf(jsonNode.getString("owner")));
                node.setType(Node.ENodeType.valueOf(jsonNode.getString("type")));
                //FIXME: implement getting and setting the value

                this.mNodes.add(node);
            }

            JSONArray relationsArray = rootObject.getJSONArray("relations");
            for (int c = 0; c < relationsArray.length(); c++)
            {
                JSONObject relationNode= relationsArray.getJSONObject(c);

                //from is always going to be a single string
                int from = relationNode.getInt("from");

                //to can have multiple objects, meaning it's going to be an array
                JSONArray toArray = relationNode.getJSONArray("to");
                for (int a = 0; a < toArray.length(); a++)
                {
                    int to = toArray.getInt(a);

                    this.mNodeGraph.add(findNodeByID(from), findNodeByID(to));
                }

            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        Log.d("NodeSet", this.mNodeGraph.toString());
    }

    /***
     * Looks up the node based on the id. Maybe a simple .get(index) would work better.
     * FIXME: rewrite
     * @param id
     * @return
     */
    public Node findNodeByID(int id)
    {
        //if it's -1, it doesn't have a node attached
        if (id == -1){ return null; }

        for (Node node : this.mNodes) {
            if (node.getID() == id)
                return node;
        }

        return null;
    }

    /***
     * Returns the attached nodes
     * @return
     */
    public List<Node> getNodes()
    {
        return this.mNodes;
    }

    /***
     * Returns the node graph attached to the level
     * @return
     */
    public DirectedGraph<Node> getNodeGraph()
    {
        return this.mNodeGraph;
    }
}
