grammar Mpl;

options {
tokenVocab=MplLexer;
}

@header {
}


document: (function NEWLINE)+ ;

function: name params? body? ;

name: WORD ;

params: OPEN_PAR paramsBody CLOSE_PAR ;

paramsBody: (singleParam (COMMA singleParam)*)? ;

singleParam : WORD EQUALS paramValue ;

paramValue: STRING | NUMBER | BOOLEAN ;

body: OPEN_BRACE NEWLINE document CLOSE_BRACE;