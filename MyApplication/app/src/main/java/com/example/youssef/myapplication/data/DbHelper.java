package com.example.youssef.myapplication.data;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.youssef.myapplication.R;
import com.example.youssef.myapplication.event.event.model.EventModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 * Created by delaroy on 7/22/17.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final String TAG = DbHelper.class.getSimpleName();

    private Resources mResources;
    private static final String DATABASE_NAME = "evenements.db";
    private static final int DATABASE_VERSION = 1;
    Context context;
    SQLiteDatabase db;


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        mResources = context.getResources();

        db = this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_BUGS_TABLE = "CREATE TABLE " + DbContract.MenuEntry.TABLE_NAME + " (" +
                DbContract.MenuEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +

                DbContract.MenuEntry.COLUMN_CODE_POSTAL + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_DATE_DEBUT_JOUR + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_ADRESSE + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_DERNIERE_FERMETURE + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_AGE_MAXIMUM + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_PARTENAIRE + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_VILLE + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_IMAGE_SOURCE + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_DEPARTEMENT + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_DESCRIPTION_FR + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_TRANCHE + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_DATE_DEBUT + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_EN_UNE + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_ARRONDISSEMENT + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_AGES + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_HORAIRES_DETAILLES_FR + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_DATE_FIN_MOIS + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_STATUT + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_RESUME_DATES_FR + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_IMAGE + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_HORAIRES_ISO + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_DATE_DEBUT_MOIS + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_DESCRIPTION_LONGUE_HTML_FR + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_MOTS_CLES_FR + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_GEOLOCALISATION + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_PAYS + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_DERNIERE_OUVERTURE + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_DATE_FIN_JOUR + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_REGION + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_RESUME_HORAIRES_FR + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_APERCU + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_LIEN + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_ACCESSIBILITE_FR + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_THEMATIQUES + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_TITRE_FR + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_INSCRIPTION_NECESSAIRE + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_IDENTIFIANT_DU_LIEU + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_DATES + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_DETAIL_DES_CONDITIONS_FR + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_LIEN_D_INSCRIPTION + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_DESCRIPTION_LONGUE_FR + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_ANIMATEURS + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_NOM_DU_LIEU + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_PUBLICS_CONCERNES + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_SELECTION + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_DERNIERE_DATE + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_DATE_FIN + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_AGE_MINIMUM + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_TYPE_D_ANIMATION + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_ORGANISATEUR + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_DERNIERE_MISE_A_JOUR + " TEXT NOT NULL, " +

                DbContract.MenuEntry.COLUMN_IDENTIFIANT + " TEXT NOT NULL, " +
                DbContract.MenuEntry.COLUMN_VOTE + " TEXT NOT NULL " +

                " );";;

        Log.d(TAG, "Database Created Successfully?" );
        db.execSQL(SQL_CREATE_BUGS_TABLE);
        Log.d(TAG, "Database Created Successfully" );


        try {
            readDataToDb(db);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private void readDataToDb(SQLiteDatabase db) throws IOException, JSONException {

        try {
            String jsonDataString = readJsonDataFromFile();
            JSONArray menuItemsJsonArray = new JSONArray(jsonDataString);

            for (int i = 0; i < menuItemsJsonArray.length(); ++i) {

                JSONObject menuItemObject = menuItemsJsonArray.getJSONObject(i).getJSONObject("fields");

                EventModel eventModel = new EventModel(menuItemObject);

                ContentValues menuValues = format(eventModel);

                db.insert(DbContract.MenuEntry.TABLE_NAME, null, menuValues);

                Log.e(TAG, "Inserted Successfully " + menuValues );
            }


        } catch (JSONException e) {
            Log.e(TAG, e.getMessage(), e);
            e.printStackTrace();
        }

    }

    private String readJsonDataFromFile() throws IOException {

        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();

        try {
            String jsonDataString = null;
            inputStream = mResources.openRawResource(R.raw.menu_item);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, "UTF-8"));
            while ((jsonDataString = bufferedReader.readLine()) != null) {
                builder.append(jsonDataString);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return new String(builder);
    }


    private ContentValues format(EventModel model){
        ContentValues menuValues = new ContentValues();

        menuValues.put(DbContract.MenuEntry.COLUMN_CODE_POSTAL, model.getCode_postal());

        menuValues.put(DbContract.MenuEntry.COLUMN_DATE_DEBUT_JOUR, model.getDate_debut_jour());

        menuValues.put(DbContract.MenuEntry.COLUMN_ADRESSE, model.getAdresse()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_DERNIERE_FERMETURE, model.getDerniere_fermeture()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_AGE_MAXIMUM, model.getAge_maximum()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_PARTENAIRE, model.getPartenaire()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_VILLE, model.getVille()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_IMAGE_SOURCE, model.getImage_source()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_DEPARTEMENT, model.getDepartement()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_DESCRIPTION_FR, model.getDescription_fr()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_TRANCHE, model.getTranche()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_DATE_DEBUT, model.getDate_debut()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_EN_UNE, model.getEn_une()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_ARRONDISSEMENT, model.getArrondissement()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_AGES, model.getAges()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_HORAIRES_DETAILLES_FR, model.getHoraires_detailles_fr()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_DATE_FIN_MOIS, model.getDate_fin_mois()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_STATUT, model.getStatut()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_RESUME_DATES_FR, model.getResume_dates_fr()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_IMAGE, model.getImage()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_HORAIRES_ISO, model.getHoraires_iso()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_DATE_DEBUT_MOIS, model.getDate_debut_mois()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_DESCRIPTION_LONGUE_HTML_FR, model.getDescription_longue_html_fr()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_MOTS_CLES_FR, model.getMots_cles_fr()  );

        String geoLocalisation = model.getGeolocalisation()[0] + "," + model.getGeolocalisation()[1];
        menuValues.put(DbContract.MenuEntry.COLUMN_GEOLOCALISATION, geoLocalisation);

        menuValues.put(DbContract.MenuEntry.COLUMN_PAYS, model.getPays()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_DERNIERE_OUVERTURE, model.getDerniere_ouverture()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_DATE_FIN_JOUR, model.getDate_fin_jour()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_REGION, model.getRegion()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_RESUME_HORAIRES_FR, model.getResume_horaires_fr()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_APERCU, model.getApercu()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_LIEN, model.getLien()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_ACCESSIBILITE_FR, model.getAccessibilite_fr()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_THEMATIQUES, model.getThematiques()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_TITRE_FR, model.getTitre_fr()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_INSCRIPTION_NECESSAIRE, model.getInscription_necessaire()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_IDENTIFIANT_DU_LIEU, model.getIdentifiant_du_lieu()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_DATES, model.getDates()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_DETAIL_DES_CONDITIONS_FR, model.getDetail_des_conditions_fr()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_LIEN_D_INSCRIPTION, model.getLien_d_inscription()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_DESCRIPTION_LONGUE_FR, model.getDescription_longue_fr()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_ANIMATEURS, model.getAnimateurs()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_NOM_DU_LIEU, model.getNom_du_lieu()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_PUBLICS_CONCERNES, model.getPublics_concernes()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_SELECTION, model.getSelection()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_DERNIERE_DATE, model.getDerniere_date()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_DATE_FIN, model.getDate_fin()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_AGE_MINIMUM, model.getAge_minimum()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_TYPE_D_ANIMATION, model.getType_d_animation()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_ORGANISATEUR, model.getOrganisateur()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_DERNIERE_MISE_A_JOUR, model.getDerniere_mise_a_jour()  );

        menuValues.put(DbContract.MenuEntry.COLUMN_IDENTIFIANT, model.getIdentifiant());
        menuValues.put(DbContract.MenuEntry.COLUMN_VOTE, "false");

        return menuValues;

    }

}
