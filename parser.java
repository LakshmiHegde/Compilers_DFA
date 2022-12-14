
//----------------------------------------------------
// The following code was generated by CUP v0.11a beta 20060608
// Wed Dec 14 12:11:18 IST 2022
//----------------------------------------------------

import java_cup.runtime.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/** CUP v0.11a beta 20060608 generated parser.
  * @version Wed Dec 14 12:11:18 IST 2022
  */
public class parser extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public parser() {super();}

  /** Constructor which sets the default scanner. */
  public parser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public parser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\045\000\002\013\012\000\002\002\004\000\002\014" +
    "\003\000\002\015\004\000\002\015\003\000\002\016\003" +
    "\000\002\016\004\000\002\017\003\000\002\017\003\000" +
    "\002\017\003\000\002\017\003\000\002\007\004\000\002" +
    "\012\003\000\002\012\004\000\002\020\010\000\002\021" +
    "\003\000\002\021\003\000\002\022\011\000\002\023\003" +
    "\000\002\023\004\000\002\024\003\000\002\024\003\000" +
    "\002\010\003\000\002\010\005\000\002\010\002\000\002" +
    "\011\003\000\002\011\005\000\002\005\005\000\002\006" +
    "\004\000\002\002\005\000\002\002\003\000\002\003\005" +
    "\000\002\003\003\000\002\004\003\000\002\004\003\000" +
    "\002\004\005\000\002\004\003" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\104\000\004\024\005\001\002\000\004\002\106\001" +
    "\002\000\004\023\006\001\002\000\004\011\007\001\002" +
    "\000\006\012\uffe9\024\011\001\002\000\006\012\uffeb\013" +
    "\101\001\002\000\004\023\100\001\002\000\004\012\013" +
    "\001\002\000\004\014\014\001\002\000\014\004\017\016" +
    "\022\017\024\023\025\024\011\001\002\000\014\004\ufff8" +
    "\015\ufff8\016\ufff8\017\ufff8\023\ufff8\001\002\000\014\004" +
    "\ufffa\015\ufffa\016\ufffa\017\ufffa\023\ufffa\001\002\000\012" +
    "\011\043\022\042\023\037\025\045\001\002\000\012\004" +
    "\017\016\022\017\024\023\025\001\002\000\004\015\uffff" +
    "\001\002\000\004\011\064\001\002\000\014\004\ufff7\015" +
    "\ufff7\016\ufff7\017\ufff7\023\ufff7\001\002\000\012\011\043" +
    "\022\042\023\037\025\045\001\002\000\004\005\036\001" +
    "\002\000\014\004\ufff9\015\ufff9\016\ufff9\017\ufff9\023\ufff9" +
    "\001\002\000\014\004\ufff5\016\ufff5\017\ufff5\023\ufff5\024" +
    "\011\001\002\000\004\015\034\001\002\000\014\004\017" +
    "\015\ufffc\016\022\017\024\023\025\001\002\000\004\015" +
    "\ufffd\001\002\000\004\015\ufffb\001\002\000\004\002\001" +
    "\001\002\000\012\004\ufff4\016\ufff4\017\ufff4\023\ufff4\001" +
    "\002\000\012\011\043\022\042\023\037\025\045\001\002" +
    "\000\026\004\uffdf\006\uffdf\010\uffdf\012\uffdf\015\uffdf\016" +
    "\uffdf\017\uffdf\020\uffdf\021\uffdf\023\uffdf\001\002\000\026" +
    "\004\uffe1\006\uffe1\010\uffe1\012\uffe1\015\uffe1\016\uffe1\017" +
    "\uffe1\020\uffe1\021\uffe1\023\uffe1\001\002\000\026\004\uffe3" +
    "\006\uffe3\010\050\012\uffe3\015\uffe3\016\uffe3\017\uffe3\020" +
    "\uffe3\021\uffe3\023\uffe3\001\002\000\026\004\uffe0\006\uffe0" +
    "\010\uffe0\012\uffe0\015\uffe0\016\uffe0\017\uffe0\020\uffe0\021" +
    "\uffe0\023\uffe0\001\002\000\012\011\043\022\042\023\037" +
    "\025\045\001\002\000\020\004\uffe6\006\046\015\uffe6\016" +
    "\uffe6\017\uffe6\020\uffe6\023\uffe6\001\002\000\026\004\uffdd" +
    "\006\uffdd\010\uffdd\012\uffdd\015\uffdd\016\uffdd\017\uffdd\020" +
    "\uffdd\021\uffdd\023\uffdd\001\002\000\012\011\043\022\042" +
    "\023\037\025\045\001\002\000\026\004\uffe4\006\uffe4\010" +
    "\050\012\uffe4\015\uffe4\016\uffe4\017\uffe4\020\uffe4\021\uffe4" +
    "\023\uffe4\001\002\000\012\011\043\022\042\023\037\025" +
    "\045\001\002\000\026\004\uffe2\006\uffe2\010\uffe2\012\uffe2" +
    "\015\uffe2\016\uffe2\017\uffe2\020\uffe2\021\uffe2\023\uffe2\001" +
    "\002\000\006\006\046\012\053\001\002\000\026\004\uffde" +
    "\006\uffde\010\uffde\012\uffde\015\uffde\016\uffde\017\uffde\020" +
    "\uffde\021\uffde\023\uffde\001\002\000\006\006\046\021\055" +
    "\001\002\000\012\011\043\022\042\023\057\025\045\001" +
    "\002\000\016\004\ufff1\015\ufff1\016\ufff1\017\ufff1\020\ufff1" +
    "\023\ufff1\001\002\000\024\004\uffdf\005\036\006\uffdf\010" +
    "\uffdf\015\uffdf\016\uffdf\017\uffdf\020\uffdf\023\uffdf\001\002" +
    "\000\004\020\062\001\002\000\020\004\ufff2\006\046\015" +
    "\ufff2\016\ufff2\017\ufff2\020\ufff2\023\ufff2\001\002\000\012" +
    "\011\043\022\042\023\057\025\045\001\002\000\014\004" +
    "\ufff3\015\ufff3\016\ufff3\017\ufff3\023\ufff3\001\002\000\012" +
    "\011\043\022\042\023\037\025\045\001\002\000\006\006" +
    "\046\012\066\001\002\000\004\014\067\001\002\000\006" +
    "\017\024\023\025\001\002\000\010\015\uffef\017\024\023" +
    "\025\001\002\000\010\015\uffec\017\uffec\023\uffec\001\002" +
    "\000\010\015\uffed\017\uffed\023\uffed\001\002\000\004\015" +
    "\074\001\002\000\014\004\ufff0\015\ufff0\016\ufff0\017\ufff0" +
    "\023\ufff0\001\002\000\004\015\uffee\001\002\000\004\015" +
    "\ufffe\001\002\000\016\004\uffe5\006\046\015\uffe5\016\uffe5" +
    "\017\uffe5\023\uffe5\001\002\000\020\004\ufff6\012\ufff6\013" +
    "\ufff6\016\ufff6\017\ufff6\023\ufff6\024\ufff6\001\002\000\004" +
    "\024\011\001\002\000\006\012\uffe8\013\104\001\002\000" +
    "\004\012\uffea\001\002\000\004\024\011\001\002\000\004" +
    "\012\uffe7\001\002\000\004\002\000\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\104\000\004\013\003\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\006\007\007\010\011" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\026\005\015\006\025\007\026" +
    "\012\017\014\027\015\020\016\031\017\030\020\014\022" +
    "\022\001\001\000\002\001\001\000\002\001\001\000\010" +
    "\002\076\003\040\004\037\001\001\000\016\005\015\006" +
    "\025\016\075\017\030\020\014\022\022\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\010\002" +
    "\053\003\040\004\037\001\001\000\002\001\001\000\002" +
    "\001\001\000\006\007\026\012\034\001\001\000\002\001" +
    "\001\000\016\005\015\006\025\016\032\017\030\020\014" +
    "\022\022\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\010\002\043\003\040" +
    "\004\037\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\010\002\051\003\040" +
    "\004\037\001\001\000\002\001\001\000\002\001\001\000" +
    "\006\003\046\004\037\001\001\000\002\001\001\000\004" +
    "\004\050\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\014\002\060\003\040" +
    "\004\037\005\055\021\057\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\014" +
    "\002\060\003\040\004\037\005\055\021\062\001\001\000" +
    "\002\001\001\000\010\002\064\003\040\004\037\001\001" +
    "\000\002\001\001\000\002\001\001\000\012\005\071\020" +
    "\070\023\072\024\067\001\001\000\012\005\071\020\070" +
    "\023\074\024\067\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\006\007\101\011\102\001\001\000\002\001\001\000\002" +
    "\001\001\000\006\007\101\011\104\001\001\000\002\001" +
    "\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$parser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$parser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$parser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 1;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}

}

/** Cup generated class to encapsulate user supplied action code.*/
class CUP$parser$actions {
  private final parser parser;

  /** Constructor */
  CUP$parser$actions(parser parser) {
    this.parser = parser;
  }

  /** Method with the actual generated action code. */
  public final java_cup.runtime.Symbol CUP$parser$do_action(
    int                        CUP$parser$act_num,
    java_cup.runtime.lr_parser CUP$parser$parser,
    java.util.Stack            CUP$parser$stack,
    int                        CUP$parser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$parser$result;

      /* select the action based on the action number */
      switch (CUP$parser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 36: // factor ::= BOOLCONST 
            {
              Expr RESULT =null;
		int bleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int bright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Boolean b = (Boolean)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = new BoolExpr(b); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("factor",2, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 35: // factor ::= LPAREN expr RPAREN 
            {
              Expr RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		Expr e = (Expr)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		 RESULT = e;  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("factor",2, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 34: // factor ::= ID 
            {
              Expr RESULT =null;
		int idleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int idright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		String id = (String)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		  RESULT = new IdExpr(id);  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("factor",2, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 33: // factor ::= NUMBER 
            {
              Expr RESULT =null;
		int nleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int nright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Integer n = (Integer)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = new NumExpr(n);  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("factor",2, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 32: // term ::= factor 
            {
              Expr RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int eright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Expr e = (Expr)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = e; 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("term",1, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 31: // term ::= term TIMES factor 
            {
              Expr RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Expr e1 = (Expr)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Expr e2 = (Expr)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = new MulExpr(e1, e2); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("term",1, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 30: // expr ::= term 
            {
              Expr RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Expr e1 = (Expr)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = e1; 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("expr",0, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 29: // expr ::= expr PLUS term 
            {
              Expr RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Expr e1 = (Expr)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Expr e2 = (Expr)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		  RESULT = new AddExpr(e1, e2); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("expr",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 28: // retinstr ::= RET expr 
            {
              Return RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int eright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Expr e = (Expr)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		  RESULT= new Return(e);  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("retinstr",4, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 27: // assigninstr ::= ID ASSIGN expr 
            {
              Assign RESULT =null;
		int idleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int idright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		String id = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int eleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int eright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Expr e = (Expr)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		  RESULT= new Assign(new IdExpr(id),e);  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("assigninstr",3, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 26: // nt_parlist ::= vardecl COMMA nt_parlist 
            {
              List<Declaration> RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int vright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Declaration v = (Declaration)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int npleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int npright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		List<Declaration> np = (List<Declaration>)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = np; RESULT.add(0,v); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("nt_parlist",7, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 25: // nt_parlist ::= vardecl 
            {
              List<Declaration> RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int vright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Declaration v = (Declaration)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		if(RESULT == null)RESULT=new ArrayList<>(); RESULT.add(v); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("nt_parlist",7, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 24: // parlist ::= 
            {
              List<Declaration> RESULT =null;
		 RESULT = new ArrayList<Declaration>(); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("parlist",6, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 23: // parlist ::= vardecl COMMA nt_parlist 
            {
              List<Declaration> RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int vright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Declaration v = (Declaration)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int npleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int npright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		List<Declaration> np = (List<Declaration>)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		  RESULT = np; RESULT.add(0,v); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("parlist",6, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 22: // parlist ::= vardecl 
            {
              List<Declaration> RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int vright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Declaration v = (Declaration)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		  if(RESULT == null) RESULT = new ArrayList<>(); RESULT.add(v); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("parlist",6, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 21: // loop_sub_block ::= ifcond 
            {
              LoopSubBlock RESULT =null;
		int fleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int fright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		IfCondition f = (IfCondition)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT=f; 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("loop_sub_block",18, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // loop_sub_block ::= assigninstr 
            {
              LoopSubBlock RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Assign a = (Assign)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT= a; 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("loop_sub_block",18, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // loopblock ::= loop_sub_block loopblock 
            {
              LoopBlock RESULT =null;
		int sleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int sright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		LoopSubBlock s = (LoopSubBlock)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		int ibleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int ibright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		LoopBlock ib = (LoopBlock)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT=ib ; RESULT.subStatements.add(0,s);  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("loopblock",17, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // loopblock ::= loop_sub_block 
            {
              LoopBlock RESULT =null;
		int sleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int sright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		LoopSubBlock s = (LoopSubBlock)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 if(RESULT == null) RESULT= new LoopBlock(); RESULT.subStatements.add(s); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("loopblock",17, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // loop ::= LOOP LPAREN expr RPAREN LFB loopblock RFB 
            {
              Loop RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)).right;
		Expr e = (Expr)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-4)).value;
		int lsleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int lsright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		LoopBlock ls = (LoopBlock)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		 RESULT = new Loop(e,ls); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("loop",16, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-6)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // subexpr ::= assigninstr 
            {
              Subexpr RESULT =null;
		int rleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int rright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Assign r = (Assign)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = new Subexpr(r); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("subexpr",15, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // subexpr ::= expr 
            {
              Subexpr RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int eright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Expr e = (Expr)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = new Subexpr(e); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("subexpr",15, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // ifcond ::= IF expr THEN subexpr ELSE subexpr 
            {
              IfCondition RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)).right;
		Expr e = (Expr)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-4)).value;
		int e1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Subexpr e1 = (Subexpr)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Subexpr e2 = (Subexpr)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = new IfCondition(e,e1,e2); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("ifcond",14, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-5)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // vardeclseq ::= vardecl vardeclseq 
            {
              List<Declaration> RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int vright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		Declaration v = (Declaration)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		int vsleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int vsright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		List<Declaration> vs = (List<Declaration>)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT=vs; RESULT.add(0,v); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("vardeclseq",8, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // vardeclseq ::= vardecl 
            {
              List<Declaration> RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int vright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Declaration v = (Declaration)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 if(RESULT == null)RESULT=new ArrayList<>(); RESULT.add(v); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("vardeclseq",8, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // vardecl ::= TYPE ID 
            {
              Declaration RESULT =null;
		int typeleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int typeright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		Type type = (Type)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		int idleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int idright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		String id = (String)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT= new Declaration(id,type); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("vardecl",5, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // instr ::= loop 
            {
              Instruction RESULT =null;
		int lleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int lright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Loop l = (Loop)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = l; 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("instr",13, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // instr ::= ifcond 
            {
              Instruction RESULT =null;
		int fleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int fright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		IfCondition f = (IfCondition)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT= f; 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("instr",13, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // instr ::= retinstr 
            {
              Instruction RESULT =null;
		int rleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int rright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Return r = (Return)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT= r; 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("instr",13, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // instr ::= assigninstr 
            {
              Instruction RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Assign a = (Assign)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT= a; 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("instr",13, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // seq ::= instr seq 
            {
              Sequence RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int iright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		Instruction i = (Instruction)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		int sleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int sright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Sequence s = (Sequence)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT=s; RESULT.instr.add(0,i);  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("seq",12, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // seq ::= instr 
            {
              Sequence RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int iright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Instruction i = (Instruction)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 if(RESULT == null) RESULT= new Sequence(); RESULT.instr.add(i); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("seq",12, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // block ::= seq 
            {
              Block RESULT =null;
		int sleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int sright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Sequence s = (Sequence)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT= new Block(null,s); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("block",11, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // block ::= vardeclseq seq 
            {
              Block RESULT =null;
		int vdsleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int vdsright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		List<Declaration> vds = (List<Declaration>)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		int sleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int sright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Sequence s = (Sequence)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT= new Block(vds, s); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("block",11, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // funcbody ::= block 
            {
              FunctionBody RESULT =null;
		int bleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int bright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Block b = (Block)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT= new FunctionBody(b); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("funcbody",10, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // $START ::= fundef EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		FunctionDeclaration start_val = (FunctionDeclaration)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		RESULT = start_val;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$parser$parser.done_parsing();
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // fundef ::= TYPE ID LPAREN parlist RPAREN LFB funcbody RFB 
            {
              FunctionDeclaration RESULT =null;
		int typeleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-7)).left;
		int typeright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-7)).right;
		Type type = (Type)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-7)).value;
		int idleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-6)).left;
		int idright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-6)).right;
		String id = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-6)).value;
		int lleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)).left;
		int lright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)).right;
		List<Declaration> l = (List<Declaration>)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-4)).value;
		int bleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int bright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		FunctionBody b = (FunctionBody)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		 RESULT = new FunctionDeclaration(type,id,l,b); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("fundef",9, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-7)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number found in internal parse table");

        }
    }
}

