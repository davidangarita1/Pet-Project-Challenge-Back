package co.com.sofka.questions.model;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

public class AnswerDTO {
    private String id;
    @NotBlank
    private String userId;
    @NotBlank
    private String questionId;
    @NotBlank
    @Size(min = 15, max = 250, message = "Error de tamaño de caracteres")
    private String answer;
    private Integer position;

    public AnswerDTO() {

    }

    public AnswerDTO(String id, @NotBlank String questionId, @NotBlank String userId, @NotBlank String answer) {
        this.id = id;
        this.userId = userId;
        this.questionId = questionId;
        this.answer = answer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPosition() {
        return Optional.ofNullable(position).orElse(1);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerDTO answerDTO = (AnswerDTO) o;
        return Objects.equals(userId, answerDTO.userId) && Objects.equals(questionId, answerDTO.questionId) && Objects.equals(answer, answerDTO.answer) && Objects.equals(position, answerDTO.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, questionId, answer, position);
    }

    @Override
    public String toString() {
        return "AnswerDTO{" +
                "userId='" + userId + '\'' +
                ", questionId='" + questionId + '\'' +
                ", answer='" + answer + '\'' +
                ", position=" + position +
                '}';
    }
}
//