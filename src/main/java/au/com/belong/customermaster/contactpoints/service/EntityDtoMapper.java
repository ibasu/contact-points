package au.com.belong.customermaster.contactpoints.service;

import au.com.belong.customermaster.contactpoints.model.PhoneContactPointsDto;
import au.com.belong.customermaster.contactpoints.repository.entity.PhoneContactPoints;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class EntityDtoMapper {

    public static List<PhoneContactPointsDto> map(List<PhoneContactPoints> contactPoints){
        return contactPoints.stream()
                .map(mapToDto)
                .collect(Collectors.toList());
    }

    public static Function<PhoneContactPoints, PhoneContactPointsDto> mapToDto = (contactPoints) -> {
        PhoneContactPointsDto phoneContactPointsDto = new PhoneContactPointsDto();
                phoneContactPointsDto.setContactNo(contactPoints.getContactNo());
                phoneContactPointsDto.setContactNoCode(contactPoints.getContactNoCode());
                phoneContactPointsDto.setCustomerId(contactPoints.getCustomerId());
                phoneContactPointsDto.setStatus(contactPoints.getStatus());
                phoneContactPointsDto.setUserId(contactPoints.getUserId());
                phoneContactPointsDto.setUserNotes(contactPoints.getUserNotes());
                phoneContactPointsDto.setCreationTime(contactPoints.getCreationTime());
                phoneContactPointsDto.setLastUpdatedTime(contactPoints.getLastUpdatedTime());

        return phoneContactPointsDto;

    };

    public static PhoneContactPoints mapToEntity(PhoneContactPointsDto contactPointsDto, PhoneContactPoints phoneContactPoints) {
        phoneContactPoints.setContactNo(contactPointsDto.getContactNo());
        phoneContactPoints.setContactNoCode(contactPointsDto.getContactNoCode());
        phoneContactPoints.setCustomerId(contactPointsDto.getCustomerId());
        phoneContactPoints.setStatus(contactPointsDto.getStatus());
        phoneContactPoints.setUserId(contactPointsDto.getUserId());
        phoneContactPoints.setUserNotes(contactPointsDto.getUserNotes());

        return phoneContactPoints;
    };
}
