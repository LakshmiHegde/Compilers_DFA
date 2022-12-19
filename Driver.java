
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;


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

			Pred_Succ_List pred_succ_list = new Pred_Succ_List(cfg_create.countNodes , cfg_create.root);
			pred_succ_list.get_pred();
			pred_succ_list.get_succ();

			LivenessAnalysis livenessAnalysis = new LivenessAnalysis(cfg_create.countNodes, pred_succ_list.predecessor , pred_succ_list.successor);
			System.out.println("-------------Liveness analysis--------------");
			livenessAnalysis.liveness_analysis();

			System.out.println("Liveness analysis");
			LVAnalysis lvAnalysis = new LVAnalysis(pred_succ_list.successor, cfg_create.countNodes);
			lvAnalysis.backwardAlgorithm();


			ReachingDefinitions reachingDefinitions = new ReachingDefinitions(cfg_create.countNodes, pred_succ_list.predecessor , pred_succ_list.successor);
			System.out.println("------------------Reaching Definitions-----------------");
			reachingDefinitions.reaching_definitions();

			System.out.println("Reaching Definitions");
			RDAnalysis rdAnalysis = new RDAnalysis(pred_succ_list.predecessor,  cfg_create.countNodes);
			rdAnalysis.forwardAlgorithm();

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

