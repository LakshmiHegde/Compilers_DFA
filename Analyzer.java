import java.util.*;

abstract class Meet{
    public abstract Set execute(Set a,Set b);
}
class Union extends Meet{
    public Set execute(Set s1, Set s2)
    {
        s1.addAll(s2);
        return s1;
    }
}

class BasicUtilities{
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

abstract class Analyzer {
    public Meet meet;
    HashMap<String, HashSet<Integer >>[] in,out;
    ArrayList<CFGNode>[] list;

    Analyzer(Meet meet, int nodes, ArrayList<CFGNode>[] list)
    {
        this.meet = meet;
        this.list = list;

        in = new HashMap[nodes+1];
        out = new HashMap[nodes+1];
        for (int i=0;i<= nodes;i++)
        {
            in[i]= new HashMap();
            out[i] = new HashMap();
        }
    }
    abstract public HashMap<String, HashSet<Integer>> union(ArrayList<CFGNode> list , HashMap<String, HashSet<Integer>>[] map );
    abstract public HashMap<String, HashSet<Integer>> compute(HashMap<String,HashSet<Integer>> in, CFGNode node);

    public void forwardAlgorithm(){
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
            for (int i=2;i<list.length;i++)
            {

                in_prev[i].putAll(in[i]);
                out_prev[i].putAll(out[i]);

                in[i] = union(list[i] , out);
                out[i] = compute(in[i] , CFG_Create.map.get(i));

            }

        }
        while( ! new BasicUtilities().checkIfEquals(out_prev,out) );

        for (int i=1;i<list.length;i++)
        {
            System.out.println("in[ "+i+"] = "+in[i]);
            //displayMap(in[i]);
            System.out.println("\nout[ "+i+"] = "+out[i]);
            //displayMap(out[i]);
            System.out.println("\n\n");
        }
    }

    public void backwardAlgorithm(){
        HashMap<String,HashSet<Integer>>[] in_prev , out_prev;
        in_prev= new HashMap[in.length];
        out_prev= new HashMap[out.length];

        for(int i=0;i<in.length;i++)
        {
            in_prev[i] =new HashMap<>();
            out_prev[i] = new HashMap<>();
        }

        do {
            for (int i = list.length - 1; i >= 1; i--) {
                in_prev[i].putAll(in[i]);
                out_prev[i].putAll(out[i]);
                in[i] = compute(out[i], CFG_Create.map.get(i));
                out[i] = union(list[i], in);
            }

        }
        while( ! new BasicUtilities().arrayEquals(in,in_prev) );

        for(int i=1;i<list.length;i++)
        {
            System.out.println("in[ " + i + "] = " + in[i].keySet());
            System.out.println("out[ " + i + "] = " + out[i].keySet());
            System.out.println("\n\n");
        }
    }

}

class RDAnalysis extends Analyzer{
    RDAnalysis(ArrayList<CFGNode>[] successor, int nodes) {
        super(new Union(), nodes, successor);
    }
    @Override
    public HashMap<String, HashSet<Integer>> union(ArrayList<CFGNode> list, HashMap<String, HashSet<Integer>>[] map)
    {
        HashMap<String,HashSet<Integer>> unionOut = new HashMap<>();

        for(CFGNode l : list)//for each predecessor
        {
            for(Map.Entry<String , HashSet<Integer>> entry: map[l.node_number].entrySet())//take out of predecessor
            {
                String key = entry.getKey();
                HashSet<Integer> values= new HashSet<>(entry.getValue());
                if(unionOut.containsKey(key))
                {
                    HashSet un_set = (HashSet) meet.execute(unionOut.get(key), values);
                    unionOut.put(key,un_set );
                }
                else{
                        unionOut.put(key, values);
                    }
            }

        }

        return unionOut;
    }
    @Override
    public HashMap<String, HashSet<Integer>> compute(HashMap<String,HashSet<Integer>> in, CFGNode node)
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


}

class LVAnalysis extends Analyzer{
    LVAnalysis(ArrayList<CFGNode>[] successor, int nodes) {
        super(new Union(), nodes, successor);
    }
    @Override
    public HashMap<String, HashSet<Integer>> union(ArrayList<CFGNode> list , HashMap<String, HashSet<Integer>>[] map )
    {

        HashMap<String, HashSet<Integer>> unionOut = new HashMap<>();
        HashSet<String> un_set = new HashSet<>();

        for(CFGNode l : list)//for each succ
        {
            un_set = (HashSet<String>) meet.execute( un_set, map[l.node_number].keySet());
        }

        for(String s: un_set)
        {
            unionOut.put(s , new HashSet<>());
        }
        return unionOut;
    }

    @Override
    public HashMap<String, HashSet<Integer>> compute(HashMap<String, HashSet<Integer>> out, CFGNode node)
    {

        Statements expression = node.statements;//gen [n]
        HashSet<String> use= new HashSet<>();

        HashMap<String, HashSet<Integer>> result = new HashMap<>();

        if( expression instanceof Assign) //not an assignment statement
        {
            Expr expr = ((Assign) expression).right;//use
            //Computing USE
            new BasicUtilities().evaluateExpression(expr, use);
            String id= ((Assign) expression).left.toString();//def

            if(out.containsKey(id))//out\def
                out.remove(id);

            //use union out\def
            for(String  s: use)
            {
                out.put(s, new HashSet<>());
            }
            return out;
        }
        else if (expression instanceof Return)
        {
            Expr expr= ((Return) expression).e;
            new BasicUtilities().evaluateExpression(expr, use);
            for(String  s: use)
            {
                out.put(s, new HashSet<>());
            }
            return out;
        }
        else if(expression instanceof Subexpr)
        {
            if(((Subexpr) expression).a == null)
            {
                Expr expr = ((Subexpr) expression).e;
                new BasicUtilities().evaluateExpression(expr, use);
                for(String  s: use)
                {
                    out.put(s, new HashSet<>());
                }
                return out;
            }
            else
            {
                Expr expr = ((Subexpr) expression).a.right;
                new BasicUtilities().evaluateExpression(expr, use);

                String id= ((Subexpr) expression).a.left.toString();//def

                if(out.containsKey(id))//out\def
                    out.remove(id);

                //use union out\def
                for(String  s: use)
                {
                    out.put(s, new HashSet<>());
                }
                return out;
            }
        }
        else {
            //declaration node or boolean constant
            //no use, no def

            return out;
        }
    }

}
