package main.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.fileTypes.ExtensionFileNameMatcher;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Iconable;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiReference;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.IncorrectOperationException;
import main.file.XirFileType;
import main.psi.util.XirPsiUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import main.XirIcons;
import main.parser.AST;
import main.psi.impl.symbols.CompleteSymbol;
import main.psi.util.XirPsiElementFactory;

import javax.swing.Icon;


public class XirSymbol extends XirPsiElementBase  implements PsiReference
{
  public XirSymbol(ASTNode node)
  {
    super(node, "XirSymbol");
  }


  @Override
  public PsiReference getReference()
  {
    return this;
  }

  @Override
  public String toString()
  {
    return "XirSymbol: " + getReferenceName();
  }

  @NotNull
  public PsiElement getElement()
  {
    return this;
  }

  @NotNull
  public TextRange getRangeInElement()
  {
    return new TextRange(0, getTextLength());
  }

  @Nullable
  public String getReferenceName()
  {
    return getText();
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
      public Icon getIcon(boolean open)
      {
        return XirSymbol.this.getIcon(Iconable.ICON_FLAG_VISIBILITY | Iconable.ICON_FLAG_READ_STATUS);
      }

      @Nullable
      public String getLocationString()
      {
        return null;
      }
    };
  }

  @NotNull
  public Object[] getVariants()
  {
    return CompleteSymbol.getVariants(this);
  }

  public String getCanonicalText()
  {
    return getText();
  }

  public boolean isSoft()
  {
    return false;
  }

  public boolean isReferenceTo(@NotNull PsiElement element)
  {
    if (element instanceof XirSymbolDefine)
    {
      XirSymbolDefine symbol = (XirSymbolDefine)element;
      String referenceName = getReferenceName();
      if ((referenceName != null) && referenceName.equals(symbol.getName()))
      {
        return resolve() == symbol;
      }
    }
    return false;
  }

  public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException
  {
    ASTNode thisNode = getNode();
    ASTNode newNode = XirPsiElementFactory.getInstance(getProject()).createSymbolNodeFromText(newElementName);
    ASTNode oldNode = thisNode.getFirstChildNode();
    thisNode.replaceChild(oldNode, newNode);
    return this;
  }

  public PsiElement bindToElement(@NotNull PsiElement element) throws IncorrectOperationException
  {
    //todo implement me!
    return this;
  }

  public PsiElement resolve()
  {
    PsiElement brother;
    brother = this.getPrevSibling();
    while (brother != null)
    {
      if (brother instanceof XirFormDefineBase) {
        PsiElement defPsi = ((XirFormDefineBase)brother).getDeclareName();
        if (defPsi != null && defPsi.textMatches(this)) {
          return defPsi;
        }
      } else if (brother instanceof XirFormImport) {
        PsiElement find = findWithFormImport((XirFormImport)brother, this);
        if (find != null) {
          return find;
        }
      }
      brother = brother.getPrevSibling();
    }

    PsiElement parent;
    PsiElement cur_ele = this;
    parent = this.getParent();
    while (parent != null) {
      if (parent instanceof PsiFile) {
        return null;
      }
      if (!(cur_ele instanceof XirInFormParamListLetInner)) {
        if (parent instanceof XirFormLetBase) {
          PsiElement find = findInLetForm((XirFormLetBase) parent, this);
          if (find != null) {
            return find;
          }
        } else if (parent instanceof XirFormDefine) {
          PsiElement find = findInDefineForm((XirFormDefine)parent, this);
          if (find != null) {
            return find;
          }
        } else if (parent instanceof XirFormDo) {
          PsiElement find = findInDoForm((XirFormDo)parent, this);
          if (find != null) {
            return find;
          }
        } else if (parent instanceof XirFormProcedure) {
          PsiElement find = findInProcedure((XirFormProcedure)parent, this);
          if (find != null) {
            return find;
          }
        } else if (parent instanceof XirFormImport) {
          return getFilesByName(getProject(), this.getText(), GlobalSearchScope.allScope(getProject()));
        } else {
          if (parent instanceof XirFormDefineBase) {
            PsiElement dec = ((XirFormDefineBase)parent).getDeclareName();
            if (dec != null && dec.textMatches(this)) {
              return dec;
            }
          }
          if (parent instanceof XirFormLocalBase) {
            PsiElement[] defs = ((XirFormLocalBase)parent).getLocalDefinitions();
//        System.out.println("my text: " + this.getText());
            for (PsiElement def : defs) {
//          System.out.println("localDefinition: " + def.getText());
              if (def.textMatches(this)) {
                return def;
              }
            }
          }
        }
      }

      brother = parent.getPrevSibling();
      while (brother != null)
      {
        if (brother instanceof XirFormDefineBase) {
          PsiElement defPsi = ((XirFormDefineBase)brother).getDeclareName();
          if (defPsi != null && defPsi.textMatches(this)) {
            return defPsi;
          }
        } else if (brother instanceof XirFormImport) {
          PsiElement find = findWithFormImport((XirFormImport)brother, this);
          if (find != null) {
            return find;
          }
        }
        brother = brother.getPrevSibling();
      }

      cur_ele = parent;
      if (cur_ele instanceof XirInFormParamListLetInner) {
        parent = cur_ele.getParent();
        if (parent == null) {
          return null;
        }
      }
      parent = parent.getParent();
    }
    return null;
  }

  private PsiElement findInDefineForm(XirFormDefine form, PsiElement toFind) {
    ASTNode node = form.getNode();
    ASTNode defNode = XirPsiUtil.getNonLeafChildAt(node, 1);
    if (defNode == null) {
      return null;
    }
    if (defNode.getElementType() == AST.AST_BASIC_ELE_SYMBOL_DEFINE) {
      if (toFind.textMatches(defNode.getPsi())) {
        return defNode.getPsi();
      }
    }
    defNode = XirPsiUtil.getNonLeafChildAt(defNode, 0);
    if (defNode == null) {
      return null;
    }
    if (defNode.getElementType() == AST.AST_BASIC_ELE_SYMBOL_DEFINE) {
      if (toFind.textMatches(defNode.getPsi())) {
        return defNode.getPsi();
      }
      ASTNode localDefinition;
      localDefinition = defNode.getTreeNext();
      while (localDefinition != null) {
        if (localDefinition.getElementType() == AST.AST_BASIC_ELE_SYMBOL_DEFINE) {
          if (toFind.textMatches(localDefinition.getPsi())) {
            return localDefinition.getPsi();
          }
        }
        localDefinition = localDefinition.getTreeNext();
      }
    }
    return null;
  }

  private PsiElement findInDoForm(XirFormDo form, PsiElement toFind)
  {
    ASTNode node = form.getNode();
    ASTNode defNode = XirPsiUtil.getNonLeafChildAt(node, 1);
    if (defNode == null) {
      return null;
    }
    IElementType defNodeType = defNode.getElementType();
    if (defNodeType != AST.AST_IN_FORM_PARAM_LIST) {
      return null;
    }
    defNode = XirPsiUtil.getNonLeafChildAt(defNode, 0);
    if (defNode == null) {
      return null;
    }
    while (defNode != null) {
      ASTNode localDefinition = XirPsiUtil.getNonLeafChildAt(defNode, 0);
      if (localDefinition != null && localDefinition.getElementType() == AST.AST_BASIC_ELE_SYMBOL_DEFINE) {
        if (toFind.textMatches(localDefinition.getPsi())) {
          return localDefinition.getPsi();
        }
      }
      defNode = XirPsiUtil.getNodeNextNonLeafSibling(defNode);
    }
    return null;
  }

  private PsiElement findInLetForm(XirFormLetBase form, PsiElement toFind)
  {
    ASTNode node = form.getNode();
    ASTNode defNode = XirPsiUtil.getNonLeafChildAt(node, 1);
    if (defNode == null) {
      return null;
    }
    if (defNode.getElementType() == AST.AST_BASIC_ELE_SYMBOL_DEFINE) {
      if (toFind.textMatches(defNode.getPsi())) {
        return defNode.getPsi();
      }
      defNode = XirPsiUtil.getNodeNextNonLeafSibling(defNode);
      if (defNode == null) {
        return null;
      }
    }
    IElementType defNodeType = defNode.getElementType();
    if (defNodeType != AST.AST_IN_FORM_PARAM_LIST) {
      return null;
    }
    defNode = XirPsiUtil.getNonLeafChildAt(defNode, 0);
    if (defNode == null) {
      return null;
    }
    while (defNode != null) {
      ASTNode localDefinition = XirPsiUtil.getNonLeafChildAt(defNode, 0);
      if (localDefinition != null && localDefinition.getElementType() == AST.AST_BASIC_ELE_SYMBOL_DEFINE) {
        if (toFind.textMatches(localDefinition.getPsi())) {
          return localDefinition.getPsi();
        }
      }
      defNode = XirPsiUtil.getNodeNextNonLeafSibling(defNode);
    }
    return null;
  }

  private PsiElement findInProcedure(XirFormProcedure form, PsiElement toFind) {
    ASTNode node = form.getNode();
    ASTNode defNode = XirPsiUtil.getNonLeafChildAt(node, 1);
    if (defNode == null) {
      return null;
    }
    IElementType defNodeType = defNode.getElementType();
    if (defNodeType == AST.AST_BASIC_ELE_SYMBOL_DEFINE) {
      if (toFind.textMatches(defNode.getPsi())) {
        return defNode.getPsi();
      }
    }
    if (defNodeType != AST.AST_IN_FORM_PARAM_LIST) {
      return null;
    }
    defNode = XirPsiUtil.getNonLeafChildAt(defNode, 0);
    if (defNode == null) {
      return null;
    }
    while (defNode != null) {
      if (defNode.getElementType() == AST.AST_BASIC_ELE_SYMBOL_DEFINE) {
        if (toFind.textMatches(defNode.getPsi())) {
          return defNode.getPsi();
        }
      }
      defNode = XirPsiUtil.getNodeNextNonLeafSibling(defNode);
    }
    return null;
  }

  private PsiElement findWithFormImport(XirFormImport form, PsiElement toFind) {
    // imported library name
    PsiElement child = XirPsiUtil.getNormalChildAt(form, 1);
    while (child != null) {
      // imported library name
      PsiElement child_child =  XirPsiUtil.getPsiLastNonLeafChild(child);
      if (child_child != null) {
        // imported library file
        PsiFile file = getFilesByName(getProject(), child_child.getText(), GlobalSearchScope.allScope(getProject()));
        if (file != null) {
          // library form in library file
          PsiElement lib_form = XirPsiUtil.getNormalChildAt(file, 0);
          if (lib_form instanceof XirFormLibrary) {
            PsiElement export_form = XirPsiUtil.getNormalChildAt(lib_form, 2);
            if (export_form instanceof XirFormExport) {
              PsiElement export_child = XirPsiUtil.getNormalChildAt(export_form, 1);
              while (export_child != null) {
                if (export_child.textMatches(toFind)) {
                  return export_child;
                }
                export_child = XirPsiUtil.getPsiNextNonLeafSibling(export_child);
              }
            }
          }
        }
      }
      child = XirPsiUtil.getPsiNextNonLeafSibling(child);
    }
    return null;
  }

  private PsiFile getFilesByName(@NotNull Project project, @NotNull String name, @NotNull GlobalSearchScope scope)
  {
    String[] exts =  FileTypeManager.getInstance().getAssociations(XirFileType.XIR_FILE_TYPE)
        .stream()
        .filter(matcher -> matcher instanceof ExtensionFileNameMatcher)
        .map(matcher -> ((ExtensionFileNameMatcher) matcher).getExtension())
        .toArray(String[]::new);

    for (String ext : exts) {
      PsiFile[] files = FilenameIndex.getFilesByName(project, name + "." + ext, scope);
      if (files.length > 0) {
        return files[0];
      }
    }
    return null;
  }

  private boolean isItDeclaration(PsiElement element)
  {
    if (null == element)
    {
      return false;
    }
    return AST.DEFINE_FORMS.contains(element.getNode().getElementType());
  }
}
