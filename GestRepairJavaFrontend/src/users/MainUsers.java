/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import users.user.AddUser;
import users.user.Table_Users;
import users.user.Table_Users_Type;
import users.employer.Table_Employer_Service_Old;
import users.employer.Table_Employer_Service;
import users.employer.Table_Employer;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import users.employer.Table_Employer_Old;

/**
 *
 * @author Convite
 */
public class MainUsers extends javax.swing.JFrame {
    /**
     * Creates new form mainVehicles
     *
     * @param login
     * @param idService
     * @param idEmployer
     */
    public MainUsers(String login, int idService, int idEmployer) {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../img/imageedit_4_8303763918.png")));
        Events(login, idService,idEmployer);
    }

    private void Events(final String login, final int idService, final int idEmployer) {
        bt_addUser.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_addUser(evt, login);
            }
        });
        bt_showUsers.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_ListUser(evt, login, idService,idEmployer);
            }
        });
        bt_showUsersType.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_ListUserType(evt, login, idService,idEmployer);
            }
        });
        bt_func_list.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Fl(evt, login, idService,idEmployer);
            }
        });
        bt_func_list_old.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Flo(evt, login, idService,idEmployer);
            }
        });
        bt_func_list_type.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Flt(evt, login, idService,idEmployer);
            }
        });
        bt_func_list_type_old.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Flto(evt, login, idService,idEmployer);
            }
        });
    }

    private void BT_addUser(java.awt.event.ActionEvent evt, String login) {
        try {
            new AddUser(login).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(MainUsers.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void BT_ListUser(java.awt.event.ActionEvent evt, String login, int idService, int idEmployer) {
        try {
            new Table_Users(login, idService,idEmployer).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(MainUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void BT_ListUserType(java.awt.event.ActionEvent evt, String login, int idService, int idEmployer) {
        try {
            new Table_Users_Type(login, idService, idEmployer).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(MainUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void BT_Fl(java.awt.event.ActionEvent evt, String login, int idService,int idEmployer) {
        try {
            new Table_Employer(login, idService,idEmployer).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(MainUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void BT_Flo(java.awt.event.ActionEvent evt, String login, int idService,int idEmployer) {
        try {
            new Table_Employer_Old(login, idService, idEmployer).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(MainUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void BT_Flt(java.awt.event.ActionEvent evt, String login, int idService,int idEmployer) {
        try {
            new Table_Employer_Service(login, idService, idEmployer).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(MainUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void BT_Flto(java.awt.event.ActionEvent evt, String login, int idService,int idEmployer) {
        try {
            new Table_Employer_Service_Old(login, idService, idEmployer).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(MainUsers.class.getName()).log(Level.SEVERE, null, ex);
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

        bt_showUsers = new javax.swing.JButton();
        bt_addUser = new javax.swing.JButton();
        bt_showUsersType = new javax.swing.JButton();
        bt_func_list = new javax.swing.JButton();
        bt_func_list_type = new javax.swing.JButton();
        bt_func_list_old = new javax.swing.JButton();
        bt_func_list_type_old = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Menu Utilizadores");
        setResizable(false);

        bt_showUsers.setText("Lista de Utilizadores");

        bt_addUser.setText("Adicionar Utilizador");

        bt_showUsersType.setText("Lista de Utilizadores Por Categoria");

        bt_func_list.setText("Lista de Funcionários");

        bt_func_list_type.setText("Lista de Funcionários por Serviço");

        bt_func_list_old.setText("Lista de Antigos Funcionários");

        bt_func_list_type_old.setText("Lista de Antigos  Funcionários por Serviço");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(bt_showUsersType, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(bt_showUsers, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_func_list, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_func_list_type, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_func_list_type_old, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(bt_func_list_old, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_addUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bt_showUsers)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_showUsersType)
                    .addComponent(bt_addUser))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bt_func_list_old, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_func_list_type_old)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bt_func_list)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_func_list_type)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        setSize(new java.awt.Dimension(542, 171));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_addUser;
    private javax.swing.JButton bt_func_list;
    private javax.swing.JButton bt_func_list_old;
    private javax.swing.JButton bt_func_list_type;
    private javax.swing.JButton bt_func_list_type_old;
    private javax.swing.JButton bt_showUsers;
    private javax.swing.JButton bt_showUsersType;
    // End of variables declaration//GEN-END:variables
}
