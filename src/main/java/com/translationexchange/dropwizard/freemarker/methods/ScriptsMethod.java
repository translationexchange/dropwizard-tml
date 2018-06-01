package com.translationexchange.dropwizard.freemarker.methods;

import com.translationexchange.core.Application;
import com.translationexchange.core.Session;
import com.translationexchange.core.Tml;
import com.translationexchange.core.Utils;
import com.translationexchange.core.languages.Language;
import freemarker.template.TemplateModelException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScriptsMethod extends BaseMethod {

  public Object exec(List params) throws TemplateModelException {
    Session session = getTmlSession();

    Application application = session.getApplication();
    Language language = session.getCurrentLanguage();
    String source = session.getCurrentSource();

    List<Map<String, Object>> languages = new ArrayList<Map<String, Object>>();

    Map<String, Object> config = Utils.map(
        "locale", language.getLocale(),
        "source", source,
        "css", application.getCss(),
        "sdk", "tml-java v" + Tml.VERSION,
        "languages", languages
    );

    for (Language lang : application.getLanguages()) {
      Map<String, Object> info = new HashMap<String, Object>();
      info.put("locale", lang.getLocale());
      info.put("english_name", lang.getEnglishName());
      info.put("native_name", lang.getNativeName());
      info.put("flag_url", lang.getFlagUrl());
      languages.add(info);
    }

    StringBuilder builder = new StringBuilder();
    builder.append("<script>");
    builder.append("(function() {");
    builder.append("var script = window.document.createElement('script');");
    builder.append("script.setAttribute('id', 'tml-agent');");
    builder.append("script.setAttribute('type', 'application/javascript');");
    builder.append("script.setAttribute('src', '").append(Tml.getConfig().getAgent().get("host")).append("');");
    builder.append("script.setAttribute('charset', 'UTF-8');");
    builder.append("script.onload = function() {");
    builder.append("   Trex.init('").append(application.getKey()).append("', ").append(Utils.buildJSON(config)).append(");");
    builder.append("};");
    builder.append("window.document.getElementsByTagName('head')[0].appendChild(script);");
    builder.append("})();");
    builder.append("</script>");

    return builder.toString();
  }

}
