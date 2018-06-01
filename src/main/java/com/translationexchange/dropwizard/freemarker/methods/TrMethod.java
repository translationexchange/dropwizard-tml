package com.translationexchange.dropwizard.freemarker.methods;

import com.translationexchange.core.Session;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateModelException;

import java.util.List;

public class TrMethod extends BaseMethod {

  public Object exec(List params) throws TemplateModelException {
    Session session = getTmlSession();
    String original = params.get(0).toString();
    String translation = translate(session, original);
    System.out.println(original + " = " + translation);

    return new SimpleScalar(translation);
  }

}
