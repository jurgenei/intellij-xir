package main.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.util.Iconable;
import com.intellij.psi.PsiElement;
import main.XirIcons;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class XirFormExport extends XirPsiElementBase
{
    public XirFormExport(ASTNode node)
    {
        super(node, "XirFormExport");
    }

    @Override
    public ItemPresentation getPresentation()
    {
        return new ItemPresentation()
        {
            public String getPresentableText()
            {
                return "export:";
            }

            @Nullable
            public Icon getIcon(boolean open)
            {
                return getMe().getIcon(Iconable.ICON_FLAG_VISIBILITY | Iconable.ICON_FLAG_READ_STATUS);
            }

            @Nullable
            public String getLocationString()
            {
                return null;
            }
        };
    }

    @Override
    public Icon getIcon(int flags)
    {
        return XirIcons.SYMBOL;
    }

    private PsiElement getMe()
    {
        return this;
    }
}
