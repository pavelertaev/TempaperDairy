import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static TaskUtils taskManager = new TaskUtils();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner); // добавление задач
                            break;
                        case 2:
                            inputDeleteTask(scanner); // удаление задач
                            break;
                        case 3:
                            inputDay(scanner); // вывод задач на определенный день
                            break;
                        case 4:
                            outputDeletedTask(scanner); // вывод удаленных задач
                            break;
                        case 5:
                            inputChange(scanner); // изменение параметров задачи
                            break;
                        case 6:
                            printSortedByDayTasks(scanner); // вывод задач сортированных по дате
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }

    //Интерфейс консоли для ввода задачи
    private static void inputTask(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Введите заголовок задачи: ");
        String taskName = scanner.nextLine();
        System.out.print("Введите описание: ");
        String description = scanner.nextLine();
        System.out.println("Введите тип задачи:\n" +
                "Если задача личная, то введите: 0 \t" +
                "Если задача рабочая, то введите: 1");
        System.out.print("Вы ввели: ");
        boolean isWork = taskManager.createIsWork(scanner.nextInt());
        System.out.println("Введите число от 0 до 4 для выбора повторяемости:\n" +
                "0. Однократня \t 1. Ежедневная \t 2. Еженедельная \t 3. Ежемесячная \t 4. Ежегодная");
        System.out.print("Вы ввели: ");
        int repeatable = scanner.nextInt();
        System.out.println("Введите дату начала задачи в формате дд.мм.гггг : ");
        String date = scanner.next();
        System.out.println("Введите время начала задачи в формате чч:мм : ");
        String time = scanner.next();
        LocalDateTime localDateTime = taskManager.createDateAndTime(date, time);
        Task task = taskManager.createTask(taskName, description, localDateTime, isWork, repeatable);
        taskManager.addTask(task);
        System.out.println("Созданная задача:");
        System.out.println(task.toString());
        System.out.print("Для выхода в меню нажмите Enter");
        scanner.nextLine();
        scanner.nextLine();
    }

    //Интерфейс консоли для удаления задачи
    public static void inputDeleteTask(Scanner scanner) {
        System.out.println("На данный момент доступны следующие задачи: ");
        taskManager.printTasks();
        if (taskManager.getTasks().isEmpty()) {
            return;
        }
        System.out.print(" Введите id доступных для удаления задач: ");
        int taskId = scanner.nextInt();
        taskManager.deleteTask(taskId);
        System.out.println("Задача успешно удалена");
        System.out.println("Для выхода в меню нажмите Enter");
        scanner.nextLine();
        scanner.nextLine();
    }

    //Интерфейс консоли для вывода списка удаленных задач
    public static void outputDeletedTask(Scanner scanner) {
        System.out.println("Список удаленных задач: ");
        taskManager.printDeletedTasks();
        if (taskManager.getDeletedTasks().isEmpty()) {
            return;
        }
        System.out.print("Для выхода в меню нажмите Enter");
        scanner.nextLine();
    }

    // интерфейс консоли для вывода всех задач на день
    public static void inputDay(Scanner scanner) {
        if (taskManager.getTasks().isEmpty()) {
            System.out.println("Ни одна задача еще не добавлена в ежедневник!");
            return;
        }
        System.out.print("Введите день в формате дд.мм.гггг: ");
        String date = scanner.next();
        LocalDateTime dateAndTime = taskManager.createDateAndTime(date, "23:59");
        System.out.println("Задачи на :" + dateAndTime.toLocalDate() + ", " + dateAndTime.toLocalDate().getDayOfWeek());
        taskManager.printTasksOf(dateAndTime);
        System.out.println("Для выхода в меню нажмите Enter");
        scanner.nextLine();
        scanner.nextLine();
    }

    // Интерфейс консоли для изменения параметров задач
    public static void inputChange(Scanner scanner) {
        System.out.print("Введите ID задачи, которую хотите отредактировать: ");
        int taskID = scanner.nextInt();
        taskManager.checkTaskId(taskID);
        System.out.println("Введите номер пункта, которых хотите изменить: ");
        System.out.println("0. Заголовок \t 1. Описание \t 2. Тип задачи \t 3. Дату выполнения \t 4. Время выполнения");
        System.out.print("Вы ввели: ");
        int numberOfChange = scanner.nextInt();
        switch (numberOfChange) {
            case 0:
                scanner.nextLine();
                System.out.print("Введите заголовок задачи: ");
                String taskName = scanner.nextLine();
                taskManager.changeHeadline(taskID, taskName);
                break;
            case 1:
                System.out.print("Введите описание: ");
                String description = scanner.nextLine();
                taskManager.changeDescription(taskID, description);
                break;
            case 2:
                System.out.println("Введите тип задачи:\n" +
                        "Если задача личная, то введите: 0 \t" +
                        "Если задача рабочая, то введите: 1");
                System.out.print("Вы ввели: ");
                boolean isWork = taskManager.createIsWork(scanner.nextInt());
                taskManager.changeIsWork(taskID, isWork);
                break;
            case 3:
                System.out.println("Введите дату начала задачи в формате дд.мм.гггг : ");
                String date = scanner.next();
                LocalDateTime localDate = taskManager.createDateAndTime(date, "00:00");
                taskManager.changeDate(taskID, localDate);
                break;
            case 4:
                System.out.println("Введите время начала задачи в формате чч:мм : ");
                String time = scanner.next();
                LocalDateTime localTime = taskManager.createDateAndTime("20.10.2020", time);
                taskManager.changeTime(taskID, localTime);
                break;
            default:
                System.out.println("Введен неправильный номер пункта!");
        }
        System.out.println("Все введенные изменения приняты. Нажмите Enter для продолжения...");
        scanner.nextLine();
        scanner.nextLine();
    }

    //Интерфейс консоли для вывода сортированных задач по дате
    public static void printSortedByDayTasks(Scanner scanner) {
        System.out.println("Задачи сортированные по дате ближайшего повторения: ");
        taskManager.printSortedByDateTasks();
        System.out.println("Нажмите Enter для продолжения...");
        scanner.nextLine();
        scanner.nextLine();

    }

    //Интерфейс консоли для вывода меню
    private static void printMenu() {
        System.out.println("" +
                "1. Добавить задачу\n" +
                "2. Удалить задачу\n" +
                "3. Получить задачи на указанный день\n" +
                "4. Получить все удаленные задачи\n" +
                "5. Редактировать задачу\n" +
                "6. Вывод всех задач, сортированных по дате\n" +
                "0. Выход");
    }
}