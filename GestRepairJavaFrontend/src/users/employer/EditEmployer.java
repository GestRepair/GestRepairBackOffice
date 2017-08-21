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

    APIService apiService = new APIService();
    APIEmployer api = new APIEmployer();

    /**
     * Creates new form EditEmployer
     *
     * @param login
     * @param id
     * @throws java.lang.Exception
     */
    public EditEmployer(String login, int id) throws Exception {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../../img/imageedit_4_8303763918.png")));
        initComponents();
        insertCb(apiService.Service(login));
        int idEmployer = parseInt(GetInfo(login, id));
        Events(login,id,idEmployer); 
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
     *
     * @param val
     * @param list
     * @return
     */
    private int newIdCb(int val, String[][] list) {
        return (val == 0) ? 1 : parseInt(list[val][0]);
    }

    private String GetInfo(String login, int id) throws Exception {
        String emp[] = api.InfoEmployer(login, id);
        l_idEmployer.setText(emp[0]);
        l_name.setText(emp[1]);
        cb_service.setSelectedItem(emp[2]);
        if ("1".equals(emp[3])) {
            l_state.setText("Ativo");
        } else {
            l_state.setText("Inativo");
        }
        return emp[0];
    }
    private void Events(final String login, final int idUser,final int idEmp) {
        bt_edit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_EditEmployer(evt, login, idUser,idEmp);
            }
        });
    }

    private void BT_EditEmployer(java.awt.event.ActionEvent evt, String login, int idUser,final int idEmp) {
        try {
            int x = JOptionPane.showConfirmDialog(this, "Deseja Alterar o serviço deste funcionário para " + cb_service.getSelectedItem() + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if ("ok".equals(api.PutEmployer(login, idEmp, newIdCb(cb_service.getSelectedIndex(), apiService.Service(login))))) {
                    JOptionPane.showMessageDialog(this, "O Serviço deste funcionário foi alterado com sucesso");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro Interno");
                }
            } else if(x == JOptionPane.NO_OPTION){
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cb_service = new javax.swing.JComboBox();
        l_name = new javax.swing.JLabel();
        l_idEmployer = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        l_state = new javax.swing.JLabel();
        bt_edit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Editar Funcionário");

        jLabel1.setText("N.º de Funcionário:");

        jLabel2.setText("Nome:");

        jLabel3.setText("Serviço:");

        cb_service.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        l_name.setText("nome");

        l_idEmployer.setText("número");

        jLabel4.setText("Estado");

        l_state.setText("estado");

        bt_edit.setText("Editar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(6, 6, 6)))
                        .addGap(72, 72, 72)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(l_state)
                            .addComponent(l_name)
                            .addComponent(cb_service, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(l_idEmployer))
                            .addComponent(bt_edit))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(l_idEmployer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(l_name))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cb_service, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(l_state))
                .addGap(18, 18, 18)
                .addComponent(bt_edit)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_edit;
    private javax.swing.JComboBox cb_service;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel l_idEmployer;
    private javax.swing.JLabel l_name;
    private javax.swing.JLabel l_state;
    // End of variables declaration//GEN-END:variables
}
