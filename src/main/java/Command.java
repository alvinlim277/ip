public enum Command{
    bye("Exits the program."), list("Displays the current tasks"),
    mark("Marks selected task as done."), unmark("Marks selected task as undone."),
    todo("Adds a task."), deadline("Adds a task with a deadline."),
    event("Adds a task with a start and end time."), delete("Deletes selected task from list."),
    save("Saves the current task list to a file"),
    invalid("You entered an invalid command.");

    private final String desc;

    Command(String desc) {
        this.desc = desc;
    }
    @Override
    public String toString() {
        return this.name() + ": " + this.desc;
    }

}