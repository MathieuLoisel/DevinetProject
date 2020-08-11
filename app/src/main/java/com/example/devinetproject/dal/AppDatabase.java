package com.example.devinetproject.dal;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.devinetproject.bo.Category;
import com.example.devinetproject.bo.CategoryConverter;
import com.example.devinetproject.bo.Word;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Représente la BDD de l'application
 */
@Database(entities = {Word.class, Category.class}, exportSchema = false, version = 1)
@TypeConverters(CategoryConverter.class)
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
                    WordDao wordDao = INSTANCE.getWordDao();
                    CategoryDao categoryDao = INSTANCE.getCategoryDao();

                    //Insertion de 5 catégories de mots. Chaque catégorie représente un nombre de
                    //lettres
                    categoryDao.insert(
                            new Category("4"),
                            new Category("5"),
                            new Category("6"),
                            new Category("7"),
                            new Category("8")
                    );

                    //Insertion de 5 mots pour le moment (1 mot par catégorie)
                    wordDao.insert(
                            new Word("/img", "Trou", "", new Category("4")),
                            new Word("/img", "Carte", "", new Category("5")),
                            new Word("/img", "Propre", "", new Category("6")),
                            new Word("/img", "Abricot", "", new Category("7")),
                            new Word("/img", "Survivre", "", new Category("8"))
                    );
                }
            });
        }
    };


}
