/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles.vehicles;

import budgets.AddBudget;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static javax.xml.bind.DatatypeConverter.parseInt;
import repairs.repairs.AddRepair;

/**
 *
 * @author Rui Barcelos
 */
public final class Table_Vehicles extends javax.swing.JFrame {

    /**
     * Creates new form Table_Vehicles
     *
     * @param login
     * @param idEmployer
     * @param idService
     * @throws java.io.IOException
     * @throws java.net.MalformedURLException
     * @throws org.json.simple.parser.ParseException
     */
    public Table_Vehicles(String login, int idEmployer, int idService) throws Exception {
        APIVehicles api = new APIVehicles();
        initComponents();
        showTable(api.vehicles(login, 0));
        row(0);
        Events(login, idEmployer, idService, api);
    }

    private void showTable(String[][] list) {
        if (list.length > 0) {
            DefaultTableModel mod = (DefaultTableModel) tbl_vehicles.getModel();
            Object[] row = new Object[12];
            for (String[] list1 : list) {
                for (int i = 0; i < row.length; i++) {
                    row[i] = list1[i];
                }
                mod.addRow(row);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Não existe dados");
            dispose();
        }
    }

    private String SelectRow(int val) {
        int i = tbl_vehicles.getSelectedRow();
        if (i < 0) {
            i = 0;
        }
        TableModel mod = tbl_vehicles.getModel();
        return (String) mod.getValueAt(i, val);
    }

    private void row(int val) {
        TableModel mod = tbl_vehicles.getModel();
        if (mod.getRowCount() > 0) {
            l_idVehicle.setText(mod.getValueAt(val, 0) + "");
            l_registration.setText(mod.getValueAt(val, 1) + "");
        } else {
            jLabel1.setText("");
            jLabel7.setText("");
            l_idVehicle.setText("");
            l_registration.setText("Não contem Dados!");
            bt_disable.setVisible(false);
            bt_edit.setVisible(false);
            bt_info.setVisible(false);
        }
    }

    private void Events(final String login, final int idEmployer, final int idService, final APIVehicles api) {
        bt_add_budgets.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_ADD_BUDGETS(evt, login, idEmployer, idService);
            }
        });
        bt_add_repair.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_ADD_REPAIR(evt, login, idEmployer, idService);
            }
        });
        bt_disable.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Disable(evt, login, api);
            }
        });
        bt_edit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Edit(evt, login);
            }
        });
        bt_info.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_INFO(evt, login);
            }
        });
        tbl_vehicles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBL_VehiclesClicked(evt);
            }
        });
    }

    private void cleanTable() {
        DefaultTableModel mod = (DefaultTableModel) tbl_vehicles.getModel();
        mod.setRowCount(0);
    }

    private void BT_Disable(java.awt.event.ActionEvent evt, String login, APIVehicles api) {
        int i = (tbl_vehicles.getSelectedRow() < 0) ? 0 : tbl_vehicles.getSelectedRow();
        TableModel mod = tbl_vehicles.getModel();
        int x = JOptionPane.showConfirmDialog(this, "Tem a certeza que quer desassociar esta viatura ao utilizador?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            try {
                String[] value = api.POSTDisableVehicle(login, (String) mod.getValueAt(i, 1));
                JOptionPane.showMessageDialog(this, value[1]);
                if ("ok".equals(value[0])) {
                    cleanTable();
                    showTable(api.vehicles(login, 0));
                    row(0);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro Interno");
            }
        } else if (x == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(this, "O viatura não desassociada");
        }
    }

    private void BT_ADD_BUDGETS(java.awt.event.ActionEvent evt, String login, int idEmployer, int idService) {
        // TODO add your handling code here:
        try {
            new AddBudget(login, parseInt(SelectRow(0)), idEmployer, idService).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Vehicles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void BT_ADD_REPAIR(java.awt.event.ActionEvent evt, String login, int idEmployer, int idService) {
        try {
            new AddRepair(login, parseInt(l_idVehicle.getText()), idEmployer, idService).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Vehicles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void BT_INFO(java.awt.event.ActionEvent evt, String login) {
        try {
            new InfoVehicle(login, parseInt(l_idVehicle.getText())).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Vehicles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void BT_Edit(java.awt.event.ActionEvent evt, String login) {
        try {
            new EditVehicle(login, parseInt(l_idVehicle.getText())).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Vehicles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void TBL_VehiclesClicked(java.awt.event.MouseEvent evt) {
        int i = tbl_vehicles.getSelectedRow();
        row(i);
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
        l_idVehicle = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        l_registration = new javax.swing.JLabel();
        bt_info = new javax.swing.JButton();
        bt_add_repair = new javax.swing.JButton();
        bt_add_budgets = new javax.swing.JButton();
        bt_disable = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Lista de Viaturas");
        setResizable(false);

        tbl_vehicles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Matrícula", "Marca", "Modelo", "CV", "Cilindrada", "Quilómetros", "Combustível", "Roda da Frente", "Roda de Trás", "Data", "Utilizador"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl_vehicles);
        tbl_vehicles.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        bt_edit.setText("Editar");

        jLabel7.setText("Veículo N.º");

        l_idVehicle.setText("Veículo");

        jLabel1.setText("Matrícula");

        l_registration.setText("matrícula");

        bt_info.setText("Info");

        bt_add_repair.setText("Adicionar Reparação");

        bt_add_budgets.setText("Adicionar Orçamento");

        bt_disable.setText("Desativar");

        jLabel2.setText("Lista de viaturas");

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
                        .addGap(18, 18, 18)
                        .addComponent(l_idVehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(l_registration)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_disable)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_add_repair)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_add_budgets)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_info)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_edit))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(l_idVehicle)
                            .addComponent(jLabel7)
                            .addComponent(jLabel1)
                            .addComponent(l_registration)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bt_info)
                            .addComponent(bt_edit)
                            .addComponent(bt_add_repair)
                            .addComponent(bt_add_budgets)
                            .addComponent(bt_disable))))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1216, 615));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_add_budgets;
    private javax.swing.JButton bt_add_repair;
    private javax.swing.JButton bt_disable;
    private javax.swing.JButton bt_edit;
    private javax.swing.JButton bt_info;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel l_idVehicle;
    private javax.swing.JLabel l_registration;
    private javax.swing.JTable tbl_vehicles;
    // End of variables declaration//GEN-END:variables
}
