package co.com.sofka.questions.useCases.persons;

import co.com.sofka.questions.collections.Person;
import co.com.sofka.questions.model.PersonDTO;
import co.com.sofka.questions.repositories.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UpdatePersonUseCaseTest {
    @SpyBean
    private UpdatePersonUseCase useCase;

    @MockBean
    private PersonRepository repository;

    @Test
    void updatePersonUseCaseTest(){

        var personDT0 = new PersonDTO("1", "123", "David", "Angarita", "david@example.com", "/profilepicture");

        var person = new Person("1", "123", "David", "Angarita", "david@example.com", "/profilepicture");

        when(repository.save(Mockito.any(Person.class))).thenReturn(Mono.just(person));

        var result = useCase.apply(personDT0);

        Assertions.assertEquals(Objects.requireNonNull(result.block()),"1");
    }
}