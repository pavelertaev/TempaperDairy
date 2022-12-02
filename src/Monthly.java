import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

public class Monthly extends Task implements Repeatable {
    public Monthly(String descriptionTask, String header, boolean dateOfCreation, LocalDateTime tipeTask) {
        super(descriptionTask, header, tipeTask, dateOfCreation);
    }

    LocalTime timeOfRepeat = LocalTime.of(getDateOfCreation().getHour(), getDateOfCreation().getMinute());

    int repeatDayOfMonth = getDateOfCreation().getDayOfMonth();

    public LocalDateTime getTime() {
        if (getDateOfCreation().isAfter(LocalDateTime.now())) {
            return getDateOfCreation();
        }
        if(repeatDayOfMonth==getDateOfCreation().getDayOfMonth()&&timeOfRepeat.isAfter(LocalTime.now())) {
            return LocalDateTime.of(LocalDate.now(),timeOfRepeat);
        }
        if(repeatDayOfMonth>LocalDate.now().getDayOfMonth()){
            return LocalDateTime.of(LocalDate.now().plusDays(repeatDayOfMonth-LocalDate.now().getDayOfMonth()),timeOfRepeat);
        }
        if(repeatDayOfMonth<LocalDate.now().getDayOfMonth()){
            return LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth()).plusDays(repeatDayOfMonth-1),timeOfRepeat);
        }
        return null;
    }

    @Override
    public boolean checkTask(LocalDateTime data) {
        if (getDateOfCreation().isAfter(data)) {
            return false;
        }
        return data.getDayOfMonth()==(repeatDayOfMonth);
    }


}
