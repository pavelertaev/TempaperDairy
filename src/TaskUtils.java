import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TaskUtils {

        private HashMap<Integer, Task> tasks = new HashMap<>();
        private HashMap<Integer, Task> deletedTasks = new HashMap<>();

        public TaskUtils() {
        }

        public HashMap<Integer, Task> getTasks() {
            return tasks;
        }

        public HashMap<Integer, Task> getDeletedTasks() {
            return deletedTasks;
        }

        public void addTask(Task task) {
            tasks.put(task.getTaskId(), task);
        }

        //Определение типа повторяемости задач и проверка правильности заполнения
        public Task createTask(String header, String description, LocalDateTime dateOfCreation, boolean tipeTask, int repeatable) {
            return switch (repeatable) {
                case 0 -> new Single(header, description, tipeTask, dateOfCreation);
                case 1 -> new Daily(header, description, tipeTask, dateOfCreation);
                case 2 -> new Weekly(header, description, tipeTask, dateOfCreation);
                case 3 -> new Monthly(header, description, tipeTask, dateOfCreation);
                case 4 -> new Annual(header, description, tipeTask, dateOfCreation);
                default -> throw new IllegalArgumentException("Неправильно был выбран тип повторяемости задача");
            };

        }

        // Проверка по формату введенной Даты и Времени
        public LocalDateTime createDateAndTime(String date, String time) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
            LocalDate localDate = LocalDate.parse(date, dateFormatter);
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime localTime = LocalTime.parse(time, timeFormatter);
            return LocalDateTime.of(localDate, localTime);
        }

        // Проверка по формату введенного Типа задачи
        public boolean createIsWork(int isWork) {
            if (isWork == 1) {
                return true;
            }
            if (isWork == 0) {
                return false;
            }
            throw new IllegalArgumentException("Не правильно введен тип задачи!");
        }

        //Вывод всех активных задач
        public void printTasks() {
            if (tasks.isEmpty()) {
                System.out.println("Пока что ни одна задача не была создана");
                return;
            }
            for (Task task : tasks.values()) {
                System.out.println(task);
            }
        }

        public void printTasksOf(LocalDateTime date) {
            boolean haveDailyTask = false;
            for (Task task : tasks.values()) {
                if (task.isTipeTask()) {
                    haveDailyTask = true;
                    System.out.println(task);
                }
            }
            if (!haveDailyTask) {
                System.out.println("На данный день нет никаких запланированных задач");
            }
        }

        //Методы для печати "удаленных задач"
        public void printDeletedTasks() {
            if (deletedTasks.isEmpty()) {
                System.out.println("Пока что ни одна задача не удалена");
                return;
            }
            for (Task task : deletedTasks.values()) {
                System.out.println(task);
            }
        }

        //"Удаление" задачи из списка актуальных задач
        public void deleteTask(int taskId) {
            Task task = tasks.get(taskId);
            deletedTasks.put(task.getTaskId(), task);
            tasks.remove(taskId);
        }

        //Методы для изменения какого-либо параметра задачи
        public void checkTaskId(int taskId) {
            if (!tasks.containsKey(taskId)) {
                throw new IllegalArgumentException("Задачи с таким ID не существует");
            }
        }

        public void changeHeadline(int taskId, String headline) {
            tasks.get(taskId).setHeader(headline);
        }

        public void changeDescription(int taskId, String description) {
            tasks.get(taskId).setDescriptionTask(description);
        }

        public void changeDate(int taskId, LocalDateTime date) {
            LocalDate localDate = date.toLocalDate();
            LocalTime localTime = tasks.get(taskId).getDateOfCreation().toLocalTime();
            tasks.get(taskId).setDateOfCreation(LocalDateTime.of(localDate, localTime));
        }

        public void changeTime(int taskId, LocalDateTime time) {
            LocalDate localDate = tasks.get(taskId).getDateOfCreation().toLocalDate();
            LocalTime localTime = time.toLocalTime();
            tasks.get(taskId).setDateOfCreation(LocalDateTime.of(localDate, localTime));
        }

        public void changeIsWork(int taskId, boolean isWork) {
            tasks.get(taskId).setTipeTask(isWork);
        }

        //Метод для печати сортированного списка задач
        public void printSortedByDateTasks() {
            List<Task> sortedTask = new ArrayList<>(tasks.values());

            //Сортировка пузырьком листа по датам
            for (int i = sortedTask.size() - 1; i > 0; i--) {
                for (int j = 0; j < i; j++) {
                    LocalDateTime dateTime1 = getNextDateTime(sortedTask.get(j));
                    LocalDateTime dateTime2 = getNextDateTime(sortedTask.get(j + 1));
                    if (dateTime1.isAfter(dateTime2)) {
                        Task task = sortedTask.get(j);
                        sortedTask.set(j, sortedTask.get(j + 1));
                        sortedTask.set(j + 1, task);
                    }
                }
            }
            for (Task task : sortedTask) {
                System.out.println(task);
            }
        }

        //Метод необходимый для сортировки массива. Определяет ближайшую дату задачи
        private LocalDateTime getNextDateTime(Task task) {
            if (task instanceof Repeatable) {
                Repeatable repeat = (Repeatable) task;
                return repeat.getTime();
            } else {
                return task.getDateOfCreation();
            }
        }
    }
