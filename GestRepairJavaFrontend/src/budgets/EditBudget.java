/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgets;

import javax.swing.JOptionPane;
import static javax.xml.bind.DatatypeConverter.parseInt;

/**
 *
 * @author Rui Barcelos
 */
public class EditBudget extends javax.swing.JFrame {

    /**
     * Creates new form EditRepair
     *
     * @param login
     * @param idBudget
     * @throws java.lang.Exception
     */
    public EditBudget(String login, int idBudget) throws Exception {
        APIBudgets api = new APIBudgets();
        //setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../img/imageedit_4_8303763918.png")));
        initComponents();
        ShowData(login, idBudget, api);
        Events(login, idBudget, api);
        insertCb(api.ListStateComplete(login));
        ta_description.setLineWrap(true);
        ta_resolution.setLineWrap(true);

    }

    private void insertCb(String[][] list) throws Exception {
        cb_state.removeAllItems();
        for (String[] list1 : list) {
            cb_state.addItem(list1[1]);
        }
    }

    private void Events(final String login, final int idBudget, final APIBudgets api) {
        bt_edit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Edit(evt, login, idBudget, api);
            }
        });
    }

    private void ShowData(String login, int id, APIBudgets api) throws Exception {
        String info[] = api.GetInfoBudget(login, id);
        l_idBudget.setText(info[0]);
        l_vehicle.setText(info[1]);
        ta_description.setText(info[2]);
        cb_state.setSelectedItem(info[3]);
        tf_price.setText(info[4]);
        l_dStart.setText(info[5]);
        tf_time.setText(info[6]);
        if (info[6] != null) {
            l_dFinish.setText(info[7]);
        } else {
            l_dFinish.setVisible(false);
            l_dFin.setVisible(false);
        }
        ta_resolution.setText(info[8]);
    }

    private String[] SendData(String login, APIBudgets api) throws Exception {
        String[] data = new String[5];
        data[0] = (ta_description.getText().length() > 0) ? ta_description.getText() : "n/d";
        data[1] = (tf_price.getText().length() > 0) ? tf_price.getText().replace(',', '.') : "0";
        data[2] = cbVal(api.ListState(login), cb_state.getSelectedIndex()) + "";
        data[3] = (tf_time.getText().length() > 0) ? tf_time.getText() : "n/d";
        data[4] = (ta_resolution.getText().length() > 0) ? ta_resolution.getText() : "n/d";
        return data;
    }

    private int cbVal(String[][] list, int val) {
        return parseInt(list[val][0]);
    }

    private void BT_Edit(java.awt.event.ActionEvent evt, String login, int idBudget, APIBudgets api) {
        try {
            if (ta_description.getText().length() > 0) {
                int x = JOptionPane.showConfirmDialog(this, "Tem a ceteza que quer atualizar os dados?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    String value[] = api.UpdateBudget(login, idBudget, SendData(login, api));
                    JOptionPane.showMessageDialog(this, value[1]);
                    if ("ok".equals(value[0])) {
                        dispose();
                    }
                } else if (x == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(this, "A Reparação não foi atualizada!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Verifique o campo descrição!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro a atualizar reparação!\n Verifique se os dados estão corretos");
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

        l_dFinish = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        l_dFin = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        l_idBudget = new javax.swing.JLabel();
        l_pric = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        l_vehicle = new javax.swing.JLabel();
        l_res = new javax.swing.JLabel();
        l_dStart = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cb_state = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_resolution = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        ta_description = new javax.swing.JTextArea();
        tf_price = new javax.swing.JFormattedTextField();
        l_cif = new javax.swing.JLabel();
        bt_edit = new javax.swing.JButton();
        tf_time = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Editar Orçamento");
        setResizable(false);

        l_dFinish.setText("conclusao");

        jLabel3.setText("Início");

        l_dFin.setText("Conclusão:");

        jLabel1.setText("Viatura:");

        l_idBudget.setText("id");

        l_pric.setText("Preço:");

        jLabel2.setText("Descrição do que o cliente pretende:");

        l_vehicle.setText("Viatura");

        l_res.setText("Resolução:");

        l_dStart.setText("inicio");

        jLabel4.setText("Estado:");

        cb_state.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        ta_resolution.setColumns(20);
        ta_resolution.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        ta_resolution.setRows(5);
        jScrollPane1.setViewportView(ta_resolution);

        ta_description.setColumns(20);
        ta_description.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        ta_description.setRows(5);
        jScrollPane2.setViewportView(ta_description);

        tf_price.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        l_cif.setText("€");

        bt_edit.setText("Editar");

        tf_time.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_timeActionPerformed(evt);
            }
        });

        jLabel5.setText("Tempo de Reparação:");

        jLabel6.setText("Dias");

        jLabel7.setText("Alterar orçamento n.º:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(l_dStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(l_vehicle))
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(127, 127, 127)
                                .addComponent(l_idBudget, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_state, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(l_res)
                                    .addComponent(jLabel3))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(l_dFin)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(l_dFinish, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_time, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addGap(211, 211, 211)
                        .addComponent(l_pric)
                        .addGap(18, 18, 18)
                        .addComponent(tf_price, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(l_cif)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_edit)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(l_idBudget)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(l_dStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_state, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(l_dFin)
                            .addComponent(l_dFinish, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(l_vehicle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(l_res, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tf_time)
                    .addComponent(jLabel6)
                    .addComponent(l_pric, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tf_price)
                    .addComponent(l_cif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_edit))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(970, 357));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tf_timeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_timeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_timeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_edit;
    private javax.swing.JComboBox cb_state;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel l_cif;
    private javax.swing.JLabel l_dFin;
    private javax.swing.JLabel l_dFinish;
    private javax.swing.JLabel l_dStart;
    private javax.swing.JLabel l_idBudget;
    private javax.swing.JLabel l_pric;
    private javax.swing.JLabel l_res;
    private javax.swing.JLabel l_vehicle;
    private javax.swing.JTextArea ta_description;
    private javax.swing.JTextArea ta_resolution;
    private javax.swing.JFormattedTextField tf_price;
    private javax.swing.JTextField tf_time;
    // End of variables declaration//GEN-END:variables
}
