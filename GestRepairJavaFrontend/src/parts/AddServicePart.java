/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts;

import javax.swing.JOptionPane;
import static javax.xml.bind.DatatypeConverter.parseInt;
import services.APIService;

/**
 *
 * @author Rui Barcelos
 */
public final class AddServicePart extends javax.swing.JFrame {

    /**
     * Creates new form AddRepair
     *
     * @param login
     * @param idPart
     * @param idService
     * @throws java.lang.Exception
     */
    public AddServicePart(String login, int idPart, int idService) throws Exception {
        APIParts api = new APIParts();
        APIService apiService = new APIService();
        initComponents();
        l_idPart.setText(idPart + "");
        Events(login, idPart, idService, api, apiService);
        showServiceCB(login, idPart, api);
    }

    /**
     * Eventos
     *
     * @param login
     * @param idPart
     * @param idService
     * @param api
     * @param apiService
     */
    private void Events(final String login, final int idPart, final int idService, final APIParts api, final APIService apiService) {
        bt_add.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_addPost(evt, login, idPart, idService, api, apiService);
            }
        });
    }

    /**
     * Verifica se pode enviar os dados, caso possa envia após confirmação
     *
     * @param evt
     * @param login
     * @param idPart
     * @param idService
     * @param api
     * @param apiService
     */
    private void BT_addPost(java.awt.event.ActionEvent evt, String login, int idPart, int idService, APIParts api, APIService apiService) {
        try {
            int x = JOptionPane.showConfirmDialog(this, "Tem a ceteza que quer inserir os dados?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                String value[] = api.POSTService(login, sendData(login, apiService), idPart);
                if ("ok".equals(value[0])) {
                    new InfoParts(login, idPart, idService).setVisible(true);
                    dispose();
                }
            } else if (x == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(this, "A peça não foi introduzida no sistema!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro a adicionar reparação!\n Verifique se os dados estão corretos");
        }
    }

    /**
     * Insere os serviços na dropdown
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
     * Vai buscar o ID do valor da dropdown
     *
     * @param val
     * @param list
     * @return
     */
    private int Cb_Val(int val, String[][] list) {
        return parseInt(list[val][0]);
    }

    /**
     * Mostra os serviços na dropdown
     *
     * @param login
     * @param idPart
     * @param api
     * @throws Exception
     */
    private void showServiceCB(String login, int idPart, APIParts api) throws Exception {
        showService(api.ListServiceNotParts(login, idPart));
    }

    /**
     * Prepara os dados para enviar
     *
     * @param login
     * @param apiService
     * @return
     * @throws Exception
     */
    private int sendData(String login, APIService apiService) throws Exception {
        return Cb_Val(cb_service.getSelectedIndex(), apiService.Service(login));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        bt_add = new javax.swing.JButton();
        l_id = new javax.swing.JLabel();
        l_idPart = new javax.swing.JLabel();
        cb_service = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Adicionar Serviço");
        setResizable(false);

        jLabel4.setText("Serviço:");

        bt_add.setText("Adicionar");

        l_id.setText("ID:");

        l_idPart.setText("id");

        cb_service.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bt_add))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(l_id)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_idPart))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_service, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l_id)
                    .addComponent(l_idPart))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cb_service, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(bt_add)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(279, 155));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_add;
    private javax.swing.JComboBox cb_service;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel l_id;
    private javax.swing.JLabel l_idPart;
    // End of variables declaration//GEN-END:variables
}
