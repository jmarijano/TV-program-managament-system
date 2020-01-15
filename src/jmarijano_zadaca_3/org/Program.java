package jmarijano_zadaca_3.org;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Program {

    private int id;
    private String name;
    private LocalTime startTime;
    private LocalTime endTime;
    private String fileName;
    private List<SpecificShow> specificShows;

    public Program() {
        specificShows = new ArrayList<>();
    }

    public Program(int id, String name, LocalTime startTime, LocalTime endTime,
            String fileName, List<SpecificShow> specificShows) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.fileName = fileName;
        this.specificShows = specificShows;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<SpecificShow> getSpecificShows() {
        return specificShows;
    }

    public void setSpecificShows(List<SpecificShow> specificShows) {
        this.specificShows = specificShows;
    }
}
