package main.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.util.Iconable;
import com.intellij.psi.PsiElement;
import main.XirIcons;
import main.psi.util.XirPsiUtil;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class XirFormLibrary extends XirPsiElementBase
{
    public XirFormLibrary(ASTNode node)
    {
        super(node, "XirFormLibrary");
    }

    @Override
    public ItemPresentation getPresentation()
    {
        return new ItemPresentation()
        {
            public String getPresentableText()
            {
                PsiElement child = XirPsiUtil.getNormalChildAt(getMe(), 1);
                if (child == null) {
                    return "<undefined>";
                }
                if (child.getFirstChild() == null) {
                    return "<undefined>";
                }
                PsiElement name_child = XirPsiUtil.getPsiLastNonLeafChild(child);
                if (name_child == null) {
                    return "<undefined>";
                }
                return "library: " + name_child.getText();
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
