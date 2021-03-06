package miw.dasm.inigo.genderguesser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowSavedFemaleNames extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_saved_female_names);
        FirstNameStore firstNameStore = new FirstNameStore(this);
        ArrayList<FirstName> firstNames = firstNameStore.getFemaleFirstNames();
        GenderFirstNameAdapter recentFirstNameAdapter = new GenderFirstNameAdapter(this, android.R.layout.simple_spinner_dropdown_item, firstNames);
        ListView recentList = (ListView) findViewById(R.id.female_name_list);
        recentList.setAdapter(recentFirstNameAdapter);
    }
}
