package main.psi.stubs.index;

import com.intellij.psi.stubs.StringStubIndexExtension;
import com.intellij.psi.stubs.StubIndexKey;
import main.psi.impl.XirFile;


public class XirClassNameIndex extends StringStubIndexExtension<XirFile>
{
  public static final StubIndexKey<String, XirFile> KEY = StubIndexKey.createIndexKey("scm.class");

  public StubIndexKey<String, XirFile> getKey()
  {
    return KEY;
  }
}
