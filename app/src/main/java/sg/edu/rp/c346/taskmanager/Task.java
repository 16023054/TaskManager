package sg.edu.rp.c346.taskmanager;

import java.io.Serializable;

public class Task implements Serializable {
    private Integer id;
    private String title;
    private String descriptions;

    public Task(String title, String descriptions) {
        this.title = title;
        this.descriptions = descriptions;
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

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }
}
