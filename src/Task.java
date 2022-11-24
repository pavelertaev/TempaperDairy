import java.time.LocalDateTime;

public class Task {
    private String descriptionTask;
    private final int taskId;
    private static int count = 0;
    private String header;
    private LocalDateTime dateOfCreation;
    private boolean tipeTask ;




    public String getDescriptionTask() {
        return descriptionTask;
    }

    public void setDescriptionTask(String descriptionTask) {
        this.descriptionTask = descriptionTask;
    }

    public int getTaskId() {
        return taskId;
    }

    public static int getCount() {
        return count;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public boolean isTipeTask() {
        return tipeTask;
    }

    public void setTipeTask(boolean tipeTask) {
        this.tipeTask = tipeTask;
    }

    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDateTime dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Task(String descriptionTask, String header, LocalDateTime data, boolean tipeTask) {
        this.descriptionTask = descriptionTask;
        if (descriptionTask == null || descriptionTask.isEmpty() || descriptionTask.isBlank()) {
            throw new RuntimeException("Описание не заполнено");
        }
        this.header = header;
        if (header == null || header.isEmpty() || header.isBlank()) {
            throw new RuntimeException("Заголовок не заполнен");
        }
        dateOfCreation = data;
        this.tipeTask = tipeTask;
        taskId = count++;
    }

    @Override
    public String toString() {
        String isWork;
        if (tipeTask) {
            isWork = "Рабочая";
        } else {
            isWork = "Личная";
        }
        return "Задача: " +
                "Уникальный номер = " + taskId +
                ", Заголовок = '" + header + '\'' +
                ", Описание = '" + descriptionTask + '\'' +
                ", Тип = '" + isWork + '\'' +
                ", Дата начала выполнения  = " + dateOfCreation.toLocalDate() +
                ", Время = " + dateOfCreation.toLocalTime();
    }
}


