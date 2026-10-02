package main.xir.impl;

import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import main.lexer.XirLexer;
import main.parser.XirParser;
import main.parser.XirPsiCreator;
import main.xir.Xir;

public class DefaultXir implements Xir
{
  @Override
  public Lexer getLexer()
  {
    return new XirLexer();
  }

  @Override
  public PsiParser getParser()
  {
    return new XirParser();
  }

  @Override
  public XirPsiCreator getPsiCreator()
  {
    return new XirPsiCreator();
  }
}
