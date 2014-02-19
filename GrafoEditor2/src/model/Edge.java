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

/**
 *
 * @author Rafael
 */
public class Edge implements GraphItem{

    private Node A, B;
    private int Weight;
    private boolean Directed;
    private String Name;
    

    public Edge(String Name, Node A, Node B, int weight, boolean Directed) {
        this.Name = Name;
        this.A = A;
        this.B = B;
        this.Weight = weight;
        this.Directed = Directed;
    }

    public Edge(String Name, Node A, Node B) {
        this.Name = Name;
        this.A = A;
        this.B = B;
        this.Weight = 0;
        this.Directed = false;
    }

    public Edge(String Name, Node A, Node B, int weight) {
        this.Name = Name;
        this.A = A;
        this.B = B;
        this.Weight = weight;
        this.Directed = false;
    }

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int weight) {
        this.Weight = weight;
    }

    public boolean isDirected() {
        return Directed;
    }

    public void setDirected(boolean Directed) {
        this.Directed = Directed;
    }

    public Node getA() {
        return A;
    }

    public void setA(Node A) {
        this.A = A;
    }

    public Node getB() {
        return B;
    }

    public void setB(Node B) {
        this.B = B;
    }

    //Se o grafo for direcionado o sentido sempre será de A para B
    public void changeDiretion() {
        Node temp = A;
        A = B;
        B = temp;
    }

}
