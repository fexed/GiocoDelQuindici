package giocodelquindici;

import java.awt.event.*;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;

/** 
 *  Gestisce le azioni dei pulsanti
 *  @author Federico Matteoni
 *  @version 1.1
 */
public class Listener implements ActionListener
{
    /**
     * La finestra del programma
     */
    private final GUI win;

    /**
     * La logica del programma
     */
    private final Giocodelquindici game;
    
    /**
     * La lista di ritorno dalla logica, per aggiornare la GUI
     */
    private int[] lista;
    
    /**
     * Il numero delle mosse eseguito
     */
    private int mosse = 0;

    public Listener(Giocodelquindici game, GUI win)
    {
        this.game = game;
        this.win = win;
        lista = new int[16];
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("genera"))
        {
            mosse = 0;
            lista = game.generate();
            int k = 0;
            for (int i = 0; i < 4; i++)
            {
                for (int j = 0; j < 4; j++)
                {
                    win.setMatrixText(i, j, Integer.toString(lista[k]), mosse);
                    k++;
                }
            }
        }
        else if (e.getActionCommand().equals("risolvi"))
        {
            
        }
        else if (e.getActionCommand().equals("salva"))
        {
            game.save();
        }
        else
        {
            //premuto un bottone della matrice
            int index = Integer.parseInt(e.getActionCommand());
            //if (index != 0)
            //{
                 mosse++;
                 lista = game.analyze(index);
                 int k = 0;
                 for (int i = 0; i < 4; i++)
                 {
                     for (int j = 0; j < 4; j++)
                     {
                         win.setMatrixText(i, j, Integer.toString(lista[k]), mosse);
                         k++;
                     }
                 }
            //}
            if (Arrays.equals(lista, new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0}))
            {
                JOptionPane.showMessageDialog(win, "Hai vinto!", "Vittoria!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}