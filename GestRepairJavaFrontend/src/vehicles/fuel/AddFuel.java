/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles.fuel;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Rui Barcelos
 */
public final class AddFuel extends javax.swing.JFrame {

    /**
     * Creates new form AddFuel
     *
     * @param login
     * @throws org.json.simple.parser.ParseException
     */
    public AddFuel(String login) throws ParseException, Exception {
        APIFuel api = new APIFuel();
        initComponents();
        Events(login,api);
    }

    private void Events(final String login, final APIFuel api) {
        bt_add.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_ADD(evt, login, api);
            }
        });
    }

    private void BT_ADD(java.awt.event.ActionEvent evt, String login, APIFuel api) {
        try {
            int tfuel = tf_fuel.getText().length();
            if (tfuel < 0 || tfuel > 50) {
                int x = JOptionPane.showConfirmDialog(this, "Tem a certeza que quer alterar a viatura?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    String[] value = api.PostFuel(login, tf_fuel.getText());
                    JOptionPane.showMessageDialog(this, value[1]);
                    if ("ok".equals(value[0])) {
                        new Table_Fuel(login).setVisible(true);
                        dispose();
                    }
                } else if (x == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(this, "A Viatura não foi alterada");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Verifique se o número de caracteres é entre 1 a 50");
            }
        } catch (Exception ex) {
            Logger.getLogger(EditFuel.class.getName()).log(Level.SEVERE, null, ex);
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

        tf_fuel = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        bt_add = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Adicionar Combustível");

        jLabel6.setText("Combustivel");

        bt_add.setText("Adicionar");

        jLabel1.setText("Adicionar Combustivel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(tf_fuel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bt_add))
                    .addComponent(jLabel6)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_fuel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_add)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(236, 159));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_add;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField tf_fuel;
    // End of variables declaration//GEN-END:variables
}
