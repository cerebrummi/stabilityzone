package common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringJoiner;

public class StartLetterFa
{
   private final static int NUMBER_OF_STEPS = 250000;

   private static HashMap<Integer, Integer> counter = new HashMap<>();

   public static void main(String[] args)
   {
      LetterFA letterFA = new LetterFA();

      for (int i = letterFA.getWalksetBn(); i < NUMBER_OF_STEPS; i++)
      {
         letterFA.step();
         
         counter.put( (i + 1),
               letterFA.getCounterA());
      }

      ArrayList<Integer> keys = new ArrayList<>(counter.keySet());
      handleCounter(keys);

   }

   public static void handleCounter(ArrayList<Integer> keys)
   {
      keys.sort(new Comparator<Integer>()
      {

         @Override
         public int compare(Integer o1, Integer o2)
         {
            if( o1 < o2)
            {
               return -1;
            }
            return 1;
         }
      });

      
      
      File file = new File("stabilityzone_lettera_250000.csv");
      
      try(FileOutputStream stream = new FileOutputStream(file);
            OutputStreamWriter writer = new OutputStreamWriter(stream,
                  StandardCharsets.UTF_8);)
      {
         StringJoiner joiner = new StringJoiner("\n");

         int a = 0;
         for (Integer key : keys)
         {
            a = counter.get(key);

            joiner.add(key + "\t"+ a);

         }

         writer.write(joiner.toString());
         writer.flush();
         writer.close();
      }
      catch (UnsupportedEncodingException e)
      {
         e.printStackTrace();
      }
      catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }

}
