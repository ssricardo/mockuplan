lexer grammar MplLexer;

@header {
package org.rss.tools.mpl.parsing.grammar2;
}

STRING : DOUBLE_QUOTE TEXT? DOUBLE_QUOTE;

DOUBLE_QUOTE : '"';

OPEN_PAR : '(' ;
CLOSE_PAR : ')' ;

OPEN_BRACE : '{' ;
CLOSE_BRACE : '}' ;

EQUALS: '=' ;

COMMA: ',' ;

fragment LOWERCASE  : [a-z] ;
fragment UPPERCASE  : [A-Z] ;
fragment DIGIT : [0-9] ;

BOOLEAN : 'true' | 'false' ;

WORD                : (LOWERCASE | UPPERCASE | '_')+ ;

NUMBER : DIGIT+;
 
WHITESPACE          : (' ' | '\t') -> skip;

NEWLINE             : ('\r'? '\n' | '\r')+ ;

mode IN_STRING;

    TEXT                : (~["])+ ;