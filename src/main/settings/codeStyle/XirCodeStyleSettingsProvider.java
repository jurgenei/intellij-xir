package main.settings.codeStyle;

import com.intellij.lang.Language;
import com.intellij.openapi.options.Configurable;
import com.intellij.psi.codeStyle.CodeStyleSettingsProvider;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;
import org.jetbrains.annotations.NotNull;
import main.XirLanguage;


public class XirCodeStyleSettingsProvider extends CodeStyleSettingsProvider
{
  @Override
  @NotNull
  public Configurable createSettingsPage(CodeStyleSettings settings, CodeStyleSettings originalSettings)
  {
    return new XirFormatConfigurable(settings, originalSettings);
  }

  @Override
  public CustomCodeStyleSettings createCustomSettings(CodeStyleSettings settings)
  {
    return new XirCodeStyleSettings(settings);
  }

  @Override
  public String getConfigurableDisplayName() {
    return "Xir";
  }

  @NotNull
  @Override
  public Language getLanguage() {
    return XirLanguage.INSTANCE;
  }
}
