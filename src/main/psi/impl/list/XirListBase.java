package main.psi.impl.list;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import main.psi.impl.XirPsiElementBase;


public abstract class XirListBase extends XirPsiElementBase
{
  public XirListBase(@NotNull ASTNode astNode, String name)
  {
    super(astNode, name);
  }
}
