package main.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import main.lexer.XirTokens;
import main.xir.impl.DefaultXir;


public class XirParserDefinition implements ParserDefinition
{
  private final DefaultXir xir = new DefaultXir();

  @NotNull
  public Lexer createLexer(Project project)
  {
    return xir.getLexer();
  }

  @NotNull
  public PsiParser createParser(Project project)
  {
    return xir.getParser();
  }

  @NotNull
  public IFileElementType getFileNodeType()
  {
    return AST.AST_FILE;
  }

  @NotNull
  public TokenSet getWhitespaceTokens()
  {
    return XirTokens.WHITESPACE_SET;
  }

  @NotNull
  public TokenSet getCommentTokens()
  {
    return XirTokens.COMMENTS;
  }

  @NotNull
  public TokenSet getStringLiteralElements()
  {
    return XirTokens.STRINGS;
  }

  @NotNull
  public PsiElement createElement(ASTNode node)
  {
    XirPsiCreator psiCreator = xir.getPsiCreator();
    return psiCreator.createElement(node);
  }

  @NotNull
  public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right)
  {
    if (XirTokens.DATUM_PREFIXES.contains(left.getElementType()))
    {
      return SpaceRequirements.MUST_NOT;
    }
    else if (left.getElementType() == XirTokens.LEFT_PAREN ||
             right.getElementType() == XirTokens.RIGHT_PAREN ||
             left.getElementType() == XirTokens.RIGHT_PAREN ||
             right.getElementType() == XirTokens.LEFT_PAREN

             ||
             left.getElementType() == XirTokens.LEFT_CURLY ||
             right.getElementType() == XirTokens.RIGHT_CURLY ||
             left.getElementType() == XirTokens.RIGHT_CURLY ||
             right.getElementType() == XirTokens.LEFT_CURLY

             ||
             left.getElementType() == XirTokens.LEFT_SQUARE ||
             right.getElementType() == XirTokens.RIGHT_SQUARE ||
             left.getElementType() == XirTokens.RIGHT_SQUARE ||
             right.getElementType() == XirTokens.LEFT_SQUARE)
    {
      return SpaceRequirements.MAY;
    }
    return SpaceRequirements.MUST;
  }

  public PsiFile createFile(FileViewProvider viewProvider)
  {
    XirPsiCreator psiCreator = xir.getPsiCreator();
    return psiCreator.createFile(viewProvider);
  }
}
