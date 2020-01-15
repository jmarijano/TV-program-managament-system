package jmarijano_zadaca_3.Builder;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import jmarijano_zacaca_3.Visitor.Visitor;
import jmarijano_zadaca_3.Composite.PlanComponent;
import jmarijano_zadaca_3.Decorator.DefaultDayDecoration;

public class DayComposite implements PlanComponent {

    private int day;
    private LocalTime startTime;
    private LocalTime endTime;
    private List<PlanComponent> shows;
    private PlanComponent parent;

    public DayComposite() {
        shows = new ArrayList<>();
    }

    public DayComposite(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public List<PlanComponent> getShows() {
        return shows;
    }

    public void setShows(List<PlanComponent> shows) {
        this.shows = shows;
    }

    @Override
    public PlanComponent getComponent() {
        return this;
    }

    @Override
    public DayComposite copy() {
        DayComposite novi = new DayComposite();
        novi.setDay(this.day);
        novi.setShows(this.shows);
        novi.setEndTime(this.endTime);
        novi.setStartTime(this.startTime);
        novi.setParent(parent);
        return novi;
    }

    @Override
    public void showData() {
        if (this.shows.size() > 0) {
            PlanComponent planComponent = new DefaultDayDecoration(this);
            planComponent.showData();
        }
    }

    @Override
    public int accept(Visitor visitor) {
        return visitor.visit(this);
    }

    public boolean add(PlanComponent planComponent) {
        if (shows.add(planComponent)) {
            return true;
        }
        return false;
    }

    public PlanComponent getParent() {
        return parent;
    }

    public void setParent(PlanComponent parent) {
        this.parent = parent;
    }

}
