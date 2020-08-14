package com.example.devinetproject.bo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Classe représentant un mot.
 * Un mot correspond à un mot à deviner du jeu Devinet. Il est constitué d'une image,
 * d'un mot à deviner, d'une proposition de l'utilisateur et d'une catégorie définissant la taille
 * du mot.
 */

@Entity(foreignKeys = {
        @ForeignKey(entity = Category.class,
                parentColumns = "id",
                childColumns = "idCategory",
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Level.class,
                parentColumns = "id",
                childColumns = "idLevel",
                onDelete = ForeignKey.CASCADE)
})
public class Word implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idWord")
    private int id;
    private String image;
    private String guessWord;
    private String proposal;
    private int idCategory;
    private int idLevel;

    public Word() {
    }

    @Ignore
    public Word(String image, String guessWord, String proposal, int idCategory, int idLevel) {
        this.image = image;
        this.guessWord = guessWord;
        this.proposal = proposal;
        this.idCategory = idCategory;
        this.idLevel = idLevel;
    }

    protected Word(Parcel in) {
        id = in.readInt();
        image = in.readString();
        guessWord = in.readString();
        proposal = in.readString();
        idCategory = in.readInt();
        idLevel = in.readInt();
    }

    public static final Creator<Word> CREATOR = new Creator<Word>() {
        @Override
        public Word createFromParcel(Parcel in) {
            return new Word(in);
        }

        @Override
        public Word[] newArray(int size) {
            return new Word[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public int getIdLevel() {
        return idLevel;
    }

    public void setIdLevel(int idLevel) {
        this.idLevel = idLevel;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", guessWord='" + guessWord + '\'' +
                ", proposal='" + proposal + '\'' +
                ", idCategory=" + idCategory +
                ", idLevel=" + idLevel +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(image);
        parcel.writeString(guessWord);
        parcel.writeString(proposal);
        parcel.writeInt(idCategory);
        parcel.writeInt(idLevel);
    }
}
