/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repairs.repairs;

import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static javax.xml.bind.DatatypeConverter.parseInt;
import repairs.employers.Table_Employer_Repair;
import repairs.parts.ListPartsRepair;

/**
 *
 * @author Rui Barcelos
 */
public final class Table_Repairs extends javax.swing.JFrame {

    

    /**
     * Creates new form Table_Vehicles
     *
     * @param login
     * @param idService
     * @throws java.io.IOException
     * @throws java.net.MalformedURLException
     * @throws org.json.simple.parser.ParseException
     */
    public Table_Repairs(String login, int idService) throws Exception {
        APIRepair api = new APIRepair();
        initComponents();
        showTable(api.ListRepairs(login, 0));
        Events(login,idService);
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

    private void Events(final String login, final int idService) {
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
                BT_Info(evt, login,idService);
            }
        });
        bt_list_empl.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_ListEmployer(evt, login,idService);
            }
        });
        bt_parts.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Parts(evt, login,idService);
            }
        });
        tbl_repair.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBL_RepairClick(evt);
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
            Logger.getLogger(Table_Repairs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void BT_ListEmployer(java.awt.event.ActionEvent evt, String login, int idService) {
        try {
            new Table_Employer_Repair(login, SelectRow(0), idService).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Repairs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void BT_Parts(java.awt.event.ActionEvent evt, String login, int idService) {
        try {
            new ListPartsRepair(login, SelectRow(0), idService).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Repairs.class.getName()).log(Level.SEVERE, null, ex);
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Lista de Reparações");
        setResizable(false);

        tbl_repair.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Veiculo", "Descrição", "Preço", "Data Inicio", "Data Conclusão ", "Resolução", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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
                .addComponent(bt_parts)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                            .addComponent(bt_list_empl)
                            .addComponent(bt_parts))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1216, 596));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_edit;
    private javax.swing.JButton bt_info;
    private javax.swing.JButton bt_list_empl;
    private javax.swing.JButton bt_parts;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel l_idRepair;
    private javax.swing.JTable tbl_repair;
    // End of variables declaration//GEN-END:variables
}
