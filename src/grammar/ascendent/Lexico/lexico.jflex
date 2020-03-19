package grammar.ascendent.Lexico;
import java_cup.runtime.*;
import grammar.ascendent.Sintactico.sym;

%%

%class Lexer 
%unicode 
%cup 
%line 
%column 
%public 
%ignorecase 
%state STRING 

%{
    private Symbol symbol (int primitivo) {
        return new Symbol(primitivo, yyline, yycolumn);
    }

    private Symbol symbol (int primitivo, Object value) {
        return new Symbol(primitivo, yyline, yycolumn, value);
    }

    StringBuilder newString = new StringBuilder();
    char newChar;
%}

endLine         = \r|\n|\r\n
whitespace      = {endLine} | [ \t\f]
number          = [0-9] [0-9]*
double          = [0-9]+ \. [0-9]+
id              = [._a-zA-Z]([._a-zA-Z] | {number})*
singleComment   = "#" ~ "\n"
blockComment    = "#*" ~ "*#"

%%

<YYINITIAL> {
    { number }          { return symbol(sym.NT_NUMBER, yytext()); }
    { double }          { return symbol(sym.NT_DOUBLE, yytext()); }
    \"                  { 
                            yybegin(STRING);
                            newString.setLength(0);
                        }
    "null"              { return symbol(sym.NT_NULL, yytext()); }
    "true"              { return symbol(sym.NT_TRUE, yytext()); }
    "false"             { return symbol(sym.NT_FALSE, yytext()); }
    "if"                { return symbol(sym.NT_IF, yytext()); }
    "else"              { return symbol(sym.NT_ELSE, yytext()); }
    "switch"            { return symbol(sym.NT_SWITCH, yytext()); }
    "case"              { return symbol(sym.NT_CASE, yytext()); }
    "default"           { return symbol(sym.NT_DEFAULT, yytext()); }
    "while"             { return symbol(sym.NT_WHILE, yytext()); }
    "do"                { return symbol(sym.NT_DO, yytext()); }
    "for"               { return symbol(sym.NT_FOR, yytext()); }
    "in"                { return symbol(sym.NT_IN, yytext()); }
    "break"             { return symbol(sym.NT_BREAK, yytext()); }
    "continue"          { return symbol(sym.NT_CONTINUE, yytext()); }
    "return"            { return symbol(sym.NT_RETURN, yytext()); }
    "function"          { return symbol(sym.NT_FUNCTION, yytext()); }
    "["                 { return symbol(sym.NT_O_CROTCHES, yytext()); }
    "]"                 { return symbol(sym.NT_C_CROTCHES, yytext()); }
    "[["                { return symbol(sym.NT_DBO_CROTCHES, yytext()); }
    "]]"                { return symbol(sym.NT_DBC_CROTCHES, yytext()); }
    "("                 { return symbol(sym.NT_O_PARENTHESOS, yytext()); }
    ")"                 { return symbol(sym.NT_C_PARENTHESOS, yytext()); }
    "{"                 { return symbol(sym.NT_O_BRACES, yytext()); }
    "}"                 { return symbol(sym.NT_C_BRACES, yytext()); }
    "!"                 { return symbol(sym.NT_NOT, yytext()); }
    "+"                 { return symbol(sym.NT_PLUS, yytext()); }
    "-"                 { return symbol(sym.NT_MINUS, yytext()); }
    "*"                 { return symbol(sym.NT_TIMES, yytext()); }
    "/"                 { return symbol(sym.NT_DIVIDED, yytext()); }
    "%%"                { return symbol(sym.NT_MOD, yytext()); }
    "^"                 { return symbol(sym.NT_POT, yytext()); }
    "<"                 { return symbol(sym.NT_LESS, yytext()); }
    "<="                { return symbol(sym.NT_LESSQ, yytext()); }
    ">"                 { return symbol(sym.NT_GREATER, yytext()); }
    ">="                { return symbol(sym.NT_GREATERQ, yytext()); }
    "=="                { return symbol(sym.NT_EQUAL, yytext()); }
    "!="                { return symbol(sym.NT_NOT_EQUAL, yytext()); }
    "&"                 { return symbol(sym.NT_AND, yytext()); }
    "|"                 { return symbol(sym.NT_OR, yytext()); }
    "="                 { return symbol(sym.NT_ASSIGN, yytext()); }
    "?"                 { return symbol(sym.NT_C_QUESTION, yytext()); }
    ":"                 { return symbol(sym.NT_COLON, yytext()); }
    ";"                 { return symbol(sym.NT_SEMICOLON, yytext()); }
    ","                 { return symbol(sym.NT_COMMA, yytext()); }
    "=>"                { return symbol(sym.NT_ARROW, yytext()); }
    { endLine }         { return symbol(sym.NT_JUMPLINE, yytext()); }
    { id }              { return symbol(sym.NT_ID, yytext()); }
    { singleComment }   { /* IGNORE CASE */ }
    { blockComment }    { /* IGNORE CASE */ }
    { whitespace }      { /* IGNORE CASE */ }
    .                   { System.out.println("El caracter '" + yytext() + "' no pertenece al lenguaje."); }
}

<STRING> {
    \"                  { 
                            yybegin(YYINITIAL); 
                            return symbol(sym.NT_STRING, newString.toString()); 
                        }
    \\\"                { newString.append('\"'); }
    \\\\                { newString.append('\\'); }
    \\n                 { newString.append('\n'); }
    \\t                 { newString.append('\t'); }
    \\r                 { newString.append('\r'); }
    { endLine }         {
                            yybegin(YYINITIAL);
                            System.out.println("String sin finalizar");
                        }
    .                   { newString.append(yytext()); }    
}
