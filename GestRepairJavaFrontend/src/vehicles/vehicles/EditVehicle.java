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
import vehicles.brands.APIBrand;
import vehicles.fuel.APIFuel;
import vehicles.models.APIModel;

/**
 *
 * @author Rui Barcelos
 */
public class EditVehicle extends javax.swing.JFrame {

    /**
     * Creates new form EditVehicle
     *
     * @param login
     * @param id
     * @throws java.lang.Exception
     */
    public EditVehicle(String login, int id) throws Exception {
        APIVehicles api = new APIVehicles();
        APIBrand apiBrand = new APIBrand();
        APIModel apiModel = new APIModel();
        APIFuel apiFuel = new APIFuel();
        initComponents();
        InfoVehicle(login, id, api, apiBrand, apiModel, apiFuel);
        Events(login, id, api, apiModel, apiBrand, apiFuel);
    }

    private void Events(final String login, final int id, final APIVehicles api, final APIModel apiModel, final APIBrand apiBrand, final APIFuel apiFuel) {
        bt_edit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_EDIT(evt, login, id, api, apiModel, apiBrand, apiFuel);
            }
        });
        cb_brand.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_BRAND(evt, login, apiModel, apiBrand);
            }
        });
    }

    private void CB_BRAND(java.awt.event.ActionEvent evt, String login, APIModel apiModel, APIBrand apiBrand) {
        try {
            String[][] brand = apiBrand.Brand(login);
            String[][] model = apiModel.Model(login, Cb_Val(cb_brand.getSelectedIndex(), brand));
            showModel(model);
        } catch (Exception ex) {
        }
    }

    private void BT_EDIT(java.awt.event.ActionEvent evt, String login, int id, APIVehicles api, APIModel apiModel, APIBrand apiBrand, APIFuel apiFuel) {
        if (validateTire(tf_rearTire.getText()) == true && validateTire(tf_rearTire.getText()) && validateNumber(tf_displacement.getText()) == true && validateNumber(tf_horsepower.getText()) == true && validateNumber(tf_kilometer.getText()) == true && tf_date.getText().length() > 8) {
            try {
                int x = JOptionPane.showConfirmDialog(this, "Tem a certeza que quer alterar a viatura?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    String[] value = api.PutVehicle(login, id, data(login, apiModel, apiBrand, apiFuel));
                    JOptionPane.showMessageDialog(this, value[1]);
                    if ("ok".equals(value[0])) {
                        dispose();
                    }
                } else if (x == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(this, "A Viatura não foi alterada");
                }

            } catch (Exception ex) {
                Logger.getLogger(EditVehicle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Verifique se os dados estão corretos");
        }
    }

    private void InfoVehicle(String login, int id, APIVehicles api, APIBrand apiBrand, APIModel apiModel, APIFuel apiFuel) throws Exception {
        l_id.setText(id + "");
        String[][] brand = apiBrand.Brand(login);
        showBrand(brand);
        String[][] fuel = apiFuel.Fuel(login);
        showFuel(fuel);
        cb_brand.setSelectedItem(api.InfoVehicle(login, id)[1]);
        String[][] model = apiModel.Model(login, Cb_Val(cb_brand.getSelectedIndex(), brand));
        showModel(model);
        String info[] = api.InfoVehicle(login, id);
        cb_model.setSelectedItem(info[2]);
        tf_register.setText(info[3]);
        tf_horsepower.setText((info[4] != null) ? info[4] : "");
        tf_displacement.setText((info[5] != null) ? info[5] : "");
        tf_kilometer.setText((info[6] != null) ? info[6] : "");
        cb_fuel.setSelectedItem(info[7]);
        tf_frontTire.setText(info[8]);
        tf_rearTire.setText(info[9]);
        tf_date.setText(info[10].substring(0, 10));

    }

    private void showBrand(String[][] list) {
        cb_brand.removeAllItems();
        for (int i = 0; i < list.length; i++) {
            cb_brand.addItem(list[i][1]);
        }
    }

    private void showModel(String[][] list) {
        cb_model.removeAllItems();
        for (int i = 0; i < list.length; i++) {
            cb_model.addItem(list[i][1]);
        }
    }

    private void showFuel(String[][] list) {
        cb_fuel.removeAllItems();
        for (int i = 0; i < list.length; i++) {
            cb_fuel.addItem(list[i][1]);
        }
    }

    private int Cb_Val(int val, String[][] list) {
        return parseInt(list[val][0]);
    }

    private static final Pattern VALID_NUMBER_REGEX = Pattern.compile("[0-9]", Pattern.CASE_INSENSITIVE);

    private static boolean validateNumber(String numberStr) {
        Matcher matcher = VALID_NUMBER_REGEX.matcher(numberStr);
        return matcher.find();
    }
    private static final Pattern VALID_TIRE_REGEX = Pattern.compile("[0-9]{3}\\/[0-9]{2}R{1}[0-9]{2}", Pattern.CASE_INSENSITIVE);

    private static boolean validateTire(String numberStr) {
        Matcher matcher = VALID_TIRE_REGEX.matcher(numberStr);
        return matcher.find();
    }

    private String[] data(String login, APIModel apiModel, APIBrand apiBrand, APIFuel apiFuel) throws Exception {
        String vehicle[] = new String[10];
        vehicle[0] = l_id.getText();
        vehicle[1] = tf_register.getText();
        vehicle[2] = Cb_Val(cb_model.getSelectedIndex(), apiModel.Model(login, Cb_Val(cb_brand.getSelectedIndex(), apiBrand.Brand(login)))) + "";;
        vehicle[3] = tf_horsepower.getText();
        vehicle[4] = tf_displacement.getText();
        vehicle[5] = tf_kilometer.getText();
        vehicle[6] = Cb_Val(cb_fuel.getSelectedIndex(), apiFuel.Fuel(login)) + "";
        vehicle[7] = tf_frontTire.getText();
        vehicle[8] = tf_rearTire.getText();
        vehicle[9] = tf_date.getText();
        return vehicle;

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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tf_register = new javax.swing.JFormattedTextField();
        cb_brand = new javax.swing.JComboBox();
        cb_model = new javax.swing.JComboBox();
        l_id = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tf_frontTire = new javax.swing.JTextField();
        tf_rearTire = new javax.swing.JTextField();
        tf_kilometer = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tf_horsepower = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tf_displacement = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cb_fuel = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        tf_date = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        bt_edit = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Editar Viatura");
        setResizable(false);

        jLabel1.setText("Marca");

        jLabel2.setText("ID");

        jLabel3.setText("Matrícula");

        jLabel4.setText("Modelo");

        try {
            tf_register.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("AA-AA-AA")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        l_id.setText("ID");

        jLabel5.setText("Pneu Frente");

        jLabel6.setText("Pneu Trás");

        jLabel7.setText("CV");

        jLabel8.setText("Cilindrada");

        jLabel9.setText("Quilómetros");

        jLabel10.setText("Combustível");

        tf_date.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));

        jLabel11.setText("Data");

        bt_edit.setText("Editar");

        jLabel12.setText("Edite aqui a viatura");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(l_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(119, 119, 119))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tf_register, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(262, 262, 262))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(bt_edit)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addGap(39, 39, 39))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(44, 44, 44)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel10)
                                                .addComponent(jLabel11))
                                            .addGap(15, 15, 15)))
                                    .addGap(10, 10, 10)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(cb_fuel, javax.swing.GroupLayout.Alignment.LEADING, 0, 200, Short.MAX_VALUE)
                                        .addComponent(cb_model, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cb_brand, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tf_date))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addGap(29, 29, 29)
                                            .addComponent(tf_rearTire, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel5)
                                                .addComponent(jLabel9)
                                                .addComponent(jLabel8))
                                            .addGap(18, 18, 18)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(tf_horsepower)
                                                .addComponent(tf_frontTire)
                                                .addComponent(tf_kilometer)
                                                .addComponent(tf_displacement)))))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 12, Short.MAX_VALUE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(l_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(tf_register, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(tf_horsepower)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(cb_brand, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(tf_displacement))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(cb_model, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(tf_kilometer))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(cb_fuel, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(tf_frontTire))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(tf_date, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(tf_rearTire))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(5, 5, 5)
                .addComponent(bt_edit)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(646, 285));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_edit;
    private javax.swing.JComboBox cb_brand;
    private javax.swing.JComboBox cb_fuel;
    private javax.swing.JComboBox cb_model;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel l_id;
    private javax.swing.JFormattedTextField tf_date;
    private javax.swing.JTextField tf_displacement;
    private javax.swing.JTextField tf_frontTire;
    private javax.swing.JTextField tf_horsepower;
    private javax.swing.JTextField tf_kilometer;
    private javax.swing.JTextField tf_rearTire;
    private javax.swing.JFormattedTextField tf_register;
    // End of variables declaration//GEN-END:variables
}
