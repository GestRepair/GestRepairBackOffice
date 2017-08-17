/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repairs.repairs;

import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rui Barcelos
 */
public class InfoRepair extends javax.swing.JFrame {
    private final String login;
    private final int idRepair;

    /**
     * Creates new form Info
     *
     * @param login
     * @param idRepair
     * @throws Exception
     */
    public InfoRepair(String login, int idRepair) throws Exception {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../../img/imageedit_4_8303763918.png")));
        initComponents();
        ShowData(login, idRepair);
        this.login = login;
        this.idRepair = idRepair;
    }
    /**
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
        l_description.setText(info[2]);
        if(info[3]!=null){
            l_price.setText(info[3]);
        }else{
            l_price.setVisible(false);
            l_pric.setVisible(false);
        }
        l_state.setText(info[4]);
        l_dStart.setText(info[5]);
        if(info[6]!=null){
            l_dFinish.setText(info[6]);
        }else{
            l_dFinish.setVisible(false);
            l_dFin.setVisible(false);
        }
        if(info[7]!=null){
            l_resolution.setText(info[7]);
        }else{
            l_resolution.setVisible(false);
            l_res.setVisible(false);
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

        l_idRe.setText("ID:");

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
        bt_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_editActionPerformed(evt);
            }
        });

        bt_emplyers.setText("Funcionários");

        bt_parts.setText("Peças");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(l_description, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(l_idRe))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(l_idRepair)
                                    .addComponent(l_vehicle)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(22, 22, 22)
                                .addComponent(l_state)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(l_dFin))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(l_dStart)
                                    .addComponent(l_dFinish)))
                            .addComponent(l_resolution, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l_res)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(l_pric)
                                .addGap(22, 22, 22)
                                .addComponent(l_price))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(bt_emplyers)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bt_parts)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_edit)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(l_idRe)
                            .addComponent(l_idRepair))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(l_vehicle)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(l_dStart)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(l_dFinish)
                            .addComponent(l_dFin))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_description, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(l_res)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_resolution, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l_pric)
                    .addComponent(l_price)
                    .addComponent(jLabel4)
                    .addComponent(l_state))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_edit)
                    .addComponent(bt_emplyers)
                    .addComponent(bt_parts))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_editActionPerformed
        try {
            new EditRepair(this.login,this.idRepair).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(InfoRepair.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_editActionPerformed

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
