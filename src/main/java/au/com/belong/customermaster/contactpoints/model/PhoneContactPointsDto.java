package au.com.belong.customermaster.contactpoints.model;

import au.com.belong.customermaster.contactpoints.repository.entity.PhoneContactPoints;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Version;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
//@RequiredArgsConstructor
//@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhoneContactPointsDto implements Serializable {

    private String id;

    @NotNull
    private String customerId;

    @NotNull
    private String contactNoCode;

    @NotNull
    @Digits(integer = 8, fraction = 0)
    private int contactNo;

    @NotNull
    private PhoneContactPointStatus status;

    private ZonedDateTime creationTime;

    private ZonedDateTime lastUpdatedTime;

    private String userId;

    private String userNotes;

//    public static void main(String[] args) {
//        String id="";
//        String contactNoCode="";
//        String customerId="";
//        int contactNo = 0;
//        PhoneContactPointStatus status = PhoneContactPointStatus.SURRENDER;
//        String userId = "";
//        String userNotes = "";
//        PhoneContactPointsDto input = new PhoneContactPointsDto(id, customerId, contactNoCode, contactNo, status, userId, userNotes);
//    }
}
