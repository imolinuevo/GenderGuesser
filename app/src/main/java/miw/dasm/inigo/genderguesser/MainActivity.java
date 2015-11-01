package miw.dasm.inigo.genderguesser;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static final String BASE_URL = "https://montanaflynn-gender-guesser.p.mashape.com";
    static final String LOG_TAG = "DemoRetrofit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Button nameSendButton = (Button) findViewById(R.id.name_send);
        nameSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmptyEditText()) {

                    EditText nameInput = (EditText) findViewById(R.id.name_input);
                    String searchText = nameInput.getText().toString();
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    GenderGuesserApiService apiService = retrofit.create(GenderGuesserApiService.class);
                    Call<FirstName> call_async = apiService.getFirstname(searchText);
                    call_async.enqueue(new Callback<FirstName>() {
                        @Override
                        public void onResponse(Response<FirstName> response, Retrofit retrofit) {
                            FirstName firstName = response.body();
                            Log.i(LOG_TAG, firstName.toString());
                            addFirstName(firstName);
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            Log.e(LOG_TAG, t.getMessage());
                        }
                    });

                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Alert message to be shown");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            }
        });
        FirstNameStore firstNameStore = new FirstNameStore(this);
        ArrayList<FirstName> firstNames = firstNameStore.getRecentFirstNames();
        RecentFirstNameAdapter recentFirstNameAdapter = new RecentFirstNameAdapter(this, android.R.layout.simple_spinner_dropdown_item, firstNames);
        ListView recentList = (ListView) findViewById(R.id.recent_list);
        recentList.setAdapter(recentFirstNameAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.empty_all) {
            emptyAll();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_male_names) {
            Intent showSavedMaleNamesIntent = new Intent(this, ShowSavedMaleNames.class);
            startActivity(showSavedMaleNamesIntent);
        } else if (id == R.id.nav_female_names) {
            Intent showSavedFemaleNamesIntent = new Intent(this, ShowSavedFemaleNames.class);
            startActivity(showSavedFemaleNamesIntent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public boolean isEmptyEditText() {
        EditText nameInput = (EditText) findViewById(R.id.name_input);
        if(nameInput.getText().toString().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public void addFirstName(FirstName firstName) {
        EditText nameInput = (EditText) findViewById(R.id.name_input);
        FirstNameStore firstNameStore = new FirstNameStore(this);
        firstName.setId(firstNameStore.getNextId());
        if (firstName.getGender() == null) {
            firstName.setGender("unknown");
        }
        firstNameStore.addFirstName(firstName);
        ArrayList<FirstName> firstNames = firstNameStore.getRecentFirstNames();
        RecentFirstNameAdapter recentFirstNameAdapter = new RecentFirstNameAdapter(this, android.R.layout.simple_spinner_dropdown_item, firstNames);
        ListView recentList = (ListView) findViewById(R.id.recent_list);
        recentList.setAdapter(recentFirstNameAdapter);
    }

    public void emptyAll() {
        FirstNameStore firstNameStore = new FirstNameStore(this);
        firstNameStore.emptyAll();
        ArrayList<FirstName> firstNames = firstNameStore.getRecentFirstNames();
        RecentFirstNameAdapter recentFirstNameAdapter = new RecentFirstNameAdapter(this, android.R.layout.simple_spinner_dropdown_item, firstNames);
        ListView recentList = (ListView) findViewById(R.id.recent_list);
        recentList.setAdapter(recentFirstNameAdapter);
    }
}
