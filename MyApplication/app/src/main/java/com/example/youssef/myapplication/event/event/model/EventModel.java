package com.example.youssef.myapplication.event.event.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Classe Model d'un event
 * Created by youssef on 24/12/17.
 */


public class EventModel {
    private String code_postal;

    private String date_debut_jour;

    private String adresse;

    private String derniere_fermeture;

    private String age_maximum;

    private String partenaire;

    private String ville;

    private String image_source;

    private String departement;

    private String description_fr;

    private String tranche;

    private String date_debut;

    private String en_une;

    private String arrondissement;

    private String ages;

    private String horaires_detailles_fr;

    private String date_fin_mois;

    private String statut;

    private String resume_dates_fr;

    private String image;

    private String horaires_iso;

    private String date_debut_mois;

    private String description_longue_html_fr;

    private String mots_cles_fr;

    private String geolocalisation;

    private String pays;

    private String derniere_ouverture;

    private String date_fin_jour;

    private String region;

    private String resume_horaires_fr;

    private String apercu;

    private String lien;

    private String accessibilite_fr;

    private String thematiques;

    private String titre_fr;

    private String inscription_necessaire;

    private String identifiant_du_lieu;

    private String dates;

    private String detail_des_conditions_fr;

    private String lien_d_inscription;

    private String description_longue_fr;

    private String animateurs;

    private String nom_du_lieu;

    private String publics_concernes;

    private String selection;

    private String derniere_date;

    private String date_fin;

    private String age_minimum;

    private String type_d_animation;

    private String organisateur;

    private String derniere_mise_a_jour;

    private String identifiant;


    public EventModel(JSONObject obj) throws JSONException {
        this.code_postal = setAttribute(obj, "titre_fr");
        this.date_debut_jour = setAttribute(obj, "date_debut_jour");
        this.adresse = setAttribute(obj, "adresse");
        this.derniere_fermeture = setAttribute(obj, "derniere_fermeture");
        this.age_maximum = setAttribute(obj, "age_maximum");
        this.partenaire = setAttribute(obj, "partenaire");
        this.ville = setAttribute(obj, "ville");
        this.image_source = setAttribute(obj, "image_source");
        this.departement = setAttribute(obj, "departement");
        this.description_fr = setAttribute(obj, "description_fr");
        this.tranche = setAttribute(obj, "tranche");
        this.date_debut = setAttribute(obj, "date_debut");
        this.en_une = setAttribute(obj, "en_une");
        this.arrondissement = setAttribute(obj, "arrondissement");
        this.ages = setAttribute(obj, "ages");
        this.horaires_detailles_fr = setAttribute(obj, "horaires_detailles_fr");
        this.date_fin_mois = setAttribute(obj, "date_fin_mois");
        this.statut = setAttribute(obj, "statut");
        this.resume_dates_fr = setAttribute(obj, "resume_dates_fr");
        this.image = setAttribute(obj, "image");
        this.horaires_iso = setAttribute(obj, "horaires_iso");
        this.date_debut_mois = setAttribute(obj, "date_debut_mois");
        this.description_longue_html_fr = setAttribute(obj, "description_longue_html_fr");
        this.mots_cles_fr = setAttribute(obj, "mots_cles_fr");


        try {
            JSONArray tmpGeoLocal = obj.getJSONArray("geolocalisation");
            this.geolocalisation = tmpGeoLocal.getString(0) + ',' + tmpGeoLocal.getString(1);
        } catch (JSONException e) {
            this.geolocalisation = "";
        }
        this.pays = setAttribute(obj, "pays");
        this.derniere_ouverture = setAttribute(obj, "derniere_ouverture");
        this.date_fin_jour = setAttribute(obj, "date_fin_jour");
        this.region = setAttribute(obj, "region");
        this.resume_horaires_fr = setAttribute(obj, "resume_horaires_fr");
        this.apercu = setAttribute(obj, "apercu");
        this.lien = setAttribute(obj, "lien");
        this.accessibilite_fr = setAttribute(obj, "accessibilite_fr");
        this.thematiques = setAttribute(obj, "thematiques");
        this.titre_fr = setAttribute(obj, "titre_fr");
        this.inscription_necessaire = setAttribute(obj, "inscription_necessaire");
        this.identifiant_du_lieu = setAttribute(obj, "identifiant_du_lieu");
        this.dates = setAttribute(obj, "dates");
        this.detail_des_conditions_fr = setAttribute(obj, "detail_des_conditions_fr");
        this.lien_d_inscription = setAttribute(obj, "lien_d_inscription");
        this.description_longue_fr = setAttribute(obj, "description_longue_fr");
        this.animateurs = setAttribute(obj, "animateurs");
        this.nom_du_lieu = setAttribute(obj, "nom_du_lieu");
        this.publics_concernes = setAttribute(obj, "publics_concernes");
        this.selection = setAttribute(obj, "selection");
        this.derniere_date = setAttribute(obj, "derniere_date");
        this.date_fin = setAttribute(obj, "date_fin");
        this.age_minimum = setAttribute(obj, "age_minimum");
        this.type_d_animation = setAttribute(obj, "type_d_animation");
        this.organisateur = setAttribute(obj, "organisateur");
        this.derniere_mise_a_jour = setAttribute(obj, "derniere_mise_a_jour");
        this.identifiant = setAttribute(obj, "identifiant");
    }

    String setAttribute(JSONObject obj, String name) {
        String res = "";
        try {
            res = obj.getString(name);
        } catch (JSONException exception) {
            Log.d(this.getClass().getSimpleName(), exception.toString());
        }
        return res;
    }

    public String getCode_postal() {
        return code_postal;
    }


    public String getDate_debut_jour() {
        return date_debut_jour;
    }


    public String getAdresse() {
        return adresse;
    }

    public String getDerniere_fermeture() {
        return derniere_fermeture;
    }

    public String getAge_maximum() {
        return age_maximum;
    }

    public String getPartenaire() {
        return partenaire;
    }

    public String getVille() {
        return ville;
    }

    public String getImage_source() {
        return image_source;
    }

    public String getDepartement() {
        return departement;
    }

    public String getDescription_fr() {
        return description_fr;
    }

    public String getTranche() {
        return tranche;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public String getEn_une() {
        return en_une;
    }

    public String getArrondissement() {
        return arrondissement;
    }

    public String getAges() {
        return ages;
    }

    public String getHoraires_detailles_fr() {
        return horaires_detailles_fr;
    }

    public String getDate_fin_mois() {
        return date_fin_mois;
    }

    public String getStatut() {
        return statut;
    }

    public String getResume_dates_fr() {
        return resume_dates_fr;
    }

    public String getImage() {
        return image;
    }

    public String getHoraires_iso() {
        return horaires_iso;
    }

    public String getDate_debut_mois() {
        return date_debut_mois;
    }

    public String getDescription_longue_html_fr() {
        return description_longue_html_fr;
    }

    public String getMots_cles_fr() {
        return mots_cles_fr;
    }

    public String getGeolocalisation() {
        return geolocalisation;
    }

    public String getPays() {
        return pays;
    }

    public String getDerniere_ouverture() {
        return derniere_ouverture;
    }

    public String getDate_fin_jour() {
        return date_fin_jour;
    }

    public String getRegion() {
        return region;
    }

    public String getResume_horaires_fr() {
        return resume_horaires_fr;
    }


    public String getApercu() {
        return apercu;
    }

    public String getLien() {
        return lien;
    }

    public String getAccessibilite_fr() {
        return accessibilite_fr;
    }

    public String getThematiques() {
        return thematiques;
    }

    public String getTitre_fr() {
        return titre_fr;
    }

    public String getInscription_necessaire() {
        return inscription_necessaire;
    }


    public String getIdentifiant_du_lieu() {
        return identifiant_du_lieu;
    }

    public String getDates() {
        return dates;
    }

    public String getDetail_des_conditions_fr() {
        return detail_des_conditions_fr;
    }

    public String getLien_d_inscription() {
        return lien_d_inscription;
    }

    public String getDescription_longue_fr() {
        return description_longue_fr;
    }

    public String getAnimateurs() {
        return animateurs;
    }

    public String getNom_du_lieu() {
        return nom_du_lieu;
    }

    public String getPublics_concernes() {
        return publics_concernes;
    }


    public String getSelection() {
        return selection;
    }


    public String getDerniere_date() {
        return derniere_date;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public String getAge_minimum() {
        return age_minimum;
    }


    public String getType_d_animation() {
        return type_d_animation;
    }


    public String getOrganisateur() {
        return organisateur;
    }


    public String getDerniere_mise_a_jour() {
        return derniere_mise_a_jour;
    }


    public String getIdentifiant() {
        return identifiant;
    }


    @Override
    public String toString() {
        return "ClassEventModel [code_postal = " + code_postal + ", date_debut_jour = " + date_debut_jour + ", adresse = " + adresse + ", derniere_fermeture = " + derniere_fermeture + ", age_maximum = " + age_maximum + ", partenaire = " + partenaire + ", ville = " + ville + ", image_source = " + image_source + ", departement = " + departement + ", description_fr = " + description_fr + ", tranche = " + tranche + ", date_debut = " + date_debut + ", en_une = " + en_une + ", arrondissement = " + arrondissement + ", ages = " + ages + ", horaires_detailles_fr = " + horaires_detailles_fr + ", date_fin_mois = " + date_fin_mois + ", statut = " + statut + ", resume_dates_fr = " + resume_dates_fr + ", image = " + image + ", horaires_iso = " + horaires_iso + ", date_debut_mois = " + date_debut_mois + ", description_longue_html_fr = " + description_longue_html_fr + ", mots_cles_fr = " + mots_cles_fr + ", geolocalisation = " + geolocalisation + ", pays = " + pays + ", derniere_ouverture = " + derniere_ouverture + ", date_fin_jour = " + date_fin_jour + ", region = " + region + ", resume_horaires_fr = " + resume_horaires_fr + ", apercu = " + apercu + ", lien = " + lien + ", accessibilite_fr = " + accessibilite_fr + ", thematiques = " + thematiques + ", titre_fr = " + titre_fr + ", inscription_necessaire = " + inscription_necessaire + ", identifiant_du_lieu = " + identifiant_du_lieu + ", dates = " + dates + ", detail_des_conditions_fr = " + detail_des_conditions_fr + ", lien_d_inscription = " + lien_d_inscription + ", description_longue_fr = " + description_longue_fr + ", animateurs = " + animateurs + ", nom_du_lieu = " + nom_du_lieu + ", publics_concernes = " + publics_concernes + ", selection = " + selection + ", derniere_date = " + derniere_date + ", date_fin = " + date_fin + ", age_minimum = " + age_minimum + ", type_d_animation = " + type_d_animation + ", organisateur = " + organisateur + ", derniere_mise_a_jour = " + derniere_mise_a_jour + ", identifiant = " + identifiant + "]";
    }
}


