package main.psi.impl.symbols;

import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.util.Iconable;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import main.XirIcons;
import main.lexer.TokenSets;
import main.lexer.XirTokens;
import main.psi.impl.XirPsiElementBase;
import main.psi.util.XirPsiElementFactory;
import main.psi.util.XirPsiUtil;

import javax.swing.Icon;

public class XirIdentifier extends XirPsiElementBase implements PsiReference, PsiNamedElement
{
  public XirIdentifier(ASTNode node)
  {
    super(node, "Identifier");
  }

  @Override
  public PsiReference getReference()
  {
    return this;
  }

  @Override
  public String toString()
  {
    return "XirIdentifier: " + getReferenceName();
  }

  public PsiElement getElement()
  {
    return this;
  }

  public TextRange getRangeInElement()
  {
    PsiElement refNameElement = getReferenceNameElement();
    if (refNameElement != null)
    {
      int offsetInParent = refNameElement.getStartOffsetInParent();
      return new TextRange(offsetInParent, offsetInParent + refNameElement.getTextLength());
    }
    return new TextRange(0, getTextLength());
  }

  @Nullable
  public PsiElement getReferenceNameElement()
  {
    ASTNode lastChild = getNode().getLastChildNode();
    if (lastChild == null)
    {
      return null;
    }
    for (IElementType elementType : TokenSets.REFERENCE_NAMES.getTypes())
    {
      if (lastChild.getElementType() == elementType)
      {
        return lastChild.getPsi();
      }
    }

    return null;
  }

  @Nullable
  public String getReferenceName()
  {
    PsiElement nameElement = getReferenceNameElement();
    if (nameElement != null)
    {
      ASTNode node = nameElement.getNode();
      if ((node != null) && (node.getElementType() == XirTokens.IDENTIFIER))
      {
        return nameElement.getText();
      }
    }
    return null;
  }

  public PsiElement setName(@NotNull @NonNls String newName) throws IncorrectOperationException
  {
    ASTNode newNode = XirPsiElementFactory.getInstance(getProject()).createSymbolNodeFromText(newName);
    ASTNode parentNode = getParent().getNode();
    if (parentNode != null)
    {
      parentNode.replaceChild(getNode(), newNode);
    }
    return newNode.getPsi();
  }

  @Override
  public Icon getIcon(int flags)
  {
    return XirIcons.SYMBOL;
  }

  @Override
  public ItemPresentation getPresentation()
  {
    return new ItemPresentation()
    {
      public String getPresentableText()
      {
        String name = getName();
        return name == null ? "<undefined>" : name;
      }

      @Nullable
      public String getLocationString()
      {
        String name = getContainingFile().getName();
        //todo show namespace
        return "(in " + name + ")";
      }

      @Nullable
      public Icon getIcon(boolean open)
      {
        return XirIdentifier.this.getIcon(Iconable.ICON_FLAG_VISIBILITY | Iconable.ICON_FLAG_READ_STATUS);
      }
    };
  }

  @Override
  public String getName()
  {
    return getNameString();
  }

  private boolean isItDeclaration(PsiElement element)
  {
    if (null == element)
    {
      return false;
    }

    PsiElement operator;
    operator = XirPsiUtil.getNormalChildAt(element, 0);
    if (null == operator)
    {
      return false;
    }
    else
    {
      return operator.textMatches("define");
    }
  }

  public PsiElement resolve()
  {
    PsiElement bigBrother = this;
    PsiElement declaration;

    while (true)
    {
      bigBrother = XirPsiUtil.getBigBrother(bigBrother);
      if (null == bigBrother)
      {
        return null;
      }
      if (isItDeclaration(bigBrother))
      {
        declaration = XirPsiUtil.getNormalChildAt(bigBrother, 1);
        if (null != declaration)
        {
          if (declaration.textMatches(this))
          {
            return declaration;
          }
        }
      }
    }
  }

  public String getCanonicalText()
  {
    return null;
  }

  public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException
  {
    PsiElement nameElement = getReferenceNameElement();
    if (nameElement != null)
    {
      ASTNode node = nameElement.getNode();
      ASTNode newNameNode = XirPsiElementFactory.getInstance(getProject()).createSymbolNodeFromText(newElementName);
      assert newNameNode != null && node != null;
      node.getTreeParent().replaceChild(node, newNameNode);
    }
    return this;
  }

  public PsiElement bindToElement(@NotNull PsiElement element) throws IncorrectOperationException
  {
    //todo implement me!
    return this;
  }

  public boolean isReferenceTo(PsiElement element)
  {
    if (element instanceof XirIdentifier)
    {
      XirIdentifier identifier = (XirIdentifier) element;
      String referenceName = getReferenceName();
      if ((referenceName != null) && referenceName.equals(identifier.getReferenceName()))
      {
        return resolve() == identifier;
      }
    }
    return false;
  }

  @NotNull
  public Object[] getVariants()
  {
    return CompleteSymbol.getVariants(this);
  }

  public boolean isSoft()
  {
    return false;
  }

  @NotNull
  public String getNameString()
  {
    return getText();
  }
}
