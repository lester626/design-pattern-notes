package adapter;

import java.sql.Array;
import java.util.*;

import static java.util.Arrays.*;

public class ComputerGame {
    private final String name;
    private final PegiAgeRating pegiAgeRating;
    private final Double budgetInMillionsOfDollars;
    private final Integer minimumGpuMemoryInMegabytes;
    private final Integer diskSpaceNeededInGB;
    private final Integer ramNeededInGb;
    private final Integer coresNeeded;
    private final Double coreSpeedInGhz;

    public ComputerGame(final String name, final PegiAgeRating pegiAgeRating, final Double budgetInMillionsOfDollars,
                        final Integer minimumGpuMemoryInMegabytes, final Integer diskSpaceNeededInGB,
                        final Integer ramNeededInGb, final Integer coresNeeded, final Double coreSpeedInGhz) {
        this.name = name;
        this.pegiAgeRating = pegiAgeRating;
        this.budgetInMillionsOfDollars = budgetInMillionsOfDollars;
        this.minimumGpuMemoryInMegabytes = minimumGpuMemoryInMegabytes;
        this.diskSpaceNeededInGB = diskSpaceNeededInGB;
        this.ramNeededInGb = ramNeededInGb;
        this.coresNeeded = coresNeeded;
        this.coreSpeedInGhz = coreSpeedInGhz;
    }

    public String getName() {
        return name;
    }

    public PegiAgeRating getPegiAgeRating() {
        return pegiAgeRating;
    }

    public Double getBudgetInMillionsOfDollars() {
        return budgetInMillionsOfDollars;
    }

    public Integer getMinimumGpuMemoryInMegabytes() {
        return minimumGpuMemoryInMegabytes;
    }

    public Integer getDiskSpaceNeededInGB() {
        return diskSpaceNeededInGB;
    }

    public Integer getRamNeededInGb() {
        return ramNeededInGb;
    }

    public Integer getCoresNeeded() {
        return coresNeeded;
    }

    public Double getCoreSpeedInGhz() {
        return coreSpeedInGhz;
    }
}

enum PegiAgeRating {
    P3, P7, P12, P16, P18
}

class Requirements {
    private final Integer gpuGb;
    private final Integer HDDGb;
    private final Integer RAMGb;
    private final Double cpuGhz;
    private final Integer coresNum;

    public Requirements(final Integer gpuGb,
                        final Integer HDDGb,
                        final Integer RAMGb,
                        final Double cpuGhz,
                        final Integer coresNum) {
        this.gpuGb = gpuGb;
        this.HDDGb = HDDGb;
        this.RAMGb = RAMGb;
        this.cpuGhz = cpuGhz;
        this.coresNum = coresNum;
    }

    public Integer getGpuGb() {
        return gpuGb;
    }

    public Integer getHDDGb() {
        return HDDGb;
    }

    public Integer getRAMGb() {
        return RAMGb;
    }

    public Double getCpuGhz() {
        return cpuGhz;
    }

    public Integer getCoresNum() {
        return coresNum;
    }
}

interface PCGame {
    String getTitle();
    Integer getPegiAllowedAge();
    boolean isTripleAGame();
    Requirements getRequirements();
}

class ComputerGameAdapter implements PCGame {
    private final ComputerGame computerGame;
    private PegiAgeRating pegiAgeRating;

    public ComputerGameAdapter(final ComputerGame computerGame) {
        this.computerGame = computerGame;
        this.pegiAgeRating = pegiAgeRating;
    }

    @Override
    public String getTitle() {
        return computerGame.getName();
    }

    @Override
    public Integer getPegiAllowedAge() {
        List<PegiAgeRating> pegiAgeRatings = asList(pegiAgeRating.values());
        int pegiIndex = pegiAgeRatings.indexOf(computerGame.getPegiAgeRating());
        String pegiString = pegiAgeRatings.get(pegiIndex).toString().substring(1);
        return Integer.parseInt(pegiString);
    }

    @Override
    public boolean isTripleAGame() {
        return computerGame.getBudgetInMillionsOfDollars() > 50000000.0;
    }

    @Override
    public Requirements getRequirements() {
        return new Requirements(computerGame.getMinimumGpuMemoryInMegabytes(),
                computerGame.getDiskSpaceNeededInGB(),
                computerGame.getRamNeededInGb(),
                computerGame.getCoreSpeedInGhz(),
                computerGame.getCoresNeeded());
    }
}

class ComputerGameUsage {
    public static void main(String[] args) {
        List<PCGame> pcGames = new ArrayList<>();
        Requirements assassinsCreedReq = new Requirements(4000, 10, 6000, 3.5, 4);
        pcGames.add(new ComputerGameAdapter(new ComputerGame("Assassin's creed",
                PegiAgeRating.P16,
                50500000.0,
                assassinsCreedReq.getGpuGb(),
                assassinsCreedReq.getHDDGb(),
                assassinsCreedReq.getRAMGb(),
                assassinsCreedReq.getCoresNum(),
                assassinsCreedReq.getCpuGhz())));
        for(final var pcGame : pcGames){
            System.out.println("Title: " + pcGame.getTitle());
            System.out.println("Age Restriction: " + pcGame.getPegiAllowedAge() + "+");
            System.out.println("Game is Triple A? " + pcGame.isTripleAGame());
            System.out.println("Requirements for " + pcGame.getTitle() + ":");
            System.out.println(pcGame.getRequirements().getGpuGb());
            System.out.println(pcGame.getRequirements().getHDDGb());
            System.out.println(pcGame.getRequirements().getRAMGb());
            System.out.println(pcGame.getRequirements().getCpuGhz());
            System.out.println(pcGame.getRequirements().getCoresNum());
        }
    }
}