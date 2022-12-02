import java.util.*;

public class DFA {
    ArrayList<CFGNode>[] predecessor, successor;
    DFA(ArrayList<CFGNode>[] predecessor, ArrayList<CFGNode>[] successor )
    {
        this.predecessor = predecessor;
        this.successor = successor;
    }


    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list)
    {

        ArrayList<T> newList = new ArrayList<T>();

        for (T element : list) {
            if (!newList.contains(element)) {

                newList.add(element);
            }
        }

        return newList;
    }


    public boolean checkIfEquals(HashMap<String, ArrayList<Integer>>[] m1 , HashMap<String, ArrayList<Integer>>[] m2)
    {
        int eq=1;
        for(int i=0; i<m1.length; i++)
        {
            HashMap<String, ArrayList<Integer>> first = m1[i];
            HashMap<String, ArrayList<Integer>> second = m2[i];

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


    public void expr_use(Expr e1, Expr e2, ArrayList<String> use)
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

    public boolean arrayEquals(ArrayList<String >[] a1, ArrayList<String>[] a2)
    {
        int[] c1;
        int[] c2;

        for (int i=1;i<a1.length;i++)
        {
            c1=new int[256];
            c2=new int[256];


            if(a1[i].size() != a2[i].size())
                return false;

            for(int j=0;j<a1[i].size();j++)
            {
                c1[a1[i].get(j).charAt(0) - 'a']++;
                c2[a2[i].get(j).charAt(0) - 'a']++;
            }

            for(int k=0;k<256;k++)
            {
                if(c1[k] != c2[k])
                    return false;
            }
        }

        return true;

    }
}



class ReachingDefinitions extends DFA{
    HashMap<String,ArrayList<Integer>>[] in , out;
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

    public void displayMap(HashMap<String,ArrayList<Integer>> map)
    {
        for (Map.Entry entry: map.entrySet())
        {
            System.out.print(entry.getKey()+" : ");
            System.out.println(entry.getValue());
        }

    }

    public HashMap<String, ArrayList<Integer>> union(ArrayList<CFGNode> list , HashMap<String,ArrayList<Integer>>[] map)
    {
        if(list.size() == 0)
            return new HashMap<>();

        HashMap<String,ArrayList<Integer>> unionOut = new HashMap<>();

        for(CFGNode l : list)
        {
            for(Map.Entry<String , ArrayList<Integer>> entry: map[l.node_number].entrySet())
            {
                String key = entry.getKey();
                ArrayList<Integer> values= new ArrayList<>(entry.getValue());

                if(unionOut.containsKey(key))
                {
                    ArrayList<Integer> append_to_this_list = unionOut.get(key);
                    unionOut.remove(key);
                    Iterator<Integer> iter = values.iterator();

                    while (iter.hasNext()) {
                        Integer val = iter.next();

                        append_to_this_list.add(val);
                    }

                    append_to_this_list= removeDuplicates(append_to_this_list);

                    unionOut.put(key, append_to_this_list);
                }
                else{
                    unionOut.put(key, values);
                }
            }

        }
        return unionOut;
    }

    public HashMap<String, ArrayList<Integer>> computeOut(HashMap<String,ArrayList<Integer>> in, CFGNode node) {
        Statements expression = node.statements;//gen [n]
        HashMap<String,ArrayList<Integer>> in_copy=new HashMap<>();
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
                    ArrayList<Integer> values= new ArrayList<Integer>();
                    values.add(node.node_number);
                    in_copy.put(id, values);
                    found=1;
                    break;
                }
            }
            if(found == 0)
            {
                ArrayList<Integer> values= new ArrayList<Integer>();
                values.add(node.node_number);
                in_copy.put(id, values);
            }
            return in_copy;
        }
        else {

            return in_copy;
        }

    }

    public void reaching_definitions()
    {
        HashMap<String,ArrayList<Integer>>[] in_prev , out_prev;
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
            System.out.println("in[ "+i+"] = ");
            displayMap(in[i]);
            System.out.println("\nout[ "+i+"] = ");
            displayMap(out[i]);
            System.out.println("\n\n");
        }
    }

}

class LivenessAnalysis extends DFA{
    ArrayList<String>[] in_la, out_la;
    LivenessAnalysis(int nodes, ArrayList<CFGNode>[] predecessor, ArrayList<CFGNode>[] successor){
        super(predecessor, successor);
        in_la = new ArrayList[nodes+1];
        out_la = new ArrayList[nodes+1];

        for (int i=0;i<=nodes;i++)
        {
            in_la[i] = new ArrayList<>();
            out_la[i] = new ArrayList<>();
        }

    }

    public ArrayList<String> union_LA(ArrayList<CFGNode> succ_list , ArrayList<String>[] in )
    {
        if(succ_list.size() == 0)
            return new ArrayList<>();

        ArrayList<String> unionOut = new ArrayList<>();

        for(CFGNode l : succ_list)
        {
            for (String s: in[l.node_number])
                unionOut.add(s);
        }
        removeDuplicates(unionOut);
        return unionOut;
    }

    public ArrayList<String> computeIn(ArrayList<String> out, CFGNode node)
    {
        Statements expression = node.statements;//gen [n]
        ArrayList<String> out_copy=new ArrayList<>(out);

        ArrayList<String> use= new ArrayList<>();

        if( expression instanceof Assign) //not an assignment statement
        {
            Expr expr = ((Assign) expression).right;//use
            //Computing USE
            if(expr instanceof AddExpr)
            {
                expr_use(((AddExpr) expr).left, ((AddExpr) expr).right, use);
                use=removeDuplicates(use);
            }
            else if (expr instanceof MulExpr)
            {
                expr_use(((MulExpr) expr).left, ((MulExpr) expr).right, use);
                use=removeDuplicates(use);
            }
            else if(expr instanceof IdExpr)
            {
                use.add(expr.toString());
            }

            //Computing out\def
            //System.out.println("Computing out def \n\n");
            String id= ((Assign) expression).left.toString();//def
            //System.out.println("def= "+id+"  out_copy= "+out_copy);
            for(int i=0;i<out_copy.size();i++)
            {
                if(out_copy.get(i).equals(id))
                {
                    out_copy.remove(out_copy.get(i));
                    break;
                }
            }
            //System.out.println("\nAfter removing, "+out_copy);

            //System.out.println("\n\n union use= "+use);
            //union use and out_copy
            for(String s: use)
            {
                out_copy.add(s);
            }
            out_copy=removeDuplicates(out_copy);
            //System.out.println("\nFinal res, "+out_copy);
            return out_copy;

        }
        else if (expression instanceof Return)
        {
            Expr expr= ((Return) expression).e;
            if(expr instanceof AddExpr)
            {
                expr_use(((AddExpr) expr).left, ((AddExpr) expr).right, use);
                use= removeDuplicates(use);
            }
            else if (expr instanceof MulExpr)
            {
                expr_use(((MulExpr) expr).left, ((MulExpr) expr).right, use);
                use=removeDuplicates(use);
            }
            else if(expr instanceof IdExpr)
            {
                use.add(expr.toString());
            }

            //union use and out_copy
            for(String s: use)
            {
                out_copy.add(s);
            }
            out_copy=removeDuplicates(out_copy);
            return out_copy;
        }
        else {
            //declaration node or boolean constant
            //no use, no def

            return out_copy;
        }
    }

    public void liveness_analysis()
    {
        ArrayList<String>[] in_prev , out_prev;
        in_prev= new ArrayList[in_la.length];
        out_prev= new ArrayList[out_la.length];

        for(int i=0;i<in_la.length;i++)
        {
            in_prev[i] =new ArrayList<>();
            out_prev[i] = new ArrayList<>();
        }

        do
        {
            for (int i=successor.length-1;i>=1;i--)
            {

                in_prev[i] = new ArrayList<>(in_la[i]);
                out_prev[i] = new ArrayList<>(out_la[i]);

                in_la[i] = computeIn(out_la[i], CFG_Create.map.get(i));
                out_la[i] = union_LA(successor[i], in_la);

                in_la[i] = removeDuplicates(in_la[i]);
                out_la[i] = removeDuplicates(out_la[i]);

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

