package no.kristiania.exam.backend.entity;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Copy {


    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @NotNull
    private User ownedBy;

    @ManyToOne
    @NotNull
    private Item pokemon;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOwnedBy() {
        return ownedBy;
    }

    public void setOwnedBy(User ownedBy) {
        this.ownedBy = ownedBy;
    }

    public Item getPokemon() {
        return pokemon;
    }

    public void setPokemon(Item pokemon) {
        this.pokemon = pokemon;
    }
}
