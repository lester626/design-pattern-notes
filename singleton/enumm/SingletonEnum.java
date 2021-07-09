package singleton.enumm;

import java.util.*;
import java.util.stream.Collectors;

public enum SingletonEnum {
    INSTANCE;

    final List<String> servers = new ArrayList<>();

    public boolean addServer(final String s) {
        if((s.startsWith("http") || s.startsWith("https")) && !servers.contains(s) && !s.equals("")) {
            return servers.add(s);
        }
        return false;
    }

    public List<String> getHttpServers(){
        return servers.stream()
                .filter(servers -> servers.startsWith("http"))
                .collect(Collectors.toUnmodifiableList());
    }

    private List<String> getServersStartingWith(final String prefix) {
        return servers.stream()
                .filter(server -> server.startsWith(prefix))
                .collect(Collectors.toUnmodifiableList());
    }

    public List<String> getHttpsServers(){
        return servers.stream()
                .filter(servers -> servers.startsWith("https"))
                .collect(Collectors.toUnmodifiableList());
    }
}

class SingletonEnumUsage {
    public static void main(String[] args) {
        SingletonEnum singletonEnumA = SingletonEnum.INSTANCE;
        SingletonEnum singletonEnumB = SingletonEnum.INSTANCE;

        singletonEnumA.addServer("http://google.com");
        singletonEnumA.addServer("https://facebook.com");
        singletonEnumB.addServer("http://bing.com");
        singletonEnumB.addServer("https://yahoo.com");

        System.out.println("http");
        System.out.println(singletonEnumA.getHttpServers());
        System.out.println("https");
        System.out.println(singletonEnumB.getHttpsServers());
    }
}