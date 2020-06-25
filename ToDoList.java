import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    
    private String category;
    private List<Task> tasksToDo;
    private List<Task> tasksDone; //TODO: Do I want this list? It will be nice if I want to show all the done tasks.

    public ToDoList (String category) {
        this.category = category;
        this.tasksToDo = new ArrayList<Task>();
        this.tasksDone = new ArrayList<Task>();
    }

    public void addTask (String name) {
        if (!this.containsTask(name)) {
            Task taskToAdd = new Task(name, false);
            tasksToDo.add(taskToAdd);
        }
    }

	public void deleteTask (String name) {
        if (this.containsTask(name)) {
            Task taskToDelete = getTask(name);
            tasksToDo.remove(taskToDelete);
        }
    }

	public void MarkAsDone(String name) {
        Task taskToMarkDone = getTask(name);
        taskToMarkDone.setDone(true);
        tasksToDo.remove(taskToMarkDone);
        tasksDone.add(taskToMarkDone);
    }

	public void MarkAsToDo(String name) {
        Task taskToMarkToDo = getTask(name);
        taskToMarkToDo.setDone(false);
        tasksDone.remove(taskToMarkToDo);
        tasksToDo.add(taskToMarkToDo);
    }
    
    public void renameTask(String oldName, String newName) {
        Task taskToRename = getTask(oldName);
        taskToRename.setName(newName);
    }

    public void renameCategory(String newCategory) {
        this.category = newCategory;
    }

    private boolean containsTask(String name) {
        for (Task t: tasksToDo) {
            if (t.getName() == name) return true;
        }
        return false;
    }

    private Task getTask(String name) {
        Task taskToGet = null;
        for (Task t: tasksToDo) {
            if (t.getName() == name) taskToGet = t;
        } 
        return taskToGet;
    }

    public String toString() {
        String s = "Category: " + this.category + "\n\n";
        for (Task t: tasksToDo) {
            s = s + t.getName() + "\n";
        }
        return s;
        //TODO: Maybe I want to see the list with index numbers.
    }

    public static void main(String[] args) {
        ToDoList td = new ToDoList("ToDoList");
        td.addTask("1. Connect to Database");
        td.addTask("2. Make unit tests");
        td.addTask("3. Refactor");
        td.addTask("4. Make web browser UI");
        System.out.println(td);

        td.renameTask("3. Refactor", "3. Decide if I want 2 lists in the field");
        td.deleteTask("1. Connect to Database");
        td.addTask("5. Check if the current code works");
        System.out.println(td);

        td.MarkAsDone("5. Check if the current code works");
        System.out.println(td);
    }
}