/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import config.GEoptions;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.Scrollable;
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
    private Rectangle rect;

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
        int imgWidth;
        int imgHeight;
        ImageIcon zoomedIconSel, zoomedIconImg, zoomedIconHL;
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
            g.setColor(GEoptions.getDefaultcolor());
            for (int i = 0; i < GC.getECsize(); i++) {
                ec = this.GC.getEC(i);
                A = ec.getA().getPoint();
                B = ec.getB().getPoint();
                if (ehl == ec) {
                    g.setColor(GEoptions.getHightlight());
                }
                if (esel == ec) {
                    g.setColor(GEoptions.getSelected());
                }
                int xz = (int) (A.x * z),
                        yz = (int) (A.y * z),
                        xz2 = (int) (B.x * z),
                        yz2 = (int) (B.y * z);

                g.drawLine(xz, yz, xz2, yz2);
                g.setColor(GEoptions.getDefaultcolor());
            }
            if (nsel != null && this.GC.getMode() == mode.addEdge) {
                g.setColor(GEoptions.getHightlight());
                int xz = (int) (nsel.getPoint().x * z),
                        yz = (int) (nsel.getPoint().y * z),
                        xz2 = (int) (this.mouse.x),
                        yz2 = (int) (this.mouse.y);
                g.drawLine(xz, yz, xz2, yz2);
                g.setColor(GEoptions.getDefaultcolor());
            }

            for (int i = 0; i < GC.getNCsize(); i++) {
                nc = this.GC.getNC(i);
                //g.drawOval(nc.getPoint().x, nc.getPoint().y, 5, 5);

                x = (int) ((this.getPointZoomed(nc.getPoint()).x));// - (GEoptions.getNodeSel().getIconWidth() / 2)));
                y = (int) ((this.getPointZoomed(nc.getPoint()).y));// - (GEoptions.getNodeSel().getIconHeight() / 2)));
                if (nsel == nc) {
                    imgWidth = (int) (GEoptions.getNodeSel().getIconHeight() * z);
                    imgHeight = (int) (GEoptions.getNodeSel().getIconHeight() * z);
                    zoomedIconSel = new ImageIcon(GEoptions.getNodeSel().getImage().getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH));
                    zoomedIconSel.paintIcon(this, g, x - zoomedIconSel.getIconWidth() / 2, y - zoomedIconSel.getIconHeight() / 2);
                } else if (nhl == nc) {
                    imgWidth = (int) (GEoptions.getNodeHL().getIconHeight() * z);
                    imgHeight = (int) (GEoptions.getNodeHL().getIconHeight() * z);
                    zoomedIconHL = new ImageIcon(GEoptions.getNodeHL().getImage().getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH));
                    zoomedIconHL.paintIcon(this, g, x - zoomedIconHL.getIconWidth() / 2, y - zoomedIconHL.getIconHeight() / 2);
                } else {
                    imgWidth = (int) (GEoptions.getNodeImg().getIconHeight() * z);
                    imgHeight = (int) (GEoptions.getNodeImg().getIconHeight() * z);
                    zoomedIconImg = new ImageIcon(GEoptions.getNodeImg().getImage().getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH));
                    zoomedIconImg.paintIcon(this, g, x - zoomedIconImg.getIconWidth() / 2, y - zoomedIconImg.getIconHeight() / 2);
                }
            }
        }
        g.drawString(this.getZoom() * 100 + "%", this.getVisibleRect().x + 6, this.getVisibleRect().y + 15);
        //g.drawRect(this.rect.x, this.rect.y, this.rect.width, this.rect.height);
        g.drawString(mouse.toString(), this.getVisibleRect().x + 6, this.getVisibleRect().y + 30);
        g.drawString(dragstartpoint.toString(), this.getVisibleRect().x + 6, this.getVisibleRect().y + 45);
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
        ActionKeyPressDown adown = new ActionKeyPressDown(this);
        ActionKeyPressUp aup = new ActionKeyPressUp(this);
        ActionKeyPressLeft aleft = new ActionKeyPressLeft(this);
        ActionKeyPressRight aright = new ActionKeyPressRight(this);
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DELETE"), "actiondelete");
        this.getActionMap().put("actiondelete", ad);
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "actionescape");
        this.getActionMap().put("actionescape", ae);
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "actiondown");
        this.getActionMap().put("actiondown", adown);
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "actionup");
        this.getActionMap().put("actionup", aup);
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "actionleft");
        this.getActionMap().put("actionleft", aleft);
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "actionright");
        this.getActionMap().put("actionright", aright);
        setAutoscrolls(true);
//        this.setBorder(new LineBorder(Color.black));
        this.revalidate();
        this.zoom = 0;
        this.rect = new Rectangle(0, 0, 1, 1);
        this.dragstartpoint = new Point(0, 0);
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
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
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

    public void corrigeTamanho() {
        Point lp = this.getPointZoomed(this.getGC().getLastPoint());
        Dimension D = new Dimension(lp.x + GEoptions.getScrollMargin() + (GEoptions.getNodeImg().getIconWidth() / 2), lp.y + GEoptions.getScrollMargin() + (GEoptions.getNodeImg().getIconHeight() / 2));
        if (D.height < this.getParent().getSize().height) {
            D.height = this.getParent().getSize().height;
        }
        if (D.width < this.getParent().getSize().width) {
            D.width = this.getParent().getSize().width;
        }
        this.setPreferredSize(D);
        this.revalidate();
    }

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        this.corrigeTamanho();
        int widght, height;        
        if (this.buttommouse == MouseEvent.BUTTON3) {
            widght = dragstartpoint.x-evt.getX();
            height = dragstartpoint.y-evt.getY();            
            rect = new Rectangle(this.getVisibleRect());
            rect.translate(widght, height);
        } else {
            rect.setLocation(evt.getX() + (GEoptions.getNodeImg().getIconWidth() / 2),
                    evt.getY() + (GEoptions.getNodeImg().getIconHeight() / 2));
        }
        scrollRectToVisible(rect);
        this.revalidate();
        this.repaint();
        if (this.Hightlighted != null && this.Hightlighted.getClass() == NodeControl.class) {
            NodeControl nc = (NodeControl) this.Hightlighted;
            if (this.getPointOri(evt.getPoint()).x > (GEoptions.getNodeImg().getIconWidth() / 2) && this.getPointOri(evt.getPoint()).y > (GEoptions.getNodeImg().getIconHeight() / 2)) {//Impede que Node seja movido para uma coordenada fora da tela
                nc.setPoint(this.getPointOri(evt.getPoint()));
                this.repaint();
            }
        }
    }//GEN-LAST:event_formMouseDragged

    private void scroll(int dir, int speed) {
        Rectangle rect = new Rectangle(0, 0, 1, 1);
        int x = 0, y = 0;
        switch (dir) {
            case 1: //up
                x = this.getVisibleRect().x;
                y = this.getVisibleRect().y - speed;
                break;
            case 2://down
                x = this.getVisibleRect().x;
                y = this.getVisibleRect().y + this.getVisibleRect().height + speed;
                break;
            case 3://left
                x = this.getVisibleRect().x - speed;
                y = this.getVisibleRect().y;
                break;
            case 4://right
                x = this.getVisibleRect().x + this.getVisibleRect().width + speed;
                y = this.getVisibleRect().y;
                break;
        }
        rect.setLocation(x, y);
        scrollRectToVisible(rect);
        this.repaint();
    }

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
            this.corrigeTamanho();
            this.repaint();
            //TODO scroll up/down
        }
    }//GEN-LAST:event_formMouseWheelMoved
    private Point dragstartpoint;
    private int buttommouse;
    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        dragstartpoint = evt.getPoint();
        this.buttommouse = evt.getButton();
//        System.out.println("d"+dragstartpoint);
    }//GEN-LAST:event_formMousePressed

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return getPreferredSize();
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 10;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 10;
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

    static class ActionKeyPressDown extends AbstractAction {

        private GraphPane GP;

        private ActionKeyPressDown(GraphPane gp) {
            this.GP = gp;
        }

        public GraphPane getGP() {
            return GP;
        }

        public void setGP(GraphPane GP) {
            this.GP = GP;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            GP.scroll(2, GEoptions.getScrollSpeed());
        }
    }

    static class ActionKeyPressLeft extends AbstractAction {

        private GraphPane GP;

        private ActionKeyPressLeft(GraphPane gp) {
            this.GP = gp;
        }

        public GraphPane getGP() {
            return GP;
        }

        public void setGP(GraphPane GP) {
            this.GP = GP;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            GP.scroll(3, GEoptions.getScrollSpeed());
        }
    }

    static class ActionKeyPressRight extends AbstractAction {

        private GraphPane GP;

        private ActionKeyPressRight(GraphPane gp) {
            this.GP = gp;
        }

        public GraphPane getGP() {
            return GP;
        }

        public void setGP(GraphPane GP) {
            this.GP = GP;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            GP.scroll(4, GEoptions.getScrollSpeed());
        }
    }

    static class ActionKeyPressUp extends AbstractAction {

        private GraphPane GP;

        private ActionKeyPressUp(GraphPane gp) {
            this.GP = gp;
        }

        public GraphPane getGP() {
            return GP;
        }

        public void setGP(GraphPane GP) {
            this.GP = GP;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            GP.scroll(1, GEoptions.getScrollSpeed());
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
