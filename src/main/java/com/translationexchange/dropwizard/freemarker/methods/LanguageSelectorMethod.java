package com.translationexchange.dropwizard.freemarker.methods;

import com.translationexchange.core.Application;
import com.translationexchange.core.Session;
import com.translationexchange.core.Tml;
import com.translationexchange.core.Utils;
import com.translationexchange.core.languages.Language;
import freemarker.template.TemplateModelException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LanguageSelectorMethod extends BaseMethod {

  private boolean isToggle(List params) {
    return true;
  }

  private boolean isPoweredBy(List params) {
    return true;
  }

  private String getElement(List params) {
    return "div";
  }

  private String getType(List params) {
    return "sideflags";
  }

  private String getToggleLabel(List params) {
    return "Enable Inline Translations";
  }

  private String getToggleLabelCancel(List params) {
    return "Disable Inline Translations";
  }

  private String getClassName(List params) {
    return "";
  }

  private String getStyle(List params) {
    return "";
  }

  public Object exec(List params) throws TemplateModelException {
    Session session = getTmlSession();
    StringBuilder builder = new StringBuilder();

    builder.append("<" + getElement(params) + " data-tml-language-selector='" + getType(params) + "' ");

    if (isToggle(params))
      builder.append("data-tml-toggle='true' ");

    if (isPoweredBy(params))
      builder.append("data-tml-powered-by='true' ");

    builder.append("data-tml-toggle_label='" + getToggleLabel(params) + "' ");
    builder.append("data-tml-toggle_label_cancel='" + getToggleLabelCancel(params) + "' ");

    builder.append("data-tml-class='" + getClassName(params) + "' ");
    builder.append("data-tml-style='" + getStyle(params) + "' ");

    builder.append("></" + getElement(params) + ">");

    return builder.toString();
  }

}
