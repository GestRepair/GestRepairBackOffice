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
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../../img/imageedit_4_8303763918.png")));
        initComponents();
        l_id.setText(id + "");
        l_username.setText(user);
        l_registration.setText(vehicle);
        showBrand(apiBrand.Brand(login));
        showModel(apiModel.Model(login, newIdCb(cb_brand.getSelectedIndex(), apiBrand.Brand(login))));
        showFuel(apiFuel.Fuel(login));
        Events(login, id, api, apiModel, apiBrand, apiFuel);
    }

    public void showBrand(String[][] list) {
        cb_brand.removeAllItems();
        for (int i = 0; i < list.length; i++) {
            cb_brand.addItem(list[i][1]);
        }
    }

    public void showModel(String[][] list) {
        cb_model.removeAllItems();
        for (int i = 0; i < list.length; i++) {
            cb_model.addItem(list[i][1]);
        }
    }

    public void showFuel(String[][] list) {
        cb_fuel.removeAllItems();
        for (int i = 0; i < list.length; i++) {
            cb_fuel.addItem(list[i][1]);
        }
    }

    public int newIdCb(int val, String[][] list) {
        if (val < 0) {
            return parseInt(list[0][0]);
        } else {
            return parseInt(list[val][0]);
        }
    }

    private String[] data(String login, APIModel apiModel, APIBrand apiBrand, APIFuel apiFuel) throws Exception {
        String data[] = new String[9];
        data[0] = newIdCb(cb_model.getSelectedIndex(), apiModel.Model(login, newIdCb(cb_brand.getSelectedIndex(), apiBrand.Brand(login)))) + "";
        data[1] = l_registration.getText();
        data[2] = newIdCb(cb_fuel.getSelectedIndex(), apiFuel.Fuel(login)) + "";
        data[3] = tf_horsepower.getText();
        data[4] = tf_displacement.getText();
        data[5] = tf_kilometers.getText();
        data[6] = tf_fronttiresize.getText();
        data[7] = tf_reartiresize.getText();
        data[8] = tf_date.getText();
        return data;
    }
    private static final Pattern VALID_NUMBER_REGEX = Pattern.compile("[0-9]", Pattern.CASE_INSENSITIVE);

    private static boolean validateNumber(String numberStr) {
        Matcher matcher = VALID_NUMBER_REGEX.matcher(numberStr);
        return matcher.find();
    }

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

    private void BT_ADD(java.awt.event.ActionEvent evt, String login, int id, APIVehicles api, APIModel apiModel, APIBrand apiBrand, APIFuel apiFuel) {
        if (validateNumber(tf_displacement.getText()) && validateNumber(tf_horsepower.getText()) == true && validateNumber(tf_kilometers.getText()) == true && tf_date.getText().length()>8) {
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
        tf_reartiresize = new javax.swing.JTextField();
        tf_fronttiresize = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        bt_add = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        l_id = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        l_username = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        l_registration = new javax.swing.JLabel();

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(l_id)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(l_username))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(bt_add)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2)
                                .addComponent(jLabel4)
                                .addComponent(jLabel3))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cb_brand, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cb_fuel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cb_model, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tf_date, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(l_registration))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel8)
                                .addComponent(jLabel7)
                                .addComponent(jLabel9)
                                .addComponent(jLabel10))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(tf_reartiresize)
                                .addComponent(tf_horsepower)
                                .addComponent(tf_kilometers)
                                .addComponent(tf_displacement)
                                .addComponent(tf_fronttiresize, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(12, 12, 12)
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
                    .addComponent(tf_fronttiresize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tf_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(tf_reartiresize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(bt_add)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(646, 272));
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
    private javax.swing.JTextField tf_fronttiresize;
    private javax.swing.JTextField tf_horsepower;
    private javax.swing.JTextField tf_kilometers;
    private javax.swing.JTextField tf_reartiresize;
    // End of variables declaration//GEN-END:variables
}
