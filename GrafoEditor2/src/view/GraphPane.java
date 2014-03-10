/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import config.GEoptions;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import viewcontrol.EdgeControl;
import viewcontrol.GraphControl;
import viewcontrol.ItemControl;
import viewcontrol.NodeControl;
import viewcontrol.mode;

/**
 *
 * @author I839169
 */
public class GraphPane extends javax.swing.JPanel {

    private GraphControl GC;
    private ItemControl Hightlighted;
    private Point mouse;

    public GraphControl getGC() {
        return GC;
    }

    public void setGC(GraphControl GC) {
        this.GC = GC;
    }

    public ItemControl getHightlighted() {
        return Hightlighted;
    }

    public void setHightlighted(ItemControl Hightlighted) {
        this.Hightlighted = Hightlighted;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        NodeControl nhl = null, nsel = null;
        EdgeControl ehl = null, esel = null;
        //&& (this.Hightlighted.class==NodeControl.class)
        if (this.Hightlighted != null) {
            if (this.Hightlighted.getClass() == NodeControl.class) {
                nhl = (NodeControl) Hightlighted;
            } else {
                ehl = (EdgeControl) Hightlighted;
            }

        }
        if (this.GC.getSelected() != null) {
            if (this.GC.getSelected().getClass() == NodeControl.class) {
                nsel = (NodeControl) this.GC.getSelected();
            } else {
                esel = (EdgeControl) this.GC.getSelected();
            }

        }
        if (this.GC != null) {
            EdgeControl ec;
            NodeControl nc;
            Point A, B;
            g.setColor(Color.black);
            for (int i = 0; i < GC.getECsize(); i++) {
                ec = this.GC.getEC(i);
                A = ec.getA().getPoint();
                B = ec.getB().getPoint();
                if (ehl == ec) {
                    g.setColor(Color.red);
                }
                if (esel == ec) {
                    g.setColor(Color.green);
                }
                g.drawLine(A.x, A.y, B.x, B.y);
                g.setColor(Color.black);
            }
            if (nsel != null && this.GC.getMode() == mode.addEdge) {
                g.setColor(Color.green);
                g.drawLine(nsel.getPoint().x, nsel.getPoint().y, this.mouse.x, this.mouse.y);
                g.setColor(Color.black);
            }
            for (int i = 0; i < GC.getNCsize(); i++) {
                nc = this.GC.getNC(i);
                //g.drawOval(nc.getPoint().x, nc.getPoint().y, 5, 5);
                if (nsel == nc) {
                    GEoptions.getNodeSel().paintIcon(this, g, nc.getPoint().x - (GEoptions.getNodeSel().getIconWidth() / 2), nc.getPoint().y - (GEoptions.getNodeSel().getIconHeight() / 2));
                } else if (nhl == nc) {
                    GEoptions.getNodeHL().paintIcon(this, g, nc.getPoint().x - (GEoptions.getNodeHL().getIconWidth() / 2), nc.getPoint().y - (GEoptions.getNodeHL().getIconHeight() / 2));
                } else {
                    GEoptions.getNodeImg().paintIcon(this, g, nc.getPoint().x - (GEoptions.getNodeImg().getIconWidth() / 2), nc.getPoint().y - (GEoptions.getNodeImg().getIconHeight() / 2));
                }
            }
        }
    }

    /**
     * Creates new form GraphPane
     */
    public GraphPane() {
        initComponents();
        this.GC = new GraphControl();
        actiondel ac = new actiondel(this);
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( "DELETE" ), "actiondelete");
        this.getActionMap().put("actiondelete",ac);
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setAutoscrolls(true);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        if (this.Hightlighted != null) {
            this.GC.GCnotify(Hightlighted);
        }else{
            this.GC.GCnotify(evt.getPoint());
        }
        this.repaint();

    }//GEN-LAST:event_formMouseClicked

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        this.Hightlighted = this.GC.searchPoint(evt.getPoint());
        this.mouse = evt.getPoint();
        this.repaint();
    }//GEN-LAST:event_formMouseMoved

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        if (this.Hightlighted != null && this.Hightlighted.getClass() == NodeControl.class) {
            NodeControl nc = (NodeControl) this.Hightlighted;
            nc.setPoint(evt.getPoint());
            this.repaint();
        }
    }//GEN-LAST:event_formMouseDragged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

static class actiondel extends AbstractAction{
        private GraphPane GP;

        public GraphPane getGP() {
            return GP;
        }

        public void setGP(GraphPane GP) {
            this.GP = GP;
        }

        public actiondel(GraphPane GP) {
            this.GP = GP;
        }
        
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if(this.GP.getGC().getSelected()!=null){
                System.out.println(this.GP.getGC().getSelected());
            }else{
                System.out.println("No selection");
            }
            
            
        }
    
    }
    
}
