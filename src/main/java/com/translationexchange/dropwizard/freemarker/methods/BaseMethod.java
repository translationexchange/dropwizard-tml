package com.translationexchange.dropwizard.freemarker.methods;

import com.translationexchange.core.Session;
import com.translationexchange.dropwizard.filters.TmlFilter;
import freemarker.core.Environment;
import freemarker.ext.beans.BeanModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public abstract class BaseMethod implements TemplateMethodModelEx {

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
