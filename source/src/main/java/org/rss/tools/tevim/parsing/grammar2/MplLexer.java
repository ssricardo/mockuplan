// Generated from /mnt/dados/projetos/git/mockuplan/source/src/main/java/org/rss/tools/tevim/parsing/grammar2/MplLexer.g4 by ANTLR 4.7
package org.rss.tools.tevim.parsing.grammar2;


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
		EQUALS=7, COMMA=8, DELIMITER=9, BOOLEAN=10, WORD=11, NUMBER=12, WHITESPACE=13, 
		NEWLINE=14, TEXT=15;
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
		"EQUALS", "COMMA", "DELIMITER", "LOWERCASE", "UPPERCASE", "DIGIT", "BOOLEAN", 
		"WORD", "NUMBER", "WHITESPACE", "NEWLINE", "TEXT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, "'\"'", "'('", "')'", "'{'", "'}'", "'='", "','", "';'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "STRING", "DOUBLE_QUOTE", "OPEN_PAR", "CLOSE_PAR", "OPEN_BRACE", 
		"CLOSE_BRACE", "EQUALS", "COMMA", "DELIMITER", "BOOLEAN", "WORD", "NUMBER", 
		"WHITESPACE", "NEWLINE", "TEXT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\21m\b\1\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\3\2\5\2+\n\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3"+
		"\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\5\16N\n\16\3\17\3\17\3\17\6\17S\n\17\r"+
		"\17\16\17T\3\20\6\20X\n\20\r\20\16\20Y\3\21\3\21\3\21\3\21\3\22\5\22a"+
		"\n\22\3\22\3\22\6\22e\n\22\r\22\16\22f\3\23\6\23j\n\23\r\23\16\23k\2\2"+
		"\24\4\3\6\4\b\5\n\6\f\7\16\b\20\t\22\n\24\13\26\2\30\2\32\2\34\f\36\r"+
		" \16\"\17$\20&\21\4\2\3\7\3\2c|\3\2C\\\3\2\62;\4\2\13\13\"\"\3\2$$\2r"+
		"\2\4\3\2\2\2\2\6\3\2\2\2\2\b\3\2\2\2\2\n\3\2\2\2\2\f\3\2\2\2\2\16\3\2"+
		"\2\2\2\20\3\2\2\2\2\22\3\2\2\2\2\24\3\2\2\2\2\34\3\2\2\2\2\36\3\2\2\2"+
		"\2 \3\2\2\2\2\"\3\2\2\2\2$\3\2\2\2\3&\3\2\2\2\4(\3\2\2\2\6.\3\2\2\2\b"+
		"\60\3\2\2\2\n\62\3\2\2\2\f\64\3\2\2\2\16\66\3\2\2\2\208\3\2\2\2\22:\3"+
		"\2\2\2\24<\3\2\2\2\26>\3\2\2\2\30@\3\2\2\2\32B\3\2\2\2\34M\3\2\2\2\36"+
		"R\3\2\2\2 W\3\2\2\2\"[\3\2\2\2$d\3\2\2\2&i\3\2\2\2(*\5\6\3\2)+\5&\23\2"+
		"*)\3\2\2\2*+\3\2\2\2+,\3\2\2\2,-\5\6\3\2-\5\3\2\2\2./\7$\2\2/\7\3\2\2"+
		"\2\60\61\7*\2\2\61\t\3\2\2\2\62\63\7+\2\2\63\13\3\2\2\2\64\65\7}\2\2\65"+
		"\r\3\2\2\2\66\67\7\177\2\2\67\17\3\2\2\289\7?\2\29\21\3\2\2\2:;\7.\2\2"+
		";\23\3\2\2\2<=\7=\2\2=\25\3\2\2\2>?\t\2\2\2?\27\3\2\2\2@A\t\3\2\2A\31"+
		"\3\2\2\2BC\t\4\2\2C\33\3\2\2\2DE\7v\2\2EF\7t\2\2FG\7w\2\2GN\7g\2\2HI\7"+
		"h\2\2IJ\7c\2\2JK\7n\2\2KL\7u\2\2LN\7g\2\2MD\3\2\2\2MH\3\2\2\2N\35\3\2"+
		"\2\2OS\5\26\13\2PS\5\30\f\2QS\7a\2\2RO\3\2\2\2RP\3\2\2\2RQ\3\2\2\2ST\3"+
		"\2\2\2TR\3\2\2\2TU\3\2\2\2U\37\3\2\2\2VX\5\32\r\2WV\3\2\2\2XY\3\2\2\2"+
		"YW\3\2\2\2YZ\3\2\2\2Z!\3\2\2\2[\\\t\5\2\2\\]\3\2\2\2]^\b\21\2\2^#\3\2"+
		"\2\2_a\7\17\2\2`_\3\2\2\2`a\3\2\2\2ab\3\2\2\2be\7\f\2\2ce\7\17\2\2d`\3"+
		"\2\2\2dc\3\2\2\2ef\3\2\2\2fd\3\2\2\2fg\3\2\2\2g%\3\2\2\2hj\n\6\2\2ih\3"+
		"\2\2\2jk\3\2\2\2ki\3\2\2\2kl\3\2\2\2l\'\3\2\2\2\r\2\3*MRTY`dfk\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}