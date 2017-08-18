/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainMenu;

import budgets.MainBudgets;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.xml.bind.DatatypeConverter.parseInt;
import login.login_menu;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import parts.MainParts;
import repairs.MainRepairs;
import schedule.MainSchedule;
import services.MainServices;
import users.user.EditPassword;
import users.user.EditUser;
import users.user.InfoUser;
import users.MainUsers;
import users.employer.APIEmployer;
import vehicles.MainVehicles;

/**
 *
 * @author Rui Barcelos
 */
public final class MainMenu extends javax.swing.JFrame {

    /**
     * Creates new form MainMenu
     *
     * @param login
     * @param dados
     * @throws java.lang.Exception
     */
    private final String login;
    private int id, idEmployer, idService;

    public MainMenu(String login, String dados) throws Exception {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../img/imageedit_4_8303763918.png")));
        setMenu(login, dados);
        this.login=login;
    }

    
    public void setMenu(String login, String dados) throws Exception {
        APIEmployer api = new APIEmployer();
        String service;
        int idEmployer, idService;
        try {
            
            JSONObject newjson = (JSONObject) new JSONParser().parse(dados);
            String data = newjson.get("data").toString();
            JSONObject newjsondata = (JSONObject) new JSONParser().parse(data);
            String name = newjsondata.get("name").toString();
            l_nome.setText(name);
            long idUser = (long) newjsondata.get("idUser");       
            int id = (int) idUser;
            this.id = id;
            idEmployer = parseInt(api.GetInfoEmployer(login, id)[0]);
            this.idEmployer = idEmployer;
            idService = parseInt(api.GetInfoEmployer(login, id)[3]);
            this.idService = idService;
            l_service.setText(api.GetInfoEmployer(login, id)[2]);
        } catch (ParseException pe) {
            System.out.println("Erro");
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

        bt_utilizadores = new javax.swing.JButton();
        bt_veiculos = new javax.swing.JButton();
        bt_repair = new javax.swing.JButton();
        l_id_nome = new javax.swing.JLabel();
        l_nome = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bt_services = new javax.swing.JButton();
        bt_budgets = new javax.swing.JButton();
        bt_Schedule = new javax.swing.JButton();
        bt_parts = new javax.swing.JButton();
        bt_providers = new javax.swing.JButton();
        l_id_nome1 = new javax.swing.JLabel();
        l_service = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        m_session = new javax.swing.JMenu();
        mi_info = new javax.swing.JMenuItem();
        mi_edit = new javax.swing.JMenuItem();
        mi_change_pass = new javax.swing.JMenuItem();
        mi_logout = new javax.swing.JMenuItem();
        mi_exit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GestRepair - Menu Principal");
        setResizable(false);

        bt_utilizadores.setText("Utilizadores");
        bt_utilizadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_utilizadoresActionPerformed(evt);
            }
        });

        bt_veiculos.setText("Viaturas");
        bt_veiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_veiculosActionPerformed(evt);
            }
        });

        bt_repair.setText("Reparações");
        bt_repair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_repairActionPerformed(evt);
            }
        });

        l_id_nome.setText("Nome:");

        l_nome.setText("Exemplo de Nome");

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel1.setText("GEST REPAIR");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/imageedit_4_8303763918.png"))); // NOI18N

        bt_services.setText("Serviços");
        bt_services.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_servicesActionPerformed(evt);
            }
        });

        bt_budgets.setText("Orçamentos");
        bt_budgets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_budgetsActionPerformed(evt);
            }
        });

        bt_Schedule.setText("Marcações");
        bt_Schedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_ScheduleActionPerformed(evt);
            }
        });

        bt_parts.setText("Peças");
        bt_parts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_partsActionPerformed(evt);
            }
        });

        bt_providers.setText("Fornecedores");

        l_id_nome1.setText("Serviço:");

        l_service.setText("Seviço");

        m_session.setText("Sessão");

        mi_info.setText("Perfil");
        mi_info.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_infoActionPerformed(evt);
            }
        });
        m_session.add(mi_info);

        mi_edit.setText("Editar");
        mi_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_editActionPerformed(evt);
            }
        });
        m_session.add(mi_edit);

        mi_change_pass.setText("Alterar Password");
        mi_change_pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_change_passActionPerformed(evt);
            }
        });
        m_session.add(mi_change_pass);

        mi_logout.setText("Terminar Sessão");
        mi_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_logoutActionPerformed(evt);
            }
        });
        m_session.add(mi_logout);

        mi_exit.setText("Sair");
        mi_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_exitActionPerformed(evt);
            }
        });
        m_session.add(mi_exit);

        jMenuBar1.add(m_session);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(bt_veiculos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bt_utilizadores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bt_repair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bt_budgets, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bt_Schedule, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bt_services, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bt_parts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bt_providers, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(l_id_nome1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(l_service)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(l_id_nome)
                        .addGap(18, 18, 18)
                        .addComponent(l_nome)
                        .addGap(51, 51, 51)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(l_id_nome)
                        .addComponent(l_nome))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(l_id_nome1)
                            .addComponent(l_service))
                        .addGap(18, 18, 18)
                        .addComponent(bt_utilizadores)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_veiculos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_repair)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_budgets)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_Schedule)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_services)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_parts)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_providers))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_utilizadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_utilizadoresActionPerformed
        new MainUsers(this.login, this.idService, this.idEmployer).setVisible(true);
    }//GEN-LAST:event_bt_utilizadoresActionPerformed

    private void bt_servicesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_servicesActionPerformed
        new MainServices(this.login).setVisible(true);
    }//GEN-LAST:event_bt_servicesActionPerformed

    private void bt_veiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_veiculosActionPerformed
        new MainVehicles(this.login,this.idEmployer,this.idService).setVisible(true);
    }//GEN-LAST:event_bt_veiculosActionPerformed

    private void bt_repairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_repairActionPerformed
        new MainRepairs(this.login,this.idService).setVisible(true);
    }//GEN-LAST:event_bt_repairActionPerformed

    private void bt_budgetsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_budgetsActionPerformed
        new MainBudgets(this.login).setVisible(true);
    }//GEN-LAST:event_bt_budgetsActionPerformed

    private void bt_ScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_ScheduleActionPerformed
        new MainSchedule(this.login).setVisible(true);
    }//GEN-LAST:event_bt_ScheduleActionPerformed

    private void mi_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_logoutActionPerformed
        // TODO add your handling code here:
        new login_menu().setVisible(true);
        dispose();
    }//GEN-LAST:event_mi_logoutActionPerformed

    private void mi_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_exitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mi_exitActionPerformed

    private void mi_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_editActionPerformed
        try {
            new EditUser(this.login, this.id).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mi_editActionPerformed

    private void mi_change_passActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_change_passActionPerformed
        try {
            new EditPassword(this.login, this.id).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mi_change_passActionPerformed

    private void mi_infoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_infoActionPerformed
        try {
            new InfoUser(this.login, this.id).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mi_infoActionPerformed

    private void bt_partsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_partsActionPerformed
        new MainParts(this.login, this.idService).setVisible(true);
    }//GEN-LAST:event_bt_partsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_Schedule;
    private javax.swing.JButton bt_budgets;
    private javax.swing.JButton bt_parts;
    private javax.swing.JButton bt_providers;
    private javax.swing.JButton bt_repair;
    private javax.swing.JButton bt_services;
    private javax.swing.JButton bt_utilizadores;
    private javax.swing.JButton bt_veiculos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel l_id_nome;
    private javax.swing.JLabel l_id_nome1;
    private javax.swing.JLabel l_nome;
    private javax.swing.JLabel l_service;
    private javax.swing.JMenu m_session;
    private javax.swing.JMenuItem mi_change_pass;
    private javax.swing.JMenuItem mi_edit;
    private javax.swing.JMenuItem mi_exit;
    private javax.swing.JMenuItem mi_info;
    private javax.swing.JMenuItem mi_logout;
    // End of variables declaration//GEN-END:variables

}
