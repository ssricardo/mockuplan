// Generated from MplParser.g4 by ANTLR 4.7

package org.rss.tools.mpl.parsing.grammar2;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MplParserParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		STRING=1, DOUBLE_QUOTE=2, OPEN_PAR=3, CLOSE_PAR=4, OPEN_BRACE=5, CLOSE_BRACE=6, 
		EQUALS=7, COMMA=8, BOOLEAN=9, WORD=10, NUMBER=11, WHITESPACE=12, NEWLINE=13, 
		TEXT=14;
	public static final int
		RULE_document = 0, RULE_function = 1, RULE_name = 2, RULE_params = 3, 
		RULE_paramsBody = 4, RULE_paramValue = 5, RULE_body = 6;
	public static final String[] ruleNames = {
		"document", "function", "name", "params", "paramsBody", "paramValue", 
		"body"
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

	@Override
	public String getGrammarFileName() { return "MplParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MplParserParser(TokenStream input) {
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
		public List<TerminalNode> NEWLINE() { return getTokens(MplParserParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(MplParserParser.NEWLINE, i);
		}
		public DocumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_document; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MplParserListener ) ((MplParserListener)listener).enterDocument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MplParserListener ) ((MplParserListener)listener).exitDocument(this);
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
				setState(14);
				function();
				setState(15);
				match(NEWLINE);
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
			if ( listener instanceof MplParserListener ) ((MplParserListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MplParserListener ) ((MplParserListener)listener).exitFunction(this);
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
		public TerminalNode WORD() { return getToken(MplParserParser.WORD, 0); }
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MplParserListener ) ((MplParserListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MplParserListener ) ((MplParserListener)listener).exitName(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
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
		public TerminalNode OPEN_PAR() { return getToken(MplParserParser.OPEN_PAR, 0); }
		public ParamsBodyContext paramsBody() {
			return getRuleContext(ParamsBodyContext.class,0);
		}
		public TerminalNode CLOSE_PAR() { return getToken(MplParserParser.CLOSE_PAR, 0); }
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MplParserListener ) ((MplParserListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MplParserListener ) ((MplParserListener)listener).exitParams(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_params);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			match(OPEN_PAR);
			setState(31);
			paramsBody();
			setState(32);
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
		public List<TerminalNode> WORD() { return getTokens(MplParserParser.WORD); }
		public TerminalNode WORD(int i) {
			return getToken(MplParserParser.WORD, i);
		}
		public List<TerminalNode> EQUALS() { return getTokens(MplParserParser.EQUALS); }
		public TerminalNode EQUALS(int i) {
			return getToken(MplParserParser.EQUALS, i);
		}
		public List<ParamValueContext> paramValue() {
			return getRuleContexts(ParamValueContext.class);
		}
		public ParamValueContext paramValue(int i) {
			return getRuleContext(ParamValueContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MplParserParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MplParserParser.COMMA, i);
		}
		public ParamsBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramsBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MplParserListener ) ((MplParserListener)listener).enterParamsBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MplParserListener ) ((MplParserListener)listener).exitParamsBody(this);
		}
	}

	public final ParamsBodyContext paramsBody() throws RecognitionException {
		ParamsBodyContext _localctx = new ParamsBodyContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_paramsBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WORD) {
				{
				setState(34);
				match(WORD);
				setState(35);
				match(EQUALS);
				setState(36);
				paramValue();
				setState(43);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(37);
					match(COMMA);
					setState(38);
					match(WORD);
					setState(39);
					match(EQUALS);
					setState(40);
					paramValue();
					}
					}
					setState(45);
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

	public static class ParamValueContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(MplParserParser.STRING, 0); }
		public TerminalNode NUMBER() { return getToken(MplParserParser.NUMBER, 0); }
		public TerminalNode BOOLEAN() { return getToken(MplParserParser.BOOLEAN, 0); }
		public ParamValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MplParserListener ) ((MplParserListener)listener).enterParamValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MplParserListener ) ((MplParserListener)listener).exitParamValue(this);
		}
	}

	public final ParamValueContext paramValue() throws RecognitionException {
		ParamValueContext _localctx = new ParamValueContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_paramValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
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
		public TerminalNode OPEN_BRACE() { return getToken(MplParserParser.OPEN_BRACE, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(MplParserParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(MplParserParser.NEWLINE, i);
		}
		public DocumentContext document() {
			return getRuleContext(DocumentContext.class,0);
		}
		public TerminalNode CLOSE_BRACE() { return getToken(MplParserParser.CLOSE_BRACE, 0); }
		public BodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MplParserListener ) ((MplParserListener)listener).enterBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MplParserListener ) ((MplParserListener)listener).exitBody(this);
		}
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_body);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			match(OPEN_BRACE);
			setState(51);
			match(NEWLINE);
			setState(52);
			document();
			setState(53);
			match(CLOSE_BRACE);
			setState(54);
			match(NEWLINE);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\20;\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\3\2\6\2\24\n\2\r\2"+
		"\16\2\25\3\3\3\3\5\3\32\n\3\3\3\5\3\35\n\3\3\4\3\4\3\5\3\5\3\5\3\5\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\7\6,\n\6\f\6\16\6/\13\6\5\6\61\n\6\3\7\3\7\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\2\2\t\2\4\6\b\n\f\16\2\3\5\2\3\3\13\13\r\r"+
		"\28\2\23\3\2\2\2\4\27\3\2\2\2\6\36\3\2\2\2\b \3\2\2\2\n\60\3\2\2\2\f\62"+
		"\3\2\2\2\16\64\3\2\2\2\20\21\5\4\3\2\21\22\7\17\2\2\22\24\3\2\2\2\23\20"+
		"\3\2\2\2\24\25\3\2\2\2\25\23\3\2\2\2\25\26\3\2\2\2\26\3\3\2\2\2\27\31"+
		"\5\6\4\2\30\32\5\b\5\2\31\30\3\2\2\2\31\32\3\2\2\2\32\34\3\2\2\2\33\35"+
		"\5\16\b\2\34\33\3\2\2\2\34\35\3\2\2\2\35\5\3\2\2\2\36\37\7\f\2\2\37\7"+
		"\3\2\2\2 !\7\5\2\2!\"\5\n\6\2\"#\7\6\2\2#\t\3\2\2\2$%\7\f\2\2%&\7\t\2"+
		"\2&-\5\f\7\2\'(\7\n\2\2()\7\f\2\2)*\7\t\2\2*,\5\f\7\2+\'\3\2\2\2,/\3\2"+
		"\2\2-+\3\2\2\2-.\3\2\2\2.\61\3\2\2\2/-\3\2\2\2\60$\3\2\2\2\60\61\3\2\2"+
		"\2\61\13\3\2\2\2\62\63\t\2\2\2\63\r\3\2\2\2\64\65\7\7\2\2\65\66\7\17\2"+
		"\2\66\67\5\2\2\2\678\7\b\2\289\7\17\2\29\17\3\2\2\2\7\25\31\34-\60";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}