/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles.brands;

import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import vehicles.vehicles.APIVehicles;
import static javax.xml.bind.DatatypeConverter.parseInt;
/**
 *
 * @author Rui Barcelos
 */
public class Table_Brand extends javax.swing.JFrame {
    APIBrand api = new APIBrand();
    private final String login;
    /**
     * Creates new form Table_Brand
     * @param login
     * @throws java.lang.Exception
     */
    public Table_Brand(String login) throws Exception {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../../img/imageedit_4_8303763918.png")));
        Table(api.Brand(login));
        row(0);
        this.login = login;
    }
    private void row(int selected){
        DefaultTableModel mod = (DefaultTableModel) tbl_brand.getModel();
        l_idBrand.setText((String) mod.getValueAt(selected, 0));
        l_nameBrand.setText((String) mod.getValueAt(selected, 1));
    }
    private void Table(String[][] list){
        DefaultTableModel mod = (DefaultTableModel) tbl_brand.getModel();
        Object[] row = new Object[2];
        for (String[] list1 : list) {
            for (int i = 0; i < row.length; i++) {
                row[i] = list1[i];
            }
            mod.addRow(row);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_brand = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        l_idBrand = new javax.swing.JLabel();
        l_nameBrand = new javax.swing.JLabel();
        bt_edit = new javax.swing.JButton();
        bt_info = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Lista de Marcas");

        tbl_brand.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N.º da Marca", "Marca"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_brand.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_brandMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_brand);
        if (tbl_brand.getColumnModel().getColumnCount() > 0) {
            tbl_brand.getColumnModel().getColumn(0).setResizable(false);
            tbl_brand.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel1.setText("ID:");

        jLabel2.setText("Nome:");

        l_idBrand.setText("id");

        l_nameBrand.setText("name");

        bt_edit.setText("Editar");
        bt_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_editActionPerformed(evt);
            }
        });

        bt_info.setText("Info");
        bt_info.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_infoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(l_idBrand)
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(l_nameBrand)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_edit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_info)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(l_idBrand)
                    .addComponent(l_nameBrand)
                    .addComponent(bt_edit)
                    .addComponent(bt_info))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_brandMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_brandMouseClicked
        int i = tbl_brand.getSelectedRow();
        row(i);
    }//GEN-LAST:event_tbl_brandMouseClicked

    private void bt_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_editActionPerformed
        try {
            new EditBrand(this.login,parseInt(l_idBrand.getText())).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Brand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_editActionPerformed

    private void bt_infoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_infoActionPerformed
        new InfoBrand(login, parseInt(l_idBrand.getText())).setVisible(true);
    }//GEN-LAST:event_bt_infoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_edit;
    private javax.swing.JButton bt_info;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel l_idBrand;
    private javax.swing.JLabel l_nameBrand;
    private javax.swing.JTable tbl_brand;
    // End of variables declaration//GEN-END:variables
}