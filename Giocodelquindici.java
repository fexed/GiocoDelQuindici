package giocodelquindici;

import java.io.*;
import java.util.Random;

/** 
 *  Logica principale del programma, genera la tabella, ne controlla la validità e controlla gli spostamenti
 *  @author Federico Matteoni
 *  @version 1.1
 */
public class Giocodelquindici implements java.io.Serializable
{
      /**
      * Vettore che contiene la tabella
      */
      private int[] lista;

      /**
       * Inizializza e riempie il vettore lsita con 0
       */
      public Giocodelquindici()
      {
          lista = new int[16];
          for (int i = 0; i < 15; i++)
          {
              lista[i] = 0;
          }
      }
      
      /**
       * Genera una nuova tabella e ne controlla la validità. Nel caso non sia risolvibile ne genera un'altra
       * @return La tabella generata
       */
      public int[] generate()
      {
          //se numero successivi dispari allora non risolvibile
          int n, conteggio;
          boolean presente;
          Random random = new Random();
          do
          {
                conteggio = 0;
                lista[15] = 0;
                for (int i = 0; i < 15; i++)
                {
                    lista[i] = 0;
                }
                for (int i = 0; i < 15; i++)
                {
                    do
                    {
                        presente = false;
                        n = random.nextInt(15)+1;
                        for (int j = 0; j < i; j++)
                        {
                            if (lista[j] == n)
                            {
                                presente = true;
                                break;
                            }
                        }
                    } while (presente);
                    lista[i] = n;
                }
                for (int i = 0; i < 15; i++)
                {
                    for (int j = i+1; j < 15; j++)
                    {
                        if (lista[i] > lista[j])
                        {
                            conteggio++;
                        }
                    }
                }
          } while (conteggio % 2 != 0);
          return lista;
      }
      
      /**
       * Genera lo spostamento di una casella, analizzando il vettore lista
       * @param index L'indice della casella da spostare
       * @return La tabella aggiornata
       */
      public int[] analyze(int index)
      {
          for (int i = 0; i < 16; i++)
          {
              if (lista[i] == index)
              {
                  index = i;
                  break;
              }
          }
          try
          {
                if (lista[index+1] == 0)
                {
                    lista[index+1] = lista[index];
                    lista[index] = 0;
                }
          }
          catch (IndexOutOfBoundsException erroreInput){}
          try
          {
                if (lista[index-1] == 0)
                {
                    lista[index-1] = lista[index];
                    lista[index] = 0;
                }
          }
          catch (IndexOutOfBoundsException erroreInput){}
          try
          {
                if (lista[index+4] == 0)
                {
                    lista[index+4] = lista[index];
                    lista[index] = 0;
                }
          }
          catch (IndexOutOfBoundsException erroreInput){}
          try
          {
                if (lista[index-4] == 0)
                {
                    lista[index-4] = lista[index];
                    lista[index] = 0;
                }
          }
          catch (IndexOutOfBoundsException erroreInput){}
          
          return lista;
      }
      
      /**
       * Risolve il gioco mostrando i passaggi (da fare)
       */
      public void resolve()
      {
          //EH
      }
      
      public void save()
      {
            try
            {
                ObjectOutputStream saveStream = new ObjectOutputStream(new FileOutputStream("fifteen.bin"));
                saveStream.writeObject(this);
                saveStream.close();
            }
            catch (IOException ex)
            {
            }
      }
      
      /**
       * Metodo di avvio, istanzia le classi Giocodelquindici e GUI
       * @param args Parametri da linea di comando
       */
      public static void main(String[] args)
      {
          Giocodelquindici game = null;
          try
          {
              ObjectInputStream inObjStream = new ObjectInputStream(new FileInputStream("fifteen.bin"));
              game = (Giocodelquindici)inObjStream.readObject();
              inObjStream.close();
          }
          catch (ClassNotFoundException ex) {}
          catch (IOException ex) {}
          
          if (game == null)
          {
            game = new Giocodelquindici();
          }
          GUI win = new GUI(game);
      }
}