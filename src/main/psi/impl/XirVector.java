package main.psi.impl;

import com.intellij.lang.ASTNode;
import main.psi.api.XirBraced;


public class XirVector extends XirPsiElementBase implements XirBraced
{
  public XirVector(ASTNode node)
  {
    super(node, "XirVector");
  }
}
