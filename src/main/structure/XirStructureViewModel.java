package main.structure;

import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.StructureViewModelBase;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.Sorter;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import main.psi.impl.XirFormDefineBase;
import main.psi.impl.XirFormExport;
import main.psi.impl.XirFormLibrary;
import main.psi.impl.XirSymbolDefine;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class XirStructureViewModel extends StructureViewModelBase implements
        StructureViewModel.ElementInfoProvider
{
  public XirStructureViewModel(@Nullable Editor editor, PsiFile file)
  {
    super(file, editor, new XirStructureViewElement(file));
  }

  @NotNull
  public Sorter[] getSorters()
  {
    return new Sorter[] { Sorter.ALPHA_SORTER };
  }

  @NotNull
  protected Class<?>[] getSuitableClasses()
  {
    return new Class[] { XirFormDefineBase.class,
            XirFormLibrary.class, XirFormExport.class, XirSymbolDefine.class };
  }

  @Override
  public boolean isAlwaysShowsPlus(StructureViewTreeElement structureViewTreeElement) {
    return false;
  }

  @Override
  public boolean isAlwaysLeaf(StructureViewTreeElement structureViewTreeElement) {
    return false;
  }
}
