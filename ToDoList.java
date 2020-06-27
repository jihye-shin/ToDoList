import java.util.Map;
import java.util.TreeMap;

public class ToDoList {
    
    private String category;
    private int lastUsedId;
    private Map<Integer,Task> tasks;
    private Map<Integer,Task> tasksDone; //TODO: Do I want this list? It will be nice if I want to show all the done tasks.

    public ToDoList (String category) {
        this.category = category;
        this.lastUsedId = 0;
        this.tasks = new TreeMap<>();
        this.tasksDone = new TreeMap<>();
    }

    public void addTask (String name) {
        //if (!this.containsTask(name)) {
            Task taskToAdd = new Task(this.lastUsedId+1, name, false);
            tasks.put(this.lastUsedId+1,taskToAdd);
            lastUsedId++;
        //}
    }

	public void deleteTaskById (int id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
        }
    }

	public void MarkAsDone(int id) {
        Task taskToMarkDone = tasks.get(id);
        taskToMarkDone.setDone(true);
        tasksDone.put(id,taskToMarkDone);
        tasks.remove(id);
    }

	public void MarkAsToDo(int id) {
        Task taskToMarkToDo = tasksDone.get(id);
        taskToMarkToDo.setDone(false);
        tasks.put(id,taskToMarkToDo);
        tasksDone.remove(id);
    }
    
    public void renameTask(int id, String newName) {
        Task taskToRename = tasks.get(id);
        taskToRename.setName(newName);
    }

    public void renameCategory(String newCategory) {
        this.category = newCategory;
    }

    private boolean containsTask(int id) {
        return tasks.containsKey(id);
    }

    private Task getTask(int id) {
        return tasks.get(id);
    }

    public String toString() {
        String s = "Category: " + this.category + "\n\n";
        s += "<To Do>\n";
        for (int id: tasks.keySet()) {
            Task t = tasks.get(id);
            s += id + ". " + t.getName() + "\n";
        }
        if (tasksDone.size() > 0) {
            s += "\n<Done>\n";
            for (int id: tasksDone.keySet()) {
                Task t = tasksDone.get(id);
                s += id + ". " + t.getName() + "\n";
            }
        }
        return s;
    }

    public static void main(String[] args) {
        ToDoList td = new ToDoList("ToDoList");
        td.addTask("Connect to Database");
        td.addTask("Make unit tests");
        td.addTask("Refactor");
        td.addTask("Make web browser UI");
        System.out.println(td);

        td.renameTask(3, "Decide if I want 2 lists in the field");
        td.deleteTaskById(1);
        td.addTask("Check if the current code works");
        System.out.println(td);

        td.MarkAsDone(5);
        System.out.println(td);
    }
}