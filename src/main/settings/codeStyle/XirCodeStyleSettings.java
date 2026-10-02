package main.settings.codeStyle;

import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;


public class XirCodeStyleSettings extends CustomCodeStyleSettings
{
  protected XirCodeStyleSettings(CodeStyleSettings container)
  {
    super("XirCodeStyleSettings", container);
  }
}
