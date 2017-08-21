/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.user;

import budgets.Table_Budgets_PU;
import users.employer.InfoEmployer;
import users.employer.AddEmployer;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static javax.xml.bind.DatatypeConverter.parseInt;
import repairs.repairs.Table_Repairs_PU;
import users.employer.APIEmployer;
import users.employer.EditEmployer;
import vehicles.vehicles.AddVehicle;
import vehicles.vehicles.Table_Vehicles_PU;

/**
 *
 * @author Convite
 */
public final class Table_Users_Type extends javax.swing.JFrame {


    APIUsers api = new APIUsers();
    APIEmployer apiEmployer = new APIEmployer();

    /**
     * Start the interface and need elements
     *
     * @param login
     * @param idService
     * @throws java.lang.Exception
     */
    public Table_Users_Type(String login, int idService) throws Exception {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../../img/imageedit_4_8303763918.png")));
        Events(login, idService);
        tbl_usersStart(login);
        int gest = parseInt(apiEmployer.GetInfoEmployerUser(login, idService)[0]);
        bt_edit.setVisible(gest == 1);
        bt_addEmployer.setVisible(gest == 1);

    }

    public void upTable(String login) throws Exception {
        DefaultTableModel mod = (DefaultTableModel) tbl_users.getModel();
        mod.setRowCount(0);
        tbl_usersStart(login);
    }

    private void showTable(String[][] list) { 
        DefaultTableModel mod = (DefaultTableModel) tbl_users.getModel();
        Object[] row = new Object[9];
        for (String[] list1 : list) {
            for (int i = 0; i < row.length; i++) {
                row[i] = list1[i];
            }
            mod.addRow(row);
        }
        tbl_users.setRowSelectionInterval(0, 0);
    }

    private void tbl_usersStart(String login) throws Exception{
        int cb = cbType.getSelectedIndex();
        showTable(api.ShowUser(login, cb));
        bt_info_func.setVisible(cb != 0);
        bt_rep_func.setVisible(cb != 0);
        linfoUser.setText(SearchTable(0,0));
        l_username.setText(SearchTable(0,8));
    }

    private void Events(final String login, final int idService) {
        bt_addEmployer.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_addEmployer(evt, login);
            }
        });
        bt_add_vehicle.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_ADDVehicle(evt, login);
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
                BT_Repair(evt, login);
            }
        });
        bt_vehicles.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Vehicles(evt, login);
            }
        });
        cbType.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_Service(evt, login);
            }
        });
        MI_UserType.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_UserType(evt, login,idService);
            }
        });
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

    private void BT_ADDVehicle(java.awt.event.ActionEvent evt, String login) {
        try {
            int i = tbl_users.getSelectedRow();
            new AddVehicle(login, parseInt(SearchTable(i,0)), SearchTable(i,8)).setVisible(true);
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

    private void BT_Repair(java.awt.event.ActionEvent evt, String login) {
        try {
            int i = tbl_users.getSelectedRow();
            new Table_Repairs_PU(login, parseInt(SearchTable(i, 0))).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void BT_Vehicles(java.awt.event.ActionEvent evt, String login) {
        try {
            int i = tbl_users.getSelectedRow();
            new Table_Vehicles_PU(login, parseInt(SearchTable(i, 0))).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void CB_Service(java.awt.event.ActionEvent evt, String login) {
        try {
            if (cbType.getSelectedIndex() == 0) {
                bt_addEmployer.setText("Adicionar Funcionário");
            } else {
                bt_addEmployer.setText("Editar Funcionário");
            }
            upTable(login);
        } catch (Exception ex) {
            Logger.getLogger(Table_Users_Type.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void MI_UserType(java.awt.event.ActionEvent evt, String login, int idService) {
         try {
            new Table_Users_Type(login, idService).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Users_Type.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String SearchTable(int row, int tb) {
        TableModel mod = tbl_users.getModel();
        return mod.getValueAt((row<0)?0:row, tb) + "";
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
        jButton4 = new javax.swing.JButton();
        bt_info_func = new javax.swing.JButton();
        bt_rep_func = new javax.swing.JButton();
        bt_edit = new javax.swing.JButton();
        bt_add_vehicle = new javax.swing.JButton();
        bt_userdata = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        MI_UserType = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Lista de Utilizadores por catergoria");

        tbl_users.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "N.º Utilizador", "Nome", "Morada", "Código de Postal","Localidade","E-mail","NIF","Contacto","Username"
            }
        ));
        tbl_users.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_usersMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_users);

        jLabel1.setText("Nº. de Utilizador:");

        jLabel6.setText("Username:");

        jLabel7.setText("Tipo de Utilizador");

        linfoUser.setText("Número");

        l_username.setText("Nome");

        bt_addEmployer.setText("Adicionar Funcionário");

        cbType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cliente", "Funcionário" }));
        cbType.setAutoscrolls(true);

        bt_vehicles.setText("Ver Viaturas");

        bt_repair.setText("Ver Reparações");

        bt_budgets.setText("Ver Orçamentos");

        jButton4.setText("Ver Marcações");

        bt_info_func.setText("Dados do Funcionário");

        bt_rep_func.setText("Ver Reparações do Funcionário");

        bt_edit.setText("Editar Utilizador");

        bt_add_vehicle.setText("Adicionar Viatura");

        bt_userdata.setText("Dados do Utilizador");

        jMenu1.setText("File");

        jMenuItem1.setText("Sair");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Utilizadores");

        jMenuItem3.setText("Todos os Utilizadores");
        jMenu3.add(jMenuItem3);

        MI_UserType.setText("Utilizadores Por Categoria");
        jMenu3.add(MI_UserType);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bt_add_vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bt_repair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(bt_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(linfoUser)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l_username))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bt_userdata, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bt_budgets, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(bt_vehicles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bt_info_func, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_addEmployer, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_rep_func))
                        .addContainerGap(677, Short.MAX_VALUE))))
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(linfoUser)
                    .addComponent(jLabel6)
                    .addComponent(l_username))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_rep_func)
                    .addComponent(bt_edit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_budgets)
                    .addComponent(bt_userdata)
                    .addComponent(bt_vehicles)
                    .addComponent(bt_info_func))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_add_vehicle)
                    .addComponent(bt_repair)
                    .addComponent(jButton4)
                    .addComponent(bt_addEmployer))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void tbl_usersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_usersMouseClicked
        int i = tbl_users.getSelectedRow();
        linfoUser.setText(SearchTable(i, 0));
        l_username.setText(SearchTable(i, 8));
    }//GEN-LAST:event_tbl_usersMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem MI_UserType;
    private javax.swing.JButton bt_addEmployer;
    private javax.swing.JButton bt_add_vehicle;
    private javax.swing.JButton bt_budgets;
    private javax.swing.JButton bt_edit;
    private javax.swing.JButton bt_info_func;
    private javax.swing.JButton bt_rep_func;
    private javax.swing.JButton bt_repair;
    private javax.swing.JButton bt_userdata;
    private javax.swing.JButton bt_vehicles;
    private javax.swing.JComboBox<String> cbType;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel l_username;
    private javax.swing.JLabel linfoUser;
    private javax.swing.JTable tbl_users;
    // End of variables declaration//GEN-END:variables
}
