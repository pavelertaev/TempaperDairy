import java.time.LocalDateTime;

public interface Repeatable {
    public  LocalDateTime getTime();
    public boolean checkTask(LocalDateTime data);

}
