package no.kristiania.exam.backend.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Item {

    // Using pokedexnumber as Id because every pokemon has a different one.
    @Id
    @NotNull
    private Long pokeDexId;

    // instead of title
    @NotBlank
    @Column(unique = true)
    @Size(max = 128)
    private String name;

    @NotBlank
    @Size(max = 128)
    private String type;

    @NotBlank
    @Size(max = 500)
    private String description;


    @NotNull
    private Long value;


    public Long getPokeDexId() {
        return pokeDexId;
    }

    public void setPokeDexId(Long pokeDexId) {
        this.pokeDexId = pokeDexId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
