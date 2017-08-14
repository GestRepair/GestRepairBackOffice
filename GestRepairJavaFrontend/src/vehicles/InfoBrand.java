package vehicles;

import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import static javax.xml.bind.DatatypeConverter.parseInt;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rui Barcelos
 */
public class InfoBrand extends javax.swing.JFrame {

    private final String login;
    private final int id;
    APIVehicles api = new APIVehicles();

    /**
     * Creates new form InfoBrand
     *
     * @param login
     * @param id
     */
    public InfoBrand(String login, int id) {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../img/imageedit_4_8303763918.png")));
        InfoBrand(login, id);
        this.id = id;
        this.login = login;
    }

    /**
     *
     * @param login
     * @param id
     */
    private void InfoBrand(String login, int id) {
        try {
            String brand[] = api.InfoBrand(login,id);
            l_idBrand.setText(id+"");
            l_nameBrand.setText(brand[1]);
            Table(api.Model(login, id));
            row(0);    
        } catch (Exception ex) {
            Logger.getLogger(InfoBrand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void row(int selected){
        DefaultTableModel mod = (DefaultTableModel) tbl_model.getModel();
        l_idModel.setText((String) mod.getValueAt(selected, 0));
        l_nameModel.setText((String) mod.getValueAt(selected, 1));
    }
    private void Table(String[][] list){
        DefaultTableModel mod = (DefaultTableModel) tbl_model.getModel();
        Object[] row = new Object[2];
        for (String[] list1 : list) {
            for (int i = 0; i < row.length; i++) {
                row[i] = list1[i];
            }
            mod.addRow(row);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_model = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        l_idBrand = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        l_nameBrand = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        l_nameModel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        l_idModel = new javax.swing.JLabel();
        bt_edit_model = new javax.swing.JButton();
        bt_edit_brand = new javax.swing.JButton();
        bt_info = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Informação da Marca");

        tbl_model.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Modelo", "Nome do Modelo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_model.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_modelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_model);

        jLabel1.setText("ID Marca:");

        l_idBrand.setText("idmarca");

        jLabel2.setText("Nome da  Marca:");

        l_nameBrand.setText("nomeMarca");

        jLabel3.setText("Nome do Modelo:");

        l_nameModel.setText("nomeMarca");

        jLabel4.setText("ID Modelo:");

        l_idModel.setText("idmodelo");

        bt_edit_model.setText("Editar Modelo");
        bt_edit_model.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_edit_modelActionPerformed(evt);
            }
        });

        bt_edit_brand.setText("Editar Marca");
        bt_edit_brand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_edit_brandActionPerformed(evt);
            }
        });

        bt_info.setText("Info");
        bt_info.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_infoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_idBrand)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_nameBrand)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_idModel)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_nameModel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addComponent(bt_info)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_edit_brand)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_edit_model)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(l_idBrand)
                    .addComponent(jLabel2)
                    .addComponent(l_nameBrand))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(l_idModel)
                    .addComponent(jLabel3)
                    .addComponent(l_nameModel))
                .addGap(22, 22, 22))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_edit_model)
                    .addComponent(bt_edit_brand)
                    .addComponent(bt_info))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_modelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_modelMouseClicked
        int i = tbl_model.getSelectedRow();
        row(i);
    }//GEN-LAST:event_tbl_modelMouseClicked

    private void bt_edit_brandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_edit_brandActionPerformed
        try {
            new EditBrand(this.login,this.id).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(InfoBrand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_edit_brandActionPerformed

    private void bt_edit_modelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_edit_modelActionPerformed
        try {
            new EditModel(login, parseInt(l_idModel.getText()), l_nameBrand.getText()).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_edit_modelActionPerformed

    private void bt_infoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_infoActionPerformed
        try {
            new InfoModel(this.login,this.id, l_nameBrand.getText()).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_infoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_edit_brand;
    private javax.swing.JButton bt_edit_model;
    private javax.swing.JButton bt_info;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel l_idBrand;
    private javax.swing.JLabel l_idModel;
    private javax.swing.JLabel l_nameBrand;
    private javax.swing.JLabel l_nameModel;
    private javax.swing.JTable tbl_model;
    // End of variables declaration//GEN-END:variables
}
