import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ToDoListApp {
    public static void main(String[] args) {
        ToDoList toDoList = new ToDoList();
        int taskId;

        toDoList.loadTasksFromFile("Task_Data.txt");

        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Remove Tasks");
            System.out.println("4. Update task status");
            System.out.println("5. Sort Tasks by Due Date");
            System.out.println("6. View Tasks Due Today");
            System.out.println("7. Save changes and Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice){
                case 1:
                    System.out.print("Enter task description: ");
                    scanner.nextLine();
                    String description = scanner.nextLine();
                    if (!description.isEmpty()){
                        LocalDate dueDate = null;
                        boolean validDate = false;
                        while(!validDate){
                            System.out.print("Enter due date (YYYY-MM-DD): ");
                            String dueDateStr = scanner.nextLine().trim();
                            try {
                                dueDate = LocalDate.parse(dueDateStr);
                                validDate = true;
                            } catch (DateTimeParseException e){
                                System.out.println("Invalid date format");
                            }
                        }
                        toDoList.addTask(description, dueDate);
                        toDoList.saveTasksToFile("Task_Data.txt");
                    } else {
                        System.out.println("Task description cannot be empty");
                    }

                    break;
                case 2:
                    toDoList.viewTask();
                    break;
                case 3:
                    System.out.print("Enter the ID of the task to remove: ");
                    taskId = scanner.nextInt();
                    toDoList.removeTask(taskId);
                    break;
                case 4:
                    System.out.print("Enter the ID of updated task: ");
                    taskId = scanner.nextInt();
                    System.out.println("Check - 1 / Uncheck - 2");
                    int check = scanner.nextInt();
                    if (check == 1){
                        toDoList.updateTaskStatus(taskId, true);
                    } else if (check == 2) {
                        toDoList.updateTaskStatus(taskId, false);
                    }
                    toDoList.saveTasksToFile("Task_Data.txt");
                    break;
                case 5:
                    toDoList.sortTasksByDueDate();
                    System.out.println("Tasks sorted by due Date:");
                    toDoList.viewTask();
                    break;
                case 6:
                    List<Task> tasksDueToday = toDoList.getTasksDueToday();
                    if (tasksDueToday.isEmpty()){
                        System.out.println("No tasks due today");
                    } else {
                        System.out.println("Tasks due today: ");
                        for (Task task: tasksDueToday) {
                            System.out.println(task.getId() + ". " + task.getDescription() + " (Due: " + task.getDueDate() + ")");
                        }
                    }
                    break;
                case 7:
                    toDoList.saveTasksToFile("Task_Data.txt");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice, try again");
            }
        }
    }
}
