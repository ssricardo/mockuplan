// Generated from /mnt/dados/projetos/git/mockuplan/source/src/main/java/org/rss/tools/tevim/parsing/grammar2/Mpl.g4 by ANTLR 4.7
package org.rss.tools.tevim.parsing.grammar2;


import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MplParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		STRING=1, DOUBLE_QUOTE=2, OPEN_PAR=3, CLOSE_PAR=4, OPEN_BRACE=5, CLOSE_BRACE=6, 
		EQUALS=7, COMMA=8, DELIMITER=9, BOOLEAN=10, WORD=11, NUMBER=12, WHITESPACE=13, 
		NEWLINE=14, TEXT=15;
	public static final int
		RULE_document = 0, RULE_function = 1, RULE_name = 2, RULE_params = 3, 
		RULE_paramsBody = 4, RULE_singleParam = 5, RULE_paramValue = 6, RULE_body = 7;
	public static final String[] ruleNames = {
		"document", "function", "name", "params", "paramsBody", "singleParam", 
		"paramValue", "body"
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

	@Override
	public String getGrammarFileName() { return "Mpl.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MplParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class DocumentContext extends ParserRuleContext {
		public List<FunctionContext> function() {
			return getRuleContexts(FunctionContext.class);
		}
		public FunctionContext function(int i) {
			return getRuleContext(FunctionContext.class,i);
		}
		public DocumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_document; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MplListener ) ((MplListener)listener).enterDocument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MplListener ) ((MplListener)listener).exitDocument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MplVisitor ) return ((MplVisitor<? extends T>)visitor).visitDocument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DocumentContext document() throws RecognitionException {
		DocumentContext _localctx = new DocumentContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_document);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(17); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(16);
				function();
				}
				}
				setState(19); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WORD );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode DELIMITER() { return getToken(MplParser.DELIMITER, 0); }
		public TerminalNode NEWLINE() { return getToken(MplParser.NEWLINE, 0); }
		public TerminalNode EOF() { return getToken(MplParser.EOF, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MplListener ) ((MplListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MplListener ) ((MplListener)listener).exitFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MplVisitor ) return ((MplVisitor<? extends T>)visitor).visitFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(21);
			name();
			setState(23);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_PAR) {
				{
				setState(22);
				params();
				}
			}

			setState(26);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_BRACE) {
				{
				setState(25);
				body();
				}
			}

			setState(28);
			_la = _input.LA(1);
			if ( !(((((_la - -1)) & ~0x3f) == 0 && ((1L << (_la - -1)) & ((1L << (EOF - -1)) | (1L << (DELIMITER - -1)) | (1L << (NEWLINE - -1)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NameContext extends ParserRuleContext {
		public TerminalNode WORD() { return getToken(MplParser.WORD, 0); }
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MplListener ) ((MplListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MplListener ) ((MplListener)listener).exitName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MplVisitor ) return ((MplVisitor<? extends T>)visitor).visitName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			match(WORD);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamsContext extends ParserRuleContext {
		public TerminalNode OPEN_PAR() { return getToken(MplParser.OPEN_PAR, 0); }
		public ParamsBodyContext paramsBody() {
			return getRuleContext(ParamsBodyContext.class,0);
		}
		public TerminalNode CLOSE_PAR() { return getToken(MplParser.CLOSE_PAR, 0); }
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MplListener ) ((MplListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MplListener ) ((MplListener)listener).exitParams(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MplVisitor ) return ((MplVisitor<? extends T>)visitor).visitParams(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_params);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			match(OPEN_PAR);
			setState(33);
			paramsBody();
			setState(34);
			match(CLOSE_PAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamsBodyContext extends ParserRuleContext {
		public List<SingleParamContext> singleParam() {
			return getRuleContexts(SingleParamContext.class);
		}
		public SingleParamContext singleParam(int i) {
			return getRuleContext(SingleParamContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MplParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MplParser.COMMA, i);
		}
		public ParamsBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramsBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MplListener ) ((MplListener)listener).enterParamsBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MplListener ) ((MplListener)listener).exitParamsBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MplVisitor ) return ((MplVisitor<? extends T>)visitor).visitParamsBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamsBodyContext paramsBody() throws RecognitionException {
		ParamsBodyContext _localctx = new ParamsBodyContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_paramsBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << BOOLEAN) | (1L << WORD) | (1L << NUMBER))) != 0)) {
				{
				setState(36);
				singleParam();
				setState(41);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(37);
					match(COMMA);
					setState(38);
					singleParam();
					}
					}
					setState(43);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SingleParamContext extends ParserRuleContext {
		public ParamValueContext paramValue() {
			return getRuleContext(ParamValueContext.class,0);
		}
		public TerminalNode WORD() { return getToken(MplParser.WORD, 0); }
		public TerminalNode EQUALS() { return getToken(MplParser.EQUALS, 0); }
		public SingleParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_singleParam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MplListener ) ((MplListener)listener).enterSingleParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MplListener ) ((MplListener)listener).exitSingleParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MplVisitor ) return ((MplVisitor<? extends T>)visitor).visitSingleParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SingleParamContext singleParam() throws RecognitionException {
		SingleParamContext _localctx = new SingleParamContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_singleParam);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WORD) {
				{
				setState(46);
				match(WORD);
				setState(47);
				match(EQUALS);
				}
			}

			setState(50);
			paramValue();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamValueContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(MplParser.STRING, 0); }
		public TerminalNode NUMBER() { return getToken(MplParser.NUMBER, 0); }
		public TerminalNode BOOLEAN() { return getToken(MplParser.BOOLEAN, 0); }
		public ParamValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MplListener ) ((MplListener)listener).enterParamValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MplListener ) ((MplListener)listener).exitParamValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MplVisitor ) return ((MplVisitor<? extends T>)visitor).visitParamValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamValueContext paramValue() throws RecognitionException {
		ParamValueContext _localctx = new ParamValueContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_paramValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << BOOLEAN) | (1L << NUMBER))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BodyContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACE() { return getToken(MplParser.OPEN_BRACE, 0); }
		public TerminalNode NEWLINE() { return getToken(MplParser.NEWLINE, 0); }
		public TerminalNode CLOSE_BRACE() { return getToken(MplParser.CLOSE_BRACE, 0); }
		public List<FunctionContext> function() {
			return getRuleContexts(FunctionContext.class);
		}
		public FunctionContext function(int i) {
			return getRuleContext(FunctionContext.class,i);
		}
		public BodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MplListener ) ((MplListener)listener).enterBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MplListener ) ((MplListener)listener).exitBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MplVisitor ) return ((MplVisitor<? extends T>)visitor).visitBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(OPEN_BRACE);
			setState(55);
			match(NEWLINE);
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WORD) {
				{
				{
				setState(56);
				function();
				}
				}
				setState(61);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(62);
			match(CLOSE_BRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\21C\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\6\2\24\n\2\r\2"+
		"\16\2\25\3\3\3\3\5\3\32\n\3\3\3\5\3\35\n\3\3\3\3\3\3\4\3\4\3\5\3\5\3\5"+
		"\3\5\3\6\3\6\3\6\7\6*\n\6\f\6\16\6-\13\6\5\6/\n\6\3\7\3\7\5\7\63\n\7\3"+
		"\7\3\7\3\b\3\b\3\t\3\t\3\t\7\t<\n\t\f\t\16\t?\13\t\3\t\3\t\3\t\2\2\n\2"+
		"\4\6\b\n\f\16\20\2\4\4\3\13\13\20\20\5\2\3\3\f\f\16\16\2A\2\23\3\2\2\2"+
		"\4\27\3\2\2\2\6 \3\2\2\2\b\"\3\2\2\2\n.\3\2\2\2\f\62\3\2\2\2\16\66\3\2"+
		"\2\2\208\3\2\2\2\22\24\5\4\3\2\23\22\3\2\2\2\24\25\3\2\2\2\25\23\3\2\2"+
		"\2\25\26\3\2\2\2\26\3\3\2\2\2\27\31\5\6\4\2\30\32\5\b\5\2\31\30\3\2\2"+
		"\2\31\32\3\2\2\2\32\34\3\2\2\2\33\35\5\20\t\2\34\33\3\2\2\2\34\35\3\2"+
		"\2\2\35\36\3\2\2\2\36\37\t\2\2\2\37\5\3\2\2\2 !\7\r\2\2!\7\3\2\2\2\"#"+
		"\7\5\2\2#$\5\n\6\2$%\7\6\2\2%\t\3\2\2\2&+\5\f\7\2\'(\7\n\2\2(*\5\f\7\2"+
		")\'\3\2\2\2*-\3\2\2\2+)\3\2\2\2+,\3\2\2\2,/\3\2\2\2-+\3\2\2\2.&\3\2\2"+
		"\2./\3\2\2\2/\13\3\2\2\2\60\61\7\r\2\2\61\63\7\t\2\2\62\60\3\2\2\2\62"+
		"\63\3\2\2\2\63\64\3\2\2\2\64\65\5\16\b\2\65\r\3\2\2\2\66\67\t\3\2\2\67"+
		"\17\3\2\2\289\7\7\2\29=\7\20\2\2:<\5\4\3\2;:\3\2\2\2<?\3\2\2\2=;\3\2\2"+
		"\2=>\3\2\2\2>@\3\2\2\2?=\3\2\2\2@A\7\b\2\2A\21\3\2\2\2\t\25\31\34+.\62"+
		"=";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}