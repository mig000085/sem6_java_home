import java.util.*;

public class Book {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Set<String>> Book = new HashMap<>();

        System.out.println(
                "Введите данные для заполнения телефонной книги (формат: Имя НомерТелефона), для просмотра данных введите \"view\", для удаления данных введите \"delete\", для завершения введите 'exit':");

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            } else if (input.equals("view")) {
                for (Map.Entry<String, Set<String>> entry : Book.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
            } else if (input.equals("delete")) {
                System.out.println("Введите имя, для которого хотите удалить данные:");
                String nameToDelete = scanner.nextLine();
                Book.remove(nameToDelete);
                System.out.println("Данные для имени " + nameToDelete + " удалены.");
            } else {
                String[] parts = input.split(" ");
                if (parts.length != 2) {
                    System.out.println("Некорректный формат данных. Повторите ввод.");
                    continue;
                }

                String name = parts[0];
                String phone = parts[1];

                Book.computeIfAbsent(name, k -> new HashSet<>()).add(phone);
            }
        }

        Map<String, Set<String>> consolidatedPhoneBook = new HashMap<>();
        for (Map.Entry<String, Set<String>> entry : Book.entrySet()) {
            String name = entry.getKey();
            Set<String> phones = entry.getValue();
            consolidatedPhoneBook.computeIfAbsent(name, k -> new HashSet<>()).addAll(phones);
        }

        List<Map.Entry<String, Set<String>>> sortedEntries = new ArrayList<>(consolidatedPhoneBook.entrySet());
        sortedEntries.sort((e1, e2) -> Integer.compare(e2.getValue().size(), e1.getValue().size()));

        for (Map.Entry<String, Set<String>> entry : sortedEntries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}