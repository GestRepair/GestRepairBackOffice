/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repairs.repairs;

import java.awt.Toolkit;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static javax.xml.bind.DatatypeConverter.parseInt;
import repairs.employers.AddEmployerRepair;
import repairs.employers.Table_Employer_Repair;

/**
 *
 * @author Rui Barcelos
 */
public final class Table_Repairs extends javax.swing.JFrame {

    public String login;
    APIRepair api = new APIRepair();
    private final int idService;

    /**
     * Creates new form Table_Vehicles
     *
     * @param login
     * @param idService
     * @throws java.io.IOException
     * @throws java.net.MalformedURLException
     * @throws org.json.simple.parser.ParseException
     */
    public Table_Repairs(String login,int idService) throws Exception {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../../img/imageedit_4_8303763918.png")));
        showTable(api.ListRepairs(login, 0));
        row(0);
        this.login = login;
        this.idService = idService;
    }

    public void showTable(String[][] list) {
        DefaultTableModel mod = (DefaultTableModel) tbl_repair.getModel();
        Object[] row = new Object[8];
        for (String[] list1 : list) {
            for (int i = 0; i < row.length; i++) {
                row[i] = list1[i];
            }
            mod.addRow(row);
        }
    }
    private void row(int val) {
        TableModel mod = tbl_repair.getModel();
        if (mod.getRowCount() > 0) {
            l_idRepair.setText(mod.getValueAt(val, 0) + "");
        }else{
            jLabel7.setText("");
            l_idRepair.setText("Não contem Dados!");
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
        tbl_repair = new javax.swing.JTable();
        bt_edit = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        l_idRepair = new javax.swing.JLabel();
        bt_info = new javax.swing.JButton();
        bt_list_empl = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Lista de Reparações");

        tbl_repair.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID","Veiculo","Descrição", "Preço", "Estado", "Data Inicio", "Data Conclusão", "Resolução"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_repair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_repairMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_repair);
        tbl_repair.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        bt_edit.setText("Editar Reparação");
        bt_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_editActionPerformed(evt);
            }
        });

        jLabel7.setText("Reparação N.º");

        l_idRepair.setText("reparação");

        bt_info.setText("Info");
        bt_info.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_infoActionPerformed(evt);
            }
        });

        bt_list_empl.setText("Ver Funcionários");
        bt_list_empl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_list_emplActionPerformed(evt);
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(l_idRepair)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_list_empl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_info)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_edit)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(l_idRepair)
                            .addComponent(jLabel7)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bt_edit)
                            .addComponent(bt_info)
                            .addComponent(bt_list_empl))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_repairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_repairMouseClicked
        int i = tbl_repair.getSelectedRow();
        row(i);
    }//GEN-LAST:event_tbl_repairMouseClicked

    private void bt_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_editActionPerformed
        // TODO add your handling code here:
        try {
            int i = tbl_repair.getSelectedRow();
            TableModel mod = tbl_repair.getModel();
            if(i<0){ i=0;}
            int idRepair = parseInt((String) mod.getValueAt(i, 0));  
            new EditRepair(this.login,idRepair).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Repairs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_editActionPerformed

    private void bt_infoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_infoActionPerformed
        try {
            int i = tbl_repair.getSelectedRow();
            TableModel mod = tbl_repair.getModel();
            if(i<0){ i=0;}
            int idRepair = parseInt((String) mod.getValueAt(i, 0));         
            new InfoRepair(this.login,idRepair,this.idService).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Repairs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_infoActionPerformed

    private void bt_list_emplActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_list_emplActionPerformed
        try {
            int i = tbl_repair.getSelectedRow();
            TableModel mod = tbl_repair.getModel();
            if(i<0){ i=0;}
            int idRepair = parseInt((String) mod.getValueAt(i, 0));         
            new Table_Employer_Repair(this.login,idRepair,this.idService).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(Table_Repairs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_list_emplActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_edit;
    private javax.swing.JButton bt_info;
    private javax.swing.JButton bt_list_empl;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel l_idRepair;
    private javax.swing.JTable tbl_repair;
    // End of variables declaration//GEN-END:variables
}
