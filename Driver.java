
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
			System.out.println("Function: ");
			System.out.println(program.toString());

			CFG_Create cfg_create = new CFG_Create();
			cfg_create.create(program);
			System.out.println("\nControl flow graph: ");
			cfg_create.display();
			//cfg_create.displayMap();
			//cfg_create.displayPrev();

			Pred_Succ_List pred_succ_list = new Pred_Succ_List(cfg_create.countNodes , cfg_create.root);
			pred_succ_list.get_pred();
			pred_succ_list.get_succ();
			pred_succ_list.display();

			//DFA dfa = new DFA(cfg_create.countNodes,pred_succ_list.predecessor,pred_succ_list.successor );
			ReachingDefinitions reachingDefinitions = new ReachingDefinitions(cfg_create.countNodes, pred_succ_list.predecessor , pred_succ_list.successor);
			System.out.println("Reaching Definitions");
			reachingDefinitions.reaching_definitions();

			LivenessAnalysis livenessAnalysis = new LivenessAnalysis(cfg_create.countNodes, pred_succ_list.predecessor , pred_succ_list.successor);
			System.out.println("Liveness analysis");
			livenessAnalysis.liveness_analysis();

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

