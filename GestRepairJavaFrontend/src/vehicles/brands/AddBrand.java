/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles.brands;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import vehicles.fuel.Table_Fuel;

/**
 *
 * @author Convite
 */
public class AddBrand extends javax.swing.JFrame {

    public String login;
    

    /**
     * Creates new form addBrand
     *
     * @param login
     */
    public AddBrand(String login) {
        APIBrand api = new APIBrand();
        initComponents();
        Events(login,api);
    }
    private void Events(final String login, final APIBrand api){
        bt_addBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_ADD(evt,login,api);
            }
        });
    }
    private void BT_ADD(java.awt.event.ActionEvent evt, String login, APIBrand api) {                                            
        try {
            int x = JOptionPane.showConfirmDialog(this, "Tem a certeza que quer adicionar a marca" + tf_brand.getText() + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                String[] value = api.PostBrand(login, tf_brand.getText());
                JOptionPane.showMessageDialog(this, value[1]);
                if ("ok".equals(value[0])) {
                    new Table_Fuel(login).setVisible(true);
                    dispose();
                }
            } else if (x == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(this, "A marca não foi adicionada!");
            }
        } catch (Exception ex) {
            Logger.getLogger(AddBrand.class.getName()).log(Level.SEVERE, null, ex);
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

        tf_brand = new javax.swing.JTextField();
        bt_addBrand = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Adicionar Marca");

        bt_addBrand.setText("Adicionar Marca");

        jLabel2.setText("Adicionar Marca automóvel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_brand, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_addBrand)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(tf_brand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bt_addBrand)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(236, 165));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_addBrand;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField tf_brand;
    // End of variables declaration//GEN-END:variables
}
