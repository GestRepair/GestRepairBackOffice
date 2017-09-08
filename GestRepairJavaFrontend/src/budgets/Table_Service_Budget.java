/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgets;

import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.table.DefaultTableModel;
import services.APIService;
import users.employer.APIEmployer;

/**
 *
 * @author Rui Barcelos
 */
public final class Table_Service_Budget extends javax.swing.JFrame {

    /**
     * Creates new form AddRepair
     *
     * @param login
     * @param idBudget
     * @throws java.lang.Exception
     */
    public Table_Service_Budget(String login, int idBudget) throws Exception {
        APIBudgets api = new APIBudgets();
        initComponents();
        showTable(api.ListService(login, idBudget));
        Events(login, idBudget);
    }

    private void showTable(String[][] list) {
        DefaultTableModel mod = (DefaultTableModel) tbl_employers_repair.getModel();
        mod.setRowCount(0);
        Object[] row = new Object[2];
        for (String[] list1 : list) {
            for (int i = 0; i < row.length; i++) {
                row[i] = list1[i];
            }
            mod.addRow(row);
        }
    }

    private void Events(final String login, final int idBudget) {
        bt_add.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_ADD(evt, login, idBudget);
            }
        });

    }

    private void BT_ADD(java.awt.event.ActionEvent evt, String login, int idBudget) {
        try {
            new AddServiceBuget(login, idBudget).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Service_Budget.class.getName()).log(Level.SEVERE, null, ex);
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
        tbl_employers_repair = new javax.swing.JTable();
        bt_add = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Lista de serviços no orçamento");
        setResizable(false);

        tbl_employers_repair.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Service"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl_employers_repair);

        bt_add.setText("Adicionar Serviço");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_add)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_add)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(635, 467));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_add;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_employers_repair;
    // End of variables declaration//GEN-END:variables
}
