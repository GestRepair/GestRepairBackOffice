/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repairs.repairs;

import javax.swing.JOptionPane;
import static javax.xml.bind.DatatypeConverter.parseInt;
import services.APIService;
import users.employer.APIEmployer;

/**
 *
 * @author Rui Barcelos
 */
public final class AddRepair extends javax.swing.JFrame {

    /**
     * Creates new form AddRepair
     *
     * @param login
     * @param vehicle
     * @param idEmployer
     * @param idService
     * @throws java.lang.Exception
     */
    public AddRepair(String login, int vehicle, int idEmployer, int idService) throws Exception {
        APIRepair api = new APIRepair();
        APIService apiService = new APIService();
        APIEmployer apiEmployer = new APIEmployer();
        initComponents();
        if (idService == 1 || idService == 2) {
            showService(apiService.Service(login));
            showEmployers(apiEmployer.ShowEmployer(login, 1, Cb_Val(cb_service.getSelectedIndex() + 2, apiService.Service(login))));
            cb_service.removeItemAt(0);
            cb_service.removeItemAt(0);
            l_idService.setVisible(false);
            l_employer.setVisible(false);
        } else {
            cb_service.setVisible(false);
            cb_employer.setVisible(false);
            l_idService.setText(apiService.GetInfo(login, idService)[1]);
            l_employer.setText(apiEmployer.GetInfoEmployerUser(login, idEmployer)[1]);
        }
        ta_pdesc.setLineWrap(true);
        Events(login, vehicle, idService, idEmployer, api, apiService, apiEmployer);
    }

    /**
     * Define os eventos
     *
     * @param login
     * @param vehicle
     * @param idService
     * @param idEmployer
     * @param api
     * @param apiService
     * @param apiEmployer
     */
    private void Events(final String login, final int vehicle, final int idService, final int idEmployer, final APIRepair api, final APIService apiService, final APIEmployer apiEmployer) {
        bt_add.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_addPost(evt, login, vehicle, idService, idEmployer, api, apiService, apiEmployer);
            }
        });
        cb_service.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_Service(evt, login, apiEmployer, apiService);
            }
        });
    }

    /**
     * Verifica se pode fazer adicionar a Base de dados, se poder envia a
     * confirmação e caso seja confirmado faz post
     *
     * @param evt
     * @param login
     * @param vehicle
     * @param idService
     * @param idEmployer
     * @param api
     * @param apiService
     * @param apiEmployer
     */
    private void BT_addPost(java.awt.event.ActionEvent evt, String login, int vehicle, int idService, int idEmployer, APIRepair api, APIService apiService, APIEmployer apiEmployer) {
        try {
            if (ta_pdesc.getText().length() > 0) {
                int serv = Cb_Val(cb_service.getSelectedIndex() + 2, apiService.Service(login));
                int empl = Cb_Val(cb_employer.getSelectedIndex(), apiEmployer.ShowEmployer(login, 1, serv));
                int x = JOptionPane.showConfirmDialog(this, "Tem a ceteza que quer inserir os dados?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    String value[] = api.PostRepair(login, vehicle, ta_pdesc.getText(), (idService == 1 || idService == 2) ? empl : idEmployer);
                    JOptionPane.showMessageDialog(this, value[1]);
                    if ("ok".equals(value[0])) {
                        dispose();
                    }
                    dispose();
                } else if (x == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(this, "A Reparação não foi introduzida no sistema!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Verifique o se o campo descrição está preenchidos!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro a adicionar reparação!\n Verifique se os dados estão corretos");

        }

    }

    /**
     * Mostra os dados dos funcionários,pela acção da combobox
     *
     * @param evt
     * @param login
     * @param apiEmployer
     * @param apiService
     */
    private void CB_Service(java.awt.event.ActionEvent evt, String login, APIEmployer apiEmployer, APIService apiService) {
        try {
            showEmployers(apiEmployer.ShowEmployer(login, 1, Cb_Val(cb_service.getSelectedIndex() + 2, apiService.Service(login))));
        } catch (Exception ex) {

        }
    }

    /**
     * Insere os dados na dropdown Serviços
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
     * Insere os dados na dropdown empregados
     *
     * @param list
     */
    private void showEmployers(String[][] list) {
        cb_employer.removeAllItems();
        for (String[] list1 : list) {
            cb_employer.addItem(list1[2]);
        }
    }
    /**
     * Vai buscar o ida da base de dados do que é mostrado na dropdown
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
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_pdesc = new javax.swing.JTextArea();
        l_idService = new javax.swing.JLabel();
        cb_employer = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        l_employer = new javax.swing.JLabel();
        bt_add = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Adicionar Reparação");
        setResizable(false);

        jLabel1.setText("Serviço:");

        cb_service.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Descrição do Problema:");

        ta_pdesc.setColumns(20);
        ta_pdesc.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        ta_pdesc.setRows(5);
        jScrollPane1.setViewportView(ta_pdesc);

        l_idService.setText("Service");

        cb_employer.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Funcionário:");

        l_employer.setText("employer");

        bt_add.setText("Adicionar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(l_idService)
                                .addGap(0, 74, Short.MAX_VALUE))
                            .addComponent(cb_service, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(l_employer))
                            .addComponent(cb_employer, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(76, 76, 76))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_add)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(l_idService)
                    .addComponent(jLabel3)
                    .addComponent(l_employer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_service, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_employer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(bt_add)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(416, 359));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_add;
    private javax.swing.JComboBox cb_employer;
    private javax.swing.JComboBox cb_service;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel l_employer;
    private javax.swing.JLabel l_idService;
    private javax.swing.JTextArea ta_pdesc;
    // End of variables declaration//GEN-END:variables
}
