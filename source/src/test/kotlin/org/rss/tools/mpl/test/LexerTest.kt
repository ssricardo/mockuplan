package org.rss.tools.mpl.test

import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.atn.ATNConfigSet
import org.antlr.v4.runtime.dfa.DFA
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import org.rss.tools.mpl.parsing.grammar2.MplLexer
import org.rss.tools.mpl.parsing.grammar2.MplLexer.VOCABULARY
import java.io.ByteArrayInputStream
import java.io.IOException
import java.util.*

class LexerTest {

    val errorListener: ANTLRErrorListener = object : ANTLRErrorListener {
        override fun syntaxError(recognizer: Recognizer<*, *>, offendingSymbol: Any, line: Int, charPositionInLine: Int, msg: String, e: RecognitionException) {
            System.err.format("Syntax Error (at line %d): %s : %s %n", line, msg, offendingSymbol)
        }

        override fun reportAmbiguity(recognizer: Parser, dfa: DFA, startIndex: Int, stopIndex: Int, exact: Boolean, ambigAlts: BitSet, configs: ATNConfigSet) {
            System.err.format("Ambiguity: %s - %s %n", dfa.toLexerString(), recognizer.currentToken.text)
        }

        override fun reportAttemptingFullContext(recognizer: Parser, dfa: DFA, startIndex: Int, stopIndex: Int, conflictingAlts: BitSet, configs: ATNConfigSet) {}

        override fun reportContextSensitivity(recognizer: Parser, dfa: DFA, startIndex: Int, stopIndex: Int, prediction: Int, configs: ATNConfigSet) {}
    }

    /** Boilerplate to process a string and generate the Tokens for it */
    @Throws(IOException::class)
    private fun getTokensFromText(txt: String): List<Token> {
        val iStream = ByteArrayInputStream(txt.toByteArray())
        val cStream = CharStreams.fromStream(iStream)
        val lex = MplLexer(cStream)
        lex.addErrorListener(errorListener)
        val tokenStream = CommonTokenStream(lex)
        //        tokenStream.consume();
        tokenStream.fill()
        return tokenStream.tokens
    }

    @Test
    @Throws(IOException::class)
    internal fun testLexer() {
        val tokens = getTokensFromText("document { \n function(param=\"valor\") \n }")

        assertTrue(tokens.stream().anyMatch { p -> p.type == MplLexer.OPEN_BRACE })
        println("Tokens: ")
        printTokens(tokens)
    }

    @Test
    @Throws(IOException::class)
    internal fun testFunction() {
        val tokens = getTokensFromText("function ()")
        printTokens(tokens)

        assertEquals(4, tokens.size) // includes EOF
        assertEquals(MplLexer.WORD, tokens[0].type)
        assertEquals(MplLexer.OPEN_PAR, tokens[1].type)
        assertEquals(MplLexer.CLOSE_PAR, tokens[2].type)
    }

    @Test
    @Throws(IOException::class)
    internal fun testParameters() {
        val tokens = getTokensFromText("param = \"value1\", myNum = 5")
        //        printTokens(tokens);
        assertAll(
                Executable { assertEquals(8, tokens.size) },
                Executable { assertEquals(MplLexer.WORD, tokens[0].type) },
                Executable { assertEquals(MplLexer.STRING, tokens[2].type) },
                Executable { assertEquals(MplLexer.NUMBER, tokens[6].type, VOCABULARY.getDisplayName(tokens[5].type)) }
        )
    }

    @Test
    @Throws(IOException::class)
    internal fun testText() {
        val tokens = getTokensFromText("\"my text with spaces. & + symbols\", \"my other text with \n newLine\"")
        printTokens(tokens)

        assertAll(
                Executable { assertEquals(4, tokens.size) },
                Executable { assertEquals(MplLexer.STRING, tokens[0].type) }
        )
    }

    private fun printTokens(tokens: List<Token>) {
        tokens.stream().map { t ->
            "${VOCABULARY.getDisplayName(t.type)} [${t.text}] "
        }.forEach { print(it) }
    }
}
