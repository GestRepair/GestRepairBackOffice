/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgets;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.xml.bind.DatatypeConverter.parseInt;
import services.APIService;
import vehicles.vehicles.APIVehicles;

/**
 *
 * @author Rui Barcelos
 */
public final class AddBudget extends javax.swing.JFrame {

    /**
     * Creates new form AddRepair
     *
     * @param login
     * @param idVehicle
     * @param employer
     * @param idService
     * @throws java.lang.Exception
     */
    public AddBudget(String login, int idVehicle, int employer, int idService) throws Exception {
        initComponents();

        APIService apiService = new APIService();
        APIVehicles apiVehicles = new APIVehicles();
        //setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../img/imageedit_4_8303763918.png")));
        Events(login, idVehicle, idService, apiService);
        l_vehicle.setText(apiVehicles.InfoVehicle(login, idVehicle)[3]);
        if (idService == 1 || idService == 2) {
            showService(apiService.Service(login));
            cb_service.removeItemAt(0);
            cb_service.removeItemAt(0);
        } else {
            l_infocb.setVisible(false);
            cb_service.setVisible(false);
        }
        ta_pdesc.setLineWrap(true);
    }

    private void showService(String[][] list) {
        cb_service.removeAllItems();
        for (String[] list1 : list) {
            cb_service.addItem(list1[1]);
        }
    }

    private int Cb_Val(int val, String[][] list) {
        return parseInt(list[val][0]);
    }

    private void Events(final String login, final int idVehicle, final int idService, final APIService apiService) {
        bt_add.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_ADD(evt, login, idVehicle, idService, apiService);
            }
        });
    }

    private String[] SendData(int idVehicle, int idService) {
        String[] data = new String[3];
        try {
            data[0] = idVehicle + "";
            data[1] = ta_pdesc.getText();
            data[2] = idService + "";
        } catch (Exception ex) {
            Logger.getLogger(AddBudget.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    private void BT_ADD(java.awt.event.ActionEvent evt, String login, int idVehicle, int idService, APIService apiService) {
        APIBudgets api = new APIBudgets();
        try {
            if (ta_pdesc.getText().length() > 10) {
                int serv = Cb_Val(cb_service.getSelectedIndex() + 2, apiService.Service(login));
                int x = JOptionPane.showConfirmDialog(this, "Tem a ceteza que quer inserir os dados?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    String[] value = api.POSTAddBudget(login, SendData(idVehicle, (idService == 1 || idService == 2) ? serv : idService));
                    JOptionPane.showMessageDialog(this, value[1]);
                    if ("ok".equals(value[0])) {
                        dispose();
                    }
                    dispose();
                } else if (x == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(this, "A Reparação não foi introduzida no sistema!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Insira uma descrição com pelo menos 10 caracteres!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro a adicionar reparação!\n Verifique se os dados estão corretos");
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

        l_infocb = new javax.swing.JLabel();
        cb_service = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_pdesc = new javax.swing.JTextArea();
        bt_add = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        l_vehicle = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Adicionar Orçamento");
        setResizable(false);

        l_infocb.setText("Selecione um serviço para o orçamento:");

        cb_service.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Descreva aqui o problema do veículo:");

        ta_pdesc.setColumns(20);
        ta_pdesc.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        ta_pdesc.setRows(5);
        jScrollPane1.setViewportView(ta_pdesc);

        bt_add.setText("Adicionar");

        jLabel3.setText("Viatura:");

        l_vehicle.setText("jLabel4");

        jLabel1.setText("Adicionar Orçamento");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l_vehicle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(101, 101, 101))
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(315, 315, 315))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(l_infocb, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(303, 303, 303))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(bt_add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cb_service, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l_vehicle, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(l_infocb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1)
                .addComponent(cb_service)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(bt_add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );

        setSize(new java.awt.Dimension(520, 420));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_add;
    private javax.swing.JComboBox cb_service;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel l_infocb;
    private javax.swing.JLabel l_vehicle;
    private javax.swing.JTextArea ta_pdesc;
    // End of variables declaration//GEN-END:variables
}
