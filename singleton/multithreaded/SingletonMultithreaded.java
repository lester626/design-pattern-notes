package singleton.multithreaded;

import java.util.*;
import java.util.stream.Collectors;

public class SingletonMultithreaded {
    private static SingletonMultithreaded instance;

    public static SingletonMultithreaded getInstance() {
        if(instance == null){
            synchronized (SingletonMultithreaded.class) {
                if(instance == null){
                    instance = new SingletonMultithreaded();
                }
            }
        }
        return instance;
    }

    private final List<String> servers;

    private SingletonMultithreaded() {
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

class SingletonMultithreadedUsage {
    public static void main(String[] args) {
        SingletonMultithreaded singletonMultithreadedA = SingletonMultithreaded.getInstance();
        SingletonMultithreaded singletonMultithreadedB = SingletonMultithreaded.getInstance();

        singletonMultithreadedA.addServer("http://google.com");
        singletonMultithreadedA.addServer("https://facebook.com");
        singletonMultithreadedB.addServer("http://bing.com");
        singletonMultithreadedB.addServer("https://yahoo.com");

        System.out.println("http");
        System.out.println(singletonMultithreadedA.getHttpServers());
        System.out.println("https");
        System.out.println(singletonMultithreadedB.getHttpsServers());
    }
}