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
import java.util.logging.Level;
import java.util.logging.Logger;
import users.employer.APIEmployer;
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
    public MainUsers(String login, int idService, int idEmployer) throws Exception {
        initComponents();
        bt_showUsersType.setVisible(idService == 1 || idService == 2);
        bt_func_list.setVisible(idService == 1 || idService == 2);
        bt_func_list_type.setVisible(idService == 1 || idService == 2);
        bt_func_list_old.setVisible((idService == 1 || idService == 2) && new APIEmployer().ShowServiceEmployer(login, 0).length > 0);
        bt_func_list_type_old.setVisible((idService == 1 || idService == 2) && new APIEmployer().ShowServiceEmployer(login, 0).length > 0);
        Events(login, idService, idEmployer);
    }

    /**
     * Aqui é definido o login
     *
     * @param login
     * @param idService
     * @param idEmployer
     */
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
                BT_ListUser(evt, login, idService, idEmployer);

            }
        });
        bt_showUsersType.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_ListUserType(evt, login, idService, idEmployer);
            }
        });
        bt_func_list.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Fl(evt, login, idService, idEmployer);
            }
        });
        bt_func_list_old.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Flo(evt, login, idService, idEmployer);
            }
        });
        bt_func_list_type.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Flt(evt, login, idService, idEmployer);
            }
        });
        bt_func_list_type_old.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Flto(evt, login, idService, idEmployer);
            }
        });
    }

    /**
     * Abre a form de adicionar utilizador
     *
     * @param evt
     * @param login
     */
    private void BT_addUser(java.awt.event.ActionEvent evt, String login) {
        try {
            new AddUser(login).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(MainUsers.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Abre a form de listagem de utilizadores
     *
     * @param evt
     * @param login
     * @param idService
     * @param idEmployer
     */
    private void BT_ListUser(java.awt.event.ActionEvent evt, String login, int idService, int idEmployer) {
        try {
            new Table_Users(login, idService, idEmployer).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(MainUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Abre a form de listagem de utilizadores por estado
     *
     * @param evt
     * @param login
     * @param idService
     * @param idEmployer
     */
    private void BT_ListUserType(java.awt.event.ActionEvent evt, String login, int idService, int idEmployer) {
        try {
            new Table_Users_Type(login, idService, idEmployer).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(MainUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Abre a form de listagem de funcionários
     *
     * @param evt
     * @param login
     * @param idService
     * @param idEmployer
     */
    private void BT_Fl(java.awt.event.ActionEvent evt, String login, int idService, int idEmployer) {
        try {
            new Table_Employer(login, idService, idEmployer).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(MainUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Abre a form de listagem de antigos funcionários
     *
     * @param evt
     * @param login
     * @param idService
     * @param idEmployer
     */
    private void BT_Flo(java.awt.event.ActionEvent evt, String login, int idService, int idEmployer) {
        try {
            new Table_Employer_Old(login, idService, idEmployer).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(MainUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Abre a form de listagem de funcionários por serviço
     *
     * @param evt
     * @param login
     * @param idService
     * @param idEmployer
     */
    private void BT_Flt(java.awt.event.ActionEvent evt, String login, int idService, int idEmployer) {
        try {
            new Table_Employer_Service(login, idService, idEmployer).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(MainUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Abre a form de listagem de antigos funcionários por serviço
     *
     * @param evt
     * @param login
     * @param idService
     * @param idEmployer
     */
    private void BT_Flto(java.awt.event.ActionEvent evt, String login, int idService, int idEmployer) {
        try {
            new Table_Employer_Service_Old(login, idService, idEmployer).setVisible(true);
            dispose();
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_func_list, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_showUsers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_func_list_old, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(bt_addUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_func_list_type_old, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_func_list_type, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_showUsersType, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bt_addUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_showUsersType)
                    .addComponent(bt_showUsers))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_func_list)
                    .addComponent(bt_func_list_type))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_func_list_type_old)
                    .addComponent(bt_func_list_old))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(642, 171));
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
