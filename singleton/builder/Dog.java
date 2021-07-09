package singleton.builder;

import java.util.ArrayList;
import java.util.List;

public class Dog {
    private String name;
    private String type;
    private Integer age;
    private List<String> toys;

    public Dog(String name, String type, int age, List<String> toys) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.toys = toys;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<String> getToys() {
        return toys;
    }

    public void setToys(List<String> toys) {
        this.toys = toys;
    }

    public static class Builder {
        private String name;
        private String type;
        private int age;
        private List<String> toys = new ArrayList<>();

        public Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public Builder withType(final String type) {
            this.type = type;
            return this;
        }

        public Builder withAge(final Integer age) {
            this.age = age;
            return this;
        }

        public Builder withToys(final List<String> toys) {
            this.toys = toys;
            return this;
        }

        public Dog build() {
            return new Dog(name, type, age, toys);
        }
    }
}

class DogUsage {
    public static void main(String[] args) {
        List<String> toys = new ArrayList<>();
        toys.add("ball");
        toys.add("squeekie");

        final Dog dog = new Dog.Builder()
                .withName("cobi")
                .withType("Shih-tzu")
                .withAge(8)
                .withToys(toys)
                .build();

        System.out.println(dog.getName() + "\n" + dog.getType() + "\n" + dog.getAge() + "\n" + dog.getToys());
    }
}
