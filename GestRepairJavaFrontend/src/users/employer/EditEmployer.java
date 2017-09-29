/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.employer;

import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.xml.bind.DatatypeConverter.parseInt;
import services.APIService;

/**
 *
 * @author Rui Barcelos
 */
public class EditEmployer extends javax.swing.JFrame {

    /**
     * Creates new form EditEmployer
     *
     * @param login
     * @param id
     * @throws java.lang.Exception
     */
    public EditEmployer(String login, int id) throws Exception {
        APIService apiService = new APIService();
        APIEmployer api = new APIEmployer();
        initComponents();
        insertCb(apiService.Service(login));
        int idEmployer = parseInt(GetInfo(login, id, api));
        Events(login, id, idEmployer, api, apiService);
    }

    /**
     * Insert to services on combobox
     *
     * @param list
     * @throws Exception
     */
    private void insertCb(String[][] list) throws Exception {
        cb_service.removeAllItems();
        for (String[] list1 : list) {
            cb_service.addItem(list1[1]);
        }
    }

    /**
     * Verifica o id de uma dropdown pela posição
     *
     * @param val
     * @param list
     * @return
     */
    private int newIdCb(int val, String[][] list) {
        return (val == 0) ? 1 : parseInt(list[val][0]);
    }

    /**
     * Mostra a informação do funcionário na form
     *
     * @param login
     * @param id
     * @param api
     * @return
     * @throws Exception
     */
    private String GetInfo(String login, int id, APIEmployer api) throws Exception {
        String emp[] = api.InfoEmployer(login, id);
        l_idEmployer.setText(emp[0]);
        l_name.setText(emp[1]);
        cb_service.setSelectedItem(emp[2]);
        if ("1".equals(emp[4])) {
            l_state.setText("Ativo");
        } else {
            l_state.setText("Inativo");
        }
        return emp[0];
    }

    /**
     * Mostra os eventos
     *
     * @param login
     * @param idUser
     * @param idEmp
     * @param api
     * @param apiService
     */
    private void Events(final String login, final int idUser, final int idEmp, final APIEmployer api, final APIService apiService) {
        bt_edit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_EditEmployer(evt, login, idUser, idEmp, api, apiService);
            }
        });
    }

    /**
     * Verifica se o utilizador quer actualizar o registo se sim actualiza
     *
     * @param evt
     * @param login
     * @param idUser
     * @param idEmp
     * @param api
     * @param apiService
     */
    private void BT_EditEmployer(java.awt.event.ActionEvent evt, String login, int idUser, int idEmp, APIEmployer api, APIService apiService) {
        try {
            int x = JOptionPane.showConfirmDialog(this, "Deseja Alterar o serviço deste funcionário para " + cb_service.getSelectedItem() + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                String[] value = api.PutEmployer(login, idEmp, newIdCb(cb_service.getSelectedIndex(), apiService.Service(login)));
                JOptionPane.showMessageDialog(this, value[1]);
                if ("ok".equals(value[0])) {
                    dispose();
                }
            } else if (x == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(this, "O serviço não foi alterado!");
            }
        } catch (Exception ex) {
            Logger.getLogger(EditEmployer.class.getName()).log(Level.SEVERE, null, ex);
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

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cb_service = new javax.swing.JComboBox();
        l_name = new javax.swing.JLabel();
        l_idEmployer = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        l_state = new javax.swing.JLabel();
        bt_edit = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Editar Funcionário");
        setResizable(false);

        jLabel2.setText("Nome:");

        jLabel3.setText("Serviço:");

        cb_service.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        l_name.setText("nome");

        l_idEmployer.setText("número");

        jLabel4.setText("Estado");

        l_state.setText("estado");

        bt_edit.setText("Editar");

        jLabel5.setText("Editar Funcionário n.º:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(l_idEmployer)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(l_state, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(124, 124, 124)
                                .addComponent(bt_edit))
                            .addComponent(l_name)
                            .addComponent(cb_service, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(l_idEmployer))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(l_name))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(bt_edit))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cb_service, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(l_state))))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(424, 194));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_edit;
    private javax.swing.JComboBox cb_service;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel l_idEmployer;
    private javax.swing.JLabel l_name;
    private javax.swing.JLabel l_state;
    // End of variables declaration//GEN-END:variables
}
