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

/**
 *
 * @author Rui Barcelos
 */
public final class Table_Repairs_PU extends javax.swing.JFrame {

    /**
     * Creates new form Table_Vehicles
     *
     * @param login
     * @param idService
     * @param id
     * @throws java.io.IOException
     * @throws java.net.MalformedURLException
     * @throws org.json.simple.parser.ParseException
     * @throws java.text.ParseException
     */
    public Table_Repairs_PU(String login, int idService, int id) throws Exception {
        APIRepair api = new APIRepair();
        initComponents();
        showTable(api.ListRepairs(login, id));
        Events(login, id, idService, api);
    }

    /**
     * Mostra os dados na tabela
     *
     * @param list
     */
    private void showTable(String[][] list) {
        if (list.length > 0) {
            DefaultTableModel mod = (DefaultTableModel) tbl_repair.getModel();
            Object[] row = new Object[8];
            for (String[] list1 : list) {
                for (int i = 0; i < row.length; i++) {
                    row[i] = list1[i];
                }
                mod.addRow(row);
            }
            tbl_repair.setRowSelectionInterval(0, 0);
        } else {
            bt_edit.setVisible(false);
            JOptionPane.showMessageDialog(this, "Não existe dados");
            dispose();
        }
    }

    /**
     * Define os eventos
     *
     * @param login
     * @param id
     * @param idService
     * @param api
     */
    private void Events(final String login, final int id, final int idService, final APIRepair api) {
        tbl_repair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBL_CLICKED(evt);
            }
        });
        bt_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_EDIT(evt, login, id, api);
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
    }

    /**
     * Abre a form de detalhes de informação
     *
     * @param evt
     * @param login
     * @param idService
     */
    private void BT_Info(java.awt.event.ActionEvent evt, String login, int idService) {
        try {
            new InfoRepair(login, SelectRow(0), idService).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Repairs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Mostra a lista de funcionários na na reparação
     *
     * @param evt
     * @param login
     * @param idService
     */
    private void BT_ListEmployer(java.awt.event.ActionEvent evt, String login, int idService) {
        try {
            new Table_Employer_Repair(login, SelectRow(0), idService).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Repairs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Abre a form onde contém as peças associadas a uma reparação
     *
     * @param evt
     * @param login
     * @param idService
     */
    private void BT_Parts(java.awt.event.ActionEvent evt, String login, int idService) {
        try {
            new ListPartsRepair(login, SelectRow(0), idService).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Repairs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Seleciona uma determinada linha da tabela
     *
     * @param val
     * @return
     */
    private int SelectRow(int val) {
        int i = tbl_repair.getSelectedRow();
        TableModel mod = tbl_repair.getModel();
        if (i < 0) {
            i = 0;
        }
        return parseInt((String) mod.getValueAt(i, val));
    }

    /**
     * Ativa a ação do click
     *
     * @param evt
     */
    private void TBL_CLICKED(java.awt.event.MouseEvent evt) {
        int i = tbl_repair.getSelectedRow();
        TableModel mod = tbl_repair.getModel();
        l_idRepair.setText((String) mod.getValueAt(i, 0));
    }

    /**
     * Abre a form de editar
     *
     * @param evt
     * @param login
     * @param id
     * @param api
     */
    private void BT_EDIT(java.awt.event.ActionEvent evt, String login, int id, APIRepair api) {
        try {
            int i = ((tbl_repair.getSelectedRow() < 0) ? 0 : tbl_repair.getSelectedRow());
            TableModel mod = tbl_repair.getModel();
            new EditRepair(login, parseInt((String) mod.getValueAt(i, 0))).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Repairs_PU.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel1 = new javax.swing.JLabel();
        bt_info = new javax.swing.JButton();
        bt_list_empl = new javax.swing.JButton();
        bt_parts = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Lista de Reparações por utilizador");
        setResizable(false);

        tbl_repair.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Matricula","Descrição do Cliente", "Preço", "Início", "Fim", "Informação", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_repair.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(tbl_repair);
        tbl_repair.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        bt_edit.setText("Editar Reparação");

        jLabel7.setText("Reparação N.º");

        l_idRepair.setText("reparação");

        jLabel1.setText("Lista de reparações por utilizador");

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(l_idRepair)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_parts)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_list_empl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_info)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bt_edit))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bt_info)
                        .addComponent(bt_list_empl)
                        .addComponent(bt_parts))
                    .addComponent(bt_edit)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(l_idRepair)
                        .addComponent(jLabel7)))
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel l_idRepair;
    private javax.swing.JTable tbl_repair;
    // End of variables declaration//GEN-END:variables
}
