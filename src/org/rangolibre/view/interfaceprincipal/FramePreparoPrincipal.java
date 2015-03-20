/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rangolibre.view.interfaceprincipal;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import org.rangolibre.control.ControlePreparacao;
import org.rangolibre.model.PedidoCardapio;
import org.rangolibre.view.LineSelectionTableCellrender;

/**
 *
 * @author aleaguado
 */
public class FramePreparoPrincipal extends javax.swing.JInternalFrame {
    Principal p;
    ControlePreparacao controlePreparo = new ControlePreparacao();
    ArrayList<PedidoCardapio> lista;
    Object[][] dados;

    /**
     * Creates new form FramePreparoPrincipal
     */
    public FramePreparoPrincipal(Principal pr) {
        p = pr;
        initComponents();
        carregarTabela(comporFiltro());
    }
    
    private void carregarTabela(String filtro){      
        try {
            
        //lista.clear();

        lista = controlePreparo.obterListaCardapio(filtro);
        
        String[] colunas = new String[]{"TIPO", "DESCRIÇÃO", "PONTOCARNE", "OBSERVAÇÃO", "MESA", "CLIENTE", "GARÇON"};
 
            dados = new String[lista.size()][7];
    
            for (int i = 0; i < lista.size(); i++) {
                dados[i][0] = lista.get(i).getItemCard().getTipo();
                dados[i][1] = lista.get(i).getItemCard().getDescCard();
                dados[i][2] = lista.get(i).getItemCard().getPonto();
                dados[i][3] = lista.get(i).getObservacao();
                dados[i][4] = lista.get(i).getCliente().getPedido().getMesa().getId();
                dados[i][5] = lista.get(i).getCliente().getNomeCliente();
                dados[i][6] = lista.get(i).getCriador().getId();

            }
                DefaultTableModel model = new DefaultTableModel(dados , colunas );
                
                tabelaPreparo.setModel(model);
                
                TableCellRenderer renderer = new LineSelectionTableCellrender();  
            for (int c = 0; c < tabelaPreparo.getColumnCount(); c++) {  
                tabelaPreparo.setDefaultRenderer(tabelaPreparo.getColumnClass(c), renderer);  
            }
            
            
            
            
        adjustJTableRowSizes(tabelaPreparo);
        for (int i = 0; i < tabelaPreparo.getColumnCount(); i++) {
            adjustColumnSizes(tabelaPreparo, i, 2);
        }
            
        tabelaPreparo.setSelectionMode(0); //somente uma linha pode ser selecionada 
 
        
         }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }  
    }
    
    
    private String comporFiltro(){
        String compondo;
        compondo = "";
        ArrayList<String> list = new ArrayList<String>();

        if (checkBebidas.isSelected()){
           list.add("'BEBIDAS'");
        }
        if (checkCarnes.isSelected()){
          list.add("'PRATOCARNE'");
        }
        if (checkEmpanadas.isSelected()){
           list.add("'EMPANADAS'");
        }        
        if (checkPratos.isSelected()){
           list.add("'PRATOS'");
        }              
        if (checkSobremesas.isSelected()){
           list.add("'SOBREMESAS'");
        }             
        
        for (int i = 0; i < list.size(); i++) {
            compondo = compondo + list.get(i);
            if (i < (list.size() - 1) ) {
                compondo = compondo + ", ";
            }
        }
        
        
        if (compondo.equals(""))
            compondo = "'PAPIBAQUIGRAFO'";

        return compondo;
    }
    
    
    private static void adjustJTableRowSizes(JTable jTable) {
        for (int row = 0; row < jTable.getRowCount(); row++) {
            int maxHeight = 0;
            for (int column = 0; column < jTable.getColumnCount(); column++) {
                TableCellRenderer cellRenderer = jTable.getCellRenderer(row, column);
                Object valueAt = jTable.getValueAt(row, column);
                Component tableCellRendererComponent = cellRenderer.getTableCellRendererComponent(jTable, valueAt, false, false, row, column);
                int heightPreferable = tableCellRendererComponent.getPreferredSize().height;
                maxHeight = Math.max(heightPreferable, maxHeight);
            }
            jTable.setRowHeight(row, maxHeight);
        }

    }

    public static void adjustColumnSizes(JTable table, int column, int margin) {
        DefaultTableColumnModel colModel = (DefaultTableColumnModel) table.getColumnModel();
        TableColumn col = colModel.getColumn(column);
        int width;

        TableCellRenderer renderer = col.getHeaderRenderer();
        if (renderer == null) {
            renderer = table.getTableHeader().getDefaultRenderer();
        }
        Component comp = renderer.getTableCellRendererComponent(
                table, col.getHeaderValue(), false, false, 0, 0);
        width = comp.getPreferredSize().width;

        for (int r = 0; r < table.getRowCount(); r++) {
            renderer = table.getCellRenderer(r, column);
            comp = renderer.getTableCellRendererComponent(
                    table, table.getValueAt(r, column), false, false, r, column);
            int currentWidth = comp.getPreferredSize().width;
            width = Math.max(width, currentWidth);
        }

        width += 2 * margin;

        col.setPreferredWidth(width + 5);
    }    
    
    
    
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelSuperior = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        checkBebidas = new javax.swing.JCheckBox();
        checkEmpanadas = new javax.swing.JCheckBox();
        checkPratos = new javax.swing.JCheckBox();
        checkCarnes = new javax.swing.JCheckBox();
        checkSobremesas = new javax.swing.JCheckBox();
        painelPrincipal = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaPreparo = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Preparo");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        painelSuperior.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel1.setText("Categoria:");

        checkBebidas.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        checkBebidas.setText("Bebidas");
        checkBebidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBebidasActionPerformed(evt);
            }
        });

        checkEmpanadas.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        checkEmpanadas.setText("Empanadas");
        checkEmpanadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkEmpanadasActionPerformed(evt);
            }
        });

        checkPratos.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        checkPratos.setText("Pratos");
        checkPratos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkPratosActionPerformed(evt);
            }
        });

        checkCarnes.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        checkCarnes.setText("Carnes");
        checkCarnes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCarnesActionPerformed(evt);
            }
        });

        checkSobremesas.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        checkSobremesas.setText("Sobremesas");
        checkSobremesas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkSobremesasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelSuperiorLayout = new javax.swing.GroupLayout(painelSuperior);
        painelSuperior.setLayout(painelSuperiorLayout);
        painelSuperiorLayout.setHorizontalGroup(
            painelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelSuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(checkBebidas)
                .addGap(30, 30, 30)
                .addComponent(checkEmpanadas)
                .addGap(42, 42, 42)
                .addComponent(checkPratos)
                .addGap(30, 30, 30)
                .addComponent(checkCarnes)
                .addGap(18, 18, 18)
                .addComponent(checkSobremesas)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        painelSuperiorLayout.setVerticalGroup(
            painelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelSuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(checkBebidas)
                    .addComponent(checkEmpanadas)
                    .addComponent(checkPratos)
                    .addComponent(checkCarnes)
                    .addComponent(checkSobremesas))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        painelPrincipal.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tabelaPreparo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelaPreparo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabelaPreparoKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaPreparo);

        javax.swing.GroupLayout painelPrincipalLayout = new javax.swing.GroupLayout(painelPrincipal);
        painelPrincipal.setLayout(painelPrincipalLayout);
        painelPrincipalLayout.setHorizontalGroup(
            painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        painelPrincipalLayout.setVerticalGroup(
            painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelSuperior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(painelPrincipal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(painelSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing

        // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameClosing

    private void checkBebidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBebidasActionPerformed
        carregarTabela(comporFiltro());
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBebidasActionPerformed

    private void checkEmpanadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkEmpanadasActionPerformed
        carregarTabela(comporFiltro());
        // TODO add your handling code here:
    }//GEN-LAST:event_checkEmpanadasActionPerformed

    private void checkPratosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkPratosActionPerformed
        carregarTabela(comporFiltro());
        // TODO add your handling code here:
    }//GEN-LAST:event_checkPratosActionPerformed

    private void checkCarnesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkCarnesActionPerformed
        carregarTabela(comporFiltro());
        // TODO add your handling code here:
    }//GEN-LAST:event_checkCarnesActionPerformed

    private void checkSobremesasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkSobremesasActionPerformed
        carregarTabela(comporFiltro());
        // TODO add your handling code here:
    }//GEN-LAST:event_checkSobremesasActionPerformed

    private void tabelaPreparoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelaPreparoKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER){
            int i = tabelaPreparo.getSelectedRow();
            preparar(lista.get(i));
            evt.consume();
}
        
// TODO add your handling code here:
    }//GEN-LAST:event_tabelaPreparoKeyPressed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        p.btPreparo.setBackground(null);
        p.cPreparo = false;
        
// TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameClosed

    private void preparar(PedidoCardapio pedicoCardapio){
        try {
            
            if ( JOptionPane.showConfirmDialog(null, "Confirma preparação do item?") == 0) {
                     pedicoCardapio.setPreparador(p.usuario);
                     pedicoCardapio = controlePreparo.prepararItem(pedicoCardapio); 
                     carregarTabela(comporFiltro());
            }    else {
                    
                    return;
                }

         }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }    
    }
    
    
    
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkBebidas;
    private javax.swing.JCheckBox checkCarnes;
    private javax.swing.JCheckBox checkEmpanadas;
    private javax.swing.JCheckBox checkPratos;
    private javax.swing.JCheckBox checkSobremesas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel painelPrincipal;
    private javax.swing.JPanel painelSuperior;
    private javax.swing.JTable tabelaPreparo;
    // End of variables declaration//GEN-END:variables
}
