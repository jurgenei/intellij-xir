package main.file;

import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import main.XirIcons;
import main.XirLanguage;

import javax.swing.Icon;

public class XirFileType extends LanguageFileType
{
  public static final XirFileType XIR_FILE_TYPE = new XirFileType();
  public static final Language XIR_LANGUAGE = XIR_FILE_TYPE.getLanguage();

  public XirFileType()
  {
    super(XirLanguage.INSTANCE);
  }

  @NotNull
  public String getName()
  {
    return "XIR";
  }

  @NotNull
  public String getDescription()
  {
    return "XIR file";
  }

  @NotNull
  public String getDefaultExtension()
  {
    return "xir";
  }

  public Icon getIcon()
  {
    return XirIcons.XIR_ICON;
  }
}
