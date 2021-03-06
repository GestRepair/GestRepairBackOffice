/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.user;

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
     * Prepara os dados para enviar
     *
     * @return
     */
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

    /**
     * Define os eventos
     *
     * @param login
     * @param api
     */
    private void Events(final String login, final APIUsers api) {
        bt_add_user.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Add_User(evt, login, api);
            }
        });
    }

    /**
     * Algoritmo para validar o NIF
     *
     * @param nif
     * @return
     */
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
     * Envia para api os dados após confirmação de do utilizador para adicionar
     * o utilizador
     *
     * @param evt
     * @param login
     * @param api
     */
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
        jLabel6 = new javax.swing.JLabel();

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

        jLabel6.setText("Adicionar Utilizador");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel9)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tflocalidade, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfnome, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfmorada, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfcodp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(tf_username)
                            .addComponent(tfemail, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfcontacto, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfnif)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(223, 223, 223)
                        .addComponent(bt_add_user, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfnome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfmorada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tfcodp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tflocalidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tfemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(tfcontacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tfnif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(bt_add_user)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(409, 451));
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
    private javax.swing.JLabel jLabel6;
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
