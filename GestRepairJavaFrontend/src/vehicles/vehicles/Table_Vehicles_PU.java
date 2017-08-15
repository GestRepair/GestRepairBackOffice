/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles.vehicles;

import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static javax.xml.bind.DatatypeConverter.parseInt;

/**
 *
 * @author Rui Barcelos
 */
public final class Table_Vehicles_PU extends javax.swing.JFrame {
    APIVehicles api = new APIVehicles();
    private final String login;
    private final int id;
    /**
     * Creates new form Table_Vehicles
     * @param login
     * @param id
     * @throws java.lang.Exception
     */
    public Table_Vehicles_PU(String login, int id) throws Exception {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../../img/imageedit_4_8303763918.png")));
        showTable(api.vehicles(login,id));
        row(0);
        this.login = login;
        this.id = id;
    }
    
    private void showTable(String[][] list) {
        DefaultTableModel mod = (DefaultTableModel) tbl_vehicles.getModel();
        Object[] row = new Object[11];
        for (String[] list1 : list) {
            for (int i = 0; i < row.length; i++) {
                row[i] = list1[i];
            }
            mod.addRow(row);
        }
    }
    private void row(int val) {
        TableModel mod = tbl_vehicles.getModel();
        if (mod.getRowCount() > 0) {
            l_idVehicle.setText(mod.getValueAt(val, 0) + "");
            l_registration.setText(mod.getValueAt(val, 1) + "");
        }else{
            jLabel1.setText("");
            jLabel7.setText("");
            l_idVehicle.setText("");
            l_registration.setText("Não contem Dados!");
            bt_edit.setVisible(false);
            bt_info.setVisible(false);
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
        tbl_vehicles = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        bt_edit = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        l_idVehicle = new javax.swing.JLabel();
        l_registration = new javax.swing.JLabel();
        bt_info = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Lista de Viaturas por utilizador");

        tbl_vehicles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Matrícula", "Marca", "Modelo", "CV", "Cilindrada", "Quilómetros", "Combustível", "Roda da Frente", "Roda de Trás", "Data"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_vehicles.setColumnSelectionAllowed(true);
        tbl_vehicles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_vehiclesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_vehicles);
        tbl_vehicles.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jLabel1.setText("Matrícula");

        bt_edit.setText("Editar");
        bt_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_editActionPerformed(evt);
            }
        });

        jLabel7.setText("Veículo N.º");

        l_idVehicle.setText("Veículo");

        l_registration.setText("Matrícula");

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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(l_idVehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(l_registration)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_info)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_edit)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(l_idVehicle)
                    .addComponent(jLabel7)
                    .addComponent(l_registration)
                    .addComponent(bt_edit)
                    .addComponent(bt_info))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_vehiclesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_vehiclesMouseClicked
        int i = tbl_vehicles.getSelectedRow();
        row(i);
    }//GEN-LAST:event_tbl_vehiclesMouseClicked

    private void bt_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_editActionPerformed
        try {
            new EditVehicle(this.login,parseInt(l_idVehicle.getText())).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Vehicles_PU.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_editActionPerformed

    private void bt_infoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_infoActionPerformed
        try {
            new InfoVehicle(this.login,parseInt(l_idVehicle.getText())).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Vehicles_PU.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_infoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_edit;
    private javax.swing.JButton bt_info;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel l_idVehicle;
    private javax.swing.JLabel l_registration;
    private javax.swing.JTable tbl_vehicles;
    // End of variables declaration//GEN-END:variables
}
