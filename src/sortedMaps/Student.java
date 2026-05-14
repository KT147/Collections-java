package sortedMaps;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

record Course(String courseId, String name, String subject){}
record Purchase(String courseId, int studentIt, double price, int yr, int dayOfYear){

    public LocalDate purchaseDate() {
        return LocalDate.ofYearDay(yr, dayOfYear);
    }
}

public class Student {

    public static int lastId = 1;
    private String name;
    private int id;
    private List<Course> courselist;

    public Student(String name, List<Course> courselist) {
        this.courselist = courselist;
        this.name = name;
        id = lastId++;
    }

    public Student(String name, Course course) {
        this(name,new ArrayList<>(List.of(course)));
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void addCourse(Course course) {
        courselist.add(course);
    }

    @Override
    public String toString() {
        String[] courseNames = new String[courselist.size()];
        Arrays.setAll(courseNames, i ->courselist.get(i).name());
        return "[%d] : %s".formatted(id, String.join(", ", courseNames));
    }
}
