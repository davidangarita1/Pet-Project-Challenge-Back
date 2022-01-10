package co.com.sofka.questions.useCases.persons;

import co.com.sofka.questions.collections.Person;
import co.com.sofka.questions.model.PersonDTO;
import co.com.sofka.questions.repositories.PersonRepository;
import co.com.sofka.questions.utils.MapperUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class CreatePersonUseCase implements SavePerson{
    private final PersonRepository personRepository;
    private final MapperUtils mapperUtils;

    public CreatePersonUseCase(MapperUtils mapperUtils, PersonRepository personRepository) {
        this.personRepository = personRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<String> apply(PersonDTO newPerson) {
        return personRepository
                .save(mapperUtils.mapperToPerson(null).apply(newPerson))
                .map(Person::getId);
    }
}
