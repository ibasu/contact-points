package au.com.belong.customermaster.contactpoints.service;

import au.com.belong.customermaster.contactpoints.aspect.LogTiming;
import au.com.belong.customermaster.contactpoints.exception.ResourceNotFoundException;
import au.com.belong.customermaster.contactpoints.model.PhoneContactPointsDto;
import au.com.belong.customermaster.contactpoints.repository.PhoneContactPointsRepository;
import au.com.belong.customermaster.contactpoints.repository.entity.PhoneContactPoints;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PhoneContactPointsService extends CrudService<PhoneContactPoints, String> {

    @Getter
    private final PhoneContactPointsRepository repository;

    @LogTiming
    public List<PhoneContactPointsDto> fetchAllContactPoints(){
        List<PhoneContactPoints> contactPoints = readAll();

        return EntityDtoMapper.map(contactPoints);
    }

    @LogTiming
    public List<PhoneContactPointsDto> fetchContactPointsByCustomer(String customerId) throws ResourceNotFoundException {
        List<PhoneContactPoints> contactPoints = findByCustomerId(customerId);

        return EntityDtoMapper.map(contactPoints);
    }

    @LogTiming
    public PhoneContactPointsDto changeState(PhoneContactPointsDto contactPointsDto) throws ResourceNotFoundException {
        List<PhoneContactPoints> contactPoints = findByCustomerId(contactPointsDto.getCustomerId());
        Optional<PhoneContactPoints> contactPoint = contactPoints.stream()
                .filter(c-> c.getContactNo() == contactPointsDto.getContactNo() && c.getContactNoCode().equals(contactPointsDto.getContactNoCode()))
                .findFirst();

        if (contactPoint.isPresent()) {
            PhoneContactPoints entity = EntityDtoMapper.mapToEntity(contactPointsDto, contactPoint.get());
            log.info("PhoneContactPoints Entity = {}", entity);
            entity = save(entity);

            return EntityDtoMapper.mapToDto.apply(entity);
        }else{
            throw new ResourceNotFoundException("No contact point found for customer id = " + contactPointsDto.getCustomerId() +
                    " having contact point number = " + contactPointsDto.getContactNoCode().concat(""+contactPointsDto.getContactNo()));
        }
    }

    private List<PhoneContactPoints> findByCustomerId(String customerId) throws ResourceNotFoundException {
        List<PhoneContactPoints> phoneContactPoints = repository.findByCustomerId(customerId);
        if (phoneContactPoints == null || phoneContactPoints.isEmpty()){
            throw new ResourceNotFoundException("No customer found with id = " + customerId);
        }

        return phoneContactPoints;
    }
}
