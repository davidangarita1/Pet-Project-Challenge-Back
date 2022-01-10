package co.com.sofka.questions.useCases.answers;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.repositories.QuestionRepository;
import co.com.sofka.questions.useCases.questions.DeleteUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeleteAnswerUseCaseTest {
    @MockBean
    private AnswerRepository repository;

    @SpyBean
    private DeleteAnswerUseCase useCase;

    @Test
    void deleteAnswerUseCaseTest(){

        var answerDTO = new AnswerDTO("1","1asd2153453", "123", "What id DDD in software");
        var answer = new Answer("1", "123", "1asd2153453", "What id DDD in software", 1);

        Mockito.when(repository.deleteById("1")).thenReturn(Mono.empty());
        Mockito.when(repository.deleteByQuestionId("1")).thenReturn(Mono.empty());

        var result = useCase.apply("1").block();
        Assertions.assertNull(result);
    }
}