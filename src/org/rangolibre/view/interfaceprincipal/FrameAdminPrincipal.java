/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rangolibre.view.interfaceprincipal;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import org.rangolibre.control.ControleCardapio;
import org.rangolibre.control.ControleIngrediente;
import org.rangolibre.model.CatIngre;
import org.rangolibre.model.Ingrediente;
import org.rangolibre.model.IngredienteCardapio;
import org.rangolibre.model.ItemCard;

/**
 *
 * @author aleaguado
 */
public class FrameAdminPrincipal extends javax.swing.JInternalFrame {
    Principal p;
    ArrayList<Ingrediente> listaIngredienteTotal;
    ArrayList<IngredienteCardapio> listaIngredienteSelecionada;
    ItemCard padrao;
    ControleCardapio controleCardapio = new ControleCardapio();
    ControleIngrediente controleIngrediente = new ControleIngrediente();
    ArrayList<CatIngre> listaCategorias;
    IngredienteCardapio selected;
    DefaultListModel modeloSelecionada = new DefaultListModel();
    DefaultListModel modeloTotal = new DefaultListModel();
    String pontoCarne;
    
    
    
    
    /**
     * Creates new form FrameAdminPrincipal
     */
    public FrameAdminPrincipal(Principal pr) {
        p = pr;
        
        initComponents(); 
        carregarCampos();
    }
    
        private void carregarCampos(){
        textoItemNovo.setText(padrao.getDescCard());
        textoItemNovo.setEnabled(true);
        
        listaIngredientesContidos.setModel(modeloSelecionada);
        listaIngredientesDisponiveis.setModel(modeloTotal);

        comboCategoriaIngredientes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
            if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                categoriaIngredientesActionPerformed();
        }
   } 

        });
        
        carregarListaIngredientesSelecionados();
        carregarComboCategoria();
    
    }
        
    private void carregarComboCategoria(){
        
        try {
            comboCategoriaIngredientes.setEnabled(true);
            comboCategoriaIngredientes.removeAllItems();
            listaCategorias = controleIngrediente.obterListaCategoria();
    
            comboCategoriaIngredientes.addItem("TODOS");
            
            for (int i = 0; i < listaCategorias.size(); i++) {
                comboCategoriaIngredientes.addItem(listaCategorias.get(i).getDesc());
            }
            
            comboCategoriaIngredientes.setSelectedIndex(0);
             
   } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());  
                }
    }
    
    private void comporDescricao(ItemCard novoItem){

        textoItemNovo.setEnabled(true);
        textoItemNovo.setText(controleCardapio.comporDescricaoDerivada(novoItem, padrao));
        textoItemNovo.setEnabled(false);    
    }    
    

       private void carregarListaIngredientesDisponiveis(){
        int i = 0;
   try {

       modeloTotal.clear();
       if (comboCategoriaIngredientes.getSelectedIndex() == 0) {
            listaIngredienteTotal = controleIngrediente.obterListaIngrediente(99999);

       } else {     
                while(!listaCategorias.get(i).getDesc().equals(comboCategoriaIngredientes.getSelectedItem().toString().trim())) {
                    i++;
                }
                listaIngredienteTotal = controleIngrediente.obterListaIngrediente(listaCategorias.get(i).getId());

       }

        for (int j = 0; j < listaIngredienteTotal.size(); j++) {
            
           // if (!modeloSelecionada.contains(listaIngredienteTotal.get(j).getDesc())) {
             modeloTotal.add(j, listaIngredienteTotal.get(j).getDesc());
           // }
        }
    
   } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());  
                }
   
    }
       
    private void categoriaIngredientesActionPerformed(){    
        
        carregarListaIngredientesDisponiveis();
    }
    
    private void carregarListaIngredientesSelecionados(){
        try {
            
        modeloSelecionada.clear();
        
        
       
        padrao.setIngre(controleCardapio.obterListaIngrediente(padrao.getIdCard()));
        
        listaIngredienteSelecionada = controleCardapio.obterListaIngrediente(padrao.getIdCard());
        
        for (int j = 0; j < listaIngredienteSelecionada.size(); j++) {
            modeloSelecionada.add(j, listaIngredienteSelecionada.get(j).getIngrediente().getDesc());
        }      
           } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());  
                }    
    }
    
    private void carregarListaIngredientesSelecionadosPos(){
        try {
        
        modeloSelecionada.clear();    
        for (int j = 0; j < listaIngredienteSelecionada.size(); j++) {
            modeloSelecionada.add(j, listaIngredienteSelecionada.get(j).getIngrediente().getDesc());
        }

           } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());  
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        painelCadastroPedido = new javax.swing.JPanel();
        painelSuperior = new javax.swing.JPanel();
        labelNovo = new javax.swing.JLabel();
        textoItemNovo = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        labelNovo1 = new javax.swing.JLabel();
        codigoCardapio = new javax.swing.JTextField();
        painelEsquerdo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        textoQuantidade = new javax.swing.JTextField();
        btAlterarQty = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaIngredientesContidos = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        textoCarne = new javax.swing.JTextField();
        painelBotoesCentral = new javax.swing.JPanel();
        btAdicionar = new javax.swing.JButton();
        botaoRemover = new javax.swing.JButton();
        painelDireito = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        listaIngredientesDisponiveis = new javax.swing.JList();
        btNovoIngrediente = new javax.swing.JButton();
        comboCategoriaIngredientes = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        btSalvar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Administração do Sistema");
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

        painelSuperior.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        labelNovo.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        labelNovo.setText("Descrição:");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/rangolibre/img/botoes/24x24/001_38.png"))); // NOI18N
        jButton1.setText("Buscar");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/rangolibre/img/botoes/24x24/001_01.png"))); // NOI18N
        jButton2.setText("Novo");

        labelNovo1.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        labelNovo1.setText("Código:");

        javax.swing.GroupLayout painelSuperiorLayout = new javax.swing.GroupLayout(painelSuperior);
        painelSuperior.setLayout(painelSuperiorLayout);
        painelSuperiorLayout.setHorizontalGroup(
            painelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelSuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelNovo)
                    .addComponent(labelNovo1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelSuperiorLayout.createSequentialGroup()
                        .addComponent(codigoCardapio, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(textoItemNovo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );
        painelSuperiorLayout.setVerticalGroup(
            painelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelSuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codigoCardapio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(labelNovo1))
                .addGap(3, 3, 3)
                .addGroup(painelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textoItemNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelNovo))
                    .addComponent(jButton2))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        painelEsquerdo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingredientes contidos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        jLabel2.setText("Quantidade:");

        textoQuantidade.setToolTipText("");

        btAlterarQty.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/rangolibre/img/botoes/24x24/001_06.png"))); // NOI18N
        btAlterarQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterarQtyActionPerformed(evt);
            }
        });

        listaIngredientesContidos.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaIngredientesContidosValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(listaIngredientesContidos);

        jLabel3.setText("*Ponto da Carne:");

        javax.swing.GroupLayout painelEsquerdoLayout = new javax.swing.GroupLayout(painelEsquerdo);
        painelEsquerdo.setLayout(painelEsquerdoLayout);
        painelEsquerdoLayout.setHorizontalGroup(
            painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEsquerdoLayout.createSequentialGroup()
                .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelEsquerdoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE))
                    .addGroup(painelEsquerdoLayout.createSequentialGroup()
                        .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelEsquerdoLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textoCarne, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(painelEsquerdoLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(2, 2, 2)
                                .addComponent(textoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btAlterarQty)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        painelEsquerdoLayout.setVerticalGroup(
            painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEsquerdoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(textoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btAlterarQty, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(24, 24, 24)
                .addGroup(painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textoCarne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        painelBotoesCentral.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/rangolibre/img/botoes/24x24/001_23_1.png"))); // NOI18N
        btAdicionar.setToolTipText("Adicionar ingrediente");
        btAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdicionarActionPerformed(evt);
            }
        });

        botaoRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/rangolibre/img/botoes/24x24/001_21.png"))); // NOI18N
        botaoRemover.setToolTipText("Remover Ingrediente");
        botaoRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoRemoverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelBotoesCentralLayout = new javax.swing.GroupLayout(painelBotoesCentral);
        painelBotoesCentral.setLayout(painelBotoesCentralLayout);
        painelBotoesCentralLayout.setHorizontalGroup(
            painelBotoesCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelBotoesCentralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelBotoesCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btAdicionar)
                    .addComponent(botaoRemover))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        painelBotoesCentralLayout.setVerticalGroup(
            painelBotoesCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelBotoesCentralLayout.createSequentialGroup()
                .addComponent(btAdicionar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoRemover)
                .addGap(0, 32, Short.MAX_VALUE))
        );

        painelDireito.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingredientes Disponíveis", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        jScrollPane4.setViewportView(listaIngredientesDisponiveis);

        btNovoIngrediente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/rangolibre/img/botoes/24x24/001_01.png"))); // NOI18N
        btNovoIngrediente.setText("Novo Ingrediente");

        javax.swing.GroupLayout painelDireitoLayout = new javax.swing.GroupLayout(painelDireito);
        painelDireito.setLayout(painelDireitoLayout);
        painelDireitoLayout.setHorizontalGroup(
            painelDireitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDireitoLayout.createSequentialGroup()
                .addGroup(painelDireitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelDireitoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btNovoIngrediente)))
                .addContainerGap())
        );
        painelDireitoLayout.setVerticalGroup(
            painelDireitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDireitoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btNovoIngrediente)
                .addContainerGap())
        );

        comboCategoriaIngredientes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboCategoriaIngredientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCategoriaIngredientesActionPerformed(evt);
            }
        });

        jLabel1.setText("Categoria:");

        btSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/rangolibre/img/botoes/24x24/001_06.png"))); // NOI18N
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

        javax.swing.GroupLayout painelCadastroPedidoLayout = new javax.swing.GroupLayout(painelCadastroPedido);
        painelCadastroPedido.setLayout(painelCadastroPedidoLayout);
        painelCadastroPedidoLayout.setHorizontalGroup(
            painelCadastroPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelCadastroPedidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelSuperior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelCadastroPedidoLayout.createSequentialGroup()
                .addGap(0, 45, Short.MAX_VALUE)
                .addGroup(painelCadastroPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelCadastroPedidoLayout.createSequentialGroup()
                        .addComponent(painelEsquerdo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(painelBotoesCentral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(painelCadastroPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelCadastroPedidoLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboCategoriaIngredientes, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(painelCadastroPedidoLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(painelDireito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelCadastroPedidoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btSalvar)
                        .addGap(25, 25, 25)
                        .addComponent(btCancelar)
                        .addGap(220, 220, 220))))
        );
        painelCadastroPedidoLayout.setVerticalGroup(
            painelCadastroPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelCadastroPedidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(painelCadastroPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelCadastroPedidoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(painelCadastroPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboCategoriaIngredientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelCadastroPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(painelEsquerdo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(painelDireito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(painelCadastroPedidoLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(painelBotoesCentral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(painelCadastroPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btSalvar)
                    .addComponent(btCancelar))
                .addGap(0, 0, 0))
        );

        jTabbedPane1.addTab("Cardápio", painelCadastroPedido);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        p.cAdm = false;
        p.btAdm.setBackground(null);
        // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameClosing

    private void btAlterarQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarQtyActionPerformed
        try {
            selected.setQty(Integer.parseInt(textoQuantidade.getText()));
            JOptionPane.showMessageDialog(null, "Quantidade Atualizada!!!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btAlterarQtyActionPerformed

    private void listaIngredientesContidosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaIngredientesContidosValueChanged
        try{
            int indexC;
            indexC = listaIngredientesContidos.getSelectedIndex();

            if (indexC < 0) //Teste para volta da exclusão
            return;

            selected = listaIngredienteSelecionada.get(indexC);

            textoQuantidade.setEnabled(true);
            textoQuantidade.setText(Integer.toString(selected.getQty()));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_listaIngredientesContidosValueChanged

    private void btAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdicionarActionPerformed

        try {
            if (modeloSelecionada.contains(listaIngredientesDisponiveis.getSelectedValue())){
                JOptionPane.showMessageDialog(null, "Ingrediente já adicionado!!!");
                return;
            }

            int indexC;
            indexC = listaIngredientesDisponiveis.getSelectedIndex();
            Ingrediente ingre = listaIngredienteTotal.get(indexC);
            IngredienteCardapio ingreCard = new IngredienteCardapio(1,ingre);
            listaIngredienteSelecionada.add(ingreCard);
            modeloSelecionada.addElement(ingreCard.getIngrediente().getDesc());

            textoItemNovo.setText(textoItemNovo.getText() + " C/ " + ingreCard.getIngrediente().getDesc().substring(0, 5) + ";" );

            //Atualiza o combolist dos selecionados
            //carregarListaIngredientesSelecionadosPos();

            //Atualiza o combolist dos disponíveis
            //carregarListaIngredientesDisponiveis();

            //Marcamos o item novo como selecionado.
            listaIngredientesContidos.setSelectedIndex(listaIngredientesContidos.getLastVisibleIndex());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btAdicionarActionPerformed

    private void botaoRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoRemoverActionPerformed
        try {
            int indexC;
            indexC = listaIngredientesContidos.getSelectedIndex();

            selected = listaIngredienteSelecionada.get(indexC);

            listaIngredienteSelecionada.remove(selected);

            //Atualiza o combolist dos selecionados
            //carregarListaIngredientesSelecionadosPos();
            modeloSelecionada.remove(indexC);

            textoItemNovo.setText(textoItemNovo.getText() + " S/ " + selected.getIngrediente().getDesc().substring(0, 5) + ";" );

            //Atualiza o combolist dos disponíveis
            //carregarListaIngredientesDisponiveis();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_botaoRemoverActionPerformed

    private void comboCategoriaIngredientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCategoriaIngredientesActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_comboCategoriaIngredientesActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed

        try {
            pontoCarne = textoCarne.getText().trim();
            ItemCard novoItem = new ItemCard();
            novoItem.setAtivo("Y");
            novoItem.setDescCard("");
            novoItem.setDescCat(padrao.getDescCat());
            //novoItem.setIdCard();
            novoItem.setIngre(listaIngredienteSelecionada);
            novoItem.setPadrao(false);
            novoItem.setPonto(pontoCarne);
            novoItem.setTipo(padrao.getTipo());
            novoItem.setUsr(p.usuario);
            novoItem.setpreco(padrao.getpreco());
            novoItem.setIdCat(padrao.getIdCat());

            //comporDescricao(novoItem);

            novoItem.setDescCard(textoItemNovo.getText().trim() + textoCarne.getText().trim());

            controleCardapio.criarDerivado(novoItem, padrao);

            JOptionPane.showMessageDialog(null, "Item Criado!!!");

            this.setClosed(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btSalvarActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed

        try {
            this.setClosed(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoRemover;
    private javax.swing.JButton btAdicionar;
    private javax.swing.JButton btAlterarQty;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btNovoIngrediente;
    private javax.swing.JButton btSalvar;
    private javax.swing.JTextField codigoCardapio;
    public javax.swing.JComboBox comboCategoriaIngredientes;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelNovo;
    private javax.swing.JLabel labelNovo1;
    private javax.swing.JList listaIngredientesContidos;
    private javax.swing.JList listaIngredientesDisponiveis;
    private javax.swing.JPanel painelBotoesCentral;
    private javax.swing.JPanel painelCadastroPedido;
    private javax.swing.JPanel painelDireito;
    private javax.swing.JPanel painelEsquerdo;
    private javax.swing.JPanel painelSuperior;
    private javax.swing.JTextField textoCarne;
    private javax.swing.JTextField textoItemNovo;
    private javax.swing.JTextField textoQuantidade;
    // End of variables declaration//GEN-END:variables
}
