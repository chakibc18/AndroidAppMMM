package com.example.youssef.myapplication.event.event.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

/**
 * Created by youssef on 24/12/17.
 */


public class EventModel
{
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

    private String[] geolocalisation;

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

    public EventModel(JSONObject obj){
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
            this.geolocalisation = new String[]{tmpGeoLocal.getString(0), tmpGeoLocal.getString(1)};
        }
        catch (JSONException e) {
            Log.e(this.getClass().getSimpleName(), e.toString());
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

    String setAttribute(JSONObject obj, String name){
        String res = "";
        try{
            res = obj.getString(name);
        }catch(JSONException exception){
            Log.d(this.getClass().getSimpleName(), exception.toString());
        }
        return res;
    }

    public String getCode_postal ()
    {
        return code_postal;
    }

    public void setCode_postal (String code_postal)
    {
        this.code_postal = code_postal;
    }

    public String getDate_debut_jour ()
    {
        return date_debut_jour;
    }

    public void setDate_debut_jour (String date_debut_jour)
    {
        this.date_debut_jour = date_debut_jour;
    }

    public String getAdresse ()
    {
        return adresse;
    }

    public void setAdresse (String adresse)
    {
        this.adresse = adresse;
    }

    public String getDerniere_fermeture ()
    {
        return derniere_fermeture;
    }

    public void setDerniere_fermeture (String derniere_fermeture)
    {
        this.derniere_fermeture = derniere_fermeture;
    }

    public String getAge_maximum ()
    {
        return age_maximum;
    }

    public void setAge_maximum (String age_maximum)
    {
        this.age_maximum = age_maximum;
    }

    public String getPartenaire ()
    {
        return partenaire;
    }

    public void setPartenaire (String partenaire)
    {
        this.partenaire = partenaire;
    }

    public String getVille ()
    {
        return ville;
    }

    public void setVille (String ville)
    {
        this.ville = ville;
    }

    public String getImage_source ()
    {
        return image_source;
    }

    public void setImage_source (String image_source)
    {
        this.image_source = image_source;
    }

    public String getDepartement ()
    {
        return departement;
    }

    public void setDepartement (String departement)
    {
        this.departement = departement;
    }

    public String getDescription_fr ()
    {
        return description_fr;
    }

    public void setDescription_fr (String description_fr)
    {
        this.description_fr = description_fr;
    }

    public String getTranche ()
    {
        return tranche;
    }

    public void setTranche (String tranche)
    {
        this.tranche = tranche;
    }

    public String getDate_debut ()
    {
        return date_debut;
    }

    public void setDate_debut (String date_debut)
    {
        this.date_debut = date_debut;
    }

    public String getEn_une ()
    {
        return en_une;
    }

    public void setEn_une (String en_une)
    {
        this.en_une = en_une;
    }

    public String getArrondissement ()
    {
        return arrondissement;
    }

    public void setArrondissement (String arrondissement)
    {
        this.arrondissement = arrondissement;
    }

    public String getAges ()
    {
        return ages;
    }

    public void setAges (String ages)
    {
        this.ages = ages;
    }

    public String getHoraires_detailles_fr ()
    {
        return horaires_detailles_fr;
    }

    public void setHoraires_detailles_fr (String horaires_detailles_fr)
    {
        this.horaires_detailles_fr = horaires_detailles_fr;
    }

    public String getDate_fin_mois ()
    {
        return date_fin_mois;
    }

    public void setDate_fin_mois (String date_fin_mois)
    {
        this.date_fin_mois = date_fin_mois;
    }

    public String getStatut ()
    {
        return statut;
    }

    public void setStatut (String statut)
    {
        this.statut = statut;
    }

    public String getResume_dates_fr ()
    {
        return resume_dates_fr;
    }

    public void setResume_dates_fr (String resume_dates_fr)
    {
        this.resume_dates_fr = resume_dates_fr;
    }

    public String getImage ()
    {
        return image;
    }

    public void setImage (String image)
    {
        this.image = image;
    }

    public String getHoraires_iso ()
    {
        return horaires_iso;
    }

    public void setHoraires_iso (String horaires_iso)
    {
        this.horaires_iso = horaires_iso;
    }

    public String getDate_debut_mois ()
    {
        return date_debut_mois;
    }

    public void setDate_debut_mois (String date_debut_mois)
    {
        this.date_debut_mois = date_debut_mois;
    }

    public String getDescription_longue_html_fr ()
    {
        return description_longue_html_fr;
    }

    public void setDescription_longue_html_fr (String description_longue_html_fr)
    {
        this.description_longue_html_fr = description_longue_html_fr;
    }

    public String getMots_cles_fr ()
    {
        return mots_cles_fr;
    }

    public void setMots_cles_fr (String mots_cles_fr)
    {
        this.mots_cles_fr = mots_cles_fr;
    }

    public String[] getGeolocalisation ()
    {
        return geolocalisation;
    }

    public void setGeolocalisation (String[] geolocalisation)
    {
        this.geolocalisation = geolocalisation;
    }

    public String getPays ()
    {
        return pays;
    }

    public void setPays (String pays)
    {
        this.pays = pays;
    }

    public String getDerniere_ouverture ()
    {
        return derniere_ouverture;
    }

    public void setDerniere_ouverture (String derniere_ouverture)
    {
        this.derniere_ouverture = derniere_ouverture;
    }

    public String getDate_fin_jour ()
    {
        return date_fin_jour;
    }

    public void setDate_fin_jour (String date_fin_jour)
    {
        this.date_fin_jour = date_fin_jour;
    }

    public String getRegion ()
    {
        return region;
    }

    public void setRegion (String region)
    {
        this.region = region;
    }

    public String getResume_horaires_fr ()
    {
        return resume_horaires_fr;
    }

    public void setResume_horaires_fr (String resume_horaires_fr)
    {
        this.resume_horaires_fr = resume_horaires_fr;
    }

    public String getApercu ()
    {
        return apercu;
    }

    public void setApercu (String apercu)
    {
        this.apercu = apercu;
    }

    public String getLien ()
    {
        return lien;
    }

    public void setLien (String lien)
    {
        this.lien = lien;
    }

    public String getAccessibilite_fr ()
    {
        return accessibilite_fr;
    }

    public void setAccessibilite_fr (String accessibilite_fr)
    {
        this.accessibilite_fr = accessibilite_fr;
    }

    public String getThematiques ()
    {
        return thematiques;
    }

    public void setThematiques (String thematiques)
    {
        this.thematiques = thematiques;
    }

    public String getTitre_fr ()
    {
        return titre_fr;
    }

    public void setTitre_fr (String titre_fr)
    {
        this.titre_fr = titre_fr;
    }

    public String getInscription_necessaire ()
    {
        return inscription_necessaire;
    }

    public void setInscription_necessaire (String inscription_necessaire)
    {
        this.inscription_necessaire = inscription_necessaire;
    }

    public String getIdentifiant_du_lieu ()
    {
        return identifiant_du_lieu;
    }

    public void setIdentifiant_du_lieu (String identifiant_du_lieu)
    {
        this.identifiant_du_lieu = identifiant_du_lieu;
    }

    public String getDates ()
    {
        return dates;
    }

    public void setDates (String dates)
    {
        this.dates = dates;
    }

    public String getDetail_des_conditions_fr ()
    {
        return detail_des_conditions_fr;
    }

    public void setDetail_des_conditions_fr (String detail_des_conditions_fr)
    {
        this.detail_des_conditions_fr = detail_des_conditions_fr;
    }

    public String getLien_d_inscription ()
    {
        return lien_d_inscription;
    }

    public void setLien_d_inscription (String lien_d_inscription)
    {
        this.lien_d_inscription = lien_d_inscription;
    }

    public String getDescription_longue_fr ()
    {
        return description_longue_fr;
    }

    public void setDescription_longue_fr (String description_longue_fr)
    {
        this.description_longue_fr = description_longue_fr;
    }

    public String getAnimateurs ()
    {
        return animateurs;
    }

    public void setAnimateurs (String animateurs)
    {
        this.animateurs = animateurs;
    }

    public String getNom_du_lieu ()
    {
        return nom_du_lieu;
    }

    public void setNom_du_lieu (String nom_du_lieu)
    {
        this.nom_du_lieu = nom_du_lieu;
    }

    public String getPublics_concernes ()
    {
        return publics_concernes;
    }

    public void setPublics_concernes (String publics_concernes)
    {
        this.publics_concernes = publics_concernes;
    }

    public String getSelection ()
    {
        return selection;
    }

    public void setSelection (String selection)
    {
        this.selection = selection;
    }

    public String getDerniere_date ()
    {
        return derniere_date;
    }

    public void setDerniere_date (String derniere_date)
    {
        this.derniere_date = derniere_date;
    }

    public String getDate_fin ()
    {
        return date_fin;
    }

    public void setDate_fin (String date_fin)
    {
        this.date_fin = date_fin;
    }

    public String getAge_minimum ()
    {
        return age_minimum;
    }

    public void setAge_minimum (String age_minimum)
    {
        this.age_minimum = age_minimum;
    }

    public String getType_d_animation ()
    {
        return type_d_animation;
    }

    public void setType_d_animation (String type_d_animation)
    {
        this.type_d_animation = type_d_animation;
    }

    public String getOrganisateur ()
    {
        return organisateur;
    }

    public void setOrganisateur (String organisateur)
    {
        this.organisateur = organisateur;
    }

    public String getDerniere_mise_a_jour ()
    {
        return derniere_mise_a_jour;
    }

    public void setDerniere_mise_a_jour (String derniere_mise_a_jour)
    {
        this.derniere_mise_a_jour = derniere_mise_a_jour;
    }

    public String getIdentifiant ()
    {
        return identifiant;
    }

    public void setIdentifiant (String identifiant)
    {
        this.identifiant = identifiant;
    }

    @Override
    public String toString()
    {
        return "ClassEventModel [code_postal = "+code_postal+", date_debut_jour = "+date_debut_jour+", adresse = "+adresse+", derniere_fermeture = "+derniere_fermeture+", age_maximum = "+age_maximum+", partenaire = "+partenaire+", ville = "+ville+", image_source = "+image_source+", departement = "+departement+", description_fr = "+description_fr+", tranche = "+tranche+", date_debut = "+date_debut+", en_une = "+en_une+", arrondissement = "+arrondissement+", ages = "+ages+", horaires_detailles_fr = "+horaires_detailles_fr+", date_fin_mois = "+date_fin_mois+", statut = "+statut+", resume_dates_fr = "+resume_dates_fr+", image = "+image+", horaires_iso = "+horaires_iso+", date_debut_mois = "+date_debut_mois+", description_longue_html_fr = "+description_longue_html_fr+", mots_cles_fr = "+mots_cles_fr+", geolocalisation = "+geolocalisation+", pays = "+pays+", derniere_ouverture = "+derniere_ouverture+", date_fin_jour = "+date_fin_jour+", region = "+region+", resume_horaires_fr = "+resume_horaires_fr+", apercu = "+apercu+", lien = "+lien+", accessibilite_fr = "+accessibilite_fr+", thematiques = "+thematiques+", titre_fr = "+titre_fr+", inscription_necessaire = "+inscription_necessaire+", identifiant_du_lieu = "+identifiant_du_lieu+", dates = "+dates+", detail_des_conditions_fr = "+detail_des_conditions_fr+", lien_d_inscription = "+lien_d_inscription+", description_longue_fr = "+description_longue_fr+", animateurs = "+animateurs+", nom_du_lieu = "+nom_du_lieu+", publics_concernes = "+publics_concernes+", selection = "+selection+", derniere_date = "+derniere_date+", date_fin = "+date_fin+", age_minimum = "+age_minimum+", type_d_animation = "+type_d_animation+", organisateur = "+organisateur+", derniere_mise_a_jour = "+derniere_mise_a_jour+", identifiant = "+identifiant+"]";
    }
}


