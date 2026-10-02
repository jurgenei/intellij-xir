package main.psi.stubs.index;

import com.intellij.openapi.project.Project;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.stubs.IntStubIndexExtension;
import com.intellij.psi.stubs.StubIndexKey;
import main.psi.impl.XirFile;
import main.psi.impl.search.XirSourceFilterScope;

import java.util.Collection;


public class XirFullScriptNameIndex extends IntStubIndexExtension<XirFile>
{
  public static final StubIndexKey<Integer, XirFile> KEY = StubIndexKey.createIndexKey("scm.script.fqn");

  public StubIndexKey<Integer, XirFile> getKey()
  {
    return KEY;
  }

  public Collection<XirFile> get(Integer integer, Project project, GlobalSearchScope scope)
  {
    return super.get(integer, project, new XirSourceFilterScope(scope, project));
  }
}