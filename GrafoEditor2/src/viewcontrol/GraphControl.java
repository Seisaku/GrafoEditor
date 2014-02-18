/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewcontrol;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import model.Edge;
import model.Graph;
import model.Node;

/**
 * Classe que cuida da interface entre o modelo Grafo e a GUI
 *
 * @author Rafael
 */
public class GraphControl {

    private Graph Graph;
    private ArrayList<EdgeControl> EC;
    private ArrayList<NodeControl> NC;

    public GraphControl() {
        this.Graph = new Graph(null);
        this.EC = new ArrayList<>();
        this.NC = new ArrayList<>();        
    }    
    
    /**
     *
     * @param i
     * @return
     */
    public EdgeControl getEC(int i) {
        return EC.get(i);
    }

    public int getECsize() {
        return EC.size();
    }

    public int getNCsize() {
        return NC.size();
    }

    /**
     *
     * @param i
     * @return
     */
    public NodeControl getNC(int i) {
        return NC.get(i);
    }

    /**
     *
     * @return
     */
    public Graph getGraph() {
        return Graph;
    }

    /**
     *
     * @param Graph
     */
    public void setGraph(Graph Graph) {
        this.Graph = Graph;
    }

    /**
     *
     * @param n
     * @param P
     */
    public void addNode(String n, Point P) {
        Node N = new Node(n);
        this.Graph.addNode(N);
        NC.add(new NodeControl(N, P));
    }

    /**
     *
     * @param n
     * @param a
     * @param b
     * @param w
     */
    public void addEdge(String n, Node a, Node b, int w) {
        Edge E = new Edge(n, a, b, w);
        this.Graph.addEdge(E);
        EC.add(new EdgeControl(E));
    }

    /**
     * Adiciona uma aresta ao grafo
     *
     * @param n nome da aresta
     * @param a primeiro nó em que a aresta incide
     * @param b segundo nó em que a aresta incide
     * @param w peso da aresta
     * @param pse lista de pontos auxiliares para desenho da aresta
     */
    public void addEdge(String n, Node a, Node b, int w, ArrayList<Point> pse) {
        Edge E = new Edge(n, a, b, w);
        this.Graph.addEdge(E);
        EC.add(new EdgeControl(E, pse));
    }

    /**
     * Função para retornar um NodeControl dado um Node
     *
     * @param N, node a ter seu controler recuperado
     * @return controlerr do node
     */
    public NodeControl NCget(Node N) {
        NodeControl nodeControl;
        for (Iterator<NodeControl> it = NC.iterator(); it.hasNext();) {
            nodeControl = it.next();
            if (nodeControl.getNode() == N) {
                return nodeControl;
            }
        }
        return null;
    }

    /**
     * Função para retornar um EdgeControl dado um Edge
     *
     * @param E, edge a ter seu controler recuperado
     * @return controler do edge
     */
    public EdgeControl ECget(Edge E) {
        EdgeControl edgeControl;
        for (Iterator<EdgeControl> it = EC.iterator(); it.hasNext();) {
            edgeControl = it.next();
            if (edgeControl.getEdge() == E) {
                return edgeControl;
            }
        }
        return null;
    }
}
