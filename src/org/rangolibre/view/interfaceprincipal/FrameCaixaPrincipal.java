/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rangolibre.view.interfaceprincipal;

import java.awt.Component;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import org.rangolibre.control.ControleCaixa;
import org.rangolibre.control.ControleMesa;
import org.rangolibre.control.ControlePedido;
import org.rangolibre.model.Cliente;
import org.rangolibre.model.ItemEmAberto;
import org.rangolibre.model.Mesa;
import org.rangolibre.model.Pedido;
import org.rangolibre.view.LineSelectionTableCellrender;
import org.rangolibre.view.LineSelectionTableCellrenderCaixa;
import static org.rangolibre.view.interfaceprincipal.FramePreparoPrincipal.adjustColumnSizes;

/**
 *
 * @author aleaguado
 */
public class FrameCaixaPrincipal extends javax.swing.JInternalFrame {
    Principal p;
        ControleMesa controleMesa = new ControleMesa();
        Cliente clienteCorrente = new Cliente();
        ArrayList<Cliente> listaClientes;
        ControlePedido controlePedido = new ControlePedido();
        ControleCaixa controleCaixa = new ControleCaixa();
        Object[][] dados;
        Object[][] dadosItens;
        ArrayList<ItemEmAberto> listaItens;
        ArrayList<ItemEmAberto> listaItensParaPagamento = new ArrayList<ItemEmAberto>();
        DefaultTableModel model;
        DefaultTableModel modeloItens;
    /**
     * Creates new form FrameCaixaPrincipal
     */
    public FrameCaixaPrincipal(Principal pr) {
        p = pr;
        initComponents();
        configuracaoInicial();
    }
    
    public FrameCaixaPrincipal(){}
    
    private void configuracaoInicial(){
        ButtonGroup btPagamento = new ButtonGroup();
        btPagamento.add(radioPgtoCliente);
        btPagamento.add(radioPgtoItem);
        btPagamento.add(radioPgtoMesa);
        
        desabilitarGeral();
   
                //Aproveito para criar um listener para o combo cliente
        comboCliente.addItemListener(new java.awt.event.ItemListener() {
   
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                trocaCliente();
             //o tratamento a esse evento aqui.
            //ele dispara duas vezes a cada item selecionado, 
            //um para o que foi DESELECTED e outro para o SELECTED por isso a comparação.
        }
   } 

});     
    }
    
    private void habilitarBotoesPagamento(){
        textoRecebido.setEnabled(true);
        textoTroco.setEnabled(true);
        textoTotal.setEnabled(true);
        btPagar.setEnabled(true);
    }
    
    private void carregarTotal(){
        if (!radioPgtoItem.isSelected()) {
            Double total = 0.00;
            for (int i = 0; i < listaItens.size(); i++) {
                total = total + listaItens.get(i).getPrecoTotal();
            }
            textoTotal.setValue(total);       
        } else {
            if (listaItensParaPagamento == null) {
                return;
            }
            
            Double total = 0.00;
            for (int i = 0; i < listaItensParaPagamento.size(); i++) {
                total = total + listaItensParaPagamento.get(i).getPrecoTotal();
        }
             textoTotal.setValue(total);  
    }
    }
    
    private void trocaCliente(){
        
         if (!clienteCorrente.getNomeCliente().trim().equals(comboCliente.getSelectedItem().toString().trim())) {
               for (int i = 0; i < listaClientes.size(); i++) {
                    if (listaClientes.get(i).getNomeCliente().equals(comboCliente.getSelectedItem().toString())) {
                        clienteCorrente = listaClientes.get(i);
                    }   
                }
        
        
        habilitarBotoesPagamento();
        carregarTabela();
        carregarTotal();        
    }
    
    }
    
        //Metodo para ser usado pelo FrameCaixaBuscaMesa
    public void carregarCampoMesa(String mesa){
        textoMesa.setText(mesa);
        textoMesa.requestFocus();
    }
    
    public void desabilitarGeral(){
        
        //Limpar seleção de mesa
        textoMesa.setText("");
        
        
        //Limpar combo de pedido
        textoPedido.setText("");
        textoPedido.setEnabled(false);
        
        //Limpar combo de cliente
        comboCliente.removeAllItems();
        comboCliente.setEnabled(false);
        
        //Desabilitar botoes radio
        radioPgtoCliente.setEnabled(false);
        radioPgtoItem.setEnabled(false);
        radioPgtoMesa.setEnabled(false);
        
        //desabilitar secao de pagamento
        textoRecebido.setValue(0.00);
        textoTroco.setValue(0.00);
        textoTotal.setValue(0.00);
        textoRecebido.setEnabled(false);
        textoTroco.setEnabled(false);
        textoTotal.setEnabled(false);
        btPagar.setEnabled(false);
        
        visibilidadeItensSeparados(false);
        
        //limpar tabela
        limparTabela();
        limparTabelaItens();
        
    }
    
        public void desabilitarInferior(){

        
        //desabilitar secao de pagamento
        textoRecebido.setValue(0.00);
        textoTroco.setValue(0.00);
        textoTotal.setValue(0.00);
        textoRecebido.setEnabled(false);
        textoTroco.setEnabled(false);
        textoTotal.setEnabled(false);
        btPagar.setEnabled(false);
        
        visibilidadeItensSeparados(false);
        
        //limpar tabela
        limparTabela();
        limparTabelaItens();
        
    }
        
    private void visibilidadeItensSeparados(boolean estado){
        btAdicionarItem.setEnabled(estado);
        btRemoverItem.setEnabled(estado);
        tabelaPgtoItens.setEnabled(estado);                
    }    
        
        private void limparTabela() {
            while (tabelaCaixa.getRowCount() > 0) {
                DefaultTableModel dm = (DefaultTableModel) tabelaCaixa.getModel();
                dm.getDataVector().removeAllElements();
            }
        }
    
         private void limparTabelaItens() {
            while (tabelaPgtoItens.getRowCount() > 0) {
                DefaultTableModel dm = (DefaultTableModel) tabelaPgtoItens.getModel();
                dm.getDataVector().removeAllElements();
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

        painelSuperiorPedido = new javax.swing.JPanel();
        labelPedido = new javax.swing.JLabel();
        textoMesa = new javax.swing.JTextField();
        btBuscar = new javax.swing.JButton();
        labelMesa = new javax.swing.JLabel();
        textoPedido = new javax.swing.JTextField();
        labelCliente = new javax.swing.JLabel();
        comboCliente = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        radioPgtoMesa = new javax.swing.JRadioButton();
        radioPgtoCliente = new javax.swing.JRadioButton();
        radioPgtoItem = new javax.swing.JRadioButton();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        painelGeral = new javax.swing.JPanel();
        painelPagamento = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        labeltroco = new javax.swing.JLabel();
        labelRecebido = new javax.swing.JLabel();
        btPagar = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        textoRecebido = new javax.swing.JFormattedTextField();
        textoTroco = new javax.swing.JFormattedTextField();
        textoTotal = new javax.swing.JFormattedTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaPgtoItens = new javax.swing.JTable();
        painelItens = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCaixa = new javax.swing.JTable();
        btAdicionarItem = new javax.swing.JButton();
        btRemoverItem = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Caixa");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
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

        painelSuperiorPedido.setBackground(javax.swing.UIManager.getDefaults().getColor("ComboBox.buttonShadow"));
        painelSuperiorPedido.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        labelPedido.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        labelPedido.setText("Pedido:");

        textoMesa.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        textoMesa.setForeground(new java.awt.Color(61, 53, 228));
        textoMesa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textoMesaFocusLost(evt);
            }
        });
        textoMesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoMesaActionPerformed(evt);
            }
        });

        btBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/rangolibre/img/botoes/24x24/001_38.png"))); // NOI18N
        btBuscar.setToolTipText("Busca de mesas");
        btBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarActionPerformed(evt);
            }
        });

        labelMesa.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        labelMesa.setText("Mesa:");

        textoPedido.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        textoPedido.setForeground(new java.awt.Color(61, 53, 228));

        labelCliente.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        labelCliente.setText("Cliente:");

        comboCliente.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        comboCliente.setForeground(new java.awt.Color(46, 90, 205));
        comboCliente.setToolTipText("Clientes na Mesa");
        comboCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboClienteActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        radioPgtoMesa.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        radioPgtoMesa.setText("Pagamento da mesa toda");
        radioPgtoMesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioPgtoMesaActionPerformed(evt);
            }
        });

        radioPgtoCliente.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        radioPgtoCliente.setText("Pagamento por cliente");
        radioPgtoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioPgtoClienteActionPerformed(evt);
            }
        });

        radioPgtoItem.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        radioPgtoItem.setText("Pagamento por seleção de itens");
        radioPgtoItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioPgtoItemActionPerformed(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout painelSuperiorPedidoLayout = new javax.swing.GroupLayout(painelSuperiorPedido);
        painelSuperiorPedido.setLayout(painelSuperiorPedidoLayout);
        painelSuperiorPedidoLayout.setHorizontalGroup(
            painelSuperiorPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelSuperiorPedidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelMesa)
                .addGap(6, 6, 6)
                .addComponent(textoMesa, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btBuscar)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelPedido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelSuperiorPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioPgtoMesa)
                    .addComponent(radioPgtoCliente)
                    .addComponent(radioPgtoItem))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(labelCliente)
                .addGap(18, 18, 18)
                .addComponent(comboCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelSuperiorPedidoLayout.setVerticalGroup(
            painelSuperiorPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(painelSuperiorPedidoLayout.createSequentialGroup()
                .addGroup(painelSuperiorPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelSuperiorPedidoLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(painelSuperiorPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelCliente)))
                    .addGroup(painelSuperiorPedidoLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(radioPgtoMesa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioPgtoCliente)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioPgtoItem)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(painelSuperiorPedidoLayout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addGroup(painelSuperiorPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(painelSuperiorPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(painelSuperiorPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textoMesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelMesa)))
                    .addGroup(painelSuperiorPedidoLayout.createSequentialGroup()
                        .addGroup(painelSuperiorPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelPedido))
                        .addGap(4, 4, 4)))
                .addGap(22, 22, 22))
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator3)
        );

        painelGeral.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        painelPagamento.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pagamento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel1.setText("TOTAL:");

        labeltroco.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        labeltroco.setText("Troco:");

        labelRecebido.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        labelRecebido.setText("Recebido:");

        btPagar.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btPagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/rangolibre/img/botoes/24x24/001_44.png"))); // NOI18N
        btPagar.setText("Efetuar Pagamento");
        btPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPagarActionPerformed(evt);
            }
        });

        textoRecebido.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        textoRecebido.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        textoRecebido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textoRecebidoFocusLost(evt);
            }
        });

        textoTroco.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        textoTroco.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N

        textoTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        textoTotal.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N

        tabelaPgtoItens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tabelaPgtoItens);

        javax.swing.GroupLayout painelPagamentoLayout = new javax.swing.GroupLayout(painelPagamento);
        painelPagamento.setLayout(painelPagamentoLayout);
        painelPagamentoLayout.setHorizontalGroup(
            painelPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPagamentoLayout.createSequentialGroup()
                .addGroup(painelPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelPagamentoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(painelPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator4)
                            .addGroup(painelPagamentoLayout.createSequentialGroup()
                                .addGroup(painelPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(painelPagamentoLayout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(labelRecebido)
                                        .addGap(18, 18, 18)
                                        .addComponent(textoRecebido, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(painelPagamentoLayout.createSequentialGroup()
                                        .addGap(62, 62, 62)
                                        .addComponent(labeltroco)
                                        .addGap(18, 18, 18)
                                        .addComponent(textoTroco, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(painelPagamentoLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(211, 211, 211)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelPagamentoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );
        painelPagamentoLayout.setVerticalGroup(
            painelPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelPagamentoLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelRecebido, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoRecebido, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(painelPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoTroco, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labeltroco, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        painelItens.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalhamento dos Pedidos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        tabelaCaixa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabelaCaixa);

        javax.swing.GroupLayout painelItensLayout = new javax.swing.GroupLayout(painelItens);
        painelItens.setLayout(painelItensLayout);
        painelItensLayout.setHorizontalGroup(
            painelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        painelItensLayout.setVerticalGroup(
            painelItensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelItensLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btAdicionarItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/rangolibre/img/botoes/24x24/001_21.png"))); // NOI18N
        btAdicionarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdicionarItemActionPerformed(evt);
            }
        });

        btRemoverItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/rangolibre/img/botoes/24x24/001_23.png"))); // NOI18N
        btRemoverItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoverItemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelGeralLayout = new javax.swing.GroupLayout(painelGeral);
        painelGeral.setLayout(painelGeralLayout);
        painelGeralLayout.setHorizontalGroup(
            painelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelGeralLayout.createSequentialGroup()
                .addComponent(painelItens, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btAdicionarItem)
                    .addComponent(btRemoverItem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelGeralLayout.setVerticalGroup(
            painelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelGeralLayout.createSequentialGroup()
                .addGroup(painelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelGeralLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(painelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(painelItens, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(painelPagamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(painelGeralLayout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(btAdicionarItem)
                        .addGap(18, 18, 18)
                        .addComponent(btRemoverItem)))
                .addContainerGap(81, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelSuperiorPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelGeral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(painelSuperiorPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelGeral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(227, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        p.cCaixa = false;
        p.btCaixa.setBackground(null);
        // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameClosing

    private void textoMesaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textoMesaFocusLost
        //Aqui dispara o carregar
        try {
            desabilitarInferior();
            if (textoMesa.getText().length() == 4) {
                Mesa mesaCorrente = controleMesa.ObterMesa(textoMesa.getText().trim());
                carregarDadosIniciais(mesaCorrente);
                textoMesa.setEnabled(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            desabilitarGeral();
        }

    }//GEN-LAST:event_textoMesaFocusLost

    private void carregarDadosIniciais(Mesa mesaCorrente){
        try{
            Pedido ped = controlePedido.obterPedidoMesa(mesaCorrente.getId()); 
            //Carregamos o cliente corrente
            clienteCorrente = controlePedido.obterCliente(ped.getId(), 0);
            listaClientes = controlePedido.obterClientes(ped);
            carregarPainelSuperior(ped);
            
           // BotoesTipoVisíveis(true);            
            
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());  
            desabilitarGeral();
        }    
    }
    
        public void carregarPainelSuperior(Pedido pedido){
        
        try {
        //Carrego o campo do pedido
        if (!textoPedido.isEnabled())
            textoPedido.setEnabled(true);
        if (!comboCliente.isEnabled())
            comboCliente.setEnabled(true);
        
        textoPedido.setText("");
        textoPedido.setText(pedido.getId().trim());
        textoPedido.setEnabled(false);
        
        carregarComboCliente();
            
         }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());  
            desabilitarGeral();
        }          
        
    }
        
            private void carregarComboCliente(){
            //Limpamos o combo antes de carregá-lo...
            comboCliente.removeAllItems();
        
            //E depois carregamos ...
            for (Cliente listaCliente : listaClientes) {
                comboCliente.addItem(listaCliente.getNomeCliente());
            }
            
            radioPgtoCliente.setEnabled(true);
            radioPgtoItem.setEnabled(true);
            radioPgtoMesa.setEnabled(true);
            radioPgtoMesa.setSelected(true);
            
            comboCliente.setSelectedItem("PADRAO");
            comboCliente.setEnabled(false);
            
            carregarTabela();
            carregarTotal();
            
            habilitarBotoesPagamento();
            
            
    
    }
            
        private void carregarTabela(){      
        try {
            
        //lista.clear();
        
        if (radioPgtoCliente.isSelected()) {
            listaItens = controleCaixa.obterTotais(null, clienteCorrente);
        } else {
            listaItens = controleCaixa.obterTotais(clienteCorrente.getPedido(), null);
        }
            
            
        
        String[] colunas = new String[]{"QTY", "ITEM", "PREÇO UNIDADE", "PREÇO TOTAL"};
 
            dados = new String[listaItens.size()][7];
    
            for (int i = 0; i < listaItens.size(); i++) {
                dados[i][0] = Integer.toString(listaItens.get(i).getQty());
                dados[i][1] = listaItens.get(i).getItem();
                dados[i][2] = Double.toString(listaItens.get(i).getPrecoUnidade());
                dados[i][3] = Double.toString(listaItens.get(i).getPrecoTotal());
            }
                model = new DefaultTableModel(dados , colunas );
                
                tabelaCaixa.setModel(model);
                
                TableCellRenderer renderer = new LineSelectionTableCellrenderCaixa();  
            for (int c = 0; c < tabelaCaixa.getColumnCount(); c++) {  
                tabelaCaixa.setDefaultRenderer(tabelaCaixa.getColumnClass(c), renderer);  
            }
            
  
        adjustJTableRowSizes(tabelaCaixa);
        for (int i = 0; i < tabelaCaixa.getColumnCount(); i++) {
            adjustColumnSizes(tabelaCaixa, i, 2);
        }
            
        tabelaCaixa.setSelectionMode(0); //somente uma linha pode ser selecionada 
 
        
         }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }  
    }        
            
 
private void popularTabelaItens(){
    String[] colunas = new String[]{"QTY", "ITEM", "PREÇO UNIDADE", "PREÇO TOTAL"};
    
    dadosItens = new String[listaItens.size()][7];
    
               modeloItens = new DefaultTableModel(dadosItens , colunas );
                
                tabelaPgtoItens.setModel(modeloItens);
                
                TableCellRenderer renderer = new LineSelectionTableCellrenderCaixa();  
                
            for (int c = 0; c < tabelaPgtoItens.getColumnCount(); c++) {  
                tabelaPgtoItens.setDefaultRenderer(tabelaPgtoItens.getColumnClass(c), renderer);  
            }
            
  
        adjustJTableRowSizes(tabelaPgtoItens);
        for (int i = 0; i < tabelaPgtoItens.getColumnCount(); i++) {
            adjustColumnSizes(tabelaPgtoItens, i, 2);
        }
            
        tabelaPgtoItens.setSelectionMode(0); //somente uma linha pode ser selecionada 

} 

private void adicionarItens(){
    int linha = tabelaCaixa.getSelectedRow();
        
   listaItensParaPagamento.add(listaItens.get(linha));
   int index = listaItensParaPagamento.size() - 1;
   if (listaItens.get(linha).getQty() > 1) {
       int qty = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade: "));
       listaItensParaPagamento.get(index).setQty(qty);
       listaItensParaPagamento.get(index).setPrecoTotal(qty*listaItensParaPagamento.get(index).getPrecoUnidade());
       modeloItens.addRow(new Object[]{listaItensParaPagamento.get(index).getQty(), listaItensParaPagamento.get(index).getItem(), listaItensParaPagamento.get(index).getPrecoUnidade(), listaItensParaPagamento.get(index).getPrecoTotal()});

   } else {
   modeloItens.addRow(new Object[]{tabelaCaixa.getValueAt(linha, 0), tabelaCaixa.getValueAt(linha, 1),  tabelaCaixa.getValueAt(linha, 2),  tabelaCaixa.getValueAt(linha, 3)});
   }
    
    
        adjustJTableRowSizes(tabelaPgtoItens);
        for (int i = 0; i < tabelaPgtoItens.getColumnCount(); i++) {
            adjustColumnSizes(tabelaPgtoItens, i, 2);
        }
        
        carregarTotal();
    
}
        
private void removerItens(){
    int linha = tabelaPgtoItens.getSelectedRow();
   // int index = tabelaCaixa.getSe
   listaItensParaPagamento.remove(linha);

  modeloItens.removeRow(linha);
    
        adjustJTableRowSizes(tabelaPgtoItens);
        for (int i = 0; i < tabelaPgtoItens.getColumnCount(); i++) {
            adjustColumnSizes(tabelaPgtoItens, i, 2);
        }
        
         carregarTotal();
    
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
            
    
    
    
    private void textoMesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoMesaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoMesaActionPerformed

    private void btBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarActionPerformed
        FrameCaixaBuscaMesa bm = new FrameCaixaBuscaMesa(this);
        p.addJanela(bm);
        bm.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btBuscarActionPerformed

    private void comboClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboClienteActionPerformed

    }//GEN-LAST:event_comboClienteActionPerformed

    private void textoRecebidoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textoRecebidoFocusLost
        try {
            textoRecebido.commitEdit();
            textoTotal.commitEdit();
            
            Double troco = Double.parseDouble(textoRecebido.getText().replace(",", ".")) - Double.parseDouble(textoTotal.getText().replace(",", "."));
            textoTroco.setValue(troco);
                    
        } catch (ParseException ex) {
            Logger.getLogger(FrameCaixaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
   // TODO add your handling code here:
    }//GEN-LAST:event_textoRecebidoFocusLost

    private void radioPgtoMesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioPgtoMesaActionPerformed
        if (radioPgtoMesa.isSelected()) {
            comboCliente.setSelectedItem("PADRAO");
            comboCliente.setEnabled(false);
            carregarTabela();
            carregarTotal();            
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_radioPgtoMesaActionPerformed

    private void radioPgtoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioPgtoClienteActionPerformed
        if (radioPgtoCliente.isSelected()) {
            comboCliente.setEnabled(true);
            comboCliente.requestFocus();
            carregarTabela();
            carregarTotal();        // TODO add your handling code here:
        }
    }//GEN-LAST:event_radioPgtoClienteActionPerformed

    private void radioPgtoItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioPgtoItemActionPerformed
        if (radioPgtoItem.isSelected()) {
            comboCliente.setSelectedItem("PADRAO");
            comboCliente.setEnabled(false);
            carregarTabela();
            carregarTotal();
            visibilidadeItensSeparados(true);
            popularTabelaItens();
            limparTabelaItens();
        }
    }//GEN-LAST:event_radioPgtoItemActionPerformed

    private void btPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPagarActionPerformed
        try {

            if ( JOptionPane.showConfirmDialog(null, "Deseja realmente efetuar este pagamento?") == 0) {
               if (radioPgtoMesa.isSelected()){
                   controleCaixa.pagarMesa(clienteCorrente.getPedido(), p.usuario);
                   JOptionPane.showMessageDialog(null, "Mesa " + clienteCorrente.getPedido().getMesa().getId() + " paga e liberada para uso!");
                   desabilitarGeral();
               }
               if (radioPgtoCliente.isSelected()){
                    controleCaixa.pagarCliente(clienteCorrente,p.usuario);  
                    JOptionPane.showMessageDialog(null, "Cliente " + clienteCorrente.getNomeCliente() + " pago!!!");
                    desabilitarGeral();
               }               
                if (radioPgtoItem.isSelected()){
                    for (int i = 0; i < listaItensParaPagamento.size(); i++) {
                        controleCaixa.pagarItem(listaItensParaPagamento.get(i).getPedido(),listaItensParaPagamento.get(i).getIdCliente(), listaItensParaPagamento.get(i).getIdItem(),p.usuario );
                    }
                    JOptionPane.showMessageDialog(null, "Itens pagos com sucesso!");
                    desabilitarGeral();                       
               }                
                 }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());  
            desabilitarGeral();
        } 
        
        
// TODO add your handling code here:
    }//GEN-LAST:event_btPagarActionPerformed

    private void btAdicionarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdicionarItemActionPerformed
        try { 
         //   if (modeloItens.contains(tabelaCaixa.getSelectedValue())){
         //       JOptionPane.showMessageDialog(null, "Ingrediente já adicionado!!!");
         //       return;
         //   }
            adicionarItens();
            
        } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());  
                }

// TODO add your handling code here:
    }//GEN-LAST:event_btAdicionarItemActionPerformed

    private void btRemoverItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoverItemActionPerformed
       try { 
 
            removerItens();
            
        } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());  
                }
        

// TODO add your handling code here:
    }//GEN-LAST:event_btRemoverItemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdicionarItem;
    private javax.swing.JButton btBuscar;
    private javax.swing.JButton btPagar;
    private javax.swing.JButton btRemoverItem;
    private javax.swing.JComboBox comboCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel labelCliente;
    private javax.swing.JLabel labelMesa;
    private javax.swing.JLabel labelPedido;
    private javax.swing.JLabel labelRecebido;
    private javax.swing.JLabel labeltroco;
    private javax.swing.JPanel painelGeral;
    private javax.swing.JPanel painelItens;
    private javax.swing.JPanel painelPagamento;
    private javax.swing.JPanel painelSuperiorPedido;
    private javax.swing.JRadioButton radioPgtoCliente;
    private javax.swing.JRadioButton radioPgtoItem;
    private javax.swing.JRadioButton radioPgtoMesa;
    private javax.swing.JTable tabelaCaixa;
    private javax.swing.JTable tabelaPgtoItens;
    private javax.swing.JTextField textoMesa;
    private javax.swing.JTextField textoPedido;
    private javax.swing.JFormattedTextField textoRecebido;
    private javax.swing.JFormattedTextField textoTotal;
    private javax.swing.JFormattedTextField textoTroco;
    // End of variables declaration//GEN-END:variables
}
