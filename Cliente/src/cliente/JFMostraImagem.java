package cliente;


import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class JFMostraImagem extends JFrame{
  private JPanel jPanelCentral;
  private JLabel jlImage;
 
  public JFMostraImagem(){
	  
  }
  public JFMostraImagem(String nomeDoArquivo){
    setSize(1000, 800);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    String caminho = null;
    try {
		caminho = new File(nomeDoArquivo).getCanonicalPath();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

    jPanelCentral = new JPanel();   
      jPanelCentral.setLayout(null);   
      jlImage = new JLabel();   
      jlImage.setBounds(320, 0, 902, 695);   
      //jlImage.setIcon(new ImageIcon((getClass().gestResource(caminho))));  
      jlImage.setIcon(new ImageIcon(caminho));
      jPanelCentral.add(jlImage);   
         
      this.add(jPanelCentral, BorderLayout.CENTER); 
   

    setVisible(true);
  }

  

  /*
   
*/
}
