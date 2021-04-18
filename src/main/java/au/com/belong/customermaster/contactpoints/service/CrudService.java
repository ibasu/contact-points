package au.com.belong.customermaster.contactpoints.service;

import au.com.belong.customermaster.contactpoints.repository.BaseJpaRepository;
import au.com.belong.customermaster.contactpoints.repository.entity.AbstractEntity;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Transactional
public abstract class CrudService<E extends AbstractEntity<ID>, ID>{

    protected abstract BaseJpaRepository<E, ID> getRepository();

    public E save(E entity){
        E saved = getRepository().saveAndFlush(entity);
        log.info("Persisted Entity with id = {}", saved.getId());

        return saved;
    }

    public List<E> readAll(){
        return getRepository().findAll();
    }
}
