// Generated from Mpl.g4 by ANTLR 4.7

package org.rss.tools.mpl.parsing.grammar;

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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, IDENTIFIER=15, ABSP=16, 
		HEADER_DELIMITER=17, DASH=18, TEXT_SYMBOL=19, CURRENCY=20, TAB=21, ALPHABET=22, 
		QUOTE=23, TS=24, LINE=25, COMMENT=26;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "IDENTIFIER", "ABSP", "HEADER_DELIMITER", 
		"DASH", "TEXT_SYMBOL", "CURRENCY", "TAB", "ALPHABET", "QUOTE", "TS", "LINE", 
		"COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'template:'", "'states:'", "'['", "','", "']'", "'styles:'", "'[x]'", 
		"'\"@\"'", "'\"'", "'^'", "'('", "'x'", "')'", "'|'", null, null, null, 
		"'-'", null, "'$'", "'\t'", null, "'''"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, "IDENTIFIER", "ABSP", "HEADER_DELIMITER", "DASH", "TEXT_SYMBOL", 
		"CURRENCY", "TAB", "ALPHABET", "QUOTE", "TS", "LINE", "COMMENT"
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
	public String getGrammarFileName() { return "Mpl.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\34\u00a0\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3"+
		"\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\6\20m\n\20\r\20\16\20n\3\21\3\21\3"+
		"\21\6\21t\n\21\r\21\16\21u\3\22\3\22\3\22\3\22\3\22\5\22}\n\22\3\22\3"+
		"\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3"+
		"\31\3\31\3\31\3\32\5\32\u0092\n\32\3\32\3\32\5\32\u0096\n\32\3\33\3\33"+
		"\7\33\u009a\n\33\f\33\16\33\u009d\13\33\3\33\3\33\2\2\34\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\3\2\6\t\2##\'\',\61<B^^aa"+
		"\u0080\u0080\5\2\62;C\\c|\4\2\13\13\"\"\4\2\f\f\17\17\2\u00a6\2\3\3\2"+
		"\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17"+
		"\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2"+
		"\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3"+
		"\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3"+
		"\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\3\67\3\2\2\2\5A\3\2\2\2\7I\3\2\2\2\t"+
		"K\3\2\2\2\13M\3\2\2\2\rO\3\2\2\2\17W\3\2\2\2\21[\3\2\2\2\23_\3\2\2\2\25"+
		"a\3\2\2\2\27c\3\2\2\2\31e\3\2\2\2\33g\3\2\2\2\35i\3\2\2\2\37l\3\2\2\2"+
		"!s\3\2\2\2#w\3\2\2\2%\u0080\3\2\2\2\'\u0082\3\2\2\2)\u0084\3\2\2\2+\u0086"+
		"\3\2\2\2-\u0088\3\2\2\2/\u008a\3\2\2\2\61\u008c\3\2\2\2\63\u0095\3\2\2"+
		"\2\65\u0097\3\2\2\2\678\7v\2\289\7g\2\29:\7o\2\2:;\7r\2\2;<\7n\2\2<=\7"+
		"c\2\2=>\7v\2\2>?\7g\2\2?@\7<\2\2@\4\3\2\2\2AB\7u\2\2BC\7v\2\2CD\7c\2\2"+
		"DE\7v\2\2EF\7g\2\2FG\7u\2\2GH\7<\2\2H\6\3\2\2\2IJ\7]\2\2J\b\3\2\2\2KL"+
		"\7.\2\2L\n\3\2\2\2MN\7_\2\2N\f\3\2\2\2OP\7u\2\2PQ\7v\2\2QR\7{\2\2RS\7"+
		"n\2\2ST\7g\2\2TU\7u\2\2UV\7<\2\2V\16\3\2\2\2WX\7]\2\2XY\7z\2\2YZ\7_\2"+
		"\2Z\20\3\2\2\2[\\\7$\2\2\\]\7B\2\2]^\7$\2\2^\22\3\2\2\2_`\7$\2\2`\24\3"+
		"\2\2\2ab\7`\2\2b\26\3\2\2\2cd\7*\2\2d\30\3\2\2\2ef\7z\2\2f\32\3\2\2\2"+
		"gh\7+\2\2h\34\3\2\2\2ij\7~\2\2j\36\3\2\2\2km\5-\27\2lk\3\2\2\2mn\3\2\2"+
		"\2nl\3\2\2\2no\3\2\2\2o \3\2\2\2pt\5-\27\2qr\7\"\2\2rt\5-\27\2sp\3\2\2"+
		"\2sq\3\2\2\2tu\3\2\2\2us\3\2\2\2uv\3\2\2\2v\"\3\2\2\2wx\7/\2\2xy\7/\2"+
		"\2yz\7/\2\2z|\3\2\2\2{}\7\17\2\2|{\3\2\2\2|}\3\2\2\2}~\3\2\2\2~\177\7"+
		"\f\2\2\177$\3\2\2\2\u0080\u0081\7/\2\2\u0081&\3\2\2\2\u0082\u0083\t\2"+
		"\2\2\u0083(\3\2\2\2\u0084\u0085\7&\2\2\u0085*\3\2\2\2\u0086\u0087\7\13"+
		"\2\2\u0087,\3\2\2\2\u0088\u0089\t\3\2\2\u0089.\3\2\2\2\u008a\u008b\7)"+
		"\2\2\u008b\60\3\2\2\2\u008c\u008d\t\4\2\2\u008d\u008e\3\2\2\2\u008e\u008f"+
		"\b\31\2\2\u008f\62\3\2\2\2\u0090\u0092\7\17\2\2\u0091\u0090\3\2\2\2\u0091"+
		"\u0092\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0096\7\f\2\2\u0094\u0096\7\17"+
		"\2\2\u0095\u0091\3\2\2\2\u0095\u0094\3\2\2\2\u0096\64\3\2\2\2\u0097\u009b"+
		"\7%\2\2\u0098\u009a\n\5\2\2\u0099\u0098\3\2\2\2\u009a\u009d\3\2\2\2\u009b"+
		"\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009e\3\2\2\2\u009d\u009b\3\2"+
		"\2\2\u009e\u009f\b\33\3\2\u009f\66\3\2\2\2\n\2nsu|\u0091\u0095\u009b\4"+
		"\2\3\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}