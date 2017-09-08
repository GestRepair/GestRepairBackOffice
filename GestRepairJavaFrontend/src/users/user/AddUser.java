/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.user;

import java.awt.Toolkit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import static javax.xml.bind.DatatypeConverter.parseInt;

/**
 *
 * @author Convite
 */
public class AddUser extends javax.swing.JFrame {

    /**
     * Creates new form AddUserForm
     *
     * @param login
     */
    public AddUser(String login) {
        APIUsers api = new APIUsers();
        initComponents();
        Events(login, api);
    }

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

    private String[] Data() {
        String[] data = new String[8];
        data[0] = tfnome.getText();
        data[1] = tfmorada.getText();
        data[2] = tfcodp.getText();
        data[3] = tflocalidade.getText();
        data[4] = tfemail.getText();
        data[5] = tfnif.getText();
        data[6] = tfcontacto.getText();
        data[7] = tf_username.getText();
        return data;
    }

    private void Events(final String login, final APIUsers api) {
        bt_add_user.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Add_User(evt, login, api);
            }
        });
    }

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

    private void BT_Add_User(java.awt.event.ActionEvent evt, String login, APIUsers api) {
        try {
            if (tf_username.getText().length() > 5 && tfnome.getText().length() > 5 && tfmorada.getText().length() > 5 && tflocalidade.getText().length() > 3) {
                //https://stackoverflow.com/questions/8204680/java-regex-email
                if (validateEmail(tfemail.getText()) == true && validateName(tfnome.getText()) == true && validateName(tflocalidade.getText()) == true && validateNumber(tfcontacto.getText()) == true || validateNIF(tfnif.getText()) == true) {
                    int x = JOptionPane.showConfirmDialog(this, "Tem a certeza que quer adicionar um novo utilizador?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (x == JOptionPane.YES_OPTION) {
                        String[] value = api.PostUser(login, Data());
                        JOptionPane.showMessageDialog(this, value[1]);
                        if ("ok".equals(value[0])) {
                            dispose();
                        }
                    } else if (x == JOptionPane.NO_OPTION) {
                        JOptionPane.showMessageDialog(this, "O utilizador não foi adicionado");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Verifique se inseriu bem os dados");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Preencha os campos por favor");
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

        tflocalidade = new javax.swing.JTextField();
        tfcontacto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfnif = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tfnome = new javax.swing.JTextField();
        tfmorada = new javax.swing.JTextField();
        tfemail = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        bt_add_user = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        tf_username = new javax.swing.JTextField();
        tfcodp = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Adicionar Utilizador");
        setResizable(false);

        jLabel5.setText("Localidade:");

        jLabel9.setText("NIF:");

        jLabel4.setText("Código Postal:");

        jLabel10.setText("Contacto");

        jLabel2.setText("Nome:");

        jLabel3.setText("Morada:");

        jLabel8.setText("E-Mail:");

        bt_add_user.setText("Adicionar Utilizador");

        jLabel1.setText("Username:");

        try {
            tfcodp.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bt_add_user)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tfcodp, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tflocalidade, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
                            .addComponent(tfnome)
                            .addComponent(tfmorada))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGap(22, 22, 22)
                                    .addComponent(jLabel8))
                                .addGroup(layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel10)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_username, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tfcontacto, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9)
                                .addGap(17, 17, 17)
                                .addComponent(tfnif, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfemail, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfnome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(tf_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(tfmorada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(tflocalidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(tfcodp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(tfemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(tfcontacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfnif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))))
                .addGap(18, 18, 18)
                .addComponent(bt_add_user)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(749, 184));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_add_user;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField tf_username;
    private javax.swing.JFormattedTextField tfcodp;
    private javax.swing.JTextField tfcontacto;
    private javax.swing.JTextField tfemail;
    private javax.swing.JTextField tflocalidade;
    private javax.swing.JTextField tfmorada;
    private javax.swing.JTextField tfnif;
    private javax.swing.JTextField tfnome;
    // End of variables declaration//GEN-END:variables
}
