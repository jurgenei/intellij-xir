package main.settings.colorXir;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import main.XirIcons;
import main.highlighter.XirSyntaxHighlighter;
import main.utils.XirResourceUtil;

import javax.swing.Icon;
import java.util.HashMap;
import java.util.Map;

public class XirColorsAndFontsPage implements ColorSettingsPage
{
  @NotNull
  public String getDisplayName()
  {
    return "XIR";
  }

  @Nullable
  public Icon getIcon()
  {
    return XirIcons.XIR_ICON;
  }

  @NotNull
  public AttributesDescriptor[] getAttributeDescriptors()
  {
    return ATTRS;
  }

  private final XirSyntaxHighlighter syntaxHighlighter;
  private final AttributesDescriptor[] ATTRS;

  public XirColorsAndFontsPage() {
    this.syntaxHighlighter = new XirSyntaxHighlighter();
    this.ATTRS =
        new AttributesDescriptor[]{desc(XirSyntaxHighlighter.IDENTIFIER_ID, syntaxHighlighter.IDENTIFIER),
            desc(XirSyntaxHighlighter.COMMENT_ID, syntaxHighlighter.COMMENT),
//                               desc(XirSyntaxHighlighter.BLOCK_COMMENT_ID, XirSyntaxHighlighter.BLOCK_COMMENT),
//                               desc(XirSyntaxHighlighter.DATUM_COMMENT_ID, XirSyntaxHighlighter.DATUM_COMMENT),
            desc(XirSyntaxHighlighter.NUMBER_ID, syntaxHighlighter.NUMBER),
            desc(XirSyntaxHighlighter.STRING_ID, syntaxHighlighter.STRING),
            desc(XirSyntaxHighlighter.BRACES_ID, syntaxHighlighter.BRACE),
            desc(XirSyntaxHighlighter.PAREN_ID, syntaxHighlighter.PAREN),
            desc(XirSyntaxHighlighter.BAD_CHARACTER_ID, syntaxHighlighter.BAD_CHARACTER),
            desc(XirSyntaxHighlighter.CHAR_ID, syntaxHighlighter.CHAR),
            desc(XirSyntaxHighlighter.LITERAL_ID, syntaxHighlighter.LITERAL),
            desc(XirSyntaxHighlighter.KEYWORD_ID, syntaxHighlighter.KEYWORD),
            desc(XirSyntaxHighlighter.PROCEDURE_ID, syntaxHighlighter.PROCEDURE),
            desc(XirSyntaxHighlighter.SPECIAL_ID, syntaxHighlighter.SPECIAL),
            desc(XirSyntaxHighlighter.QUOTED_TEXT_ID, syntaxHighlighter.QUOTED_TEXT),
            desc(XirSyntaxHighlighter.QUOTED_STRING_ID, syntaxHighlighter.QUOTED_STRING),
            desc(XirSyntaxHighlighter.QUOTED_NUMBER_ID, syntaxHighlighter.QUOTED_NUMBER),
        };
  }

  private static AttributesDescriptor desc(String displayName, TextAttributesKey key)
  {
    return new AttributesDescriptor(displayName, key);
  }

  @NotNull
  public ColorDescriptor[] getColorDescriptors()
  {
    return new ColorDescriptor[0];
  }

  @NotNull
  public SyntaxHighlighter getHighlighter()
  {
    return new XirSyntaxHighlighter();
  }

  @NonNls
  @NotNull
  public String getDemoText()
  {
    String content = XirResourceUtil.readResourceAsString("sample-code.scm");
    if (content != null) {
      return content;
    }

    // Fallback to default demo text
    return ";; Test highlighting\n" +
            "\n" +
            "(define string \"Some string\")\n" +
            "\n" +
            "(define quoted '(my quoted 3 items \"with quoted string\"))\n" +
            "\n" +
            "(define char #\\c)\n" +
            "\n" +
            "(define special #!eof)\n" +
            "\n" +
            "(let ((x '(1 3 5 7 9)))\n" +
            "  (do ((x x (cdr x))\n" +
            "       (sum 0 (+ sum (car x))))\n" +
            "      ((null? x) sum)))";
  }

  @Nullable
  public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap()
  {
    Map<String, TextAttributesKey> map = new HashMap<String, TextAttributesKey>();
    map.put("def", syntaxHighlighter.IDENTIFIER);
    return map;
  }
}
