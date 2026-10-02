package main.psi.util;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiErrorElement;
import com.intellij.psi.PsiFileFactory;
import org.jetbrains.annotations.NotNull;
import main.file.XirFileType;
import main.psi.impl.XirFile;


public class XirPsiElementFactoryImpl extends XirPsiElementFactory
{
  private final Project project;

  public XirPsiElementFactoryImpl(Project project)
  {
    this.project = project;
  }

  private static final String DUMMY = "DUMMY.";


  public ASTNode createSymbolNodeFromText(@NotNull String newName)
  {
    String text = "(" + newName + ")";
    XirFile dummyFile = createXirFileFromText(text);
    return dummyFile.getFirstChild().getFirstChild().getNextSibling().getNode();
  }

  @Override
  public XirFile createXirFileFromText(String text)
  {
    return (XirFile) PsiFileFactory.getInstance(getProject())
      .createFileFromText(DUMMY + XirFileType.XIR_FILE_TYPE.getDefaultExtension(),
              XirFileType.XIR_FILE_TYPE, text);
  }

  @Override
  public boolean hasSyntacticalErrors(@NotNull PsiElement element)
  {
    if ((element instanceof PsiErrorElement))
    {
      return true;
    }
    for (PsiElement child : element.getChildren())
    {
      if (hasSyntacticalErrors(child))
      {
        return true;
      }
    }
    return false;
  }

  public Project getProject()
  {
    return project;
  }
}
