public class MorseCodeTree
{
    
    String file = "data.txt";     
    MorseCode coder = new MorseCode(file);
	
    Node morseTree = new Node('*'); // The tree of code table
	   
    public MorseCode(String filename) throws IOException
    {   
        
        Scanner sc = new Scanner(new File(filename)); //scanner for txt file
        
        while (sc.hasNextLine()) { //loop through the txt file
			String data = sc.nextLine(); //reading
			
			String[] tokens = data.split(" "); //tokens[0] = A-Z-0 || tokens[1] = -... ect
		   
			Node.putTree(tokens[0].charAt(0), tokens[1],morseTree); //method to build the tree accroding to the list             
        
        }
       

    }
}


class Node
{
    private char value;
    private Node left, right;
    
    public Node(char c)
    {
        value = c;
        left = right = null;
        //Dot (.) = left
        //Dash(_) = right
    }
      
   
    //Function to put the code table to construc the tree
    //references to a method that put Dot(.) to left node and Dase(-) to right
    	
    public static void putTree(char c, String s,Node root) // c = the word, s = ._._ ...., root = tree
    {
     
        Node temp = root;//temp node for go through the tree
              
        for(int i = 0; i< s.length(); i++){ // loop time = s(.._..) length
            
            
            
            char patten = s.charAt(i); // single dot/dash value
    
            
            if(patten == '.'){ //left (.)
                
                if(temp.left == null){ // left node is null node
                     temp.left = new Node('*'); //create new node to left
                     temp = temp.left; //point to left node
                
                }
                else{
                     temp = temp.left; //point to left node, if node exist
                }
                
                
            }
            else{ //right (-)              
                
                if(temp.right == null){ // Same as left method
                     temp.right = new Node('*');
                     temp = temp.right;
                
                }
                else{
                     temp = temp.right;
                }
                              
                
            }
            
            
            
            if ( (i+1) == s.length() ){//max node here, put char value to this node.value

                temp.value = c;

            }

        }
        
    }
       
        

}