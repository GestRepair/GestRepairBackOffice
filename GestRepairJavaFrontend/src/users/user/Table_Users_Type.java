/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.user;

import budgets.APIBudgets;
import budgets.Table_Budgets_PU;
import users.employer.InfoEmployer;
import users.employer.AddEmployer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static javax.xml.bind.DatatypeConverter.parseInt;
import repairs.repairs.APIRepair;
import repairs.repairs.Table_Repairs_PU;
import schedule.APISchedule;
import schedule.Table_Schedule_PU;
import users.employer.APIEmployer;
import users.employer.EditEmployer;
import vehicles.vehicles.APIVehicles;
import vehicles.vehicles.Table_Vehicles_PU;
import vehicles.vehicles.VerifyVehicle;

/**
 *
 * @author Convite
 */
public final class Table_Users_Type extends javax.swing.JFrame {

    /**
     * Start the interface and need elements
     *
     * @param login
     * @param idService
     * @throws java.lang.Exception
     */
    public Table_Users_Type(String login, int idService, int idEmployer) throws Exception {
        APIUsers api = new APIUsers();
        APIEmployer apiEmployer = new APIEmployer();
        initComponents();
        Events(login, idService, idEmployer, api);
        tbl_usersStart(login, api);
        int gest = parseInt(apiEmployer.GetInfoEmployerUser(login, idService)[0]);
        bt_edit.setVisible(gest == 1);
        bt_addEmployer.setVisible(gest == 1);

    }

    public void upTable(String login, APIUsers api) throws Exception {
        DefaultTableModel mod = (DefaultTableModel) tbl_users.getModel();
        mod.setRowCount(0);
        tbl_usersStart(login, api);
    }

    private void showTable(String login, String[][] list) {
        APIVehicles apiVehicle = new APIVehicles();
        APISchedule apiSchedule = new APISchedule();
        APIRepair apiRepair = new APIRepair();
        APIBudgets apiBudgets = new APIBudgets();
        if (list.length > 0) {
            try {
                DefaultTableModel mod = (DefaultTableModel) tbl_users.getModel();
                Object[] row = new Object[9];
                for (String[] list1 : list) {
                    for (int i = 0; i < row.length; i++) {
                        row[i] = list1[i];
                    }
                    mod.addRow(row);
                }
                tbl_users.setRowSelectionInterval(0, 0);
                String id = (String) tbl_users.getModel().getValueAt(0, 0);
                linfoUser.setText(id);
                bt_add_vehicle.setVisible(true);
                bt_edit.setVisible(true);
                bt_userdata.setVisible(true);
                bt_vehicles.setVisible(apiVehicle.vehicles(login, parseInt(id)).length > 0);
                bt_schedule.setVisible(apiSchedule.ListSchedule(login, parseInt(id)).length > 0);
                bt_repair.setVisible(apiRepair.ListRepairs(login, parseInt(id)).length > 0);
                bt_budgets.setVisible(apiBudgets.ListBudgets(login, parseInt(id)).length > 0);
            } catch (Exception ex) {
                Logger.getLogger(Table_Users_Type.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            bt_add_vehicle.setVisible(false);
            bt_budgets.setVisible(false);
            bt_edit.setVisible(false);
            bt_repair.setVisible(false);
            bt_schedule.setVisible(false);
            bt_userdata.setVisible(false);
            bt_vehicles.setVisible(false);
            JOptionPane.showMessageDialog(this, "Não existe dados");
            dispose();
        }
    }

    private void tbl_usersStart(String login, APIUsers api) throws Exception {
        int cb = cbType.getSelectedIndex();
        showTable(login,api.ShowUser(login, cb));
        bt_info_func.setVisible(cb != 0);
        linfoUser.setText(SearchTable(0, 0));
        l_username.setText(SearchTable(0, 8));
    }

    private void Events(final String login, final int idService, final int idEmployer, final APIUsers api) {
        bt_addEmployer.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_addEmployer(evt, login);
            }
        });
        bt_add_vehicle.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_ADDVehicle(evt, login, idService, idEmployer);
            }
        });
        bt_budgets.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Budget(evt, login);
            }
        });
        bt_edit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Edit(evt, login);
            }
        });
        bt_info_func.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_InfoFunc(evt, login);
            }
        });
        bt_userdata.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_InfoUser(evt, login);
            }
        });
        bt_repair.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Repair(evt, login, idService);
            }
        });
        bt_vehicles.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Vehicles(evt, login, idEmployer, idService);
            }
        });
        bt_schedule.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Schedule(evt, login);
            }
        });
        cbType.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_Service(evt, login, api);
            }
        });
        tbl_users.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBL_CLICKED(evt,login);
            }
        });
    }

    private void TBL_CLICKED(java.awt.event.MouseEvent evt, String login) {
        try {
            APIVehicles apiVehicle = new APIVehicles();
            APISchedule apiSchedule = new APISchedule();
            APIRepair apiRepair = new APIRepair();
            APIBudgets apiBudgets = new APIBudgets();
            int i = tbl_users.getSelectedRow();
            String id = SearchTable(i, 0);
            linfoUser.setText(id);
            l_username.setText(SearchTable(i, 8));
            bt_vehicles.setVisible(apiVehicle.vehicles(login, parseInt(id)).length > 0);
            bt_schedule.setVisible(apiSchedule.ListSchedule(login, parseInt(id)).length > 0);
            bt_repair.setVisible(apiRepair.ListRepairs(login, parseInt(id)).length > 0);
            bt_budgets.setVisible(apiBudgets.ListBudgets(login, parseInt(id)).length > 0);
        } catch (Exception ex) {
            Logger.getLogger(Table_Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void BT_addEmployer(java.awt.event.ActionEvent evt, String login) {
        try {
            if ("Adicionar Funcionário".equals(bt_addEmployer.getText())) {
                int id = parseInt(linfoUser.getText());
                String user = l_username.getText();
                int cb = cbType.getSelectedIndex();
                new AddEmployer(login, id, user).setVisible(cb == 0);
            } else {
                String id = linfoUser.getText();
                new EditEmployer(login, parseInt(id)).setVisible(true);
            }
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Users_Type.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void BT_ADDVehicle(java.awt.event.ActionEvent evt, String login, int idService, int idEmployer) {
        try {
            int i = tbl_users.getSelectedRow();
            new VerifyVehicle(login, parseInt(SearchTable(i, 0)), SearchTable(i, 8), idService, idEmployer).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Users_Type.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void BT_Budget(java.awt.event.ActionEvent evt, String login) {
        try {
            int i = tbl_users.getSelectedRow();
            new Table_Budgets_PU(login, parseInt(SearchTable(i, 0))).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void BT_Edit(java.awt.event.ActionEvent evt, String login) {
        try {
            int i = tbl_users.getSelectedRow();
            new EditUser(login, parseInt(SearchTable(i, 0))).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void BT_InfoFunc(java.awt.event.ActionEvent evt, String login) {
        try {
            int i = tbl_users.getSelectedRow();
            new InfoEmployer(login, parseInt(SearchTable(i, 0))).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void BT_InfoUser(java.awt.event.ActionEvent evt, String login) {
        try {
            int i = tbl_users.getSelectedRow();
            new InfoUser(login, parseInt(SearchTable(i, 0))).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void BT_Repair(java.awt.event.ActionEvent evt, String login, int idService) {
        try {
            int i = tbl_users.getSelectedRow();
            new Table_Repairs_PU(login, idService, parseInt(SearchTable(i, 0))).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void BT_Schedule(java.awt.event.ActionEvent evt, String login) {
        try {
            int i = tbl_users.getSelectedRow();
            new Table_Schedule_PU(login, parseInt(SearchTable(i, 0))).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void BT_Vehicles(java.awt.event.ActionEvent evt, String login, int idEmployer, int idService) {
        try {
            int i = tbl_users.getSelectedRow();
            new Table_Vehicles_PU(login, parseInt(SearchTable(i, 0)), idEmployer, idService).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void CB_Service(java.awt.event.ActionEvent evt, String login, APIUsers api) {
        try {
            if (cbType.getSelectedIndex() == 0) {
                bt_addEmployer.setText("Adicionar Funcionário");
            } else {
                bt_addEmployer.setText("Editar Funcionário");
            }
            upTable(login, api);
        } catch (Exception ex) {
            Logger.getLogger(Table_Users_Type.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String SearchTable(int row, int tb) {
        TableModel mod = tbl_users.getModel();
        return mod.getValueAt((row < 0) ? 0 : row, tb) + "";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_users = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        linfoUser = new javax.swing.JLabel();
        l_username = new javax.swing.JLabel();
        bt_addEmployer = new javax.swing.JButton();
        cbType = new javax.swing.JComboBox<String>();
        bt_vehicles = new javax.swing.JButton();
        bt_repair = new javax.swing.JButton();
        bt_budgets = new javax.swing.JButton();
        bt_schedule = new javax.swing.JButton();
        bt_info_func = new javax.swing.JButton();
        bt_edit = new javax.swing.JButton();
        bt_add_vehicle = new javax.swing.JButton();
        bt_userdata = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Lista de Utilizadores por catergoria");
        setResizable(false);

        tbl_users.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "N.º Utilizador", "Nome", "Morada", "Código de Postal","Localidade","E-mail","NIF","Contacto","Username"
            }
        ));
        jScrollPane2.setViewportView(tbl_users);

        jLabel1.setText("Nº. de Utilizador:");

        jLabel6.setText("Username:");

        jLabel7.setText("Listar Utilizadores por função");

        linfoUser.setText("Número");

        l_username.setText("Nome");

        bt_addEmployer.setText("Adicionar Funcionário");

        cbType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cliente", "Funcionário" }));
        cbType.setAutoscrolls(true);

        bt_vehicles.setText("Ver Viaturas");

        bt_repair.setText("Ver Reparações");

        bt_budgets.setText("Ver Orçamentos");

        bt_schedule.setText("Ver Marcações");

        bt_info_func.setText("Dados do Funcionário");

        bt_edit.setText("Editar Utilizador");

        bt_add_vehicle.setText("Adicionar Viatura");

        bt_userdata.setText("Dados do Utilizador");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(bt_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bt_addEmployer, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                        .addComponent(bt_budgets, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bt_repair, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bt_add_vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(linfoUser)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(l_username, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(bt_userdata, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bt_info_func, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_schedule, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bt_vehicles, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23))
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(l_username)
                    .addComponent(jLabel1)
                    .addComponent(linfoUser))
                .addGap(1, 1, 1)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bt_add_vehicle)
                            .addComponent(bt_repair)
                            .addComponent(bt_budgets)
                            .addComponent(bt_edit)
                            .addComponent(bt_addEmployer))
                        .addGap(29, 29, 29))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bt_userdata)
                        .addComponent(bt_vehicles)
                        .addComponent(bt_schedule)
                        .addComponent(bt_info_func)))
                .addGap(11, 11, 11))
        );

        setSize(new java.awt.Dimension(1216, 639));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_addEmployer;
    private javax.swing.JButton bt_add_vehicle;
    private javax.swing.JButton bt_budgets;
    private javax.swing.JButton bt_edit;
    private javax.swing.JButton bt_info_func;
    private javax.swing.JButton bt_repair;
    private javax.swing.JButton bt_schedule;
    private javax.swing.JButton bt_userdata;
    private javax.swing.JButton bt_vehicles;
    private javax.swing.JComboBox<String> cbType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel l_username;
    private javax.swing.JLabel linfoUser;
    private javax.swing.JTable tbl_users;
    // End of variables declaration//GEN-END:variables
}
