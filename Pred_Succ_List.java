import java.util.ArrayList;

public class Pred_Succ_List {
    ArrayList<CFGNode>[] predecessor, successor;
    CFGNode root=null;

    Pred_Succ_List(int nodes , CFGNode root)
    {
        predecessor= new ArrayList[nodes+1];
        successor= new ArrayList[nodes+1];

        for (int i=0;i<=nodes;i++)
        {
            predecessor[i] = new ArrayList<>();
            successor[i] = new ArrayList<>();
        }
        this.root = root;
    }

    public void get_pred()
    {
        while(root.exit != null)
        {
            if(root instanceof DecisionNode)
            {
                //System.out.println("current node is dec node ");
                if(root.exit instanceof DecisionNode)
                {
                    //System.out.println("Next is dec node ");
                    predecessor[((DecisionNode) root.exit).ifNode.node_number].add((DecisionNode) ((DecisionNode) root).ifNode);
                    predecessor[((DecisionNode) root.exit).elseNode.node_number].add((DecisionNode) ((DecisionNode) root).ifNode);

                    predecessor[((DecisionNode) root.exit).ifNode.node_number].add((DecisionNode) ((DecisionNode) root).elseNode);
                    predecessor[((DecisionNode) root.exit).elseNode.node_number].add((DecisionNode) ((DecisionNode) root).elseNode);


                    //System.out.println("Updated  "+ predecessor[((DecisionNode) root.exit).ifNode.node_number]+"  "
                      ////      + predecessor[((DecisionNode) root.exit).elseNode.node_number]+
                          //  " \n\n");

                    root= root.exit;
                }
                else if (root.exit instanceof LoopNode)
                {

                    CFGNode temp=((LoopNode)root.exit).entry;
                    predecessor[ ((LoopNode) root.exit).entry.node_number].add(((DecisionNode)root).ifNode);
                    predecessor[ ((LoopNode) root.exit).entry.node_number].add(((DecisionNode)root).elseNode);
                    //System.out.println(temp.refToFirst);
                    //System.out.println("ref to first "+temp.refToFirst);

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
                    //System.out.println("Next is cfg node ");
                    predecessor[root.exit.node_number].add(((DecisionNode) root).ifNode);
                    predecessor[root.exit.node_number].add(((DecisionNode) root).elseNode);
                    //System.out.println("Updated  "+ predecessor[root.exit.node_number] + " \n\n");

                    root= root.exit;
                }

            }
            else //it will be cfg node, as we have bypassed loop, by shifting pointer to entry
            {
                //predecessor[root.exit.node_number].add(root);
                //System.out.println("curr node is cfg  ");
                //root= root.exit;
                if(root.exit instanceof DecisionNode)
                {
                    //System.out.println("Next is dec node ");
                    predecessor[((DecisionNode) root.exit).ifNode.node_number].add(root);
                    predecessor[((DecisionNode) root.exit).elseNode.node_number].add(root);

                    //System.out.println("Updated  "+ predecessor[((DecisionNode) root.exit).ifNode.node_number]+"  "
                      ////      + predecessor[((DecisionNode) root.exit).elseNode.node_number]+
                          //  " \n\n");

                    root= root.exit;
                }
                else if (root.exit instanceof LoopNode)
                {
                    CFGNode temp=((LoopNode)root.exit).entry;
                    predecessor[ ((LoopNode) root.exit).entry.node_number].add(root);

                    //System.out.println(temp.refToFirst);
                    //System.out.println("\n\n\n\n-----------------------LOOP NODE----------------------\n\n\n\n");

                    while(temp.refToFirst == null )
                    {
                        //System.out.println("Inside loop "+temp.statements.toString());

                        if (temp instanceof DecisionNode)
                        {
                            if(temp.exit instanceof DecisionNode)
                            {

                                predecessor[((DecisionNode) temp.exit).ifNode.node_number].add( ((DecisionNode) temp).ifNode);
                                predecessor[((DecisionNode) temp.exit).elseNode.node_number].add( ((DecisionNode) temp).ifNode);

                                predecessor[((DecisionNode) temp.exit).ifNode.node_number].add( ((DecisionNode) temp).elseNode);
                                predecessor[((DecisionNode) temp.exit).elseNode.node_number].add( ((DecisionNode) temp).elseNode);

                                //System.out.println("Next node is dec node\n Updated , pre["+((DecisionNode) temp.exit).ifNode.node_number +"] = "+predecessor[((DecisionNode) temp.exit).ifNode.node_number]+"  pre[ "+((DecisionNode) temp.exit).elseNode.node_number+"]=  "+predecessor[((DecisionNode) temp.exit).elseNode.node_number]);

                                temp=temp.exit;
                            }
                            else if(temp.exit instanceof CFGNode)
                            {

                                predecessor[temp.exit.node_number].add(((DecisionNode) temp).ifNode);
                                predecessor[temp.exit.node_number].add(((DecisionNode) temp).elseNode);
                                //System.out.println("Next node is cfg node\n Updated , pre["+temp.exit.node_number+"] = "+predecessor[temp.exit.node_number]);
                                temp=temp.exit;
                            }
                        }
                        else if(temp instanceof CFGNode)
                        {
                            if(temp.exit instanceof DecisionNode)
                            {
                                predecessor[((DecisionNode) temp.exit).ifNode.node_number].add( temp);
                                predecessor[((DecisionNode) temp.exit).elseNode.node_number].add(temp);
                                //System.out.println("Next node is dec node\n Updated , pre["+((DecisionNode) temp.exit).ifNode.node_number +"] = "+predecessor[((DecisionNode) temp.exit).ifNode.node_number]+"  pre[ "+((DecisionNode) temp.exit).elseNode.node_number+"]=  "+predecessor[((DecisionNode) temp.exit).elseNode.node_number]);

                            }
                            else if(temp.exit instanceof CFGNode)
                            {
                                predecessor[temp.exit.node_number].add(temp);
                                //System.out.println("Next node is cfg node\n Updated , pre["+temp.exit.node_number+"] = "+predecessor[temp.exit.node_number]);

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

                            //System.out.println("Next node is dec node\n Updated , pre["+((DecisionNode) temp.exit).ifNode.node_number +"] = "+predecessor[((DecisionNode) temp.exit).ifNode.node_number]+"  pre[ "+((DecisionNode) temp.exit).elseNode.node_number+"]=  "+predecessor[((DecisionNode) temp.exit).elseNode.node_number]);


                        }
                        else if(temp.exit instanceof CFGNode)
                        {
                            predecessor[temp.exit.node_number].add(((DecisionNode) temp).ifNode);
                            predecessor[temp.exit.node_number].add(((DecisionNode) temp).elseNode);
                            //System.out.println("Next node is cfg node\n Updated , pre["+temp.exit.node_number+"] = "+predecessor[temp.exit.node_number]);

                        }
                    }
                    else if(temp instanceof CFGNode)
                    {
                        if(temp.exit instanceof DecisionNode)
                        {
                            predecessor[((DecisionNode) temp.exit).ifNode.node_number].add( temp);
                            predecessor[((DecisionNode) temp.exit).elseNode.node_number].add(temp);
                            //System.out.println("Next node is dec node\n Updated , pre["+((DecisionNode) temp.exit).ifNode.node_number +"] = "+predecessor[((DecisionNode) temp.exit).ifNode.node_number]+"  pre[ "+((DecisionNode) temp.exit).elseNode.node_number+"]=  "+predecessor[((DecisionNode) temp.exit).elseNode.node_number]);

                        }
                        else if(temp.exit instanceof CFGNode)
                        {
                            predecessor[temp.exit.node_number].add(temp);
                            //System.out.println("Next node is cfg node\n Updated , pre["+temp.exit.node_number+"] = "+predecessor[temp.exit.node_number]);

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
                    //System.out.println("Next is cfg node ");
                    predecessor[root.exit.node_number].add(root);
                    //System.out.println("Next node is cfg node\n Updated , pre["+root.exit.node_number+"] = "+predecessor[root.exit.node_number]);


                    root= root.exit;
                }



            }


        }

    }

    public void get_succ()
    {
        for (int i=0;i<predecessor.length;i++) {
            int len = predecessor[i].size();

            for (int j = 0; j < len; j++)
            {
                successor[predecessor[i].get(j).node_number].add(CFG_Create.map.get(i));
            }
        }
    }
    public void display()
    {
        System.out.println("--------------------------------------Predecessor List--------------------------------------------");
        for (int i=1;i<predecessor.length;i++)
        {
            System.out.print(i+"  { ");
            for(CFGNode cfgNode : predecessor[i])
                System.out.print(cfgNode.node_number+"   ");

            System.out.println(" }");
        }
        System.out.println("--------------------------------------Successor List--------------------------------------------");
        for (int i=1;i<successor.length;i++)
        {
            System.out.print(i+"  { ");
            for(CFGNode cfgNode : successor[i])
                System.out.print(cfgNode.node_number+"   ");

            System.out.println(" }");
        }

    }

}
