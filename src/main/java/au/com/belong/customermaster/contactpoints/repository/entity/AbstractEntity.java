package au.com.belong.customermaster.contactpoints.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity<ID> {

    public static final ZoneId ZULU = ZoneId.of("Zulu");
    private ZonedDateTime lastUpdatedTime = zuluNow();

    @Column(nullable = false, updatable = false)
    @NotNull
    private ZonedDateTime creationTime = lastUpdatedTime;

    @Version
    private Long version;

    private static ZonedDateTime zuluNow(){
        return ZonedDateTime.now(ZULU);
    }

    public abstract ID getId();
}
