package com.example.youssef.myapplication.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by delaroy on 7/22/17.
 */

public class DbContract {

    public static final String CONTENT_AUTHORITY = "com.example.youssef.events";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    private static final String PATH_ENTRIES = "entries";

    public static class MenuEntry implements BaseColumns {

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd.events.entries";

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd.events.entry";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ENTRIES).build();

        public static final String TABLE_NAME = "events";

        public static final String COLUMN_CODE_POSTAL ="code_postal";

        public static final String COLUMN_DATE_DEBUT_JOUR ="date_debut_jour";

        public static final String COLUMN_ADRESSE ="adresse";

        public static final String COLUMN_DERNIERE_FERMETURE ="derniere_fermeture";

        public static final String COLUMN_AGE_MAXIMUM ="age_maximum";

        public static final String COLUMN_PARTENAIRE ="partenaire";

        public static final String COLUMN_VILLE ="ville";

        public static final String COLUMN_IMAGE_SOURCE ="image_source";

        public static final String COLUMN_DEPARTEMENT ="departement";

        public static final String COLUMN_DESCRIPTION_FR ="description_fr";

        public static final String COLUMN_TRANCHE ="tranche";

        public static final String COLUMN_DATE_DEBUT ="date_debut";

        public static final String COLUMN_EN_UNE ="en_une";

        public static final String COLUMN_ARRONDISSEMENT ="arrondissement";

        public static final String COLUMN_AGES ="ages";

        public static final String COLUMN_HORAIRES_DETAILLES_FR ="horaires_detailles_fr";

        public static final String COLUMN_DATE_FIN_MOIS ="date_fin_mois";

        public static final String COLUMN_STATUT ="statut";

        public static final String COLUMN_RESUME_DATES_FR ="resume_dates_fr";

        public static final String COLUMN_IMAGE ="image";

        public static final String COLUMN_HORAIRES_ISO ="horaires_iso";

        public static final String COLUMN_DATE_DEBUT_MOIS ="date_debut_mois";

        public static final String COLUMN_DESCRIPTION_LONGUE_HTML_FR ="description_longue_html_fr";

        public static final String COLUMN_MOTS_CLES_FR ="mots_cles_fr";

        public static final String COLUMN_GEOLOCALISATION = "geolocalisation";

        public static final String COLUMN_PAYS ="pays";

        public static final String COLUMN_DERNIERE_OUVERTURE ="derniere_ouverture";

        public static final String COLUMN_DATE_FIN_JOUR ="date_fin_jour";

        public static final String COLUMN_REGION ="region";

        public static final String COLUMN_RESUME_HORAIRES_FR ="resume_horaires_fr";

        public static final String COLUMN_APERCU ="apercu";

        public static final String COLUMN_LIEN ="lien";

        public static final String COLUMN_ACCESSIBILITE_FR ="accessibilite_fr";

        public static final String COLUMN_THEMATIQUES ="thematiques";

        public static final String COLUMN_TITRE_FR ="titre_fr";

        public static final String COLUMN_INSCRIPTION_NECESSAIRE ="inscription_necessaire";

        public static final String COLUMN_IDENTIFIANT_DU_LIEU ="identifiant_du_lieu";

        public static final String COLUMN_DATES ="dates";

        public static final String COLUMN_DETAIL_DES_CONDITIONS_FR ="detail_des_conditions_fr";

        public static final String COLUMN_LIEN_D_INSCRIPTION ="lien_d_inscription";

        public static final String COLUMN_DESCRIPTION_LONGUE_FR ="description_longue_fr";

        public static final String COLUMN_ANIMATEURS ="animateurs";

        public static final String COLUMN_NOM_DU_LIEU ="nom_du_lieu";

        public static final String COLUMN_PUBLICS_CONCERNES ="publics_concernes";

        public static final String COLUMN_SELECTION ="selection";

        public static final String COLUMN_DERNIERE_DATE ="derniere_date";

        public static final String COLUMN_DATE_FIN ="date_fin";

        public static final String COLUMN_AGE_MINIMUM ="age_minimum";

        public static final String COLUMN_TYPE_D_ANIMATION ="type_d_animation";

        public static final String COLUMN_ORGANISATEUR ="organisateur";

        public static final String COLUMN_DERNIERE_MISE_A_JOUR ="derniere_mise_a_jour";

        public static final String COLUMN_IDENTIFIANT ="identifiant";

        public static final String COLUMN_VOTE ="vote";

    }
}
