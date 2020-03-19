package Lexico;
import java_cup.runtime.*;
import Sintactico.sym;

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
    private Symbol symbol (int type) {
        return new Symbol(type, yyline, yycolumn);
    }

    private Symbol symbol (int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
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
    { number }          { return symbol(sym.ntNumber, yytext()); }
    { double }          { return symbol(sym.ntDouble, yytext()); }
    \"                  { 
                            yybegin(STRING);
                            newString.setLength(0);
                        }
    "null"              { return symbol(sym.ntNull, yytext()); }
    "true"              { return symbol(sym.ntTrue, yytext()); }
    "false"             { return symbol(sym.ntFalse, yytext()); }
    "if"                { return symbol(sym.ntIf, yytext()); }
    "else"              { return symbol(sym.ntElse, yytext()); }
    "switch"            { return symbol(sym.ntSwitch, yytext()); }
    "case"              { return symbol(sym.ntCase, yytext()); }
    "default"           { return symbol(sym.ntDefault, yytext()); }
    "while"             { return symbol(sym.ntWhile, yytext()); }
    "do"                { return symbol(sym.ntDo, yytext()); }
    "for"               { return symbol(sym.ntFor, yytext()); }
    "in"                { return symbol(sym.ntIn, yytext()); }
    "break"             { return symbol(sym.ntBreak, yytext()); }
    "continue"          { return symbol(sym.ntContinue, yytext()); }
    "return"            { return symbol(sym.ntReturn, yytext()); }
    "function"          { return symbol(sym.ntFunction, yytext()); }
    "["                 { return symbol(sym.ntOcrotches, yytext()); }
    "]"                 { return symbol(sym.ntCcrotches, yytext()); }
    "("                 { return symbol(sym.ntOparentheses, yytext()); }
    ")"                 { return symbol(sym.ntCparentheses, yytext()); }
    "{"                 { return symbol(sym.ntObraces, yytext()); }
    "}"                 { return symbol(sym.ntCbraces, yytext()); }
    "!"                 { return symbol(sym.ntNot, yytext()); }
    "+"                 { return symbol(sym.ntPlus, yytext()); }
    "-"                 { return symbol(sym.ntMinus, yytext()); }
    "*"                 { return symbol(sym.ntTimes, yytext()); }
    "/"                 { return symbol(sym.ntDivided, yytext()); }
    "%%"                { return symbol(sym.ntMod, yytext()); }
    "^"                 { return symbol(sym.ntPot, yytext()); }
    "<"                 { return symbol(sym.ntLess, yytext()); }
    "<="                { return symbol(sym.ntLesseq, yytext()); }
    ">"                 { return symbol(sym.ntGreater, yytext()); }
    ">="                { return symbol(sym.ntGreatereq, yytext()); }
    "="                 { return symbol(sym.ntEqual, yytext()); }
    "!="                { return symbol(sym.ntNotequal, yytext()); }
    "&"                 { return symbol(sym.ntAnd, yytext()); }
    "|"                 { return symbol(sym.ntOr, yytext()); }
    "?"                 { return symbol(sym.ntCquestion, yytext()); }
    ":"                 { return symbol(sym.ntColon, yytext()); }
    ";"                 { return symbol(sym.ntSemicolon, yytext()); }
    ","                 { return symbol(sym.ntComma, yytext()); }
    "."                 { return symbol(sym.ntPoint, yytext()); }
    { id }              { return symbol(sym.ntId, yytext()); }
    { singleComment }   { /* IGNORE CASE */ }
    { blockComment }    { /* IGNORE CASE */ }
    { whitespace }      { /* IGNORE CASE */ }
    .                   { System.out.println("El caracter '" + yytext() + "' no pertenece al lenguaje."); }
}

<STRING> {
    \"                  { 
                            yybegin(YYINITIAL); 
                            return symbol(sym.ntString, newString.toString()); 
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
