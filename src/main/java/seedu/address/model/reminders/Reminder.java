package seedu.address.model.reminders;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Objects;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 *  Represents a reminder.
 */

public class Reminder {

    private ObjectProperty<String> reminder;
    private ObjectProperty<Date> date;
    private ObjectProperty<Time> time;
    private ObjectProperty<Status> status;

    /**
     * Every field must be present and not null.
     */
    public Reminder(String reminder, Date date, Time time) {
        requireAllNonNull(reminder, date, time);

        this.reminder = new SimpleObjectProperty<>(reminder);
        this.date = new SimpleObjectProperty<>(date);
        this.time = new SimpleObjectProperty<>(time);
        this.status = new SimpleObjectProperty<>(new Status(date, time));
    }

    /**
     * Creates a copy of the given Reminder.
     */
    public Reminder(Reminder source) {
        this(source.getReminder(), source.getDate(), source.getTime());
    }

    public void setReminder(String reminder) {
        this.reminder.set(requireNonNull(reminder));
    }

    public ObjectProperty<String> reminderProperty() {
        return reminder;
    }

    public String getReminder() {
        return reminder.get();
    }

    public void setTime(Time time) {
        this.time.set(requireNonNull(time));
    }

    public ObjectProperty<Time> timeProperty() {
        return time;
    }

    public Time getTime() {
        return time.get();
    }

    public void setDate(Date date) {
        this.date.set(requireNonNull(date));
    }

    public ObjectProperty<Date> dateProperty() {
        return date;
    }

    public Date getDate() {
        return date.get();
    }

    public LocalDateTime getLocalDateTime() {
        return LocalDateTime.of(date.get().toLocalDate(), time.get().toLocalTime());
    }

    public boolean hasEventPast() {
        return status.get().hasEventPast();
    }

    public boolean isEventToday() {
        return status.get().isEventToday();
    }

    public boolean isEventWithinThreeDays() {
        return status.get().isEventWithinThreeDays();
    }

    public ObjectProperty<Status> statusProperty() {
        return status;
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof Reminder
                && this.getReminder().equals(((Reminder) other).getReminder())
                && this.getDate().equals(((Reminder) other).getDate())
                && this.getTime().equals(((Reminder) other).getTime()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(reminder.get(), date.get(), time.get());
    }

    @Override
    public String toString() {
        return reminder.get() + "\n" + date.get() + "\n" + time.get();
    }
}
