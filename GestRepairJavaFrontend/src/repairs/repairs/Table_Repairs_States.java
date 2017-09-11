/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repairs.repairs;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static javax.xml.bind.DatatypeConverter.parseInt;
import repairs.employers.Table_Employer_Repair;
import repairs.parts.ListPartsRepair;
import repairs.state.APIState;

/**
 *
 * @author Rui Barcelos
 */
public final class Table_Repairs_States extends javax.swing.JFrame {

    /**
     * Creates new form Table_Vehicles
     *
     * @param login
     * @param idService
     * @throws java.lang.Exception
     */
    public Table_Repairs_States(String login, int idService) throws Exception {
        APIRepair api = new APIRepair();
        APIState apiState = new APIState();
        initComponents();
        String[][] state = apiState.ShowState(login);
        showStates(state);
        showTable(api.ListRepairsState(login, Cb_Val(cb_states.getSelectedIndex(), state)));
        Events(login, idService, api, apiState);
        row();
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

    private void row() {
        TableModel mod = tbl_repair.getModel();
        if (mod.getRowCount() > 0) {
            l_idRepair.setText(SelectRow(0) + "");
        } else {
            jLabel7.setText("");
            l_idRepair.setText("Não contem Dados!");
            bt_edit.setVisible(false);
            bt_info.setVisible(false);
        }
    }

    private int SelectRow(int val) {
        int i = tbl_repair.getSelectedRow();
        TableModel mod = tbl_repair.getModel();
        if (i < 0) {
            i = 0;
        }
        return parseInt((String) mod.getValueAt(i, val));
    }

    private void showStates(String[][] list) {
        cb_states.removeAllItems();
        for (String[] list1 : list) {
            cb_states.addItem(list1[1]);
        }
    }

    public void cleanTable() {
        DefaultTableModel mod = (DefaultTableModel) tbl_repair.getModel();
        mod.setRowCount(0);
    }

    private int Cb_Val(int val, String[][] list) {
        return parseInt(list[val][0]);
    }

    private void Events(final String login, final int idService, final APIRepair api, final APIState apiState) {
        tbl_repair.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        bt_edit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Edit(evt, login);
            }
        });

        bt_info.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Info(evt, login, idService);
            }
        });
        bt_list_empl.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_ListEmployer(evt, login, idService);
            }
        });
        bt_parts.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Parts(evt, login, idService);
            }
        });
        tbl_repair.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBL_RepairClick(evt);
            }
        });
        cb_states.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_State(evt, login, api, apiState);
            }
        });
    }

    private void BT_Edit(java.awt.event.ActionEvent evt, String login) {
        try {
            new EditRepair(login, SelectRow(0)).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(EditRepair.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void BT_Info(java.awt.event.ActionEvent evt, String login, int idService) {
        try {
            new InfoRepair(login, SelectRow(0), idService).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Repairs_States.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void BT_ListEmployer(java.awt.event.ActionEvent evt, String login, int idService) {
        try {
            new Table_Employer_Repair(login, SelectRow(0), idService).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Repairs_States.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void BT_Parts(java.awt.event.ActionEvent evt, String login, int idService) {
        try {
            new ListPartsRepair(login, SelectRow(0), idService).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Repairs_States.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void CB_State(java.awt.event.ActionEvent evt, String login, APIRepair api, APIState apiState) {
        try {
            String[][] state = apiState.ShowState(login);
            int idState = Cb_Val(cb_states.getSelectedIndex(), state);
            String[][] list = api.ListRepairsState(login, idState);
            if (list.length > 0) {
                cleanTable();
                showTable(list);
            } else {
                JOptionPane.showMessageDialog(this, "Lista sem Dados Disponiveis");
            }
        } catch (Exception ex) {
            Logger.getLogger(Table_Repairs_States.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void TBL_RepairClick(java.awt.event.MouseEvent evt) {
        row();
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
        bt_parts = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cb_states = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Lista de Reparações");
        setResizable(false);

        tbl_repair.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Veiculo", "Descrição", "Preço", "Data Inicio", "Data Conclusão ", "Resolução"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl_repair);
        tbl_repair.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        bt_edit.setText("Editar Reparação");

        jLabel7.setText("Reparação N.º");

        l_idRepair.setText("reparação");

        bt_info.setText("Info");

        bt_list_empl.setText("Ver Funcionários");

        bt_parts.setText("Ver Peças");

        jLabel1.setText("Selecione o estado da reparação que pretenda ver:");

        cb_states.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_states, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 872, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(l_idRepair)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_parts)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_list_empl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_info)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_edit)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_states, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_parts)
                    .addComponent(bt_list_empl)
                    .addComponent(bt_info)
                    .addComponent(bt_edit)
                    .addComponent(jLabel7)
                    .addComponent(l_idRepair))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1216, 639));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_edit;
    private javax.swing.JButton bt_info;
    private javax.swing.JButton bt_list_empl;
    private javax.swing.JButton bt_parts;
    private javax.swing.JComboBox cb_states;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel l_idRepair;
    private javax.swing.JTable tbl_repair;
    // End of variables declaration//GEN-END:variables
}
