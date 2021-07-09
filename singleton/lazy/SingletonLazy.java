package singleton.lazy;

import java.util.*;
import java.util.stream.Collectors;

public class SingletonLazy {
    private static SingletonLazy instance;

    public static SingletonLazy getInstance() {
        if(instance == null){
            instance = new SingletonLazy();
        }
        return instance;
    }

    private final List<String> servers;

    private SingletonLazy() {
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

class SingletonLazyUsage {
    public static void main(String[] args) {
        SingletonLazy singletonLazyA = SingletonLazy.getInstance();
        SingletonLazy singletonLazyB = SingletonLazy.getInstance();

        singletonLazyA.addServer("http://google.com");
        singletonLazyA.addServer("https://facebook.com");
        singletonLazyB.addServer("http://bing.com");
        singletonLazyB.addServer("https://yahoo.com");

        System.out.println("http");
        System.out.println(singletonLazyA.getHttpServers());
        System.out.println("https");
        System.out.println(singletonLazyB.getHttpsServers());
    }
}
