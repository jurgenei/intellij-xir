package main;

import com.intellij.lang.Language;

public class XirLanguage extends Language
{
  public static final XirLanguage INSTANCE = new XirLanguage();

  public XirLanguage()
  {
    super("XIR");
  }
}
