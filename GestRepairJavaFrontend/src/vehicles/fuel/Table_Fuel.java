/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles.fuel;

import java.awt.Toolkit;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static javax.xml.bind.DatatypeConverter.parseInt;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Rui Barcelos
 */
public final class Table_Fuel extends javax.swing.JFrame {
    public String login;
    APIFuel api = new APIFuel();
    /**
     * Creates new form Table_Vehicles
     * @param login
     * @throws java.io.IOException
     * @throws java.net.MalformedURLException
     * @throws org.json.simple.parser.ParseException
     */
    public Table_Fuel(String login) throws Exception {
        this.login = login;
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../../img/imageedit_4_8303763918.png")));
        showTable(api.Fuel(login));
        row(0);
    }
        
    public void showTable(String[][] list) {
        DefaultTableModel mod = (DefaultTableModel) tbl_fuels.getModel();
        Object[] row = new Object[2];
        for (String[] list1 : list) {
            for (int i = 0; i < row.length; i++) {
                row[i] = list1[i];
            }
            mod.addRow(row);
        }
    }
    private void row(int val){
        TableModel mod = tbl_fuels.getModel();
        l_id.setText((String) mod.getValueAt(val, 0));
        l_registration.setText((String) mod.getValueAt(val, 1));
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
        tbl_fuels = new javax.swing.JTable();
        bt_edit = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        l_id = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        l_registration = new javax.swing.JLabel();
        bt_info = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Lista de Combustíveis");

        tbl_fuels.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Combustível"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_fuels.setColumnSelectionAllowed(true);
        tbl_fuels.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_fuelsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_fuels);
        tbl_fuels.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        bt_edit.setText("Editar");
        bt_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_editActionPerformed(evt);
            }
        });

        jLabel7.setText("ID:");

        l_id.setText("id");

        jLabel1.setText("Combustível");

        l_registration.setText("combustível");

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
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(l_id, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(l_registration)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 248, Short.MAX_VALUE)
                .addComponent(bt_info)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_edit)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l_id)
                    .addComponent(jLabel7)
                    .addComponent(jLabel1)
                    .addComponent(l_registration)
                    .addComponent(bt_edit)
                    .addComponent(bt_info))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void tbl_fuelsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_fuelsMouseClicked
        int i = tbl_fuels.getSelectedRow();
        row(i); 
    }//GEN-LAST:event_tbl_fuelsMouseClicked

    private void bt_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_editActionPerformed
        // TODO add your handling code here:
        try {
            new EditFuel(this.login, parseInt(l_id.getText())).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Fuel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_editActionPerformed

    private void bt_infoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_infoActionPerformed
        try {
            new InfoFuel(this.login, parseInt(l_id.getText())).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Fuel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_infoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_edit;
    private javax.swing.JButton bt_info;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel l_id;
    private javax.swing.JLabel l_registration;
    private javax.swing.JTable tbl_fuels;
    // End of variables declaration//GEN-END:variables
}
