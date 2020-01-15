package jmarijano_zadaca_3.Builder;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import jmarijano_zadaca_3.Composite.PlanComponent;
import jmarijano_zadaca_3.org.SpecificShow;

public class ConcreteBuilder implements PlanBuilder {

    private DayComposite danComposite;
    private List<SpecificShow> validShows;
    Comparator<SpecificShow> compareByStartTime;

    public ConcreteBuilder() {
        danComposite = new DayComposite();
        validShows = new ArrayList<>();
    }

    @Override
    public PlanBuilder buildDay(int day) {
        danComposite.setDay(day);
        return this;
    }

    @Override
    public PlanBuilder buildStartTime(LocalTime startTime) {
        danComposite.setStartTime(startTime);
        return this;

    }

    @Override
    public PlanBuilder buildEndTime(LocalTime endTime) {
        danComposite.setEndTime(endTime);
        return this;
    }

    public ArrayList<SpecificShow> copyObjects(List<SpecificShow> shows) {
        ArrayList<SpecificShow> output = new ArrayList<>();
        for (SpecificShow obj : shows) {
            output.add(obj.copy());
        }
        return output;
    }

    public void addShowsWithStartTimeAndDay(List<SpecificShow> shows) {
        for (SpecificShow showToBeInserted : shows) {
            int j = 0;
            if (showToBeInserted.getDay() == danComposite.getDay()
                    && showToBeInserted.getEndTime() != null && showToBeInserted.getStartTime() != null
                    && !showToBeInserted.getStartTime().isBefore(danComposite.getStartTime())
                    && !showToBeInserted.getEndTime().isAfter(danComposite.getEndTime())
                    && showToBeInserted.getEndTime().isAfter(showToBeInserted.getStartTime())) {
                if (validShows.isEmpty()) {
                    validShows.add(showToBeInserted);
                } else {
                    for (SpecificShow validShow : validShows) {
                        boolean isInvalidStartTime = validShow.getStartTime().isBefore(showToBeInserted.getEndTime());
                        boolean isInvalidEndTime = validShow.getEndTime().isAfter(showToBeInserted.getStartTime());
                        if (isInvalidEndTime && isInvalidStartTime) {
                            j++;
                        }
                    }
                    if (j == 0) {
                        validShows.add(showToBeInserted);
                    }
                    if (j != 0) {
                        System.err.println("Emisija " + showToBeInserted.getShow().getName() + " ima preklapanje");
                    }
                }
            }
        }
    }

    public void addShowsWithDay(List<SpecificShow> shows) {
        for (SpecificShow showToBeInserted : shows) {
            int j = 0;
            if (showToBeInserted.getDay() == danComposite.getDay()
                    && showToBeInserted.getEndTime() == null && showToBeInserted.getStartTime() == null) {
                if (validShows.isEmpty()) {
                    showToBeInserted.setStartTime(danComposite.getStartTime());
                    showToBeInserted.setEndTime(showToBeInserted.getStartTime().plusMinutes(showToBeInserted.getShow().getDuration()));
                    validShows.add(showToBeInserted);
                } else {
                    int showDuration = showToBeInserted.getShow().getDuration();
                    LocalTime nextStartTime = validShows.get(0).getStartTime();
                    long minute = Duration.between(danComposite.getStartTime(), nextStartTime).toMinutes();
                    if (showDuration <= minute) {
                        showToBeInserted.setStartTime(danComposite.getStartTime());
                        showToBeInserted.setEndTime(showToBeInserted.getStartTime().plusMinutes(showDuration));
                        j++;
                    } else {
                        for (int p = 0; p < validShows.size() - 1; p++) {
                            LocalTime currentEndTime = validShows.get(p).getEndTime();
                            nextStartTime = validShows.get(p + 1).getStartTime();
                            minute = Duration.between(currentEndTime, nextStartTime).toMinutes();
                            if (showDuration <= minute) {
                                showToBeInserted.setStartTime(currentEndTime);
                                showToBeInserted.setEndTime(currentEndTime.plusMinutes(showDuration));
                                j++;
                                break;
                            }
                        }
                    }
                    if (j == 0) {
                        LocalTime krajZadnje = validShows.get(validShows.size() - 1).getEndTime();
                        minute = Duration.between(krajZadnje, danComposite.getEndTime()).toMinutes();
                        if (showToBeInserted.getShow().getDuration() <= minute) {
                            showToBeInserted.setStartTime(krajZadnje);
                            showToBeInserted.setEndTime(krajZadnje.plusMinutes(showToBeInserted.getShow().getDuration()));
                            j++;
                        }
                    }
                    if (j != 0) {
                        validShows.add(showToBeInserted);
                    }
                    if (j == 0) {
                        System.err.println("Emisija " + showToBeInserted.getShow().getName() + " nije dodana");
                    }
                    Collections.sort(validShows, compareByStartTime);
                }
            }
        }
    }

    public void addShowsWithoutStartTimeAndDay(List<SpecificShow> shows, List<SpecificShow> mainShowList) {
        int brojac = 0;
        for (SpecificShow showToBeInserted : shows) {
            int j = 0;
            if (showToBeInserted.getDay() == 0
                    && showToBeInserted.getStartTime() == null
                    && showToBeInserted.getEndTime() == null) {
                if (validShows.isEmpty()) {
                    showToBeInserted.setStartTime(danComposite.getStartTime());
                    showToBeInserted.setEndTime(showToBeInserted.getStartTime().plusMinutes(showToBeInserted.getShow().getDuration()));
                    showToBeInserted.setDay(danComposite.getDay());
                    validShows.add(showToBeInserted);
                    mainShowList.remove(showToBeInserted);
                } else {
                    for (int p = 0; p < validShows.size() - 1; p++) {
                        int duration = showToBeInserted.getShow().getDuration();
                        LocalTime currentEndTime = validShows.get(p).getEndTime();
                        LocalTime nextStartTime = validShows.get(p + 1).getStartTime();
                        long minute = Duration.between(currentEndTime, nextStartTime).toMinutes();
                        if (duration <= minute) {
                            showToBeInserted.setStartTime(currentEndTime);
                            showToBeInserted.setEndTime(currentEndTime.plusMinutes(duration));
                            showToBeInserted.setDay((danComposite.getDay()));
                            j++;
                            break;
                        }
                    }
                    if (j == 0) {
                        LocalTime krajZadnje = validShows.get(validShows.size() - 1).getEndTime();
                        long minute = Duration.between(krajZadnje, danComposite.getEndTime()).toMinutes();
                        if (showToBeInserted.getShow().getDuration() <= minute) {
                            showToBeInserted.setStartTime(krajZadnje);
                            showToBeInserted.setDay(danComposite.getDay());
                            j++;
                        }
                    }
                    if (j != 0) {
                        validShows.add(showToBeInserted);
                        mainShowList.remove(brojac);
                        brojac--;
                    }
                    if (j == 0) {
                        System.err.println("Emisija " + showToBeInserted.getShow().getName() + " nije dodana");
                    }
                    Collections.sort(validShows, compareByStartTime);
                }
            }
            brojac++;
        }
    }

    @Override
    public PlanBuilder buildEmisijePrograma(List<SpecificShow> emisijeGlavna) {
        ArrayList<SpecificShow> shows = copyObjects(emisijeGlavna);
        addShowsWithStartTimeAndDay(shows);
        compareByStartTime = (SpecificShow p1, SpecificShow p2) -> p1.getStartTime().compareTo(p2.getStartTime());
        Collections.sort(validShows, compareByStartTime);
        addShowsWithDay(shows);
        addShowsWithoutStartTimeAndDay(shows, emisijeGlavna);
        danComposite.setShows((List<PlanComponent>) (List<?>) validShows);
        return this;
    }

    @Override
    public DayComposite buildDayComposite() {
        return danComposite;
    }
}
