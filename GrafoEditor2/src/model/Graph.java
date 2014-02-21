/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import viewcontrol.EdgeControl;
import viewcontrol.NodeControl;

/**
 *
 * @author Rafael
 */
public class Graph {

    private String Name;
    private ArrayList<Node> Nodes;
    private ArrayList<Edge> Edges;

    public Graph() {
        this.Name = "Grafo";
        this.Nodes = new ArrayList<>();
        this.Edges = new ArrayList<>();
    }
   
    
    
    public Graph(String Name) {
        this();
        this.Name = Name;
 
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getNumNodes() {
        return Nodes.size();
    }

    public int getNumEdges() {
        return Edges.size();
    }

    public ArrayList<Node> getNodes() {
        return Nodes;
    }

    public void setNodes(ArrayList<Node> Nodes) {
        this.Nodes = Nodes;
    }

    public ArrayList<Edge> getEdges() {
        return Edges;
    }

    public void setEdges(ArrayList<Edge> Edges) {
        this.Edges = Edges;
    }

    public void addNode(Node N) {
        this.Nodes.add(N);
        System.out.println("add Node");
    }

    public void addEdge(Edge E) {
        this.Edges.add(E);
        System.out.println("add Edge");
    }

    public void remove(Edge edge) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void removeEdge(Edge edge) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void removeNode(Node node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
