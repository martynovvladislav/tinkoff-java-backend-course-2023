package edu.hw6.task6.portUtils;

import java.util.Map;

public class ServiceList {
    private ServiceList() {}

    public static final Map<Integer, String> SERVICES = Map.ofEntries(
        Map.entry(80, "HTTP"),
        Map.entry(21, "FTP"),
        Map.entry(25, "SMTP"),
        Map.entry(22, "SSH"),
        Map.entry(443, "HTTPS"),
        Map.entry(3306, "MySQL Database"),
        Map.entry(5432, "PostgreSQL Database"),
        Map.entry(3389, "Remote Desktop Protocol (RDP)"),
        Map.entry(27017, "MongoDB Database"),
        Map.entry(1521, "Oracle Database"),
        Map.entry(8080, "HTTP Proxy"),
        Map.entry(1080, "SOCKS"),
        Map.entry(1900, "Simple Service Discovery Protocol (SSDP)"),
        Map.entry(135, "EPMAP"),
        Map.entry(137, "Служба имен NetBIOS"),
        Map.entry(138, "Служба датаграмм NetBIOS"),
        Map.entry(139, "Служба сеансов NetBIOS"),
        Map.entry(445, "Microsoft-DS Active Directory"),
        Map.entry(5353, "Многоадресный DNS"),
        Map.entry(5355, "LLMNR (Link-Local Multicast Name Resolution)")
    );
}
