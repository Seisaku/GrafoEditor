/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewcontrol;

import java.awt.Point;
import java.util.ArrayList;
import model.Edge;

/**
 *
 * @author Rafael
 */
public class EdgeControl extends ItemControl {

    private Edge Edge;
    private ArrayList<Point> PseudoNodes;
    private NodeControl A, B;
    private GraphControl GC;

    public GraphControl getGC() {
        return GC;
    }

    public void setGC(GraphControl GC) {
        this.GC = GC;
    }

    EdgeControl(Edge E, NodeControl A, NodeControl B) {
        this.Edge = E;  
        this.A = A;
        this.B = B;
        A.getNode().addEdge(E);
        B.getNode().addEdge(E);
    }

    public NodeControl getA() {
        return A;
    }

    public void setA(NodeControl A) {
        this.A = A;
    }

    public NodeControl getB() {
        return B;
    }

    public void setB(NodeControl B) {
        this.B = B;
    }

    public EdgeControl(Edge Edge, ArrayList<Point> PseudoNodes) {
        this.Edge = Edge;
        this.PseudoNodes = PseudoNodes;
    }

    public Point getPseudoNodes(int i) {
        return PseudoNodes.get(i);
    }

    public void addPseudoNodes(Point PseudoNode) {
        this.PseudoNodes.add(PseudoNode);
    }

    public Edge getEdge() {
        return Edge;
    }

    public void setEdge(Edge Edge) {
        this.Edge = Edge;
    }

    public void select() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove() {
    }

    @Override
    public String toString() {
        return "EdgeControl{" + this.Edge + '}';
    }

}
