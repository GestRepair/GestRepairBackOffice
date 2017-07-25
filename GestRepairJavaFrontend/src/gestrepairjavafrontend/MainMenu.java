/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestrepairjavafrontend;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rui Barcelos
 */
public final class MainMenu extends javax.swing.JFrame {

    /**
     * Creates new form MainMenu
     * @param login
     * @param dados
     */
    public MainMenu(String login,String dados) {
        initComponents();
        setMenu(login, dados);
        //System.out.println(login);
        //System.out.println(dados);
        setLogin(login);
    }
    private String login;
    public void setLogin(String log){
        this.login= log;
    }
    public String getLogin(){
        return login;
    };
    public void setMenu(String login,String dados){
        JSONParser parser = new JSONParser();
            try{
                JSONObject newjson = (JSONObject)new JSONParser().parse(dados);
                //String result = newjson.get("result").toString();
                //System.out.println(result);
                String data = newjson.get("data").toString();
                //System.out.println(data);
                JSONObject newjsondata = (JSONObject)new JSONParser().parse(data);
                l_nome.setText(newjsondata.get("name").toString());
                l_funcao.setText(newjsondata.get("nameRole").toString());
              
            }catch(ParseException pe){
                System.out.println("Erro");
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

        bt_utilizadores = new javax.swing.JButton();
        bt_veiculos = new javax.swing.JButton();
        l_utilizadores = new javax.swing.JLabel();
        bt_rep_execucao = new javax.swing.JButton();
        bt_rep_historico = new javax.swing.JButton();
        bt_stock = new javax.swing.JButton();
        bt_encomendas = new javax.swing.JButton();
        l_reparacoes = new javax.swing.JLabel();
        l_Peças = new javax.swing.JLabel();
        bt_orc_execucao = new javax.swing.JButton();
        l_orcamento = new javax.swing.JLabel();
        bt_orc_aprovados = new javax.swing.JButton();
        bt_orc_naoaprov = new javax.swing.JButton();
        bt_orc_historico = new javax.swing.JButton();
        l_id_nome = new javax.swing.JLabel();
        l_id_funcao = new javax.swing.JLabel();
        l_nome = new javax.swing.JLabel();
        l_funcao = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        l_reparacoes1 = new javax.swing.JLabel();
        bt_add_service = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bt_utilizadores.setText("Utilizadores");
        bt_utilizadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_utilizadoresActionPerformed(evt);
            }
        });

        bt_veiculos.setText("Veiculos");

        l_utilizadores.setText("Utilizadores");

        bt_rep_execucao.setText("Execução");

        bt_rep_historico.setText("Histórico");

        bt_stock.setText("Stock");
        bt_stock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_stockActionPerformed(evt);
            }
        });

        bt_encomendas.setText("Encomendas");

        l_reparacoes.setText("Reparações");

        l_Peças.setText("Peças");

        bt_orc_execucao.setText("Execução");
        bt_orc_execucao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_orc_execucaoActionPerformed(evt);
            }
        });

        l_orcamento.setText("Orçamento");

        bt_orc_aprovados.setText("Aprovados");

        bt_orc_naoaprov.setText("Não Aprovados");

        bt_orc_historico.setText("Histórico");

        l_id_nome.setText("Nome:");

        l_id_funcao.setText("Função:");

        l_nome.setText("Exemplo de Nome");

        l_funcao.setText("Função");

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel1.setText("GEST REPAIR");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/imageedit_4_8303763918.png"))); // NOI18N

        l_reparacoes1.setText("Serviço");

        bt_add_service.setText("Adicionar Serviços");
        bt_add_service.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_add_serviceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(l_id_nome)
                        .addGap(18, 18, 18)
                        .addComponent(l_nome))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(l_orcamento)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(bt_orc_aprovados, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(bt_orc_execucao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(bt_orc_naoaprov, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(bt_orc_historico, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(l_utilizadores)
                            .addComponent(bt_veiculos, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_utilizadores, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(l_id_funcao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(l_funcao)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(80, 80, 80)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(l_Peças)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(bt_encomendas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bt_stock, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(l_reparacoes)
                                .addComponent(bt_rep_execucao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(bt_rep_historico, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(l_reparacoes1)
                        .addComponent(bt_add_service, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(l_reparacoes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_rep_execucao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_rep_historico)
                        .addGap(18, 18, 18)
                        .addComponent(l_reparacoes1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_add_service)
                        .addGap(82, 82, 82)
                        .addComponent(l_Peças)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_stock)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_encomendas))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(l_utilizadores)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_utilizadores)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_veiculos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(l_orcamento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_orc_execucao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_orc_aprovados)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_orc_naoaprov)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_orc_historico)
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(l_id_nome)
                            .addComponent(l_nome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(l_id_funcao)
                            .addComponent(l_funcao))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_utilizadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_utilizadoresActionPerformed
        try {
            // TODO add your handling code here:
            new tableUsers(getLogin()).setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_bt_utilizadoresActionPerformed

    private void bt_stockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_stockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_stockActionPerformed

    private void bt_orc_execucaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_orc_execucaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_orc_execucaoActionPerformed

    private void bt_add_serviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_add_serviceActionPerformed
        // TODO add your handling code here:
        new CreateServiceIO(getLogin()).setVisible(true);
    }//GEN-LAST:event_bt_add_serviceActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_add_service;
    private javax.swing.JButton bt_encomendas;
    private javax.swing.JButton bt_orc_aprovados;
    private javax.swing.JButton bt_orc_execucao;
    private javax.swing.JButton bt_orc_historico;
    private javax.swing.JButton bt_orc_naoaprov;
    private javax.swing.JButton bt_rep_execucao;
    private javax.swing.JButton bt_rep_historico;
    private javax.swing.JButton bt_stock;
    private javax.swing.JButton bt_utilizadores;
    private javax.swing.JButton bt_veiculos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel l_Peças;
    private javax.swing.JLabel l_funcao;
    private javax.swing.JLabel l_id_funcao;
    private javax.swing.JLabel l_id_nome;
    private javax.swing.JLabel l_nome;
    private javax.swing.JLabel l_orcamento;
    private javax.swing.JLabel l_reparacoes;
    private javax.swing.JLabel l_reparacoes1;
    private javax.swing.JLabel l_utilizadores;
    // End of variables declaration//GEN-END:variables
}
