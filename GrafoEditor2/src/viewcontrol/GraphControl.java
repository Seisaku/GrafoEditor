/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewcontrol;

import java.awt.Point;
import java.awt.geom.Line2D;
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
    private mode mode;
    private ItemControl selected;

    public ItemControl getSelected() {
        return selected;
    }

    public void setSelected(ItemControl selected) {
        this.selected = selected;
    }
    
    
    

    public GraphControl() {
        this.mode = mode.sel;
        this.Graph = new Graph(null);
        this.EC = new ArrayList<>();
        this.NC = new ArrayList<>();
    }

    public mode getMode() {
        return mode;
    }

    public void setMode(mode mode) {
        this.mode = mode;
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

//    /**
//     * @param a primeiro nó em que a aresta incide
//     * @param b segundo nó em que a aresta incide
//     * @param w peso da aresta
//     */
//    public void addEdge(Node a, Node b, int w) {
//        Edge E = new Edge("", a, b, w);
//        this.Graph.addEdge(E);
//        EC.add(new EdgeControl(E));
//    }
//
//    /**
//     * @param n nome da aresta
//     * @param a primeiro nó em que a aresta incide
//     * @param b segundo nó em que a aresta incide
//     * @param w peso da aresta
//     */
//    public void addEdge(String n, Node a, Node b, int w) {
//        Edge E = new Edge(n, a, b, w);
//        this.Graph.addEdge(E);
//        EC.add(new EdgeControl(E));
//    }
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

    public void GCnotify(NodeControl N) {
//        System.out.println("Notify N");
        switch (mode) {
            case remNode:
                this.removeNode(N);
                break;
            case sel:
//                System.out.println("Case sel");
                this.selected = N;
//                System.out.println(N.getNode().getName()+" selecionado");
                break;
            case addEdge:
                //TODO addEdge
//                System.out.println("Case addEdge");
                if (this.selected == null) {
                this.selected = N;
            } else {
                NodeControl nc1 = (NodeControl) this.selected, nc2 = N;
//                    System.out.println("Aresta entre: "+nc1.getNode().getName()+" e "+nc2.getNode().getName());
                this.addEdgeControl(this, nc1, nc2);
            }
                break;

        }

    }

    public void GCnotify(ItemControl I) {
        if (I.getClass() == EdgeControl.class) {
            this.GCnotify((EdgeControl) I);
        } else {
            this.GCnotify((NodeControl) I);
        }
    }

    public void GCnotify(EdgeControl E) {
        switch (mode) {
            case remEdge:
                this.removeEdge(E);
                break;
            case sel:
                this.selected = E;
                break;

        }
    }

    public void GCnotify(Point P) {
        switch (mode) {
            case addNode:
                this.addNodeControl(this, P);
                break;
        }
    }

    public ItemControl searchPoint(Point P) {
        for (NodeControl nodeControl : NC) {
            if (P.distance(nodeControl.getPoint()) < 25) {
//                System.out.println(nodeControl.getNode().getName() + " encontrado..");
                return nodeControl;
            }
        }
        Line2D Line;
        for (EdgeControl edgeControl : EC) {
            Point A = edgeControl.getA().getPoint(), B = edgeControl.getB().getPoint();
            int xinf = A.x < B.x ? A.x : B.x;
            int yinf = A.y < B.y ? A.y : B.y;
            int xsup = A.x > B.x ? A.x : B.x;
            int ysup = A.y > B.y ? A.y : B.y;
            boolean LimX = (P.x > xinf && P.x < xsup);
            boolean LimY = (P.y > yinf && P.y < ysup);
            boolean Limite = (xsup - xinf) > (ysup - yinf) ? LimX : LimY;
            Line = new Line2D.Double(A, B);
            if (Limite && Line.ptLineDist(P) < 5) {
                return edgeControl;
            }
        }
        return null;
    }

    private void removeNode(NodeControl N) {
        N.remove();
        Node node = N.getNode();
        ArrayList<Edge> ER = node.getEdges();
        for (Edge edge : ER) {
            this.Graph.removeEdge(edge);
        }
        this.Graph.removeNode(node);
    }

    private void removeEdge(EdgeControl E) {
        this.Graph.removeEdge(E.getEdge());
    }

    private void addNode(Point P) {
    }

    private void addEdgeControl(GraphControl GC, NodeControl nc1, NodeControl nc2) {
        Edge E = new Edge("e" + this.EC.size() + 1, nc1.getNode(), nc2.getNode(), 0);
        this.Graph.addEdge(E);
        EC.add(new EdgeControl(E, nc1, nc2));
//        System.out.println("Vertice adicionado: " + E.getName());
        this.selected = null;
        this.mode = mode.sel;
    }

    private void addNodeControl(GraphControl GC, Point P) {
        Node N = new Node("v" + (this.NC.size() + 1));
        this.Graph.addNode(N);
        NC.add(new NodeControl(N, P));
//        System.out.println("Node adicionado: " + N.getName());
        this.mode = mode.sel;
    }

}
