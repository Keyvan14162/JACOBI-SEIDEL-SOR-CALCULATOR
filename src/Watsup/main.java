package Watsup;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Keyvan
 */
public class main extends javax.swing.JFrame {

    /**
     * Creates new form Jacobi3x3
     */
    public main() {
        initComponents();
        fullScreen();
        
    }
    
    int iter = 0;
    int iteratorSteps = 10;
    
    float x11,x12,x13,x14,x21,x22,x23,x24,x31,x32,x33,x34,x35;
    //Setting the starting values
    float x1 = 0;
    float x2 = 0;
    float x3 = 0;
    float tempx1;
    float tempx2;
    float tempx3;
    
    
    public final void fullScreen(){
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen icin
    }
    
    public void getValues() {           
        try{
            iteratorSteps = Integer.valueOf(jTextFieldIterator.getText());
            x11 = Float.valueOf(jTextField1x1.getText());
            x12 = Float.valueOf(jTextField1x2.getText());
            x13 = Float.valueOf(jTextField1x3.getText());
            x14 = Float.valueOf(jTextField1x4.getText());
            x21 = Float.valueOf(jTextField2x1.getText());
            x22 = Float.valueOf(jTextField2x2.getText());
            x23 = Float.valueOf(jTextField2x3.getText());
            x24 = Float.valueOf(jTextField2x4.getText());
            x31 = Float.valueOf(jTextField3x1.getText());
            x32 = Float.valueOf(jTextField3x2.getText());
            x33 = Float.valueOf(jTextField3x3.getText());
            x34 = Float.valueOf(jTextField3x4.getText());   
            
            //System.out.println(x11+" "+x12+" "+x13+" "+x14);
            //System.out.println(x21+" "+x22+" "+x23+" "+x24);
            //System.out.println(x31+" "+x32+" "+x33+" "+x34);
            
        }catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ENTER ONLY NUMBERS AND FILL ALL THE FIELDS"); 
        }  
    }
    
    public Color randomColor() {
        //Choosing rondom color for clicked piano title
        //Also for clicked note counter
        Random random = new Random();
        
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        
        Color color = new Color(r, g, b);
        
        return color;
    }
    
    public void jacobi() {
        jTextArea1.setText("");
        
        jTextArea1.append("x1 = "+(x14/x11)+"   +   ( "+ ((-1*x13)/x11) +"(x3) )   +   ( "+ ((-1*x12)/x11) +"(x2) ) \n");
        jTextArea1.append("x2 = "+(x24/x22)+"   +   ( "+ ((-1*x23)/x22) +"(x3) )   +   ( "+ ((-1*x21)/x22) +"(x1) ) \n");
        jTextArea1.append("x3 = "+(x34/x33)+"   +   ( "+ ((-1*x31)/x33) +"(x1) )   +   ( "+ ((-1*x32)/x33) +"(x2) ) \n\n");
        
        jTextArea1.append("-----------------------GAUSS JACOBI-----------------------\n");        
        jTextArea1.append("ITERATION          X1              X2             X3        ");
        jTextArea1.append("\n____________________________________________________________");
        
        for(int i=0 ; i<iteratorSteps ; i++){
            
            float tempx1 = (x14 - (x13 * x3) - (x12 * x2)) / (x11);
            float tempx2 = (x24 - (x23 * x3) - (x21 * x1)) / (x22);
            float tempx3 = (x34 - (x31 * x1) - (x32 * x2)) / (x33);
            
            String striter = new DecimalFormat("000").format(iter);      
            String strx1 = new DecimalFormat("00.0000").format(x1);   
            String strx2 = new DecimalFormat("00.0000").format(x2);    
            String strx3 = new DecimalFormat("00.0000").format(x3);
            
            
            jTextArea1.append("\n       "+ striter +"         | "+strx1+"     | "+strx2+"      | "+strx3);
            
            //System.out.println(tempx1+" "+tempx2+" "+tempx3);
            x1 = tempx1;
            x2 = tempx2;
            x3 = tempx3;
                    
            iter++;
        } 
        iter = 0;
        x1 = 0;
        x2 = 0;
        x3 = 0;     
    }
    
    public void seidel() {
        jTextArea1.setText("");
        
        jTextArea1.append("x1 = "+(x14/x11)+"   +   ( "+ ((-1*x13)/x11) +"(x3) )   +   ( "+ ((-1*x12)/x11) +"(x2) ) \n");
        jTextArea1.append("x2 = "+(x24/x22)+"   +   ( "+ ((-1*x23)/x22) +"(x3) )   +   ( "+ ((-1*x21)/x22) +"(x1) ) \n");
        jTextArea1.append("x3 = "+(x34/x33)+"   +   ( "+ ((-1*x31)/x33) +"(x1) )   +   ( "+ ((-1*x32)/x33) +"(x2) ) \n\n");
        
        jTextArea1.append("----------------------GAUSSIAN SEIDEL---------------------\n");        
        jTextArea1.append("ITERATION          X1              X2             X3        ");
        jTextArea1.append("\n____________________________________________________________");
        
        for(int i=0 ; i<iteratorSteps ; i++){         
            
            String striter = new DecimalFormat("000").format(iter);          
            String strx1 = new DecimalFormat("00.0000").format(x1);        
            String strx2 = new DecimalFormat("00.0000").format(x2);       
            String strx3 = new DecimalFormat("00.0000").format(x3);         
            
            jTextArea1.append("\n       "+ striter +"         | "+strx1+"     | "+strx2+"      | "+strx3);
            
            x1 = (x14 - (x13 * x3) - (x12 * x2)) / (x11);
            x2 = (x24 - (x23 * x3) - (x21 * x1)) / (x22);
            x3 = (x34 - (x31 * x1) - (x32 * x2)) / (x33);
            
                    
            iter++;
        } 
        iter = 0;
        x1 = 0;
        x2 = 0;
        x3 = 0;
    }
    
    public void sor() {
        float w = 1;
        try {
            w = Float.valueOf(jTextFieldW.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ENTER ONLY NUMBERS AND FILL ALL THE FIELDS");
        }
        
        jTextArea1.setText("");
     
        jTextArea1.append("x1 = x1   +   (w / "+x11+")  [ "+x14+"   +   ( "+(-1*x11)+"(x1) )   +   ( "+ (-1*x12) +"(x2) )   +   ( "+ (-1*x13) +"(x3) )  ] \n");
        jTextArea1.append("x2 = x2   +   (w / "+x22+")  [ "+x24+"   +   ( "+(-1*x21)+"(x1) )   +   ( "+ (-1*x22) +"(x2) )   +   ( "+ (-1*x23) +"(x3) )  ] \n");
        jTextArea1.append("x3 = x3   +   (w / "+x33+")  [ "+x34+"   +   ( "+(-1*x31)+"(x1) )   +   ( "+ (-1*x32) +"(x2) )   +   ( "+ (-1*x33) +"(x3) )  ] \n\n");
        
        jTextArea1.append("-------------------SOR METHOD(w = "+w+")--------------------\n");        
        jTextArea1.append("ITERATION          X1              X2             X3        ");
        jTextArea1.append("\n____________________________________________________________");
        
        for(int i=0 ; i<iteratorSteps ; i++){         
            
            String striter = new DecimalFormat("000").format(iter);        
            String strx1 = new DecimalFormat("00.0000").format(x1);
            String strx2 = new DecimalFormat("00.0000").format(x2);
            String strx3 = new DecimalFormat("00.0000").format(x3);
                       
            jTextArea1.append("\n       "+ striter +"         | "+strx1+"     | "+strx2+"      | "+strx3);
            
            //Cok ugrastigim icin yanlis olssa bile silmicem 
            //x1 = x1 + (w / Math.abs(x11))*( x14 -1*(Math.abs(x11))*x1 - (x13 * x3) - (x12 * x2));
            //x2 = x2 + (w / Math.abs(x22))*( x24 -1*(Math.abs(x22))*x2 - (x23 * x3) - (x21 * x1));
            //x3 = x3 + (w / Math.abs(x33))*( x34 -1*(Math.abs(x33))*x3 - (x31 * x1) - (x32 * x2));
            
            //x1 = x1 + (w / x11)*( x14 -1*(Math.abs(x11))*x1 - (x13 * x3) - (x12 * x2));
            //x2 = x2 + (w / x22)*( x24 -1*(Math.abs(x22))*x2 - (x23 * x3) - (x21 * x1));
            //x3 = x3 + (w / x33)*( x34 -1*(Math.abs(x33))*x3 - (x31 * x1) - (x32 * x2));
            
            x1 = x1 + (w / x11)*( x14 -(x11 * x1) - (x13 * x3) - (x12 * x2));
            x2 = x2 + (w / x22)*( x24 -(x22 * x2) - (x23 * x3) - (x21 * x1));
            x3 = x3 + (w / x33)*( x34 -(x33 * x3) - (x31 * x1) - (x32 * x2));
            
            iter++;
        } 
        iter = 0;
        x1 = 0;
        x2 = 0;
        x3 = 0;
    }
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField15 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jTextField1x1 = new javax.swing.JTextField();
        jTextField2x1 = new javax.swing.JTextField();
        jTextField3x1 = new javax.swing.JTextField();
        jTextField1x2 = new javax.swing.JTextField();
        jTextField2x2 = new javax.swing.JTextField();
        jTextField3x2 = new javax.swing.JTextField();
        jTextField1x3 = new javax.swing.JTextField();
        jTextField2x3 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField3x3 = new javax.swing.JTextField();
        jTextField1x4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField2x4 = new javax.swing.JTextField();
        jTextFieldW = new javax.swing.JTextField();
        jTextField3x4 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldIterator = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButtonSeidel = new javax.swing.JButton();
        jButtonJacobi = new javax.swing.JButton();
        jButtonSOR = new javax.swing.JButton();
        jButtonColor = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Jacobi-Seidel-SOR Calculator");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jTextField1x1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jTextField1x1.setText("10");
        jTextField1x1.setBorder(null);
        jTextField1x1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField1x1.setPreferredSize(new java.awt.Dimension(70, 40));

        jTextField2x1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jTextField2x1.setText("1");
        jTextField2x1.setBorder(null);
        jTextField2x1.setPreferredSize(new java.awt.Dimension(70, 40));

        jTextField3x1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jTextField3x1.setText("-2");
        jTextField3x1.setBorder(null);
        jTextField3x1.setPreferredSize(new java.awt.Dimension(70, 40));

        jTextField1x2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jTextField1x2.setText("-2");
        jTextField1x2.setBorder(null);
        jTextField1x2.setPreferredSize(new java.awt.Dimension(70, 40));
        jTextField1x2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1x2ActionPerformed(evt);
            }
        });

        jTextField2x2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jTextField2x2.setText("10");
        jTextField2x2.setBorder(null);
        jTextField2x2.setPreferredSize(new java.awt.Dimension(70, 40));

        jTextField3x2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jTextField3x2.setText("1");
        jTextField3x2.setBorder(null);
        jTextField3x2.setPreferredSize(new java.awt.Dimension(70, 40));

        jTextField1x3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jTextField1x3.setText("1");
        jTextField1x3.setBorder(null);
        jTextField1x3.setPreferredSize(new java.awt.Dimension(70, 40));

        jTextField2x3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jTextField2x3.setText("-2");
        jTextField2x3.setBorder(null);
        jTextField2x3.setPreferredSize(new java.awt.Dimension(70, 40));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText("YOU MUST ENTER PIVOTED VERSION OF MATRIX \nOTHERWISE PROGRAM WILL BE WORKED WRONG\n\nIn the current example:\n10(x1) - 2(x2) + 1(x3) = 13\n1(x1) + 10(x2) - 2(x3) = 13\n-2(x1) + 1(x2) + 10(x3) = 13\n\nProgram will find x values ​​using selected operation\n\nThe W factor (relaxation coefficient)\nis only required for the ask method.\n\n\nİSMAİL KEYVAN\nGithub : github.com/Keyvan14162?tab=repositories");
        jTextArea1.setOpaque(false);
        jScrollPane1.setViewportView(jTextArea1);

        jTextField3x3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jTextField3x3.setText("10");
        jTextField3x3.setBorder(null);
        jTextField3x3.setPreferredSize(new java.awt.Dimension(70, 40));

        jTextField1x4.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jTextField1x4.setText("13");
        jTextField1x4.setBorder(null);
        jTextField1x4.setPreferredSize(new java.awt.Dimension(70, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("W FACTOR(ONLY FOR SOR METHOD)");

        jTextField2x4.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jTextField2x4.setText("13");
        jTextField2x4.setBorder(null);
        jTextField2x4.setPreferredSize(new java.awt.Dimension(70, 40));

        jTextFieldW.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldW.setText("1");

        jTextField3x4.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jTextField3x4.setText("13");
        jTextField3x4.setBorder(null);
        jTextField3x4.setPreferredSize(new java.awt.Dimension(70, 40));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("ITERATOR STEPS");

        jLabel1.setText("=");

        jTextFieldIterator.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldIterator.setText("10");

        jLabel2.setText("=");

        jLabel3.setText("=");

        jButtonSeidel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonSeidel.setText("CALCULATE BY GAUSSIAN SEIDEL METHOD");
        jButtonSeidel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSeidelActionPerformed(evt);
            }
        });

        jButtonJacobi.setBackground(new java.awt.Color(255, 255, 255));
        jButtonJacobi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonJacobi.setText("CALCULATE BY GAUSS JACOBI METHOD");
        jButtonJacobi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonJacobiActionPerformed(evt);
            }
        });

        jButtonSOR.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonSOR.setText("CALCULATE BY SOR METHOD");
        jButtonSOR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSORActionPerformed(evt);
            }
        });

        jButtonColor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonColor.setText("CHANGE BACKGROUND COLOR");
        jButtonColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonColorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonSOR, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSeidel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonJacobi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField2x1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2x2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2x3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField3x1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField3x2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField3x3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField1x1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1x2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1x3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField3x4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField2x4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField1x4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldW, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldIterator, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextField1x1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField1x2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField1x3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField1x4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField2x1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField2x2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField2x3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField2x4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField3x1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField3x2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField3x3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField3x4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldIterator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                        .addComponent(jButtonJacobi, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonSeidel, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonSOR, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonColor, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSORActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSORActionPerformed
        // TODO add your handling code here:
        getValues();
        sor();
    }//GEN-LAST:event_jButtonSORActionPerformed

    private void jButtonJacobiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonJacobiActionPerformed
        // TODO add your handling code here:
        getValues();
        jacobi();
    }//GEN-LAST:event_jButtonJacobiActionPerformed

    private void jButtonSeidelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSeidelActionPerformed
        // TODO add your handling code here:
        getValues();
        seidel();
    }//GEN-LAST:event_jButtonSeidelActionPerformed

    private void jButtonColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonColorActionPerformed
        // TODO add your handling code here:
        jPanel1.setBackground(randomColor());
    }//GEN-LAST:event_jButtonColorActionPerformed

    private void jTextField1x2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1x2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1x2ActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonColor;
    private javax.swing.JButton jButtonJacobi;
    private javax.swing.JButton jButtonSOR;
    private javax.swing.JButton jButtonSeidel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField1x1;
    private javax.swing.JTextField jTextField1x2;
    private javax.swing.JTextField jTextField1x3;
    private javax.swing.JTextField jTextField1x4;
    private javax.swing.JTextField jTextField2x1;
    private javax.swing.JTextField jTextField2x2;
    private javax.swing.JTextField jTextField2x3;
    private javax.swing.JTextField jTextField2x4;
    private javax.swing.JTextField jTextField3x1;
    private javax.swing.JTextField jTextField3x2;
    private javax.swing.JTextField jTextField3x3;
    private javax.swing.JTextField jTextField3x4;
    private javax.swing.JTextField jTextFieldIterator;
    private javax.swing.JTextField jTextFieldW;
    // End of variables declaration//GEN-END:variables
}
