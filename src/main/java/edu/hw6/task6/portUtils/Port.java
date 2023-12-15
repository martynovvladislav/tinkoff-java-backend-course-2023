package edu.hw6.task6.portUtils;

import java.util.Objects;

public class Port {
    private final int id;
    private final Protocol protocol;
    private final String service;

    public enum Protocol {
        TCP,
        UDP
    }

    public Port(int id, Protocol protocol, String service) {
        this.id = id;
        this.protocol = protocol;
        this.service = Objects.requireNonNullElse(service, "");
    }

    public int getId() {
        return id;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public String getService() {
        return service;
    }
}
