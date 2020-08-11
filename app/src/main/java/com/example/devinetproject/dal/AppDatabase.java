package com.example.devinetproject.dal;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.devinetproject.bo.Category;
import com.example.devinetproject.bo.Level;
import com.example.devinetproject.bo.Word;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Représente la BDD de l'application
 */
@Database(entities = {Word.class, Category.class, Level.class}, exportSchema = false, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    //Permet de n'avoir qu'une connexion à la BDD
    private static AppDatabase INSTANCE = null;

    /**
     * Permet de fournir une instance de WordDao aux couches supérieures
     * @return instance de WordDao
     */
    public abstract WordDao getWordDao();

    /**
     * Permet de fournir une instance de CategoryDao aux couches supérieures
     * @return instance de CategoryDao
     */
    public abstract CategoryDao getCategoryDao();

    /**
     * Permet de fournir une instance de LevelDao aux couches supérieures
     * @return instance de LevelDao
     */
    public abstract LevelDao getLevelDao();

    //Utilisation d'un ExecutorService afin de faire des appels à la BDD de manière asynchrone
    //(recommandation Google API 30)
    public static final ExecutorService databaseWriterExecutor = Executors.newFixedThreadPool(1);

    /**
     * Singleton permettant de n'avoir qu'une seule connexion à la BDD et de ne pas la recréé
     * à chaque appel
     *
     * Utilisation de synchronized pour la définition de la méthode pour qu'il n'y ai jamais
     * deux appel à cette méthode en même temps (ce qui aurait pour conséquence de créer deux
     * pool de connexion)
     *
     * @param context contexte de la classe
     * @return L'instance de la BDD
     */
    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "devinet_db")
                    .addCallback(roomFixture)
                    .build();
        }
        return INSTANCE;
    }


    /**
     * Fixtures permettant de remplir la BDD lors de la sa création (via le .addCallBack).
     */
    private static Callback roomFixture = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            AppDatabase.databaseWriterExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    WordDao WordDao = INSTANCE.getWordDao();
                    CategoryDao CategoryDao = INSTANCE.getCategoryDao();
                    LevelDao levelDao = INSTANCE.getLevelDao();

                    //Insertion de 5 catégories de mots (ex : légumes, voitures, etc...) .
                    CategoryDao.insert(
                            new Category("Légumes"),
                            new Category("Voitures"),
                            new Category("Fruits"),
                            new Category("Objets"),
                            new Category("Drapeaux")
                    );

                    //Insertion de 5 niveaux. Chaque niveau représente un nombre de
                    //lettres
                    levelDao.insert(
                            new Level("4 lettres"),
                            new Level("5 lettres"),
                            new Level("6 lettres"),
                            new Level("7 lettres"),
                            new Level("8 lettres")
                    );



                    //Insertion de 5 mots pour le moment (1 mot par catégorie)
                    WordDao.insert(
                            new Word("/img", "Trou", "", 1, 1),
                            new Word("/img", "Carte", "", 2, 2),
                            new Word("/img", "Propre", "", 3, 3),
                            new Word("/img", "Abricot", "", 4, 4),
                            new Word("/img", "Survivre", "", 5, 5)
                    );
                }
            });
        }
    };


}
