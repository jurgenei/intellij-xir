package main.highlighter;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import main.lexer.XirTokens;

public class XirBraceMatcher implements PairedBraceMatcher
{
  private static final
  BracePair[]
    PAIRS =
    new BracePair[]{new BracePair(XirTokens.LEFT_PAREN, XirTokens.RIGHT_PAREN, true),
                    new BracePair(XirTokens.LEFT_SQUARE, XirTokens.RIGHT_SQUARE, true),
                    new BracePair(XirTokens.LEFT_CURLY, XirTokens.RIGHT_CURLY, true),
                    new BracePair(XirTokens.OPEN_VECTOR, XirTokens.RIGHT_PAREN, true)};

  public BracePair[] getPairs()
  {
    return PAIRS;
  }

  public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType, @Nullable IElementType tokenType)
  {
    return tokenType == null ||
           XirTokens.WHITESPACE_SET.contains(tokenType) ||
           XirTokens.COMMENTS.contains(tokenType) ||
           tokenType == XirTokens.UNQUOTE ||
           tokenType == XirTokens.RIGHT_SQUARE ||
           tokenType == XirTokens.RIGHT_PAREN ||
           tokenType == XirTokens.RIGHT_CURLY;
  }

  public int getCodeConstructStart(PsiFile file, int openingBraceOffset)
  {
    return openingBraceOffset;
  }
}
