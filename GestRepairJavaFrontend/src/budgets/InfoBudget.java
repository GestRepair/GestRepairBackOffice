/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgets;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rui Barcelos
 */
public class InfoBudget extends javax.swing.JFrame {

    /**
     * Cria um formulário para mostrar a informação do orçamento
     * @param login
     * @param idBudget
     * @throws Exception
     */
    public InfoBudget(String login, int idBudget) throws Exception {
        initComponents();
        ShowData(login, idBudget);
        Events(login, idBudget);
    }

    /**
     * Vai buscar os dados à API para mostrar
     * @param login
     * @param id
     * @throws Exception
     */
    private void ShowData(String login, int id) throws Exception {
        APIBudgets api = new APIBudgets();
        String info[] = api.GetInfoBudget(login, id);
        l_idRepair.setText(info[0]);
        l_vehicle.setText(info[1]);
        l_description.setText("<html>"+info[2]+"</html>");
        if (info[4] != null) {
            l_price.setText(info[4]);
        } else {
            l_price.setVisible(false);
            l_pric.setVisible(false);
        }
        l_state.setText(info[3]);
        if (info[6] != null) {
        l_trep.setText(info[6]);
        } else {
            l_trep.setVisible(false);
            l_itrep.setVisible(false);
            ldis.setVisible(false);
        }
        l_dStart.setText(info[5]);
        if (info[7] != null) {
            l_dFinish.setText(info[7]);
        } else {
            l_dFinish.setVisible(false);
            l_dFin.setVisible(false);
        }
        if (info[8] != null) {
            l_resolution.setText("<html>"+info[8]+"</html>");
        } else {
            l_resolution.setVisible(false);
            l_res.setVisible(false);
        }
    }
    /**
     * Aqui é onde é definido os eventos
     * @param login
     * @param idBudget 
     */
    private void Events(final String login, final int idBudget) {
        bt_edit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Edit(evt, login, idBudget);
            }
        });
        bt_services.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_SERVICES(evt, login, idBudget);
            }
        });
    }
    /**
     * Quando o botão Serviços é clicado muda para a outra form e fecha esta
     * @param evt
     * @param login
     * @param idBudget 
     */
    private void BT_SERVICES(java.awt.event.ActionEvent evt, String login, int idBudget) {
        try {
            new Table_Service_Budget(login, idBudget).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Service_Budget.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Abre a form Editar orçamento e fecha esta
     * @param evt
     * @param login
     * @param idBudget 
     */
    private void BT_Edit(java.awt.event.ActionEvent evt, String login, int idBudget) {
        try {
            new EditBudget(login, idBudget).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Service_Budget.class.getName()).log(Level.SEVERE, null, ex);
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
        bt_services = new javax.swing.JButton();
        l_trep = new javax.swing.JLabel();
        l_itrep = new javax.swing.JLabel();
        ldis = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Informação do Orçamento");
        setResizable(false);

        l_idRe.setText("Informação do orçamento n.º:");

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

        bt_services.setText("Ver Serviços");

        l_trep.setText("trep");

        l_itrep.setText("Tempo de Reparação:");

        ldis.setText("dias");

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
                                .addComponent(l_idRe)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l_idRepair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l_vehicle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(l_dFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(l_dFinish, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(l_dStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 467, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(l_res, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 526, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bt_services, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bt_edit, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(l_pric, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(l_itrep, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(l_trep, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ldis, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(379, 379, 379))
                                    .addComponent(l_price, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(l_state, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addComponent(l_description, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(l_resolution, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l_idRe)
                    .addComponent(l_idRepair))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(l_vehicle))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l_dStart)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l_dFinish)
                    .addComponent(l_dFin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(l_description, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(l_res)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(l_resolution, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(l_state))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l_pric)
                    .addComponent(l_price))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(l_trep, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ldis))
                    .addComponent(l_itrep))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_edit)
                    .addComponent(bt_services))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(616, 624));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_edit;
    private javax.swing.JButton bt_services;
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
    private javax.swing.JLabel l_itrep;
    private javax.swing.JLabel l_pric;
    private javax.swing.JLabel l_price;
    private javax.swing.JLabel l_res;
    private javax.swing.JLabel l_resolution;
    private javax.swing.JLabel l_state;
    private javax.swing.JLabel l_trep;
    private javax.swing.JLabel l_vehicle;
    private javax.swing.JLabel ldis;
    // End of variables declaration//GEN-END:variables
}
