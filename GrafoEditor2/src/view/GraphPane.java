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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JToolTip;
import javax.swing.Scrollable;
import javax.swing.ToolTipManager;
import viewcontrol.EdgeControl;
import viewcontrol.GraphControl;
import viewcontrol.ItemControl;
import viewcontrol.NodeControl;
import viewcontrol.mode;

public class GraphPane extends javax.swing.JPanel implements Scrollable, KeyListener {

    private GraphControl GC;
    private ItemControl Hightlighted;
    private Point mouse;
    private int zoom;
    private Rectangle scrollrect, selectionrect;
    private boolean showseletion;
    private Point dragstartpoint;
    private int buttommouse;
    private boolean shiftStatus;
    private boolean ctrilStatus;

    /**
     * Creates new form GraphPane
     */
    public GraphPane() {
        initComponents();
        this.showseletion = false;
        this.selectionrect = new Rectangle(0, 0, 1, 1);
        this.mouse = new Point(0, 0);
        this.GC = new GraphControl(this);
//        ActionKeyPressDel ad = new ActionKeyPressDel(this);
//        ActionKeyPressEsc ae = new ActionKeyPressEsc(this);
//        ActionKeyPressDown adown = new ActionKeyPressDown(this);
//        ActionKeyPressUp aup = new ActionKeyPressUp(this);
//        ActionKeyPressLeft aleft = new ActionKeyPressLeft(this);
//        ActionKeyPressRight aright = new ActionKeyPressRight(this);
//        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DELETE"), "actiondelete");
//        this.getActionMap().put("actiondelete", ad);
//        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "actionescape");
//        this.getActionMap().put("actionescape", ae);
//        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "actiondown");
//        this.getActionMap().put("actiondown", adown);
//        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "actionup");
//        this.getActionMap().put("actionup", aup);
//        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "actionleft");
//        this.getActionMap().put("actionleft", aleft);
//        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "actionright");
//        this.getActionMap().put("actionright", aright);
        setAutoscrolls(true);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.revalidate();
        this.zoom = 0;
        this.scrollrect = new Rectangle(0, 0, 1, 1);
        this.selectionrect = new Rectangle(0, 0, 1, 1);
        this.dragstartpoint = new Point(0, 0);
        this.setToolTipText("GraphPane");
//        JToolTip JT = this.createToolTip();
//        JT.setTipText("GP");
//        JT.setComponent(this);
//        JT.setVisible(true);
    }
  

    @Override
    public JToolTip createToolTip() {
        JToolTip jt = super.createToolTip();
        ToolTipManager.sharedInstance().setDismissDelay(1000000);        
        return jt;
    }
    
    

    public boolean isShiftStatus() {
        return shiftStatus;
    }

    public void setShiftStatus(boolean shiftStatus) {
        this.shiftStatus = shiftStatus;
    }

    public boolean isCtrilStatus() {
        return ctrilStatus;
    }

    public void setCtrilStatus(boolean ctrilStatus) {
        this.ctrilStatus = ctrilStatus;
    }

    /**
     *
     * @return
     */
    public GraphControl getGC() {
        return GC;
    }

    /**
     *
     * @param GC
     */
    public void setGC(GraphControl GC) {
        this.GC = GC;
    }

    /**
     *
     * @return
     */
    public ItemControl getHightlighted() {
        return Hightlighted;
    }

    /**
     *
     * @param Hightlighted
     */
    public void setHightlighted(ItemControl Hightlighted) {
        this.Hightlighted = Hightlighted;
    }

    /**
     *
     * @return
     */
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
                if (ehl == ec || this.selectionrect.contains(ec.getA().getPoint())||this.selectionrect.contains(ec.getB().getPoint())) {
                    g.setColor(GEoptions.getHightlight());
                }
                if (esel == ec || this.GC.isGroupSelected(ec)) {
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
                        xz2 = (int) (this.mouse.x * z),
                        yz2 = (int) (this.mouse.y * z);
                g.drawLine(xz, yz, xz2, yz2);
                g.setColor(GEoptions.getDefaultcolor());
            }

            for (int i = 0; i < GC.getNCsize(); i++) {
                nc = this.GC.getNC(i);
                x = this.getPointZoomed(nc.getPoint()).x;
                y = this.getPointZoomed(nc.getPoint()).y;
                if (nsel == nc || this.GC.isGroupSelected(nc)) {
                    imgWidth = (int) (GEoptions.getNodeSel().getIconHeight() * z);
                    imgHeight = (int) (GEoptions.getNodeSel().getIconHeight() * z);
                    zoomedIconSel = new ImageIcon(GEoptions.getNodeSel().getImage().getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH));
                    zoomedIconSel.paintIcon(this, g, x - zoomedIconSel.getIconWidth() / 2, y - zoomedIconSel.getIconHeight() / 2);
                } else if (nhl == nc || this.selectionrect.contains(nc.getPoint())) {
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
        if (this.getGC().getMode() == mode.addNode) {
            imgWidth = (int) (GEoptions.getNodeHL().getIconHeight() * z);
            imgHeight = (int) (GEoptions.getNodeHL().getIconHeight() * z);
            zoomedIconHL = new ImageIcon(GEoptions.getNodeHL().getImage().getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH));
            zoomedIconHL.paintIcon(this, g, this.mouse.x - zoomedIconHL.getIconWidth() / 2, this.mouse.y - zoomedIconHL.getIconHeight() / 2);
        }
        g.drawString(this.getZoom() * 100 + "%", this.getVisibleRect().x + 6, this.getVisibleRect().y + 15);
        if (this.showseletion) {
            
            g.drawRect(this.selectionrect.x, this.selectionrect.y, this.selectionrect.width, this.selectionrect.height);
            g.setColor(GEoptions.getSelRect());
            g.fillRect(this.selectionrect.x, this.selectionrect.y, this.selectionrect.width, this.selectionrect.height);
            g.setColor(GEoptions.getDefaultcolor());
        }
        g.drawString(mouse.toString(), this.getVisibleRect().x + 6, this.getVisibleRect().y + 30);
        //g.drawString(dragstartpoint.toString(), this.getVisibleRect().x + 6, this.getVisibleRect().y + 45);
        //g.drawString(getPointZoomed(mouse).toString()+" "+this.getZoom()*100+"%", 6,30);
        //g.drawString(getPointOri(getPointZoomed(mouse)).toString(), 6,45);
    }

    /**
     *
     * @param ori
     * @return
     */
    public Point getPointZoomed(Point ori) {
        Point p;
        int nx = (int) (ori.x * getZoom()),
                ny = (int) (ori.y * getZoom());
        p = new Point(nx, ny);
        return p;
    }

    /**
     * Recupera o ponto real dado um ponto afetado por zoom
     *
     * @param zoomed
     * @return
     */
    public Point getPointOri(Point zoomed) {
        Point p;
        int nx = (int) (zoomed.x / getZoom()),
                ny = (int) (zoomed.y / getZoom());
        p = new Point(nx, ny);
        return p;
    }

    /**
     * This method is called from within the constructor to
     * initialize the form. WARNING: Do NOT modify this
     * code. The content of this method is always
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
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
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
        this.grabFocus();
        if (this.Hightlighted != null) {
            this.GC.GCnotify(Hightlighted);
        } else {
            this.GC.GCnotify(this.getPointOri(evt.getPoint()));
        }
        this.repaint();

    }//GEN-LAST:event_formMouseClicked

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        this.Hightlighted = this.GC.searchPoint(this.getPointOri(evt.getPoint()));
        if(this.Hightlighted!=null){
            ToolTipManager.sharedInstance().setEnabled(true);
            this.setToolTipText(Hightlighted.getName());
        }else{
                ToolTipManager.sharedInstance().setEnabled(false);
        }
        
        this.mouse = this.getPointOri(evt.getPoint());        
        this.repaint();
    }//GEN-LAST:event_formMouseMoved

    /**
     * Procura o último vertice (Mais distânte da origem da
     * área de desenho) e garante que a área de desenho
     * tenha o tamanho adequado para abrigar o grafo inteiro
     */
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
        this.showseletion = true;
        this.corrigeTamanho();
        int widght, height;
        int x, y;
        x = evt.getX() < dragstartpoint.x ? evt.getX() : dragstartpoint.x;
        y = evt.getY() < dragstartpoint.y ? evt.getY() : dragstartpoint.y;
        widght = dragstartpoint.x - evt.getX();
        height = dragstartpoint.y - evt.getY();
        if (this.buttommouse == MouseEvent.BUTTON1) {
            selectionrect = new Rectangle(x, y, Math.abs(widght), Math.abs(height));
            scrollrect.setLocation(evt.getX() + (GEoptions.getNodeImg().getIconWidth() / 2),
                    evt.getY() + (GEoptions.getNodeImg().getIconHeight() / 2));
        } else if (this.buttommouse == MouseEvent.BUTTON3) {
            scrollrect = new Rectangle(this.getVisibleRect());
            scrollrect.translate(widght, height);
        }
        scrollRectToVisible(scrollrect);
        if (this.Hightlighted != null && this.Hightlighted.getClass() == NodeControl.class) {
            this.showseletion = false;
            this.selectionrect = new Rectangle(0, 0, 1, 1);
            NodeControl nc = (NodeControl) this.Hightlighted;
            if (this.getPointOri(evt.getPoint()).x > (GEoptions.getNodeImg().getIconWidth() / 2) && this.getPointOri(evt.getPoint()).y > (GEoptions.getNodeImg().getIconHeight() / 2)) {//Impede que Node seja movido para uma coordenada fora da tela
                nc.setPoint(this.getPointOri(evt.getPoint()));

            }
        }
        this.atualizaDisplay();
    }//GEN-LAST:event_formMouseDragged

    private void atualizaDisplay() {
        this.corrigeTamanho();
        this.repaint();
    }

    private void scroll(int dir, int speed) {
        scrollrect = new Rectangle(0, 0, 1, 1);
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
        scrollrect.setLocation(x, y);
        scrollRectToVisible(scrollrect);
        this.atualizaDisplay();
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
            this.atualizaDisplay();
            //TODO scroll up/down
        } else {
            if (evt.isShiftDown()) {
                this.scroll(evt.getWheelRotation() == 1 ? 4 : 3, 3 * evt.getScrollAmount());
            } else {
                this.scroll(evt.getWheelRotation() == 1 ? 2 : 1, 3 * evt.getScrollAmount());
            }
        }
    }//GEN-LAST:event_formMouseWheelMoved

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        dragstartpoint = evt.getPoint();
        this.buttommouse = evt.getButton();

//        System.out.println("d"+dragstartpoint);
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        this.GC.selectRect(selectionrect);
        this.showseletion = false;
        this.selectionrect = new Rectangle(0, 0, 1, 1);
        this.atualizaDisplay();
    }//GEN-LAST:event_formMouseReleased

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        this.grabFocus();
    }//GEN-LAST:event_formMouseEntered

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

    @Override
    public void keyTyped(KeyEvent e) {
        //System.out.println(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case 16:
                this.shiftStatus = true;
                break;
            case 17:
                this.ctrilStatus = true;
                break;
            case 18:
                System.out.println("Alt");
                break;
            case 38:
//                System.out.println("UP");
                this.scroll(1, GEoptions.getScrollSpeed());
                break;
            case 40:
//                System.out.println("DOWN");
                this.scroll(2, GEoptions.getScrollSpeed());
                break;
            case 37:
//                System.out.println("LEFT");
                this.scroll(3, GEoptions.getScrollSpeed());
                break;
            case 39:
//                System.out.println("RIGHT");
                this.scroll(4, GEoptions.getScrollSpeed());
                break;
            case 127:
//                System.out.println("DEL");
                this.delAction();
                break;
            case 27:
//                System.out.println("ESC");
                this.escAction();
                break;
            case 65:
//                System.out.println("A");
                this.GC.setMode(mode.addEdge);
                break;
            case 86:
//                System.out.println("V");
                this.GC.setMode(mode.addNode);

                break;
            default:
                System.out.println(e.getKeyCode());
        }
        this.atualizaDisplay();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 16:
                this.shiftStatus = false;
                if (this.getGC().getMode() == mode.addEdge || this.getGC().getMode() == mode.addNode) {
                    this.getGC().setMode(mode.sel);
                }
                break;
            case 17:
                this.ctrilStatus = false;
                break;
            case 18:
//                System.out.println("Alt");
                break;
            case 38:
//                System.out.println("UP");
                break;
            case 40:
//                System.out.println("DOWN");
                break;
            case 37:
//                System.out.println("LEFT");
                break;
            case 39:
//                System.out.println("RIGHT");
                break;
            case 127:
//                System.out.println("DEL");
                break;
            case 27:
//                System.out.println("ESC");
                break;
            case 65:
//                System.out.println("A");
                break;
            case 86:
//                System.out.println("V");
                break;
            default:
                System.out.println(e.getKeyCode());
        }
        this.atualizaDisplay();
    }

    private void delAction() {
        for (ItemControl si : this.GC.getSelectionGroup()) {
            this.getGC().removeItem(si);
        }
        if (this.getGC().getSelected() != null) {
            this.getGC().removeItem(this.getGC().getSelected());
            this.atualizaDisplay();
        } else {
            System.out.println("No selection");
        }

    }

    private void escAction() {
        this.getGC().setSelected(null);
        this.getGC().setMode(mode.sel);
        this.atualizaDisplay();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
