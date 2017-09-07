/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgets;

import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static javax.xml.bind.DatatypeConverter.parseInt;

/**
 *
 * @author Rui Barcelos
 */
public final class Table_Budgets_State extends javax.swing.JFrame {

    /**
     * Creates new form Table_Vehicles
     *
     * @param login
     * @throws java.lang.Exception
     */
    public Table_Budgets_State(String login) throws Exception {
        APIBudgets api = new APIBudgets();
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../img/imageedit_4_8303763918.png")));
        int i = cb_state.getSelectedIndex();
        if (i < 0) {
            i = 0;
        }

        String[][] data = api.ListBudgetsByState(login, cbVal(api.ListState(login), i));
        if (data.length > 0) {
            showTable(data);
            l_idBudget.setText((String) tbl_budgets.getModel().getValueAt(i, 0));
        } else {
            JOptionPane.showMessageDialog(this, "Sem dados!");
        }
        insertCb(api.ListState(login));
        Events(login, api);

    }

    private void showTable(String[][] list) {
        DefaultTableModel mod = (DefaultTableModel) tbl_budgets.getModel();
        Object[] row = new Object[9];
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

    private int cbVal(String[][] list, int val) {
        return parseInt(list[val][0]);
    }

    private void insertCb(String[][] list) throws Exception {
        cb_state.removeAllItems();
        for (String[] list1 : list) {
            cb_state.addItem(list1[1]);
        }
    }

    private void Events(final String login, final APIBudgets api) {
        bt_edit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Edit(evt, login);
            }
        });
        bt_info.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Info(evt, login);
            }
        });
        tbl_budgets.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBL_budgetsClicked(evt);
            }
        });
        cb_state.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_State(evt, login, api);
            }
        });
    }

    private void BT_Edit(java.awt.event.ActionEvent evt, String login) {
        try {
            TableModel mod = tbl_budgets.getModel();
            int i = tbl_budgets.getSelectedRow();
            new EditBudget(login, parseInt((String) mod.getValueAt(i, 0))).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(Table_Budgets_State.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void BT_Info(java.awt.event.ActionEvent evt, String login) {
        try {
            TableModel mod = tbl_budgets.getModel();
            int i = tbl_budgets.getSelectedRow();
            new InfoBudget(login, parseInt((String) mod.getValueAt(i, 0))).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(Table_Budgets_State.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void CB_State(java.awt.event.ActionEvent evt, String login, APIBudgets api) {
        try {
            String[][] data = api.ListBudgetsByState(login, cbVal(api.ListState(login), cb_state.getSelectedIndex()));
            if (data.length > 0) {
                DefaultTableModel mod = (DefaultTableModel) tbl_budgets.getModel();
                mod.setRowCount(0);
                showTable(data);
            } else {
                JOptionPane.showMessageDialog(this, "Sem dados!");
            }
        } catch (Exception ex) {
            Logger.getLogger(Table_Budgets_State.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void TBL_budgetsClicked(java.awt.event.MouseEvent evt) {
        int i = tbl_budgets.getSelectedRow();
        TableModel mod = tbl_budgets.getModel();
        l_idBudget.setText((String) mod.getValueAt(i, 0));
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
        tbl_budgets = new javax.swing.JTable();
        bt_edit = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        l_idBudget = new javax.swing.JLabel();
        bt_info = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cb_state = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Lista de orçamentos por estado");

        tbl_budgets.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Matrícula", "Desc.Cliente", "Preço", "Entrada", "Tempo de Reparação", "Fim do Processo", "Resolução"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_budgets.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(tbl_budgets);
        tbl_budgets.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        bt_edit.setText("Editar ");

        jLabel7.setText("Orçamento  N.º");

        l_idBudget.setText("orçamento");

        bt_info.setText("Info");

        jLabel1.setText("Selecione o estado do orçamento em que pretende ver:");

        cb_state.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(l_idBudget)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_info)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bt_edit))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_state, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cb_state, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bt_edit)
                        .addComponent(bt_info))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(l_idBudget)
                        .addComponent(jLabel7)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_edit;
    private javax.swing.JButton bt_info;
    private javax.swing.JComboBox cb_state;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel l_idBudget;
    private javax.swing.JTable tbl_budgets;
    // End of variables declaration//GEN-END:variables
}
