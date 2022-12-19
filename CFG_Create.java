
import java.util.HashMap;
import java.util.Map;

class CFGNode{
    int node_number;
    CFGNode exit;
    Statements statements;
    CFGNode refToFirst;
    CFGNode prev;
    CFGNode exitNode; //if condition is false in while condition , then take this as next statement
    CFGNode()
    {

    }
    CFGNode(Statements stat, int node_number)
    {
        statements=stat;
        this.node_number=node_number;
        exit=null;
        prev= null;
    }

}
class DecisionNode extends CFGNode{
    CFGNode ifNode;
    CFGNode elseNode;
    CFGNode predicate;

    DecisionNode(){
    }
    DecisionNode(IfCondition ifCond)
    {
        predicate = new CFGNode(ifCond.pred , ++CFG_Create.countNodes);
        ifNode= new CFGNode((Subexpr)ifCond.e1, ++CFG_Create.countNodes);
        elseNode= new CFGNode((Subexpr)ifCond.e2,++CFG_Create.countNodes);
        exit=new CFGNode();
        refToFirst = null;

    }

    public void display()
    {
        System.out.println("\n\nPredicate Node: "+ predicate.node_number+" : "+ predicate.statements);
        System.out.println("\n\nDecision node \nIf Node "
                + ifNode.node_number+ " : "
                +  ifNode.statements + "  \nElse Node "
                + elseNode.node_number+ " :  "
                +  elseNode.statements);
    }

}

class LoopNode extends CFGNode{

    CFGNode entry; //predicate node


    LoopNode(){
    }
    LoopNode(Loop loop )
    {
        entry=new CFGNode((Expr)loop.pred , ++CFG_Create.countNodes);
        CFG_Create.map.put(entry.node_number, entry);
        node_number= entry.node_number;//add node number of entry node, as a node number here
        //CFGNode temp=entry.exit;
        CFGNode  temp=entry;
        //System.out.println("loop started, entry= "+entry);
        exit = new CFGNode();

        for(LoopSubBlock lsb: loop.ls.subStatements)
        {
            //System.out.println(lsb.getClass().getName());
            if(lsb instanceof Assign)
            {
                temp.exit = new CFGNode(((Assign)lsb), ++CFG_Create.countNodes);
                //System.out.println("loop assign current "+temp);
                CFG_Create.map.put(temp.exit.node_number, temp.exit);
                temp=temp.exit;
                //System.out.println("after "+temp);
            }
            else if(lsb instanceof IfCondition){
                DecisionNode dn = new DecisionNode((IfCondition) lsb);
                temp.exit=dn;
                CFG_Create.map.put(dn.predicate.node_number , dn.predicate);
                CFG_Create.map.put(dn.ifNode.node_number, dn.ifNode);
                CFG_Create.map.put(dn.elseNode.node_number, dn.elseNode);
                //System.out.println("loop if current "+temp);
                temp=temp.exit;
                dn.ifNode.exit = dn.elseNode.exit = temp;
                //System.out.println("\n\n ---------------------------------------------Inside, "+(dn.ifNode.exit == temp) + " " +(dn.elseNode.exit == temp)+(dn.exit == temp)+"------------------\n\n");

                //System.out.println("after "+temp);
            }

        }


        //System.out.println(temp.refToFirst);
        temp.refToFirst= entry;
        //System.out.println("loop ended "+ (temp.refToFirst == entry) +"  "+temp+"  "+temp.refToFirst+"  "+entry);
    }
    public void display()
    {
        CFGNode temp=entry;
        //System.out.println(temp.refToFirst);
        //System.out.println("ref to first "+temp.refToFirst);

        while(temp.refToFirst == null )
        {
            //System.out.println("node "+temp.getClass().getName());
            if (temp instanceof DecisionNode)
            {
                //System.out.println("\nLoop if current "+temp);
                ((DecisionNode) temp).display();

                temp = ((DecisionNode)temp).exit;

            }
            else if(temp instanceof CFGNode)
            {
                //System.out.println("Loop cfg current "+temp);
                System.out.println("CFG node : "+temp.node_number+"  "+temp.statements.toString());
                temp= temp.exit;
                //System.out.println("\nNext node "+temp);
            }
            //System.out.println("Loop Next node "+temp);

        }
        //last node
        //System.out.println("Loop ended");
        if (temp instanceof DecisionNode)
        {
            System.out.println("Decision node \n"
                    +((DecisionNode)temp).ifNode.node_number+"  "
                    +  ((DecisionNode)temp).ifNode.statements.toString() + " \n "
                    +((DecisionNode)temp).elseNode.node_number+ "  "
                    +  ((DecisionNode)temp).elseNode.statements.toString());

            //System.out.println(((DecisionNode)temp).exit.getClass().getName());
            temp = ((DecisionNode)temp).exit;

        }
        if(temp instanceof CFGNode)
        {
            System.out.println("CFG node : "+temp.node_number+"  "+temp.statements.toString());
            temp= temp.exit;
        }

        //System.out.println("\n\n Done loop, next node "+temp );

    }

}

public class CFG_Create {
    public static int countNodes=0;
    CFGNode root=null , temp=null, leaf=null;
    public static HashMap<Integer, CFGNode> map = new HashMap<>();

    public void create(FunctionDeclaration function)
    {
        for(Declaration d : function.declarationList)
        {
            if(root == null)
            {
                root= new CFGNode((Declaration)d , ++countNodes);
                map.put(root.node_number, root);
                temp=root;
                continue;
            }
            root.exit = new CFGNode((Declaration)d , ++countNodes);
            map.put(root.exit.node_number, root.exit);
            root.exit.prev = root;
            root = root.exit;
            //System.out.println(d.toString());
        }

        bodyCreate(function.body,root);

        root = temp;
        System.out.println("\n\n nodes= "+countNodes + "  "+root +"  \n\n");
    }

    public void bodyCreate(FunctionBody body,CFGNode root)
    {
        blockCreate(body.block,root);
    }

    public void blockCreate(Block block,CFGNode root)
    {

        if(block.declarationList == null)
        {
            sequenceCreate(block.seq,root);
            return;
        }
        for(Declaration d : block.declarationList)
        {
            root.exit = new CFGNode((Declaration)d , ++countNodes);
            //System.out.println("current decl list "+root);
            map.put(root.exit.node_number, root.exit);
            root.exit.prev = root;
            root = root.exit;
            //System.out.println("after decl list "+root);
        }
        sequenceCreate(block.seq,root);
        return;
    }

    public void sequenceCreate(Sequence seq,CFGNode root)
    {
        for(Instruction i : seq.instr)
        {
            if(i instanceof Assign)
            {
                root.exit = new CFGNode((Assign)i , ++countNodes);
                map.put(root.exit.node_number, root.exit);
                root.exit.prev = root;
                //System.out.println("\n\nassign "+root);
                root= root.exit;
                //System.out.println("after "+root);

            }
            else if(i instanceof Return)
            {
                root.exit= new CFGNode((Return)i , ++countNodes);
                map.put(root.exit.node_number, root.exit);
                root.exit.prev = root;
                //System.out.println("\n\nreturn "+root);
                root=root.exit;
                //System.out.println("after "+root);

            }
            else if(i instanceof IfCondition)
            {
                DecisionNode ifN= new DecisionNode((IfCondition) i);
                root.exit =ifN;
                map.put(ifN.predicate.node_number, ifN.predicate);
                map.put(ifN.ifNode.node_number, ifN.ifNode);
                map.put(ifN.elseNode.node_number , ifN.elseNode);

                root.exit.prev = root;
                //System.out.println("\n\nif "+root);
                root = root.exit;
                ifN.ifNode.exit = ifN.elseNode.exit = root;
                //System.out.println("\n\n ---------------------------------------------Inside, "+(ifN.ifNode.exit == temp) + " " +(ifN.elseNode.exit == temp)+(ifN.exit == temp)+"------------------\n\n");
                //System.out.println("after "+root);

            }
            else if(i instanceof Loop)
            {

                LoopNode loopNode= new LoopNode((Loop) i);
                root.exit = loopNode;

                root.exit.prev = root;
                //System.out.println("\n\nloop node "+root);
                root = root.exit;
                loopNode.entry.exitNode = root.exit; //exit node when condition is false.Add it as part of entry node.
                //System.out.println("after "+root);
            }
        }
        leaf = root;


    }

    public void display()
    {

        CFGNode temp =root;
        if(root == null)
            return;


        while(root != null)
        {

            if (root instanceof DecisionNode)
            {
                //System.out.println("if current "+root);
                ((DecisionNode) root).display();
                root = ((DecisionNode)root).exit;
            }

            else if ( root instanceof LoopNode)
            {
                //System.out.println("\n\nLoop current "+root);
                ((LoopNode)root).display();
                root = ((LoopNode)root).exit;

            }

            else if(root instanceof CFGNode)
            {
                //System.out.println("\n\ncfg current "+root);
                System.out.println("\nCFG node : "+root.node_number+"   "+root.statements);
                root = root.exit;
            }

            //System.out.println("next node "+root+"\n\n");
        }
        root=temp;
    }


    public void displayPrev()
    {

        CFGNode temp = leaf;
        if(temp == null)
            return;


        while(temp != null)
        {

            if (temp instanceof DecisionNode)
            {
                //System.out.println("if current "+temp);
                System.out.println("\n\nDecision node \nIf Node "
                        +((DecisionNode)temp).ifNode.node_number+ ":  "
                        +  ((DecisionNode)temp).ifNode.statements + " \n Else node "
                        +((DecisionNode)temp).elseNode.node_number+ " : "
                        +  ((DecisionNode)temp).elseNode.statements);

                //System.out.println(((DecisionNode)).extit.getClass().getName());
                temp = ((DecisionNode)temp).prev;

            }

            else if ( temp instanceof LoopNode)
            {
                //System.out.println("\n\nLoop current "+root);
                ((LoopNode)temp).display();
                temp = ((LoopNode)temp).prev;

            }

            else if(temp instanceof CFGNode)
            {
                //System.out.println("\n\ncfg current "+temp);
                System.out.println("\nCFG node : "+temp.node_number+"   "+temp.statements);
                temp = temp.prev;
            }

            //System.out.println("next node "+temp+"\n\n");
        }
        //root=temp;
    }

    public void displayMap()
    {
        for (Map.Entry entry: map.entrySet())
        {
            System.out.print(entry.getKey()+" : ");
            System.out.println(((CFGNode)entry.getValue()).statements);
        }

    }

}
