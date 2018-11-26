// Generated from D:\dev\git\mockuplan\source\src\main\java\org\rss\tools\mpl\parsing\grammar2\MplLexer.g4 by ANTLR 4.7
package org.rss.tools.mpl.parsing.grammar2;


import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MplLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		STRING=1, DOUBLE_QUOTE=2, OPEN_PAR=3, CLOSE_PAR=4, OPEN_BRACE=5, CLOSE_BRACE=6, 
		EQUALS=7, COMMA=8, BOOLEAN=9, WORD=10, NUMBER=11, WHITESPACE=12, NEWLINE=13, 
		TEXT=14;
	public static final int
		IN_STRING=1;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE", "IN_STRING"
	};

	public static final String[] ruleNames = {
		"STRING", "DOUBLE_QUOTE", "OPEN_PAR", "CLOSE_PAR", "OPEN_BRACE", "CLOSE_BRACE", 
		"EQUALS", "COMMA", "LOWERCASE", "UPPERCASE", "DIGIT", "BOOLEAN", "WORD", 
		"NUMBER", "WHITESPACE", "NEWLINE", "TEXT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, "'\"'", "'('", "')'", "'{'", "'}'", "'='", "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "STRING", "DOUBLE_QUOTE", "OPEN_PAR", "CLOSE_PAR", "OPEN_BRACE", 
		"CLOSE_BRACE", "EQUALS", "COMMA", "BOOLEAN", "WORD", "NUMBER", "WHITESPACE", 
		"NEWLINE", "TEXT"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public MplLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MplLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\20i\b\1\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\5\2)\n\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b"+
		"\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\5\rJ\n\r\3\16\3\16\3\16\6\16O\n\16\r\16\16\16P\3\17\6\17T\n\17\r"+
		"\17\16\17U\3\20\3\20\3\20\3\20\3\21\5\21]\n\21\3\21\3\21\6\21a\n\21\r"+
		"\21\16\21b\3\22\6\22f\n\22\r\22\16\22g\2\2\23\4\3\6\4\b\5\n\6\f\7\16\b"+
		"\20\t\22\n\24\2\26\2\30\2\32\13\34\f\36\r \16\"\17$\20\4\2\3\7\3\2c|\3"+
		"\2C\\\3\2\62;\4\2\13\13\"\"\3\2$$\2n\2\4\3\2\2\2\2\6\3\2\2\2\2\b\3\2\2"+
		"\2\2\n\3\2\2\2\2\f\3\2\2\2\2\16\3\2\2\2\2\20\3\2\2\2\2\22\3\2\2\2\2\32"+
		"\3\2\2\2\2\34\3\2\2\2\2\36\3\2\2\2\2 \3\2\2\2\2\"\3\2\2\2\3$\3\2\2\2\4"+
		"&\3\2\2\2\6,\3\2\2\2\b.\3\2\2\2\n\60\3\2\2\2\f\62\3\2\2\2\16\64\3\2\2"+
		"\2\20\66\3\2\2\2\228\3\2\2\2\24:\3\2\2\2\26<\3\2\2\2\30>\3\2\2\2\32I\3"+
		"\2\2\2\34N\3\2\2\2\36S\3\2\2\2 W\3\2\2\2\"`\3\2\2\2$e\3\2\2\2&(\5\6\3"+
		"\2\')\5$\22\2(\'\3\2\2\2()\3\2\2\2)*\3\2\2\2*+\5\6\3\2+\5\3\2\2\2,-\7"+
		"$\2\2-\7\3\2\2\2./\7*\2\2/\t\3\2\2\2\60\61\7+\2\2\61\13\3\2\2\2\62\63"+
		"\7}\2\2\63\r\3\2\2\2\64\65\7\177\2\2\65\17\3\2\2\2\66\67\7?\2\2\67\21"+
		"\3\2\2\289\7.\2\29\23\3\2\2\2:;\t\2\2\2;\25\3\2\2\2<=\t\3\2\2=\27\3\2"+
		"\2\2>?\t\4\2\2?\31\3\2\2\2@A\7v\2\2AB\7t\2\2BC\7w\2\2CJ\7g\2\2DE\7h\2"+
		"\2EF\7c\2\2FG\7n\2\2GH\7u\2\2HJ\7g\2\2I@\3\2\2\2ID\3\2\2\2J\33\3\2\2\2"+
		"KO\5\24\n\2LO\5\26\13\2MO\7a\2\2NK\3\2\2\2NL\3\2\2\2NM\3\2\2\2OP\3\2\2"+
		"\2PN\3\2\2\2PQ\3\2\2\2Q\35\3\2\2\2RT\5\30\f\2SR\3\2\2\2TU\3\2\2\2US\3"+
		"\2\2\2UV\3\2\2\2V\37\3\2\2\2WX\t\5\2\2XY\3\2\2\2YZ\b\20\2\2Z!\3\2\2\2"+
		"[]\7\17\2\2\\[\3\2\2\2\\]\3\2\2\2]^\3\2\2\2^a\7\f\2\2_a\7\17\2\2`\\\3"+
		"\2\2\2`_\3\2\2\2ab\3\2\2\2b`\3\2\2\2bc\3\2\2\2c#\3\2\2\2df\n\6\2\2ed\3"+
		"\2\2\2fg\3\2\2\2ge\3\2\2\2gh\3\2\2\2h%\3\2\2\2\r\2\3(INPU\\`bg\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}