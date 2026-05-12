package challenge2;

import java.util.Comparator;
import java.util.Objects;

enum Status {IN_QUEUE, ASSIGNED, IN_PROGRESS};

enum Priority {HIGH, LOW, MEDIUM};

public class Task implements Comparable<Task> {

    private String assignee;
    private String name;
    private String description;
    private Status status;
    private Priority priority;

    public Task(String assignee, String name, String description, Status status, Priority priority) {
        this.assignee = assignee;
        this.name = name;
        this.description = description;
        this.status = status;
        this.priority = priority;
    }

    public Task(String assignee, String name, String description, Priority priority) {
        this(
                assignee,
                name,
                description,
                assignee == null ? Status.IN_QUEUE : Status.ASSIGNED,
                priority
        );
    }

    public Task(String name,
                String description, Priority priority) {

        this(null, name, description, priority);
    }

    public String getAssignee() {
        return assignee;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "%-20s %-25s %-10s %-10s %s".formatted(assignee, name, description, priority, status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task task)) return false;

        return Objects.equals(name, task.name)
                && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
    @Override
    public int compareTo(Task o) {

        int result = Comparator.nullsLast(String::compareTo)
                .compare(this.name, o.name);

        if (result == 0) {
            result = Comparator.nullsLast(String::compareTo)
                    .compare(this.description, o.description);
        }

        return result;
    }
}
