// $ANTLR 3.5.2 proyecto.g 2025-05-18 14:14:25

    import java.util.HashMap;
    import java.util.HashSet;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class proyectoParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "CDOUBLE", "CINT", "COMMA", "DOUBLE", 
		"FLOAT", "GUION_BAJO", "ID", "INT", "PRIVATE", "PROTECTED", "PUBLIC", 
		"SEMICOLON", "STRING", "VOID", "WS", "'('", "')'", "'*'", "'+'", "'-'", 
		"'/'", "'='", "'class'", "'{'", "'}'"
	};
	public static final int EOF=-1;
	public static final int T__19=19;
	public static final int T__20=20;
	public static final int T__21=21;
	public static final int T__22=22;
	public static final int T__23=23;
	public static final int T__24=24;
	public static final int T__25=25;
	public static final int T__26=26;
	public static final int T__27=27;
	public static final int T__28=28;
	public static final int CDOUBLE=4;
	public static final int CINT=5;
	public static final int COMMA=6;
	public static final int DOUBLE=7;
	public static final int FLOAT=8;
	public static final int GUION_BAJO=9;
	public static final int ID=10;
	public static final int INT=11;
	public static final int PRIVATE=12;
	public static final int PROTECTED=13;
	public static final int PUBLIC=14;
	public static final int SEMICOLON=15;
	public static final int STRING=16;
	public static final int VOID=17;
	public static final int WS=18;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public proyectoParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public proyectoParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return proyectoParser.tokenNames; }
	@Override public String getGrammarFileName() { return "proyecto.g"; }


	    // Tablas de símbolos
	    HashMap TSG = new HashMap();
	    HashMap TSL = new HashMap();

	    // Tabla para BTA: 1 = estático, 0 = dinámico
	    HashMap<String, Integer> tablaBTA = new HashMap<>();

	    // Para seguimiento de funciones
	    HashMap<String, Integer> functionCallCount = new HashMap();
	    HashSet<String> definedFunctions = new HashSet();
	    HashSet<String> calledFunctions = new HashSet();

	    // Contadores de enteros
	    int cantidadEnteros = 0;
	    int cantidadEnterosGlobal = 0;

	    // Variable para rastrear la clase actual
	    String currentClassName;

	    // Método para verificar métodos especiales (main y constructores)
	    public boolean isSpecialMethod(String methodName, String className) {
	        return methodName.equals("main") || methodName.equals(className);
	    }
	    
	    // Registro de llamadas a funciones
	    public void registerFunctionCall(String className, String functionName) {
	        String fullName = className + "." + functionName;
	        if (definedFunctions.contains(fullName)) {
	            int count = functionCallCount.getOrDefault(fullName, 0);
	            functionCallCount.put(fullName, count + 1);
	            calledFunctions.add(fullName);
	        }
	    }

	    // Métodos para manejo de tablas de símbolos
	    public void pushTSG(String id, String tipo, int linea) {
	        Integer se_encuentra = (Integer) TSG.get(id);
	        if(se_encuentra==null) {
	            if(tipo.compareTo("int")==0)
	                TSG.put(id,1);
	            else if(tipo.compareTo("double")==0)
	                TSG.put(id,2);
	            else if(tipo.compareTo("class")==0)
	                TSG.put(id,3);
	        } else {
	            reportError("El ID " + id + " ya ha sido decalarado previamente.", linea);
	        }
	    }

	    public void pushTSL(String id, String tipo, int linea) {
	        Integer se_encuentra = (Integer) TSL.get(id);
	        if(se_encuentra==null) {
	            if(tipo.compareTo("int")==0)
	                TSL.put(id,1);
	            else if(tipo.compareTo("double")==0)
	                TSL.put(id,2);
	            else System.err.println("Tipo desconocido: " + tipo);
	        }
	        else reportError("El ID " + id + " ya ha sido decalarado previamente.", linea);
	    }

	    public int searchID(String id, int linea) {
	        Integer existeTSG = (Integer) TSG.get(id);
	        Integer existeTSL = (Integer) TSL.get(id);

	        if(existeTSL != null) {
	            return existeTSL;
	        } else if (existeTSG != null) {
	            return existeTSG;
	        }

	        reportError("La variable " + id + " no ha sido declarada.", linea);
	        return 3;
	    }

	    public int compareTipo(int id1, int id2) {
	        return id1 == id2 ? id1 : 1;
	    }

	    public void reportError(String mensaje, int linea) {
	        System.err.println(">>MyCompiler>> Error en linea " + linea + ":" + mensaje);
	    }

	    public void displayRecognitionError(String [] tokenNames, RecognitionException e) {
	        String hdr = getErrorHeader(e);
	        String msg = getErrorMessage(e, tokenNames);
	        System.err.println(">>MyCompiler>> Error en linea " + e.line + ":" + e.charPositionInLine + " -> " + msg);
	    } 

	    public void imprimirEnteros() {
	        System.out.println("La cantidad de enteros con parentesis es de: " + cantidadEnteros);
	        cantidadEnterosGlobal = cantidadEnteros + cantidadEnterosGlobal;
	        cantidadEnteros = 0;
	    }

	    // Registro de definiciones de funciones
	    public void registerFunctionDefinition(String className, String functionName) {
	        String fullName = className + "." + functionName;
	        if (!isSpecialMethod(functionName, className)) {
	            definedFunctions.add(fullName);
	            functionCallCount.put(fullName, 0);
	        }
	    }
	    
	    // Generación del informe de optimización
	    public void printOptimizationReport() {
	        System.out.println("\n=== Informe de Optimización ===");
	        boolean foundOptimizable = false;
	        
	        // Funciones nunca llamadas
	        for (String func : definedFunctions) {
	            if (!calledFunctions.contains(func)) {
	                System.out.println("Funcion '" + func + "' nunca es llamada y puede ser eliminada.");
	                foundOptimizable = true;
	            }
	        }
	        
	        // Funciones llamadas una vez
	        for (String func : functionCallCount.keySet()) {
	            if (functionCallCount.get(func) == 1) {
	                System.out.println("Funcion '" + func + "' es llamada solo una vez y puede ser reducida (inlined).");
	                foundOptimizable = true;
	            }
	        }
	        
	        if (!foundOptimizable) {
	            System.out.println("No se encontraron funciones optimizables.");
	        }
	    }



	// $ANTLR start "program"
	// proyecto.g:141:1: program : ( clase )+ ;
	public final void program() throws RecognitionException {
		try {
			// proyecto.g:141:8: ( ( clase )+ )
			// proyecto.g:141:10: ( clase )+
			{
			// proyecto.g:141:10: ( clase )+
			int cnt1=0;
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= PRIVATE && LA1_0 <= PUBLIC)||LA1_0==26) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// proyecto.g:141:10: clase
					{
					pushFollow(FOLLOW_clase_in_program24);
					clase();
					state._fsp--;

					}
					break;

				default :
					if ( cnt1 >= 1 ) break loop1;
					EarlyExitException eee = new EarlyExitException(1, input);
					throw eee;
				}
				cnt1++;
			}

			printOptimizationReport();
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "program"



	// $ANTLR start "clase"
	// proyecto.g:143:1: clase : ( modificadorAcceso )? 'class' ID '{' ( miembro[currentClassName] )* '}' ;
	public final void clase() throws RecognitionException {
		Token ID1=null;

		try {
			// proyecto.g:143:6: ( ( modificadorAcceso )? 'class' ID '{' ( miembro[currentClassName] )* '}' )
			// proyecto.g:143:8: ( modificadorAcceso )? 'class' ID '{' ( miembro[currentClassName] )* '}'
			{
			// proyecto.g:143:8: ( modificadorAcceso )?
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( ((LA2_0 >= PRIVATE && LA2_0 <= PUBLIC)) ) {
				alt2=1;
			}
			switch (alt2) {
				case 1 :
					// proyecto.g:143:8: modificadorAcceso
					{
					pushFollow(FOLLOW_modificadorAcceso_in_clase35);
					modificadorAcceso();
					state._fsp--;

					}
					break;

			}

			match(input,26,FOLLOW_26_in_clase38); 
			ID1=(Token)match(input,ID,FOLLOW_ID_in_clase40); 
			currentClassName = (ID1!=null?ID1.getText():null); pushTSG(currentClassName,"class", ID1.getLine());
			match(input,27,FOLLOW_27_in_clase53); 
			// proyecto.g:145:9: ( miembro[currentClassName] )*
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( ((LA3_0 >= DOUBLE && LA3_0 <= FLOAT)||(LA3_0 >= INT && LA3_0 <= PUBLIC)||(LA3_0 >= STRING && LA3_0 <= VOID)) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// proyecto.g:145:9: miembro[currentClassName]
					{
					pushFollow(FOLLOW_miembro_in_clase55);
					miembro(currentClassName);
					state._fsp--;

					}
					break;

				default :
					break loop3;
				}
			}

			match(input,28,FOLLOW_28_in_clase59); 
			System.out.println("El numero total de enteros con parentesis es: " + cantidadEnterosGlobal);
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "clase"



	// $ANTLR start "miembro"
	// proyecto.g:148:1: miembro[String className] : ( metodo[className] | atributo );
	public final void miembro(String className) throws RecognitionException {
		try {
			// proyecto.g:148:26: ( metodo[className] | atributo )
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( ((LA4_0 >= PRIVATE && LA4_0 <= PUBLIC)) ) {
				int LA4_1 = input.LA(2);
				if ( ((LA4_1 >= DOUBLE && LA4_1 <= FLOAT)||LA4_1==INT||(LA4_1 >= STRING && LA4_1 <= VOID)) ) {
					int LA4_2 = input.LA(3);
					if ( (LA4_2==ID) ) {
						int LA4_3 = input.LA(4);
						if ( (LA4_3==19) ) {
							alt4=1;
						}
						else if ( (LA4_3==COMMA||LA4_3==SEMICOLON) ) {
							alt4=2;
						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 4, 3, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 4, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 4, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( ((LA4_0 >= DOUBLE && LA4_0 <= FLOAT)||LA4_0==INT||(LA4_0 >= STRING && LA4_0 <= VOID)) ) {
				int LA4_2 = input.LA(2);
				if ( (LA4_2==ID) ) {
					int LA4_3 = input.LA(3);
					if ( (LA4_3==19) ) {
						alt4=1;
					}
					else if ( (LA4_3==COMMA||LA4_3==SEMICOLON) ) {
						alt4=2;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 4, 3, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 4, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 4, 0, input);
				throw nvae;
			}

			switch (alt4) {
				case 1 :
					// proyecto.g:148:28: metodo[className]
					{
					pushFollow(FOLLOW_metodo_in_miembro74);
					metodo(className);
					state._fsp--;

					}
					break;
				case 2 :
					// proyecto.g:148:48: atributo
					{
					pushFollow(FOLLOW_atributo_in_miembro79);
					atributo();
					state._fsp--;

					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "miembro"



	// $ANTLR start "metodo"
	// proyecto.g:150:1: metodo[String className] : ( modificadorAcceso )? tipo ID '(' ( decl_args )? ')' '{' ( instruccion )* '}' ;
	public final void metodo(String className) throws RecognitionException {
		Token ID2=null;
		ParserRuleReturnScope tipo3 =null;

		try {
			// proyecto.g:150:25: ( ( modificadorAcceso )? tipo ID '(' ( decl_args )? ')' '{' ( instruccion )* '}' )
			// proyecto.g:150:27: ( modificadorAcceso )? tipo ID '(' ( decl_args )? ')' '{' ( instruccion )* '}'
			{
			// proyecto.g:150:27: ( modificadorAcceso )?
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( ((LA5_0 >= PRIVATE && LA5_0 <= PUBLIC)) ) {
				alt5=1;
			}
			switch (alt5) {
				case 1 :
					// proyecto.g:150:27: modificadorAcceso
					{
					pushFollow(FOLLOW_modificadorAcceso_in_metodo87);
					modificadorAcceso();
					state._fsp--;

					}
					break;

			}

			pushFollow(FOLLOW_tipo_in_metodo90);
			tipo3=tipo();
			state._fsp--;

			ID2=(Token)match(input,ID,FOLLOW_ID_in_metodo92); 
			pushTSG((ID2!=null?ID2.getText():null), (tipo3!=null?input.toString(tipo3.start,tipo3.stop):null), ID2.getLine()); 
			     registerFunctionDefinition(className, (ID2!=null?ID2.getText():null));
			match(input,19,FOLLOW_19_in_metodo105); 
			// proyecto.g:153:9: ( decl_args )?
			int alt6=2;
			int LA6_0 = input.LA(1);
			if ( ((LA6_0 >= DOUBLE && LA6_0 <= FLOAT)||LA6_0==INT||(LA6_0 >= STRING && LA6_0 <= VOID)) ) {
				alt6=1;
			}
			switch (alt6) {
				case 1 :
					// proyecto.g:153:9: decl_args
					{
					pushFollow(FOLLOW_decl_args_in_metodo107);
					decl_args();
					state._fsp--;

					}
					break;

			}

			match(input,20,FOLLOW_20_in_metodo110); 
			match(input,27,FOLLOW_27_in_metodo117); 
			// proyecto.g:155:9: ( instruccion )*
			loop7:
			while (true) {
				int alt7=2;
				int LA7_0 = input.LA(1);
				if ( ((LA7_0 >= DOUBLE && LA7_0 <= FLOAT)||(LA7_0 >= ID && LA7_0 <= INT)||(LA7_0 >= STRING && LA7_0 <= VOID)) ) {
					alt7=1;
				}

				switch (alt7) {
				case 1 :
					// proyecto.g:155:9: instruccion
					{
					pushFollow(FOLLOW_instruccion_in_metodo128);
					instruccion();
					state._fsp--;

					}
					break;

				default :
					break loop7;
				}
			}

			match(input,28,FOLLOW_28_in_metodo136); 
			TSL.clear();
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "metodo"



	// $ANTLR start "atributo"
	// proyecto.g:158:1: atributo : ( modificadorAcceso )? tipo id1= ID ( COMMA id2= ID )* SEMICOLON ;
	public final void atributo() throws RecognitionException {
		Token id1=null;
		Token id2=null;
		ParserRuleReturnScope tipo4 =null;

		try {
			// proyecto.g:158:9: ( ( modificadorAcceso )? tipo id1= ID ( COMMA id2= ID )* SEMICOLON )
			// proyecto.g:158:11: ( modificadorAcceso )? tipo id1= ID ( COMMA id2= ID )* SEMICOLON
			{
			// proyecto.g:158:11: ( modificadorAcceso )?
			int alt8=2;
			int LA8_0 = input.LA(1);
			if ( ((LA8_0 >= PRIVATE && LA8_0 <= PUBLIC)) ) {
				alt8=1;
			}
			switch (alt8) {
				case 1 :
					// proyecto.g:158:11: modificadorAcceso
					{
					pushFollow(FOLLOW_modificadorAcceso_in_atributo145);
					modificadorAcceso();
					state._fsp--;

					}
					break;

			}

			pushFollow(FOLLOW_tipo_in_atributo148);
			tipo4=tipo();
			state._fsp--;

			id1=(Token)match(input,ID,FOLLOW_ID_in_atributo152); 
			pushTSG((id1!=null?id1.getText():null), (tipo4!=null?input.toString(tipo4.start,tipo4.stop):null), id1.getLine());
			// proyecto.g:160:5: ( COMMA id2= ID )*
			loop9:
			while (true) {
				int alt9=2;
				int LA9_0 = input.LA(1);
				if ( (LA9_0==COMMA) ) {
					alt9=1;
				}

				switch (alt9) {
				case 1 :
					// proyecto.g:160:6: COMMA id2= ID
					{
					match(input,COMMA,FOLLOW_COMMA_in_atributo169); 
					id2=(Token)match(input,ID,FOLLOW_ID_in_atributo173); 
					pushTSG((id2!=null?id2.getText():null), (tipo4!=null?input.toString(tipo4.start,tipo4.stop):null), id2.getLine());
					}
					break;

				default :
					break loop9;
				}
			}

			match(input,SEMICOLON,FOLLOW_SEMICOLON_in_atributo184); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "atributo"



	// $ANTLR start "llamadaFuncion"
	// proyecto.g:163:1: llamadaFuncion : ID '(' ( expr ( ',' expr )* )? ')' ;
	public final void llamadaFuncion() throws RecognitionException {
		Token ID5=null;

		try {
			// proyecto.g:163:15: ( ID '(' ( expr ( ',' expr )* )? ')' )
			// proyecto.g:163:17: ID '(' ( expr ( ',' expr )* )? ')'
			{
			ID5=(Token)match(input,ID,FOLLOW_ID_in_llamadaFuncion191); 
			match(input,19,FOLLOW_19_in_llamadaFuncion193); 
			// proyecto.g:163:24: ( expr ( ',' expr )* )?
			int alt11=2;
			int LA11_0 = input.LA(1);
			if ( ((LA11_0 >= CDOUBLE && LA11_0 <= CINT)||LA11_0==ID||LA11_0==19) ) {
				alt11=1;
			}
			switch (alt11) {
				case 1 :
					// proyecto.g:163:25: expr ( ',' expr )*
					{
					pushFollow(FOLLOW_expr_in_llamadaFuncion196);
					expr();
					state._fsp--;

					// proyecto.g:163:30: ( ',' expr )*
					loop10:
					while (true) {
						int alt10=2;
						int LA10_0 = input.LA(1);
						if ( (LA10_0==COMMA) ) {
							alt10=1;
						}

						switch (alt10) {
						case 1 :
							// proyecto.g:163:31: ',' expr
							{
							match(input,COMMA,FOLLOW_COMMA_in_llamadaFuncion199); 
							pushFollow(FOLLOW_expr_in_llamadaFuncion201);
							expr();
							state._fsp--;

							}
							break;

						default :
							break loop10;
						}
					}

					}
					break;

			}

			match(input,20,FOLLOW_20_in_llamadaFuncion207); 
			registerFunctionCall(currentClassName, (ID5!=null?ID5.getText():null));
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "llamadaFuncion"



	// $ANTLR start "instruccion"
	// proyecto.g:166:1: instruccion : ( asignacion | decl_local | llamadaFuncion SEMICOLON );
	public final void instruccion() throws RecognitionException {
		try {
			// proyecto.g:166:12: ( asignacion | decl_local | llamadaFuncion SEMICOLON )
			int alt12=3;
			int LA12_0 = input.LA(1);
			if ( (LA12_0==ID) ) {
				int LA12_1 = input.LA(2);
				if ( (LA12_1==25) ) {
					alt12=1;
				}
				else if ( (LA12_1==19) ) {
					alt12=3;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 12, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( ((LA12_0 >= DOUBLE && LA12_0 <= FLOAT)||LA12_0==INT||(LA12_0 >= STRING && LA12_0 <= VOID)) ) {
				alt12=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 12, 0, input);
				throw nvae;
			}

			switch (alt12) {
				case 1 :
					// proyecto.g:166:14: asignacion
					{
					pushFollow(FOLLOW_asignacion_in_instruccion221);
					asignacion();
					state._fsp--;

					}
					break;
				case 2 :
					// proyecto.g:166:27: decl_local
					{
					pushFollow(FOLLOW_decl_local_in_instruccion225);
					decl_local();
					state._fsp--;

					}
					break;
				case 3 :
					// proyecto.g:166:40: llamadaFuncion SEMICOLON
					{
					pushFollow(FOLLOW_llamadaFuncion_in_instruccion229);
					llamadaFuncion();
					state._fsp--;

					match(input,SEMICOLON,FOLLOW_SEMICOLON_in_instruccion231); 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "instruccion"



	// $ANTLR start "asignacion"
	// proyecto.g:168:1: asignacion : ID '=' expr SEMICOLON ;
	public final void asignacion() throws RecognitionException {
		Token ID6=null;
		ParserRuleReturnScope expr7 =null;

		try {
			// proyecto.g:168:11: ( ID '=' expr SEMICOLON )
			// proyecto.g:168:13: ID '=' expr SEMICOLON
			{
			ID6=(Token)match(input,ID,FOLLOW_ID_in_asignacion238); 
			match(input,25,FOLLOW_25_in_asignacion240); 
			pushFollow(FOLLOW_expr_in_asignacion242);
			expr7=expr();
			state._fsp--;


			        int tipoVar = searchID((ID6!=null?ID6.getText():null), ID6.getLine());
			        if(tipoVar != (expr7!=null?((proyectoParser.expr_return)expr7).code:0)) {
			            reportError("No se puede asignar un valor de tipo " + (expr7!=null?((proyectoParser.expr_return)expr7).code:0) + 
			                        " a la variable '" + (ID6!=null?ID6.getText():null) + "' de tipo " + tipoVar, 
			                        ID6.getLine());
			        }

			        // Determinar si la expresión es reducible (estática)
			        if ((expr7!=null?((proyectoParser.expr_return)expr7).isStatic:0) == 1) {
			            System.out.println(">> Asignacion a '" + (ID6!=null?ID6.getText():null) + "' es reducible.");
			            tablaBTA.put((ID6!=null?ID6.getText():null), 1);
			        } else {
			            System.out.println(">> Asignacion a '" + (ID6!=null?ID6.getText():null) + "' NO es reducible.");
			            tablaBTA.put((ID6!=null?ID6.getText():null), 0);
			        }

			        imprimirEnteros();
			    
			match(input,SEMICOLON,FOLLOW_SEMICOLON_in_asignacion255); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "asignacion"



	// $ANTLR start "decl_args"
	// proyecto.g:191:1: decl_args : tipo1= tipo id1= ID ( COMMA tipo2= tipo id2= ID )* ;
	public final void decl_args() throws RecognitionException {
		Token id1=null;
		Token id2=null;
		ParserRuleReturnScope tipo1 =null;
		ParserRuleReturnScope tipo2 =null;

		try {
			// proyecto.g:191:10: (tipo1= tipo id1= ID ( COMMA tipo2= tipo id2= ID )* )
			// proyecto.g:191:12: tipo1= tipo id1= ID ( COMMA tipo2= tipo id2= ID )*
			{
			pushFollow(FOLLOW_tipo_in_decl_args265);
			tipo1=tipo();
			state._fsp--;

			id1=(Token)match(input,ID,FOLLOW_ID_in_decl_args269); 
			pushTSL((id1!=null?id1.getText():null), (tipo1!=null?input.toString(tipo1.start,tipo1.stop):null), id1.getLine());
			// proyecto.g:192:5: ( COMMA tipo2= tipo id2= ID )*
			loop13:
			while (true) {
				int alt13=2;
				int LA13_0 = input.LA(1);
				if ( (LA13_0==COMMA) ) {
					alt13=1;
				}

				switch (alt13) {
				case 1 :
					// proyecto.g:192:6: COMMA tipo2= tipo id2= ID
					{
					match(input,COMMA,FOLLOW_COMMA_in_decl_args278); 
					pushFollow(FOLLOW_tipo_in_decl_args282);
					tipo2=tipo();
					state._fsp--;

					id2=(Token)match(input,ID,FOLLOW_ID_in_decl_args286); 
					pushTSL((id2!=null?id2.getText():null), (tipo2!=null?input.toString(tipo2.start,tipo2.stop):null), id2.getLine());
					}
					break;

				default :
					break loop13;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "decl_args"



	// $ANTLR start "decl_local"
	// proyecto.g:194:1: decl_local : tipo id1= ID ( COMMA id2= ID )* SEMICOLON ;
	public final void decl_local() throws RecognitionException {
		Token id1=null;
		Token id2=null;
		ParserRuleReturnScope tipo8 =null;

		try {
			// proyecto.g:194:11: ( tipo id1= ID ( COMMA id2= ID )* SEMICOLON )
			// proyecto.g:194:13: tipo id1= ID ( COMMA id2= ID )* SEMICOLON
			{
			pushFollow(FOLLOW_tipo_in_decl_local297);
			tipo8=tipo();
			state._fsp--;

			id1=(Token)match(input,ID,FOLLOW_ID_in_decl_local301); 
			pushTSL((id1!=null?id1.getText():null), (tipo8!=null?input.toString(tipo8.start,tipo8.stop):null), id1.getLine());
			// proyecto.g:195:5: ( COMMA id2= ID )*
			loop14:
			while (true) {
				int alt14=2;
				int LA14_0 = input.LA(1);
				if ( (LA14_0==COMMA) ) {
					alt14=1;
				}

				switch (alt14) {
				case 1 :
					// proyecto.g:195:6: COMMA id2= ID
					{
					match(input,COMMA,FOLLOW_COMMA_in_decl_local310); 
					id2=(Token)match(input,ID,FOLLOW_ID_in_decl_local314); 
					pushTSL((id2!=null?id2.getText():null), (tipo8!=null?input.toString(tipo8.start,tipo8.stop):null), id2.getLine());
					}
					break;

				default :
					break loop14;
				}
			}

			match(input,SEMICOLON,FOLLOW_SEMICOLON_in_decl_local325); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "decl_local"


	public static class expr_return extends ParserRuleReturnScope {
		public int code;
		public int isStatic;
	};


	// $ANTLR start "expr"
	// proyecto.g:198:1: expr returns [int code, int isStatic] : m1= multExpr ( ( '+' | '-' ) m2= multExpr )* ;
	public final proyectoParser.expr_return expr() throws RecognitionException {
		proyectoParser.expr_return retval = new proyectoParser.expr_return();
		retval.start = input.LT(1);

		ParserRuleReturnScope m1 =null;
		ParserRuleReturnScope m2 =null;

		try {
			// proyecto.g:198:38: (m1= multExpr ( ( '+' | '-' ) m2= multExpr )* )
			// proyecto.g:199:5: m1= multExpr ( ( '+' | '-' ) m2= multExpr )*
			{
			pushFollow(FOLLOW_multExpr_in_expr343);
			m1=multExpr();
			state._fsp--;

			retval.code = (m1!=null?((proyectoParser.multExpr_return)m1).code:0); retval.isStatic = (m1!=null?((proyectoParser.multExpr_return)m1).isStatic:0);
			// proyecto.g:200:5: ( ( '+' | '-' ) m2= multExpr )*
			loop15:
			while (true) {
				int alt15=2;
				int LA15_0 = input.LA(1);
				if ( ((LA15_0 >= 22 && LA15_0 <= 23)) ) {
					alt15=1;
				}

				switch (alt15) {
				case 1 :
					// proyecto.g:200:6: ( '+' | '-' ) m2= multExpr
					{
					if ( (input.LA(1) >= 22 && input.LA(1) <= 23) ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					pushFollow(FOLLOW_multExpr_in_expr363);
					m2=multExpr();
					state._fsp--;


					        if(retval.code != 3) {
					            if((m1!=null?((proyectoParser.multExpr_return)m1).code:0) == (m2!=null?((proyectoParser.multExpr_return)m2).code:0)) retval.code = (m1!=null?((proyectoParser.multExpr_return)m1).code:0);
					            else retval.code = 3;
					        }

					        if ((m1!=null?((proyectoParser.multExpr_return)m1).isStatic:0) == 1 && (m2!=null?((proyectoParser.multExpr_return)m2).isStatic:0) == 1) {
					            retval.isStatic = 1;
					        } else {
					            retval.isStatic = 0;
					        }
					    
					}
					break;

				default :
					break loop15;
				}
			}

			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "expr"


	public static class multExpr_return extends ParserRuleReturnScope {
		public int code;
		public int isStatic;
	};


	// $ANTLR start "multExpr"
	// proyecto.g:214:1: multExpr returns [int code, int isStatic] : a1= atom ( ( '*' | '/' ) a2= atom )* ;
	public final proyectoParser.multExpr_return multExpr() throws RecognitionException {
		proyectoParser.multExpr_return retval = new proyectoParser.multExpr_return();
		retval.start = input.LT(1);

		ParserRuleReturnScope a1 =null;
		ParserRuleReturnScope a2 =null;

		try {
			// proyecto.g:214:42: (a1= atom ( ( '*' | '/' ) a2= atom )* )
			// proyecto.g:215:5: a1= atom ( ( '*' | '/' ) a2= atom )*
			{
			pushFollow(FOLLOW_atom_in_multExpr386);
			a1=atom();
			state._fsp--;

			retval.code = (a1!=null?((proyectoParser.atom_return)a1).code:0); retval.isStatic = (a1!=null?((proyectoParser.atom_return)a1).isStatic:0);
			// proyecto.g:216:5: ( ( '*' | '/' ) a2= atom )*
			loop16:
			while (true) {
				int alt16=2;
				int LA16_0 = input.LA(1);
				if ( (LA16_0==21||LA16_0==24) ) {
					alt16=1;
				}

				switch (alt16) {
				case 1 :
					// proyecto.g:216:6: ( '*' | '/' ) a2= atom
					{
					if ( input.LA(1)==21||input.LA(1)==24 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					pushFollow(FOLLOW_atom_in_multExpr405);
					a2=atom();
					state._fsp--;


					        if(retval.code != 3) {
					            if((a1!=null?((proyectoParser.atom_return)a1).code:0) == (a2!=null?((proyectoParser.atom_return)a2).code:0)) retval.code = (a1!=null?((proyectoParser.atom_return)a1).code:0);
					            else retval.code = 3;
					        }

					        if ((a1!=null?((proyectoParser.atom_return)a1).isStatic:0) == 1 && (a2!=null?((proyectoParser.atom_return)a2).isStatic:0) == 1) {
					            retval.isStatic = 1;
					        } else {
					            retval.isStatic = 0;
					        }
					    
					}
					break;

				default :
					break loop16;
				}
			}

			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "multExpr"


	public static class atom_return extends ParserRuleReturnScope {
		public int code;
		public int isStatic;
	};


	// $ANTLR start "atom"
	// proyecto.g:230:1: atom returns [int code, int isStatic] : ( CINT | CDOUBLE | '(' expr ')' | ID | llamadaFuncion );
	public final proyectoParser.atom_return atom() throws RecognitionException {
		proyectoParser.atom_return retval = new proyectoParser.atom_return();
		retval.start = input.LT(1);

		Token ID10=null;
		ParserRuleReturnScope expr9 =null;

		try {
			// proyecto.g:230:38: ( CINT | CDOUBLE | '(' expr ')' | ID | llamadaFuncion )
			int alt17=5;
			switch ( input.LA(1) ) {
			case CINT:
				{
				alt17=1;
				}
				break;
			case CDOUBLE:
				{
				alt17=2;
				}
				break;
			case 19:
				{
				alt17=3;
				}
				break;
			case ID:
				{
				int LA17_4 = input.LA(2);
				if ( (LA17_4==19) ) {
					alt17=5;
				}
				else if ( (LA17_4==COMMA||LA17_4==SEMICOLON||(LA17_4 >= 20 && LA17_4 <= 24)) ) {
					alt17=4;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 17, 4, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 17, 0, input);
				throw nvae;
			}
			switch (alt17) {
				case 1 :
					// proyecto.g:231:5: CINT
					{
					match(input,CINT,FOLLOW_CINT_in_atom426); 
					 retval.code =1; retval.isStatic =1; 
					}
					break;
				case 2 :
					// proyecto.g:232:5: CDOUBLE
					{
					match(input,CDOUBLE,FOLLOW_CDOUBLE_in_atom440); 
					 retval.code =2; retval.isStatic =1; 
					}
					break;
				case 3 :
					// proyecto.g:233:5: '(' expr ')'
					{
					match(input,19,FOLLOW_19_in_atom451); 
					pushFollow(FOLLOW_expr_in_atom453);
					expr9=expr();
					state._fsp--;

					match(input,20,FOLLOW_20_in_atom455); 

					        retval.code = (expr9!=null?((proyectoParser.expr_return)expr9).code:0);
					        retval.isStatic = (expr9!=null?((proyectoParser.expr_return)expr9).isStatic:0);
					        if(retval.code == 1) { cantidadEnteros++; }
					    
					}
					break;
				case 4 :
					// proyecto.g:238:5: ID
					{
					ID10=(Token)match(input,ID,FOLLOW_ID_in_atom463); 

					        retval.code = searchID((ID10!=null?ID10.getText():null), ID10.getLine());
					        // Revisa si la variable fue marcada como estática (1) o no (0)
					        Integer valor = tablaBTA.get((ID10!=null?ID10.getText():null));
					        retval.isStatic = (valor != null && valor == 1) ? 1 : 0;
					    
					}
					break;
				case 5 :
					// proyecto.g:244:5: llamadaFuncion
					{
					pushFollow(FOLLOW_llamadaFuncion_in_atom471);
					llamadaFuncion();
					state._fsp--;

					 retval.code = 3; retval.isStatic = 0; 
					}
					break;

			}
			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "atom"



	// $ANTLR start "modificadorAcceso"
	// proyecto.g:247:1: modificadorAcceso : ( PUBLIC | PRIVATE | PROTECTED );
	public final void modificadorAcceso() throws RecognitionException {
		try {
			// proyecto.g:247:18: ( PUBLIC | PRIVATE | PROTECTED )
			// proyecto.g:
			{
			if ( (input.LA(1) >= PRIVATE && input.LA(1) <= PUBLIC) ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "modificadorAcceso"


	public static class tipo_return extends ParserRuleReturnScope {
	};


	// $ANTLR start "tipo"
	// proyecto.g:248:1: tipo : ( INT | DOUBLE | FLOAT | STRING | VOID );
	public final proyectoParser.tipo_return tipo() throws RecognitionException {
		proyectoParser.tipo_return retval = new proyectoParser.tipo_return();
		retval.start = input.LT(1);

		try {
			// proyecto.g:248:5: ( INT | DOUBLE | FLOAT | STRING | VOID )
			// proyecto.g:
			{
			if ( (input.LA(1) >= DOUBLE && input.LA(1) <= FLOAT)||input.LA(1)==INT||(input.LA(1) >= STRING && input.LA(1) <= VOID) ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "tipo"

	// Delegated rules



	public static final BitSet FOLLOW_clase_in_program24 = new BitSet(new long[]{0x0000000004007002L});
	public static final BitSet FOLLOW_modificadorAcceso_in_clase35 = new BitSet(new long[]{0x0000000004000000L});
	public static final BitSet FOLLOW_26_in_clase38 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_ID_in_clase40 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_27_in_clase53 = new BitSet(new long[]{0x0000000010037980L});
	public static final BitSet FOLLOW_miembro_in_clase55 = new BitSet(new long[]{0x0000000010037980L});
	public static final BitSet FOLLOW_28_in_clase59 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_metodo_in_miembro74 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_atributo_in_miembro79 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_modificadorAcceso_in_metodo87 = new BitSet(new long[]{0x0000000000030980L});
	public static final BitSet FOLLOW_tipo_in_metodo90 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_ID_in_metodo92 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_19_in_metodo105 = new BitSet(new long[]{0x0000000000130980L});
	public static final BitSet FOLLOW_decl_args_in_metodo107 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_20_in_metodo110 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_27_in_metodo117 = new BitSet(new long[]{0x0000000010030D80L});
	public static final BitSet FOLLOW_instruccion_in_metodo128 = new BitSet(new long[]{0x0000000010030D80L});
	public static final BitSet FOLLOW_28_in_metodo136 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_modificadorAcceso_in_atributo145 = new BitSet(new long[]{0x0000000000030980L});
	public static final BitSet FOLLOW_tipo_in_atributo148 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_ID_in_atributo152 = new BitSet(new long[]{0x0000000000008040L});
	public static final BitSet FOLLOW_COMMA_in_atributo169 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_ID_in_atributo173 = new BitSet(new long[]{0x0000000000008040L});
	public static final BitSet FOLLOW_SEMICOLON_in_atributo184 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_llamadaFuncion191 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_19_in_llamadaFuncion193 = new BitSet(new long[]{0x0000000000180430L});
	public static final BitSet FOLLOW_expr_in_llamadaFuncion196 = new BitSet(new long[]{0x0000000000100040L});
	public static final BitSet FOLLOW_COMMA_in_llamadaFuncion199 = new BitSet(new long[]{0x0000000000080430L});
	public static final BitSet FOLLOW_expr_in_llamadaFuncion201 = new BitSet(new long[]{0x0000000000100040L});
	public static final BitSet FOLLOW_20_in_llamadaFuncion207 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_asignacion_in_instruccion221 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_decl_local_in_instruccion225 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_llamadaFuncion_in_instruccion229 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_SEMICOLON_in_instruccion231 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_asignacion238 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_25_in_asignacion240 = new BitSet(new long[]{0x0000000000080430L});
	public static final BitSet FOLLOW_expr_in_asignacion242 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_SEMICOLON_in_asignacion255 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tipo_in_decl_args265 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_ID_in_decl_args269 = new BitSet(new long[]{0x0000000000000042L});
	public static final BitSet FOLLOW_COMMA_in_decl_args278 = new BitSet(new long[]{0x0000000000030980L});
	public static final BitSet FOLLOW_tipo_in_decl_args282 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_ID_in_decl_args286 = new BitSet(new long[]{0x0000000000000042L});
	public static final BitSet FOLLOW_tipo_in_decl_local297 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_ID_in_decl_local301 = new BitSet(new long[]{0x0000000000008040L});
	public static final BitSet FOLLOW_COMMA_in_decl_local310 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_ID_in_decl_local314 = new BitSet(new long[]{0x0000000000008040L});
	public static final BitSet FOLLOW_SEMICOLON_in_decl_local325 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_multExpr_in_expr343 = new BitSet(new long[]{0x0000000000C00002L});
	public static final BitSet FOLLOW_set_in_expr353 = new BitSet(new long[]{0x0000000000080430L});
	public static final BitSet FOLLOW_multExpr_in_expr363 = new BitSet(new long[]{0x0000000000C00002L});
	public static final BitSet FOLLOW_atom_in_multExpr386 = new BitSet(new long[]{0x0000000001200002L});
	public static final BitSet FOLLOW_set_in_multExpr395 = new BitSet(new long[]{0x0000000000080430L});
	public static final BitSet FOLLOW_atom_in_multExpr405 = new BitSet(new long[]{0x0000000001200002L});
	public static final BitSet FOLLOW_CINT_in_atom426 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CDOUBLE_in_atom440 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_19_in_atom451 = new BitSet(new long[]{0x0000000000080430L});
	public static final BitSet FOLLOW_expr_in_atom453 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_20_in_atom455 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_atom463 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_llamadaFuncion_in_atom471 = new BitSet(new long[]{0x0000000000000002L});
}
