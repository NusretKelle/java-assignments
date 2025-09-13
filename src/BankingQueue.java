import java.util.*;

public class BankingQueue {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue<Integer> queue = new LinkedList<>();
        Map<String, String> counters = new LinkedHashMap<>();
        counters.put("Counter 1", "Not assigned");
        counters.put("Counter 2", "Not assigned");
        counters.put("Counter 3", "Not assigned");
        counters.put("Counter 4", "Not assigned");

        int nextTicket = 1001;

        while (true) {
            System.out.println("\nEnter 0 to 5 for following options:");
            System.out.println("0 -> Issue new ticket number");
            System.out.println("1 -> Assign first ticket in queue to Counter 1");
            System.out.println("2 -> Assign first ticket in queue to Counter 2");
            System.out.println("3 -> Assign first ticket in queue to Counter 3");
            System.out.println("4 -> Assign first ticket in queue to Counter 4");
            System.out.println("5 -> Quit program");

            System.out.println("Tickets in queue: " + queue);
            System.out.println("Counter assignment: " + counters);
            System.out.print("Enter your option: ");

            int option = sc.nextInt();

            switch (option) {
                case 0:
                    queue.add(nextTicket++);
                    break;
                case 1: case 2: case 3: case 4:
                    if (!queue.isEmpty()) {
                        int ticket = queue.poll();
                        counters.put("Counter " + option, String.valueOf(ticket));
                    } else {
                        System.err.println("# No tickets in queue to assign!");
                    }
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    System.exit(0);
                default:
                    System.err.println("# Invalid option!");
            }
        }
    }
}
