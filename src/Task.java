import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public class Task {
    private String descriptionTask;
    private final  int taskId;
    private static int count=0;
    private String header;
    private LocalDateTime dateOfCreation;
    private String tipeTask;

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

    public String getTipeTask() {
        return tipeTask;
    }

    public void setTipeTask(String tipeTask) {
        this.tipeTask = tipeTask;
    }

    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDateTime dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Task(String descriptionTask, String header, LocalDateTime data,String tipeTask) {
        this.descriptionTask = descriptionTask;
        if(descriptionTask==null||descriptionTask.isEmpty()||descriptionTask.isBlank()){
            throw new RuntimeException("Описание не заполнено");
        }
        this.header = header;
        if(header==null||header.isEmpty()||header.isBlank()){
            throw new RuntimeException("Заголовок не заполнен");
        }
        dateOfCreation = data;
        this.tipeTask=tipeTask;
        if(tipeTask==null||tipeTask.isEmpty()||tipeTask.isBlank()){
            throw new RuntimeException("Тип задачи не заполнен");
        }
        taskId=count++;
    }




}

