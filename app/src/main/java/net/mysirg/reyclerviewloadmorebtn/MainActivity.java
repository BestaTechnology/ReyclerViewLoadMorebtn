package net.mysirg.reyclerviewloadmorebtn;

import android.app.SearchManager;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.ScrollingTabContainerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;

import net.mysirg.reyclerviewloadmorebtn.Adapters.MainAdapter;
import net.mysirg.reyclerviewloadmorebtn.Models.ModelClass;

import java.nio.file.DirectoryStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    RecyclerView mRecyclerView;
    SearchView searchView;
    LinearLayoutManager manager;
    MainAdapter adapter;
    RecyclerView.Adapter mAdapter;
    ProgressBar progressBar;
    List<ModelClass> mModelclass;

 int currentitems , totalitems, scrolledItems;
 Boolean isScrolling = false;


    String[] titles = {"Android Cupcake", "Android Donut", "Android Eclair", "Android Froyo", "Android Gingerbread",
            "Android Honeycomb", "Android Ice Cream Sandwich", "Android Jelly Bean", "Android KitKat",
            "Android Lollipop", "Android Marshmallow", "Android Nougat", "Android Oreo", "Android Pie"};
    int[] images = {R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background
            , R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background
            , R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findids();


         mModelclass = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            mModelclass.add(new ModelClass(images[i], titles[i]));
            //Log.d("vivz","Data added in the list");
        }


        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MainAdapter(mModelclass);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling=true;
                    Log.d("vivz","Scrolling is listening");
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentitems= manager.getChildCount();
                totalitems = manager.getItemCount();
                scrolledItems= manager.findFirstVisibleItemPosition();
                if (isScrolling  && (currentitems+scrolledItems==totalitems)){
                    isScrolling=false;
                    datafetch();
                    Log.d("vivz","Data fetch method is called");


                }
            }
        });


    }

    private void datafetch() {
        progressBar.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 10; i++) {

                    mModelclass.add(new ModelClass(images[i], titles[i]));
                    mAdapter.notifyDataSetChanged();
                 //   progressBar.setVisibility(View.GONE);
                    //Log.d("vivz","Data added in the list");
                }

            }
        },2000);

    }


    public void findids() {

        mRecyclerView= findViewById(R.id.recyclerview_id);
        searchView= findViewById(R.id.search_view);
        progressBar= findViewById(R.id.progress_bar);
        manager= new LinearLayoutManager(this);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_serach, menu);

        MenuItem item = menu.findItem(R.id.search_view);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);


        searchView = (SearchView) item.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));


        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);



    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText){


        return false;
    }

}
