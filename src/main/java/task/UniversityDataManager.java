package task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
public class UniversityDataManager implements AutoCloseable {
    private static final String DEFAULT_PROPERTIES_PATH = "C://Projects//MySpring//src//main//resources//config3.properties";
    private Connection connection;

    @Autowired
    public UniversityDataManager(Environment environment) throws SQLException {
        Properties props = new Properties();
        props.setProperty("password", environment.getProperty("password"));
        props.setProperty("user", environment.getProperty("user"));
        props.setProperty("serverTimezone", "UTC");
        connection = DriverManager.getConnection(environment.getProperty("url"), props);
    }



    @Override
    public void close() throws Exception {
        connection.close();
    }

    public List<Exercise> queryAllExercise() throws SQLException {
        ArrayList<Exercise> exercises = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM spring.exercise");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int idLesson = resultSet.getInt("idLesson");
            Date dateOfTheLesson = resultSet.getDate("dateOfTheLesson");
            String topic = resultSet.getString("topic");
            String formOfOccupation = resultSet.getString("formOfOccupation");
            Exercise exercise = new Exercise(id, idLesson, dateOfTheLesson, topic, formOfOccupation);
            exercises.add(exercise);
        }
        return exercises;
    }

    public List<Exercise> getAllExercise(List<Exercise> exercises) {
        for (Exercise s : exercises) {
            System.out.printf("Id = %d, idLesson = %d, dateOfTheLesson - %s, topic - %s, formOfOccupation - %s.%n",
                    s.getId(), s.getIdLesson(), s.getDateOfTheLesson(), s.getTopic(), s.getFormOfOccupation());
        }
        return exercises;
    }

    public List<Lesson> queryAllLesson() throws SQLException {
        ArrayList<Lesson> lessons =new ArrayList<>() ;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM spring.lesson");
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            int quantityhours = resultSet.getInt(3);
            String form = resultSet.getString(4);
            String teacher = resultSet.getString(5);
            Lesson lesson = new Lesson(id, name, quantityhours, form, teacher);
            lessons.add(lesson);
        }
        return lessons;
    }

    public List<Lesson> getAllLesson(List<Lesson> lessons) {
        for (Lesson s : lessons) {
            System.out.printf("Id = %d, name - %s, quantityhours = %d, form - %s, teacher - %s.%n",
                    s.getId(), s.getName(), s.getQuantityHours(), s.getForm(), s.getTeacher());
        }
        return lessons;
    }

}
