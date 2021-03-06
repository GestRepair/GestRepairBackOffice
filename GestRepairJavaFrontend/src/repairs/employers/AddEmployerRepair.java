/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repairs.employers;

import javax.swing.JOptionPane;
import static javax.xml.bind.DatatypeConverter.parseInt;
import services.APIService;
import users.employer.APIEmployer;

/**
 *
 * @author Rui Barcelos
 */
public final class AddEmployerRepair extends javax.swing.JFrame {

    /**
     * Creates new form AddRepair
     *
     * @param login
     * @param idRepair
     * @param idService
     * @throws java.lang.Exception
     */
    public AddEmployerRepair(String login, int idRepair, int idService) throws Exception {
        APIEmployerRepair api = new APIEmployerRepair();
        APIService apiService = new APIService();
        APIEmployer apiEmployer = new APIEmployer();
        initComponents();
        showService(apiService.ShowNotRepairService(login, idRepair));
        showEmployers(apiEmployer.ShowNotRepairEmployer(login, 1, Cb_Val(cb_service.getSelectedIndex(), apiService.ShowNotRepairService(login, idRepair))));
        Events(login, idRepair, api, apiEmployer, apiService);
    }

    /**
     * Define os eventos
     *
     * @param login
     * @param idRepair
     * @param api
     * @param apiEmployer
     * @param apiService
     */
    private void Events(final String login, final int idRepair, final APIEmployerRepair api, final APIEmployer apiEmployer, final APIService apiService) {
        bt_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_ADD(evt, login, idRepair, api, apiEmployer, apiService);
            }
        });
        cb_service.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_SERVICE(evt, login, idRepair, apiEmployer, apiService);
            }
        });
    }

    /**
     * Mostra os enpregados na combobox
     *
     * @param evt
     * @param login
     * @param idRepair
     * @param apiEmployer
     * @param apiService
     */
    private void CB_SERVICE(java.awt.event.ActionEvent evt, String login, int idRepair, APIEmployer apiEmployer, APIService apiService) {
        try {
            showEmployers(apiEmployer.ShowNotRepairEmployer(login, 1, Cb_Val(cb_service.getSelectedIndex(), apiService.ShowNotRepairService(login, idRepair))));
        } catch (Exception ex) {
        }
    }

    /**
     * Pede a confirmação do utilizador, e se sim insere a o registo na base de
     * dados
     *
     * @param evt
     * @param login
     * @param idRepair
     * @param api
     * @param apiEmployer
     * @param apiService
     */
    private void BT_ADD(java.awt.event.ActionEvent evt, String login, int idRepair, APIEmployerRepair api, APIEmployer apiEmployer, APIService apiService) {
        try {
            int serv = Cb_Val(cb_service.getSelectedIndex(), apiService.ShowNotRepairService(login, idRepair));
            int empl = Cb_Val(cb_employer.getSelectedIndex(), apiEmployer.ShowNotRepairEmployer(login, 1, serv));
            int x = JOptionPane.showConfirmDialog(this, "Tem a ceteza que quer inserir os dados?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                String value[] = api.PostEmployerRepair(login, idRepair, empl);
                JOptionPane.showMessageDialog(this, value[1]);
                if ("ok".equals(value[0])) {
                    dispose();
                }
            } else if (x == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(this, "A Reparação não foi introduzida no sistema!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro a adicionar reparação!\n Verifique se os dados estão corretos");
        }
    }

    /**
     * Insere os dados na dropdown serviços
     *
     * @param list
     */
    private void showService(String[][] list) {
        cb_service.removeAllItems();
        for (String[] list1 : list) {
            cb_service.addItem(list1[1]);
        }
    }

    /**
     * Insere os dados na dropdown Empregados
     *
     * @param list
     */
    private void showEmployers(String[][] list) {
        cb_employer.removeAllItems();
        for (String[] list1 : list) {
            cb_employer.addItem(list1[1]);
        }
    }
    /**
     * Vai buscar o id dos valores da dropdown
     * @param val
     * @param list
     * @return 
     */
    private int Cb_Val(int val, String[][] list) {
        return parseInt(list[val][0]);
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
        cb_service = new javax.swing.JComboBox();
        cb_employer = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        bt_add = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Adicionar Funcionário à Reparação");

        jLabel1.setText("Serviço:");

        cb_service.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cb_employer.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Funcionário:");

        bt_add.setText("Adicionar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(bt_add)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cb_service, javax.swing.GroupLayout.Alignment.LEADING, 0, 200, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_employer, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_service, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cb_employer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(bt_add)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(236, 218));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_add;
    private javax.swing.JComboBox cb_employer;
    private javax.swing.JComboBox cb_service;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
