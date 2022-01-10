package co.com.sofka.questions.routers;

import co.com.sofka.questions.model.PersonDTO;
import co.com.sofka.questions.useCases.persons.CreatePersonUseCase;
import co.com.sofka.questions.useCases.persons.GetPersonUseCase;
import co.com.sofka.questions.useCases.persons.UpdatePersonUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class PersonRouter {

    @Bean
    public RouterFunction<ServerResponse> getPerson(GetPersonUseCase useCase) {
        return route(
                GET("person/{uid}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.apply(
                                        request.pathVariable("uid")),
                                PersonDTO.class
                        ))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> createPerson(CreatePersonUseCase createUseCase) {
        Function<PersonDTO, Mono<ServerResponse>> executor = personDTO ->  createUseCase.apply(personDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(result));

        return route(
                POST("person/create").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(PersonDTO.class).flatMap(executor)
        );
    }

    @Bean
    public RouterFunction<ServerResponse> updatePerson(UpdatePersonUseCase useCase) {
        Function<PersonDTO, Mono<ServerResponse>> executor = personDTO ->  useCase.apply(personDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(result));

        return route(
                PUT("person/update").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(PersonDTO.class).flatMap(executor)
        );
    }
}
