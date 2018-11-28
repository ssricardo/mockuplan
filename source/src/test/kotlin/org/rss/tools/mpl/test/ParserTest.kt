package org.rss.tools.mpl.test

import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.atn.ATNConfigSet
import org.antlr.v4.runtime.dfa.DFA
import org.junit.Assert.*
import org.junit.jupiter.api.Test
import org.rss.tools.mpl.parsing.grammar2.MplLexer
import org.rss.tools.mpl.parsing.grammar2.MplParser
import java.util.*

class ParserTest {

    @Test
    internal fun testDocument() {
        val tokens = listOf(
                TestToken("init", MplLexer.WORD),
                TestToken("", MplLexer.NEWLINE),
                TestToken("print", MplLexer.WORD),
                TestToken("", MplLexer.NEWLINE)
        )
        val p = createParserNoError(tokens)
        val doc = p.document()
        assertTrue("init" in doc.text)
        val fList = doc.function()
        assertEquals(2, fList.size)
    }

    @Test
    internal fun testFunctionPlain() {
        val noParam = createParserNoError(listOf(
                TestToken("count", MplLexer.WORD),
                TestToken("", MplLexer.NEWLINE)))

        val f = noParam.function()
        assertTrue("count" in f.text)
    }

    @Test
    internal fun testFunctionParam() {
        val parser = createParserNoError(listOf(
                TestToken("count", MplLexer.WORD),
                TestToken("(", MplLexer.OPEN_PAR),
                TestToken("order", MplLexer.WORD),
                TestToken("=", MplLexer.EQUALS),
                TestToken("1", MplLexer.NUMBER),
                TestToken(",", MplLexer.COMMA),

                TestToken("visible", MplLexer.WORD),
                TestToken("=", MplLexer.EQUALS),
                TestToken("false", MplLexer.BOOLEAN),
                TestToken(")", MplLexer.CLOSE_PAR)))

        val f = parser.function()

        val paramList = f.params().paramsBody().singleParam()
        assertEquals(2, paramList.size)
        with(paramList.get(0)) {
            assertEquals("order", this.WORD().text)
            assertEquals("1", this.paramValue().text)
        }
    }

    @Test
    internal fun testFunctionBody() {
        val parser = createParserNoError(listOf(
                TestToken("count", MplLexer.WORD),
                TestToken("{", MplLexer.OPEN_BRACE),
                TestToken("", MplLexer.NEWLINE),
                TestToken("line", MplLexer.WORD),
                TestToken("", MplLexer.NEWLINE),
                TestToken("}", MplLexer.CLOSE_BRACE)))

        val f = parser.function()
        assertTrue("count" in f.text)
    }

    private fun createParserNoError(tokens: List<TestToken>): MplParser {
        val ts = ListTokenSource(tokens)
        val c = CommonTokenStream(ts)
        val p = MplParser(c)
        p.addErrorListener(NoErrorListener)
        return p
    }

    object NoErrorListener: BaseErrorListener() {

        override fun syntaxError(recognizer: Recognizer<*, *>?, offendingSymbol: Any?, line: Int, charPositionInLine: Int, msg: String?, e: RecognitionException?) {
            fail()
        }

        override fun reportAmbiguity(recognizer: Parser?, dfa: DFA?, startIndex: Int, stopIndex: Int, exact: Boolean, ambigAlts: BitSet?, configs: ATNConfigSet?) {
            fail()
        }
    }

    class TestToken (val _text: String, val tcod: Int) : Token {

        override fun getText(): String? {
            return _text
        }

        override fun getType(): Int {
            return tcod
        }

        override fun getLine(): Int = 0


        override fun getCharPositionInLine(): Int = 0

        override fun getChannel(): Int = 0

        override fun getTokenIndex(): Int = 0

        override fun getStartIndex(): Int = 0

        override fun getStopIndex(): Int = 0

        override fun getTokenSource(): TokenSource? {
            return null
        }

        override fun getInputStream(): CharStream? {
            return null
        }
    }
}

