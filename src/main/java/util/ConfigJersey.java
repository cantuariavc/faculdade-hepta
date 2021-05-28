package util;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("rest")
public class ConfigJersey extends ResourceConfig {
    public ConfigJersey() {
        packages("rest");
        register(MultiPartFeature.class);
//        register(new CORSFilter());
    }
}
