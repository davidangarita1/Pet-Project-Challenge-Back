package co.com.sofka.questions.useCases.persons;

import co.com.sofka.questions.collections.Person;
import co.com.sofka.questions.model.PersonDTO;
import co.com.sofka.questions.repositories.PersonRepository;
import co.com.sofka.questions.utils.MapperUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class UpdatePersonUseCase implements SavePerson{
    private final PersonRepository personRepository;
    private final MapperUtils mapperUtils;

    public UpdatePersonUseCase(MapperUtils mapperUtils, PersonRepository personRepository) {
        this.personRepository = personRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<String> apply(PersonDTO dto) {
        Objects.requireNonNull(dto.getId(), "Id of the person is required");
        return personRepository
                .save(mapperUtils.mapperToPerson(dto.getId()).apply(dto))
                .map(Person::getId);
    }
}
