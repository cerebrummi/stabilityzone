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
   private static HashMap<Integer,Integer[]> counter = new HashMap<>();
   LetterFA letterFA = new LetterFA();
   
   public static void main(String[] args)
   {
      StartLetterFa program = new StartLetterFa();
      try 
      {
          program.run();
      }
      catch (Exception e) 
      {
         e.printStackTrace();
      }
   }
   
   public void run() throws Exception {
      for (int i = 1 + letterFA.getWalksetBn(); i <= NUMBER_OF_STEPS; i++) {
          letterFA.step();
          counter.put(i, new Integer[] {
              letterFA.getCounterA(),
              letterFA.getCounterB(),
              letterFA.getCounterC(),
              letterFA.getCounterD(),
          });
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

      
      
      File file = new File("stabilityzone_lettersABCD_250000.csv");
      
      try(FileOutputStream stream = new FileOutputStream(file);
            OutputStreamWriter writer = new OutputStreamWriter(stream,
                  StandardCharsets.UTF_8);)
      {
         StringJoiner joiner = new StringJoiner("\n");
         joiner.add("n\ta\tb\tc\td");
         
         Integer[] content;
         for (Integer key : keys)
         {
            content = counter.get(key);

            joiner.add(key + "\t"+ content[0]+ "\t"+ content[1]+ "\t"+content[2]+ "\t"+content[3]);

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
