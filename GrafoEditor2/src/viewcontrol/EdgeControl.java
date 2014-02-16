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
public class EdgeControl {
    private Edge Edge;
    private ArrayList<Point> PseudoNodes;

    public ArrayList<Point> getPseudoNodes() {
        return PseudoNodes;
    }

    public void setPseudoNodes(ArrayList<Point> PseudoNodes) {
        this.PseudoNodes = PseudoNodes;
    }
    
    public Edge getEdge() {
        return Edge;
    }

    public void setEdge(Edge Edge) {
        this.Edge = Edge;
    }
    
}
