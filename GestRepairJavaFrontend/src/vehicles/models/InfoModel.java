package vehicles.models;

import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rui Barcelos
 */
public class InfoModel extends javax.swing.JFrame {

    /**
     * Creates new form InfoModel
     *
     * @param login
     * @param id
     * @param brand
     */
    public InfoModel(String login, int id, String brand) {
        APIModel api = new APIModel();
        initComponents();
        l_brand.setText(brand);
        InfoModel(login, id, api);
        Events(login, id, brand);
    }

    /**
     * Eventos
     *
     * @param login
     * @param id
     * @param brand
     */
    private void Events(final String login, final int id, final String brand) {
        bt_edit_model.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_EDIT(evt, login, id, brand);
            }
        });
    }

    /**
     * Abre a form de editar o modelo
     *
     * @param evt
     * @param login
     * @param id
     * @param brand
     */
    private void BT_EDIT(java.awt.event.ActionEvent evt, String login, int id, String brand) {
        try {
            new EditModel(login, id, brand).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param login
     * @param id
     */
    private void InfoModel(String login, int id, APIModel api) {
        try {
            String brand[] = api.InfoModel(login, id);
            l_idModel.setText(brand[0]);
            l_nameModel.setText(brand[1]);
        } catch (Exception ex) {
            Logger.getLogger(InfoModel.class.getName()).log(Level.SEVERE, null, ex);
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

        l_idModel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        l_nameModel = new javax.swing.JLabel();
        bt_edit_model = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        l_brand = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Informação do Modelo");

        l_idModel.setText("idModelo");

        jLabel2.setText("Nome do Modelo:");

        l_nameModel.setText("nomeModelo");

        bt_edit_model.setText("Editar Modelo");

        jLabel3.setText("Marca:");

        l_brand.setText("marca");

        jLabel4.setText("Detalhes do modelo n.º");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_edit_model)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_brand))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_nameModel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_idModel)))
                .addContainerGap(130, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(l_idModel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(l_nameModel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(l_brand))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_edit_model)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(316, 144));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_edit_model;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel l_brand;
    private javax.swing.JLabel l_idModel;
    private javax.swing.JLabel l_nameModel;
    // End of variables declaration//GEN-END:variables
}
