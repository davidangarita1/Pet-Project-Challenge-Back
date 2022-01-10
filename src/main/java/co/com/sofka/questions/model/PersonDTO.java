package co.com.sofka.questions.model;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class PersonDTO {
    private String id;
    @NotBlank
    private String uid;
    private String name;
    private String lastName;
    @NotBlank
    private String email;
    private String pictureURL;

    public PersonDTO() {
    }

    public PersonDTO(String id, String uid, String name, String lastName, String email, String pictureURL) {
        this.id = id;
        this.uid = uid;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.pictureURL = pictureURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return Objects.equals(id, personDTO.id) && Objects.equals(uid, personDTO.uid) && Objects.equals(name, personDTO.name) && Objects.equals(lastName, personDTO.lastName) && Objects.equals(email, personDTO.email) && Objects.equals(pictureURL, personDTO.pictureURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uid, name, lastName, email, pictureURL);
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id='" + id + '\'' +
                ", uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", pictureURL=" + pictureURL +
                '}';
    }
}
