package giocodelquindici;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** 
 *  Interfaccia grafica del programma, contiene la matrice di pulsanti e i comandi
 *  @author Federico Matteoni
 *  @version 1.1
 */
public class GUI extends JFrame
{
      /** 
       *  Matrice per la visualizzazione, 4x4
       */
      private JButton[][] matrice;

      /** 
       *  Container principale
       */
      private Container cnt;
      
      /**
       *  Contiene la matrice di pulsanti, GridLayout
       */
      private JPanel pnlMatrice;
      
      /**
       * Genera una nuova matrice
       */
      private JMenuItem genera;
      
      /**
       * Serializza il gioco
       */
      private JMenuItem salva;
      
      /**
       * Risolve la matrice, mostrando i passaggi
       */
      private JMenuItem risolvi;
      
      /**
       * Mosse eseguite
       */
      private JMenuItem mosse;
      
      /**
       * Menu della finestra
       */
      private JMenuBar menu;
      
      /**
       * Inizializza pulsanti, pannelli e container
       * @param game La logica del programma, da passare al listener
       */
      public GUI(Giocodelquindici game)
      {
          super("Gioco del quindici");
          Listener listener = new Listener(game, this);
          cnt = getContentPane();
          
          pnlMatrice = new JPanel();
          pnlMatrice.setLayout(new GridLayout(4, 4, 1, 1));
          matrice = new JButton[4][4];
          int conteggio = 0;
          for (int i = 0; i < 4; i++)
          {
              for (int j = 0; j < 4; j++)
              {
                  if (i == 3 && j == 3)
                  {
                      matrice[i][j] = new JButton("");
                      matrice[i][j].setBackground(Color.WHITE);
                      matrice[i][j].setBorderPainted(false);
                      matrice[i][j].addActionListener(listener);
                      matrice[i][j].setActionCommand(Integer.toString(0));
                  }
                  else
                  {
                      conteggio++;
                      matrice[i][j] = new JButton(Integer.toString(conteggio));
                      matrice[i][j].setBackground(Color.LIGHT_GRAY);
                      matrice[i][j].setBorderPainted(false);
                      matrice[i][j].addActionListener(listener);
                      matrice[i][j].setActionCommand(Integer.toString(conteggio));
                  }
                  pnlMatrice.add(matrice[i][j]);
              }
          }
          
          genera = new JMenuItem("Genera");
          genera.addActionListener(listener);
          genera.setActionCommand("genera");
          salva = new JMenuItem("Salva");
          salva.addActionListener(listener);
          salva.setActionCommand("salva");
          risolvi = new JMenuItem("Risolvi");
          risolvi.addActionListener(listener);
          risolvi.setActionCommand("risolvi");
          mosse = new JMenuItem("0 click");
          menu = new JMenuBar();
          menu.add(genera);
          menu.add(salva);
          menu.add(risolvi);
          menu.add(mosse);
          
          cnt.setLayout(new BorderLayout());
          cnt.add(pnlMatrice, BorderLayout.CENTER);
          this.setJMenuBar(menu);
          
          setSize(300, 300);
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          listener.actionPerformed(new ActionEvent(new JButton("0"), 0, "0"));
          setVisible(true);
      }
      
      /**
       * Aggiorna l'interfaccia
       * @param i Coordinata della cella da aggiornare, riga
       * @param j Coordinata della cella da aggiornare, colonna
       * @param text Cosa scrivere nella cella
       * @param clicks Numero di mosse
       */
      public void setMatrixText(int i, int j, String text, int clicks)
      {
          mosse.setText(clicks + " click");
          matrice[i][j].setText(text.equals("0") ? "" : text);
          if (text.equals("0"))
          {
              matrice[i][j].setBackground(Color.WHITE);
          }
          else
          {
              matrice[i][j].setBackground(Color.LIGHT_GRAY);
          }
          matrice[i][j].setActionCommand(text);
      }
}