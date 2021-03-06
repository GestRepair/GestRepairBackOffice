/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repairs.repairs;

import javax.swing.JOptionPane;
import static javax.xml.bind.DatatypeConverter.parseInt;
import repairs.state.APIState;

/**
 *
 * @author Rui Barcelos
 */
public class EditRepair extends javax.swing.JFrame {

    /**
     * Creates new form EditRepair
     *
     * @param login
     * @param idRepair
     * @throws java.lang.Exception
     */
    public EditRepair(String login, int idRepair) throws Exception {
        APIRepair api = new APIRepair();
        APIState apiState = new APIState();
        initComponents();
        ShowData(login, idRepair, api);
        insertCb(apiState.ShowStateComplete(login));
        ta_description.setLineWrap(true);
        ta_resolution.setLineWrap(true);
        Events(login, idRepair, api, apiState);
    }

    /**
     * Insere os estados da reparação na dropdown
     *
     * @param list
     * @throws Exception
     */
    private void insertCb(String[][] list) throws Exception {
        cb_state.removeAllItems();
        for (String[] list1 : list) {
            cb_state.addItem(list1[1]);
        }
    }

    /**
     * Mostra os dados na interface
     *
     * @param login
     * @param id
     * @param api
     * @throws Exception
     */
    private void ShowData(String login, int id, APIRepair api) throws Exception {
        String info[] = api.GetInfoRepair(login, id);
        l_idRepair.setText(info[0]);
        l_vehicle.setText(info[1]);
        ta_description.setText(info[2]);
        tf_price.setText(info[3]);
        cb_state.setSelectedItem(info[4]);
        l_dStart.setText(info[5]);
        if (info[6] != null) {
            l_dFinish.setText(info[6]);
        } else {
            l_dFinish.setVisible(false);
            l_dFin.setVisible(false);
        }
        ta_resolution.setText(info[7]);
    }

    /**
     * Prepara os dados para enviar
     *
     * @param login
     * @param apiState
     * @return
     * @throws Exception
     */
    private String[] SendData(String login, APIState apiState) throws Exception {
        String[] data = new String[4];
        data[0] = (ta_description.getText().length() > 0) ? ta_description.getText() : "n/d";
        data[1] = (tf_price.getText().length() > 0) ? tf_price.getText().replace(',', '.') : "0";
        data[2] = cbVal(apiState.ShowState(login), cb_state.getSelectedIndex()) + "";
        data[3] = (ta_resolution.getText().length() > 0) ? ta_resolution.getText() : "n/d";
        return data;
    }

    /**
     * Vai buscar o id do valor da dropdown
     *
     * @param list
     * @param val
     * @return
     */
    private int cbVal(String[][] list, int val) {
        return parseInt(list[val][0]);
    }

    /**
     * Determina os eventos
     *
     * @param login
     * @param idRepair
     * @param api
     * @param apiState
     */
    private void Events(final String login, final int idRepair, final APIRepair api, final APIState apiState) {
        bt_edit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_EDIT(evt, login, idRepair, api, apiState);
            }
        });
    }

    /**
     * Verifica se pode enviar os dados e pede a confirmação dos utilizadores e
     * depois da confirmação envia
     *
     * @param evt
     * @param login
     * @param idRepair
     * @param api
     * @param apiState
     */
    private void BT_EDIT(java.awt.event.ActionEvent evt, String login, int idRepair, APIRepair api, APIState apiState) {
        try {
            if (ta_description.getText().length() > 0) {
                int x = JOptionPane.showConfirmDialog(this, "Tem a ceteza que quer atualizar os dados?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    String[] value = api.UpdateRepair(login, idRepair, SendData(login, apiState));
                    JOptionPane.showMessageDialog(this, value[1]);
                    if ("ok".equals(value[0])) {
                        dispose();
                    }
                } else if (x == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(this, "A Reparação não foi atualizada!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Verifique o se o campo descrição está preenchido!");
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
        l_idRe = new javax.swing.JLabel();
        l_idRepair = new javax.swing.JLabel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Editar Reparação");
        setResizable(false);

        l_dFinish.setText("conclusao");

        jLabel3.setText("Início");

        l_dFin.setText("Conclusão:");

        jLabel1.setText("Viatura:");

        l_idRe.setText("ID:");

        l_idRepair.setText("id");

        l_pric.setText("Preço:");

        jLabel2.setText("Descrição do Problema:");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel4)
                                .addGap(22, 22, 22)
                                .addComponent(cb_state, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(l_idRe)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(l_idRepair))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(l_vehicle))))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(l_pric)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tf_price, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(l_cif))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l_res)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l_dStart))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(l_dFin)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l_dFinish))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(bt_edit)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(l_res, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l_pric)
                    .addComponent(jLabel4)
                    .addComponent(cb_state, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l_cif))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_edit)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(774, 276));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_edit;
    private javax.swing.JComboBox cb_state;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel l_cif;
    private javax.swing.JLabel l_dFin;
    private javax.swing.JLabel l_dFinish;
    private javax.swing.JLabel l_dStart;
    private javax.swing.JLabel l_idRe;
    private javax.swing.JLabel l_idRepair;
    private javax.swing.JLabel l_pric;
    private javax.swing.JLabel l_res;
    private javax.swing.JLabel l_vehicle;
    private javax.swing.JTextArea ta_description;
    private javax.swing.JTextArea ta_resolution;
    private javax.swing.JFormattedTextField tf_price;
    // End of variables declaration//GEN-END:variables
}
