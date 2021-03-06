/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repairs.repairs;

import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import repairs.employers.Table_Employer_Repair;
import repairs.parts.AddParts;
import repairs.parts.ListPartsRepair;

/**
 *
 * @author Rui Barcelos
 */
public class InfoRepair extends javax.swing.JFrame {

    /**
     * Creates new form Info
     *
     * @param login
     * @param idRepair
     * @param idService
     * @throws Exception
     */
    public InfoRepair(String login, int idRepair, int idService) throws Exception {
        initComponents();
        ShowData(login, idRepair);
        Events(login, idRepair, idService);

    }

    /**
     * Mostra dos dados na form
     *
     * @param login
     * @param id
     * @throws Exception
     */
    private void ShowData(String login, int id) throws Exception {
        APIRepair api = new APIRepair();
        String info[] = api.GetInfoRepair(login, id);
        l_idRepair.setText(info[0]);
        l_vehicle.setText(info[1]);
        l_description.setText("<html>" + info[2] + "</html>");
        if (info[3] != null) {
            l_price.setText(info[3]);
        } else {
            l_price.setVisible(false);
            l_pric.setVisible(false);
        }
        l_state.setText(info[4]);
        l_dStart.setText(info[5]);
        if (info[6] != null) {
            l_dFinish.setText(info[6]);
        } else {
            l_dFinish.setVisible(false);
            l_dFin.setVisible(false);
        }
        if (info[7] != null) {
            l_resolution.setText("<html>" + info[7] + "</html>");
        } else {
            l_resolution.setVisible(false);
            l_res.setVisible(false);
        }
    }

    /**
     * Mostra os eventos
     *
     * @param login
     * @param idRepair
     * @param idService
     */
    private void Events(final String login, final int idRepair, final int idService) {
        bt_parts.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    BT_ListPartsRepair(evt, login, idRepair, idService);
                } catch (Exception ex) {
                    Logger.getLogger(AddParts.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        bt_edit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    BT_Edit(evt, login, idRepair);
                } catch (Exception ex) {
                    Logger.getLogger(AddParts.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        bt_emplyers.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    BT_Employers(evt, login, idRepair, idService);
                } catch (Exception ex) {
                    Logger.getLogger(AddParts.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        bt_parts.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    BT_ListPartsRepair(evt, login, idRepair, idService);
                } catch (Exception ex) {
                    Logger.getLogger(AddParts.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    /**
     * Abre as peças para listar uma reparação
     *
     * @param evt
     * @param login
     * @param idRepair
     * @param idService
     * @throws Exception
     */
    private void BT_ListPartsRepair(java.awt.event.ActionEvent evt, String login, int idRepair, int idService) throws Exception {
        new ListPartsRepair(login, idRepair, idService).setVisible(true);
    }

    /**
     * Abre o formulário de editar
     *
     * @param evt
     * @param login
     * @param idRepair
     */
    private void BT_Edit(java.awt.event.ActionEvent evt, String login, int idRepair) {
        try {
            new EditRepair(login, idRepair).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(InfoRepair.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Abre a form de dos funcionários de uma determinada reparação
     * @param evt
     * @param login
     * @param idRepair
     * @param idService 
     */
    private void BT_Employers(java.awt.event.ActionEvent evt, String login, int idRepair, int idService) {
        try {
            new Table_Employer_Repair(login, idRepair, idService).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(InfoRepair.class.getName()).log(Level.SEVERE, null, ex);
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

        l_idRe = new javax.swing.JLabel();
        l_idRepair = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        l_vehicle = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        l_description = new javax.swing.JLabel();
        l_pric = new javax.swing.JLabel();
        l_price = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        l_state = new javax.swing.JLabel();
        l_dStart = new javax.swing.JLabel();
        l_dFinish = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        l_dFin = new javax.swing.JLabel();
        l_resolution = new javax.swing.JLabel();
        l_res = new javax.swing.JLabel();
        bt_edit = new javax.swing.JButton();
        bt_emplyers = new javax.swing.JButton();
        bt_parts = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Informação da Reparação");
        setResizable(false);

        l_idRe.setText("Reparação n.º");

        l_idRepair.setText("id");

        jLabel1.setText("Viatura:");

        l_vehicle.setText("Viatura");

        jLabel2.setText("Descrição do Problema:");

        l_description.setText("Descrição");

        l_pric.setText("Preço:");

        l_price.setText("preco");

        jLabel4.setText("Estado:");

        l_state.setText("estado");

        l_dStart.setText("inicio");

        l_dFinish.setText("conclusao");

        jLabel3.setText("Início");

        l_dFin.setText("Conclusão:");

        l_resolution.setText("resolucao");

        l_res.setText("Resolução:");

        bt_edit.setText("Editar");

        bt_emplyers.setText("Funcionários");

        bt_parts.setText("Ver Peças");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bt_emplyers)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bt_parts)
                                .addGap(18, 18, 18)
                                .addComponent(bt_edit)
                                .addGap(0, 319, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(l_idRe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(l_vehicle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(l_idRepair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(l_res)
                            .addComponent(jLabel2)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(l_description, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(l_pric, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(l_state, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(l_price, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addComponent(l_resolution, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(l_dFin))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(l_dFinish, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(l_dStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l_idRe)
                    .addComponent(l_idRepair))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(l_vehicle))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l_dStart)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l_dFinish)
                    .addComponent(l_dFin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(l_description, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(l_res)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(l_resolution, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l_pric)
                    .addComponent(l_price))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(l_state))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_edit)
                    .addComponent(bt_emplyers)
                    .addComponent(bt_parts))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(616, 640));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_edit;
    private javax.swing.JButton bt_emplyers;
    private javax.swing.JButton bt_parts;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel l_dFin;
    private javax.swing.JLabel l_dFinish;
    private javax.swing.JLabel l_dStart;
    private javax.swing.JLabel l_description;
    private javax.swing.JLabel l_idRe;
    private javax.swing.JLabel l_idRepair;
    private javax.swing.JLabel l_pric;
    private javax.swing.JLabel l_price;
    private javax.swing.JLabel l_res;
    private javax.swing.JLabel l_resolution;
    private javax.swing.JLabel l_state;
    private javax.swing.JLabel l_vehicle;
    // End of variables declaration//GEN-END:variables
}
