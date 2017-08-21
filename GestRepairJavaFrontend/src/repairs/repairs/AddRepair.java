/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repairs.repairs;

import java.awt.Toolkit;
import javax.swing.JOptionPane;
import static javax.xml.bind.DatatypeConverter.parseInt;
import services.APIService;
import users.employer.APIEmployer;

/**
 *
 * @author Rui Barcelos
 */
public final class AddRepair extends javax.swing.JFrame {

    APIRepair api = new APIRepair();
    APIService apiService = new APIService();
    APIEmployer apiEmployer = new APIEmployer();
    private final int vehicle, employer, idService;
    private final String login;

    /**
     * Creates new form AddRepair
     *
     * @param login
     * @param vehicle
     * @param employer
     * @param idService
     * @throws java.lang.Exception
     */
    public AddRepair(String login, int vehicle, int employer, int idService) throws Exception {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../../img/imageedit_4_8303763918.png")));
        this.login = login;
        if (idService == 1 || idService == 2) {
            showService(apiService.Service(login));
            showEmployers(apiEmployer.ShowEmployer(login, 1, Cb_Val(cb_service.getSelectedIndex() + 2, apiService.Service(login))));
            cb_service.removeItemAt(0);
            cb_service.removeItemAt(0);
            l_idService.setVisible(false);
            l_employer.setVisible(false);
        } else {
            cb_service.setVisible(false);
            cb_employer.setVisible(false);
            l_idService.setText(apiService.GetInfo(login, idService)[1]);
            l_employer.setText(apiEmployer.GetInfoEmployerUser(login, employer)[1]);
        }
        ta_pdesc.setLineWrap(true);
        this.employer = employer;
        this.vehicle = vehicle;
        this.idService = idService;
    }

    private void showService(String[][] list) {
        cb_service.removeAllItems();
        for (String[] list1 : list) {
            cb_service.addItem(list1[1]);
        }
    }

    private void showEmployers(String[][] list) {
        cb_employer.removeAllItems();
        for (String[] list1 : list) {
            cb_employer.addItem(list1[2]);
        }
    }

    private int Cb_Val(int val, String[][] list) {
        return parseInt(list[val][0]);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cb_service = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_pdesc = new javax.swing.JTextArea();
        l_idService = new javax.swing.JLabel();
        cb_employer = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        l_employer = new javax.swing.JLabel();
        bt_add = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Adicionar Reparação");

        jLabel1.setText("Serviço:");

        cb_service.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_service.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_serviceActionPerformed(evt);
            }
        });

        jLabel2.setText("Descrição do Problema:");

        ta_pdesc.setColumns(20);
        ta_pdesc.setRows(5);
        jScrollPane1.setViewportView(ta_pdesc);

        l_idService.setText("Service");

        cb_employer.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Funcionário:");

        l_employer.setText("employer");

        bt_add.setText("Adicionar");
        bt_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_addActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(l_idService)
                                .addGap(0, 74, Short.MAX_VALUE))
                            .addComponent(cb_service, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(l_employer))
                            .addComponent(cb_employer, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(76, 76, 76))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_add)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(l_idService)
                    .addComponent(jLabel3)
                    .addComponent(l_employer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_service, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_employer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(bt_add)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cb_serviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_serviceActionPerformed
        try {
            showEmployers(apiEmployer.ShowEmployer(this.login, 1, Cb_Val(cb_service.getSelectedIndex() + 2, apiService.Service(this.login))));
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_cb_serviceActionPerformed

    private void bt_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addActionPerformed
        try {
            int serv = Cb_Val(cb_service.getSelectedIndex() + 2, apiService.Service(this.login));
            int empl = Cb_Val(cb_employer.getSelectedIndex(), apiEmployer.ShowEmployer(login, 1, serv));
            int x = JOptionPane.showConfirmDialog(this, "Tem a ceteza que quer inserir os dados?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (this.idService == 1 || this.idService == 2) {
                    if ("ok".equals(api.PostRepair(this.login, this.vehicle, ta_pdesc.getText(), empl))) {
                        JOptionPane.showMessageDialog(this, "Reparação inserida com sucesso!");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Erro ao inserir os dados!");
                    }
                } else {
                    if ("ok".equals(api.PostRepair(this.login, this.vehicle, ta_pdesc.getText(), this.employer))) {
                        JOptionPane.showMessageDialog(this, "Reparação inserida com sucesso!");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Erro ao inserir os dados!");
                    }
                }
                dispose();
            } else if (x == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(this, "A Reparação não foi introduzida no sistema!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro a adicionar reparação!\n Verifique se os dados estão corretos");
        }
    }//GEN-LAST:event_bt_addActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_add;
    private javax.swing.JComboBox cb_employer;
    private javax.swing.JComboBox cb_service;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel l_employer;
    private javax.swing.JLabel l_idService;
    private javax.swing.JTextArea ta_pdesc;
    // End of variables declaration//GEN-END:variables
}
