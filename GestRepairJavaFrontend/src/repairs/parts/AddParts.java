/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repairs.parts;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static javax.xml.bind.DatatypeConverter.parseInt;
import parts.APIParts;

/**
 *
 * @author Rui Barcelos
 */
public final class AddParts extends javax.swing.JFrame {

    /**
     * Creates new form AddRepair
     *
     * @param login
     * @param idRepair
     * @param idService
     * @throws java.lang.Exception
     */
    public AddParts(final String login, final int idRepair, final int idService) throws Exception {
        APIPartsRepair api = new APIPartsRepair();
        APIParts apiparts = new APIParts();
        initComponents();
        l_idRepair.setText(idRepair + "");
        showService(apiparts.ListPartswhith(login));
        Events(login, idRepair, idService, apiparts, api);
        showTableParts(login, apiparts);
    }

    /**
     * Adiciona os a informação à dropdown
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
     * Vai inserir os ids quando se seleciona
     *
     * @param val
     * @param list
     * @return
     */
    private int Cb_Val(int val, String[][] list) {
        return parseInt(list[val][0]);
    }

    /**
     * Mostra as peças na tabela
     *
     * @param login
     * @param apiparts
     * @throws Exception
     */
    private void showTableParts(String login, APIParts apiparts) throws Exception {
        String data[][] = apiparts.ListPartsZero(login, Cb_Val(cb_service.getSelectedIndex(), apiparts.ListPartswhith(login)));
        showTable(data);
    }

    /**
     * Mostra os dados na tabela
     *
     * @param list
     */
    private void showTable(String[][] list) {
        DefaultTableModel mod = (DefaultTableModel) tbl_parts.getModel();
        Object[] row = new Object[2];
        for (String[] list1 : list) {
            for (int i = 0; i < row.length; i++) {
                row[i] = list1[i];
            }
            mod.addRow(row);
        }
    }

    /**
     * Aqui é definido os eventos
     *
     * @param login
     * @param idRepair
     * @param idService
     * @param apiparts
     * @param api
     */
    private void Events(final String login, final int idRepair, final int idService, final APIParts apiparts, final APIPartsRepair api) {
        bt_add.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    BT_AddPart(evt, login, idRepair, idService, api);
                } catch (Exception ex) {
                    Logger.getLogger(AddParts.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        cb_service.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    CB_SERVICE(evt, login, apiparts);
                } catch (Exception ex) {
                    Logger.getLogger(AddParts.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    /**
     * Limpa a tabela
     */
    public void cleanTable() {
        DefaultTableModel mod = (DefaultTableModel) tbl_parts.getModel();
        mod.setRowCount(0);
    }

    /**
     * Limpa a Tabela e insere os dados caso da tabela na combobox
     *
     * @param evt
     * @param login
     * @param apiparts
     * @throws Exception
     */
    private void CB_SERVICE(java.awt.event.ActionEvent evt, String login, APIParts apiparts) throws Exception {
        cleanTable();
        showTableParts(login, apiparts);
    }

    /**
     * Prepara os dados para enviar
     *
     * @return
     */
    private int sendData() {
        DefaultTableModel mod = (DefaultTableModel) tbl_parts.getModel();
        int i = tbl_parts.getSelectedRow();
        int val = parseInt((String) mod.getValueAt(i, 0));
        return val;
    }

    /**
     * Envia a confirmação e se for aceite adiciona a peça à reparação
     *
     * @param evt
     * @param login
     * @param idRepair
     * @param idService
     * @param api
     */
    private void BT_AddPart(java.awt.event.ActionEvent evt, String login, int idRepair, int idService, APIPartsRepair api) {
        try {
            int x = JOptionPane.showConfirmDialog(this, "Tem a ceteza que quer inserir os dados?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                String value[] = api.PostParts(login, idRepair, sendData());
                JOptionPane.showMessageDialog(this, value[1]);
                if ("ok".equals(value[0])) {
                    new ListPartsRepair(login, idRepair, idService).setVisible(true);
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
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cb_service = new javax.swing.JComboBox();
        bt_add = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        l_idRepair = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_parts = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Adicionar peça à reparação");
        setResizable(false);

        jLabel1.setText("Serviço:");

        cb_service.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        bt_add.setText("Adicionar");

        jLabel4.setText("ID:");

        l_idRepair.setText("reparação");

        jLabel2.setText("Selecione um peça para adicionar à reparação:");

        tbl_parts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Peça"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl_parts);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(l_idRepair)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(cb_service, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bt_add)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 6, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(l_idRepair))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_service)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_add)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(494, 414));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_add;
    private javax.swing.JComboBox cb_service;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel l_idRepair;
    private javax.swing.JTable tbl_parts;
    // End of variables declaration//GEN-END:variables
}
