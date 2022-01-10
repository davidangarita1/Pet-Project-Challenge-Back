package co.com.sofka.questions.useCases.questions;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.repositories.QuestionRepository;
import co.com.sofka.questions.utils.Category;
import co.com.sofka.questions.utils.Type;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

@SpringBootTest
class DeleteUseCaseTest {
    @MockBean
    private AnswerRepository answerRepository;
    @MockBean
    private QuestionRepository questionRepository;

    @SpyBean
    DeleteUseCase useCase;

    @Test
    void deleteUseCaseTest(){

        var questionDT0 = new QuestionDTO("1as", "1234", "What id DDD in software", Type.OPEN, Category.SCIENCES, "Mensaje Email");

        var question = new Question("1as", "1234", "What id DDD in software",Type.OPEN, Category.SOFTWARE_DEVELOPMENT, "Mensaje Email");

        Mockito.when(questionRepository.deleteById("1as")).thenReturn(Mono.empty());
        Mockito.when(answerRepository.deleteByQuestionId("1as")).thenReturn(Mono.empty());

        var result = useCase.apply("1as").block();
        Assertions.assertNull(result);

        Mockito.verify(answerRepository, Mockito.times(1)).deleteByQuestionId("1as");
    }
}