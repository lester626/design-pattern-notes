package decorator;

import com.sun.source.tree.Tree;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface StatisticsLogger {
    void displayStatistics();
    List<Double> getExecutionTimes();
}

class ExecutionTimesBaseStatistics implements StatisticsLogger {
    private final List<Double> executionTimes;

    public ExecutionTimesBaseStatistics(List<Double> executionTimes) {
        this.executionTimes = executionTimes;
    }

    @Override
    public void displayStatistics() {
        final StringBuilder stringBuilder = new StringBuilder();
        executionTimes.forEach(executionTime -> stringBuilder.append("Execution TIme: ").append(executionTime).append("\n"));
        System.out.println(stringBuilder.toString());
    }

    @Override
    public List<Double> getExecutionTimes() {
        return executionTimes;
    }
}

class WithMeanStatisticsLogger implements StatisticsLogger{
    private final StatisticsLogger statisticsLogger;

    public WithMeanStatisticsLogger(final StatisticsLogger statisticsLogger) {
        this.statisticsLogger = statisticsLogger;
    }

    @Override
    public void displayStatistics() {
        Double sum = 0.0;
        for(Double avg : statisticsLogger.getExecutionTimes()){
            sum += avg;
        }
        statisticsLogger.displayStatistics();
        System.out.println("Average result: " + sum / statisticsLogger.getExecutionTimes().size());
    }

    @Override
    public List<Double> getExecutionTimes() {
        return statisticsLogger.getExecutionTimes();
    }
}

class WithSummaryStatisticsLogger implements StatisticsLogger{
    private final StatisticsLogger statisticsLogger;

    public WithSummaryStatisticsLogger(final StatisticsLogger statisticsLogger) {
        this.statisticsLogger = statisticsLogger;
    }

    @Override
    public void displayStatistics() {
        List<Double> executionTimes = statisticsLogger.getExecutionTimes();
        int sum = 0;
        for (Double sumAll : executionTimes) {
            sum += sumAll;
        }
        statisticsLogger.displayStatistics();
        System.out.println("Number of records: " + executionTimes.size());
        System.out.println("Sum: " + sum);
        System.out.println("Minimum value: " + Collections.min(executionTimes));
        System.out.println("Maximum value: " + Collections.max(executionTimes));
    }

    @Override
    public List<Double> getExecutionTimes() {
        return statisticsLogger.getExecutionTimes();
    }
}

class DecoratorDemo {
    public static void main(String[] args) {
        final StatisticsLogger statisticsLogger = new WithMeanStatisticsLogger(
                new WithSummaryStatisticsLogger(
                        new ExecutionTimesBaseStatistics(List.of(4.1, 2.2, 3.4))));
        statisticsLogger.displayStatistics();
    }
}
