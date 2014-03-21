/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewcontrol;

import java.awt.Point;
import java.util.ArrayList;
import model.Node;

/**
 *
 * @author Rafael
 */
public class NodeControl extends ItemControl {

    private Node Node;
    private Point Point;
    private GraphControl GC;
    private final ArrayList<EdgeControl> EC;

    public void addEC(EdgeControl ec){
        this.EC.add(ec);
    }
    
    public ArrayList<EdgeControl> getEC(){
        return new ArrayList<>(EC);
    }
    
    public boolean removeEC(EdgeControl ec){
        return EC.remove(ec);
    }
    
    public GraphControl getGC() {
        return GC;
    }

    public void setGC(GraphControl GC) {
        this.GC = GC;
    }

    NodeControl(){
        EC = new ArrayList<>();
    }
    
    NodeControl(Node N) {
        this();
        this.Node = N;
        
    }

    public NodeControl(Node Node, Point Point) {
        this();
        this.Node = Node;
        this.Point = Point;
    }

    public Point getPoint() {
        return Point;
    }

    public void setPoint(Point Point) {
        this.Point = Point;
    }

    public Node getNode() {
        return Node;
    }

    public void setNode(Node Node) {
        this.Node = Node;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "NodeControl{" + this.Node + '}';
    }

    void setPoint(int x, int y) {
        this.Point = new Point(x, y);
    }

    @Override
    public String getName() {
        return this.Node.getName();
    }

    
}
