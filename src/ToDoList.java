import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ToDoList {
    private ArrayList<Task> tasks = new ArrayList<>();
    private int taskIdCounter = 1;


    public void addTask(String description, LocalDate dueDate){
        Task task = new Task(taskIdCounter, description, dueDate);
        tasks.add(task);
        taskIdCounter++;
    }
    public void viewTask(){
        for (Task task: tasks){
            System.out.println(task.getId() + ". " + task.getDescription() + " - " + task.isCompleted());
            System.out.println("Deadline: " + task.getDueDate());
        }
    }
    public void removeTask(int taskId){
        tasks.removeIf(task -> task.getId() == taskId);
        System.out.println("Task was deleted");
    }
    public void sortTasksByDueDate(){
        Collections.sort(tasks, Comparator.comparing(Task::getDueDate));
    }
    public List<Task> getTasksDueToday(){
        LocalDate today = LocalDate.now();
        return tasks.stream()
                .filter(task -> task.getDueDate() != null && task.getDueDate().equals(today))
                .toList();
    }
    //data will be saved to a text file named "task_data.txt" in the current working directory.
    public void saveTasksToFile(String filename){
        try(PrintWriter writer = new PrintWriter(new FileWriter(filename))){
            for (Task task: tasks){
                writer.println(task.getId() + "|" + task.getDescription() + "|" + task.isCompleted()
                 + "|" + task.getDueDate());
            }
            System.out.println("Tasks saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving to file:" + e.getMessage());
        }
    }
    public void loadTasksFromFile(String fileName){
        tasks.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null){
                String[] parts = line.split("\\|");
                if (parts.length == 4){
                    int id = Integer.parseInt(parts[0]);
                    String description = parts[1];
                    boolean isStatus = Boolean.parseBoolean(parts[2]);
                    String dueDateStr = parts[3];
                    LocalDate dueDate = LocalDate.parse(dueDateStr);
                    Task task = new Task(id, description, dueDate);
                    task.setCompleted(isStatus);
                    tasks.add(task);
                }
            }
            System.out.println("Tasks loaded from " + fileName);
        } catch (IOException e){
            System.err.println("Error loading tasks from file: " + e.getMessage());
        }
    }
    public void updateTaskStatus(int taskId, boolean completed){
        Task task = findTaskById(taskId);
        if(task != null){
            task.setCompleted(completed);
            System.out.println("Task " + task.getId() + " marked as completed " + completed);
        } else  {
            System.out.println("Task not found");
        }
    }
    public Task findTaskById(int taskId){
        for (Task task: tasks){
            if(task.getId() == taskId){
                return task;
            }
        }
        return null;
    }
}
