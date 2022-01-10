package co.com.sofka.questions.useCases.persons;

import co.com.sofka.questions.model.PersonDTO;
import co.com.sofka.questions.repositories.PersonRepository;
import co.com.sofka.questions.utils.MapperUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class GetPersonUseCase implements Function<String, Mono<PersonDTO>> {
    private final PersonRepository personRepository;
    private final MapperUtils mapperUtils;

    public GetPersonUseCase(PersonRepository personRepository, MapperUtils mapperUtils) {
        this.personRepository = personRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<PersonDTO> apply(String uid) {
        Objects.requireNonNull(uid, "User Id is required");
        return personRepository.findPersonByUid(uid)
                .map(mapperUtils.mapEntityToPersonDTO())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.CONFLICT)));
    }
}
