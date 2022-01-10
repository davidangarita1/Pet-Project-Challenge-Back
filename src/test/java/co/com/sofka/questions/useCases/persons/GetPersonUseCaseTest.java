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
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Objects;


@SpringBootTest
class GetPersonUseCaseTest {
    @MockBean
    private PersonRepository repository;
    @SpyBean
    private GetPersonUseCase useCase;

    @Test
    void getPersonUseCaseTest(){

        var personDT0 = new PersonDTO("1", "123", "David", "Angarita", "david@example.com", "/profilepicture");
        var person = new Person();
        person.setId("1");
        person.setUid("123");
        person.setName("David");
        person.setLastName("Angarita");
        person.setEmail("david@example.com");
        person.setPictureURL("/profilepicture");

        Mockito.when(repository.findPersonByUid(Mockito.any(String.class))).thenReturn(Mono.just(person));

        var onePerson = useCase.apply("123");
        Assertions.assertEquals(Objects.requireNonNull(onePerson.block()).getUid(), person.getUid());
    }

    @Test
    void getPersonSwitchIfEmptyTest(){
        Mockito.when(repository.findPersonByUid(Mockito.any(String.class))).thenReturn(Mono.empty());

        Assertions.assertThrows(ResponseStatusException.class, () -> useCase.apply("123").block());
    }
}