/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles.vehicles;

import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import static javax.xml.bind.DatatypeConverter.parseInt;
import org.json.simple.parser.ParseException;
import vehicles.brands.APIBrand;
import vehicles.fuel.APIFuel;
import vehicles.models.APIModel;

/**
 *
 * @author Rui Barcelos
 */
public final class AddVehicle extends javax.swing.JFrame {

    /**
     * Creates new form AddVehicle
     *
     * @param login
     * @param id
     * @param user
     * @param vehicle
     * @throws org.json.simple.parser.ParseException
     */
    public AddVehicle(String login, int id, String user, String vehicle) throws ParseException, Exception {
        APIVehicles api = new APIVehicles();
        APIBrand apiBrand = new APIBrand();
        APIModel apiModel = new APIModel();
        APIFuel apiFuel = new APIFuel();
        initComponents();
        l_id.setText(id + "");
        l_username.setText(user);
        l_registration.setText(vehicle);
        showBrand(apiBrand.Brand(login));
        showModel(apiModel.Model(login, newIdCb(cb_brand.getSelectedIndex(), apiBrand.Brand(login))));
        showFuel(apiFuel.Fuel(login));
        Events(login, id, api, apiModel, apiBrand, apiFuel);
    }

    /**
     * Insere os dados na dropdown Marcas
     *
     * @param list
     */
    private void showBrand(String[][] list) {
        cb_brand.removeAllItems();
        for (int i = 0; i < list.length; i++) {
            cb_brand.addItem(list[i][1]);
        }
    }

    /**
     * Insere os dados na dropdown modelos
     *
     * @param list
     */
    private void showModel(String[][] list) {
        cb_model.removeAllItems();
        for (int i = 0; i < list.length; i++) {
            cb_model.addItem(list[i][1]);
        }
    }

    /**
     * Insere os dados na dropdown combustiveis
     *
     * @param list
     */
    private void showFuel(String[][] list) {
        cb_fuel.removeAllItems();
        for (int i = 0; i < list.length; i++) {
            cb_fuel.addItem(list[i][1]);
        }
    }

    /**
     * Consulta o id das Dropdowns
     *
     * @param val
     * @param list
     * @return
     */
    private int newIdCb(int val, String[][] list) {
        if (val < 0) {
            return parseInt(list[0][0]);
        } else {
            return parseInt(list[val][0]);
        }
    }

    /**
     * Prepara os dados para enviar
     *
     * @param login
     * @param apiModel
     * @param apiBrand
     * @param apiFuel
     * @return
     * @throws Exception
     */
    private String[] data(String login, APIModel apiModel, APIBrand apiBrand, APIFuel apiFuel) throws Exception {
        String data[] = new String[9];
        data[0] = newIdCb(cb_model.getSelectedIndex(), apiModel.Model(login, newIdCb(cb_brand.getSelectedIndex(), apiBrand.Brand(login)))) + "";
        data[1] = l_registration.getText();
        data[2] = newIdCb(cb_fuel.getSelectedIndex(), apiFuel.Fuel(login)) + "";
        data[3] = tf_horsepower.getText();
        data[4] = tf_displacement.getText();
        data[5] = tf_kilometers.getText();
        data[6] = tf_fronttire.getText();
        data[7] = tf_reartire.getText();
        data[8] = tf_date.getText();
        return data;
    }

    private static final Pattern VALID_NUMBER_REGEX = Pattern.compile("[0-9]", Pattern.CASE_INSENSITIVE);

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
     * @param apiModel
     * @param apiBrand
     * @param apiFuel
     */
    private void Events(final String login, final int id, final APIVehicles api, final APIModel apiModel, final APIBrand apiBrand, final APIFuel apiFuel) {
        bt_add.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_ADD(evt, login, id, api, apiModel, apiBrand, apiFuel);
            }
        });
        cb_brand.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_BRAND(evt, login, apiModel, apiBrand);
            }
        });

    }

    /**
     * Adiciona os dados na vitura depois da validação e confirmação do
     * utilizador
     *
     * @param evt
     * @param login
     * @param id
     * @param api
     * @param apiModel
     * @param apiBrand
     * @param apiFuel
     */
    private void BT_ADD(java.awt.event.ActionEvent evt, String login, int id, APIVehicles api, APIModel apiModel, APIBrand apiBrand, APIFuel apiFuel) {
        if (tf_fronttire.getText().length() > 8 && tf_reartire.getText().length() > 8 && validateNumber(tf_displacement.getText()) && validateNumber(tf_horsepower.getText()) == true && validateNumber(tf_kilometers.getText()) == true && tf_date.getText().length() > 8) {
            try {
                int x = JOptionPane.showConfirmDialog(this, "Tem a certeza que quer adicionar uma nova viatura?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    String[] value = api.POSTAddVehicle(login, id, data(login, apiModel, apiBrand, apiFuel));
                    JOptionPane.showMessageDialog(this, value[1]);
                    if ("ok".equals(value[0])) {
                        dispose();
                    }
                } else if (x == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(this, "A Viatura não foi adicionada!");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro Interno");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Verifique se os dados estão corretos");
        }
    }

    /**
     * Altera o valor das tabelas após a mudança do valor da dropdown
     *
     * @param evt
     * @param login
     * @param apiModel
     * @param apiBrand
     */
    private void CB_BRAND(java.awt.event.ActionEvent evt, String login, APIModel apiModel, APIBrand apiBrand) {
        try {
            showModel(apiModel.Model(login, newIdCb(cb_brand.getSelectedIndex(), apiBrand.Brand(login))));
        } catch (Exception ex) {
            Logger.getLogger(AddVehicle.class.getName()).log(Level.SEVERE, null, ex);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cb_brand = new javax.swing.JComboBox();
        cb_model = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cb_fuel = new javax.swing.JComboBox();
        tf_date = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        tf_kilometers = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tf_horsepower = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tf_displacement = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        bt_add = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        l_id = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        l_username = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        l_registration = new javax.swing.JLabel();
        tf_fronttire = new javax.swing.JFormattedTextField();
        tf_reartire = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Adicionar Viatura");
        setResizable(false);

        jLabel1.setText("Matrícula");

        jLabel2.setText("Marca");

        cb_brand.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        cb_model.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Modelo");

        jLabel4.setText("Combustível");

        cb_fuel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tf_date.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));

        jLabel5.setText("Data de Registo");

        jLabel6.setText("Quilómetros");

        jLabel7.setText("CV");

        jLabel8.setText("Cilindrada");

        jLabel9.setText("Pneu Frente");

        jLabel10.setText("Pneu Trás");

        bt_add.setText("Adicionar Viatura");

        jLabel11.setText("Utilizador n.º:");

        l_id.setText("user");

        jLabel12.setText("Username:");

        l_username.setText("user");

        jLabel13.setText("Adicione uma nova Viatura");

        l_registration.setText("matricula");

        try {
            tf_fronttire.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###/##R##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            tf_reartire.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###/##R##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(l_registration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(729, 729, 729))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(l_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(754, 754, 754))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cb_brand, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cb_model, 0, 300, Short.MAX_VALUE)
                                    .addComponent(cb_fuel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tf_date))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(l_username, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(tf_kilometers, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(tf_horsepower, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE))))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(318, 318, 318)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(bt_add, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tf_displacement, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tf_fronttire, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tf_reartire, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(l_id)
                    .addComponent(jLabel12)
                    .addComponent(l_username))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6)
                    .addComponent(tf_kilometers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l_registration))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cb_brand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(tf_horsepower, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cb_model, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(tf_displacement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cb_fuel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(tf_fronttire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tf_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(tf_reartire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(bt_add)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(896, 289));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_add;
    private javax.swing.JComboBox cb_brand;
    private javax.swing.JComboBox cb_fuel;
    private javax.swing.JComboBox cb_model;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel l_id;
    private javax.swing.JLabel l_registration;
    private javax.swing.JLabel l_username;
    private javax.swing.JFormattedTextField tf_date;
    private javax.swing.JTextField tf_displacement;
    private javax.swing.JFormattedTextField tf_fronttire;
    private javax.swing.JTextField tf_horsepower;
    private javax.swing.JTextField tf_kilometers;
    private javax.swing.JFormattedTextField tf_reartire;
    // End of variables declaration//GEN-END:variables
}
