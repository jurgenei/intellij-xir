package main.psi.impl;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class XirFormLetBase extends XirFormLocalBase
{
    public XirFormLetBase(ASTNode node, @NotNull String formName)
    {
        super(node, formName);
    }
}
