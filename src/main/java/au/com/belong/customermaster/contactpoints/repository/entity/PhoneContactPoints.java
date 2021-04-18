package au.com.belong.customermaster.contactpoints.repository.entity;

import au.com.belong.customermaster.contactpoints.model.PhoneContactPointStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CONTACT_POINTS_PHONE")
@Getter
@Setter
//@Builder
@ToString
public class PhoneContactPoints extends AbstractEntity<String>{

    public PhoneContactPoints(){
        super();
    }

    @Id
    private String id;

    @Column(updatable = false)
    @NotNull
    private String customerId;

    @Column(updatable = false)
    @NotNull
    private String contactNoCode;

    @Column(updatable = false)
    @NotNull
    private int contactNo;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private PhoneContactPointStatus status;

    @Column
    private String userId;

    @Column
    private String userNotes;

}
