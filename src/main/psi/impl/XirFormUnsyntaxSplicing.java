package main.psi.impl;

import com.intellij.lang.ASTNode;

public class XirFormUnsyntaxSplicing extends XirPsiElementBase
{
    public XirFormUnsyntaxSplicing(ASTNode node)
    {
        super(node, "XirFormUnquoteSplicing");
    }
}
