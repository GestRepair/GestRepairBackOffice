/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import static java.lang.Integer.parseInt;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Rui Barcelos
 */
public final class Table_Services extends javax.swing.JFrame {

    

    /**
     * Creates new form Table_Vehicles
     *
     * @param login
     * @throws java.io.IOException
     * @throws java.net.MalformedURLException
     * @throws org.json.simple.parser.ParseException
     * @throws java.text.ParseException
     */
    public Table_Services(String login) throws Exception {
        APIService api = new APIService();
        initComponents();
        showTable(api.Service(login));
        Events (login);
    }

    public void showTable(String[][] list) {
        DefaultTableModel mod = (DefaultTableModel) tbl_schedule.getModel();
        Object[] row = new Object[5];
        for (String[] list1 : list) {
            for (int i = 0; i < row.length; i++) {
                row[i] = list1[i];
            }
            mod.addRow(row);
        }
    }
    private int SelectedRow(int val){
        int i = tbl_schedule.getSelectedRow();
        TableModel mod = tbl_schedule.getModel();
        return parseInt((String) mod.getValueAt(i, val));
    }
    private void TBL_ScheduleClicked(java.awt.event.MouseEvent evt) {                                          
        int i = tbl_schedule.getSelectedRow();
        TableModel mod = tbl_schedule.getModel();
        l_idService.setText((String) mod.getValueAt(i, 0));
    }                                         

    private void BT_Edit(java.awt.event.ActionEvent evt,String login) {                                        
        try {
            new EditService(login,SelectedRow(0)).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(Table_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
    private void BT_Info(java.awt.event.ActionEvent evt,String login) {                                        
        try {
            new InfoService(login,SelectedRow(0)).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(Table_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
    private void Events (final String login){
        bt_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Edit(evt,login);
            }
        });
        bt_info.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_Info(evt,login);
            }
        });
        tbl_schedule.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBL_ScheduleClicked(evt);
            }
        });
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
        tbl_schedule = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        l_idService = new javax.swing.JLabel();
        bt_edit = new javax.swing.JButton();
        bt_info = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Lista de Serviços");
        setResizable(false);

        tbl_schedule.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Serviço", "Preço Mínimo", "Descrição", "Fotografia"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_schedule.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(tbl_schedule);
        tbl_schedule.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jLabel7.setText("Serviço  N.º");

        l_idService.setText("serviço");

        bt_edit.setText("Editar");

        bt_info.setText("Info");

        jLabel1.setText("Lista de Serviços");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(l_idService)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_info)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bt_edit))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_edit)
                    .addComponent(bt_info)
                    .addComponent(jLabel7)
                    .addComponent(l_idService))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1216, 639));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_edit;
    private javax.swing.JButton bt_info;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel l_idService;
    private javax.swing.JTable tbl_schedule;
    // End of variables declaration//GEN-END:variables
}
