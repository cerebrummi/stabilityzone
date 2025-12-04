package enums;

public enum Letter
{
   a("LMLMMM"), 
   b("LMMMMM"), 
   c("MMLMMM"), 
   d("MMMMMM");
   
   public final String pattern;

   Letter(String pattern)
   {
      this.pattern = pattern;
   }
}
