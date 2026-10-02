package main.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import main.XirIcons;
import org.jetbrains.annotations.NotNull;
import main.psi.api.XirPsiElement;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;


public abstract class XirPsiElementBase extends ASTWrapperPsiElement implements XirPsiElement
{
  final private String name;

  public XirPsiElementBase(@NotNull ASTNode astNode, @NotNull String name)
  {
    super(astNode);
    this.name = name;
  }

  public <T> T findFirstChildByClass(Class<T> aClass)
  {
    PsiElement element = getFirstChild();
    while (element != null && !aClass.isInstance(element))
    {
      element = element.getNextSibling();
    }
    return aClass.cast(element);
  }

  @Override
  public String toString()
  {
    return name == null ? super.toString() : name;
  }

  @Override
  public ItemPresentation getPresentation()
  {
    return new ItemPresentation()
    {
      public String getPresentableText()
      {
        return getText();
      }

      @Nullable
      public String getLocationString()
      {
        return null;
      }

      @Nullable
      public Icon getIcon(boolean open)
      {
        return XirIcons.SYMBOL;
      }
    };
  }
}
