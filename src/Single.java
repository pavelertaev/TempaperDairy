import java.time.LocalDateTime;

public class Single extends Task implements Repeatable {

    public Single(String descriptionTask, String header, LocalDateTime dateOfCreation, String tipeTask) {
        super(descriptionTask, header, dateOfCreation, tipeTask);

    }


    public LocalDateTime getTime() {
        return getDateOfCreation();
    }


    public boolean checkTask(LocalDateTime data ) {
        return getDateOfCreation().equals(data);
    }
}
