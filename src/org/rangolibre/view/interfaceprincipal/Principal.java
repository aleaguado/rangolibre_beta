/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rangolibre.view.interfaceprincipal;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.rangolibre.model.Usuario;


/**
 *
 * @author aleaguado
 */
public class Principal extends javax.swing.JFrame {
    
    //Objeto do tipo usuário que guarda o usuário logado!!
    Usuario usuario = new Usuario();

    JButton btPedido = new JButton("Pedidos");
    Icon iconePedidoA = new ImageIcon(getClass().getResource("/org/rangolibre/img/botoes/Pedido_A_100_100.png"));  
    Icon iconePedidoB = new ImageIcon(getClass().getResource("/org/rangolibre/img/botoes/Pedido_A_100_100.png"));  
    JButton btPreparo = new JButton("Preparo");   
    JButton btCaixa = new JButton("Caixa");
    JButton btAdm = new JButton("Administração");
    JButton btRel = new JButton("Relatório");
    
    //Controles
    boolean cPedido = false;
    boolean cPreparo = false;
    boolean cCaixa = false;
    boolean cAdm = false;
    boolean cRel = false;
    
    
    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
        ajustesInicializacao(); //Coloca frame maximizado
        criarBotoesPrincipais(null); //Cria os botões necessários
        desabilitarGeral(); //Coloca os botões desabilitados
    }
    
    private void ajustesInicializacao(){
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
    }

//########INICIO DA PARTE DE BOTOES###################################################     

    
    private void criarBotoesPrincipais(ArrayList<String> per) {
        ButtonGroup bg = new ButtonGroup();
        PainelNavegacao.setLayout(null);
        
        PainelNavegacao.add(btPedido);
        btPedido.setBounds(0,3,100,40);
        btPedido.setVisible(true);
        bg.add(btPedido);
        
        btPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btPedidoActionPerformed(evt);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
               
        PainelNavegacao.add(btPreparo);
        btPreparo.setBounds(110,3,100,40);
        btPreparo.setVisible(true);        
        bg.add(btPreparo);
 
        btPreparo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPreparoActionPerformed(evt);
            }
        });
 
        PainelNavegacao.add(btCaixa);
        btCaixa.setBounds(220,3,100,40);
        btCaixa.setVisible(true);
        bg.add(btCaixa);

        btCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCaixaActionPerformed(evt);
            }
        });        
        
        
        
        PainelNavegacao.add(btAdm);
        btAdm.setBounds(330,3,120,40);
        btAdm.setVisible(true); 
        bg.add(btAdm);
        
        btAdm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdmActionPerformed(evt);
            }
        });   

       
        PainelNavegacao.add(btRel);
        btRel.setBounds(460,3,100,40);
        btRel.setVisible(true);
        bg.add(btRel);
        
        btRel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRelActionPerformed(evt);
            }
        }); 
        

    }

    //Ações do botão btPedido
    private void btPedidoActionPerformed(java.awt.event.ActionEvent evt) throws PropertyVetoException {
        if (!cPedido) {
            cPedido = true;
            btPedido.setBackground(new java.awt.Color(125,189,7));
            FramePedidoPrincipal fp = new FramePedidoPrincipal(this);
           // FramePedidoPrincipal.setDefaultLookAndFeelDecorated(true);
            PainelVisualizacao.add(fp);
            fp.setSize(PainelVisualizacao.getSize());
            fp.setLocation(0,0);
            fp.setResizable(false);
            fp.show();
        }
    }   
    
     //Ações do botão btPreparo
    private void btPreparoActionPerformed(java.awt.event.ActionEvent evt) {
       if (!cPreparo) {
         cPreparo = true;        
         btPreparo.setBackground(new java.awt.Color(125,189,7));
         FramePreparoPrincipal fpp = new FramePreparoPrincipal(this);
         PainelVisualizacao.add(fpp);
         fpp.setSize(PainelVisualizacao.getSize());
         fpp.setLocation(0,0);
         fpp.show();
        }
    }      

         //Ações do botão btCaixa
    private void btCaixaActionPerformed(java.awt.event.ActionEvent evt) {                                         
       if (!cCaixa) {
         cCaixa = true;         
         btCaixa.setBackground(new java.awt.Color(125,189,7));
         FrameCaixaPrincipal fc = new FrameCaixaPrincipal(this);
         PainelVisualizacao.add(fc);
         fc.setSize(PainelVisualizacao.getSize());
         fc.setLocation(0,0);
         fc.show();   
       }
        // TODO add your handling code here:
    } 
    
    public void addJanela(Component cp){
        PainelVisualizacao.add(cp);
    }
    


    //Ações do botão btAdm
    private void btAdmActionPerformed(java.awt.event.ActionEvent evt) {                                         
       if (!cAdm) {
         cAdm = true;  
         btAdm.setBackground(new java.awt.Color(125,189,7));
         FrameAdminPrincipal fa = new FrameAdminPrincipal(this);
         PainelVisualizacao.add(fa);
         fa.setSize(PainelVisualizacao.getSize());
         fa.setLocation(0,0);
         fa.show();  
       }
        // TODO add your handling code here:
    }     

    //Ações do botão btRel
    private void btRelActionPerformed(java.awt.event.ActionEvent evt) {                                         
       if (!cRel) {
         cRel = true;  
         btRel.setBackground(new java.awt.Color(125,189,7));
         FrameRelatoriosPrincipal frel = new FrameRelatoriosPrincipal(this);
         PainelVisualizacao.add(frel);
         frel.setSize(PainelVisualizacao.getSize());
         frel.setLocation(0,0);
         frel.show();     
       }
        // TODO add your handling code here:
    }
    
    
        public void desabilitarGeral(){
            btAdm.setEnabled(false);
            btCaixa.setEnabled(false);
            btPedido.setEnabled(false);
            btPreparo.setEnabled(false);
            btRel.setEnabled(false);
        }
    
    
    //Método para carregar a tela ...
        public void carregarTela(ArrayList<String> a, Usuario usr){
        usuario = usr;
        
        labelUsuario.setText(usuario.getId());
        
        for (int i = 0; i < a.size(); i++) {
            switch (a.get(i)) {
                case "PED_CRIAR":
                    btPedido.setEnabled(true);
                    break;
                case "PED_ENCER":
                    btPedido.setEnabled(true);
                    break;
                case "PED_EXCLU": 
                    btPedido.setEnabled(true);
                    break;
                case "PED_ADD": 
                    btPedido.setEnabled(true);
                    break;
                case "PED_REM":
                    btPedido.setEnabled(true);
                    break;
                case "PED_EDIT": 
                    btPedido.setEnabled(true);
                    break;
                case "PREP_PRAT":
                    btPreparo.setEnabled(true);
                    break;
                case "PREP_EMP":
                    btPreparo.setEnabled(true);
                    break;
                case "PREP_BEB":
                    btPreparo.setEnabled(true);
                    break;
                case "ADM_USR":
                    btAdm.setEnabled(true);
                    break;
                case "ADM_CARD":
                    btAdm.setEnabled(true);
                    break;
                case "ADM_PED":
                    btAdm.setEnabled(true);
                    break;
                case "ADM_PREC":
                    btAdm.setEnabled(true);
                    break;
                case "ADM_MES":
                    btAdm.setEnabled(true);
                    break;
                case "CAIXA":
                    btCaixa.setEnabled(true);
                    break;
                default:
                   JOptionPane.showMessageDialog(null, "Problemas com permissão!");
                }
   
        }
    }
    
    
    
    
    
    
    
//########FIM DA PARTE DE BOTOES###################################################    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PainelNavegacao =  new javax.swing.JDesktopPane() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // g.setColor(new java.awt.Color(60,87,142));
                g.setColor(Color.white);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        PainelVisualizacao =  new javax.swing.JDesktopPane() {
            private Image image;
            {
                try {
                    image = ImageIO.read(getClass().getResource("/org/rangolibre/img/FundoSoftware.jpg"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (image != null) {
                    Dimension dimension = this.getSize();
                    int x = (int)(dimension.getWidth() - image.getWidth(this)) / 2;
                    int y = (int)(dimension.getHeight() - image.getHeight(this)) / 2;
                    g.setColor(Color.WHITE);
                    // g.setColor(new java.awt.Color(172,0,0));
                    g.fillRect(0, 0, getWidth(), getHeight());
                    g.drawImage(image, x, y, image.getWidth(this), image.getHeight(this), this);
                } else {
                    g.drawString("Imagem nao encontrada", 50, 50);
                }
            }
        }
        ;
        painelUsuario = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        labelUsuario = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        menuPedido = new javax.swing.JMenuItem();
        menuPreparo = new javax.swing.JMenuItem();
        menuPagamento = new javax.swing.JMenuItem();
        menuCadastro = new javax.swing.JMenuItem();
        Relatórios = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuSair = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Rango Libre - Software Libre para gestão de restaurantes");

        PainelVisualizacao.setBackground(new java.awt.Color(225, 6, 13));
        PainelVisualizacao.setBorder(null);

        painelUsuario.setBackground(java.awt.Color.white);
        painelUsuario.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        painelUsuario.setPreferredSize(new java.awt.Dimension(191, 50));

        jLabel1.setFont(new java.awt.Font("Cantarell", 1, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(28, 68, 161));
        jLabel1.setText("Usuário:");

        labelUsuario.setBackground(java.awt.Color.lightGray);
        labelUsuario.setForeground(new java.awt.Color(4, 3, 213));

        javax.swing.GroupLayout painelUsuarioLayout = new javax.swing.GroupLayout(painelUsuario);
        painelUsuario.setLayout(painelUsuarioLayout);
        painelUsuarioLayout.setHorizontalGroup(
            painelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                .addContainerGap())
        );
        painelUsuarioLayout.setVerticalGroup(
            painelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelUsuarioLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(painelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(labelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu2.setText("Arquivo");

        menuPedido.setText("Lançar Pedido");
        jMenu2.add(menuPedido);

        menuPreparo.setText("Preparar Item");
        jMenu2.add(menuPreparo);

        menuPagamento.setText("Efetuar Pagamento");
        jMenu2.add(menuPagamento);

        menuCadastro.setText("Cadastro");
        jMenu2.add(menuCadastro);

        Relatórios.setText("Relatórios e Consultas");
        jMenu2.add(Relatórios);
        jMenu2.add(jSeparator1);

        menuSair.setText("Sair");
        jMenu2.add(menuSair);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Sobre");
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PainelNavegacao, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(PainelVisualizacao)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PainelNavegacao)
                    .addComponent(painelUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PainelVisualizacao, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane PainelNavegacao;
    private javax.swing.JDesktopPane PainelVisualizacao;
    private javax.swing.JMenuItem Relatórios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JMenuItem menuCadastro;
    private javax.swing.JMenuItem menuPagamento;
    private javax.swing.JMenuItem menuPedido;
    private javax.swing.JMenuItem menuPreparo;
    private javax.swing.JMenuItem menuSair;
    private javax.swing.JPanel painelUsuario;
    // End of variables declaration//GEN-END:variables
}

