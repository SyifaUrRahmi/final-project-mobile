package com.example.finalproject.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.Adapter.FavoriteAdapter;
import com.example.finalproject.Database.Detail;
import com.example.finalproject.Database.DetailHelper;
import com.example.finalproject.Database.MappingHelper;
import com.example.finalproject.R;


import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavoritesFragment extends Fragment {
    RecyclerView rvFavorite;

    ArrayList<Detail> hasil = new ArrayList<>();
    TextView belum_ada, hapus;
    SearchView searchView;
    DetailHelper detailHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvFavorite = view.findViewById(R.id.rv_fav);
        rvFavorite.setHasFixedSize(true);

        belum_ada = view.findViewById(R.id.belum_ada);
        hapus = view.findViewById(R.id.hapus);

        detailHelper = DetailHelper.getInstance(getContext());
        detailHelper.open();

        new LoadNotesAsync(getContext(), details -> {
            showCurrentDetail(details);
        }).execute();

        searchView = view.findViewById(R.id.cari_);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchData(s);
                return true;
            }
        });

        hapus.setOnClickListener(view1 -> {
            onBackPressed();
        });
    }

    private void searchData(String text) {
        if (!TextUtils.isEmpty(text)) {
            hasil = detailHelper.cari(text);
            if(hasil != null){
                belum_ada.setText("Data yang Anda cari tidak ditemukan");
                showCurrentDetail(hasil);
            }else{
                belum_ada.setText("Anda belum menyukai film apapun");
            }
        } else {
            belum_ada.setText("Anda belum menyukai film apapun");
            new LoadNotesAsync(getContext(), notes -> {
                showCurrentDetail(notes);
            }).execute();
        }
    }

    private void showCurrentDetail(ArrayList<Detail> details) {
        if (!details.isEmpty()) {
            rvFavorite.setVisibility(View.VISIBLE);
            belum_ada.setVisibility(View.GONE);
            hapus.setVisibility(View.VISIBLE);
            rvFavorite.setLayoutManager(new LinearLayoutManager(getContext()));
            FavoriteAdapter adapter = new FavoriteAdapter(details);
            rvFavorite.setAdapter(adapter);
        } else {
            belum_ada.setVisibility(View.VISIBLE);
            hapus.setVisibility(View.GONE);
            rvFavorite.setVisibility(View.GONE);
        }
    }

    public static class LoadNotesAsync {
        private final WeakReference<Context> weakContext;
        private final WeakReference<LoadNotesCallback> weakCallback;

        private LoadNotesAsync(Context context, LoadNotesCallback callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }
        void execute() {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());
            executor.execute(() -> {
                Context context = weakContext.get();
                DetailHelper detailHelper = DetailHelper.getInstance(context);
                detailHelper.open();
                Cursor notesCursor = detailHelper.queryAll();
                ArrayList<Detail> details1 =
                        MappingHelper.mapCursorToArrayList(notesCursor);
                detailHelper.close();
                if (details1 != null){
                    handler.post(() -> weakCallback.get().postExecute(details1));
                }
            });
        }

    }

    interface LoadNotesCallback {
        void postExecute(ArrayList<Detail> details);
    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Apakah Anda yakin?");

        builder.setTitle("Alert!");
        builder.setCancelable(false);
        builder.setPositiveButton("Ya", (DialogInterface.OnClickListener) (dialog, which) -> {
            detailHelper.hapus();
            hapus.setVisibility(View.GONE);
            Toast.makeText(getContext(), "Berhasil Menghapus semua data dari Favorit", Toast.LENGTH_SHORT).show();
            FavoritesFragment favoritesFragment = new FavoritesFragment();
            FragmentManager fragmentManager = getParentFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, favoritesFragment,
                            FavoritesFragment.class.getSimpleName())
                    .addToBackStack(null)
                    .commit();
        dialog.cancel();
        });
        builder.setNegativeButton("Tidak", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}