import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

public class Annual extends Task implements Repeatable{
    public Annual(String descriptionTask, String header, boolean dateOfCreation, LocalDateTime tipeTask) {
        super(descriptionTask, header, tipeTask, dateOfCreation);
    }
    LocalTime timeOfRepeat = LocalTime.of(getDateOfCreation().getHour(), getDateOfCreation().getMinute());
    int repeatDayOfYear = getDateOfCreation().getDayOfYear();

    public LocalDateTime getTime() {
        if (getDateOfCreation().isAfter(LocalDateTime.now())) {
            return getDateOfCreation();
        }
        if(repeatDayOfYear==getDateOfCreation().getDayOfYear()&&timeOfRepeat.isAfter(LocalTime.now())) {
            return LocalDateTime.of(LocalDate.now(),timeOfRepeat);
        }
        if(repeatDayOfYear>LocalDate.now().getDayOfYear()){
            return LocalDateTime.of(LocalDate.now().plusDays(repeatDayOfYear-LocalDate.now().getDayOfYear()),timeOfRepeat);
        }
        if(repeatDayOfYear<LocalDate.now().getDayOfYear()){
            return LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.firstDayOfNextYear()).plusDays(repeatDayOfYear-1),timeOfRepeat);
        }
        return null;
    }

    @Override
    public boolean checkTask(LocalDateTime data) {
        if (getDateOfCreation().isAfter(data)) {
            return false;
        }
        return data.getDayOfYear()==(repeatDayOfYear);
    }

}
