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

  public static String getName(){

    Scanner console = new Scanner(System.in);
    System.out.println("Enter the output txt name: ");
    String name = console.next() + ".txt" ;
    console.close();    

    return name;
  }

  public static void main(String[] args) throws FileNotFoundException {   

    try (Scanner sc = new Scanner(new File("web.txt"))) 
    {
      
      File outFile = new File(getName()); // your txt output
      

      if (!outFile.createNewFile()) {
        System.out.println("File exists.Re-run and try other name.");       
        return;
      }

      int count = 0;      

      PrintWriter out = new PrintWriter(outFile);

        while (sc.hasNextLine())
        {
            String line = sc.nextLine();
            

            int start = 0; // start of the word
            int end = line.length() - 4; // end of the word in google chrome bookmark

            int k = end;

            while( k-- > 0){// search from back to front
              if(line.charAt(k) == '>' && line.charAt(k-1) == '"'){
                start = k+1;
                break;
              }
            
            }

          /* // search from front to back
            for(int i = 0; i< line.length();i++){ // a word start must be "> after it
              if(line.charAt(i) == '"' && line.charAt(i+1) == '>'){
                start = i+2;
                break;
              }

            }
          */

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

                if((start > 0 ) && (end > 0)){            
                  out.println(line.substring(start,end));
                }

                //already have a https:// or http:// , no need to add
                //Return link should be www.xxx.com or http://www....
                if(c == 'h'){ 
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
      System.out.println("error, try again.");
      
    }
    

  }
}