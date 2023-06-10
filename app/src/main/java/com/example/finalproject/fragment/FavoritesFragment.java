package com.example.finalproject.fragment;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalproject.Adapter.FavoriteAdapter;
import com.example.finalproject.Database.Detail;
import com.example.finalproject.Database.DetailHelper;
import com.example.finalproject.Database.MappingHelper;
import com.example.finalproject.R;

import org.w3c.dom.Text;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavoritesFragment extends Fragment {

    ArrayList<Detail> details = new ArrayList<>();
    RecyclerView rvFavorite;
    ArrayList<Detail> detail;
    TextView belum_ada;

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

        new LoadNotesAsync(getContext(), details -> {
            if (details != null){
                showCurrentDetail(details);
            } else {
                belum_ada.setVisibility(View.VISIBLE);
            }
        }).execute();

    }

    private void showCurrentDetail(ArrayList<Detail> details) {
        rvFavorite.setLayoutManager(new LinearLayoutManager(getContext()));
        if (details != null) {
            FavoriteAdapter adapter = new FavoriteAdapter(details);
            rvFavorite.setAdapter(adapter);
            belum_ada.setVisibility(View.GONE);
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
}