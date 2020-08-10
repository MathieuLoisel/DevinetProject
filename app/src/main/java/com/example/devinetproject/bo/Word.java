package com.example.devinetproject.bo;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * Classe représentant un mot.
 * Un mot correspond à un mot à deviner du jeu Devinet. Il est constitué d'une image,
 * d'un mot à deviner, d'une proposition de l'utilisateur et d'une catégorie définissant la taille
 * du mot.
 */

//TODO: Trouver comment utiliser Room avec des objets (ici pour stocker l'attribut category)

@Entity(foreignKeys = @ForeignKey(entity = Word.class,
                                    parentColumns = "category",
                                    childColumns = "id",
                                    onDelete = ForeignKey.CASCADE))
public class Word {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String img;
    private String guessWord;
    private String proposal;
    private Category category;

    public Word(String img, String guessWord, String proposal, Category category) {
        this.img = img;
        this.guessWord = guessWord;
        this.proposal = proposal;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getGuessWord() {
        return guessWord;
    }

    public void setGuessWord(String guessWord) {
        this.guessWord = guessWord;
    }

    public String getProposal() {
        return proposal;
    }

    public void setProposal(String proposal) {
        this.proposal = proposal;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", img='" + img + '\'' +
                ", word='" + guessWord + '\'' +
                ", proposal='" + proposal + '\'' +
                ", category=" + category +
                '}';
    }
}
