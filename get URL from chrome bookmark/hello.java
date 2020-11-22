
//import java.util.List; 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class hello {

  public static void main(String[] args) throws FileNotFoundException { 

    
    

    // txt input, rename to your txt name
    try (Scanner sc = new Scanner(new File("web.txt"))) 
    {
      int count = 0;
      File outFile = new File("songWebText.txt"); // your txt output

      if (!outFile.createNewFile()) {
        System.out.println("File exists.");
        return;

      }

      PrintWriter out = new PrintWriter(outFile);

        while (sc.hasNextLine())
        {
            String line = sc.nextLine();

            //Credit to BullyWiiPlaza in sof
            String regex = "\\(?\\b(http://|www[.])[-A-Za-z0-9+&@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&@#/%=~_()|]";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(line);
            

            if(m.find()){
                String urlStr = m.group();
                count++;

                if (urlStr.startsWith("(") && urlStr.endsWith(")")){          
                  urlStr = urlStr.substring(1, urlStr.length() - 1);
                }

                //System.out.println(++i + " " + "https://" + urlStr);
                char c = urlStr.charAt(0);

                if(c == 'h'){ //already have a https:// or http:// , no need to add
                  out.println(urlStr);
                }
                else{
                  out.println("https://" + urlStr);
                }
                

            }

            
        }

        System.out.println("Done, total link = " + count + ".");
        out.close();
    }
    catch(IOException e){
      System.out.println("not found");
      
    }
    

  }
}