package task;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class Application {
    private static final String DEFAULT_PROPERTIES_PATH = "C://Projects//MySpring//src//main//resources//config3.properties";

    public static void main(String[] args)  {

        DbProperties dbProperties = loadProperties(args);

        try (UniversityDataManager universityDataManager = new UniversityDataManager(dbProperties)) {
            List<Exercise> exercises = universityDataManager.getAllExercise();
            List<Lesson> lessons = universityDataManager.getAllLesson();
            System.out.println("PrintAllLesson:");
            universityDataManager.printAllLesson(lessons);
            System.out.println("PrintAllExercise:");
            universityDataManager.printAllExercise(exercises);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
}

