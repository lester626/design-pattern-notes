package singleton.eager;

import java.util.*;
import java.util.stream.Collectors;

public class SingletonEager {
    private static SingletonEager instance = new SingletonEager();

    public static SingletonEager getInstance() {
        return instance;
    }

    private final List<String> servers;

    private SingletonEager() {
        servers = new ArrayList<>();
    }

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

class SingletonEagerUsage {
    public static void main(String[] args) {
        SingletonEager singletonEagerA = SingletonEager.getInstance();
        SingletonEager singletonEagerB = SingletonEager.getInstance();

        singletonEagerA.addServer("http://google.com");
        singletonEagerA.addServer("https://facebook.com");
        singletonEagerB.addServer("http://bing.com");
        singletonEagerB.addServer("https://yahoo.com");

        System.out.println("http");
        System.out.println(singletonEagerA.getHttpServers());
        System.out.println("https");
        System.out.println(singletonEagerB.getHttpsServers());
    }
}