import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class Clid {
    public static void main(String[] args) {
        System.out.println("=Tasky CLI=");
        System.out.println("Supported commands:");
        printHelp();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter command: ");

        Repository repository = new Repository();

        while (scanner.hasNext()) {
            try {
                String input = scanner.next();
                Command command = Command.valueOf(input.toUpperCase());
                System.out.println(command);

                switch (command) {
                    case ADD:

                        System.out.print("Title: ");
                        String title = scanner.next();

                        System.out.print("Description: ");
                        String description = scanner.next();

                        System.out.print("Due date (d.MM.yyyy):");
                        String localDateString = scanner.next();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
                        LocalDate localDate = LocalDate.parse(localDateString, formatter);

                        System.out.print("State: ");
                        printTaskstates();
                        Taskstate state = Taskstate.valueOf(scanner.next().toUpperCase());

                        if (repository.add(title, description, localDate, state)) {
                            System.out.println(String.format("Added Task[%s] title = %s, desc = %s, dueDate = %s, state = %s", repository.allTasks().size(), title, description, localDateString, state));
                        }
                        break;
                    case READ:
                        break;
                }

            } catch (Exception e) {
                System.out.println("Retry an error occurred: " + e.getMessage());
            }
        }


//		Task t = new Task("Hello", "World");
//		repository.add(t);
//		t.setTaskstate(Taskstate.Doing);
//		t.setDesc("Second World");
//		repository.remove(t);
    }

    private static void printHelp() {
        Arrays.stream(Command.values()).forEach(command -> System.out.println(command.name().toLowerCase()));
    }

    private static void printTaskstates() {
        Arrays.stream(Taskstate.values()).forEach(state -> System.out.println(state.name().toLowerCase()));
    }

}


