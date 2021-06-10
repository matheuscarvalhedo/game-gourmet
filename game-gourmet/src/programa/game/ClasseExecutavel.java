package programa.game;


import javax.swing.JOptionPane;
import javax.swing.UIManager;

import model.ListComida;
import model.Comida;



public class ClasseExecutavel extends javax.swing.JFrame {
	
	
    private final Comida massa = new Comida("Lasanha","");
    private final Comida notMassa = new Comida("Bolo de Chocolate","");
    
    
    private final ListComida comidaMassa = new ListComida();
    private final ListComida comidaNotMassa = new ListComida();
    
    
    private int resposta;

    
    public ClasseExecutavel() {        
        initComponents();
                      
        
        this.comidaMassa.getComidas().add(massa);
        this.comidaNotMassa.getComidas().add(notMassa);
    }

    @SuppressWarnings("unchecked")
    
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jBtOk = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Jogo Gourmet");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Pense em um prato que gosta");

        jBtOk.setText("OK");
        jBtOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtOkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jBtOk, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(123, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtOk)
                .addGap(19, 19, 19))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void jBtOkActionPerformed(java.awt.event.ActionEvent evt) {
        
        this.setVisible(false);
        
        
        initJogo();
        
        
        this.setVisible(true);
    }

    
    public static void main(String ... args) {
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            System.out.println("Erro ao carregar LookAndFeel");
        }

        
        java.awt.EventQueue.invokeLater(() -> {
            new ClasseExecutavel().setVisible(true);
        });
    }

    
    private javax.swing.JButton jBtOk;
    private javax.swing.JLabel jLabel1;
    

    
    
    private void initJogo() {
        resposta = JOptionPane.showConfirmDialog(rootPane, "O prato que voce pensou foi massa ?", "Confirm", JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {
            
            advinharComida(comidaMassa);
            return;
        }
        
        
        advinharComida(comidaNotMassa);        
    }
    
    
    private void advinharComida(ListComida comida) {
        int contador;
        int tamanhoList = comida.getComidas().size() -1;

        
        for (contador = tamanhoList; contador > 0; contador--) {
            resposta = perguntaComida(comida, contador, true);

            
            
            if (resposta == JOptionPane.YES_OPTION) {
                
                resposta = perguntaComida(comida, contador, false);
                
                
                if (resposta == JOptionPane.YES_OPTION) {
                    
                    acertei();
                    break;                    
                } else if ((resposta == JOptionPane.NO_OPTION) && (contador == 0)) {
                    
                    adicionarComida(comida, contador);
                    break;                    
                }
            }
        }

        
        if (contador == 0) {
            
            resposta = perguntaComida(comida, contador, false);
            
            if (resposta == JOptionPane.YES_OPTION) {
                
                acertei();
                return;
                
            }
            
            adicionarComida(comida, contador);            
        }
    }
    
    
    private void adicionarComida(ListComida comida, int ordemComida) {                
        comida.getComidas().add(montaObjetoComidaNovo(comida, ordemComida));
    }
    
    
    private void acertei() {
        JOptionPane.showMessageDialog(rootPane, "Acertei de novo!", "Acertei", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    private int perguntaComida(ListComida comida, int contador, boolean tipo) {
        if (tipo) {
            return JOptionPane.showConfirmDialog(rootPane, "O prato que pensou foi ".concat(comida.getComidas().get(contador).getTipo()).concat(" ?"), "Confirm", JOptionPane.YES_NO_OPTION);
        }
        
        return JOptionPane.showConfirmDialog(rootPane, "O prato que pensou foi ".concat(comida.getComidas().get(contador).getNome()).concat(" ?"), "Confirm", JOptionPane.YES_NO_OPTION);
    }
    
    
    private Comida montaObjetoComidaNovo(ListComida comida, int ordemComida) {
        String nomeComida = JOptionPane.showInputDialog(rootPane, "Qual prato voce pensou ?", "Desisto", JOptionPane.QUESTION_MESSAGE);
        String tipoComida = JOptionPane.showInputDialog(rootPane, nomeComida.concat(" É ________ mas ").concat(comida.getComidas().get(ordemComida).getNome()).concat(" nao."), "Complete", JOptionPane.QUESTION_MESSAGE);
        
        Comida prato = new Comida(nomeComida, tipoComida);
                
        return prato;        
    }
	
	
	}
	

	

	
	
	
	
	
	
	

		


