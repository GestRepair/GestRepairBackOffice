/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts;

import java.awt.Toolkit;
import javax.swing.JOptionPane;
import static javax.xml.bind.DatatypeConverter.parseInt;
import services.APIService;

/**
 *
 * @author Rui Barcelos
 */
public final class EditParts extends javax.swing.JFrame {

    APIParts api = new APIParts();
    APIService apiService = new APIService();
    
    /**
     * Creates new form AddRepair
     *
     * @param login
     * @param idPart
     * @param idService
     * @throws java.lang.Exception
     */
    public EditParts(String login,int idPart ,int idService) throws Exception {
        initComponents();
        Events(login);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../img/imageedit_4_8303763918.png")));
        GetData(login, idPart);
        ta_pdesc.setLineWrap(true);
    }

    private void Events(final String login) {
        bt_edit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_addPost(evt,login);
            }
        });
    }

    private void BT_addPost(java.awt.event.ActionEvent evt,String login) {
        try {
            if ((tf_name.getText().length() > 0 && tf_amount.getText().length() > 0) || tf_name.getText().length() > 0 || tf_amount.getText().length() > 0) {
                int x = JOptionPane.showConfirmDialog(this, "Tem a ceteza que quer inserir os dados?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    if ("ok".equals(api.PutPart(login, sendData(login),parseInt(l_idPart.getText())))) {
                        JOptionPane.showMessageDialog(this, "Reparação inserida com sucesso!");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Erro ao inserir os dados!");
                    }
                    dispose();
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
    private void GetData(String login, int idPart) throws Exception{
        String[] data = api.InfoParts(login,idPart);
        l_idPart.setText(data[0]);
        tf_name.setText(data[1]);
        ta_pdesc.setText(data[2]);
        tf_amount.setText(data[3]);
        tf_price.setText(data[4]);
    }

    private String[] sendData(String login) throws Exception {
        String[] data = new String[5];
        data[0] = tf_name.getText();
        data[1] = (ta_pdesc.getText().length() > 0) ? ta_pdesc.getText() : "n/d";
        data[2] = tf_amount.getText();
        data[3] = (tf_price.getText().length() > 0) ? tf_price.getText().replace(',', '.') : "0";
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
        jLabel4 = new javax.swing.JLabel();
        tf_amount = new javax.swing.JTextField();
        tf_price = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        bt_edit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        l_idPart = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Editar Peça");

        jLabel2.setText("Descrição da Peça:");

        ta_pdesc.setColumns(20);
        ta_pdesc.setRows(5);
        jScrollPane1.setViewportView(ta_pdesc);

        jLabel3.setText("Nome:");

        jLabel4.setText("Quantidade");

        tf_price.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        jLabel5.setText("Preço");

        bt_edit.setText("Editar");

        jLabel1.setText("ID:");

        l_idPart.setText("id");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tf_name)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tf_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addGap(50, 50, 50)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_price, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabel5))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bt_edit))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l_idPart)))
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_edit)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_edit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel l_idPart;
    private javax.swing.JTextArea ta_pdesc;
    private javax.swing.JTextField tf_amount;
    private javax.swing.JTextField tf_name;
    private javax.swing.JFormattedTextField tf_price;
    // End of variables declaration//GEN-END:variables
}
