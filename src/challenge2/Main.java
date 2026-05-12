package challenge2;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Set<Task> tasks = TaskData.getTasks("all");
        sortAndPrint("All Tasks", tasks);

        Comparator<Task> sortByPriority = Comparator.comparing(Task::getPriority);

        Set<Task> annsTasks = TaskData.getTasks("ann");
        sortAndPrint("Ann's Tasks", annsTasks, sortByPriority);

        Set<Task> bobsTasks = TaskData.getTasks("bob");
        sortAndPrint("Bob's Tasks", bobsTasks, sortByPriority);

        Set<Task> carolsTasks = TaskData.getTasks("carol");
        sortAndPrint("Carol's Tasks", carolsTasks, sortByPriority);

        List<Set<Task>> sets = List.of(annsTasks, bobsTasks, carolsTasks);

        Set<Task> assignedTasks = getUnion(sets);
        sortAndPrint("Assigned Tasks", assignedTasks);

        Set<Task> everyTask = getUnion(List.of(tasks, assignedTasks));
        sortAndPrint("The True All Tasks", everyTask);

        Set<Task> missingTasks = getDifference(everyTask,tasks);
        sortAndPrint("Missing Tasks", missingTasks);

        Set<Task> unassignedTasks = getDifference(tasks,assignedTasks);
        sortAndPrint("Unassigned Tasks", unassignedTasks, sortByPriority);

        Set<Task> overlap = getUnion(List.of(
                getIntersect(annsTasks,bobsTasks),
                getIntersect(carolsTasks,bobsTasks),
                getIntersect(annsTasks,carolsTasks)
        ));
        sortAndPrint("Assigned to Multiples", overlap, sortByPriority);


    }

    private static void sortAndPrint(String header, Collection<Task> collection) {
        sortAndPrint(header, collection, null);
    }


    private static void sortAndPrint(String header, Collection<Task> collection, Comparator<Task> sorter) {

        String lineSeparator = "_".repeat(90);
        System.out.println(lineSeparator);
        System.out.println(header);
        System.out.println(lineSeparator);

        List<Task> list = new ArrayList<>(collection);
        list.sort(sorter);
        list.forEach(System.out::println);
    }

    private static Set<Task> getUnion(List<Set<Task>> sets) {
        Set<Task> union = new HashSet<>();
        for (var set : sets) {
            union.addAll(set);
        }
        return union;
    }

    private static Set<Task> getIntersect(Set<Task> set1, Set<Task> set2) {
        Set<Task> intersection = new HashSet<>(set1);

        intersection.retainAll(set2);

        return intersection;
    }

    private static Set<Task> getDifference(Set<Task> set1, Set<Task> set2) {
        Set<Task> modifiedSet = new HashSet<>(set1);

        modifiedSet.removeAll(set2);

        return modifiedSet;
    }
}
