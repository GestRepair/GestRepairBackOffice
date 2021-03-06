/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles.models;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.xml.bind.DatatypeConverter.parseInt;
import vehicles.brands.APIBrand;

/**
 *
 * @author Convite
 */
public final class AddModel extends javax.swing.JFrame {

    /**
     * Creates new form addBrand
     *
     * @param login
     * @throws java.lang.Exception
     */
    public AddModel(String login) throws Exception {
        APIModel api = new APIModel();
        APIBrand apiBrand = new APIBrand();
        initComponents();
        showBrand(apiBrand.Brand(login));
        Events(login, api, apiBrand);
    }

    /**
     * Insere dados na dropdown
     *
     * @param list
     */
    private void showBrand(String[][] list) {
        CB_Brands.removeAllItems();
        for (int i = 0; i < list.length; i++) {
            CB_Brands.addItem(list[i][1]);
        }
    }

    /**
     * Eventos
     *
     * @param login
     * @param api
     * @param apiBrand
     */
    private void Events(final String login, final APIModel api, final APIBrand apiBrand) {
        bt_addModel.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_ADD(evt, login, api, apiBrand);
            }
        });
    }

    /**
     * Atravéz da posição da dropdown podereiinserir os dados
     *
     * @param val
     * @param list
     * @return
     */
    public int newIdCb(int val, String[][] list) {
        return parseInt(list[val][0]);
    }

    /**
     * Após a verificação e confirmação é adicionado uma nova viatura
     *
     * @param evt
     * @param login
     * @param api
     * @param apiBrand
     */
    private void BT_ADD(java.awt.event.ActionEvent evt, String login, APIModel api, APIBrand apiBrand) {
        try {
            int lmod = tf_model.getText().length();
            if (lmod < 25 && lmod > 1) {
                int x = JOptionPane.showConfirmDialog(this, "Tem a certeza que quer adicionar um novo modelo?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    String value[] = api.PostModel(login, newIdCb(CB_Brands.getSelectedIndex(), apiBrand.Brand(login)), tf_model.getText());
                    JOptionPane.showMessageDialog(this, value[1]);
                    if ("ok".equals(value[0])) {
                        dispose();
                    }
                } else if (x == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(this, "O modelo não foi adicionada!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "O modelo tem de ter entre 2 a 25 caracteres");
            }
        } catch (Exception ex) {
            Logger.getLogger(AddModel.class.getName()).log(Level.SEVERE, null, ex);
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

        tf_model = new javax.swing.JTextField();
        bt_addModel = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        CB_Brands = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Adicionar Modelo");

        bt_addModel.setText("Adicionar Modelo");

        jLabel1.setText("Adicionar Modelo");

        CB_Brands.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        jLabel2.setText("Escolha uma marca");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_model, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(bt_addModel)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(CB_Brands, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(CB_Brands, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tf_model, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bt_addModel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(236, 217));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox CB_Brands;
    private javax.swing.JButton bt_addModel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField tf_model;
    // End of variables declaration//GEN-END:variables
}
