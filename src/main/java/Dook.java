import java.util.ArrayList;
import java.util.Scanner;

public class Dook {
    public static String name = "Dook";
    public static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        greetUser();
        Scanner sc = new Scanner(System.in);
        String input;
        while (true) {
            input = sc.nextLine();

            // use a parser string input =>
            String[] tmp = input.split(" ", 2);
            String command = tmp[0];
            String body = tmp.length == 1 ? "" : tmp[1].trim();

            switch (command) {
                case ("bye"):
                    bidFarewell();
                    return;
                case ("list"):
                    displayList();
                    break;
                case ("mark"):
                    markTask(body, true);
                    break;
                case ("unmark"):
                    markTask(body, false);
                    break;
                case ("todo"):
                    addToDo(body);
                    break;
                case ("deadline"):
                    addDeadline(body);
                    break;
                case ("event"):
                    addEvent(body);
                    break;
                default:
                    printMessage("Invalid Command.");
                    break;
            }

        }
    }

    private static void addToDo(String body) {
        if (body.isBlank()) {
            printMessage(String.format("Task cannot be empty!"));
            return;
        }
        Task task = new Todo(body.trim());
        addToTaskList(task);
        printMessage(String.format("added: %s.\n Now you have %d tasks in the list", task, taskList.size()));

    }

    private static void addDeadline(String body) {
        if (body.isBlank()) {
            printMessage(String.format("Task cannot be empty!"));
            return;
        }

        String[] tmp = body.split("/by", 2);
        if (tmp.length <= 1) {
            printMessage(String.format("Usage: deadline [name] /by [time]."));
            return;
        }

        Task task = new Deadline(tmp[0].trim(), tmp[1].trim());
        addToTaskList(task);
        printMessage(String.format("added: %s.\n Now you have %d tasks in the list", task, taskList.size()));

    }

    private static void addEvent(String body) {
        if (body.isBlank()) {
            printMessage(String.format("Task cannot be empty!"));
            return;
        }
        String[] tmp1 = body.split("/from", 2);
        if (tmp1.length <= 1) {
            printMessage(String.format("Usage: event [name] /from [start] /to [end]."));
            return;
        }

        String desc = tmp1[0].trim();
        String[] tmp2 = tmp1[1].split("/to", 2);
        if (tmp2.length <= 1) {
            printMessage(String.format("Usage: event [name] /from [start] /to [end]."));
            return;
        }
        String from = tmp2[0].trim();
        String to = tmp2[1].trim();

        Task task = new Event(desc, from, to);
        addToTaskList(task);
        printMessage(String.format("added: %s.\n Now you have %d tasks in the list", task, taskList.size()));

    }
    private static void addToTaskList(Task task) {
        taskList.add(task);
    }

    private static void displayList() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            result.append(String.format("%d. %s\n", i + 1, taskList.get(i)));
        }
        printMessage(result.toString() + String.format("\nYou have %d %s in the list",
                taskList.size(),
                taskList.size() == 1 ? "task" : "tasks"));
    }

    private static void markTask(String body, boolean value) {
        int index;
        try {
            index = Integer.parseInt(body.split(" ", 2)[0]);
        } catch (NumberFormatException e) {
            printMessage(String.format("Usage: %s [task number]", value ? "mark" : "unmark"));
            return;
        }

        if (index <= 0 || index > taskList.size()) {
            printMessage("That task does not exist on the list.");
            return;
        }

        Task curr = taskList.get(index - 1);
        if (value) {
            curr.markAsDone();
        } else {
            curr.unmarkAsDone();
        }
        String message = String.format("I have marked this task as %s:\n   %s",
                value ? "done" : "not done yet", curr);
        printMessage(message);
    }


    private static void greetUser() {
        printMessage(String.format("%s here.\nWhat can I do for you?", name));
    }

    public static void printMessage(String msg) {
        printDivider();
        System.out.println(msg);
        printDivider();
    }
    public static void printDivider() {
        System.out.println("_______________________________________");
    }
    private static void bidFarewell() {
        printMessage("Goodbye.");
    }
}
