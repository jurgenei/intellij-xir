package main.psi.impl.list;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import main.psi.api.XirBraced;


public class XirList extends XirListBase implements XirBraced
{
  public XirList(@NotNull ASTNode astNode)
  {
    super(astNode, "XirList");
  }

  @Override
  public String toString()
  {
    return getText();
  }
}
