/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.user;

import budgets.Table_Budgets_PU;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static javax.xml.bind.DatatypeConverter.parseInt;
import org.json.simple.parser.ParseException;
import repairs.repairs.Table_Repairs_PU;
import schedule.Table_Schedule_PU;
import vehicles.vehicles.Table_Vehicles_PU;
import vehicles.vehicles.VerifyVehicle;

/**
 *
 * @author Convite
 */
public final class Table_Users extends javax.swing.JFrame {

    /**
     *
     * @param login
     * @param idService
     * @throws IOException
     * @throws ParseException
     */
    public Table_Users(String login, int idService, int idEmployer) throws Exception {
        APIUsers api = new APIUsers();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../../img/imageedit_4_8303763918.png")));
        initComponents();
        showTable(api.ShowUser(login, 2));
        tbl_usersStart();
        Events(login, idService, idEmployer);
    }

    public void showTable(String[][] list) {
        if (list.length > 0) {
            DefaultTableModel mod = (DefaultTableModel) tbl_users.getModel();
            Object[] row = new Object[10];
            for (String[] list1 : list) {
                System.arraycopy(list1, 0, row, 0, row.length);
                mod.addRow(row);
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

    private void tbl_usersStart() {
        // TODO add your handling code here:
        TableModel mod = tbl_users.getModel();
        linfoUser.setText(mod.getValueAt(0, 0) + "");
        l_username.setText(mod.getValueAt(0, 8) + "");
    }

    private void Events(final String login, final int idService, final int idEmployer) {

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
                BT_Vehicles(evt, login, idEmployer, idService);
            }
        });
        bt_schedule.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Schedule(evt, login);
            }
        });
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
        linfoUser = new javax.swing.JLabel();
        l_username = new javax.swing.JLabel();
        bt_edit = new javax.swing.JButton();
        bt_userdata = new javax.swing.JButton();
        bt_add_vehicle = new javax.swing.JButton();
        bt_vehicles = new javax.swing.JButton();
        bt_schedule = new javax.swing.JButton();
        bt_budgets = new javax.swing.JButton();
        bt_repair = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lista de Utilizadores");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        tbl_users.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "N.º Utilizador", "Nome", "Morada", "Código de Postal","Localidade","E-mail","NIF","Contacto","Username","Tipo de Utilizador"
            }
        ){
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        }

    );
    tbl_users.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    jScrollPane2.setViewportView(tbl_users);

    jLabel1.setText("Nº. de Utilizador:");

    jLabel6.setText("Username:");

    linfoUser.setText("id");

    l_username.setText("username");

    bt_edit.setText("Editar Utilizador");

    bt_userdata.setText("Dados do Utilizador");

    bt_add_vehicle.setText("Adicionar Viatura");

    bt_vehicles.setText("Ver Viaturas");

    bt_schedule.setText("Ver Marcações");

    bt_budgets.setText("Ver Orçamentos");

    bt_repair.setText("Ver Reparações");

    jLabel2.setText("Lista de Utilizadores");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(88, 88, 88)
                                    .addComponent(linfoUser))
                                .addComponent(jLabel1))
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(l_username)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bt_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(bt_add_vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(bt_repair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(bt_userdata, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(bt_budgets, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(bt_vehicles, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bt_schedule, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(bt_budgets)
                .addComponent(bt_userdata)
                .addComponent(bt_vehicles)
                .addComponent(jLabel1)
                .addComponent(linfoUser))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(bt_add_vehicle)
                .addComponent(bt_repair)
                .addComponent(bt_schedule)
                .addComponent(bt_edit)
                .addComponent(jLabel6)
                .addComponent(l_username))
            .addContainerGap())
    );

    setSize(new java.awt.Dimension(1216, 637));
    setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_add_vehicle;
    private javax.swing.JButton bt_budgets;
    private javax.swing.JButton bt_edit;
    private javax.swing.JButton bt_repair;
    private javax.swing.JButton bt_schedule;
    private javax.swing.JButton bt_userdata;
    private javax.swing.JButton bt_vehicles;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel l_username;
    private javax.swing.JLabel linfoUser;
    private javax.swing.JTable tbl_users;
    // End of variables declaration//GEN-END:variables
}
