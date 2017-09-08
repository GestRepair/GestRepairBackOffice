/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts;

import java.awt.Toolkit;
import javax.swing.JOptionPane;

/**
 *
 * @author Rui Barcelos
 */
public final class AddAmount extends javax.swing.JFrame {

    /**
     * Creates new form AddRepair
     *
     * @param login
     * @param idPart
     * @param idService
     * @throws java.lang.Exception
     */
    public AddAmount(String login, int idPart, int idService) throws Exception {
        APIParts api = new APIParts();
        initComponents();
        l_idPart.setText(idPart + "");
        Events(login, idPart, idService,api);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../img/imageedit_4_8303763918.png")));

    }

    private void Events(final String login, final int idPart, final int idService, final APIParts api) {
        bt_add.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_addPost(evt, login, idPart, idService,api);
            }
        });
    }

    private void BT_addPost(java.awt.event.ActionEvent evt, String login, int idPart, int idService, APIParts api) {
        try {
            if (tf_amount.getText().length() > 0) {
                int x = JOptionPane.showConfirmDialog(this, "Tem a ceteza que quer inserir os dados?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    String value[] = api.PUTAmount(login, sendData(), idPart);
                    JOptionPane.showMessageDialog(this, value[1]);
                    if ("ok".equals(value[0])) {
                        new InfoParts(login, idPart, idService).setVisible(true);
                        dispose();
                    }
                } else if (x == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(this, "A peça não foi introduzida no sistema!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por Favor preencha os dados todos!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro a adicionar reparação!\n Verifique se os dados estão corretos");
        }
    }

    private String sendData() throws Exception {
        return tf_amount.getText();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        tf_amount = new javax.swing.JTextField();
        bt_add = new javax.swing.JButton();
        l_id = new javax.swing.JLabel();
        l_idPart = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Adicionar Quantidade");
        setResizable(false);

        jLabel4.setText("Quantidade");

        bt_add.setText("Adicionar");

        l_id.setText("ID:");

        l_idPart.setText("id");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(bt_add)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tf_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(l_id)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_idPart)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l_id)
                    .addComponent(l_idPart))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tf_amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(bt_add)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(296, 155));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_add;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel l_id;
    private javax.swing.JLabel l_idPart;
    private javax.swing.JTextField tf_amount;
    // End of variables declaration//GEN-END:variables
}
