package task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import java.sql.SQLException;
import java.util.List;

@Controller
public class UniversityController {
    private UniversityDataManager universityDataManager;

    @Autowired
    public UniversityController(UniversityDataManager universityDataManager) {
        this.universityDataManager = universityDataManager;
    }

    @GetMapping(value = "/lesson")
    public List<Lesson> lesson() throws SQLException {
        List<Lesson> allLesson = universityDataManager.getAllLesson();
        return  universityDataManager.printAllLesson(allLesson);
    }

    @GetMapping(value = "/exercise")
    public List<Exercise> exercise() throws SQLException {
        List<Exercise> allExercise = universityDataManager.getAllExercise();
        return universityDataManager.printAllExercise(allExercise);
    }
}
