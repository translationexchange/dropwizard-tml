package com.translationexchange.dropwizard.freemarker.directives;

import com.translationexchange.core.Session;
import com.translationexchange.dropwizard.filters.TmlFilter;
import freemarker.core.Environment;
import freemarker.ext.beans.BeanModel;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateModelException;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseDirective implements TemplateDirectiveModel {

  protected HttpServletRequest getHttpServletRequest() throws TemplateModelException {
    Environment env = Environment.getCurrentEnvironment();
    BeanModel model = (BeanModel) env.getGlobalVariable("request");
    return (HttpServletRequest) model.getWrappedObject();
  }

  protected Session getTmlSession() throws TemplateModelException {
    return (Session) getHttpServletRequest().getAttribute(TmlFilter.TML_SESSION_KEY);
  }

  protected String translate(Session tmlSession, String label) {
    return tmlSession.translate(label);
  }

}
