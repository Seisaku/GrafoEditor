/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.awt.Color;
import javax.swing.ImageIcon;

/**
 *
 * @author I839169
 */
public class GEoptions {

    private static Color defaultcolor = Color.BLACK;
    private static Color selected = new Color(17,149, 29);//Color.ORANGE;
    private static Color hightlight = Color.RED;
    private static int NodeDist = 40;
    private static int EdgeDist = 5;
    private static int ScrollMargin = 20;
    private static int ScrollSpeed = 7;
    private static ImageIcon NodeImg, NodeHL, NodeSel;
    private static ImageIcon NodeAzul, NodeAzulEscuro, NodeMarrom, NodeRosa,
            NodeVerde, NodeVerdeEscuro, NodeVermelho, NodeAmarelo, NodeLaranja, NodePreto, NodeRoxo;
    private static String resourcePath = "/resource/";
    private static int zoomStep = 5;

    
    public static int getScrollSpeed() {
        return ScrollSpeed;
    }

    public static void setScrollSpeed(int ScrollSpeed) {
        GEoptions.ScrollSpeed = ScrollSpeed;
    }
    
    public static Color getDefaultcolor() {
        return defaultcolor;
    }

    public static void setDefaultcolor(Color defaultcolor) {
        GEoptions.defaultcolor = defaultcolor;
    }

    
    
    public static int getZoomStep() {
        return zoomStep;
    }

    public static void setZoomStep(int zoomStep) {
        GEoptions.zoomStep = zoomStep;
    }

    public static int getScrollMargin() {
        return ScrollMargin;
    }

    public static void setScrollMargin(int ScrollMargin) {
        GEoptions.ScrollMargin = ScrollMargin;
    }

    public static String getResourcePath() {
        return resourcePath;
    }

    public static void setResourcePath(String resourcePath) {
        GEoptions.resourcePath = resourcePath;
    }

    public GEoptions() {

        NodeAzul = new ImageIcon(this.getClass().getResource("/resource/Azul.png"));
        NodeAzulEscuro = new ImageIcon(this.getClass().getResource("/resource/AzulEscuro.png"));
        NodeMarrom = new ImageIcon(this.getClass().getResource("/resource/Marrom.png"));
        NodeRosa = new ImageIcon(this.getClass().getResource("/resource/Rosa.png"));
        NodeVerde = new ImageIcon(this.getClass().getResource("/resource/Verde.png"));
        NodeVerdeEscuro = new ImageIcon(this.getClass().getResource("/resource/VerdeEscuro.png"));
        NodeVermelho = new ImageIcon(this.getClass().getResource("/resource/Vermelho.png"));
        NodeAmarelo = new ImageIcon(this.getClass().getResource("/resource/Amarelo.png"));
        NodeLaranja = new ImageIcon(this.getClass().getResource("/resource/Laranja.png"));
        NodePreto = new ImageIcon(this.getClass().getResource("/resource/Preto.png"));
        NodeRoxo = new ImageIcon(this.getClass().getResource("/resource/Roxo.png"));

//        NodeImg = new ImageIcon(this.getClass().getResource("/resource/AzulEscuroG.png"));
//        NodeHL = new ImageIcon(this.getClass().getResource("/resource/VermelhaG.png"));
//        NodeSel = new ImageIcon(this.getClass().getResource("/resource/VerdeEscuroG.png"));
        NodeImg = NodeAzul;
        NodeHL = NodeVermelho;
        NodeSel = NodeVerdeEscuro;
    }

    public static ImageIcon getNodeImg() {
        return NodeImg;
    }

    public static void setNodeImg(ImageIcon NodeImg) {
        GEoptions.NodeImg = NodeImg;
    }

    public static ImageIcon getNodeHL() {
        return NodeHL;
    }

    public static void setNodeHL(ImageIcon NodeHL) {
        GEoptions.NodeHL = NodeHL;
    }

    public static ImageIcon getNodeSel() {
        return NodeSel;
    }

    public static void setNodeSel(ImageIcon NodeSel) {
        GEoptions.NodeSel = NodeSel;
    }

    /**
     * @return the selected
     */
    public static Color getSelected() {
        return selected;
    }

    /**
     * @param aSelected the selected to set
     */
    public static void setSelected(Color aSelected) {
        selected = aSelected;
    }

    /**
     * @return the hightlight
     */
    public static Color getHightlight() {
        return hightlight;
    }

    /**
     * @param aHightlight the hightlight to set
     */
    public static void setHightlight(Color aHightlight) {
        hightlight = aHightlight;
    }

    /**
     * @return the NodeDist
     */
    public static int getNodeDist() {
        return NodeDist;
    }

    /**
     * @param aNodeDist the NodeDist to set
     */
    public static void setNodeDist(int aNodeDist) {
        NodeDist = aNodeDist;
    }

    /**
     * @return the EdgeDist
     */
    public static int getEdgeDist() {
        return EdgeDist;
    }

    /**
     * @param aEdgeDist the EdgeDist to set
     */
    public static void setEdgeDist(int aEdgeDist) {
        EdgeDist = aEdgeDist;
    }
}
