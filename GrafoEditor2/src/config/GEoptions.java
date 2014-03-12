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
    private static Color selected = Color.RED;
    private static Color hightlight = Color.GREEN;
    private static int NodeDist = 25;
    private static int EdgeDist = 5;
    private static int ScrollMargin = 20;
    private static ImageIcon NodeImg,NodeHL,NodeSel;
    private static String resourcePath = "/resource/";

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
        NodeImg = new ImageIcon(this.getClass().getResource("/resource/Azul.png"));
        NodeHL = new ImageIcon(this.getClass().getResource("/resource/Vermelha.png"));
        NodeSel = new ImageIcon(this.getClass().getResource("/resource/Verde.png"));
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
