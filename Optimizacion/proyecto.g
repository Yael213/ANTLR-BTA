grammar proyecto;  

@header {
    import java.util.HashMap;
    import java.util.HashSet;
}

@members {
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
}

// Reglas del parser
program: clase+ {printOptimizationReport();}; 

clase: modificadorAcceso? 'class' ID 
    {currentClassName = $ID.text; pushTSG(currentClassName,"class", $ID.getLine());}
    '{' miembro[currentClassName]* '}' 
    {System.out.println("El numero total de enteros con parentesis es: " + cantidadEnterosGlobal);};

miembro[String className]: metodo[className] | atributo;

metodo[String className]: modificadorAcceso? tipo ID 
    {pushTSG($ID.text, $tipo.text, $ID.getLine()); 
     registerFunctionDefinition(className, $ID.text);}
    '(' decl_args? ')' 
    '{' 
        instruccion* 
    '}' {TSL.clear();};

atributo: modificadorAcceso? tipo id1=ID    
    {pushTSG($id1.text, $tipo.text, $id1.getLine());}
    (COMMA id2=ID {pushTSG($id2.text, $tipo.text, $id2.getLine());})* 
    SEMICOLON;

llamadaFuncion: ID '(' (expr (',' expr)*)? ')' 
    {registerFunctionCall(currentClassName, $ID.text);};

instruccion: asignacion | decl_local | llamadaFuncion SEMICOLON;

asignacion: ID '=' expr 
    {
        int tipoVar = searchID($ID.text, $ID.getLine());
        if(tipoVar != $expr.code) {
            reportError("No se puede asignar un valor de tipo " + $expr.code + 
                        " a la variable '" + $ID.text + "' de tipo " + tipoVar, 
                        $ID.getLine());
        }

        // Determinar si la expresión es reducible (estática)
        if ($expr.isStatic == 1) {
            System.out.println(">> Asignacion a '" + $ID.text + "' es reducible.");
            tablaBTA.put($ID.text, 1);
        } else {
            System.out.println(">> Asignacion a '" + $ID.text + "' NO es reducible.");
            tablaBTA.put($ID.text, 0);
        }

        imprimirEnteros();
    }
    SEMICOLON;


decl_args: tipo1=tipo id1=ID {pushTSL($id1.text, $tipo1.text, $id1.getLine());}
    (COMMA tipo2=tipo id2=ID {pushTSL($id2.text, $tipo2.text, $id2.getLine());})*;

decl_local: tipo id1=ID {pushTSL($id1.text, $tipo.text, $id1.getLine());}
    (COMMA id2=ID {pushTSL($id2.text, $tipo.text, $id2.getLine());})* 
    SEMICOLON;

expr returns [int code, int isStatic]: 
    m1=multExpr {$code = $m1.code; $isStatic = $m1.isStatic;} 
    (('+' | '-') m2=multExpr {
        if($code != 3) {
            if($m1.code == $m2.code) $code = $m1.code;
            else $code = 3;
        }

        if ($m1.isStatic == 1 && $m2.isStatic == 1) {
            $isStatic = 1;
        } else {
            $isStatic = 0;
        }
    })*;


multExpr returns [int code, int isStatic]: 
    a1=atom {$code = $a1.code; $isStatic = $a1.isStatic;}
    (('*' | '/') a2=atom {
        if($code != 3) {
            if($a1.code == $a2.code) $code = $a1.code;
            else $code = 3;
        }

        if ($a1.isStatic == 1 && $a2.isStatic == 1) {
            $isStatic = 1;
        } else {
            $isStatic = 0;
        }
    })*;


atom returns [int code, int isStatic]: 
    CINT       { $code=1; $isStatic=1; }
  | CDOUBLE    { $code=2; $isStatic=1; }
  | '(' expr ')' {
        $code = $expr.code;
        $isStatic = $expr.isStatic;
        if($code == 1) { cantidadEnteros++; }
    }
  | ID {
        $code = searchID($ID.text, $ID.getLine());
        // Revisa si la variable fue marcada como estática (1) o no (0)
        Integer valor = tablaBTA.get($ID.text);
        $isStatic = (valor != null && valor == 1) ? 1 : 0;
    }
  | llamadaFuncion { $code = 3; $isStatic = 0; };


modificadorAcceso: PUBLIC | PRIVATE | PROTECTED;
tipo: INT | DOUBLE | FLOAT | STRING | VOID;

// Reglas del lexer
INT: 'int';
DOUBLE: 'double';
FLOAT: 'float';
STRING: 'string';
VOID: 'void';

PUBLIC: 'public';
PRIVATE: 'private';
PROTECTED: 'protected';
SEMICOLON: ';';
COMMA: ',';
GUION_BAJO: '_';

CDOUBLE: (CINT '.' CINT)+;
CINT: ('0'..'9')+;
ID: ('a'..'z'|'A'..'Z'| CINT | GUION_BAJO)+;
WS: (' ' | '\n' | '\t' | '\r')+ { $channel=HIDDEN; };