package main.psi.impl;

import com.intellij.lang.ASTNode;


public class XirQuoted extends XirPsiElementBase
{
  public XirQuoted(ASTNode node)
  {
    super(node, "Quoted");
  }

  @Override
  public String toString()
  {
    return "XirQuoted";
  }
}
