package co.com.sofka.questions.useCases.persons;

import co.com.sofka.questions.collections.Person;
import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.PersonDTO;
import co.com.sofka.questions.repositories.PersonRepository;
import co.com.sofka.questions.utils.Category;
import co.com.sofka.questions.utils.Type;
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
class CreatePersonUseCaseTest {
    @SpyBean
    private CreatePersonUseCase createPersonUseCase;

    @MockBean
    private PersonRepository repository;

    @Test
    void createPersonUseCaseTest() {

        var personDT0 = new PersonDTO("1", "123", "David", "Angarita", "david@example.com", "/profilepicture");

        var person = new Person("1", "123", "David", "Angarita", "david@example.com", "/profilepicture");

        when(repository.save(Mockito.any(Person.class))).thenReturn(Mono.just(person));

        var result = createPersonUseCase.apply(personDT0);

        Assertions.assertEquals(Objects.requireNonNull(result.block()),"1");
    }
}