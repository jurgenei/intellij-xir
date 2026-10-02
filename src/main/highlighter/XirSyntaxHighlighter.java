package main.highlighter;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.SyntaxHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import main.lexer.XirLexer;
import main.lexer.XirTokens;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class XirSyntaxHighlighter extends SyntaxHighlighterBase implements XirTokens
{
  private static final Map<IElementType, TextAttributesKey[]> ATTRIBUTES = new HashMap<IElementType, TextAttributesKey[]>();

  @NotNull
  public Lexer getHighlightingLexer()
  {
    return new XirLexer();
  }

  @NotNull
  public TextAttributesKey[] getTokenHighlights(IElementType tokenType)
  {
    TextAttributesKey[] Keys = ATTRIBUTES.get(tokenType);
    if (null == Keys) {
//      System.out.println("tokenType: " + tokenType.toString());
      return EMPTY_KEYS;
    } else {
      return Keys;
    }
  }

  public static final String COMMENT_ID = "XIR Comment";
  public static final String IDENTIFIER_ID = "XIR Identifier";
  public static final String NUMBER_ID = "XIR Numbers";
  public static final String STRING_ID = "XIR Strings";
  public static final String STRING_ESCAPE_ID = "XIR String Escape";
  public static final String BAD_CHARACTER_ID = "XIR Bad character";
  public static final String BRACES_ID = "XIR Braces";
  public static final String PAREN_ID = "XIR Parentheses";
  public static final String LITERAL_ID = "XIR Literal";
  public static final String CHAR_ID = "XIR Character";
  public static final String KEYWORD_ID = "XIR Keyword";
  public static final String PROCEDURE_ID = "XIR Procedure";
  public static final String SPECIAL_ID = "XIR Special";
  public static final String QUOTED_TEXT_ID = "XIR Quoted text";
  public static final String QUOTED_STRING_ID = "XIR Quoted string";
  public static final String QUOTED_NUMBER_ID = "XIR Quoted number";
  public static final String DOT_ID = "XIR Dot";
  public static final String ABBREVIATION_ID = "XIR Abbreviation";

  public TextAttributesKey COMMENT = createTextAttributesKey(COMMENT_ID, xmlDefault("XML_COMMENT", SyntaxHighlighterColors.LINE_COMMENT));
  public TextAttributesKey IDENTIFIER = createTextAttributesKey(IDENTIFIER_ID, xmlDefault("XML_TAG_NAME", SyntaxHighlighterColors.KEYWORD));
  public TextAttributesKey NUMBER = createTextAttributesKey(NUMBER_ID, xmlDefault("XML_ATTRIBUTE_VALUE", SyntaxHighlighterColors.NUMBER));
  public TextAttributesKey STRING = createTextAttributesKey(STRING_ID, xmlDefault("XML_ATTRIBUTE_VALUE", SyntaxHighlighterColors.STRING));
  public TextAttributesKey STRING_ESCAPE = createTextAttributesKey(STRING_ESCAPE_ID, xmlDefault("XML_ENTITY_REFERENCE", SyntaxHighlighterColors.VALID_STRING_ESCAPE));
  public TextAttributesKey BRACE = createTextAttributesKey(BRACES_ID, xmlDefault("XML_TAG", SyntaxHighlighterColors.BRACES));
  public TextAttributesKey PAREN = createTextAttributesKey(PAREN_ID, xmlDefault("XML_TAG", SyntaxHighlighterColors.PARENTHS));
  public TextAttributesKey LITERAL = createTextAttributesKey(LITERAL_ID, xmlDefault("XML_ATTRIBUTE_NAME", HighlighterColors.TEXT));
  public TextAttributesKey CHAR = createTextAttributesKey(CHAR_ID, xmlDefault("XML_ATTRIBUTE_VALUE", SyntaxHighlighterColors.STRING));
  public TextAttributesKey BAD_CHARACTER = createTextAttributesKey(BAD_CHARACTER_ID, defaultFor(HighlighterColors.BAD_CHARACTER));
  public TextAttributesKey KEYWORD = createTextAttributesKey(KEYWORD_ID, xmlDefault("XML_TAG_NAME", SyntaxHighlighterColors.KEYWORD));
  public TextAttributesKey PROCEDURE = createTextAttributesKey(PROCEDURE_ID, xmlDefault("XML_ATTRIBUTE_NAME", SyntaxHighlighterColors.KEYWORD));
  public TextAttributesKey SPECIAL = createTextAttributesKey(SPECIAL_ID, xmlDefault("XML_ATTRIBUTE_NAME", SyntaxHighlighterColors.KEYWORD));
  public TextAttributesKey QUOTED_TEXT = createTextAttributesKey(QUOTED_TEXT_ID, xmlDefault("XML_ATTRIBUTE_NAME", HighlighterColors.TEXT));
  public TextAttributesKey QUOTED_STRING = createTextAttributesKey(QUOTED_STRING_ID, xmlDefault("XML_ATTRIBUTE_VALUE", SyntaxHighlighterColors.STRING));
  public TextAttributesKey QUOTED_NUMBER = createTextAttributesKey(QUOTED_NUMBER_ID, xmlDefault("XML_ATTRIBUTE_VALUE", SyntaxHighlighterColors.NUMBER));
  public TextAttributesKey DOT = createTextAttributesKey(DOT_ID, xmlDefault("XML_TAG", SyntaxHighlighterColors.DOT));
  public TextAttributesKey ABBREVIATION = createTextAttributesKey(ABBREVIATION_ID, xmlDefault("XML_TAG_NAME", SyntaxHighlighterColors.KEYWORD));

  public static TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

  {
    newFillMap(ATTRIBUTES, pack(COMMENT),
            XirTokens.LINE_COMMENT, XirTokens.BLOCK_COMMENT, XirTokens.DATUM_COMMENT);
    newFillMap(ATTRIBUTES, pack(NUMBER), XirTokens.NUMBER_LITERAL);
    newFillMap(ATTRIBUTES, pack(STRING), XirTokens.STRING_LITERAL);
    newFillMap(ATTRIBUTES, pack(BRACE),
            XirTokens.LEFT_SQUARE, XirTokens.RIGHT_SQUARE, XirTokens.LEFT_CURLY, XirTokens.RIGHT_CURLY);
    newFillMap(ATTRIBUTES, pack(PAREN), XirTokens.LEFT_PAREN, XirTokens.RIGHT_PAREN);
    newFillMap(ATTRIBUTES, pack(CHAR), XirTokens.CHAR_LITERAL);
    newFillMap(ATTRIBUTES, pack(SPECIAL), XirTokens.SPECIAL);
    newFillMap(ATTRIBUTES, pack(KEYWORD), XirTokens.KEYWORD, XirTokens.BOOLEAN_LITERAL);
    newFillMap(ATTRIBUTES, pack(PROCEDURE), XirTokens.PROCEDURE);
    newFillMap(ATTRIBUTES, pack(DOT), XirTokens.DOT);
    newFillMap(ATTRIBUTES, pack(ABBREVIATION),
            XirTokens.QUOTE, XirTokens.QUASIQUOTE, XirTokens.UNQUOTE, XirTokens.UNQUOTE_SPLICING,
            XirTokens.SYNTAX, XirTokens.QUASISYNTAX, XirTokens.UNSYNTAX, XirTokens.UNSYNTAX_SPLICING);
  }

  protected void newFillMap(@NotNull Map<IElementType, TextAttributesKey[]> map, TextAttributesKey[] value, @NotNull TokenSet keys) {
    newFillMap(map, value, keys.getTypes());
  }

  protected void newFillMap(@NotNull Map<IElementType, TextAttributesKey[]> map, TextAttributesKey[] value, @NotNull IElementType... types) {
    IElementType[] var3 = types;
    int var4 = types.length;

    for(int var5 = 0; var5 < var4; ++var5) {
      IElementType type = var3[var5];
      map.put(type, value);
    }
  }

  private TextAttributes defaultFor(TextAttributesKey key)
  {
    return key.getDefaultAttributes();
  }

  private TextAttributes xmlDefault(String xmlExternalName, TextAttributesKey fallback)
  {
    TextAttributes xmlDefault = TextAttributesKey.find(xmlExternalName).getDefaultAttributes();
    return xmlDefault != null ? xmlDefault : defaultFor(fallback);
  }

  private TextAttributes brighter(TextAttributesKey key)
  {
    TextAttributes attributes = key.getDefaultAttributes().clone();
    Color foregroundColor = attributes.getForegroundColor();
    if (foregroundColor != null)
    {
      attributes.setForegroundColor(foregroundColor.brighter());
    }
    else
    {
      attributes.setForegroundColor(Color.darkGray);
    }
    return attributes;
  }

}
