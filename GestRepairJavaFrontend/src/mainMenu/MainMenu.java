/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainMenu;

import budgets.MainBudgets;
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
     * @param data
     * @throws java.lang.Exception
     */
    public MainMenu(String login, String data) throws Exception {
        initComponents();
        setMenu(login, data);
    }

    /**
     * Insere os dados na form
     *
     * @param login
     * @param dados
     * @throws Exception
     */
    private void setMenu(String login, String dados) throws Exception {
        APIEmployer api = new APIEmployer();
        try {
            int idEmployer, idService;
            long idUser;
            JSONObject newjson = (JSONObject) new JSONParser().parse(dados);
            String data = newjson.get("data").toString();
            JSONObject newjsondata = (JSONObject) new JSONParser().parse(data);
            String name = newjsondata.get("name").toString();
            l_nome.setText(name);
            idUser = (long) newjsondata.get("idUser");
            idEmployer = parseInt(api.GetInfoEmployerUser(login, (int) idUser)[0]);
            idService = parseInt(api.GetInfoEmployerUser(login, (int) idUser)[3]);
            l_service.setText(api.GetInfoEmployerUser(login, (int) idUser)[2]);
            bt_services.setVisible(idService == 1);
            bt_parts.setVisible(idService == 1 || idService == 2);
            Events(login, idEmployer, idService, (int) idUser);
        } catch (ParseException pe) {
            System.out.println("Erro");
        }

    }

    /**
     * Aqui é onde é definido os eventos
     *
     * @param login
     * @param idEmployer
     * @param idService
     * @param idUser
     */
    private void Events(final String login, final int idEmployer, final int idService, final int idUser) {
        bt_utilizadores.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_User(evt, login, idService, idEmployer);
            }
        });
        bt_veiculos.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Vehicles(evt, login, idService, idEmployer);
            }
        });
        bt_repair.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Repair(evt, login, idService);
            }
        });
        bt_services.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Service(evt, login);
            }
        });
        bt_budgets.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Budgets(evt, login);
            }
        });
        bt_Schedule.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Schedule(evt, login);
            }
        });
        bt_parts.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Parts(evt, login, idService);
            }
        });
        mi_exit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_Exit(evt);
            }
        });
        mi_edit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_Edit(evt, login, idUser);
            }
        });
        mi_change_pass.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_ChangePass(evt, login, idUser);
            }
        });
        mi_info.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_Info(evt, login, idUser);
            }
        });
    }

    /**
     * Aqui abre o menú de utilizadores
     *
     * @param evt
     * @param login
     * @param idService
     * @param idEmployer
     */
    private void BT_User(java.awt.event.ActionEvent evt, String login, int idService, int idEmployer) {
        try {
            new MainUsers(login, idService, idEmployer).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(MainParts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Aqui abre o menu das viaturas
     *
     * @param evt
     * @param login
     * @param idService
     * @param idEmployer
     */
    private void BT_Vehicles(java.awt.event.ActionEvent evt, String login, int idService, int idEmployer) {
        try {
            new MainVehicles(login, idService, idEmployer).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(MainParts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Aqui Abre o menu das reparações
     *
     * @param evt
     * @param login
     * @param idService
     */
    private void BT_Repair(java.awt.event.ActionEvent evt, String login, int idService) {
        try {
            new MainRepairs(login, idService).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(MainParts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Aqui Abre o menu dos serviços
     *
     * @param evt
     * @param login
     */
    private void BT_Service(java.awt.event.ActionEvent evt, String login) {
        try {
            new MainServices(login).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(MainParts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Aqui abre o menu dos orçamentos
     *
     * @param evt
     * @param login
     */
    private void BT_Budgets(java.awt.event.ActionEvent evt, String login) {
        try {
            new MainBudgets(login).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(MainParts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Aqui mostra o menu das marcações
     *
     * @param evt
     * @param login
     */
    private void BT_Schedule(java.awt.event.ActionEvent evt, String login) {
        try {
            new MainSchedule(login).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(MainParts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Aqui mostra o menu das peças
     *
     * @param evt
     * @param login
     * @param idService
     */
    private void BT_Parts(java.awt.event.ActionEvent evt, String login, int idService) {
        try {
            new MainParts(login, idService).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(MainParts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Aqui Faz fechar o programa
     *
     * @param evt
     */
    private void MI_Exit(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    /**
     * Aqui edita o próprio utilizador
     *
     * @param evt
     * @param login
     * @param idUser
     */
    private void MI_Edit(java.awt.event.ActionEvent evt, String login, int idUser) {
        try {
            new EditUser(login, idUser).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Mostra a janela para mudar a password
     *
     * @param evt
     * @param login
     * @param idUser
     */
    private void MI_ChangePass(java.awt.event.ActionEvent evt, String login, int idUser) {
        try {
            new EditPassword(login, idUser).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Aqui Abre os detalhes do seu próprio prefil
     *
     * @param evt
     * @param login
     * @param idUser
     */
    private void MI_Info(java.awt.event.ActionEvent evt, String login, int idUser) {
        try {
            new InfoUser(login, idUser).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
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
        bt_services = new javax.swing.JButton();
        bt_budgets = new javax.swing.JButton();
        bt_Schedule = new javax.swing.JButton();
        bt_parts = new javax.swing.JButton();
        l_id_nome1 = new javax.swing.JLabel();
        l_service = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        m_session = new javax.swing.JMenu();
        mi_info = new javax.swing.JMenuItem();
        mi_edit = new javax.swing.JMenuItem();
        mi_change_pass = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        mi_exit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GestRepair - Menu Principal");
        setResizable(false);

        bt_utilizadores.setText("Utilizadores");

        bt_veiculos.setText("Viaturas");

        bt_repair.setText("Reparações");

        l_id_nome.setText("Nome:");

        l_nome.setText("Exemplo de Nome");

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel1.setText("GEST REPAIR");

        bt_services.setText("Serviços");

        bt_budgets.setText("Orçamentos");

        bt_Schedule.setText("Marcações");

        bt_parts.setText("Peças");

        l_id_nome1.setText("Serviço:");

        l_service.setText("Seviço");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/imageedit_4_8303763918.png"))); // NOI18N

        m_session.setText("Sessão");

        mi_info.setText("Perfil");
        m_session.add(mi_info);

        mi_edit.setText("Editar");
        m_session.add(mi_edit);

        mi_change_pass.setText("Alterar Password");
        m_session.add(mi_change_pass);

        jMenuItem1.setText("Creditos");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        m_session.add(jMenuItem1);

        mi_exit.setText("Sair");
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
                        .addComponent(l_id_nome)
                        .addGap(18, 18, 18)
                        .addComponent(l_nome))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(bt_budgets, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                        .addComponent(bt_Schedule, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_services, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_parts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(l_id_nome1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(l_service))
                        .addComponent(bt_repair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_utilizadores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_veiculos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(71, 71, 71)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(l_id_nome)
                            .addComponent(l_nome))
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(l_id_nome1)
                            .addComponent(l_service)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
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
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(505, 389));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Abre a janela para mostar a informação do programa
     * @param evt 
     */
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new info().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_Schedule;
    private javax.swing.JButton bt_budgets;
    private javax.swing.JButton bt_parts;
    private javax.swing.JButton bt_repair;
    private javax.swing.JButton bt_services;
    private javax.swing.JButton bt_utilizadores;
    private javax.swing.JButton bt_veiculos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JLabel l_id_nome;
    private javax.swing.JLabel l_id_nome1;
    private javax.swing.JLabel l_nome;
    private javax.swing.JLabel l_service;
    private javax.swing.JMenu m_session;
    private javax.swing.JMenuItem mi_change_pass;
    private javax.swing.JMenuItem mi_edit;
    private javax.swing.JMenuItem mi_exit;
    private javax.swing.JMenuItem mi_info;
    // End of variables declaration//GEN-END:variables
}
