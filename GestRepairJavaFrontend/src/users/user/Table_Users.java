/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.user;

import budgets.APIBudgets;
import budgets.Table_Budgets_PU;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static javax.xml.bind.DatatypeConverter.parseInt;
import org.json.simple.parser.ParseException;
import repairs.repairs.APIRepair;
import repairs.repairs.Table_Repairs_PU;
import schedule.APISchedule;
import schedule.Table_Schedule_PU;
import vehicles.vehicles.APIVehicles;
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
     * @param idEmployer
     * @throws IOException
     * @throws ParseException
     */
    public Table_Users(String login, int idService, int idEmployer) throws Exception {
        APIUsers api = new APIUsers();
        initComponents();
        showTable(login, api.ShowUser(login, 2));
        tbl_usersStart();
        Events(login, idService, idEmployer);

    }

    /**
     * Insere os dados na tabela
     *
     * @param login
     * @param list
     */
    public void showTable(String login, String[][] list) {
        APIVehicles apiVehicle = new APIVehicles();
        APISchedule apiSchedule = new APISchedule();
        APIRepair apiRepair = new APIRepair();
        APIBudgets apiBudgets = new APIBudgets();
        if (list.length > 0) {
            try {
                DefaultTableModel mod = (DefaultTableModel) tbl_users.getModel();
                Object[] row = new Object[10];
                for (String[] list1 : list) {
                    System.arraycopy(list1, 0, row, 0, row.length);
                    mod.addRow(row);
                }
                tbl_users.setRowSelectionInterval(0, 0);
                String id = (String) tbl_users.getModel().getValueAt(0, 0);
                linfoUser.setText(id);
                bt_vehicles.setVisible(apiVehicle.vehicles(login, parseInt(id)).length > 0);
                bt_schedule.setVisible(apiSchedule.ListSchedule(login, parseInt(id)).length > 0);
                bt_repair.setVisible(apiRepair.ListRepairs(login, parseInt(id)).length > 0);
                bt_budgets.setVisible(apiBudgets.ListBudgets(login, parseInt(id)).length > 0);
            } catch (Exception ex) {
                Logger.getLogger(Table_Users.class.getName()).log(Level.SEVERE, null, ex);
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

    /**
     * Inicializa a primera linha na tabela
     */
    private void tbl_usersStart() {
        // TODO add your handling code here:
        TableModel mod = tbl_users.getModel();
        linfoUser.setText(mod.getValueAt(0, 0) + "");
        l_username.setText(mod.getValueAt(0, 8) + "");
    }

    /**
     * Define os eventos
     *
     * @param login
     * @param idService
     * @param idEmployer
     */
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
        tbl_users.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBL_CLICKED(evt, login);
            }
        });
    }

    /**
     * Ao clicar mostra a informação retivo à linha clicada
     *
     * @param evt
     * @param login
     */
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

    /**
     * Abre a form de criar utilizador
     *
     * @param evt
     * @param login
     * @param idService
     * @param idEmployer
     */
    private void BT_ADDVehicle(java.awt.event.ActionEvent evt, String login, int idService, int idEmployer) {
        try {
            int i = tbl_users.getSelectedRow();
            new VerifyVehicle(login, parseInt(SearchTable(i, 0)), SearchTable(i, 8), idService, idEmployer).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Users_Type.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Mostra os dados da tabela budget
     *
     * @param evt
     * @param login
     */
    private void BT_Budget(java.awt.event.ActionEvent evt, String login) {
        try {
            int i = tbl_users.getSelectedRow();
            new Table_Budgets_PU(login, parseInt(SearchTable(i, 0))).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Abre a form de editar utilizador na qual a linha foi editada
     *
     * @param evt
     * @param login
     */
    private void BT_Edit(java.awt.event.ActionEvent evt, String login) {
        try {
            int i = tbl_users.getSelectedRow();
            new EditUser(login, parseInt(SearchTable(i, 0))).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Mostra os dados de informação de utilizador no qual a linha selecionada
     *
     * @param evt
     * @param login
     */
    private void BT_InfoUser(java.awt.event.ActionEvent evt, String login) {
        try {
            int i = tbl_users.getSelectedRow();
            new InfoUser(login, parseInt(SearchTable(i, 0))).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Abre a tabela de reparações consoante a linha clicada
     *
     * @param evt
     * @param login
     * @param idService
     */
    private void BT_Repair(java.awt.event.ActionEvent evt, String login, int idService) {
        try {
            int i = tbl_users.getSelectedRow();
            new Table_Repairs_PU(login, idService, parseInt(SearchTable(i, 0))).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Abre a tabela de marcações consoante a linha clicada
     *
     * @param evt
     * @param login
     */
    private void BT_Schedule(java.awt.event.ActionEvent evt, String login) {
        try {
            int i = tbl_users.getSelectedRow();
            new Table_Schedule_PU(login, parseInt(SearchTable(i, 0))).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Abre a tabela de viaturas consoante as linhas clicadas
     *
     * @param evt
     * @param login
     * @param idEmployer
     * @param idService
     */
    private void BT_Vehicles(java.awt.event.ActionEvent evt, String login, int idEmployer, int idService) {
        try {
            int i = tbl_users.getSelectedRow();
            new Table_Vehicles_PU(login, parseInt(SearchTable(i, 0)), idEmployer, idService).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Procura qual é a linha clicada
     * @param row
     * @param tb
     * @return 
     */
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
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(bt_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bt_userdata, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(344, 344, 344)
                    .addComponent(bt_budgets, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(bt_repair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_schedule, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(bt_add_vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bt_vehicles, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(linfoUser, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel6)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(l_username, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))))
        .addComponent(jScrollPane2)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel2)
                .addComponent(jLabel1)
                .addComponent(linfoUser)
                .addComponent(jLabel6)
                .addComponent(l_username))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(bt_budgets)
                .addComponent(bt_repair)
                .addComponent(bt_add_vehicle)
                .addComponent(bt_edit))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(bt_vehicles)
                .addComponent(bt_schedule)
                .addComponent(bt_userdata))
            .addContainerGap())
    );

    setSize(new java.awt.Dimension(1216, 639));
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
