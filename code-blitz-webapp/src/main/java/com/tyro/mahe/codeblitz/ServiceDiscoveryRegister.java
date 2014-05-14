package com.tyro.mahe.codeblitz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

@Component
public class ServiceDiscoveryRegister {

    private Client client;

    @Autowired
    public ServiceDiscoveryRegister(Client client) {
        this.client = client;
    }

    @PostConstruct
    public void registerWithServiceDiscoveryServer() {
        String portOfApplication = System.getProperty("jetty.port");
        System.out.println("Jetty Port: " + portOfApplication);

        String applicationName = System.getProperty("app.name");
        System.out.println("App Name: " + applicationName);
        register(applicationName, portOfApplication);

    }

    private void register(final String applicationName, String portOfApplication) {
        client.target("http://127.0.0.1:4001")
                .path("v2")
                .path("keys")
                .path(applicationName)
                .request()
                .post(Entity.entity("value=" + portOfApplication, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
    }
}
