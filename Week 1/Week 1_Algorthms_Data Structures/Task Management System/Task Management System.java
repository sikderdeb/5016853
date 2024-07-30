class Task {
    int taskId;
    String taskName;
    String status;
    public Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

class TaskNode {
    Task task;
    TaskNode next;
    public TaskNode(Task task) {
        this.task = task;
        this.next = null;
    }
}

class TaskLinkedList {
    private TaskNode head;
    public void addTask(Task task) {
        TaskNode newNode = new TaskNode(task);
        if (head == null) 
            head = newNode;
        else {
            TaskNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }
    public Task searchTask(int taskId) {
        TaskNode current = head;
        while (current != null) {
            if (current.task.taskId == taskId) 
                return current.task;
            current = current.next;
        }
        return null;
    }
    public void traverseTasks() {
        TaskNode current = head;
        while (current != null) {
            System.out.println(current.task);
            current = current.next;
        }
    }
    public boolean deleteTask(int taskId) {
        if (head == null) 
            return false;

        if (head.task.taskId == taskId)
        {
            head = head.next;
            return true;
        }
        TaskNode current = head;
        while (current.next != null && current.next.task.taskId != taskId) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        TaskLinkedList taskList = new TaskLinkedList();
        taskList.addTask(new Task(1, "Task 1", "Incomplete"));
        taskList.addTask(new Task(2, "Task 2", "Incomplete"));
        taskList.addTask(new Task(3, "Task 3", "Complete"));
        System.out.println("Traversing tasks:");
        taskList.traverseTasks();
        System.out.println("\nSearching for task with ID 2:");
        Task foundTask = taskList.searchTask(2);
        if (foundTask != null) {
            System.out.println("Found: " + foundTask);
        } else {
            System.out.println("Task not found");
        }
        System.out.println("\nDeleting task with ID 2:");
        boolean isDeleted = taskList.deleteTask(2);
        System.out.println("Task deleted: " + isDeleted);
        System.out.println("\nTraversing tasks again:");
        taskList.traverseTasks();
    }
}