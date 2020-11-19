
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

    File outFile = new File("songWebText.txt"); // your txt output
    PrintWriter out = new PrintWriter(outFile);
    //int i = 1;

    // txt input, rename to your txt name
    try (Scanner sc = new Scanner(new File("web.txt"))) 
    {
        while (sc.hasNextLine())
        {
            String line = sc.nextLine();

            //Credit to BullyWiiPlaza in sof
            String regex = "\\(?\\b(http://|www[.])[-A-Za-z0-9+&@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&@#/%=~_()|]";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(line);
            

            if(m.find()){
                String urlStr = m.group();

                if (urlStr.startsWith("(") && urlStr.endsWith(")")){          
                  urlStr = urlStr.substring(1, urlStr.length() - 1);
                }

                //System.out.println(++i + " " + "https://" + urlStr);
                out.println("https://" + urlStr);

            }

            
        }
    }
    catch(IOException e){
      System.out.println("not found");
    }
    
    out.close();


  }
}