import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DFA {
    ArrayList<CFGNode>[] predecessor;
    HashMap<String,ArrayList<Integer>>[] in , out;

    DFA(int nodes)
    {
        predecessor= new ArrayList[nodes+1];

        for (int i=0;i<=nodes;i++)
            predecessor[i] = new ArrayList<>();

        in = new HashMap[nodes+1];
        out= new HashMap[nodes+1];

        for (int i=0;i<=nodes;i++)
        {
            in[i] = new HashMap<>();
            out[i] = new HashMap<>();
        }

    }

    public void get_pred_succ(CFGNode root)
    {
        while(root.exit != null)
        {
            if(root instanceof DecisionNode)
            {
                System.out.println("current node is dec node ");
                if(root.exit instanceof DecisionNode)
                {
                    System.out.println("Next is dec node ");
                    predecessor[((DecisionNode) root.exit).ifNode.node_number].add((DecisionNode) ((DecisionNode) root).ifNode);
                    predecessor[((DecisionNode) root.exit).elseNode.node_number].add((DecisionNode) ((DecisionNode) root).ifNode);

                    predecessor[((DecisionNode) root.exit).ifNode.node_number].add((DecisionNode) ((DecisionNode) root).elseNode);
                    predecessor[((DecisionNode) root.exit).elseNode.node_number].add((DecisionNode) ((DecisionNode) root).elseNode);


                    System.out.println("Updated  "+ predecessor[((DecisionNode) root.exit).ifNode.node_number]+"  "
                            + predecessor[((DecisionNode) root.exit).elseNode.node_number]+
                            " \n\n");

                    root= root.exit;
                }
                else if (root.exit instanceof LoopNode)
                {

                    CFGNode temp=((LoopNode)root.exit).entry;
                    predecessor[ ((LoopNode) root.exit).entry.node_number].add(((DecisionNode)root).ifNode);
                    predecessor[ ((LoopNode) root.exit).entry.node_number].add(((DecisionNode)root).elseNode);
                    //System.out.println(temp.refToFirst);
                    System.out.println("ref to first "+temp.refToFirst);

                    while(temp.refToFirst == null )
                    {
                        if (temp instanceof DecisionNode)
                        {
                            if(temp.exit instanceof DecisionNode)
                            {
                                predecessor[((DecisionNode) temp.exit).ifNode.node_number].add( ((DecisionNode) temp).ifNode);
                                predecessor[((DecisionNode) temp.exit).elseNode.node_number].add( ((DecisionNode) temp).ifNode);

                                predecessor[((DecisionNode) temp.exit).ifNode.node_number].add( ((DecisionNode) temp).elseNode);
                                predecessor[((DecisionNode) temp.exit).elseNode.node_number].add( ((DecisionNode) temp).elseNode);
                                temp=temp.exit;
                            }
                            else if(temp.exit instanceof CFGNode)
                            {
                                predecessor[temp.exit.node_number].add(((DecisionNode) temp).ifNode);
                                predecessor[temp.exit.node_number].add(((DecisionNode) temp).elseNode);
                                temp=temp.exit;
                            }
                        }
                        else if(temp instanceof CFGNode)
                        {
                            if(temp.exit instanceof DecisionNode)
                            {
                                predecessor[((DecisionNode) temp.exit).ifNode.node_number].add( temp);
                                predecessor[((DecisionNode) temp.exit).elseNode.node_number].add(temp);
                            }
                            else if(temp.exit instanceof CFGNode)
                            {
                                predecessor[temp.exit.node_number].add(temp);
                            }
                            temp=temp.exit;
                        }
                        //System.out.println("Loop Next node "+temp);


                    }//loop processed

                    //process last node

                    if (temp instanceof DecisionNode)
                    {
                        if(temp.exit instanceof DecisionNode)
                        {
                            predecessor[((DecisionNode) temp.exit).ifNode.node_number].add( ((DecisionNode) temp).ifNode);
                            predecessor[((DecisionNode) temp.exit).elseNode.node_number].add( ((DecisionNode) temp).ifNode);

                            predecessor[((DecisionNode) temp.exit).ifNode.node_number].add( ((DecisionNode) temp).elseNode);
                            predecessor[((DecisionNode) temp.exit).elseNode.node_number].add( ((DecisionNode) temp).elseNode);

                        }
                        else if(temp.exit instanceof CFGNode)
                        {
                            predecessor[temp.exit.node_number].add(((DecisionNode) temp).ifNode);
                            predecessor[temp.exit.node_number].add(((DecisionNode) temp).elseNode);
                        }
                    }
                    else if(temp instanceof CFGNode)
                    {
                        if(temp.exit instanceof DecisionNode)
                        {
                            predecessor[((DecisionNode) temp.exit).ifNode.node_number].add( temp);
                            predecessor[((DecisionNode) temp.exit).elseNode.node_number].add(temp);
                        }
                        else if(temp.exit instanceof CFGNode)
                        {
                            predecessor[temp.exit.node_number].add(temp);
                        }
                    }

                    //Add last node as pred to entry
                    root = root.exit;
                    predecessor[temp.refToFirst.node_number].add(temp);
                    predecessor[root.exit.node_number].add(((LoopNode)root).entry);
                    predecessor[root.exit.node_number].add(temp);
                    root=root.exit;


                }
                else
                {
                    System.out.println("Next is cfg node ");
                    predecessor[root.exit.node_number].add(((DecisionNode) root).ifNode);
                    predecessor[root.exit.node_number].add(((DecisionNode) root).elseNode);
                    System.out.println("Updated  "+ predecessor[root.exit.node_number] + " \n\n");

                    root= root.exit;
                }

            }
            else //it will be cfg node, as we have bypassed loop, by shifting pointer to entry
            {
                //predecessor[root.exit.node_number].add(root);
                System.out.println("curr node is cfg  ");
                //root= root.exit;
                if(root.exit instanceof DecisionNode)
                {
                    System.out.println("Next is dec node ");
                    predecessor[((DecisionNode) root.exit).ifNode.node_number].add(root);
                    predecessor[((DecisionNode) root.exit).elseNode.node_number].add(root);

                    System.out.println("Updated  "+ predecessor[((DecisionNode) root.exit).ifNode.node_number]+"  "
                            + predecessor[((DecisionNode) root.exit).elseNode.node_number]+
                            " \n\n");

                    root= root.exit;
                }
                else if (root.exit instanceof LoopNode)
                {
                    CFGNode temp=((LoopNode)root.exit).entry;
                    predecessor[ ((LoopNode) root.exit).entry.node_number].add(root);

                    //System.out.println(temp.refToFirst);
                    System.out.println("\n\n\n\n-----------------------LOOP NODE----------------------\n\n\n\n");

                    while(temp.refToFirst == null )
                    {
                        System.out.println("Inside loop "+temp.statements);

                        if (temp instanceof DecisionNode)
                        {
                            if(temp.exit instanceof DecisionNode)
                            {

                                predecessor[((DecisionNode) temp.exit).ifNode.node_number].add( ((DecisionNode) temp).ifNode);
                                predecessor[((DecisionNode) temp.exit).elseNode.node_number].add( ((DecisionNode) temp).ifNode);

                                predecessor[((DecisionNode) temp.exit).ifNode.node_number].add( ((DecisionNode) temp).elseNode);
                                predecessor[((DecisionNode) temp.exit).elseNode.node_number].add( ((DecisionNode) temp).elseNode);

                                System.out.println("Next node is dec node\n Updated , pre["+((DecisionNode) temp.exit).ifNode.node_number +"] = "+predecessor[((DecisionNode) temp.exit).ifNode.node_number]+"  pre[ "+((DecisionNode) temp.exit).elseNode.node_number+"]=  "+predecessor[((DecisionNode) temp.exit).elseNode.node_number]);

                                temp=temp.exit;
                            }
                            else if(temp.exit instanceof CFGNode)
                            {

                                predecessor[temp.exit.node_number].add(((DecisionNode) temp).ifNode);
                                predecessor[temp.exit.node_number].add(((DecisionNode) temp).elseNode);
                                System.out.println("Next node is cfg node\n Updated , pre["+temp.exit.node_number+"] = "+predecessor[temp.exit.node_number]);
                                temp=temp.exit;
                            }
                        }
                        else if(temp instanceof CFGNode)
                        {
                            if(temp.exit instanceof DecisionNode)
                            {
                                predecessor[((DecisionNode) temp.exit).ifNode.node_number].add( temp);
                                predecessor[((DecisionNode) temp.exit).elseNode.node_number].add(temp);
                                System.out.println("Next node is dec node\n Updated , pre["+((DecisionNode) temp.exit).ifNode.node_number +"] = "+predecessor[((DecisionNode) temp.exit).ifNode.node_number]+"  pre[ "+((DecisionNode) temp.exit).elseNode.node_number+"]=  "+predecessor[((DecisionNode) temp.exit).elseNode.node_number]);

                            }
                            else if(temp.exit instanceof CFGNode)
                            {
                                predecessor[temp.exit.node_number].add(temp);
                                System.out.println("Next node is cfg node\n Updated , pre["+temp.exit.node_number+"] = "+predecessor[temp.exit.node_number]);

                            }
                            temp=temp.exit;
                        }
                        //System.out.println("Loop Next node "+temp);


                    }//loop processed

                    //process last node

                    if (temp instanceof DecisionNode)
                    {
                        if(temp.exit instanceof DecisionNode)
                        {
                            predecessor[((DecisionNode) temp.exit).ifNode.node_number].add( ((DecisionNode) temp).ifNode);
                            predecessor[((DecisionNode) temp.exit).elseNode.node_number].add( ((DecisionNode) temp).ifNode);

                            predecessor[((DecisionNode) temp.exit).ifNode.node_number].add( ((DecisionNode) temp).elseNode);
                            predecessor[((DecisionNode) temp.exit).elseNode.node_number].add( ((DecisionNode) temp).elseNode);

                            System.out.println("Next node is dec node\n Updated , pre["+((DecisionNode) temp.exit).ifNode.node_number +"] = "+predecessor[((DecisionNode) temp.exit).ifNode.node_number]+"  pre[ "+((DecisionNode) temp.exit).elseNode.node_number+"]=  "+predecessor[((DecisionNode) temp.exit).elseNode.node_number]);


                        }
                        else if(temp.exit instanceof CFGNode)
                        {
                            predecessor[temp.exit.node_number].add(((DecisionNode) temp).ifNode);
                            predecessor[temp.exit.node_number].add(((DecisionNode) temp).elseNode);
                            System.out.println("Next node is cfg node\n Updated , pre["+temp.exit.node_number+"] = "+predecessor[temp.exit.node_number]);

                        }
                    }
                    else if(temp instanceof CFGNode)
                    {
                        if(temp.exit instanceof DecisionNode)
                        {
                            predecessor[((DecisionNode) temp.exit).ifNode.node_number].add( temp);
                            predecessor[((DecisionNode) temp.exit).elseNode.node_number].add(temp);
                            System.out.println("Next node is dec node\n Updated , pre["+((DecisionNode) temp.exit).ifNode.node_number +"] = "+predecessor[((DecisionNode) temp.exit).ifNode.node_number]+"  pre[ "+((DecisionNode) temp.exit).elseNode.node_number+"]=  "+predecessor[((DecisionNode) temp.exit).elseNode.node_number]);

                        }
                        else if(temp.exit instanceof CFGNode)
                        {
                            predecessor[temp.exit.node_number].add(temp);
                            System.out.println("Next node is cfg node\n Updated , pre["+temp.exit.node_number+"] = "+predecessor[temp.exit.node_number]);

                        }
                    }

                    root = root.exit;
                    predecessor[temp.refToFirst.node_number].add(temp);
                    predecessor[root.exit.node_number].add(((LoopNode)root).entry);
                    predecessor[root.exit.node_number].add(temp);
                    root=root.exit;

                }
                else
                {
                    System.out.println("Next is cfg node ");
                    predecessor[root.exit.node_number].add(root);
                    System.out.println("Next node is cfg node\n Updated , pre["+root.exit.node_number+"] = "+predecessor[root.exit.node_number]);


                    root= root.exit;
                }



            }


        }

    }

    public void display()
    {
        for (int i=1;i<predecessor.length;i++)
        {
            System.out.print(i+"  { ");
            for(CFGNode cfgNode : predecessor[i])
                System.out.print(cfgNode.node_number+"   ");

            System.out.println(" }");
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

    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list)
    {

        // Create a new ArrayList
        ArrayList<T> newList = new ArrayList<T>();

        // Traverse through the first list
        for (T element : list) {

            // If this element is not present in newList
            // then add it
            if (!newList.contains(element)) {

                newList.add(element);
            }
        }

        // return the new list
        return newList;
    }

    public HashMap<String, ArrayList<Integer>> union(ArrayList<CFGNode> list)
    {
        if(list.size() == 0)
            return new HashMap<>();

        HashMap<String,ArrayList<Integer>> unionOut = new HashMap<>();

        for(CFGNode l : list)
        {
            for(Map.Entry<String , ArrayList<Integer>> entry: out[l.node_number].entrySet())
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
        String expression = node.statements;//gen [n]
        HashMap<String,ArrayList<Integer>> in_copy=new HashMap<>();
        in_copy.putAll(in);
        int pos= expression.indexOf("=");
        if( pos == -1) //not an assignment statement
        {
            return in_copy;
        }
        else {
            String id= expression.substring(0,pos);
            //if present, then  kill all def inside in.
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
        System.out.println(checkIfEquals(in_prev, in));
        System.out.println(checkIfEquals(out_prev,out));

        do
        {
            System.out.println("---------------------------------------------------ITERATION - "+cnt++ +"----------------------------------------------------");
            for (int i=2;i<predecessor.length;i++)
            {

                in_prev[i].putAll(in[i]);
                out_prev[i].putAll(out[i]);

                in[i] = union(predecessor[i]);
                out[i] = computeOut(in[i] , CFG_Create.map.get(i));


                System.out.println("in[ "+i+"] = ");
                displayMap(in[i]);
                System.out.println("out[ "+i+"] = ");
                displayMap(out[i]);
                //System.out.println(in_prev.equals(in) +"   "+out_prev.equals(out));
                System.out.println("\n\n");
            }

            System.out.println(checkIfEquals(in_prev, in));
            System.out.println(checkIfEquals(out_prev,out));

        }
        while(!checkIfEquals(in_prev, in) && !checkIfEquals(out_prev,out));
    }
}
