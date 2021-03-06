/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles.fuel;

import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Rui Barcelos
 */
public class EditFuel extends javax.swing.JFrame {

    /**
     * Creates new form EditVehicle
     *
     * @param login
     * @param id
     * @throws java.lang.Exception
     */
    public EditFuel(String login, int id) throws Exception {
        APIFuel api = new APIFuel();
        initComponents();
        InfoFuel(login, id, api);
        Events(login, id, api);
    }

    /**
     * Mostra a informação dos combustíveis
     *
     * @param login
     * @param id
     * @param api
     * @throws Exception
     */
    private void InfoFuel(String login, int id, APIFuel api) throws Exception {
        String fuel[] = api.InfoFuel(login, id);
        l_id.setText(fuel[0]);
        tf_fuel.setText(fuel[1]);
    }

    /**
     * Eventos
     *
     * @param login
     * @param id
     * @param api
     * @throws Exception
     */
    private void Events(final String login, final int id, final APIFuel api) throws Exception {
        bt_edit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_EDIT(evt, login, id, api);
            }
        });
    }

    /**
     * Edita os dados de aós verificação dos dados e confirmação
     *
     * @param evt
     * @param login
     * @param id
     * @param api
     */
    private void BT_EDIT(java.awt.event.ActionEvent evt, String login, int id, APIFuel api) {
        try {
            int tfuel = tf_fuel.getText().length();
            if (tfuel > 1 && tfuel < 50) {
                int x = JOptionPane.showConfirmDialog(this, "Tem a certeza que quer alterar o combustivel?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    String[] value = api.PutFuel(login, id, tf_fuel.getText());
                    JOptionPane.showMessageDialog(this, value[1]);
                    if ("ok".equals(value[0])) {
                        new Table_Fuel(login).setVisible(true);
                        dispose();
                    }
                } else if (x == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(this, "O combustivel não foi alterada");
                }
            } else {
                JOptionPane.showMessageDialog(this, "O combustivel tem de ter entre 2 a 50 caracteres");
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

        jLabel2 = new javax.swing.JLabel();
        l_id = new javax.swing.JLabel();
        tf_fuel = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        bt_edit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Editar Combustível");

        jLabel2.setText("Editar combustivel n.º:");

        l_id.setText("ID");

        jLabel10.setText("Combustível");

        bt_edit.setText("Editar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_edit)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_fuel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_id)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(l_id))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(tf_fuel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_edit)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(298, 135));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_edit;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel l_id;
    private javax.swing.JTextField tf_fuel;
    // End of variables declaration//GEN-END:variables
}
