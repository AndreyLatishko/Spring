package task;

import java.sql.Date;

public class Exercise {
    private int id,idLesson;
    private String  topic, formOfOccupation;
    private Date dateOfTheLesson;
    //commit


    public Exercise(int id, int idLesson, Date dateOfTheLesson, String topic, String formOfOccupation) {
        this.id = id;
        this.idLesson = idLesson;
        this.dateOfTheLesson = dateOfTheLesson;
        this.topic = topic;
        this.formOfOccupation = formOfOccupation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdLesson() {
        return idLesson;
    }

    public void setIdLesson(int idLesson) {
        this.idLesson = idLesson;
    }

    public Date getDateOfTheLesson() {
        return dateOfTheLesson;
    }

    public void setDateOfTheLesson(Date dateOfTheLesson) {
        this.dateOfTheLesson = dateOfTheLesson;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getFormOfOccupation() {
        return formOfOccupation;
    }

    public void setFormOfOccupation(String formOfOccupation) {
        this.formOfOccupation = formOfOccupation;
    }
}
