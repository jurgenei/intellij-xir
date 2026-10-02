package main.lexer;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import main.parser.XirElementType;

public interface XirTokens
{
  // Special characters
  IElementType SHARP_MARK = new XirElementType("#");
  IElementType OPEN_VECTOR = new XirElementType("#(");
  IElementType LEFT_PAREN = new XirElementType("(");
  IElementType RIGHT_PAREN = new XirElementType(")");

  IElementType LEFT_CURLY = new XirElementType("{");
  IElementType RIGHT_CURLY = new XirElementType("}");

  IElementType LEFT_SQUARE = new XirElementType("[");
  IElementType RIGHT_SQUARE = new XirElementType("]");

  IElementType QUOTE = new XirElementType("'");
  IElementType QUASIQUOTE = new XirElementType("`");
  IElementType UNQUOTE = new XirElementType(",");
  IElementType UNQUOTE_SPLICING = new XirElementType(",@");
  IElementType SYNTAX = new XirElementType("#'");
  IElementType QUASISYNTAX = new XirElementType("#`");
  IElementType UNSYNTAX = new XirElementType("#,");
  IElementType UNSYNTAX_SPLICING = new XirElementType("#,@");

  // Comments
  IElementType LINE_COMMENT = new XirElementType("line comment");
  IElementType BLOCK_COMMENT = new XirElementType("block comment");
  IElementType DATUM_COMMENT_PRE = new XirElementType("datum comment prefix");
  IElementType DATUM_COMMENT = new XirElementType("datum comment");

  TokenSet COMMENTS = TokenSet.create(LINE_COMMENT, BLOCK_COMMENT, DATUM_COMMENT);

  // Literals
  IElementType STRING_LITERAL = new XirElementType("string literal");
  IElementType NUMBER_LITERAL = new XirElementType("number literal");
  IElementType CHAR_LITERAL = new XirElementType("character literal");
  IElementType BOOLEAN_LITERAL = new XirElementType("boolean literal");
  IElementType NAME_LITERAL = new XirElementType("name literal");

  TokenSet LITERALS = TokenSet.create(NAME_LITERAL);

  IElementType IDENTIFIER = new XirElementType("identifier");
  IElementType KEYWORD = new XirElementType("keyword");
  IElementType PROCEDURE = new XirElementType("procedure");

  IElementType DOT = new XirElementType(".");

  IElementType SPECIAL = new XirElementType("special");

  // Control characters
  IElementType WHITESPACE = TokenType.WHITE_SPACE;
  IElementType BAD_CHARACTER = TokenType.BAD_CHARACTER;

  // Useful token sets
  TokenSet WHITESPACE_SET = TokenSet.create(WHITESPACE);
  TokenSet IDENTIFIERS = TokenSet.create(IDENTIFIER, NAME_LITERAL, PROCEDURE);
  TokenSet STRINGS = TokenSet.create(STRING_LITERAL, NUMBER_LITERAL, CHAR_LITERAL, BOOLEAN_LITERAL);

  TokenSet DATUM_PREFIXES = TokenSet.create(QUOTE, QUASIQUOTE, UNQUOTE, UNQUOTE_SPLICING,
          SYNTAX, QUASISYNTAX, UNSYNTAX, UNSYNTAX_SPLICING);
  TokenSet BRACES = TokenSet.create(LEFT_PAREN, LEFT_CURLY, LEFT_SQUARE, RIGHT_PAREN, RIGHT_CURLY, RIGHT_SQUARE);
  TokenSet OPEN_BRACES = TokenSet.create(LEFT_PAREN, LEFT_CURLY, LEFT_SQUARE);
  TokenSet CLOSE_BRACES = TokenSet.create(RIGHT_PAREN, RIGHT_CURLY, RIGHT_SQUARE);
  TokenSet OPEN_SEXP_BRACES = TokenSet.create(LEFT_PAREN, LEFT_SQUARE);
}
