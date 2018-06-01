<#-- @ftlvariable name="" type="com.translationexchange.dropwizard.views.HomeView" -->
<#assign tr="com.translationexchange.dropwizard.freemarker.methods.TrMethod"?new()>
<#assign scripts="com.translationexchange.dropwizard.freemarker.methods.ScriptsMethod"?new()>
<#assign languageSelector="com.translationexchange.dropwizard.freemarker.methods.LanguageSelectorMethod"?new()>

<#assign tr1="com.translationexchange.dropwizard.freemarker.directives.TrDirective"?new()>

<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8"/>
  <meta http-equiv="X-UA-Compatible" content="chrome=1">
  <meta name="google" content="notranslate">
  <title>Welp</title>
  <link href="/css/home_ltr.css" rel="stylesheet" type="text/css">
  ${scripts()}
</head>

<body>

${languageSelector()}

<div class="navbar navbar-default navbar-static-top">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">
        <strong>Welp</strong>
      </a>
    </div>
    <div class="navbar-collapse collapse">
      <ul class="nav navbar-nav">
        <li><a href="#">
        ${tr("About Us")}
        </a></li>
        <li><a href="#">
        ${tr("Search")}
        </a></li>
        <li><a href="#">
        ${tr("Write a Review")}
        </a></li>
        <li><a href="#">
        ${tr("Find Friends")}
        </a></li>
        <li><a href="#">
        ${tr("Help")}
        </a></li>
      </ul>

      <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            More <b class='caret'></b>
          </a>
          <ul class="dropdown-menu">
            <li class="divider"></li>
            <li><a href="#">
            ${tr("Log in")}
            </a></li>
            <li class="divider"></li>
            <li><a href="#">
            ${tr("Sign Up")}
            </a></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</div>

<div class="container">

  <div class="content">

    <div class="row">
      <div class="col-sm-12">
        <div class="pull-right"><br><a href="#">
          ${tr("More Cities")}
        </a></div>
        <h4>
          Welp Los Angeles
          <br>
          <@tr1>
            This is cool
          </@tr1>
        </h4>
        <hr>
      </div>
    </div>

    <div class="row">

      <div class="col-sm-7">

        <div class="well">
          <h3 class="text-primary">
            ${ tr("The best way to find local businesses") }
          </h3>
          <p class="text-muted">
            Search for everything from the city's tastiest burger to the most renowned cardiologist.
            What will you uncover in your neighborhood?
          </p>
          <p>
            <a class="btn btn-primary" href="#">
              Create your free account
            </a>
          </p>
        </div>

        <h3 class="text-primary">
          Best of Welp: Los Angeles
        </h3>
        <hr>
        <small class="pull-right"><a href="#">
          See More
        </a></small>
        <h5 class="text-primary">
          Restaurants
        </h5>

        <#list restaurants as restaurant>

          <div class="media">
            <a class="pull-left" href="#">
              <span class="media-object media-img"></span>
            </a>
            <div class="media-body">
              <strong>${restaurant?index + 1}. <a href="">${restaurant.name}</a></strong>
              <div class="text-muted">
                <img src="/images/${restaurant.rating}-stars.png" title="${restaurant.rating} out of stars">
                ${restaurant.review_count} Reviews
              </div>
              <div class="media">
                <a class="pull-left" href="#">
                  <span class="media-object media-img-sm"></span>
                </a>
                <div class="media-body">
                  <p>
                    ${restaurant.last_comment}
                  </p>
                </div>
              </div>
            </div>
          </div>

        </#list>

      </div>

      <div class="col-sm-5">

        <form>
          <div class="form-group">
            <label>
              <small>
                Find
              </small>
            </label>
            <input type="text" class="form-control" placeholder="tacos, cheap dinner, etc.">
          </div>
          <div class="form-group">
            <label>
              <small>
                Near
              </small>
            </label>
            <input type="text" class="form-control" value="Los Angeles">
          </div>
          <div class="form-group">
            <button type="submit" class="btn btn-default btn-sm">
              Search
            </button>
          </div>
        </form>

        <hr>

        <h4 class="text-primary">
          Review of the day
        </h4>

        <div class="media">
          <a class="pull-left" href="#">
            <span class="media-object media-img"></span>
          </a>
          <div class="media-body">
            <strong>
              Jane Smith reviewed Ricky's Fish Tacos
            </strong>
            <div><img src="/images/5-stars.png" title="5 out of 5"/>
            </div>
            <small class="text-muted">
              234 Reviews
            </small>

            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Minima, architecto, laboriosam
              doloribus sequi tenetur maxime magni porro sunt facilis officia deserunt blanditiis vitae
              consectetur rerum alias dolorem et dignissimos...
              <a href="#">
                Read More
              </a></p>
          </div>
        </div>

        <hr>

        <h4 class="text-primary">
          Welp on the go
        </h4>
        <p>
          Get the Welp app on your mobile phone.
          It's free and helps you find great, local businesses on the go!
        </p>
        <button type="submit" class="btn btn-default btn-sm">
          Get it free now
        </button>
      </div>
    </div>
    <br><br><br>
  </div>

</div>

<script src="/js/jquery.min.js" type="text/javascript"></script>
<script src="/js/bootstrap.min.js" type="text/javascript"></script>

</body>
</html>

