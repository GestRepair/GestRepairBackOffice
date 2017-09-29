package vehicles.brands;

import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import vehicles.models.EditModel;
import vehicles.models.InfoModel;
import vehicles.models.Table_Model;
import static javax.xml.bind.DatatypeConverter.parseInt;
import vehicles.models.APIModel;

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

    /**
     * Creates new form InfoBrand
     *
     * @param login
     * @param id
     */
    public InfoBrand(String login, int id) {
        APIBrand api = new APIBrand();
        APIModel apiModel = new APIModel();
        initComponents();
        InfoBrand(login, id, api, apiModel);
        Events(login, id, api, apiModel);
        this.id = id;
        this.login = login;
    }

    /**
     * Eventos
     *
     * @param login
     * @param id
     * @param api
     * @param apiModel
     */
    private void Events(final String login, final int id, final APIBrand api, final APIModel apiModel) {
        bt_edit_brand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_EDIT_Brand(evt, login, id, api);
            }
        });
        bt_edit_model.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_EDIT_Model(evt, login, id, api);
            }
        });
        bt_info.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_INFO(evt, login, id);
            }
        });
        tbl_model.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBL_CLICKED(evt);
            }
        });
    }

    /**
     * Abre a form de editar marca
     *
     * @param evt
     * @param login
     * @param id
     * @param api
     */
    private void BT_EDIT_Brand(java.awt.event.ActionEvent evt, String login, int id, APIBrand api) {
        try {
            try {
                new EditBrand(login, id).setVisible(true);
                dispose();
            } catch (Exception ex) {
                Logger.getLogger(InfoBrand.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(EditBrand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Abre form de editar modelo
     *
     * @param evt
     * @param login
     * @param id
     * @param api
     */
    private void BT_EDIT_Model(java.awt.event.ActionEvent evt, String login, int id, APIBrand api) {
        try {
            new EditModel(login, parseInt(l_idModel.getText()), l_nameBrand.getText()).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Mostra a form de informar modelo
     *
     * @param evt
     * @param login
     * @param id
     */
    private void BT_INFO(java.awt.event.ActionEvent evt, String login, int id) {
        try {
            new InfoModel(login, id, l_nameBrand.getText()).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Table_Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Seleciona uma linha ao clicar
     *
     * @param evt
     */
    private void TBL_CLICKED(java.awt.event.MouseEvent evt) {
        int i = tbl_model.getSelectedRow();
        row(i);
    }

    /**
     * Mostra a informação da marca
     *
     * @param login
     * @param id
     */
    private void InfoBrand(String login, int id, APIBrand api, APIModel apiModel) {
        try {
            String brand[] = api.InfoBrand(login, id);
            l_idBrand.setText(id + "");
            l_nameBrand.setText(brand[1]);
            Table(apiModel.Model(login, id));
            row(0);
        } catch (Exception ex) {
            Logger.getLogger(InfoBrand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Depois da linha estar selecionada edita a informação
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
        jLabel5 = new javax.swing.JLabel();

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
        jScrollPane1.setViewportView(tbl_model);

        l_idBrand.setText("idmarca");

        jLabel2.setText("Nome da  Marca:");

        l_nameBrand.setText("nomeMarca");

        jLabel3.setText("Nome do Modelo:");

        l_nameModel.setText("nomeMarca");

        jLabel4.setText("ID Modelo:");

        l_idModel.setText("idmodelo");

        bt_edit_model.setText("Editar Modelo");

        bt_edit_brand.setText("Editar Marca");

        bt_info.setText("Info");

        jLabel5.setText("Informação da marca automóve n.º:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_nameModel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_info)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_edit_brand)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_edit_model))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_idModel)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_nameBrand))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_idBrand)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(l_idBrand))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(l_nameBrand))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(l_idModel))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bt_edit_model)
                        .addComponent(bt_edit_brand)
                        .addComponent(bt_info))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(l_nameModel)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(627, 602));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_edit_brand;
    private javax.swing.JButton bt_edit_model;
    private javax.swing.JButton bt_info;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel l_idBrand;
    private javax.swing.JLabel l_idModel;
    private javax.swing.JLabel l_nameBrand;
    private javax.swing.JLabel l_nameModel;
    private javax.swing.JTable tbl_model;
    // End of variables declaration//GEN-END:variables
}
