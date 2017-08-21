package vehicles.models;

import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import vehicles.vehicles.APIVehicles;
import static javax.xml.bind.DatatypeConverter.parseInt;
import vehicles.brands.APIBrand;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rui Barcelos
 */
public class Table_Model extends javax.swing.JFrame {

    private final String login;
    APIModel api = new APIModel();
    APIBrand apiBrand = new APIBrand();
    /**
     * Creates new form ListBrand
     *
     * @param login
     */
    public Table_Model(String login) {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../../img/imageedit_4_8303763918.png")));
        ListBrand(login);
        this.login = login;
    }

    public void cleanTable() {
        DefaultTableModel mod = (DefaultTableModel) tbl_model.getModel();
        mod.setRowCount(0);
    }

    /**
     *
     * @param login
     * @param id
     */
    private void ListBrand(String login) {
        try {
            String brand[][] = apiBrand.Brand(login);
            for (int i = 0; i < brand.length; i++) {
                cb_brand.addItem(brand[i][1]);
            }
            Table(api.Model(login, cb_val(brand)));
            row(0);
        } catch (Exception ex) {
            Logger.getLogger(Table_Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int cb_val(String[][] list) throws Exception {
        int id = cb_brand.getSelectedIndex();
        return parseInt(list[(id==0)?id:id-1][0]);
    }

    private void row(int selected) {
        DefaultTableModel mod = (DefaultTableModel) tbl_model.getModel();
        l_idModel.setText((String) mod.getValueAt(selected, 0));
        l_nameModel.setText((String) mod.getValueAt(selected, 1));
    }

    private void Table(String[][] list) {
        DefaultTableModel mod = (DefaultTableModel) tbl_model.getModel();
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
        tbl_model = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        l_nameModel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        l_idModel = new javax.swing.JLabel();
        bt_edit_model = new javax.swing.JButton();
        bt_info = new javax.swing.JButton();
        cb_brand = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Lista de Modelos");

        tbl_model.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Modelo", "Nome do Modelo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_model.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_modelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_model);

        jLabel1.setText("Marca:");

        jLabel3.setText("Nome do Modelo:");

        l_nameModel.setText("nomeMarca");

        jLabel4.setText("ID Modelo:");

        l_idModel.setText("idmodelo");

        bt_edit_model.setText("Editar Modelo");
        bt_edit_model.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_edit_modelActionPerformed(evt);
            }
        });

        bt_info.setText("Info");
        bt_info.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_infoActionPerformed(evt);
            }
        });

        cb_brand.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        cb_brand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_brandActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_brand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_idModel)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_nameModel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_info)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_edit_model)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cb_brand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(l_idModel)
                    .addComponent(jLabel3)
                    .addComponent(l_nameModel))
                .addGap(22, 22, 22))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_edit_model)
                    .addComponent(bt_info))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_modelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_modelMouseClicked
        int i = tbl_model.getSelectedRow();
        row(i);
    }//GEN-LAST:event_tbl_modelMouseClicked

    private void cb_brandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_brandActionPerformed
        try {
            cleanTable();
            String brand[][];
            brand = apiBrand.Brand(this.login);
            Table(api.Model(login, cb_val(brand)));
        } catch (Exception ex) {
            Logger.getLogger(Table_Model.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_cb_brandActionPerformed

    private void bt_edit_modelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_edit_modelActionPerformed
        try {
            new EditModel(login, parseInt(l_idModel.getText()), (String) cb_brand.getSelectedItem()).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_edit_modelActionPerformed

    private void bt_infoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_infoActionPerformed
        try {
            new InfoModel(this.login, parseInt(l_idModel.getText()), (String) cb_brand.getSelectedItem()).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_infoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_edit_model;
    private javax.swing.JButton bt_info;
    private javax.swing.JComboBox cb_brand;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel l_idModel;
    private javax.swing.JLabel l_nameModel;
    private javax.swing.JTable tbl_model;
    // End of variables declaration//GEN-END:variables
}