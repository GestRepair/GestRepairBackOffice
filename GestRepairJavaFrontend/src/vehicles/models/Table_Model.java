package vehicles.models;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import static javax.xml.bind.DatatypeConverter.parseInt;
import vehicles.brands.APIBrand;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rui Barcelos
 */
public class Table_Model extends javax.swing.JFrame {

    /**
     * Creates new form ListBrand
     *
     * @param login
     */
    public Table_Model(String login) {
        APIModel api = new APIModel();
        APIBrand apiBrand = new APIBrand();
        initComponents();
        ListBrand(login, api, apiBrand);
        Events(login, api, apiBrand);
    }

    /**
     * Mostra os eventos
     *
     * @param login
     * @param api
     * @param apiBrand
     */
    private void Events(final String login, final APIModel api, final APIBrand apiBrand) {
        bt_edit_model.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_EDIT(evt, login);
            }
        });
        bt_info.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_INFO(evt, login);
            }
        });
        cb_brand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_BRAND(evt, login, api, apiBrand);
            }
        });
        tbl_model.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBL_CLICKED(evt);
            }
        });
    }

    /**
     * Actualiza a tabela quando altera-se o valor da dropdown
     *
     * @param evt
     * @param login
     * @param api
     * @param apiBrand
     */
    private void CB_BRAND(java.awt.event.ActionEvent evt, String login, APIModel api, APIBrand apiBrand) {
        try {
            cleanTable();
            String brand[][];
            brand = apiBrand.Brand(login);
            Table(api.Model(login, cb_val(brand)));
        } catch (Exception ex) {
            Logger.getLogger(Table_Model.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Abre a form de editar viatura
     *
     * @param evt
     * @param login
     */
    private void BT_EDIT(java.awt.event.ActionEvent evt, String login) {
        try {
            new EditModel(login, parseInt(l_idModel.getText()), (String) cb_brand.getSelectedItem()).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Abre a form de informação do modelo
     *
     * @param evt
     * @param login
     */
    private void BT_INFO(java.awt.event.ActionEvent evt, String login) {
        try {
            new InfoModel(login, parseInt(l_idModel.getText()), (String) cb_brand.getSelectedItem()).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Ao clicar seleciona uma linha
     *
     * @param evt
     */
    private void TBL_CLICKED(java.awt.event.MouseEvent evt) {
        int i = tbl_model.getSelectedRow();
        row(i);
    }

    private void cleanTable() {
        DefaultTableModel mod = (DefaultTableModel) tbl_model.getModel();
        mod.setRowCount(0);
    }

    /**
     * Insere na dropdown a lista de viatutas
     *
     * @param login
     * @param id
     */
    private void ListBrand(String login, APIModel api, APIBrand apiBrand) {
        cb_brand.removeAllItems();
        try {
            String brand[][] = apiBrand.Brand(login);
            for (int i = 0; i < brand.length; i++) {
                cb_brand.addItem(brand[i][1]);
            }
            Table(api.Model(login, cb_val(brand)));
            row(0);
        } catch (Exception ex) {
            Logger.getLogger(Table_Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Encontra o id pela posição da dropdown
     *
     * @param list
     * @return
     * @throws Exception
     */
    private int cb_val(String[][] list) throws Exception {
        int id = cb_brand.getSelectedIndex();
        return parseInt(list[id][0]);
    }

    /**
     * Ao selecionar a linha mostro informação no post
     *
     * @param selected
     */
    private void row(int selected) {
        DefaultTableModel mod = (DefaultTableModel) tbl_model.getModel();
        l_idModel.setText((String) mod.getValueAt(selected, 0));
        l_nameModel.setText((String) mod.getValueAt(selected, 1));
    }

    /**
     * Insere os dados na tabela
     *
     * @param list
     */
    private void Table(String[][] list) {
        if (list.length > 0) {
            DefaultTableModel mod = (DefaultTableModel) tbl_model.getModel();
            Object[] row = new Object[2];
            for (String[] list1 : list) {
                for (int i = 0; i < row.length; i++) {
                    row[i] = list1[i];
                }
                mod.addRow(row);
            }
            tbl_model.setRowSelectionInterval(0, 0);
            l_idModel.setText((String) tbl_model.getModel().getValueAt(0, 0));
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
        jLabel3 = new javax.swing.JLabel();
        l_nameModel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        l_idModel = new javax.swing.JLabel();
        bt_edit_model = new javax.swing.JButton();
        bt_info = new javax.swing.JButton();
        cb_brand = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Lista de Modelos");

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
        jScrollPane1.setViewportView(tbl_model);

        jLabel1.setText("Selecione a marca do modelo que pretende ver:");

        jLabel3.setText("Nome do Modelo:");

        l_nameModel.setText("nomeMarca");

        jLabel4.setText("ID Modelo:");

        l_idModel.setText("idmodelo");

        bt_edit_model.setText("Editar Modelo");

        bt_info.setText("Info");

        cb_brand.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_brand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_idModel)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_nameModel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_info)
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
                    .addComponent(cb_brand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_edit_model)
                    .addComponent(bt_info)
                    .addComponent(jLabel4)
                    .addComponent(l_idModel)
                    .addComponent(jLabel3)
                    .addComponent(l_nameModel))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(599, 639));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_edit_model;
    private javax.swing.JButton bt_info;
    private javax.swing.JComboBox cb_brand;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel l_idModel;
    private javax.swing.JLabel l_nameModel;
    private javax.swing.JTable tbl_model;
    // End of variables declaration//GEN-END:variables
}
