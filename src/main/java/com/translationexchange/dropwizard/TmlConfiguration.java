package com.translationexchange.dropwizard;

import com.translationexchange.core.Tml;
import com.translationexchange.core.Utils;
import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;

import javax.validation.constraints.*;

public class TmlConfiguration extends Configuration {

  public void initTml() {
//    Tml.getConfig().setApplication(Utils.map(
//        "key", "e9ecde2f25c1fed5c555e4bb036210fcde472536f5d71cc8b85f3508de9c845c",
//        "host", "http://localhost:3000",
//        "cdn_host", "https://trex-snapshots-dev.s3-us-west-1.amazonaws.com"
//    ));
//
//    Tml.getConfig().setAgent(Utils.map(
//        "host", "http://localhost:8282/dist/agent.js"
//    ));

//		Tml.getConfig().setCache(Utils.buildMap(
//			"enabled", 				true,
//	    	"class", 				"com.translationexchange.cache.Memcached",
//	        "host", 				"localhost:11211",
//	        "namespace", 			"e9ecde2f25c1",
//	        "version_check_interval", 10
//		));

    Tml.getConfig().setApplication(Utils.map(
        "key", "a6d6badfbf585429e6159cabdfde72f3b771333dd41065a7fe11c19490891b1e"
    ));

    Tml.getConfig().setCache(Utils.map(
        "enabled", true,
        "class", "com.translationexchange.core.cache.FileCache",
        "path", 	"translations",
        "version", "20180323155419"
    ));
  }

}
