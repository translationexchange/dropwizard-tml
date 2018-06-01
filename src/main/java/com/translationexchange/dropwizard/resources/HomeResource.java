package com.translationexchange.dropwizard.resources;

import com.translationexchange.core.Utils;
import com.translationexchange.dropwizard.views.HomeView;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/home")
@Produces(MediaType.TEXT_HTML)

public class HomeResource {

  @GET
  public HomeView getHome(@Context HttpServletRequest httpRequest) {
    List<Object> restaurants = Utils.buildList(
        Utils.map(
            "name", "Ricky's Fish Tacos",
            "rating", 4,
            "review_count", 14,
            "last_comment", "Luckily, the perfect hot day food is a fish taco."
        ),
        Utils.map(
            "name", "Genwa Korean Bbq",
            "rating", 3,
            "review_count", 567,
            "last_comment", "I love love love the fact that you get 25 side dishes."
        ),
        Utils.map(
            "name", "Kang Hodong Baekjeong",
            "rating", 2,
            "review_count", 1,
            "last_comment", "Thick slices of juicy pastrami on rye hits the spot every time."
        ),
        Utils.map(
            "name", "Guisados",
            "rating", 1,
            "review_count", 12,
            "last_comment", "I can't wait to introduce more people to these orgasmic tacos."
        )
    );

    return new HomeView(httpRequest, restaurants);
  }

}