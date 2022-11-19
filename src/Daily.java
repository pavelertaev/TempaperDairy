import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Daily extends Task implements Repeatable {
    public Daily(String descriptionTask, String header, LocalDateTime dateOfCreation, String tipeTask) {
        super(descriptionTask, header, dateOfCreation, tipeTask);
    }
    LocalTime timeOfRepeat = LocalTime.of(getDateOfCreation().getHour(),getDateOfCreation().getMinute());

    public LocalDateTime getTime() {
        if (getDateOfCreation().isAfter(LocalDateTime.now())) {
            return getDateOfCreation();
        }
        if(timeOfRepeat.isAfter(LocalTime.now())){
            return LocalDateTime.of(LocalDate.now(),timeOfRepeat);
        }
        return LocalDateTime.of(LocalDate.now().plusDays(1),timeOfRepeat);
    }


    @Override
    public boolean checkTask(LocalDateTime data) {
        return getDateOfCreation().isBefore(data);
    }
}
