/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewcontrol;

import config.GEoptions;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Iterator;
import model.Edge;
import model.Graph;
import model.Node;
import view.MainFrame;

/**
 * Classe que cuida da interface entre o modelo Grafo e a
 * GUI
 *
 * @author Rafael
 */
public class GraphControl {

    private Graph Graph;
    private final ArrayList<NodeControl> NC;
    private final ArrayList<EdgeControl> EC;
    private mode mode;
    private ItemControl selected;
    private MainFrame MF;

    public MainFrame getMF() {
        return MF;
    }

    public void setMF(MainFrame MF) {
        this.MF = MF;
    }

    public Point getLastPoint() {
        Point ret = new Point(0, 0);
        for (NodeControl nodeControl : NC) {
            if (nodeControl.getPoint().x > ret.x) {
                ret.x = nodeControl.getPoint().x;
            }
            if (nodeControl.getPoint().y > ret.y) {
                ret.y = nodeControl.getPoint().y;
            }
        }
        return ret;
    }

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
        if (mode == mode.sel) {
            this.MF.resetButtom();
        }

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
     * @param pse lista de pontos auxiliares para desenho da
     * aresta
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

    public void GCnotify(NodeControl N, boolean S) {
//        System.out.println("Notify N");
        switch (mode) {
            case remNode:
                this.removeNodeControl(N);
                break;
            case sel:
//                System.out.println("Case sel");
                this.selected = N;
//                System.out.println(N.getNode().getName()+" selecionado");
                break;
            case addEdge:
//                System.out.println("Case addEdge");
                if (this.selected == null || this.selected.getClass() == EdgeControl.class) {
                    this.selected = N;
                } else if (this.selected.getClass() == NodeControl.class) {

                    NodeControl nc1 = (NodeControl) this.selected, nc2 = N;
//                    System.out.println("Aresta entre: "+nc1.getNode().getName()+" e "+nc2.getNode().getName());
                    this.addEdgeControl(this, nc1, nc2, S);
                }
                break;

        }

    }

    public void GCnotify(ItemControl I, boolean S) {
        if (I.getClass() == EdgeControl.class) {
            this.GCnotify((EdgeControl) I, S);
        } else {
            this.GCnotify((NodeControl) I, S);
        }
    }

    public void GCnotify(EdgeControl E, boolean S) {
        switch (mode) {
            case remEdge:
                this.removeEdgeControl(E);
                break;
            case sel:
                this.selected = E;
                break;

        }
    }

    public void GCnotify(Point P, boolean S) {
        switch (mode) {
            case addNode:
                this.addNodeControl(this, P, S);
                break;
            case addEdge:
            case sel:
                this.selected = null;
                break;
        }
    }

    public ItemControl searchPoint(Point P) {
        for (NodeControl nodeControl : NC) {
            if (P.distance(nodeControl.getPoint()) < GEoptions.getNodeDist()) {
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
            if (Limite && Line.ptLineDist(P) < GEoptions.getEdgeDist()) {
                return edgeControl;
            }
        }
        return null;
    }

    private void removeNodeControl(NodeControl N) {
        Node node = N.getNode();
        ArrayList<Edge> ER = node.getEdges();
        for (Edge edge : ER) {
            this.Graph.removeEdge(edge);

        }
        this.EC.removeAll(N.getEC());
        this.Graph.removeNode(node);
        this.NC.remove(N);
    }

    private void removeEdgeControl(EdgeControl E) {
        this.Graph.removeEdge(E.getEdge());
        this.EC.remove(E);
    }

    private void addEdgeControl(GraphControl GC, NodeControl nc1, NodeControl nc2, boolean S) {
        Edge E = new Edge("e" + (this.EC.size() + 1), nc1.getNode(), nc2.getNode(), 0);
        this.Graph.addEdge(E);
        EdgeControl ec = new EdgeControl(E, nc1, nc2);
        EC.add(ec);
        nc1.addEC(ec);
        nc2.addEC(ec);
//        System.out.println("Vertice adicionado: " + E.getName());
        this.selected = null;
        if (!S) {
            this.setMode(mode.sel);
        }
    }

    private void addNodeControl(GraphControl GC, Point P, boolean S) {
        Node N = new Node("v" + (this.NC.size() + 1));
        this.Graph.addNode(N);
        NC.add(new NodeControl(N, P));
//        System.out.println("Node adicionado: " + N.getName());
        if (!S) {
            this.setMode(mode.sel);
        }
    }

    public void removeItem(ItemControl selected) {
        if (selected.getClass() == EdgeControl.class) {
            this.removeEdgeControl((EdgeControl) selected);
        } else if (selected.getClass() == NodeControl.class) {
            this.removeNodeControl((NodeControl) selected);
        }
        this.selected = null;
    }

}
