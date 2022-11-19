import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class Weekly extends Task implements Repeatable {
    public Weekly(String descriptionTask, String header, LocalDateTime dateOfCreation, String tipeTask) {
        super(descriptionTask, header, dateOfCreation, tipeTask);
    }

    LocalTime timeOfRepeat = LocalTime.of(getDateOfCreation().getHour(), getDateOfCreation().getMinute());
    DayOfWeek dayOfWeek = getDateOfCreation().getDayOfWeek();

    public LocalDateTime getTime() {
        if (getDateOfCreation().isAfter(LocalDateTime.now())) {
            return getDateOfCreation();
        }
        if (dayOfWeek.equals(LocalDate.now().getDayOfWeek()) && timeOfRepeat.isAfter(LocalTime.now()))
        {
            return LocalDateTime.of(LocalDate.now(), timeOfRepeat);
        }
        return LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.next(dayOfWeek)), timeOfRepeat);
    }

    @Override
    public boolean checkTask(LocalDateTime data) {
        if (getDateOfCreation().isAfter(data)) {
            return false;
        }
        return data.getDayOfWeek().equals(dayOfWeek);
    }

}
