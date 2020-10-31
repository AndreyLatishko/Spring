package task;

public class Lesson {
    private int id, quantityHours;
    private String name, form, teacher;

    public Lesson(int id, String name, int quantityHours, String form, String teacher) {
        this.id = id;
        this.name = name;
        this.quantityHours = quantityHours;
        this.form = form;
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantityHours() {
        return quantityHours;
    }

    public void setQuantityHours(int quantityHours) {
        this.quantityHours = quantityHours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
