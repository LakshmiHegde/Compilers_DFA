P: lexer.flex AST.java parser.cup Driver.java Pred_Succ_List.java CFG_Create.java DFA.java Analyzer.java
	jflex lexer.flex 
	java -jar /home/lakshmi/cup-11a.jar -dump < parser.cup
	javac DFA.java CFG_Create.java sym.java parser.java IdLexer.java Driver.java AST.java Pred_Succ_List.java Analyzer.java -cp /home/lakshmi/jflex-1.8.2/lib/jflex-full-1.8.2.jar:/home/lakshmi/cup-11a.jar:./
