import java.time.LocalDateTime;

public class Single extends Task implements Repeatable {

    public Single(String descriptionTask, String header, boolean dateOfCreation, LocalDateTime tipeTask) {
        super(descriptionTask, header, tipeTask, dateOfCreation);

    }


    public LocalDateTime getTime() {
        return getDateOfCreation();
    }


    public boolean checkTask(LocalDateTime data ) {
        return getDateOfCreation().isBefore(data);
    }
}
