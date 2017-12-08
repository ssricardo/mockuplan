grammar Smml;

@header {
package org.rss.tools.smml.parsing.grammar;
}

document: header LINE* body;

header: HEADER_DELIMITER headerBody HEADER_DELIMITER;

headerBody: templateLine? statesLine? stylesLine?;

templateLine: 'template:' TS? QUOTE fileName QUOTE LINE;
statesLine: 'states:' TS? '[' IDENTIFIER (',' TS? IDENTIFIER)* ']' LINE;
stylesLine: 'styles:' TS? '[' style ']' LINE;
style: (IDENTIFIER | fileName) (',' TS? (IDENTIFIER | fileName))*;

body: bodyLine+;
bodyLine: TAB* component+ LINE;
component: lineHeader
        | button
        | inputText
        | email
        | checkbox
        | radio
        | combobox
        | table
        | list
        | lineText;

lineHeader: CURRENCY+ ABSP;
checkbox: '[x]' TS?;
button: '[' TS? (IDENTIFIER | ABSP) TS? ']';
email: '"@"';
inputText: ('"' TS* '"');
combobox: '^' TS? IDENTIFIER '^';
radio: '(' TS? 'x'? ')';
table: '|' TS? IDENTIFIER TS? '|';
list: DASH listItem;
listItem: TS? (IDENTIFIER | ABSP);
lineText: (IDENTIFIER | TEXT_SYMBOL | ABSP)+;

fileName: ((TEXT_SYMBOL | DASH)* IDENTIFIER)+;

/* -------------------- */
IDENTIFIER: ALPHABET+;
ABSP: (ALPHABET | (' ' ALPHABET))+;
HEADER_DELIMITER: '---' ('\r'? '\n');
DASH: '-';
TEXT_SYMBOL: '!' | '%' | '_' | '*'..'.' | ':'..'@' | '/' | '\\' | '~';

CURRENCY: '$';
TAB: '\t';
ALPHABET: [a-zA-Z0-9];
QUOTE: '\'';
TS: (' ' | '\t') -> channel(HIDDEN);
LINE: '\r'? '\n' | '\r';

COMMENT: '#'  ~[\n\r]* -> skip;