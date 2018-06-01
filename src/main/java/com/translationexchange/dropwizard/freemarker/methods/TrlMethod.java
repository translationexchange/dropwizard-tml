package com.translationexchange.dropwizard.freemarker.methods;

import com.translationexchange.core.Session;
import freemarker.core.Environment;
import freemarker.ext.beans.BeanModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class TrlMethod extends TrMethod {

  public Object exec(List params) throws TemplateModelException {
    Session session = getTmlSession();
    return translate(session, (String) params.get(0));
  }

}
