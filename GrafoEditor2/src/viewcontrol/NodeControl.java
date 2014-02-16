/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package viewcontrol;

import java.awt.Point;
import model.Node;

/**
 *
 * @author Rafael
 */
public class NodeControl {
    private Node Node;
    private Point Point;

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
    
    
}
