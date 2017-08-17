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
import vehicles.vehicles.AddVehicle;
import vehicles.vehicles.Table_Vehicles_PU;

/**
 *
 * @author Convite
 */
public final class Table_Users extends javax.swing.JFrame {

    
    private final String login;
    APIUsers api = new APIUsers();
    private final int idService;
    /**
     *
     * @param login
     * @param service
     * @throws IOException
     * @throws ParseException
     */
    public Table_Users(String login, int idService) throws Exception {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../../img/imageedit_4_8303763918.png")));
        initComponents();      
        showTable(api.ShowUser(login,2));
        tbl_usersStart();
        this.login = login;
        this.idService = idService;
    }

    public void showTable(String[][] list) {
        DefaultTableModel mod = (DefaultTableModel) tbl_users.getModel();
        Object[] row = new Object[10];
        for (String[] list1 : list) {
            for (int i = 0; i < row.length; i++) {
                row[i] = list1[i];
            }
            mod.addRow(row);
        }
    }

    private void tbl_usersStart() {                                       
        // TODO add your handling code here:
        TableModel mod = tbl_users.getModel();
        linfoUser.setText(mod.getValueAt(0, 0) + "");
        l_username.setText(mod.getValueAt(0, 8) + "");
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
        bt_vehicles1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        bt_budgets = new javax.swing.JButton();
        bt_repair = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lista de Utilizadores");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

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
    tbl_users.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tbl_usersMouseClicked(evt);
        }
    });
    jScrollPane2.setViewportView(tbl_users);

    jLabel1.setText("Nº. de Utilizador:");

    jLabel6.setText("Username:");

    linfoUser.setText("id");

    l_username.setText("username");

    bt_edit.setText("Editar Utilizador");
    bt_edit.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            bt_editActionPerformed(evt);
        }
    });

    bt_userdata.setText("Dados do Utilizador");
    bt_userdata.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            bt_userdataActionPerformed(evt);
        }
    });

    bt_add_vehicle.setText("Adicionar Viatura");
    bt_add_vehicle.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            bt_add_vehicleActionPerformed(evt);
        }
    });

    bt_vehicles1.setText("Ver Viaturas");
    bt_vehicles1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            bt_vehicles1ActionPerformed(evt);
        }
    });

    jButton4.setText("Ver Marcações");

    bt_budgets.setText("Ver Orçamentos");
    bt_budgets.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            bt_budgetsActionPerformed(evt);
        }
    });

    bt_repair.setText("Ver Reparações");
    bt_repair.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            bt_repairActionPerformed(evt);
        }
    });

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

    jMenuItem4.setText("Utilizadores Por Categoria");
    jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem4ActionPerformed(evt);
        }
    });
    jMenu3.add(jMenuItem4);

    jMenuBar1.add(jMenu3);

    setJMenuBar(jMenuBar1);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(bt_add_vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(bt_repair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(bt_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(bt_userdata, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(bt_budgets, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(bt_vehicles1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(88, 88, 88)
                            .addComponent(linfoUser))
                        .addComponent(jLabel1))
                    .addGap(36, 36, 36)
                    .addComponent(jLabel6)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(l_username)))
            .addContainerGap(728, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(linfoUser)
                .addComponent(jLabel6)
                .addComponent(l_username))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(bt_edit)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(bt_budgets)
                .addComponent(bt_userdata)
                .addComponent(bt_vehicles1))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(bt_add_vehicle)
                .addComponent(bt_repair)
                .addComponent(jButton4))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void tbl_usersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_usersMouseClicked
        // TODO add your handling code here:
        int i = tbl_users.getSelectedRow();
        TableModel mod = tbl_users.getModel();
        linfoUser.setText(mod.getValueAt(i, 0) + "");
        l_username.setText(mod.getValueAt(i, 8) + "");

        //addItem(mod.getValueAt(i, 9)+"");
    }//GEN-LAST:event_tbl_usersMouseClicked

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        try {
            // TODO add your handling code here:
            new Table_Users_Type(this.login,this.idService).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(Table_Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void bt_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_editActionPerformed
        try {
            new EditUser(login, parseInt(linfoUser.getText())).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(Table_Users_Type.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_editActionPerformed

    private void bt_userdataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_userdataActionPerformed
        try
        {new InfoUser(this.login,parseInt(linfoUser.getText())).setVisible(true);}
        catch(Exception e){JOptionPane.showMessageDialog(this, e);}
    }//GEN-LAST:event_bt_userdataActionPerformed

    private void bt_add_vehicleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_add_vehicleActionPerformed
        try {
            new AddVehicle(login, parseInt(linfoUser.getText()), l_username.getText()).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(Table_Users_Type.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_add_vehicleActionPerformed

    private void bt_vehicles1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_vehicles1ActionPerformed
        try {
            // TODO add your handling code here:
            new Table_Vehicles_PU(login, parseInt(linfoUser.getText())).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(Table_Users.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_vehicles1ActionPerformed

    private void bt_budgetsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_budgetsActionPerformed
        try {
            new Table_Budgets_PU(login, parseInt(linfoUser.getText())).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(Table_Users_Type.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_budgetsActionPerformed

    private void bt_repairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_repairActionPerformed
        try {
            new Table_Repairs_PU(login, parseInt(linfoUser.getText())).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(Table_Users_Type.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_repairActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_add_vehicle;
    private javax.swing.JButton bt_budgets;
    private javax.swing.JButton bt_edit;
    private javax.swing.JButton bt_repair;
    private javax.swing.JButton bt_userdata;
    private javax.swing.JButton bt_vehicles1;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel l_username;
    private javax.swing.JLabel linfoUser;
    private javax.swing.JTable tbl_users;
    // End of variables declaration//GEN-END:variables
}
