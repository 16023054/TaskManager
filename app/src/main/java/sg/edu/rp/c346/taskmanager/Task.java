package sg.edu.rp.c346.taskmanager;

import java.io.Serializable;

public class Task implements Serializable {
    private Integer id;
    private String title;
    private String descriptions;
    private Boolean completed;



    public Task(Integer id, String title, String descriptions, Boolean completed) {
        this.id = id;
        this.title = title;
        this.descriptions = descriptions;
        this.completed = completed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }
}
