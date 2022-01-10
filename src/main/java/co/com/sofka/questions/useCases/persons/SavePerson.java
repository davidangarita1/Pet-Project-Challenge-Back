package co.com.sofka.questions.useCases.persons;

import co.com.sofka.questions.model.PersonDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SavePerson {
    Mono<String> apply(@Valid PersonDTO personDTO);
}
