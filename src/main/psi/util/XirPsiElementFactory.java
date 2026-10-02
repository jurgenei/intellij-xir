package main.psi.util;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import main.psi.impl.XirFile;


public abstract class XirPsiElementFactory
{
  public static XirPsiElementFactory getInstance(Project project)
  {
    return ServiceManager.getService(project, XirPsiElementFactory.class);
  }

  public abstract ASTNode createSymbolNodeFromText(@NotNull String newName);

  public abstract boolean hasSyntacticalErrors(@NotNull PsiElement element);

  public abstract XirFile createXirFileFromText(String text);
}
