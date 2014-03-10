/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import viewcontrol.mode;

/**
 *
 * @author Rafael
 */
public class ToolPanel extends javax.swing.JPanel {
    private MainFrame MF;

    public MainFrame getMF() {
        return MF;
    }

    public void setMF(MainFrame MF) {
        this.MF = MF;
    }    
    
    public void resetButtom(){
        this.NodeButtom.setSelected(false);
        this.EdgeButtom.setSelected(false);
    }
    /**
     * Creates new form ToolPanel
     */
    public ToolPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        NodeButtom = new javax.swing.JToggleButton();
        EdgeButtom = new javax.swing.JToggleButton();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        NodeButtom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/Icon-AddNode.png"))); // NOI18N
        NodeButtom.setMnemonic('V');
        NodeButtom.setToolTipText("Adicionar Vértice");
        NodeButtom.setMaximumSize(new java.awt.Dimension(40, 40));
        NodeButtom.setMinimumSize(new java.awt.Dimension(40, 40));
        NodeButtom.setPreferredSize(new java.awt.Dimension(40, 40));
        NodeButtom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NodeButtomActionPerformed(evt);
            }
        });
        add(NodeButtom);

        EdgeButtom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/Icon-AddAresta.png"))); // NOI18N
        EdgeButtom.setMnemonic('A');
        EdgeButtom.setToolTipText("Adicionar Aresta");
        EdgeButtom.setMaximumSize(new java.awt.Dimension(40, 40));
        EdgeButtom.setMinimumSize(new java.awt.Dimension(40, 40));
        EdgeButtom.setPreferredSize(new java.awt.Dimension(40, 40));
        EdgeButtom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EdgeButtomActionPerformed(evt);
            }
        });
        add(EdgeButtom);
    }// </editor-fold>//GEN-END:initComponents

    private void NodeButtomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NodeButtomActionPerformed
        this.MF.getGC().setMode(mode.addNode);
    }//GEN-LAST:event_NodeButtomActionPerformed

    private void EdgeButtomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EdgeButtomActionPerformed
        this.MF.getGC().setMode(mode.addEdge);
    }//GEN-LAST:event_EdgeButtomActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton EdgeButtom;
    private javax.swing.JToggleButton NodeButtom;
    // End of variables declaration//GEN-END:variables
}
