/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import config.GEoptions;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.Scrollable;
import javax.swing.border.LineBorder;
import viewcontrol.EdgeControl;
import viewcontrol.GraphControl;
import viewcontrol.ItemControl;
import viewcontrol.NodeControl;
import viewcontrol.mode;

/**
 *
 * @author I839169
 */
public class GraphPane extends javax.swing.JPanel implements Scrollable {

    private GraphControl GC;
    private ItemControl Hightlighted;
    private Point mouse;
    private int zoom;

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

    public float getZoom() {
        return (100 - (this.zoom * GEoptions.getZoomStep())) / (float) 100;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int x, y;
        float z;
        NodeControl nhl = null, nsel = null;
        EdgeControl ehl = null, esel = null;
        z = this.getZoom();
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
                int xz = (int) (A.x * z),
                        yz = (int) (A.y * z),
                        xz2 = (int) (B.x * z),
                        yz2 = (int) (B.y * z);

                g.drawLine(xz, yz, xz2, yz2);
                g.setColor(Color.black);
            }
            if (nsel != null && this.GC.getMode() == mode.addEdge) {
                g.setColor(Color.green);
                int xz = (int) (nsel.getPoint().x * z),
                        yz = (int) (nsel.getPoint().y * z),
                        xz2 = (int) (this.mouse.x),
                        yz2 = (int) (this.mouse.y);
                g.drawLine(xz, yz, xz2, yz2);
                g.setColor(Color.black);
            }

            for (int i = 0; i < GC.getNCsize(); i++) {
                nc = this.GC.getNC(i);
                //g.drawOval(nc.getPoint().x, nc.getPoint().y, 5, 5);

                x = (int) ((nc.getPoint().x - (GEoptions.getNodeSel().getIconWidth() / 2)) * z);
                y = (int) ((nc.getPoint().y - (GEoptions.getNodeSel().getIconHeight() / 2)) * z);
                if (nsel == nc) {
                    GEoptions.getNodeSel().paintIcon(this, g, x, y);
                } else if (nhl == nc) {
                    GEoptions.getNodeHL().paintIcon(this, g, x, y);
                } else {
                    GEoptions.getNodeImg().paintIcon(this, g, x, y);
                }
            }
        }
        g.drawString(this.getZoom() * 100 + "%", 6, 15);
        //g.drawString(mouse.toString(), 6,15);
        //g.drawString(getPointZoomed(mouse).toString()+" "+this.getZoom()*100+"%", 6,30);
        //g.drawString(getPointOri(getPointZoomed(mouse)).toString(), 6,45);
    }

    /**
     * Creates new form GraphPane
     */
    public GraphPane() {
        initComponents();
        this.mouse = new Point(0, 0);
        this.GC = new GraphControl();
        ActionKeyPressDel ad = new ActionKeyPressDel(this);
        ActionKeyPressEsc ae = new ActionKeyPressEsc(this);
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DELETE"), "actiondelete");
        this.getActionMap().put("actiondelete", ad);
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "actionescape");
        this.getActionMap().put("actionescape", ae);
        setAutoscrolls(true);
        this.setBorder(new LineBorder(Color.black));
        this.revalidate();
        this.zoom = 0;
    }

    public Point getPointZoomed(Point ori) {
        Point p;
        int nx = (int) (ori.x * getZoom()),
                ny = (int) (ori.y * getZoom());
        p = new Point(nx, ny);
        return p;
    }

    public Point getPointOri(Point zoomed) {
        Point p;
        int nx = (int) (zoomed.x / getZoom()),
                ny = (int) (zoomed.y / getZoom());
        p = new Point(nx, ny);
        return p;
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
        addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                formMouseWheelMoved(evt);
            }
        });
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
            this.GC.GCnotify(Hightlighted, evt.isShiftDown());
        } else {
            this.GC.GCnotify(this.getPointOri(evt.getPoint()), evt.isShiftDown());
        }
        this.repaint();

    }//GEN-LAST:event_formMouseClicked

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        this.Hightlighted = this.GC.searchPoint(this.getPointOri(evt.getPoint()));
        this.mouse = this.getPointOri(evt.getPoint());
        this.repaint();
    }//GEN-LAST:event_formMouseMoved

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged

        Point lp = this.getGC().getLastPoint();
        Dimension D = new Dimension(lp.x + GEoptions.getScrollMargin() + (GEoptions.getNodeImg().getIconWidth() / 2), lp.y + GEoptions.getScrollMargin() + (GEoptions.getNodeImg().getIconHeight() / 2));
        if (D.height < this.getParent().getSize().height) {
            D.height = this.getParent().getSize().height;
        }
        if (D.width < this.getParent().getSize().width) {
            D.width = this.getParent().getSize().width;
        }
        this.setPreferredSize(D);
        this.revalidate();

        Rectangle r = new Rectangle(evt.getX() + (GEoptions.getNodeImg().getIconWidth() / 2), evt.getY() + (GEoptions.getNodeImg().getIconHeight() / 2), 1, 1);
        scrollRectToVisible(r);

        if (this.Hightlighted != null && this.Hightlighted.getClass() == NodeControl.class) {
            NodeControl nc = (NodeControl) this.Hightlighted;
            if (this.getPointOri(evt.getPoint()).x > (GEoptions.getNodeImg().getIconWidth() / 2) && this.getPointOri(evt.getPoint()).y > (GEoptions.getNodeImg().getIconHeight() / 2)) {//Impede que Node seja movido para uma coordenada fora da tela
                nc.setPoint(this.getPointOri(evt.getPoint()));
                this.repaint();
            }
        }
    }//GEN-LAST:event_formMouseDragged

    private void formMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_formMouseWheelMoved
        if (evt.isControlDown()) {
            this.zoom += evt.getWheelRotation();
            int lim = (100 / GEoptions.getZoomStep()) - 1;
            if (zoom > lim) {
                zoom = lim + 1;
            }
            if (zoom <= -lim) {
                zoom = -lim;
            }
            this.repaint();
        }
    }//GEN-LAST:event_formMouseWheelMoved

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return getPreferredSize();
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 20;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 20;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return false;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
static class ActionKeyPressEsc extends AbstractAction {

        private GraphPane GP;

        public GraphPane getGP() {
            return GP;
        }

        public void setGP(GraphPane GP) {
            this.GP = GP;
        }

        public ActionKeyPressEsc(GraphPane GP) {
            this.GP = GP;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            GP.getGC().setSelected(null);
            GP.getGC().setMode(mode.sel);
            GP.repaint();
        }

    }

    static class ActionKeyPressDel extends AbstractAction {

        private GraphPane GP;

        public GraphPane getGP() {
            return GP;
        }

        public void setGP(GraphPane GP) {
            this.GP = GP;
        }

        public ActionKeyPressDel(GraphPane GP) {
            this.GP = GP;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (this.GP.getGC().getSelected() != null) {
                //System.out.println(this.GP.getGC().getSelected());
                this.GP.getGC().removeItem(this.GP.getGC().getSelected());
                GP.repaint();
            } else {
                System.out.println("No selection");
            }

        }

    }

}
