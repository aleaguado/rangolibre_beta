/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rangolibre.view.interfaceprincipal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ItemListener;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.rangolibre.control.ControleCaixa;
import org.rangolibre.control.ControleCardapio;
import org.rangolibre.control.ControleMesa;
import org.rangolibre.control.ControlePedido;
import org.rangolibre.model.Cliente;
import org.rangolibre.model.ItemCard;
import org.rangolibre.model.ItemEmAberto;
import org.rangolibre.model.Mesa;
import org.rangolibre.model.Pedido;
import org.rangolibre.model.PedidoCardapio;
import org.rangolibre.view.CardapioBotao;

/**
 *
 * @author aleaguado
 */
public class FramePedidoPrincipal extends javax.swing.JInternalFrame {

    Principal p;
    //Esses objetos guardarão o estado da tela ...
    ArrayList<PedidoCardapio> listaPedidoCardapio = new ArrayList<PedidoCardapio>() ;

    Cliente clienteCorrente = new Cliente();
    ArrayList<Cliente> listaClientes;
    //Controle Pedido é o cara da camada de controle
    ControlePedido controlePedido = new ControlePedido();
    ControleMesa controleMesa = new ControleMesa();
    ControleCaixa controleCaixa = new ControleCaixa();
    ControleCardapio controleCardapio = new ControleCardapio();
    CardapioBotao[] listaCardapioPadraoBotao;
    int tamanholistaP;
    CardapioBotao[] listaCardapioDerivadoBotao;
    int tamanholistaD;
    ItemCard cardapioSelecionado;
    
    
    //Botoes de Tipo
        JButton btT1 = new JButton("Bebidas");
        JButton btT2 = new JButton("Pratos");   
        JButton btT3 = new JButton("Carnes");
        JButton btT4 = new JButton("Empanadas");
        JButton btT5 = new JButton("Sobremesas");
     
    
    /**
     * Creates new form FramePedidoPrincipal
     */
    
    public FramePedidoPrincipal(){}

    
    
    public FramePedidoPrincipal(Principal pr) {
        p = pr;
        initComponents();
        desabilitarGeral();
        criarBotoesTipo();
    }
    
    public void desabilitarGeral(){
        //Vamos configurar alguns tamanhos:
        
        painelDireitoPedido.setSize((this.getSize().width - 370), (this.getSize().width - 75));
        painelSuperiorPedido.setBackground(Color.gray);
        textoMesa.setEnabled(true);
        textoPedido.setText("");
        textoPedido.setEnabled(false);
        comboCliente.removeAllItems();
        comboCliente.setEnabled(false);
        btAdicionarCliente.setEnabled(false);
        tabelaResumo.setEnabled(true);
        DefaultTableModel model = new DefaultTableModel();
        tabelaResumo.setModel(model);        
        tabelaResumo.setEnabled(false);     
        btSalvar.setEnabled(false);
        btCancelar.setEnabled(false);
        btExplodir.setEnabled(false);
        btAdicionar.setEnabled(false);
        btLimpar.setEnabled(false);
        BotoesTipoVisíveis(false);
        descricaoItemSelecionado.setEnabled(false);
        painelPadrao.removeAll();
        painelDerivado.removeAll();
        listaPedidoCardapio.clear();
        
        //Aproveito para criar um listener para o combo cliente
        comboCliente.addItemListener(new java.awt.event.ItemListener() {
   
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                clienteComboAcao();
             //o tratamento a esse evento aqui.
            //ele dispara duas vezes a cada item selecionado, 
            //um para o que foi DESELECTED e outro para o SELECTED por isso a comparação.
        }
   } 

});
}
    private void criarBotoesTipo(){
        painelCategoria.setLayout(new GridLayout(1,4));
        Icon iconeBebida = new ImageIcon(getClass().getResource("/org/rangolibre/img/botoes/bebida.png"));  
        Icon iconePrato = new ImageIcon(getClass().getResource("/org/rangolibre/img/botoes/prato.png"));  
        Icon iconeEmpanada = new ImageIcon(getClass().getResource("/org/rangolibre/img/botoes/empanada.png"));  
        Icon iconeSobremesa = new ImageIcon(getClass().getResource("/org/rangolibre/img/botoes/sobremesa.png"));  
        Icon iconeCarne = new ImageIcon(getClass().getResource("/org/rangolibre/img/botoes/pratocarne.png"));  

        //Adicionar Listener geral
        
        btT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoActionPerformed(evt);
            }
            });
        btT2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoActionPerformed(evt);
            }
            });
        btT3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoActionPerformed(evt);
            }
            });
        btT4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoActionPerformed(evt);
            }
            });        
        btT5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoActionPerformed(evt);
            }
            });  
        
        btT1.setIcon(iconeBebida);
        btT2.setIcon(iconePrato);
        btT3.setIcon(iconeCarne);
        btT4.setIcon(iconeEmpanada);
        btT5.setIcon(iconeSobremesa);

        painelCategoria.add(btT1);
        painelCategoria.add(btT2);
        painelCategoria.add(btT3);
        painelCategoria.add(btT4);
        painelCategoria.add(btT5);
        
        BotoesTipoVisíveis(false);
    
    }
    
    private void tipoActionPerformed(java.awt.event.ActionEvent evt){

        btT1.setBackground(null);
        btT2.setBackground(null);
        btT3.setBackground(null);
        btT4.setBackground(null);
        btT5.setBackground(null);
        
        carregarPainelPadrao(evt.getActionCommand().trim());    
        }
    
    public void carregarPainelPadrao(String tipo){
        try{
            String tipoCardapio;
            switch (tipo){
                case "Bebidas":
                    btT1.setBackground(Color.yellow);
                    tipoCardapio = "BEBIDAS";
                    
                    break;
                case "Pratos":
                    btT2.setBackground(Color.yellow);
                    tipoCardapio = "PRATOS";
                    
                    break;
                case "Carnes":
                    btT3.setBackground(Color.yellow);
                    tipoCardapio = "PRATOCARNE";
                    
                    break;
                case "Empanadas":
                    btT4.setBackground(Color.yellow);
                    tipoCardapio = "EMPANADAS";
                    
                    break;
                case "Sobremesas":
                    btT5.setBackground(Color.yellow);
                    tipoCardapio = "SOBREMESAS";
                    
                    break;
                default:
                     tipoCardapio = "BEBIDAS";
            }

            //Limpar o frame e a listaCardapioPadraoBotao
            painelPadrao.removeAll();
            painelDerivado.removeAll();
            //painelPadrao.setSize(400, 400);
            
            
            ArrayList<ItemCard> listaC;
            listaC = controleCardapio.obterCardapioPadrao(tipoCardapio);
            
           listaCardapioPadraoBotao = new CardapioBotao[listaC.size()];
           if (listaC.size() > 3) {
                int linhas = (listaC.size()/4)+1;
                painelPadrao.setLayout(new GridLayout(linhas,4));
           } else {
                painelPadrao.setLayout(new GridLayout(1,listaC.size()));
           }       
                tamanholistaP = 0;
             for (int i = 0; i < listaC.size(); i++) {
                tamanholistaP++; 
                listaCardapioPadraoBotao[i] = new CardapioBotao(listaC.get(i), new JButton(listaC.get(i).getDescCard())); 
                listaCardapioPadraoBotao[i].bt.setVisible(true);
                painelPadrao.add(listaCardapioPadraoBotao[i].bt);               
                
                listaCardapioPadraoBotao[i].bt.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        padraoActionPerformed(evt);
                    }
                });
   
            }
            this.setSize(this.getSize());
            
 
         }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());  
            desabilitarGeral();
        }        
        
        
    }
    
    private void padraoActionPerformed(java.awt.event.ActionEvent evt){
      
        for (int i = 0; i < tamanholistaP; i++) {
            listaCardapioPadraoBotao[i].bt.setBackground(null);
        }

        
        JButton btRecebe = (JButton) evt.getSource();
        int i = 0;
        
        btRecebe.setBackground(Color.yellow);
        
        //Busco aqui o ItemCardápio desejado!!!
        while (btRecebe != listaCardapioPadraoBotao[i].bt) {
            i++;
        }
        cardapioSelecionado = listaCardapioPadraoBotao[i].itemCardapio;
        descricaoItemSelecionado.setEnabled(true);
        descricaoItemSelecionado.setText(cardapioSelecionado.getDescCard());
        descricaoItemSelecionado.setEditable(false);
        
        //Agora com base nesse item cardápio, carregamos os derivados e também 
        //damos a opção de já adicionar o pedido.
        carregarPainelDerivado(listaCardapioPadraoBotao[i].itemCardapio);
        
        textoObs.setEnabled(true);
        btExplodir.setEnabled(true);
        btAdicionar.setEnabled(true);
        btLimpar.setEnabled(true);
        textoObs.requestFocus();

    }
    
    private void carregarPainelDerivado(ItemCard padraoSelecionado){
        try {
        
        ArrayList<ItemCard> listaDerivado;
        listaDerivado = controleCardapio.obterCardapioDerivado(padraoSelecionado);
//##########limpar todos os derivados
            painelDerivado.removeAll();
         
            painelDerivado.setMaximumSize(painelDireitoPedido.getSize());
           listaCardapioDerivadoBotao = new CardapioBotao[listaDerivado.size()];
           if (listaDerivado.size() > 2) {
                painelDerivado.setMaximumSize(painelDireitoPedido.getSize());
                int linhas = (listaDerivado.size()/2)+1;
                painelDerivado.setLayout(new GridLayout(linhas,2));
           } else {
                painelDerivado.setLayout(new GridLayout(1,listaDerivado.size()));
           }       
                tamanholistaD = 0;
             for (int i = 0; i < listaDerivado.size(); i++) {
                tamanholistaD++;
                listaCardapioDerivadoBotao[i] = new CardapioBotao(listaDerivado.get(i), new JButton(listaDerivado.get(i).getDescCard())); 
                listaCardapioDerivadoBotao[i].bt.setVisible(true);
                painelDerivado.add(listaCardapioDerivadoBotao[i].bt);               
                
                listaCardapioDerivadoBotao[i].bt.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        derivadoActionPerformed(evt);
                    }
                });
   
            }
            this.setSize(this.getSize());       
        
//###################
       
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());  
            desabilitarGeral();
        }                   
                
                
                
    }
      
    private void derivadoActionPerformed(java.awt.event.ActionEvent evt){
        for (int i = 0; i < tamanholistaP; i++) {
            listaCardapioPadraoBotao[i].bt.setBackground(null);
        }
        for (int i = 0; i < tamanholistaD; i++) {
            listaCardapioDerivadoBotao[i].bt.setBackground(null);
        }
        
         JButton btRecebe = (JButton) evt.getSource();
         btRecebe.setBackground(Color.yellow); 
         
        int i=0; 
        //Busco aqui o ItemCardápio desejado!!!
        while (btRecebe != listaCardapioDerivadoBotao[i].bt) {
            i++;
        }
         
         
         
        cardapioSelecionado = listaCardapioDerivadoBotao[i].itemCardapio;
        descricaoItemSelecionado.setEnabled(true);
        descricaoItemSelecionado.setText(cardapioSelecionado.getDescCard());
        descricaoItemSelecionado.setEditable(false);
         
         
         
    }
    
    
        
    public void BotoesTipoVisíveis(boolean estado){
        btT1.setVisible(estado);
        btT2.setVisible(estado);
        btT3.setVisible(estado);
        btT4.setVisible(estado);
        btT5.setVisible(estado);
    }
       
    
    public void decisaoComMesa(Mesa mesaCorrente){
        try{
            Pedido ped = new Pedido();
            if (mesaCorrente.getStatus().equals("L")) {
                if ( JOptionPane.showConfirmDialog(null, "Mesa Livre! Deseja ocupar a mesa?") == 0)
                     ped = criarPedido(mesaCorrente);  
                else {
                    desabilitarGeral();
                    return;
                }
            }

            if (mesaCorrente.getStatus().equals("O")) {
                    ped = controlePedido.obterPedidoMesa(mesaCorrente.getId());                   
            }
            //Carregamos o cliente corrente
            clienteCorrente = controlePedido.obterCliente(ped.getId(), 0);
            listaClientes = controlePedido.obterClientes(ped);
            carregarPainelSuperior(ped);
            carregarItensPedido(ped);
            BotoesTipoVisíveis(true);
            
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());  
            desabilitarGeral();
        }    
    }
    
    private Pedido criarPedido(Mesa mesaCorrente){
        try {
            //Criamos um objeto do tipo pedido com a mesa e usuário que já temos
            Pedido pedido = new Pedido();
            pedido.setMesa(mesaCorrente);
            pedido.setUsr(p.usuario);
            
            //E aqui criamos no banco de dados!
            pedido = controlePedido.criar(pedido);
            
            return pedido;
            
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());    
            desabilitarGeral();
        }
        return null;
    }
    
    //Metodo para ser usado pelo FramePedidoBuscaMesa
    public void carregarCampoMesa(String mesa){
        textoMesa.setText(mesa);
        textoMesa.requestFocus();
    }
    
    public void setarComboCliente(String cliente){
        comboCliente.setSelectedItem(cliente);
    }

    public void carregarPainelSuperior(Pedido pedido){
        
        try {
        //Carrego o campo do pedido
        if (!textoPedido.isEnabled())
            textoPedido.setEnabled(true);
        if (!comboCliente.isEnabled())
            comboCliente.setEnabled(true);
        if (!btAdicionarCliente.isEnabled())
            btAdicionarCliente.setEnabled(true);
        
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
    
    }
    
    private void carregarItensPedido(Pedido pedido){
        //Precisamos obter o cliente selecionado no combo
        try { 
        Icon iconePreparado = new ImageIcon(getClass().getResource("/org/rangolibre/img/botoes/24x24/001_06.png"));  
        Icon iconePendente = new ImageIcon(getClass().getResource("/org/rangolibre/img/botoes/24x24/001_02.png"));  
   
            

            //Testar se o clienteCorrente esta certinho com a referencia para o pedido
            ArrayList<PedidoCardapio> listaItens = controlePedido.obterListaPedidoCardapioSimples(clienteCorrente);

            String[] colunas = new String[]{"Item", "Status"};
 
            Object[][] dados = new String[listaItens.size()][2];
    
            for (int i = 0; i < listaItens.size(); i++) {
                dados[i][0] = listaItens.get(i).getItemCard().getDescCard().trim();
                if (listaItens.get(i).getStatus().equals("C"))
                    dados[i][1] = "EM PREPARAÇÃO";
                else
                    dados[i][1] = "PREPARADO";
            }
                DefaultTableModel model = new DefaultTableModel(dados , colunas );
  
            tabelaResumo.setModel(model);
            tabelaResumo.clearSelection();  
            tabelaResumo.getColumnModel().getColumn(0).setPreferredWidth(230);
            
            this.setSize(this.getSize());
            //tabelaResumo.
       
         }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());  
             desabilitarGeral();
        }  
        
    }

    //Acao tomada ao selecionar um cliente
    private void clienteComboAcao(){
            try {
                btT1.setBackground(null);
                btT2.setBackground(null);
                btT3.setBackground(null);
                btT4.setBackground(null);
                btT5.setBackground(null);
                listaPedidoCardapio.clear();
               // painelCategoria.removeAll();
                painelPadrao.removeAll();
                painelDerivado.removeAll();
                textoObs.setText("");
                textoObs.setEnabled(false);
                btAdicionar.setEnabled(false);
                btLimpar.setEnabled(false);
                descricaoItemSelecionado.setText("");
                descricaoItemSelecionado.setEnabled(false);
                
                
            if (!clienteCorrente.getNomeCliente().trim().equals(comboCliente.getSelectedItem().toString().trim())) {
                
                for (int i = 0; i < listaClientes.size(); i++) {
                    if (listaClientes.get(i).getNomeCliente().equals(comboCliente.getSelectedItem().toString())) {
                        clienteCorrente = listaClientes.get(i);
                    }   
                }
                
                carregarItensPedido(clienteCorrente.getPedido());
                BotoesTipoVisíveis(true);
            }
// TODO add your handling code here:
        } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());               
                desabilitarGeral();
        }
    }
    
    private void adicionarCardapioAoCliente(){
        //Primeiro vamos criar um PedidoCardapio com as informações que temos!
        PedidoCardapio pedidoCardapio = new PedidoCardapio();
        pedidoCardapio.setCliente(clienteCorrente);
        pedidoCardapio.setItemCard(cardapioSelecionado);
        pedidoCardapio.setObservacao(textoObs.getText().trim());
        pedidoCardapio.setStatus("C");
        pedidoCardapio.setCriador(p.usuario);
        
        //Vamos adicionar este item ao nosso array de itens
        listaPedidoCardapio.add(pedidoCardapio);
        
        Object[] linha = {pedidoCardapio.getItemCard().getDescCard(), "PENDENTE"};//alguma linha 
                
        DefaultTableModel novoModelo = (DefaultTableModel) tabelaResumo.getModel();
        novoModelo.addRow(linha);
        tabelaResumo.setModel(novoModelo);
        btSalvar.setEnabled(true);
        btCancelar.setEnabled(true);
    }
    
    private void acoesPosSalvar(){
                btT1.setBackground(null);
                btT2.setBackground(null);
                btT3.setBackground(null);
                btT4.setBackground(null);
                btT5.setBackground(null);
               // painelCategoria.removeAll();
                painelPadrao.removeAll();
                painelDerivado.removeAll();
                textoObs.setText("");
                textoObs.setEnabled(false);
                btAdicionar.setEnabled(false);
                btLimpar.setEnabled(false);
                descricaoItemSelecionado.setText("");
                descricaoItemSelecionado.setEnabled(false);
                btSalvar.setEnabled(false);
                btCancelar.setEnabled(false);
                carregarItensPedido(clienteCorrente.getPedido());
                BotoesTipoVisíveis(true);
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
        btAdicionarCliente = new javax.swing.JButton();
        comboCliente = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        painelDireitoPedido = new javax.swing.JPanel();
        painelCategoria = new javax.swing.JPanel();
        painelPadrao = new javax.swing.JPanel();
        painelObs = new javax.swing.JPanel();
        textoObs = new javax.swing.JTextField();
        labelObs = new javax.swing.JLabel();
        btExplodir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btAdicionar = new javax.swing.JButton();
        btLimpar = new javax.swing.JButton();
        descricaoItemSelecionado = new javax.swing.JTextField();
        painelDerivado = new javax.swing.JPanel();
        painelEsquerdoPedido = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaResumo = new javax.swing.JTable();
        painelResumoAuxiliar = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        painelItensAuxiliar = new javax.swing.JPanel();
        btSalvar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Pedidos");
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

        btAdicionarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/rangolibre/img/botoes/24x24/001_57.png"))); // NOI18N
        btAdicionarCliente.setToolTipText("Adiciona clientes na mesa");
        btAdicionarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdicionarClienteActionPerformed(evt);
            }
        });

        comboCliente.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        comboCliente.setForeground(new java.awt.Color(46, 90, 205));
        comboCliente.setToolTipText("Clientes na Mesa");
        comboCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboClienteActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

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
                .addGap(27, 27, 27)
                .addComponent(labelCliente)
                .addGap(24, 24, 24)
                .addComponent(comboCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btAdicionarCliente)
                .addContainerGap())
        );
        painelSuperiorPedidoLayout.setVerticalGroup(
            painelSuperiorPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelSuperiorPedidoLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(painelSuperiorPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(painelSuperiorPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(painelSuperiorPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textoMesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelMesa)))
                    .addGroup(painelSuperiorPedidoLayout.createSequentialGroup()
                        .addGroup(painelSuperiorPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(painelSuperiorPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(labelCliente)
                                .addComponent(comboCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(textoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelPedido))
                            .addComponent(btAdicionarCliente))
                        .addGap(4, 4, 4)))
                .addGap(12, 12, 12))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        painelDireitoPedido.setBorder(javax.swing.BorderFactory.createTitledBorder("Adicionar Itens ao pedido"));
        painelDireitoPedido.setInheritsPopupMenu(true);

        painelCategoria.setBorder(javax.swing.BorderFactory.createTitledBorder("Passo 1: Selecione a categoria"));
        painelCategoria.setToolTipText("Passo 1: Selecione a categoria do item");

        painelPadrao.setBorder(javax.swing.BorderFactory.createTitledBorder("Passo 2: Selecione o Prato Padrão"));

        javax.swing.GroupLayout painelPadraoLayout = new javax.swing.GroupLayout(painelPadrao);
        painelPadrao.setLayout(painelPadraoLayout);
        painelPadraoLayout.setHorizontalGroup(
            painelPadraoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        painelPadraoLayout.setVerticalGroup(
            painelPadraoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 102, Short.MAX_VALUE)
        );

        painelObs.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        labelObs.setText("Observações:");

        btExplodir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/rangolibre/img/botoes/24x24/001_13.png"))); // NOI18N
        btExplodir.setText("Novo Derivado");
        btExplodir.setToolTipText("Se o prato derivado ainda não existe, crie ele!");
        btExplodir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExplodirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelObsLayout = new javax.swing.GroupLayout(painelObs);
        painelObs.setLayout(painelObsLayout);
        painelObsLayout.setHorizontalGroup(
            painelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelObsLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(labelObs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoObs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btExplodir))
        );
        painelObsLayout.setVerticalGroup(
            painelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelObsLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(painelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelObs)
                    .addComponent(textoObs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btExplodir))
                .addGap(0, 7, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Adicione o item ao pedido"));

        btAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/rangolibre/img/botoes/24x24/001_23.png"))); // NOI18N
        btAdicionar.setText("Adicionar");
        btAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btAdicionar.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdicionarActionPerformed(evt);
            }
        });

        btLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/rangolibre/img/botoes/24x24/001_49.png"))); // NOI18N
        btLimpar.setText("Limpar");
        btLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimparActionPerformed(evt);
            }
        });

        descricaoItemSelecionado.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btAdicionar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btLimpar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(descricaoItemSelecionado)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(descricaoItemSelecionado, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btLimpar)
                        .addComponent(btAdicionar)))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        painelDerivado.setBorder(javax.swing.BorderFactory.createTitledBorder("Passo 3: Se necessário, selecione o prato derivado"));
        painelDerivado.setMaximumSize(new java.awt.Dimension(800, 32767));

        javax.swing.GroupLayout painelDerivadoLayout = new javax.swing.GroupLayout(painelDerivado);
        painelDerivado.setLayout(painelDerivadoLayout);
        painelDerivadoLayout.setHorizontalGroup(
            painelDerivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 537, Short.MAX_VALUE)
        );
        painelDerivadoLayout.setVerticalGroup(
            painelDerivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 73, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout painelDireitoPedidoLayout = new javax.swing.GroupLayout(painelDireitoPedido);
        painelDireitoPedido.setLayout(painelDireitoPedidoLayout);
        painelDireitoPedidoLayout.setHorizontalGroup(
            painelDireitoPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDireitoPedidoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelDireitoPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(painelObs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelDireitoPedidoLayout.createSequentialGroup()
                        .addGroup(painelDireitoPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(painelDerivado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(painelPadrao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(painelCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        painelDireitoPedidoLayout.setVerticalGroup(
            painelDireitoPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDireitoPedidoLayout.createSequentialGroup()
                .addComponent(painelCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelPadrao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelDerivado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(9, 9, 9)
                .addComponent(painelObs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        painelEsquerdoPedido.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel1.setText("Resumo do Pedido");

        tabelaResumo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Qtd", "Item", "Status"
            }
        ));
        tabelaResumo.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabelaResumo);
        if (tabelaResumo.getColumnModel().getColumnCount() > 0) {
            tabelaResumo.getColumnModel().getColumn(0).setMinWidth(50);
            tabelaResumo.getColumnModel().getColumn(0).setPreferredWidth(50);
            tabelaResumo.getColumnModel().getColumn(0).setMaxWidth(50);
            tabelaResumo.getColumnModel().getColumn(2).setMinWidth(60);
            tabelaResumo.getColumnModel().getColumn(2).setPreferredWidth(60);
            tabelaResumo.getColumnModel().getColumn(2).setMaxWidth(60);
        }

        painelResumoAuxiliar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Cantarell", 0, 10)); // NOI18N
        jLabel2.setText("Legenda:");

        jLabel3.setFont(new java.awt.Font("Cantarell", 0, 10)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/rangolibre/img/botoes/24x24/001_02.png"))); // NOI18N
        jLabel3.setText("Em preparação");

        jLabel4.setFont(new java.awt.Font("Cantarell", 0, 10)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/rangolibre/img/botoes/24x24/001_06.png"))); // NOI18N
        jLabel4.setText("Preparado");

        javax.swing.GroupLayout painelResumoAuxiliarLayout = new javax.swing.GroupLayout(painelResumoAuxiliar);
        painelResumoAuxiliar.setLayout(painelResumoAuxiliarLayout);
        painelResumoAuxiliarLayout.setHorizontalGroup(
            painelResumoAuxiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelResumoAuxiliarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(26, 26, 26))
        );
        painelResumoAuxiliarLayout.setVerticalGroup(
            painelResumoAuxiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelResumoAuxiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel3)
                .addComponent(jLabel4)
                .addComponent(jLabel2))
        );

        painelItensAuxiliar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/rangolibre/img/botoes/24x24/001_18.png"))); // NOI18N
        btSalvar.setText("Salvar");
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });

        btCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/rangolibre/img/botoes/24x24/001_05.png"))); // NOI18N
        btCancelar.setText("Cancelar");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelItensAuxiliarLayout = new javax.swing.GroupLayout(painelItensAuxiliar);
        painelItensAuxiliar.setLayout(painelItensAuxiliarLayout);
        painelItensAuxiliarLayout.setHorizontalGroup(
            painelItensAuxiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelItensAuxiliarLayout.createSequentialGroup()
                .addContainerGap(63, Short.MAX_VALUE)
                .addComponent(btSalvar)
                .addGap(28, 28, 28)
                .addComponent(btCancelar)
                .addGap(62, 62, 62))
        );
        painelItensAuxiliarLayout.setVerticalGroup(
            painelItensAuxiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelItensAuxiliarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(painelItensAuxiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btCancelar)
                    .addComponent(btSalvar))
                .addContainerGap())
        );

        javax.swing.GroupLayout painelEsquerdoPedidoLayout = new javax.swing.GroupLayout(painelEsquerdoPedido);
        painelEsquerdoPedido.setLayout(painelEsquerdoPedidoLayout);
        painelEsquerdoPedidoLayout.setHorizontalGroup(
            painelEsquerdoPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEsquerdoPedidoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelEsquerdoPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(painelItensAuxiliar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelEsquerdoPedidoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(painelResumoAuxiliar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        painelEsquerdoPedidoLayout.setVerticalGroup(
            painelEsquerdoPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEsquerdoPedidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelItensAuxiliar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(painelResumoAuxiliar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(painelEsquerdoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(painelDireitoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(painelSuperiorPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(painelSuperiorPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(painelEsquerdoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(painelDireitoPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
       
        p.btPedido.setBackground(null);
        p.cPedido = false;
        
        // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameClosing

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed

        // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameClosed

    private void btBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarActionPerformed
        FramePedidoBuscaMesa bm = new FramePedidoBuscaMesa(this);
        p.addJanela(bm);
        bm.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btBuscarActionPerformed

    private void btExplodirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExplodirActionPerformed
        FramePedidoExplodir pe = new FramePedidoExplodir(cardapioSelecionado, this);
        p.addJanela(pe);
        pe.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btExplodirActionPerformed

    private void textoMesaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textoMesaFocusLost
        //Aqui dispara o carregar
        try {
            desabilitarGeral();
            if (textoMesa.getText().length() == 4) {
                Mesa mesaCorrente = new Mesa();
                //Carregamos geral ...
                mesaCorrente = controleMesa.ObterMesa(textoMesa.getText().trim());
                decisaoComMesa(mesaCorrente);
                textoMesa.setEnabled(false);
            }
        } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());               
                desabilitarGeral();
                }
        
    }//GEN-LAST:event_textoMesaFocusLost

    private void comboClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboClienteActionPerformed
 
    }//GEN-LAST:event_comboClienteActionPerformed

    private void textoMesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoMesaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoMesaActionPerformed

    private void btLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimparActionPerformed
        desabilitarGeral();
        // TODO add your handling code here:
    }//GEN-LAST:event_btLimparActionPerformed

    private void btAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdicionarActionPerformed
        adicionarCardapioAoCliente();
                btT1.setBackground(null);
                btT2.setBackground(null);
                btT3.setBackground(null);
                btT4.setBackground(null);
                btT5.setBackground(null);
               // painelCategoria.removeAll();
                painelPadrao.removeAll();
                painelDerivado.removeAll();
                textoObs.setText("");
                textoObs.setEnabled(false);
                btAdicionar.setEnabled(false);
                btLimpar.setEnabled(false);
                descricaoItemSelecionado.setText("");
                descricaoItemSelecionado.setEnabled(false);
                this.setSize(this.getSize());
        

// TODO add your handling code here:
    }//GEN-LAST:event_btAdicionarActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        try {
             if ( JOptionPane.showConfirmDialog(null, "Deseja realmente cancelar as ações?") == 0) {
                acoesPosSalvar();
                listaPedidoCardapio = new ArrayList<PedidoCardapio>();
             }
            
        }        catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());               
                desabilitarGeral();
                }   

      
        
// TODO add your handling code here:
    }//GEN-LAST:event_btCancelarActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        try {
            if ( JOptionPane.showConfirmDialog(null, "Confirma envio dos itens?") == 0) {
                for (int i = 0; i < listaPedidoCardapio.size(); i++) {
                    //Momento em que colocamos no banco de dados os itens
                    controlePedido.adicionarItem(listaPedidoCardapio.get(i));
                }
                acoesPosSalvar();
            }
        } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());               
                desabilitarGeral();
                }               
          
// TODO add your handling code here:
    }//GEN-LAST:event_btSalvarActionPerformed

    private void btAdicionarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdicionarClienteActionPerformed
        FramePedidoAdicionarCliente addC = new FramePedidoAdicionarCliente(clienteCorrente.getPedido(), this);        
        p.addJanela(addC);
        addC.setVisible(true); 
        addC.setLocation(500, 10);
        
// TODO add your handling code here:
    }//GEN-LAST:event_btAdicionarClienteActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdicionar;
    private javax.swing.JButton btAdicionarCliente;
    private javax.swing.JButton btBuscar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btExplodir;
    private javax.swing.JButton btLimpar;
    private javax.swing.JButton btSalvar;
    private javax.swing.JComboBox comboCliente;
    private javax.swing.JTextField descricaoItemSelecionado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelCliente;
    private javax.swing.JLabel labelMesa;
    private javax.swing.JLabel labelObs;
    private javax.swing.JLabel labelPedido;
    private javax.swing.JPanel painelCategoria;
    private javax.swing.JPanel painelDerivado;
    private javax.swing.JPanel painelDireitoPedido;
    private javax.swing.JPanel painelEsquerdoPedido;
    private javax.swing.JPanel painelItensAuxiliar;
    private javax.swing.JPanel painelObs;
    private javax.swing.JPanel painelPadrao;
    private javax.swing.JPanel painelResumoAuxiliar;
    private javax.swing.JPanel painelSuperiorPedido;
    private javax.swing.JTable tabelaResumo;
    private javax.swing.JTextField textoMesa;
    private javax.swing.JTextField textoObs;
    private javax.swing.JTextField textoPedido;
    // End of variables declaration//GEN-END:variables
}
