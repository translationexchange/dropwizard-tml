package com.translationexchange.dropwizard.views;

import com.translationexchange.core.Utils;
import io.dropwizard.views.View;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.List;

public class HomeView extends View {
  private final List<Object> restaurants;
  private HttpServletRequest request;

  public HomeView(HttpServletRequest request, List<Object> restaurants) {
    super("Home.ftl", Charset.forName("UTF-8"));

    this.request = request;
    this.restaurants = restaurants;
  }
  public HttpServletRequest getRequest() {
    return this.request;
  }

  public List<Object> getRestaurants() {
    return this.restaurants;
  }
}