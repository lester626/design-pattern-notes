package template;

import java.util.*;

public abstract class PerformanceTestTemplate {
    abstract void testIteration();
    abstract int getWarmUpIterationsNum();
    abstract int getIterationsNum();

    public final void run() {
        for (int index = 0; index < getWarmUpIterationsNum(); index++) {
            testIteration();
        }

        final List<Long> iterationsExecutionTimes = new ArrayList<>();

        for (int index = 0; index < getIterationsNum(); index++) {
            long startTimestamp = System.currentTimeMillis();
            testIteration();
            long endTimestamp = System.currentTimeMillis();
            iterationsExecutionTimes.add(endTimestamp - startTimestamp);
        }

        showStatistics(iterationsExecutionTimes);
    }

    private void showStatistics(final List<Long> iterationsExecutionTimes) {
        System.out.println("Shortest iteration took " + calculateShortestIteration(iterationsExecutionTimes));
        System.out.println("Longest iteration took " + calculateLongestIteration(iterationsExecutionTimes));
        System.out.println("All iterations took " + calculateTotalExecutionTime(iterationsExecutionTimes));
    }

    private Long calculateShortestIteration(final List<Long> iterationsDurations) {
        return iterationsDurations.stream()
                .min(Comparator.naturalOrder())
                .orElseThrow();
    }

    private Long calculateLongestIteration(final List<Long> iterationsDurations) {
        return iterationsDurations.stream()
                .max(Comparator.naturalOrder())
                .orElseThrow();
    }

    private Long calculateTotalExecutionTime(final List<Long> iterationsDurations) {
        return iterationsDurations.stream().mapToLong(x -> x).sum();
    }
}

class RandomListSortingPerformanceTest extends PerformanceTestTemplate {

    private static final int NUMBERS_NUM = 100000;

    @Override
    protected int getWarmUpIterationsNum() {
        return 2;
    }

    @Override
    protected int getIterationsNum() {
        return 100;
    }

    @Override
    protected void testIteration() {
        final List<Integer> integers = new ArrayList<>();
        final Random random = new Random();
        for (int idx = 0; idx < NUMBERS_NUM; idx++) {
            integers.add(random.nextInt());
        }

        Collections.sort(integers);
    }
}

class StringBuilderAppendPerformanceTest extends PerformanceTestTemplate {

    private static final int CHARS_NUM = 1000000;

    @Override
    protected int getWarmUpIterationsNum() {
        return 2;
    }

    @Override
    protected int getIterationsNum() {
        return 100;
    }

    @Override
    protected void testIteration() {
        final Random random = new Random();
        final StringBuilder stringBuilder = new StringBuilder();
        for (int idx = 0; idx < CHARS_NUM; idx++) {
            stringBuilder.append(Math.abs(random.nextInt()% 128));
        }
        log.trace(stringBuilder.toString());
    }
}

class TemplateMethodUsage {
    public static void main(String[] args) {
        PerformanceTestTemplate testTemplate = new RandomListSortingPerformanceTest();
        testTemplate.run();

        testTemplate = new StringBuilderAppendPerformanceTest();
        testTemplate.run();
    }
}
