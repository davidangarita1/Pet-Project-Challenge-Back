package co.com.sofka.questions.repositories;

import co.com.sofka.questions.collections.Person;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface PersonRepository extends ReactiveCrudRepository<Person, String> {
    Mono<Person> findPersonByUid(String uid);
}
