package task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

@Component
public class UniversityDataManager implements AutoCloseable {
    private Connection connection;
    private Exercise exercise;
    private Lesson lessons;
    ArrayList<Exercise> exerciseArrayList = new ArrayList<>();
    ArrayList<Lesson> lessonArrayList = new ArrayList<>();
    private List<Exercise> exercises;

    @Autowired
    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    @Autowired
    public void setLessons(Lesson lessons) {
        this.lessons = lessons;
    }

    public UniversityDataManager(DbProperties dbProperties) throws SQLException {
        Properties props = new Properties();
        props.setProperty("password", dbProperties.getPassword());
        props.setProperty("user", dbProperties.getUser());
        props.setProperty("serverTimezone", "UTC");
        connection = DriverManager.getConnection(dbProperties.getUrl(), props);
    }

    private static final String DEFAULT_PROPERTIES_PATH = "C://Projects//MySpring//src//main//resources//config3.properties";
    private static DbProperties loadProperties(String[] args) {
        Properties properties = new Properties();
        String propertiesFilePath;
        if (args.length > 0) {
            propertiesFilePath = args[0];
        } else {
            System.out.println("Default properties file is used");
            propertiesFilePath = DEFAULT_PROPERTIES_PATH;
        }
        File propertiesFile = new File(propertiesFilePath);
        if (!propertiesFile.exists()) {
            throw new IllegalStateException("Properties file is not existing");
        }
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(propertiesFile))) {
            properties.load(bufferedReader);
        }  catch (IOException ex) {
            ex.printStackTrace();
            throw new IllegalStateException("Could not read properties file");
        }

        String url = properties.getProperty("url");
        String password = properties.getProperty("password");
        String user = properties.getProperty("user");
        Objects.requireNonNull(url);
        Objects.requireNonNull(password);
        Objects.requireNonNull(user);

        return new DbProperties(url, user, password);
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

    public List<Exercise> printAllExercise(List<Exercise> exercises) {
        for (Exercise s : exercises) {
            System.out.printf("Id = %d, idLesson = %d, dateOfTheLesson - %s, topic - %s, formOfOccupation - %s.%n",
                    s.getId(), s.getIdLesson(), s.getDateOfTheLesson(), s.getTopic(), s.getFormOfOccupation());
        }
        return exercises;
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

    public List<Lesson> printAllLesson(List<Lesson> lessons) {
        for (Lesson s : lessons) {
            System.out.printf("Id = %d, name - %s, quantityhours = %d, form - %s, teacher - %s.%n",
                    s.getId(), s.getName(), s.getQuantityHours(), s.getForm(), s.getTeacher());
        }
        return lessons;
    }

}
