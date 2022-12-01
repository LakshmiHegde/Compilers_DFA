import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class Driver {

	public static void main(String[] args) 
	{
    	
    		String input = args[0];
	
		try {
		  	//System.out.println("Parsing " + input);
			parser p = new parser(new IdLexer(new FileReader(input)));
      		//System.out.println(p);
			FunctionDeclaration program = (FunctionDeclaration)(p.parse().value);
			System.out.println(program.toString());
			CFG_Create cfg_create = new CFG_Create();
			cfg_create.create(program);
			System.out.println("After create "+ cfg_create.root);
			//cfg_create.display();
			//cfg_create.displayMap();
			cfg_create.displayPrev();

			/*DFA dfa = new DFA(cfg_create.countNodes);
			System.out.println("After dis "+cfg_create.root+"  \n\n ");
			dfa.get_pred_succ(cfg_create.root);
			System.out.println("\n\n Done predecessor\n\n");
			dfa.display();

			dfa.reaching_definitions();*/
		}
	
		catch(FileNotFoundException e) {
      			System.out.println("File not found!");
			System.exit(1);
    		}
	
		catch(Exception e) {
      			System.out.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
    		}
	}
	
	

} 

