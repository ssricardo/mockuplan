package org.rss.tools.mpl.grammar;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.junit.jupiter.api.Test;
import org.rss.tools.mpl.parsing.grammar2.MplLexer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.BitSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.rss.tools.mpl.parsing.grammar2.MplLexer.VOCABULARY;

public class LexerTest {

    final ANTLRErrorListener errorListener = new ANTLRErrorListener() {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
            System.err.format("Syntax Error (at line %d): %s : %s %n", line, msg, offendingSymbol);
        }

        @Override
        public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact, BitSet ambigAlts, ATNConfigSet configs) {
            System.err.format("Ambiguity: %s - %s %n", dfa.toLexerString(), recognizer.getCurrentToken().getText());
        }

        @Override
        public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex, BitSet conflictingAlts, ATNConfigSet configs) {
        }

        @Override
        public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction, ATNConfigSet configs) {
        }
    };
    
    private List<Token> getTokensFromText(String txt) throws IOException {
        InputStream is = new ByteArrayInputStream(txt.getBytes());
        CharStream cStream = CharStreams.fromStream(is);
        MplLexer lex = new MplLexer(cStream);
        lex.addErrorListener(errorListener);
        CommonTokenStream tokenStream = new CommonTokenStream(lex);
//        tokenStream.consume();
        tokenStream.fill();
        return tokenStream.getTokens();
    }

    @Test
    void testLexer() throws IOException {
        List<Token> tokens = getTokensFromText("document { \n function(param=\"valor\") \n }");

        System.out.println("Tokens: ");
        printTokens(tokens);
    }

    @Test
    void testFunction() throws IOException {
        List<Token> tokens = getTokensFromText("function ()");
        printTokens(tokens);

        assertEquals(4, tokens.size()); // inclues EOF
        assertEquals(MplLexer.WORD, tokens.get(0).getType());
        assertEquals(MplLexer.OPEN_PAR, tokens.get(1).getType());
        assertEquals(MplLexer.CLOSE_PAR, tokens.get(2).getType());


    }

    @Test
    void testParameters() throws IOException {
        List<Token> tokens = getTokensFromText("param = \"value1\", myNum = 5");
//        printTokens(tokens);
        assertAll(
                () -> assertEquals(8, tokens.size()),
                () -> assertEquals(MplLexer.WORD, tokens.get(0).getType()),
                () -> assertEquals(MplLexer.STRING, tokens.get(2).getType()),
                () -> assertEquals(MplLexer.NUMBER, tokens.get(6).getType(), VOCABULARY.getDisplayName(tokens.get(5).getType()))
        );
    }

    @Test
    void testText() throws IOException {
        List<Token> tokens = getTokensFromText("\"my text with spaces. & + symbols\", \"my other text with \n newLine\"");
        printTokens(tokens);

        assertAll(
                () -> assertEquals(4, tokens.size()),
                () -> assertEquals(MplLexer.STRING, tokens.get(0).getType())
//                () -> assertEquals(MplLexer.STRING, tokens.get(2).getType()),
//                () -> assertEquals(MplLexer.NUMBER, tokens.get(6).getType(), VOCABULARY.getDisplayName(tokens.get(5).getType()))
        );
    }

    private void printTokens(List<Token> tokens) {
        tokens.stream().map(t -> {
            StringBuilder sb = new StringBuilder();
            sb/*.append(t.getType())
                    .append(": ")*/
                    .append(VOCABULARY.getDisplayName(t.getType()))
                    .append("[")
                    .append(t.getText())
                    .append("] ");
            return sb.toString();
        }).forEach(System.out::print);
    }
}
