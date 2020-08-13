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
                            new Category("Drapeaux")
                    );

                    //Insertion de 5 niveaux. Chaque niveau représente un nombre de
                    //lettres
                    levelDao.insert(
                            new Level("4 lettres"),
                            new Level("5 lettres"),
                            new Level("6 lettres"),
                            new Level("7 lettres")
                    );



                    WordDao.insert(

                            //Insertion mots pour les légumes 4 lettres
                            new Word("/img", "Chou", "", 1, 1),
                            new Word("/img", "Fêve", "", 1, 1),
                            new Word("/img", "Pois", "", 1, 1),
                            new Word("/img", "Rave", "", 1, 1),

                            //Insertion mots pour les légumes 5 lettres
                            new Word("/img", "Bette", "", 1, 2),
                            new Word("/img", "Navet", "", 1, 2),
                            new Word("/img", "Radis", "", 1, 2),
                            new Word("/img", "Luffa", "", 1, 2),
                            new Word("/img", "Pépon", "", 1, 2),

                            //Insertion  mots pour les légumes 6 lettres
                            new Word("/img", "Celeri", "", 1, 3),
                            new Word("/img", "Endive", "", 1, 3),
                            new Word("/img", "Laitue", "", 1, 3),
                            new Word("/img", "Oignon", "", 1, 3),
                            new Word("/img", "Panais", "", 1, 3),
                            new Word("/img", "Patate", "", 1, 3),
                            new Word("/img", "Piment", "", 1, 3),
                            new Word("/img", "Salade", "", 1, 3),

                            //Insertion 5 mots pour les légumes 7 lettres
                            new Word("/img", "Asperge", "", 1, 4),
                            new Word("/img", "Carotte", "", 1, 4),
                            new Word("/img", "Cresson", "", 1, 4),
                            new Word("/img", "Épinard", "", 1, 4),
                            new Word("/img", "Haricot", "", 1, 4),
                            new Word("/img", "Poireau", "", 1, 4),
                            new Word("/img", "Poivron", "", 1, 4),
                            new Word("/img", "Brocoli", "", 1, 4),



                            //Insertion 4 mots pour les voitures 4 lettres
                            new Word("/img", "Alfa", "", 2, 2),
                            new Word("/img", "Audi", "", 2, 2),
                            new Word("/img", "Ford", "", 2, 2),
                            new Word("/img", "Seat", "", 2, 2),

                            //Insertion 4 mots pour les voitures 5 lettres
                            new Word("/img", "SMART  ", "", 2, 3),
                            new Word("/img", "BUICK", "", 2, 3),
                            new Word("/img", "Honda", "", 2, 3),

                            //Insertion 4 mots pour les voitures 6 lettres
                            new Word("/img", "Ferrari", "", 2, 4),

                            //Insertion 4 mots pour les voitures 7 lettres
                            new Word("/img", "Peugeot", "", 2, 4),
                            new Word("/img", "Citroën", "", 2, 4),
                            new Word("/img", "Hyundai", "", 2, 4),



                            //Insertion 4 mots pour les fruits 4 lettres
                            new Word("/img", "Cola", "", 3, 1),
                            new Word("/img", "Mûre", "", 3, 1),
                            new Word("/img", "Noix", "", 3, 1),

                             //Insertion 4 mots pour les fruits 5 lettres
                            new Word("/img", "Ataca", "", 3, 2),
                            new Word("/img", "Datte", "", 3, 2),
                            new Word("/img", "Melon", "", 3, 2),
                            new Word("/img", "Olive", "", 3, 2),

                             //Insertion 4 mots pour les fruits 6 lettres
                            new Word("/img", "Ananas", "", 3, 3),
                            new Word("/img", "Banane", "", 3, 3),
                            new Word("/img", "Cerise", "", 3, 3),
                            new Word("/img", "Golden", "", 3, 3),
                            new Word("/img", "Merise", "", 3, 3),

                             //Insertion 4 mots pour les fruits 7 lettres
                            new Word("/img", "ABRICOT", "", 3, 4),
                            new Word("/img", "GRENADE", "", 3, 4),




                            //Insertion 4 mots pour les drapeaux 4 lettres
                            new Word("/img", "SIAM", "", 4, 1),
                            new Word("/img", "CUBA", "", 4, 1),
                            new Word("/img", "MALI", "", 4, 1),
                            new Word("/img", "INDE", "", 4, 1),

                            //Insertion 4 mots pour les drapeaux 5 lettres
                            new Word("/img", "LIBYE", "", 4, 2),
                            new Word("/img", "TCHAD", "", 4, 2),
                            new Word("/img", "GABON", "", 4, 2),
                            new Word("/img", "LIBAN", "", 4, 2),

                            //Insertion 4 mots pour les drapeaux 6 lettres
                            new Word("/img", "Brésil", "", 4, 3),
                            new Word("/img", "Suisse", "", 4, 3),
                            new Word("/img", "Italie", "", 4, 3),
                            new Word("/img", "Serbie", "", 4, 3),
                            new Word("/img", "France", "", 4, 3),

                            //Insertion 4 mots pour les drapeaux 7 lettres
                            new Word("/img", "Sénégal", "", 4, 4),
                            new Word("/img", "Islande", "", 4, 4),
                            new Word("/img", "Hongrie", "", 4, 4),
                            new Word("/img", "Croatie", "", 4, 4)
                    );
                }
            });
        }
    };


}
