Construya un IDE (Entorno integrado de desarrollo)
en la herramienta que usted prefiera para llevar a cabo análisis semántico empleando los analizadores generados por ANTLR.
El IDE debe tener como mínimo una caja de texto para editar un programa de prueba, así como una opción para cargar un programa de prueba y
otra opción para compilar y que muestre los errores detectados por el analizador semántico en el propio en una caja de texto del propio IDE.
Se va a valorar positivamente el hecho de que agreguen alguna barra de herramientas con speed buttons para cargar y compilar el programa.
El compilador debe reconocer un programa como el siguiente;

Las características del analizador semántico son las siguientes:

- [x] Debe usar una tabla de símbolos local (TSL) para variables locales y argumentos locales del método
- [x] Debe usar una tabla de símbolos global (TSG) para clases, métodos y atributos

Debe marcar error en

- [x] Redeclaración de clases (no se admite repetición de nombre de clases)
- [x] Redeclaración de métodos (no se admite repetición de nombre de métodos en una clase)
- [x] Redeclaración de variables locales dentro de un método (no pueden reusarse variables locales, sin embargo, si pueden usarse IDs locales que ya existen en TSG)
- [x] Uso de variables no declaradas (ni en TSL, ni en TSG)
- [x] Expresiones que combinan operandos de diferente tipo
- [x] Asignación de una expresión de un tipo a un ID de diferente tipo a la expresión
- [x] Al buscar el tipo de un ID en una expresión debe buscar primero en TSL (ya que puede ser variable global o un argumento y después en TSG (ya que puede ser un atributo de la clase)

Falta por agregar

- [ ] Mensajes de errores, que no se vean horribles
- [ ] Los IDs puedan aceptar otros caracteres guiones bajos, símbolos, etc.
- [ ] Interfaz gráfica

Cosas que estaría bien agregar
- [ ] Qué los mismos nombres de métodos se pueden usar en distintas clases