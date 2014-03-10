/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package model;

import java.util.ArrayList;

/**
 *
 * @author Rafael
 */
public class Node implements GraphItem{

    private ArrayList<Edge> Edges;
    private String Name;

    public Node(String Name) {
        this.Name = Name;
        Edges = new ArrayList<>();
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public ArrayList<Edge> getEdges() {
        return Edges;
    }

    public void setEdges(ArrayList<Edge> Edges) {
        this.Edges = Edges;
    }

    public void addEdge(Edge E) {
        Edges.add(E);

    }

    public void removeEdge(Edge E) {
        Edges.remove(E);

    }

    public int getDegree() {
        return Edges.size();
    }

    @Override
    public String toString() {
        return this.Name;
    }
    
    
}
