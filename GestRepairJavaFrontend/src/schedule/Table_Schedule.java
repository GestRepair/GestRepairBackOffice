/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedule;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static javax.xml.bind.DatatypeConverter.parseInt;

/**
 *
 * @author Rui Barcelos
 */
public final class Table_Schedule extends javax.swing.JFrame {

    /**
     * Creates new form Table_Schedule
     *
     * @param login
     * @throws java.lang.Exception
     *
     */
    public Table_Schedule(String login) throws Exception {
        APISchedule api = new APISchedule();
        initComponents();
        ShowDate(login, api);
        Events(login, api);
        int i = cbVal(cb_date.getSelectedIndex(), api.ListDays(login));
        showTable(api.ListTime(login, i));

    }

    /**
     * Insere os dados na tabela
     *
     * @param list
     */
    private void showTable(String[][] list) {
        if (list.length > 0) {
            DefaultTableModel mod = (DefaultTableModel) tbl_schedule.getModel();
            Object[] row = new Object[4];
            for (String[] list1 : list) {
                for (int i = 0; i < row.length; i++) {
                    row[i] = list1[i];
                }
                mod.addRow(row);
            }
            bt_info.setVisible(true);
            l_idSchedule.setText((String) mod.getValueAt(0, 0));
            tbl_schedule.setRowSelectionInterval(0, 0);
        } else {
            bt_info.setVisible(false);
            JOptionPane.showMessageDialog(this, "Sem dados");
        }
    }

    /**
     * Insere as Datas numa combobox
     *
     * @param login
     * @param api
     * @throws Exception
     */
    private void ShowDate(String login, APISchedule api) throws Exception {
        cb_date.removeAllItems();
        String[][] data = api.ListDays(login);
        for (int i = 0; i < data.length; i++) {
            cb_date.addItem(data[i][1]);
        }
    }

    /**
     * Atravez da combobox vai buscar o id
     *
     * @param val
     * @param data
     * @return
     */
    private int cbVal(int val, String[][] data) {
        return parseInt(data[val][0]);
    }

    /**
     * Atravez do click vai-se buscar o id
     *
     * @param evt
     * @param login
     */
    private void tbl_scheduleClick(java.awt.event.MouseEvent evt, String login) {
        int i = tbl_schedule.getSelectedRow();
        TableModel mod = tbl_schedule.getModel();
        l_idSchedule.setText((String) mod.getValueAt(i, 0));
    }

    /**
     * Limpa os dados da tabela
     */
    private void cleanTable() {
        DefaultTableModel mod = (DefaultTableModel) tbl_schedule.getModel();
        mod.setRowCount(0);
    }

    /**
     * Aqui são definido os eventos
     *
     * @param login
     * @param api
     */
    private void Events(final String login, final APISchedule api) {
        tbl_schedule.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_scheduleClick(evt, login);
            }
        });
        cb_date.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_LIST(evt, login, api);
            }
        });
        bt_info.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_INFO(evt, login);
            }
        });
        bt_disable.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_DISABLE(evt, login, api);
            }
        });
    }

    /**
     * Após a confirmação do utilizador a marcação é desativada
     *
     * @param evt
     * @param login
     * @param api
     */
    private void BT_DISABLE(java.awt.event.ActionEvent evt, String login, APISchedule api) {
        DefaultTableModel mod = (DefaultTableModel) tbl_schedule.getModel();
        int i = tbl_schedule.getSelectedRow();
        int val = parseInt((String) mod.getValueAt(i, 0));
        try {
            int x = JOptionPane.showConfirmDialog(this, "Tem a ceteza que quer atualizar os dados?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                String value[] = api.GETDisable(login, val);
                JOptionPane.showMessageDialog(this, value[1]);
                if ("ok".equals(value[0])) {
                    dispose();
                }
            } else if (x == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(this, "O agendamento não foi atualizado!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro a agendar!");
        }
    }

    /**
     * Mostra a form que mostra os detalhes do serviço
     *
     * @param evt
     * @param login
     */
    private void BT_INFO(java.awt.event.ActionEvent evt, String login) {
        try {
            DefaultTableModel mod = (DefaultTableModel) tbl_schedule.getModel();
            int i = tbl_schedule.getSelectedRow();
            int val = parseInt((String) mod.getValueAt(i, 0));
            new InfoSchedule(login, val).setVisible(true);
            dispose();
        } catch (Exception ex) {

        }
    }

    /**
     * Mostra a tablela consoante a escolha da dropdown
     *
     * @param evt
     * @param login
     * @param api
     */
    private void CB_LIST(java.awt.event.ActionEvent evt, String login, APISchedule api) {
        try {
            int i = cbVal(cb_date.getSelectedIndex(), api.ListDays(login));
            String[][] data = api.ListTime(login, i);
            cleanTable();
            showTable(data);
        } catch (Exception ex) {

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
        tbl_schedule = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        l_idSchedule = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cb_date = new javax.swing.JComboBox<>();
        bt_info = new javax.swing.JButton();
        bt_disable = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Lista de Marcações");
        setResizable(false);

        tbl_schedule.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Veículo", "Serviço", "Data"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl_schedule);
        tbl_schedule.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jLabel7.setText("Agendamento  N.º");

        l_idSchedule.setText("agendamento");

        jLabel1.setText("Selecione a data do serviço em que pretende ver as marcações:");

        cb_date.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        bt_info.setText("Info");

        bt_disable.setText("Desativar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(l_idSchedule)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_disable)
                        .addGap(18, 18, 18)
                        .addComponent(bt_info)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cb_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_info)
                    .addComponent(jLabel7)
                    .addComponent(l_idSchedule)
                    .addComponent(bt_disable))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1216, 639));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_disable;
    private javax.swing.JButton bt_info;
    private javax.swing.JComboBox<String> cb_date;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel l_idSchedule;
    private javax.swing.JTable tbl_schedule;
    // End of variables declaration//GEN-END:variables
}
