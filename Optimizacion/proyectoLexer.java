// $ANTLR 3.5.2 proyecto.g 2025-05-18 14:14:25

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class proyectoLexer extends Lexer {
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
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public proyectoLexer() {} 
	public proyectoLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public proyectoLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "proyecto.g"; }

	// $ANTLR start "T__19"
	public final void mT__19() throws RecognitionException {
		try {
			int _type = T__19;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// proyecto.g:2:7: ( '(' )
			// proyecto.g:2:9: '('
			{
			match('('); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__19"

	// $ANTLR start "T__20"
	public final void mT__20() throws RecognitionException {
		try {
			int _type = T__20;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// proyecto.g:3:7: ( ')' )
			// proyecto.g:3:9: ')'
			{
			match(')'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__20"

	// $ANTLR start "T__21"
	public final void mT__21() throws RecognitionException {
		try {
			int _type = T__21;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// proyecto.g:4:7: ( '*' )
			// proyecto.g:4:9: '*'
			{
			match('*'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__21"

	// $ANTLR start "T__22"
	public final void mT__22() throws RecognitionException {
		try {
			int _type = T__22;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// proyecto.g:5:7: ( '+' )
			// proyecto.g:5:9: '+'
			{
			match('+'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__22"

	// $ANTLR start "T__23"
	public final void mT__23() throws RecognitionException {
		try {
			int _type = T__23;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// proyecto.g:6:7: ( '-' )
			// proyecto.g:6:9: '-'
			{
			match('-'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__23"

	// $ANTLR start "T__24"
	public final void mT__24() throws RecognitionException {
		try {
			int _type = T__24;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// proyecto.g:7:7: ( '/' )
			// proyecto.g:7:9: '/'
			{
			match('/'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__24"

	// $ANTLR start "T__25"
	public final void mT__25() throws RecognitionException {
		try {
			int _type = T__25;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// proyecto.g:8:7: ( '=' )
			// proyecto.g:8:9: '='
			{
			match('='); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__25"

	// $ANTLR start "T__26"
	public final void mT__26() throws RecognitionException {
		try {
			int _type = T__26;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// proyecto.g:9:7: ( 'class' )
			// proyecto.g:9:9: 'class'
			{
			match("class"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__26"

	// $ANTLR start "T__27"
	public final void mT__27() throws RecognitionException {
		try {
			int _type = T__27;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// proyecto.g:10:7: ( '{' )
			// proyecto.g:10:9: '{'
			{
			match('{'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__27"

	// $ANTLR start "T__28"
	public final void mT__28() throws RecognitionException {
		try {
			int _type = T__28;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// proyecto.g:11:7: ( '}' )
			// proyecto.g:11:9: '}'
			{
			match('}'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__28"

	// $ANTLR start "INT"
	public final void mINT() throws RecognitionException {
		try {
			int _type = INT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// proyecto.g:251:4: ( 'int' )
			// proyecto.g:251:6: 'int'
			{
			match("int"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INT"

	// $ANTLR start "DOUBLE"
	public final void mDOUBLE() throws RecognitionException {
		try {
			int _type = DOUBLE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// proyecto.g:252:7: ( 'double' )
			// proyecto.g:252:9: 'double'
			{
			match("double"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DOUBLE"

	// $ANTLR start "FLOAT"
	public final void mFLOAT() throws RecognitionException {
		try {
			int _type = FLOAT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// proyecto.g:253:6: ( 'float' )
			// proyecto.g:253:8: 'float'
			{
			match("float"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FLOAT"

	// $ANTLR start "STRING"
	public final void mSTRING() throws RecognitionException {
		try {
			int _type = STRING;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// proyecto.g:254:7: ( 'string' )
			// proyecto.g:254:9: 'string'
			{
			match("string"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STRING"

	// $ANTLR start "VOID"
	public final void mVOID() throws RecognitionException {
		try {
			int _type = VOID;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// proyecto.g:255:5: ( 'void' )
			// proyecto.g:255:7: 'void'
			{
			match("void"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "VOID"

	// $ANTLR start "PUBLIC"
	public final void mPUBLIC() throws RecognitionException {
		try {
			int _type = PUBLIC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// proyecto.g:257:7: ( 'public' )
			// proyecto.g:257:9: 'public'
			{
			match("public"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PUBLIC"

	// $ANTLR start "PRIVATE"
	public final void mPRIVATE() throws RecognitionException {
		try {
			int _type = PRIVATE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// proyecto.g:258:8: ( 'private' )
			// proyecto.g:258:10: 'private'
			{
			match("private"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PRIVATE"

	// $ANTLR start "PROTECTED"
	public final void mPROTECTED() throws RecognitionException {
		try {
			int _type = PROTECTED;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// proyecto.g:259:10: ( 'protected' )
			// proyecto.g:259:12: 'protected'
			{
			match("protected"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PROTECTED"

	// $ANTLR start "SEMICOLON"
	public final void mSEMICOLON() throws RecognitionException {
		try {
			int _type = SEMICOLON;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// proyecto.g:260:10: ( ';' )
			// proyecto.g:260:12: ';'
			{
			match(';'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SEMICOLON"

	// $ANTLR start "COMMA"
	public final void mCOMMA() throws RecognitionException {
		try {
			int _type = COMMA;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// proyecto.g:261:6: ( ',' )
			// proyecto.g:261:8: ','
			{
			match(','); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COMMA"

	// $ANTLR start "GUION_BAJO"
	public final void mGUION_BAJO() throws RecognitionException {
		try {
			int _type = GUION_BAJO;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// proyecto.g:262:11: ( '_' )
			// proyecto.g:262:13: '_'
			{
			match('_'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GUION_BAJO"

	// $ANTLR start "CDOUBLE"
	public final void mCDOUBLE() throws RecognitionException {
		try {
			int _type = CDOUBLE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// proyecto.g:264:8: ( ( CINT '.' CINT )+ )
			// proyecto.g:264:10: ( CINT '.' CINT )+
			{
			// proyecto.g:264:10: ( CINT '.' CINT )+
			int cnt1=0;
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= '0' && LA1_0 <= '9')) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// proyecto.g:264:11: CINT '.' CINT
					{
					mCINT(); 

					match('.'); 
					mCINT(); 

					}
					break;

				default :
					if ( cnt1 >= 1 ) break loop1;
					EarlyExitException eee = new EarlyExitException(1, input);
					throw eee;
				}
				cnt1++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CDOUBLE"

	// $ANTLR start "CINT"
	public final void mCINT() throws RecognitionException {
		try {
			int _type = CINT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// proyecto.g:265:5: ( ( '0' .. '9' )+ )
			// proyecto.g:265:7: ( '0' .. '9' )+
			{
			// proyecto.g:265:7: ( '0' .. '9' )+
			int cnt2=0;
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( ((LA2_0 >= '0' && LA2_0 <= '9')) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// proyecto.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt2 >= 1 ) break loop2;
					EarlyExitException eee = new EarlyExitException(2, input);
					throw eee;
				}
				cnt2++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CINT"

	// $ANTLR start "ID"
	public final void mID() throws RecognitionException {
		try {
			int _type = ID;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// proyecto.g:266:3: ( ( 'a' .. 'z' | 'A' .. 'Z' | CINT | GUION_BAJO )+ )
			// proyecto.g:266:5: ( 'a' .. 'z' | 'A' .. 'Z' | CINT | GUION_BAJO )+
			{
			// proyecto.g:266:5: ( 'a' .. 'z' | 'A' .. 'Z' | CINT | GUION_BAJO )+
			int cnt3=0;
			loop3:
			while (true) {
				int alt3=5;
				switch ( input.LA(1) ) {
				case 'a':
				case 'b':
				case 'c':
				case 'd':
				case 'e':
				case 'f':
				case 'g':
				case 'h':
				case 'i':
				case 'j':
				case 'k':
				case 'l':
				case 'm':
				case 'n':
				case 'o':
				case 'p':
				case 'q':
				case 'r':
				case 's':
				case 't':
				case 'u':
				case 'v':
				case 'w':
				case 'x':
				case 'y':
				case 'z':
					{
					alt3=1;
					}
					break;
				case 'A':
				case 'B':
				case 'C':
				case 'D':
				case 'E':
				case 'F':
				case 'G':
				case 'H':
				case 'I':
				case 'J':
				case 'K':
				case 'L':
				case 'M':
				case 'N':
				case 'O':
				case 'P':
				case 'Q':
				case 'R':
				case 'S':
				case 'T':
				case 'U':
				case 'V':
				case 'W':
				case 'X':
				case 'Y':
				case 'Z':
					{
					alt3=2;
					}
					break;
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
					{
					alt3=3;
					}
					break;
				case '_':
					{
					alt3=4;
					}
					break;
				}
				switch (alt3) {
				case 1 :
					// proyecto.g:266:6: 'a' .. 'z'
					{
					matchRange('a','z'); 
					}
					break;
				case 2 :
					// proyecto.g:266:15: 'A' .. 'Z'
					{
					matchRange('A','Z'); 
					}
					break;
				case 3 :
					// proyecto.g:266:25: CINT
					{
					mCINT(); 

					}
					break;
				case 4 :
					// proyecto.g:266:32: GUION_BAJO
					{
					mGUION_BAJO(); 

					}
					break;

				default :
					if ( cnt3 >= 1 ) break loop3;
					EarlyExitException eee = new EarlyExitException(3, input);
					throw eee;
				}
				cnt3++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ID"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// proyecto.g:267:3: ( ( ' ' | '\\n' | '\\t' | '\\r' )+ )
			// proyecto.g:267:5: ( ' ' | '\\n' | '\\t' | '\\r' )+
			{
			// proyecto.g:267:5: ( ' ' | '\\n' | '\\t' | '\\r' )+
			int cnt4=0;
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( ((LA4_0 >= '\t' && LA4_0 <= '\n')||LA4_0=='\r'||LA4_0==' ') ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// proyecto.g:
					{
					if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt4 >= 1 ) break loop4;
					EarlyExitException eee = new EarlyExitException(4, input);
					throw eee;
				}
				cnt4++;
			}

			 _channel=HIDDEN; 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	@Override
	public void mTokens() throws RecognitionException {
		// proyecto.g:1:8: ( T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | INT | DOUBLE | FLOAT | STRING | VOID | PUBLIC | PRIVATE | PROTECTED | SEMICOLON | COMMA | GUION_BAJO | CDOUBLE | CINT | ID | WS )
		int alt5=25;
		alt5 = dfa5.predict(input);
		switch (alt5) {
			case 1 :
				// proyecto.g:1:10: T__19
				{
				mT__19(); 

				}
				break;
			case 2 :
				// proyecto.g:1:16: T__20
				{
				mT__20(); 

				}
				break;
			case 3 :
				// proyecto.g:1:22: T__21
				{
				mT__21(); 

				}
				break;
			case 4 :
				// proyecto.g:1:28: T__22
				{
				mT__22(); 

				}
				break;
			case 5 :
				// proyecto.g:1:34: T__23
				{
				mT__23(); 

				}
				break;
			case 6 :
				// proyecto.g:1:40: T__24
				{
				mT__24(); 

				}
				break;
			case 7 :
				// proyecto.g:1:46: T__25
				{
				mT__25(); 

				}
				break;
			case 8 :
				// proyecto.g:1:52: T__26
				{
				mT__26(); 

				}
				break;
			case 9 :
				// proyecto.g:1:58: T__27
				{
				mT__27(); 

				}
				break;
			case 10 :
				// proyecto.g:1:64: T__28
				{
				mT__28(); 

				}
				break;
			case 11 :
				// proyecto.g:1:70: INT
				{
				mINT(); 

				}
				break;
			case 12 :
				// proyecto.g:1:74: DOUBLE
				{
				mDOUBLE(); 

				}
				break;
			case 13 :
				// proyecto.g:1:81: FLOAT
				{
				mFLOAT(); 

				}
				break;
			case 14 :
				// proyecto.g:1:87: STRING
				{
				mSTRING(); 

				}
				break;
			case 15 :
				// proyecto.g:1:94: VOID
				{
				mVOID(); 

				}
				break;
			case 16 :
				// proyecto.g:1:99: PUBLIC
				{
				mPUBLIC(); 

				}
				break;
			case 17 :
				// proyecto.g:1:106: PRIVATE
				{
				mPRIVATE(); 

				}
				break;
			case 18 :
				// proyecto.g:1:114: PROTECTED
				{
				mPROTECTED(); 

				}
				break;
			case 19 :
				// proyecto.g:1:124: SEMICOLON
				{
				mSEMICOLON(); 

				}
				break;
			case 20 :
				// proyecto.g:1:134: COMMA
				{
				mCOMMA(); 

				}
				break;
			case 21 :
				// proyecto.g:1:140: GUION_BAJO
				{
				mGUION_BAJO(); 

				}
				break;
			case 22 :
				// proyecto.g:1:151: CDOUBLE
				{
				mCDOUBLE(); 

				}
				break;
			case 23 :
				// proyecto.g:1:159: CINT
				{
				mCINT(); 

				}
				break;
			case 24 :
				// proyecto.g:1:164: ID
				{
				mID(); 

				}
				break;
			case 25 :
				// proyecto.g:1:167: WS
				{
				mWS(); 

				}
				break;

		}
	}


	protected DFA5 dfa5 = new DFA5(this);
	static final String DFA5_eotS =
		"\10\uffff\1\25\2\uffff\6\25\2\uffff\1\37\1\40\2\uffff\10\25\3\uffff\1"+
		"\25\1\54\10\25\1\uffff\3\25\1\70\3\25\1\74\1\25\1\76\1\25\1\uffff\3\25"+
		"\1\uffff\1\103\1\uffff\1\104\1\105\2\25\3\uffff\1\110\1\25\1\uffff\1\25"+
		"\1\113\1\uffff";
	static final String DFA5_eofS =
		"\114\uffff";
	static final String DFA5_minS =
		"\1\11\7\uffff\1\154\2\uffff\1\156\1\157\1\154\1\164\1\157\1\162\2\uffff"+
		"\1\60\1\56\2\uffff\1\141\1\164\1\165\1\157\1\162\1\151\1\142\1\151\3\uffff"+
		"\1\163\1\60\1\142\1\141\1\151\1\144\1\154\1\166\1\164\1\163\1\uffff\1"+
		"\154\1\164\1\156\1\60\1\151\1\141\1\145\1\60\1\145\1\60\1\147\1\uffff"+
		"\1\143\1\164\1\143\1\uffff\1\60\1\uffff\2\60\1\145\1\164\3\uffff\1\60"+
		"\1\145\1\uffff\1\144\1\60\1\uffff";
	static final String DFA5_maxS =
		"\1\175\7\uffff\1\154\2\uffff\1\156\1\157\1\154\1\164\1\157\1\165\2\uffff"+
		"\2\172\2\uffff\1\141\1\164\1\165\1\157\1\162\1\151\1\142\1\157\3\uffff"+
		"\1\163\1\172\1\142\1\141\1\151\1\144\1\154\1\166\1\164\1\163\1\uffff\1"+
		"\154\1\164\1\156\1\172\1\151\1\141\1\145\1\172\1\145\1\172\1\147\1\uffff"+
		"\1\143\1\164\1\143\1\uffff\1\172\1\uffff\2\172\1\145\1\164\3\uffff\1\172"+
		"\1\145\1\uffff\1\144\1\172\1\uffff";
	static final String DFA5_acceptS =
		"\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\uffff\1\11\1\12\6\uffff\1\23\1"+
		"\24\2\uffff\1\30\1\31\10\uffff\1\25\1\27\1\26\12\uffff\1\13\13\uffff\1"+
		"\17\3\uffff\1\10\1\uffff\1\15\4\uffff\1\14\1\16\1\20\2\uffff\1\21\2\uffff"+
		"\1\22";
	static final String DFA5_specialS =
		"\114\uffff}>";
	static final String[] DFA5_transitionS = {
			"\2\26\2\uffff\1\26\22\uffff\1\26\7\uffff\1\1\1\2\1\3\1\4\1\22\1\5\1\uffff"+
			"\1\6\12\24\1\uffff\1\21\1\uffff\1\7\3\uffff\32\25\4\uffff\1\23\1\uffff"+
			"\2\25\1\10\1\14\1\25\1\15\2\25\1\13\6\25\1\20\2\25\1\16\2\25\1\17\4\25"+
			"\1\11\1\uffff\1\12",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\27",
			"",
			"",
			"\1\30",
			"\1\31",
			"\1\32",
			"\1\33",
			"\1\34",
			"\1\36\2\uffff\1\35",
			"",
			"",
			"\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
			"\1\41\1\uffff\12\24\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
			"",
			"",
			"\1\42",
			"\1\43",
			"\1\44",
			"\1\45",
			"\1\46",
			"\1\47",
			"\1\50",
			"\1\51\5\uffff\1\52",
			"",
			"",
			"",
			"\1\53",
			"\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
			"\1\55",
			"\1\56",
			"\1\57",
			"\1\60",
			"\1\61",
			"\1\62",
			"\1\63",
			"\1\64",
			"",
			"\1\65",
			"\1\66",
			"\1\67",
			"\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
			"\1\71",
			"\1\72",
			"\1\73",
			"\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
			"\1\75",
			"\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
			"\1\77",
			"",
			"\1\100",
			"\1\101",
			"\1\102",
			"",
			"\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
			"",
			"\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
			"\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
			"\1\106",
			"\1\107",
			"",
			"",
			"",
			"\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
			"\1\111",
			"",
			"\1\112",
			"\12\25\7\uffff\32\25\4\uffff\1\25\1\uffff\32\25",
			""
	};

	static final short[] DFA5_eot = DFA.unpackEncodedString(DFA5_eotS);
	static final short[] DFA5_eof = DFA.unpackEncodedString(DFA5_eofS);
	static final char[] DFA5_min = DFA.unpackEncodedStringToUnsignedChars(DFA5_minS);
	static final char[] DFA5_max = DFA.unpackEncodedStringToUnsignedChars(DFA5_maxS);
	static final short[] DFA5_accept = DFA.unpackEncodedString(DFA5_acceptS);
	static final short[] DFA5_special = DFA.unpackEncodedString(DFA5_specialS);
	static final short[][] DFA5_transition;

	static {
		int numStates = DFA5_transitionS.length;
		DFA5_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA5_transition[i] = DFA.unpackEncodedString(DFA5_transitionS[i]);
		}
	}

	protected class DFA5 extends DFA {

		public DFA5(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 5;
			this.eot = DFA5_eot;
			this.eof = DFA5_eof;
			this.min = DFA5_min;
			this.max = DFA5_max;
			this.accept = DFA5_accept;
			this.special = DFA5_special;
			this.transition = DFA5_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | INT | DOUBLE | FLOAT | STRING | VOID | PUBLIC | PRIVATE | PROTECTED | SEMICOLON | COMMA | GUION_BAJO | CDOUBLE | CINT | ID | WS );";
		}
	}

}
