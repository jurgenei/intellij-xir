package main.settings.codeStyle;

import com.intellij.application.options.CodeStyleAbstractConfigurable;
import com.intellij.application.options.CodeStyleAbstractPanel;
import com.intellij.psi.codeStyle.CodeStyleSettings;


public class XirFormatConfigurable extends CodeStyleAbstractConfigurable
{
  public XirFormatConfigurable(CodeStyleSettings settings, CodeStyleSettings cloneSettings)
  {
    super(settings, cloneSettings, "Xir");
  }

  protected CodeStyleAbstractPanel createPanel(CodeStyleSettings settings)
  {
    return new XirCodeStylePanel(getCurrentSettings(), settings);
  }

  public String getHelpTopic()
  {
    return "Xir help topic. Nothing";
  }
}
