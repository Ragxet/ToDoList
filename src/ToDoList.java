import java.util.ArrayList;

public class ToDoList {
    private ArrayList<Task> tasks = new ArrayList<>();
    private int taskIdCounter = 1;
    public void addTask(String description){
        Task task = new Task(taskIdCounter, description);
        tasks.add(task);
        taskIdCounter++;
    }
    public void viewTask(){
        for (Task task: tasks){
            System.out.println(task.getID() + ". " + task.getDescription() + " - " + task.isStatus());
        }
    }
    public void removeTask(int taskId){
        tasks.removeIf(task -> task.getID() == taskId);
    }
}
