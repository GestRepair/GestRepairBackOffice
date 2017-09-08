/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts;

import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Convite
 */
public class MainParts extends javax.swing.JFrame {

    /**
     * Creates new form mainVehicles
     *
     * @param login
     * @param idService
     */
    public MainParts(String login, int idService) {
        initComponents();
        Events(login, idService);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../img/imageedit_4_8303763918.png")));
    }

    private void Events(final String login, final int idService) {
        bt_addParts.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_addPart(evt, login, idService);
            }
        });
        bt_listParts.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_ListPart(evt, login, idService);
            }
        });
        bt_listPartsService.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_ListPartService(evt, login, idService);
            }
        });
    }

    private void BT_addPart(java.awt.event.ActionEvent evt, String login, int idService) {
        try {
            new AddParts(login, idService).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(MainParts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void BT_ListPart(java.awt.event.ActionEvent evt, String login, int idService) {
        try {
            new Table_Parts(login, idService).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(MainParts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void BT_ListPartService(java.awt.event.ActionEvent evt, String login, int idService) {
        try {
            new Table_Parts_Type(login, idService).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(MainParts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bt_listParts = new javax.swing.JButton();
        bt_listPartsService = new javax.swing.JButton();
        bt_addParts = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Menu Peças");
        setResizable(false);

        bt_listParts.setText("Lista de Peças");

        bt_listPartsService.setText("Lista de Peças por Serviço");

        bt_addParts.setText("Adicionar Peça");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(bt_listPartsService, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(bt_listParts, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_addParts, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bt_listParts)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_listPartsService)
                    .addComponent(bt_addParts))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(542, 113));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_addParts;
    private javax.swing.JButton bt_listParts;
    private javax.swing.JButton bt_listPartsService;
    // End of variables declaration//GEN-END:variables
}
