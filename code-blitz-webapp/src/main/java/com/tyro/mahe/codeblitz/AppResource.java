package com.tyro.mahe.codeblitz;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.impl.JacksonProvider;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;

@Path("whoAmI")
@Component
public class AppResource {

    @Autowired
    private Client client;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String whoAmI() {
        return "WhoAmI";
    }

    @GET
    @Path("connectingTo")
    @Produces(MediaType.TEXT_PLAIN)
    public String connectingTo() {
        return query("switch-engine");
    }

    private String query(final String applicationName) {
        String json = client.target("http://127.0.0.1:4001")
                .path("v2")
                .path("keys")
                .path(applicationName)
                .request()
                .get(String.class);

        Configuration provider = Configuration.defaultConfiguration().provider(new JacksonProvider());
        JsonPath.using(provider)  ;
        System.out.println("json = " + json);
                            JSONObject object = JsonPath.read(json, "$.node[?(@.key == '/switch-engine')]");
        //System.out.println("ports = " + obj.toJSONString());

        StringBuilder builder = new StringBuilder();
/*        for (Object port : ports) {
            String strPort = (String) port;
            System.out.println("strPort = " + strPort);
            builder.append("Port Number: " + strPort).append("/n");
        }*/

        return builder.toString();

    }
}
