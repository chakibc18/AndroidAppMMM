package com.example.youssef.myapplication.event.list;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.youssef.myapplication.MainActivity;
import com.example.youssef.myapplication.R;
import com.example.youssef.myapplication.data.DbContract;

/**
 * Created by youssef on 23/12/17.
 */

public class ListEvent extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = "MainActivity";
    private static final String[] PROJECTION = new String[]{
            DbContract.MenuEntry._ID,

            DbContract.MenuEntry.COLUMN_CODE_POSTAL,

            DbContract.MenuEntry.COLUMN_DATE_DEBUT_JOUR,

            DbContract.MenuEntry.COLUMN_ADRESSE,

            DbContract.MenuEntry.COLUMN_DERNIERE_FERMETURE,

            DbContract.MenuEntry.COLUMN_AGE_MAXIMUM,

            DbContract.MenuEntry.COLUMN_PARTENAIRE,

            DbContract.MenuEntry.COLUMN_VILLE,

            DbContract.MenuEntry.COLUMN_IMAGE_SOURCE,

            DbContract.MenuEntry.COLUMN_DEPARTEMENT,

            DbContract.MenuEntry.COLUMN_DESCRIPTION_FR,

            DbContract.MenuEntry.COLUMN_TRANCHE,

            DbContract.MenuEntry.COLUMN_DATE_DEBUT,

            DbContract.MenuEntry.COLUMN_EN_UNE,

            DbContract.MenuEntry.COLUMN_ARRONDISSEMENT,

            DbContract.MenuEntry.COLUMN_AGES,

            DbContract.MenuEntry.COLUMN_HORAIRES_DETAILLES_FR,

            DbContract.MenuEntry.COLUMN_DATE_FIN_MOIS,

            DbContract.MenuEntry.COLUMN_STATUT,

            DbContract.MenuEntry.COLUMN_RESUME_DATES_FR,

            DbContract.MenuEntry.COLUMN_IMAGE,

            DbContract.MenuEntry.COLUMN_HORAIRES_ISO,

            DbContract.MenuEntry.COLUMN_DATE_DEBUT_MOIS,

            DbContract.MenuEntry.COLUMN_DESCRIPTION_LONGUE_HTML_FR,

            DbContract.MenuEntry.COLUMN_MOTS_CLES_FR,

            DbContract.MenuEntry.COLUMN_GEOLOCALISATION,

            DbContract.MenuEntry.COLUMN_PAYS,

            DbContract.MenuEntry.COLUMN_DERNIERE_OUVERTURE,

            DbContract.MenuEntry.COLUMN_DATE_FIN_JOUR,

            DbContract.MenuEntry.COLUMN_REGION,

            DbContract.MenuEntry.COLUMN_RESUME_HORAIRES_FR,

            DbContract.MenuEntry.COLUMN_APERCU,

            DbContract.MenuEntry.COLUMN_LIEN,

            DbContract.MenuEntry.COLUMN_ACCESSIBILITE_FR,

            DbContract.MenuEntry.COLUMN_THEMATIQUES,

            DbContract.MenuEntry.COLUMN_TITRE_FR,

            DbContract.MenuEntry.COLUMN_INSCRIPTION_NECESSAIRE,

            DbContract.MenuEntry.COLUMN_IDENTIFIANT_DU_LIEU,

            DbContract.MenuEntry.COLUMN_DATES,

            DbContract.MenuEntry.COLUMN_DETAIL_DES_CONDITIONS_FR,

            DbContract.MenuEntry.COLUMN_LIEN_D_INSCRIPTION,

            DbContract.MenuEntry.COLUMN_DESCRIPTION_LONGUE_FR,

            DbContract.MenuEntry.COLUMN_ANIMATEURS,

            DbContract.MenuEntry.COLUMN_NOM_DU_LIEU,

            DbContract.MenuEntry.COLUMN_PUBLICS_CONCERNES,

            DbContract.MenuEntry.COLUMN_SELECTION,

            DbContract.MenuEntry.COLUMN_DERNIERE_DATE,

            DbContract.MenuEntry.COLUMN_DATE_FIN,

            DbContract.MenuEntry.COLUMN_AGE_MINIMUM,

            DbContract.MenuEntry.COLUMN_TYPE_D_ANIMATION,

            DbContract.MenuEntry.COLUMN_ORGANISATEUR,

            DbContract.MenuEntry.COLUMN_DERNIERE_MISE_A_JOUR,

            DbContract.MenuEntry.COLUMN_IDENTIFIANT

    };
    public int position = 1;
    public String filter = null;
    public Pair<String, String[]> advancedSeach;
    RecyclerView recyclerView;
    private MyAdapter adapter;
    private MainActivity mainActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.mainActivity = (MainActivity) getActivity();
        RecyclerViewClickListner listener = (View view, int pos) -> {
            position = pos;
            mainActivity.getInformation(getView());
        };

        adapter = new MyAdapter(getActivity(), listener);
        recyclerView.setAdapter(adapter);
        getActivity().getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String queryUri = DbContract.MenuEntry.CONTENT_URI.toString();

        if (advancedSeach != null) {
            return new CursorLoader(getContext(), Uri.parse(queryUri), PROJECTION, advancedSeach.first, advancedSeach.second, null);
        } else if (filter != null) {
            String filter_selection = DbContract.MenuEntry.COLUMN_TITRE_FR + " LIKE ?";
            return new CursorLoader(getContext(), Uri.parse(queryUri), PROJECTION, filter_selection, new String[]{"%" + filter + "%"}, null);
        } else
            return new CursorLoader(getContext(), Uri.parse(queryUri), PROJECTION, null, null, null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.setData(data);
        advancedSeach = null;
        filter = null;
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.setData(null);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.add(0, 0, 0, "Réinitialiser").setShortcut('3', 'c');
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case 0:
                advancedSeach = null;
                filter = null;
                getLoaderManager().restartLoader(0, null, this);
                recyclerView.scrollToPosition(0);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
