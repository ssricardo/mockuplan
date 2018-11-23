grammar MplParser;

options {
tokenVocab=MplLexer;
}

@header {
package org.rss.tools.mpl.parsing.grammar2;
}

document: (function NEWLINE)+ ;

function: name params? body? ;

name: WORD ;

params: OPEN_PAR paramsBody CLOSE_PAR ;

paramsBody: (WORD EQUALS paramValue (COMMA WORD EQUALS paramValue)*)? ;

paramValue: STRING | NUMBER | BOOLEAN ;

body: OPEN_BRACE NEWLINE document CLOSE_BRACE NEWLINE;