/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

/**
 *
 * @author Rafael
 */
public class GraphPaneContainer extends javax.swing.JPanel {

    public GraphPane getGP() {
        return GP;
    }

    public void setGP(GraphPane GP) {
        this.GP = GP;
    }

    /**
     * Creates new form GraphFrame
     */
    public GraphPaneContainer() {
        initComponents();        
    }

    public GraphPane getGraphPane() {
        return GP;
    }

    public void setGraphPane(GraphPane graphPane) {
        this.GP = graphPane;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TabsPane = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        GP = new view.GraphPane();

        setMinimumSize(new java.awt.Dimension(300, 300));
        setPreferredSize(new java.awt.Dimension(300, 300));
        setLayout(new java.awt.BorderLayout());

        TabsPane.setName(""); // NOI18N

        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setMinimumSize(new java.awt.Dimension(1000, 1000));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1000, 1000));

        jPanel1.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout GPLayout = new javax.swing.GroupLayout(GP);
        GP.setLayout(GPLayout);
        GPLayout.setHorizontalGroup(
            GPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 515, Short.MAX_VALUE)
        );
        GPLayout.setVerticalGroup(
            GPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );

        jPanel1.add(GP, java.awt.BorderLayout.CENTER);

        jScrollPane1.setViewportView(jPanel1);

        TabsPane.addTab("Grafo", jScrollPane1);
        jScrollPane1.getAccessibleContext().setAccessibleDescription("");

        add(TabsPane, java.awt.BorderLayout.CENTER);
        TabsPane.getAccessibleContext().setAccessibleName("Tabs");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.GraphPane GP;
    private javax.swing.JTabbedPane TabsPane;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}