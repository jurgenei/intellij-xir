package main.xir;

import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import main.parser.XirPsiCreator;

public interface Xir
{
  // Parsing customisations
  Lexer getLexer();

  PsiParser getParser();

  XirPsiCreator getPsiCreator();
}
