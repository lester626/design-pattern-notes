package observer;

import java.util.ArrayList;
import java.util.List;

abstract class Observer {
    private final Subject subject;

    public Observer(final Subject subject) {
        this.subject = subject;
        subject.subscribe(this);
    }

    public abstract void update(final List<Integer> values);

    public void sendValue(final int value) {
        subject.sendValue(value);
    }
}

class ConcreteValueObserver extends Observer {

    public ConcreteValueObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update(final List<Integer> values) {
        System.out.println("Concrete Value: " + values.get(values.size()-1));
    }
}

class ValueLowerObserver extends Observer {

    public ValueLowerObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update(final List<Integer> values) {
        if(values.size() > 2){
            int prevValue = values.get(values.size()-2);
            int lateValue = values.get(values.size()-1);
            if(prevValue < lateValue){
                System.out.println("Value Lower: " + lateValue);
            }
        }
    }
}

class ByTenChangeObserver extends Observer {

    public ByTenChangeObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update(final List<Integer> values) {
        if(values.size() > 2){
            int lateValue = values.get(values.size()-1);
            int differTen = lateValue - values.get(values.size()-2);
            if(differTen >= 10) {
                System.out.println("By Ten Change: " + lateValue);
            }
        }
    }
}

public class Subject {
    private final int value;

    private final List<Observer> observers = new ArrayList<>();
    private final List<Integer> values = new ArrayList<>();

    public Subject(final int value) {
        this.value = value;
    }

    public void subscribe(final Observer observer) {
        if(!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void sendValue(final int value) {
        values.add(value);
        notifyAboutChange();
    }

    public void notifyAboutChange() {
        for(final var observer : observers) {
            observer.update(values);
        }
    }
}

class SubjectDemo {
    public static void main(String[] args) {
        final Subject subject = new Subject(5);

        final Observer observerA = new ConcreteValueObserver(subject);
        final Observer observerB = new ValueLowerObserver(subject);
        final Observer observerC = new ByTenChangeObserver(subject);
        final Observer observerD = new ConcreteValueObserver(subject);
        final Observer observerE = new ValueLowerObserver(subject);
        final Observer observerF = new ByTenChangeObserver(subject);
        final Observer observerG = new ConcreteValueObserver(subject);
        final Observer observerH = new ValueLowerObserver(subject);
        final Observer observerI = new ByTenChangeObserver(subject);

        observerA.sendValue(10);
        observerB.sendValue(12);
        observerD.sendValue(44);
        observerC.sendValue(52);
        observerE.sendValue(32);
        observerF.sendValue(86);
        observerG.sendValue(52);
        observerH.sendValue(99);
        observerI.sendValue(94);
        observerC.sendValue(21);
    }
}


