package task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public class UniversityController {
    private UniversityDataManager universityDataManager;

    @Autowired
    public UniversityController(UniversityDataManager universityDataManager) {
        this.universityDataManager = universityDataManager;
    }

    @GetMapping(value = "/lesson")
    public List<Lesson> lesson() throws SQLException {
        List<Lesson> allLesson = universityDataManager.queryAllLesson();
        return universityDataManager.getAllLesson(allLesson);
    }

    @GetMapping(value = "/exercise")
    public List<Exercise> exercise() throws SQLException {
        List<Exercise> allExercise = universityDataManager.queryAllExercise();
        return universityDataManager.getAllExercise(allExercise);
    }
}
