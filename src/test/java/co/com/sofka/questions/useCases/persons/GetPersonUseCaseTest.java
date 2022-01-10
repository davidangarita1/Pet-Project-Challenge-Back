package co.com.sofka.questions.useCases.persons;

import co.com.sofka.questions.collections.Person;
import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.PersonDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.PersonRepository;
import co.com.sofka.questions.repositories.QuestionRepository;
import co.com.sofka.questions.useCases.questions.GetUseCase;
import co.com.sofka.questions.utils.Category;
import co.com.sofka.questions.utils.Type;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GetPersonUseCaseTest {
    @MockBean
    private PersonRepository repository;
    @SpyBean
    private GetPersonUseCase useCase;

    @Test
    public void getPersonUseCaseTest(){

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
        Assertions.assertEquals(onePerson.block().getUid(), person.getUid());
    }
}