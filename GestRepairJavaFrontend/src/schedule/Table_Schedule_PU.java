/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedule;

import java.awt.Toolkit;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Rui Barcelos
 */
public final class Table_Schedule_PU extends javax.swing.JFrame {

    public String log;
    public int id;
    APISchedule api = new APISchedule();

    /**
     * Creates new form Table_Vehicles
     *
     * @param login
     * @param id
     * @throws java.io.IOException
     * @throws java.net.MalformedURLException
     * @throws org.json.simple.parser.ParseException
     * @throws java.text.ParseException
     */
    public Table_Schedule_PU(String login, int id) throws IOException, MalformedURLException, ParseException, java.text.ParseException {
        log = login;
        this.id = id;
        initComponents();
        showTable(api.ValuesToTable(login, id));
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../img/imageedit_4_8303763918.png")));
    }

    public void showTable(String[][] list) {
        DefaultTableModel mod = (DefaultTableModel) tbl_vehicles.getModel();
        Object[] row = new Object[10];
        for (String[] list1 : list) {
            for (int i = 0; i < row.length; i++) {
                if (i == 5 || i == 6) {
                    row[i] = list1[i];
                } else {
                    row[i] = list1[i];
                }
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
        tbl_vehicles = new javax.swing.JTable();
        bt_edit = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        l_idRepair = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tbl_vehicles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Serviço", "Veículo", "Data", "Activo?"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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

        bt_edit.setText("Editar Reparação");
        bt_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_editActionPerformed(evt);
            }
        });

        jLabel7.setText("Agendamento N.º:");

        l_idRepair.setText("agendamento");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(l_idRepair)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_edit)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(l_idRepair)
                            .addComponent(jLabel7))
                        .addContainerGap(54, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(bt_edit)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_vehiclesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_vehiclesMouseClicked
        int i = tbl_vehicles.getSelectedRow();
        TableModel mod = tbl_vehicles.getModel();
        l_idRepair.setText((String) mod.getValueAt(i, 0));
    }//GEN-LAST:event_tbl_vehiclesMouseClicked

    private void bt_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_editActionPerformed
        // TODO add your handling code here:
        try {
            //api.PutVehicle(log,l_idVehicle.getText(), tf_registration.getText(), tf_horsepower.getText(), tf_displacement.getText(), tf_kilometer.getText(), tf_frontTire.getText(), tf_rearTire.getText());
            DefaultTableModel mod = (DefaultTableModel) tbl_vehicles.getModel();
            mod.setRowCount(0);
            showTable(api.ValuesToTable(log, id));
        } catch (IOException | ParseException | java.text.ParseException ex) {
            Logger.getLogger(Table_Schedule_PU.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_editActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_edit;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel l_idRepair;
    private javax.swing.JTable tbl_vehicles;
    // End of variables declaration//GEN-END:variables
}