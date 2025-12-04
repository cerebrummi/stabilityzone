package common;

import enums.Letter;

/**
 * A fractal algorithm shows prime number patterning.
 * 
 * @author heeren Heeren, B. (2025). A fractal algorithm shows prime number
 *         patterning. Zenodo. https://doi.org/10.5281/zenodo.16829092
 */
public class LetterFA
{
   private static final int _19 = 19;
   /**
    * 4 is the step where letter a has formed.
    */
   private int walksetBn = 4;
   private String walksetBPn = "M";

   private int offset = 0;
   StringBuilder walksetCPn = new StringBuilder(Letter.a.pattern);

   private int counterA;

   public void step()
   {
      walksetBn++;

      walksetBPn = String.valueOf(walksetCPn.charAt(0));

      fractalProcessMove();

      if ("L".equalsIgnoreCase(walksetBPn.toString()))
      {
         if (walksetBn < _19)
         {
            fractalProcessCopy();
         }
         fractalProcessChange();
      }

      counterA = 0;

      String copyCPn = walksetCPn.toString();
      int start = offset;
      int end = offset + 6;
      int stabilityZone = walksetBn;
      for (; end < stabilityZone;)
      {
         String letter = copyCPn.substring(start, end);
         matchLetter(letter);
         start += 6;
         end += 6;
      }
   }

   public void matchLetter(String letter)
   {
      if (Letter.a.pattern.equals(letter))
      {
         counterA++;
      }
      else if (Letter.b.pattern.equals(letter))
      {

      }
      else if (Letter.c.pattern.equals(letter))
      {

      }
      else if (Letter.d.pattern.equals(letter))
      {

      }
      else
      {
         System.err.println("invalid letter " + letter + " n = " + walksetBn);
         System.exit(1);
      }
   }

   private void fractalProcessChange()
   {
      int index = walksetBn + 1;
      for (int i = 0; i < walksetCPn.length(); i++)
      {
         if ((index % walksetBn) == 0)
         {
            walksetCPn.replace(i, i + 1, "M");
         }
         index++;
      }
   }

   private void fractalProcessCopy()
   {

      String copy = walksetCPn.toString();

      for (int i = 1; i < walksetBn; i++)
      {
         walksetCPn.append(copy);
      }
   }

   private void fractalProcessMove()
   {
      String firstSymbol = walksetCPn.substring(0, 1);
      walksetCPn.deleteCharAt(0);
      if (walksetBn < _19)
      {
         walksetCPn.append(firstSymbol);
      }
      offset--;
      if (offset < 0)
      {
         offset = 5;
      }

   }

   public int getCounterA()
   {
      return counterA;
   }

   public int getWalksetBn()
   {
      return walksetBn;
   }

   public Integer getPatternSize()
   {
      return walksetCPn.length();
   }
}
