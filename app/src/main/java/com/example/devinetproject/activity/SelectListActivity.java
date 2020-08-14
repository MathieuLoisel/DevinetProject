package com.example.devinetproject.activity;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;
        import androidx.lifecycle.LiveData;
        import androidx.lifecycle.Observer;
        import androidx.lifecycle.ViewModelProvider;

        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.os.Parcelable;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.AdapterView.OnItemClickListener;
        import android.widget.ListView;

        import com.example.devinetproject.R;
        import com.example.devinetproject.activity.adapter.CategoryAdapter;
        import com.example.devinetproject.bo.Category;
        import com.example.devinetproject.bo.Word;
        import com.example.devinetproject.vm.CategoryVm;
        import com.example.devinetproject.vm.WordVm;

        import java.util.ArrayList;
        import java.util.List;

public class SelectListActivity extends AppCompatActivity {

    private CategoryVm categoryVm = null;
    private ListView categoryList = null;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        setSupportActionBar(toolbar);
        categoryList = findViewById(R.id.lv_category_list);
        context = this;

        final WordVm wordVm = new ViewModelProvider(this).get(WordVm.class);

        //TODO:if progress == 100 new DialogAlert
        categoryList.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int idLevel = getIntent().getIntExtra("idLevel", -1);
                Category category = (Category)adapterView.getAdapter().getItem(i);
                int idCategory = category.getId();

                final LiveData<List<Word>> listWordsByLevelAndCategory =  wordVm.getByLevelAndCategory(idLevel, idCategory);
                listWordsByLevelAndCategory.observe(SelectListActivity.this, new Observer<List<Word>>() {
                    @Override
                    public void onChanged(List<Word> words) {
                        Intent redirectToPlay = new Intent(context, PlayActivity.class);
                        redirectToPlay.putParcelableArrayListExtra("listWords", (ArrayList<? extends Parcelable>) words);
                        startActivity(redirectToPlay);
                    }
                });
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        menu.findItem(R.id.btn_home).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.btn_main_settings :
                Intent intentSettings = new Intent(this,SettingsActivity.class);
                startActivity(intentSettings);
                return true;
            case R.id.btn_main_about_us :
                Intent intentAboutUs = new Intent(this,AboutUsActivity.class);
                startActivity(intentAboutUs);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        categoryVm = new ViewModelProvider(this).get(CategoryVm.class);

        categoryVm.get().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                categoryList.setAdapter(new CategoryAdapter(SelectListActivity.this,
                        R.layout.style_ligne_select_level_layout,
                        categories,
                        new ViewModelProvider(SelectListActivity.this).get(WordVm.class),
                        SelectListActivity.this));
            }
        });
    }
}