/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles.vehicles;

import javax.swing.JOptionPane;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Rui Barcelos
 */
public class VerifyVehicle extends javax.swing.JFrame {

    /**
     * Creates new form verifyVehicle
     *
     * @param login
     * @param id
     * @param user
     * @param idService
     * @param idEmployer
     */
    public VerifyVehicle(final String login, int id, String user, int idService, int idEmployer) {
        APIVehicles api = new APIVehicles();
        initComponents();
        Events(login, id, idService, idEmployer, user, api);
    }

    /**
     * Eventos
     *
     * @param login
     * @param id
     * @param idService
     * @param idEmployer
     * @param user
     * @param api
     */
    private void Events(final String login, final int id, final int idService, final int idEmployer, final String user, final APIVehicles api) {
        bt_verify.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_VERIFY(evt, login, id, idService, idEmployer, user, api);
            }
        });
    }

    /**
     * Prepara os dados para enviar
     *
     * @return
     */
    private String Data() {
        return tf_registration.getText();
    }

    /**
     * Confirma se o utilizador quer que verificar a viatura existe
     *
     * @param evt
     * @param login
     * @param id
     * @param idService
     * @param idEmployer
     * @param user
     * @param api
     */
    private void BT_VERIFY(java.awt.event.ActionEvent evt, String login, int id, int idService, int idEmployer, String user, APIVehicles api) {
        try {
            if (tf_registration.getText().length() > 5) {
                int x = JOptionPane.showConfirmDialog(this, "Tem a certeza que quer verificar se viatura " + Data() + " faz parte do sistema?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    String[] value = api.POSTVerifyVehicle(login, Data());
                    if ("ok".equals(value[0])) {
                        JSONObject res = (JSONObject) new JSONParser().parse(value[2]);
                        long i = (long) res.get("bool");
                        if (i == 0) {
                            new AddVehicle(login, id, user, tf_registration.getText()).setVisible(true);
                            dispose();
                        } else {
                            String[] value1 = api.POSTVerifyOwnerVehicle(login, Data());
                            JSONObject res2 = (JSONObject) new JSONParser().parse(value1[2]);
                            long e = (long) res2.get("bool");
                            if (e == 1) {
                                String[] value2 = api.POSTVerifyUserVehicle(login, Data(), id);
                                JSONObject res3 = (JSONObject) new JSONParser().parse(value2[2]);
                                long p = (long) res3.get("bool");
                                if (p == 0) {;
                                    String[] value3 = api.POSTVerifyADDVehicle(login, Data(), id);
                                    if ("ok".equals(value3[0])) {
                                        JOptionPane.showMessageDialog(this, value3[1]);
                                        dispose();
                                    }
                                } else {
                                    String[] value4 = api.PUTVerifyADDVehicle(login, Data(), id);
                                    if ("ok".equals(value4[0])) {
                                        JOptionPane.showMessageDialog(this, value4[1]);
                                        dispose();
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "Por favor desative a viatura!");
                                new Table_Vehicles(login, idService, idEmployer).setVisible(true);
                                dispose();
                            }
                        }
                    }
                } else if (x == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(this, "O utilizador não foi adicionado");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Preencha os dados por favor");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro a adicionar utilizador");
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

        tf_registration = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        bt_verify = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Verificar Viatura");
        setResizable(false);

        jLabel1.setText("Matricula:");

        bt_verify.setText("Verificar");

        jLabel2.setText("Verifique se a viatura já existe no sistema");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(bt_verify)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_registration, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_registration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_verify)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(376, 130));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_verify;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField tf_registration;
    // End of variables declaration//GEN-END:variables
}
