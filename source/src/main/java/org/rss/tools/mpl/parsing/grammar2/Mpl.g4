grammar Mpl;

options {
tokenVocab=MplLexer;
}

@header {
}

document: (function)+ ;

function: name params? body? ( DELIMITER | NEWLINE | EOF );

name: WORD ;

params: OPEN_PAR paramsBody CLOSE_PAR ;

paramsBody: (singleParam (COMMA singleParam)*)? ;

singleParam : (WORD EQUALS)? paramValue ;

paramValue: STRING | NUMBER | BOOLEAN ;

body: OPEN_BRACE NEWLINE function* CLOSE_BRACE ;