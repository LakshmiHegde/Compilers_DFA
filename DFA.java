import java.util.*;

public class DFA {
    ArrayList<CFGNode>[] predecessor, successor;
    DFA(ArrayList<CFGNode>[] predecessor, ArrayList<CFGNode>[] successor )
    {
        this.predecessor = predecessor;
        this.successor = successor;
    }

    public boolean checkIfEquals(HashMap<String, HashSet<Integer>>[] m1 , HashMap<String, HashSet<Integer>>[] m2)
    {
        int eq=1;
        for(int i=0; i<m1.length; i++)
        {
            HashMap<String, HashSet<Integer>> first = m1[i];
            HashMap<String, HashSet<Integer>> second = m2[i];

            if(!first.equals(second))
            {
                eq=0;
                break;
            }
        }
        if(eq == 1)
            return true;
        return false;
    }

    public void expr_use(Expr e1, Expr e2, HashSet<String> use)
    {
        if(e1 instanceof IdExpr)
        {
            use.add(e1.toString());
        }
        else {
            if(e1 instanceof AddExpr)
            {
                expr_use(((AddExpr)e1).left , ((AddExpr)e1).right , use);
            }
            else if(e1 instanceof MulExpr)
            {
                expr_use(((MulExpr)e1).left , ((MulExpr)e1).right , use);
            }
        }

        if(e2 instanceof IdExpr)
        {
            use.add(e2.toString());
        }
        else{
            if(e2 instanceof AddExpr)
            {
                expr_use(((AddExpr)e2).left , ((AddExpr)e2).right , use);
            }
            else if(e2 instanceof MulExpr)
            {
                expr_use(((MulExpr)e2).left , ((MulExpr)e2).right , use);
            }
        }



    }

    public boolean arrayEquals(HashMap<String, HashSet<Integer>>[] a1, HashMap<String, HashSet<Integer>>[] a2)
    {
        for (int i=0;i< a1.length;i++)
        {
            if(!a1[i].keySet().equals(a2[i].keySet()))
                return false;
        }
        return true;
    }
    public boolean arrayEquals(HashSet<String >[] a1, HashSet<String>[] a2)
    {
        for (int i=0;i<a1.length;i++)
        {
            if(!a1[i].equals(a2[i]))
                return false;
        }

        return true;

    }

    public void evaluateExpression(Expr expr, HashSet<String> use)
    {
        if(expr instanceof AddExpr)
        {
            expr_use(((AddExpr) expr).left, ((AddExpr) expr).right, use);
        }
        else if (expr instanceof MulExpr)
        {
            expr_use(((MulExpr) expr).left, ((MulExpr) expr).right, use);
        }
        else if(expr instanceof IdExpr)
        {
            use.add(expr.toString());
        }
    }

}


class ReachingDefinitions extends DFA{
    HashMap<String,HashSet<Integer>>[] in , out;
    ReachingDefinitions(int nodes, ArrayList<CFGNode>[] predecessor, ArrayList<CFGNode>[] successor){
        super(predecessor, successor );
        in = new HashMap[nodes+1];
        out= new HashMap[nodes+1];

        for (int i=0;i<=nodes;i++)
        {
            in[i] = new HashMap<>();
            out[i] = new HashMap<>();
        }
    }

    public void displayMap(HashMap<String,HashSet<Integer>> map)
    {
        for (Map.Entry entry: map.entrySet())
        {
            System.out.print(entry.getKey()+" : ");
            System.out.print(entry.getValue()+"   ");
        }

        System.out.println();
    }

    public HashMap<String, HashSet<Integer>> union(ArrayList<CFGNode> list , HashMap<String,HashSet<Integer>>[] map)
    {
        if(list.size() == 0)
            return new HashMap<>();

        HashMap<String,HashSet<Integer>> unionOut = new HashMap<>();

        for(CFGNode l : list)//for each predecessor
        {
            for(Map.Entry<String , HashSet<Integer>> entry: map[l.node_number].entrySet())//take out of predecessor
            {
                String key = entry.getKey();
                HashSet<Integer> values= new HashSet<>(entry.getValue());

                if(unionOut.containsKey(key))
                {
                    unionOut.get(key).addAll(values);
                }
                else{
                    unionOut.put(key, values);
                }
            }

        }
        return unionOut;
    }

    public HashMap<String, HashSet<Integer>> computeOut(HashMap<String,HashSet<Integer>> in, CFGNode node)
    {
        Statements expression = node.statements;//gen [n]
        HashMap<String,HashSet<Integer>> in_copy=new HashMap<>();
        in_copy.putAll(in);

        if( expression instanceof Assign) //not an assignment statement
        {
            String id= ((Assign) expression).left.toString();

            int found=0;
            for(Map.Entry entry: in_copy.entrySet())
            {
                if(entry.getKey().equals(id))
                {
                    in_copy.remove(entry.getKey());
                    HashSet<Integer> values= new HashSet<>();
                    values.add(node.node_number);
                    in_copy.put(id, values);
                    found=1;
                    break;
                }
            }
            if(found == 0)
            {
                HashSet<Integer> values= new HashSet<>();
                values.add(node.node_number);
                in_copy.put(id, values);
            }
            return in_copy;
        }
        else if(expression instanceof Subexpr)
        {
            if(((Subexpr) expression).a != null)
            {
                String id= ((Subexpr) expression).a.left.toString();

                int found=0;
                for(Map.Entry entry: in_copy.entrySet())
                {
                    if(entry.getKey().equals(id))
                    {
                        in_copy.remove(entry.getKey());
                        HashSet<Integer> values= new HashSet<>();
                        values.add(node.node_number);
                        in_copy.put(id, values);
                        found=1;
                        break;
                    }
                }
                if(found == 0)
                {
                    HashSet<Integer> values= new HashSet<>();
                    values.add(node.node_number);
                    in_copy.put(id, values);
                }
                return in_copy;
            }
        }
        return in_copy;
    }

    public void reaching_definitions()
    {
        HashMap<String,HashSet<Integer>>[] in_prev , out_prev;
        in_prev= new HashMap[in.length];
        out_prev= new HashMap[out.length];

        for(int i=0;i<in.length;i++)
        {
            in_prev[i] =new HashMap<>();
            out_prev[i] = new HashMap<>();
        }

        int cnt=1;


        do
        {
            for (int i=2;i<predecessor.length;i++)
            {

                in_prev[i].putAll(in[i]);
                out_prev[i].putAll(out[i]);

                in[i] = union(predecessor[i] , out);
                out[i] = computeOut(in[i] , CFG_Create.map.get(i));

            }

        }
        while(!(checkIfEquals(in_prev, in)) || !(checkIfEquals(out_prev,out)) );

        for (int i=1;i<predecessor.length;i++)
        {
            System.out.print("in[ "+i+"] = ");
            displayMap(in[i]);
            System.out.print("\nout[ "+i+"] = ");
            displayMap(out[i]);
            System.out.println("\n\n");
        }
    }

}

class LivenessAnalysis extends DFA{

    HashSet<String>[] in_la, out_la;
    LivenessAnalysis(int nodes, ArrayList<CFGNode>[] predecessor, ArrayList<CFGNode>[] successor){
        super(predecessor, successor);
        in_la = new HashSet[nodes+1];
        out_la = new HashSet[nodes+1];

        for (int i=0;i<=nodes;i++)
        {
            in_la[i] = new HashSet<>();
            out_la[i] = new HashSet<>();
        }

    }

    public HashSet<String> union_LA(ArrayList<CFGNode> succ_list , HashSet<String>[] in )
    {
        if(succ_list.size() == 0)
            return new HashSet<>();

        HashSet<String> unionOut = new HashSet<>();

        for(CFGNode l : succ_list)//for each successor
        {
            for (String s: in[l.node_number]) //get its in
                unionOut.add(s);
        }

        return unionOut;
    }

    public HashSet<String> computeIn(HashSet<String> out, CFGNode node)
    {
        Statements expression = node.statements;//gen [n]
        HashSet<String> out_copy=new HashSet<>(out);

        //System.out.println("in comp "+node.node_number+"  "+expression+"  "+expression.getClass());
        HashSet<String> use= new HashSet<>();

        if( expression instanceof Assign) //not an assignment statement
        {
            Expr expr = ((Assign) expression).right;//use
            //Computing USE
            evaluateExpression(expr, use);

            //Computing out\def
            //System.out.println("Computing out def \n\n");

            String id= ((Assign) expression).left.toString();//def
            //System.out.println("def= "+id+"  out_copy= "+out_copy);

            if(out_copy.contains(id))
            {
                out_copy.remove(id);
            }

            //System.out.println("\nAfter removing, "+out_copy);

            //System.out.println("\n\n union use= "+use);
            //union use and out_copy
            out_copy.addAll(use);

            //System.out.println("\nFinal res, "+out_copy);
            return out_copy;

        }
        else if (expression instanceof Return)
        {
            Expr expr= ((Return) expression).e;
            evaluateExpression(expr, use);
            out_copy.addAll(use);

            return out_copy;
        }
        else if(expression instanceof Subexpr)
        {
            if(((Subexpr) expression).a == null)
            {
                Expr expr = ((Subexpr) expression).e;
                evaluateExpression(expr, use);
                out_copy.addAll(use);

                return out_copy;

            }
            else
            {
                Expr expr = ((Subexpr) expression).a.right;
                //Computing USE
                evaluateExpression(expr,use);

                //Computing out\def
                //System.out.println("Computing out def \n\n");
                String id= ((Subexpr) expression).a.left.toString();//def
                //System.out.println("def= "+id+"  out_copy= "+out_copy);
                if(out_copy.contains(id))
                {
                    out_copy.remove(id);
                }
                //union use and out_copy
                out_copy.addAll(use);
                //System.out.println("\nFinal res, "+out_copy);
                return out_copy;
            }
        }
        else {
            //declaration node or boolean constant
            //no use, no def
            return out_copy;
        }
    }

    public void liveness_analysis()
    {
        HashSet<String>[] in_prev , out_prev;
        in_prev= new HashSet[in_la.length];
        out_prev= new HashSet[out_la.length];

        for(int i=0;i<in_la.length;i++)
        {
            in_prev[i] =new HashSet<>();
            out_prev[i] = new HashSet<>();
        }

        do
        {
            for (int i=successor.length-1;i>=1;i--)
            {
                in_prev[i] = new HashSet<>(in_la[i]);
                out_prev[i] = new HashSet<>(out_la[i]);

                in_la[i] = computeIn(out_la[i], CFG_Create.map.get(i));
                out_la[i] = union_LA(successor[i], in_la);

            }
        }
        while( !arrayEquals(in_la,in_prev) || !arrayEquals(out_la,out_prev) );


        for(int i=1;i<successor.length;i++)
        {
            System.out.println("in[ " + i + "] = " + in_la[i]);
            System.out.println("out[ " + i + "] = " + out_la[i]);
            System.out.println("\n\n");
        }

    }
}

