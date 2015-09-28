package com.example.batool2.soccerapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class SoccerList extends Activity implements View.OnClickListener{

    List<TeamDatabase> teamList = new ArrayList<TeamDatabase>();
    ListView listView;

    Button detail;
    Button addTeam;
    Button deleteTeam;
    Button findTeam;

    EditText teamName;
    EditText yearEstablished;
    EditText managerName;

    Spinner sp;

    TabHost tabHost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soccer_list);

        listView = (ListView) findViewById(R.id.listView);

        detail = (Button) findViewById(R.id.detailButton);

        addTeam = (Button) findViewById(R.id.addTeamButton);

        addTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTeams(teamName.getText().toString(),yearEstablished.getText().toString(), managerName.getText().toString());
                populateList();
                Toast.makeText(getApplicationContext(), teamName.getText().toString() + " has been added to the teams list",Toast.LENGTH_SHORT);
            }
        });

        deleteTeam = (Button) findViewById(R.id.deleteTeamButton);

        findTeam = (Button) findViewById(R.id.findTeamButton);


        teamName = (EditText) findViewById(R.id.teamNameField);
        yearEstablished = (EditText) findViewById((R.id.establishedField));
        managerName = (EditText) findViewById((R.id.managerNameField));

        tabHost = (TabHost) findViewById(R.id.tabHost);

        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("Creator");
        tabSpec.setContent(R.id.creatorTab);
        tabSpec.setIndicator("Create a team");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("Teams List");
        tabSpec.setContent(R.id.teamStoreTab);
        tabSpec.setIndicator("Teams List");
        tabHost.addTab(tabSpec);

        teamName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                addTeam.setEnabled(!teamName.getText().toString().trim().isEmpty());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void populateList()
    {
        ArrayAdapter<TeamDatabase> adapter = new TeamListAdapter();
        listView.setAdapter(adapter);
    }

    private void addTeams(String name, String established, String managerName)
    {
        teamList.add(new TeamDatabase(name, established, managerName));

    }

    @Override
    public void onClick(View v) {


    }

    public void deleteTeam(String teamName, ArrayList list) {
        if(list.contains(teamName))
        {
            teamList.remove(teamName);
        }

    }

    private class TeamListAdapter extends ArrayAdapter<TeamDatabase>
    {

        public TeamListAdapter()
        {
            super(SoccerList.this, R.layout.list_view_items, teamList);
        }
        @Override
        public View getView(int position, View view, ViewGroup parent)
        {
            if (view == null)
            {

                view = getLayoutInflater().inflate(R.layout.list_view_items, parent, false);

                TeamDatabase currentTeamList = teamList.get(position);

                TextView name = (TextView) view.findViewById(R.id.team_name);
                name.setText(currentTeamList.getTeamName());

                TextView managerName = (TextView) view.findViewById(R.id.name_of_manager);
                managerName.setText(currentTeamList.getManagerName());

                TextView yearOfEstablishment = (TextView) view.findViewById(R.id.year_of_establishment);
                yearOfEstablishment.setText(currentTeamList.getYearEstablished());

            }

            return view;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    }


