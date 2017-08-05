/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles;

import java.awt.Toolkit;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Rui Barcelos
 */
public final class Table_Vehicles_PU extends javax.swing.JFrame {
    public String log;
    public int idV;
    APIVehicles api = new APIVehicles();
    /**
     * Creates new form Table_Vehicles
     * @param login
     * @param id
     * @throws java.io.IOException
     * @throws java.net.MalformedURLException
     * @throws org.json.simple.parser.ParseException
     */
    public Table_Vehicles_PU(String login, int id) throws IOException, MalformedURLException, ParseException {
        log = login;
        idV = id;
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../img/imageedit_4_8303763918.png")));
        showTable(DataToTable(api.GetVehicles(login,id)));
       
    }
    
     public String[][] DataToTable(String list) {
        try {
            JSONObject jo = (JSONObject) new JSONParser().parse(list);
            JSONArray data = (JSONArray) jo.get("data");
            String[][] dataTable = new String[data.size()][11];
            for (int i = 0; i < data.size(); i++) {

                JSONObject datas = (JSONObject) data.get(i);
                dataTable[i][0] = (long) datas.get("idVehicle") + "";
                dataTable[i][1] = (String) datas.get("registration");
                dataTable[i][2] = (String) datas.get("nameBrand");
                dataTable[i][3] = (String) datas.get("nameModel");
                dataTable[i][4] = (long) datas.get("horsepower")+ "";
                dataTable[i][5] = (long) datas.get("displacement")+ "";
                dataTable[i][6] = (long) datas.get("kilometers")+ "";
                dataTable[i][7] = (String) datas.get("nameFuel");
                dataTable[i][8] = (String) datas.get("fronttiresize");
                dataTable[i][9] = (String) datas.get("reartiresize");
                dataTable[i][10] = (String) datas.get("date");  
            };
            return dataTable;
        } catch (ParseException pe) {
            JOptionPane.showMessageDialog(this,"Erro a mostrar a tabela");
            return null;
        }
    }
    
    public void showTable(String[][] list) {
        DefaultTableModel mod = (DefaultTableModel) tbl_vehicles.getModel();
        Object[] row = new Object[11];
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
        tbl_vehicles = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        tf_registration = new javax.swing.JFormattedTextField();
        bt_edit = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        tf_displacement = new javax.swing.JTextField();
        tf_horsepower = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tf_kilometer = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tf_frontTire = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tf_rearTire = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        l_idVehicle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Lista de viaturas");

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

        try {
            tf_registration.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("AA-AA-AA")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tf_registration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_registrationActionPerformed(evt);
            }
        });

        bt_edit.setText("Editar Veiculo");
        bt_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_editActionPerformed(evt);
            }
        });

        jLabel2.setText("Cilindrada");

        jLabel3.setText("CV");

        jLabel4.setText("Quilómetros");

        jLabel5.setText("Pneu Frente");

        jLabel6.setText("Pneu Trás");

        jLabel7.setText("Veículo N.º");

        l_idVehicle.setText("Veículo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bt_edit))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(l_idVehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tf_registration)
                            .addComponent(tf_displacement, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_horsepower, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                            .addComponent(tf_kilometer))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_frontTire, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                            .addComponent(tf_rearTire))
                        .addGap(88, 88, 88)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tf_horsepower, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(tf_registration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l_idVehicle)
                                .addComponent(jLabel7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tf_kilometer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tf_displacement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_frontTire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_rearTire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_edit)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_vehiclesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_vehiclesMouseClicked
        int i = tbl_vehicles.getSelectedRow();
        TableModel mod = tbl_vehicles.getModel();
        l_idVehicle.setText((String) mod.getValueAt(i, 0));
        tf_registration.setText((String) mod.getValueAt(i, 1));
        tf_horsepower.setText((String) mod.getValueAt(i, 4));
        tf_displacement.setText((String) mod.getValueAt(i, 5));
        tf_kilometer.setText((String) mod.getValueAt(i, 6));
        tf_frontTire.setText((String) mod.getValueAt(i, 8));
        tf_rearTire.setText((String) mod.getValueAt(i, 9));
    }//GEN-LAST:event_tbl_vehiclesMouseClicked

    private void bt_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_editActionPerformed
        // TODO add your handling code here:
        try {
            api.PutVehicle(log,l_idVehicle.getText(), tf_registration.getText(), tf_horsepower.getText(), tf_displacement.getText(), tf_kilometer.getText(), tf_frontTire.getText(), tf_rearTire.getText());
            DefaultTableModel mod = (DefaultTableModel)tbl_vehicles.getModel();
            mod.setRowCount(0);
            showTable(DataToTable(api.GetVehicles(log,idV)));
        } catch (Exception ex) {
            Logger.getLogger(Table_Vehicles_PU.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_editActionPerformed

    private void tf_registrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_registrationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_registrationActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_edit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel l_idVehicle;
    private javax.swing.JTable tbl_vehicles;
    private javax.swing.JTextField tf_displacement;
    private javax.swing.JTextField tf_frontTire;
    private javax.swing.JTextField tf_horsepower;
    private javax.swing.JTextField tf_kilometer;
    private javax.swing.JTextField tf_rearTire;
    private javax.swing.JFormattedTextField tf_registration;
    // End of variables declaration//GEN-END:variables
}
