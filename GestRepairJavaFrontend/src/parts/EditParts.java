/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.xml.bind.DatatypeConverter.parseInt;

/**
 *
 * @author Rui Barcelos
 */
public final class EditParts extends javax.swing.JFrame {

    /**
     * Creates new form AddRepair
     *
     * @param login
     * @param idPart
     * @param idService
     * @throws java.lang.Exception
     */
    public EditParts(String login, int idPart, int idService) throws Exception {
        initComponents();
        APIParts api = new APIParts();
        GetData(login, idPart, api);
        ta_pdesc.setLineWrap(true);
        Events(login, idPart, idService, api);
    }

    /**
     * Aqui são declarados os eventos
     *
     * @param login
     * @param idPart
     * @param idService
     * @param api
     */
    private void Events(final String login, final int idPart, final int idService, final APIParts api) {
        bt_edit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Edit(evt, login, api);
            }
        });
        bt_add_amount.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_ADDAmount(evt, login, idPart, idService);
            }
        });
        bt_edit_price.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_EditPrice(evt, login, idPart, idService);
            }
        });
    }

    /**
     * Prepara os dados para enviar
     *
     * @param evt
     * @param login
     * @param api
     */
    private void BT_Edit(java.awt.event.ActionEvent evt, String login, APIParts api) {
        try {
            if (tf_name.getText().length() > 0 && ta_pdesc.getText().length() > 0) {
                int x = JOptionPane.showConfirmDialog(this, "Tem a ceteza que quer inserir os dados?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    String[] value = api.PutPart(login, sendData(), parseInt(l_idPart.getText()));
                    JOptionPane.showMessageDialog(this, value[1]);
                    if ("ok".equals(value[0])) {
                        dispose();
                    }
                } else if (x == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(this, "A Reparação não foi introduzida no sistema!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por Favor preencha os dados todos!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro a adicionar reparação!\n Verifique se os dados estão corretos");
        }
    }

    /**
     * Abre a form para alterar o preço da peça
     *
     * @param evt
     * @param login
     * @param idPart
     * @param idService
     */
    private void BT_EditPrice(java.awt.event.ActionEvent evt, String login, int idPart, int idService) {
        try {
            new EditPrice(login, idPart, idService).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(InfoParts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Abre a form para adicionar a quantidade da peça
     *
     * @param evt
     * @param login
     * @param idPart
     * @param idService
     */
    private void BT_ADDAmount(java.awt.event.ActionEvent evt, String login, int idPart, int idService) {
        try {
            new AddAmount(login, idPart, idService).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(InfoParts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Envia insere os dados nas textboxs
     *
     * @param login
     * @param idPart
     * @param api
     * @throws Exception
     */
    private void GetData(String login, int idPart, APIParts api) throws Exception {
        String[] data = api.InfoParts(login, idPart);
        l_idPart.setText(data[0]);
        tf_name.setText(data[1]);
        ta_pdesc.setText(data[2]);
    }

    /**
     * Prepara os dados para enviar para a API
     *
     * @return
     * @throws Exception
     */
    private String[] sendData() throws Exception {
        String[] data = new String[3];
        data[0] = tf_name.getText();
        data[1] = (ta_pdesc.getText().length() > 0) ? ta_pdesc.getText() : "n/d";
        return data;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_pdesc = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        tf_name = new javax.swing.JTextField();
        bt_edit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        l_idPart = new javax.swing.JLabel();
        bt_add_amount = new javax.swing.JButton();
        bt_edit_price = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Editar Peça");
        setResizable(false);

        jLabel2.setText("Descrição da Peça:");

        ta_pdesc.setColumns(20);
        ta_pdesc.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        ta_pdesc.setRows(5);
        jScrollPane1.setViewportView(ta_pdesc);

        jLabel3.setText("Nome:");

        bt_edit.setText("Editar");

        jLabel1.setText("Editar Peça n.º:");

        l_idPart.setText("id");

        bt_add_amount.setText("Adicionar Quantidade");

        bt_edit_price.setText("Alterar Preço");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(bt_add_amount)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_edit_price)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                        .addComponent(bt_edit))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l_idPart)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(tf_name))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(l_idPart))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_edit)
                    .addComponent(bt_add_amount)
                    .addComponent(bt_edit_price))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(416, 381));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_add_amount;
    private javax.swing.JButton bt_edit;
    private javax.swing.JButton bt_edit_price;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel l_idPart;
    private javax.swing.JTextArea ta_pdesc;
    private javax.swing.JTextField tf_name;
    // End of variables declaration//GEN-END:variables
}
