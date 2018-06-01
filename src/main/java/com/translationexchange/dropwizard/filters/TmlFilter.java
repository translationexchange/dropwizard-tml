package com.translationexchange.dropwizard.filters;

import com.translationexchange.core.Session;
import com.translationexchange.core.Tml;
import com.translationexchange.core.Utils;
import com.translationexchange.j2ee.servlets.LocalizedServlet;
import com.translationexchange.j2ee.utils.SecurityUtils;
import com.translationexchange.j2ee.utils.SessionUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public final class TmlFilter implements Filter {
  private FilterConfig filterConfig = null;
  public static final String TML_SESSION_KEY = "tml";

  public void init(FilterConfig filterConfig)
      throws ServletException {
    this.filterConfig = filterConfig;

    System.out.println("INIT FILTER");
  }

  public void destroy() {
    this.filterConfig = null;
  }

  /**
   * Returns current locale - must be overwritten
   *
   * @return
   */
  protected String getCurrentLocale(HttpServletRequest req) {
    return null;
  }

  /**
   * Returns current source - must be overwritten
   *
   * @return
   */
  protected String getCurrentSource(HttpServletRequest req) {
    return null;
  }

  protected Map<String, Object> prepareSessionParams(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, Object> params = SecurityUtils.decode(SessionUtils.getSessionCookie(SessionUtils.getCookieName(), req));
    if (params != null) Tml.getLogger().debug(params);

    // Locale can be forced by the user
    String locale = getCurrentLocale(req);
    if (locale == null) {
      // Or passed as a parameter
      locale = req.getParameter("locale");
      if (locale != null) {
        params.put("locale", locale);
        SessionUtils.setSessionCookie(SessionUtils.getCookieName(), SecurityUtils.encode(params), resp);
        Tml.getLogger().debug("Param Locale: " + locale);
      } else if (params.get("locale") != null) {
        // Or loaded from the cookie
        locale = (String) params.get("locale");
        Tml.getLogger().debug("Cookie Locale: " + locale);
      } else {
        // Or taken from the Accepted Locale header
        List<String> locales = new ArrayList<String>();
        @SuppressWarnings("unchecked")
        Enumeration<Locale> e = req.getLocales();
        while (e.hasMoreElements()) {
          locales.add(e.nextElement().getLanguage());
        }
        locale = Utils.join(locales, ",");
        Tml.getLogger().debug("Header Locale: " + locale);
      }
    } else {
      Tml.getLogger().debug("User Locale: " + locale);
    }
    params.put("locale", locale);
    System.out.println("Current locale: " + locale);

    String source = getCurrentSource(req);
    if (source == null) {
      URL url = new URL(req.getRequestURL().toString());
      source = url.getPath();
      Tml.getLogger().debug("Url Source: " + source);
    } else {
      Tml.getLogger().debug("User Source: " + source);
    }

    if (source.equals("/")) {
      source = "index";
    }

    params.put("source", source);

    return params;
  }

  public void doFilter(ServletRequest request,
                       ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    System.out.println("IN FILTER");

    if (filterConfig == null)
      return;

    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;

    Tml.getLogger().debug("Requesting " + httpRequest.getRequestURL().toString());

    Session tmlSession = null;
    Long t0 = (new Date()).getTime();

    try {
      tmlSession = new Session(prepareSessionParams(httpRequest, httpResponse));
      request.setAttribute(TML_SESSION_KEY, tmlSession);
      chain.doFilter(request, response);

      tmlSession.getApplication().submitMissingTranslationKeys();

      Long t1 = (new Date()).getTime();
      Tml.getLogger().debug("************ Request took: " + (t1 - t0) + " mls");
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
  }
}
