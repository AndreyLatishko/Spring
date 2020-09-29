package task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UniversityDataManager implements AutoCloseable {

    private Connection connection;
    private Exercise exercise;
    private Lesson lessons;
    ArrayList<Exercise> exerciseArrayList = new ArrayList<>();
    ArrayList<Lesson> lessonArrayList = new ArrayList<>();


    public UniversityDataManager(DbProperties dbProperties) throws SQLException {
        Properties props = new Properties();
        props.setProperty("password", dbProperties.getPassword());
        props.setProperty("user", dbProperties.getUser());
        props.setProperty("serverTimezone", "UTC");
        connection = DriverManager.getConnection(dbProperties.getUrl(), props);
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    public List<Exercise> getAllExercise() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM spring.exercise");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int idLesson = resultSet.getInt("idLesson");
            Date dateOfTheLesson = resultSet.getDate("dateOfTheLesson");
            String topic = resultSet.getString("topic");
            String formOfOccupation = resultSet.getString("formOfOccupation");
            exercise = new Exercise(id, idLesson, dateOfTheLesson, topic, formOfOccupation);
            exerciseArrayList.add(exercise);
        }
        return exerciseArrayList;
    }

    public void printAllExercise(List<Exercise> exercises) {
        for (Exercise s : exercises) {
            System.out.printf("Id = %d, idLesson = %d, dateOfTheLesson - %s, topic - %s, formOfOccupation - %s.%n",
                    s.getId(), s.getIdLesson(), s.getDateOfTheLesson(), s.getTopic(), s.getFormOfOccupation());
        }
    }

    public List<Lesson> getAllLesson() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM spring.lesson");
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            int quantityhours = resultSet.getInt(3);
            String form = resultSet.getString(4);
            String teacher = resultSet.getString(5);
            lessons = new Lesson(id, name, quantityhours, form, teacher);
            lessonArrayList.add(lessons);
        }
        return lessonArrayList;
    }

    public void printAllLesson(List<Lesson> lessons) {
        for (Lesson s : lessons) {
            System.out.printf("Id = %d, name - %s, quantityhours = %d, form - %s, teacher - %s.%n",
                    s.getId(), s.getName(), s.getQuantityHours(), s.getForm(), s.getTeacher());
        }
    }

}
