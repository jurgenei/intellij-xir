package main.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.util.Iconable;
import com.intellij.psi.PsiElement;
import main.XirIcons;
import main.psi.util.XirPsiUtil;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class XirInFormParamList extends XirPsiElementBase
{
    public XirInFormParamList(ASTNode node)
    {
        super(node, "XirInFormParamList");
    }

    @Override
    public ItemPresentation getPresentation()
    {
        return new ItemPresentation()
        {
            public String getPresentableText()
            {
                PsiElement child = XirPsiUtil.getNormalChildAt(getMe(), 0);
                if (child != null) {
                    boolean firstParam = true;
                    String text = child.getText();
                    ASTNode nextNode = XirPsiUtil.getNodeNextNonLeafSibling(child.getNode());
                    if (nextNode == null) {
                        return text;
                    }
                    text += ": (";
                    while (nextNode != null) {
                        if (firstParam) {
                            firstParam = false;
                        } else {
                            text += " ";
                        }
                        text += nextNode.getText();
                        nextNode = XirPsiUtil.getNodeNextNonLeafSibling(nextNode);
                    }
                    text += ")";
                    return text;
                } else {
                    return "<undefined>";
                }
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
