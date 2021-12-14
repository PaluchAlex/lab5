package ro.ubbcluj.map.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Friendship extends Entity<Long> {
    private final Long userId1;
    private final Long userId2;
 //   Date date;


    public Friendship(long id, Long userId1, Long userId2) {
        super(id);
        this.userId1 = userId1;
        this.userId2 = userId2;
    }

    public Long getUserId1() {
        return userId1;
    }

    public Long getUserId2() {
        return userId2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Friendship that = (Friendship) o;
        return Objects.equals(userId1, userId1) && Objects.equals(userId2, that.userId2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId1, userId2);
    }

    @Override
    public String toString() {
        return "Friendship{" +
                "id=" + id +
                ", userId1=" + userId1 +
                ", userId2=" + userId2 +
                '}';
    }
}
