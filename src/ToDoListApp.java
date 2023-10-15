import java.util.Scanner;

public class ToDoListApp {
    public static void main(String[] args) {
        ToDoList toDoList = new ToDoList();
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Remove Tasks");
            System.out.println("4. Exit");
            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice){
                case 1:
                    System.out.println("Enter task description: ");
                    scanner.nextLine();
                    String description = scanner.nextLine();
                    toDoList.addTask(description);
                    break;
                case 2:
                    toDoList.viewTask();
                    break;
                case 3:
                    System.out.print("Enter the ID of the task to remove: ");
                    int taskId = scanner.nextInt();
                    toDoList.removeTask(taskId);
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice, try again");
            }
        }
    }
}
