package com.translationexchange.dropwizard;

import com.google.common.collect.ImmutableList;
import com.sun.xml.internal.ws.util.ServiceFinder;
import com.translationexchange.dropwizard.filters.TmlFilter;
import com.translationexchange.dropwizard.resources.HomeResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import io.dropwizard.views.ViewRenderer;
import io.dropwizard.views.freemarker.FreemarkerViewRenderer;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;
import java.util.Map;

public class TmlApplication extends Application<TmlConfiguration> {

  public static void main(final String[] args) throws Exception {
    new TmlApplication().run(args);
  }

  @Override
  public String getName() {
    return "Dropwizard TML";
  }

  @Override
  public void initialize(final Bootstrap<TmlConfiguration> bootstrap) {
//    bootstrap.addBundle(new ViewBundle());
    FreemarkerViewRenderer renderer = new FreemarkerViewRenderer();
//    Map<String, String> attrs =
//    renderer.configure()
    bootstrap.addBundle(new ViewBundle(ImmutableList.of(renderer)));
    bootstrap.addBundle(new AssetsBundle("/assets", "/", "index.html"));
  }

  @Override
  public void run(final TmlConfiguration configuration,
                  final Environment environment) {

    configuration.initTml();

    final HomeResource resource = new HomeResource();
    environment.jersey().register(resource);

//    environment.servlets().addFilter("tmlFilter", new TmlFilter());
    environment.servlets().addFilter("tmlFilter", TmlFilter.class)
        .addMappingForUrlPatterns(null, false, environment.getApplicationContext().getContextPath() + "*");

//    final FilterRegistration.Dynamic tmlFilter = environment.servlets().addFilter("tmlFilter", TmlFilter.class);
//    tmlFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

  }

}
