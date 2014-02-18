/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import viewcontrol.EdgeControl;
import viewcontrol.GraphControl;
import viewcontrol.NodeControl;

/**
 *
 * @author Rafael
 */
public class GraphPane2 extends JPanel {

    private GraphControl GC;
    private BufferedImage BI;
    
    
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(BI == null){
            this.startBI();
        }
        EdgeControl ec;
        NodeControl nc;
        Point A, B;
        for (int i = 0; i < GC.getECsize(); i++) {
            ec = this.GC.getEC(i);
            A = ec.getA().getPoint();
            B = ec.getB().getPoint();
            g.drawLine(A.x, A.y, B.x, B.y);
        }
        for (int i = 0; i < GC.getNCsize(); i++) {
            nc = this.GC.getNC(i);
            g.drawOval(nc.getPoint().x, nc.getPoint().y, 5, 5);            
            g.drawImage(BI,nc.getPoint().x,nc.getPoint().y,this);
        }

    }
    
    public void startBI(){
        try {
            BI = ImageIO.read(new URL("resource//Node.png"));
        } catch (IOException ex) {
            Logger.getLogger(GraphPane2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public GraphControl getGC() {
        return GC;
    }

    public void setGC(GraphControl GC) {
        this.GC = GC;
    }

}
