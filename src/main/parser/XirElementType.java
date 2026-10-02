package main.parser;

import com.intellij.psi.tree.IElementType;
import main.file.XirFileType;

public class XirElementType extends IElementType
{
  private final String name;

  public XirElementType(String debugName)
  {
    super(debugName, XirFileType.XIR_LANGUAGE);
    name = debugName;
  }

  public String getName()
  {
    return name;
  }
}
