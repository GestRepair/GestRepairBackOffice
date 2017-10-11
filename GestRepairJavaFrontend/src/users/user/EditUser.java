/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.user;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import static javax.xml.bind.DatatypeConverter.parseInt;

/**
 *
 * @author Rui Barcelos
 */
public final class EditUser extends javax.swing.JFrame {

    /**
     * Creates new form editUser
     *
     * @param login
     * @param id
     * @throws java.lang.Exception
     */
    public EditUser(String login, int id) throws Exception {
        APIUsers api = new APIUsers();
        initComponents();
        GetInfo(login, id, api);
        Events(login, id, api);
    }

    /**
     * Mostra na form a utilização existente
     *
     * @param login
     * @param id
     * @param api
     * @throws Exception
     */
    public void GetInfo(String login, int id, APIUsers api) throws Exception {
        String emp[] = api.GetInfoUser(login, id);
        l_id.setText(emp[0]);
        tf_name.setText(emp[1]);
        tf_street.setText(emp[2]);
        tf_zipcode.setText(emp[3]);
        tf_city.setText(emp[4]);
        tf_email.setText(emp[5]);
        tf_contact.setText(emp[6]);
        tf_nif.setText(emp[7]);
        l_username.setText(emp[8]);
        l_state.setText(("1".equals(emp[9])) ? "Ativo" : "Inativo");
        l_type.setText(("1".equals(emp[10])) ? "Funcionário" : "Cliente");
    }

    /**
     * Prepara os dados para enviar
     *
     * @return
     */
    private String[] data() {
        String data[] = new String[7];
        data[0] = tf_name.getText();
        data[1] = tf_street.getText();
        data[2] = tf_zipcode.getText();
        data[3] = tf_city.getText();
        data[4] = tf_email.getText();
        data[5] = tf_nif.getText();
        data[6] = tf_contact.getText();
        return data;
    }
    /**
     * Validações
     */
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern VALID_NAME_REGEX = Pattern.compile("[A-zÀ-ÖØ-öø-ž ]+", Pattern.CASE_INSENSITIVE);
    private static final Pattern VALID_NUMBER_REGEX = Pattern.compile("[0-9]", Pattern.CASE_INSENSITIVE);

    private static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    private static boolean validateName(String nameStr) {
        Matcher matcher = VALID_NAME_REGEX.matcher(nameStr);
        return matcher.find();
    }

    private static boolean validateNumber(String numberStr) {
        Matcher matcher = VALID_NUMBER_REGEX.matcher(numberStr);
        return matcher.find();
    }

    /**
     * Eventos
     *
     * @param login
     * @param id
     * @param api
     */
    private void Events(final String login, final int id, final APIUsers api) {
        bt_edit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    BT_EDIT(evt, login, id, api);
                } catch (Exception ex) {
                    Logger.getLogger(EditPassword.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    /**
     * Algoritmo para validar o NIF
     *
     * @param nif
     * @return
     */
    // validate the nif number
    private boolean validateNIF(String nif) {
        int zerm = 9 * nif.charAt(0);
        int firm = 8 * nif.charAt(1);
        int secm = 7 * nif.charAt(2);
        int trem = 6 * nif.charAt(3);
        int form = 5 * nif.charAt(4);
        int fivm = 4 * nif.charAt(5);
        int sixm = 3 * nif.charAt(6);
        int sevm = 2 * nif.charAt(7);
        int sum = zerm + firm + secm + trem + form + fivm + sixm + sevm;
        int resNif = sum % 11;
        resNif = (resNif == 0 || resNif == 1) ? 0 : (11 - resNif);
        return (parseInt(nif.charAt(8) + "") == resNif && nif.length() == 9);

    }

    /**
     * Edita os dados após a validação dos dados e confirmação do utilizador
     *
     * @param evt
     * @param login
     * @param id
     * @param api
     * @throws Exception
     */
    private void BT_EDIT(java.awt.event.ActionEvent evt, String login, int id, APIUsers api) throws Exception {
        if (tf_name.getText().length() > 0 && tf_street.getText().length() > 0 && tf_city.getText().length() > 0) {
            if (validateEmail(tf_email.getText()) == true && validateName(tf_name.getText()) == true && validateName(tf_city.getText()) == true && validateNumber(tf_contact.getText()) == true && validateNIF(tf_nif.getText()) == true) {
                int x = JOptionPane.showConfirmDialog(this, "Quer modificar este utilizador?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    try {
                        String[] value = api.PutUser(login, id, data());
                        JOptionPane.showMessageDialog(this, value[1]);
                        if ("ok".equals(value[0])) {
                            dispose();
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(EditUser.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (x == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(this, "O utilizador não foi modificada");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Verifique se inseriu bem os dados");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Preencha os campos por favor");
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

        tf_name = new javax.swing.JTextField();
        tf_street = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tf_city = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tf_zipcode = new javax.swing.JFormattedTextField();
        tf_contact = new javax.swing.JTextField();
        tf_nif = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tf_email = new javax.swing.JTextField();
        l_id = new javax.swing.JLabel();
        l_state = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        l_username = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        l_type = new javax.swing.JLabel();
        bt_edit = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar Utilizador");
        setResizable(false);

        jLabel2.setText("Morada");

        jLabel3.setText("Código Postal");

        jLabel4.setText("Telemóvel");

        jLabel5.setText("NIF");

        try {
            tf_zipcode.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel6.setText("Email");

        l_id.setText("Utilizador");

        l_state.setText("estado");

        jLabel9.setText("Estado:");

        jLabel10.setText("Username");

        l_username.setText("username");

        jLabel8.setText("Tipo:");

        l_type.setText("tipo");

        bt_edit.setText("Editar");

        jLabel11.setText("Editar Utilizador n.º:");

        jLabel7.setText("Nome");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(33, 33, 33))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(45, 45, 45)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(l_type, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(12, 12, 12))
                            .addComponent(l_state, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(161, 161, 161)
                        .addComponent(bt_edit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(l_id, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_username, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                        .addGap(103, 103, 103))
                    .addComponent(tf_name)
                    .addComponent(tf_street)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tf_zipcode, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tf_city))
                    .addComponent(tf_nif)
                    .addComponent(tf_email)
                    .addComponent(tf_contact))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l_id, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(l_username, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_name)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_street)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tf_zipcode)
                    .addComponent(tf_city))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_contact)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_nif)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tf_email))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(l_state, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l_type, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_edit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );

        setSize(new java.awt.Dimension(511, 332));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_edit;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel l_id;
    private javax.swing.JLabel l_state;
    private javax.swing.JLabel l_type;
    private javax.swing.JLabel l_username;
    private javax.swing.JTextField tf_city;
    private javax.swing.JTextField tf_contact;
    private javax.swing.JTextField tf_email;
    private javax.swing.JTextField tf_name;
    private javax.swing.JTextField tf_nif;
    private javax.swing.JTextField tf_street;
    private javax.swing.JFormattedTextField tf_zipcode;
    // End of variables declaration//GEN-END:variables
}
