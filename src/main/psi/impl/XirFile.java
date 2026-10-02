package main.psi.impl;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.source.PsiFileImpl;
import com.intellij.psi.impl.source.PsiFileWithStubSupport;
import org.jetbrains.annotations.NotNull;
import main.file.XirFileType;
import main.psi.api.XirPsiElement;
import main.psi.impl.list.XirList;
import main.psi.impl.symbols.XirIdentifier;
import main.psi.util.XirPsiUtil;
import main.psi.util.XirTextUtil;


public class XirFile extends PsiFileBase implements PsiFile, PsiFileWithStubSupport, XirPsiElement
{
  private PsiElement context = null;

  @Override
  public String toString()
  {
    return "XirFile";
  }

  public XirFile(FileViewProvider viewProvider)
  {
    super(viewProvider, XirFileType.XIR_LANGUAGE);
  }

  @Override
  public PsiElement getContext()
  {
    if (context != null)
    {
      return context;
    }
    return super.getContext();
  }

  protected PsiFileImpl clone()
  {
    XirFile clone = (XirFile) super.clone();
    clone.context = context;
    return clone;
  }

  @NotNull
  public FileType getFileType()
  {
    return XirFileType.XIR_FILE_TYPE;
  }

  @NotNull
  public String getPackageName()
  {
    String ns = getNamespace();
    if (ns == null)
    {
      return "";
    }
    else
    {
      return XirTextUtil.getSymbolPrefix(ns);
    }
  }

  public String getNamespace()
  {
    XirList ns = getNamespaceElement();
    if (ns == null)
    {
      return null;
    }
    XirIdentifier first = ns.findFirstChildByClass(XirIdentifier.class);
    if (first == null)
    {
      return null;
    }
    XirIdentifier snd = XirPsiUtil.findNextSiblingByClass(first, XirIdentifier.class);
    if (snd == null)
    {
      return null;
    }

    return snd.getNameString();
  }

  public XirList getNamespaceElement()
  {
    // TODO CMF
    return null; //XirPsiUtil.findFormByNameSet(this, XirParser.NS_TOKENS);
  }
}
