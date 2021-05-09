package com.example.scorecard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Intent.ACTION_SEND;

public class MainActivity extends AppCompatActivity {

    int teamAPoint=0;
    int teamBPoint=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
        Function to add 1 point to team A
     */
    public void addPointToA(View V)
    {
        teamAPoint+=1;
        displayTeamAScore(teamAPoint);
    }

    /*
       Function to add 1 point to team B
    */
    public void addPointToB(View V)
    {
        teamBPoint+=1;
        displayTeamBScore(teamBPoint);
    }

    /*
       Function to subtract 1 point to team A
    */
    public void subPointFromA(View V)
    {
        teamAPoint-=1;
        if(teamAPoint<0) teamAPoint=0;
        displayTeamAScore(teamAPoint);
    }

    /*
       Function to subtract 1 point to team B
    */
    public void subPointFromB(View V)
    {
        teamBPoint-=1;
        if(teamBPoint<0) teamBPoint=0;
        displayTeamBScore(teamBPoint);
    }

    /*
        Display points for Team A
    */
    public void displayTeamAScore(int score)
    {
        TextView scoreView = (TextView) findViewById(R.id.teamAScore);
        scoreView.setText(String.valueOf(score));
    }

    /*
        Display points for Team B
    */
    public void displayTeamBScore(int score)
    {
        TextView scoreView = (TextView) findViewById(R.id.teamBScore);
        scoreView.setText(String.valueOf(score));
    }

    /*
       Reset Team Score to 0
    */
    public void resetScore(View V)
    {
        teamAPoint=0;
        teamBPoint=0;

        Context context = getApplicationContext();
        CharSequence text = "Reset !";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        displayTeamAScore(teamAPoint);
        displayTeamBScore(teamBPoint);

    }

    public void sendToClient(View V)
    {


        EditText etA = (EditText) findViewById(R.id.team_name_A);
        String teamAName = etA.getText().toString();

        EditText etB = (EditText) findViewById(R.id.team_name_B);
        String teamBName = etB.getText().toString();

        String resultOfGame="";

        if(teamAPoint>teamBPoint)
            resultOfGame = "Team "+teamAName+" won by "+(teamAPoint-teamBPoint)+" point(s) against Team "+teamBName+"\n";
        else if(teamBPoint>teamAPoint)
            resultOfGame = "Team "+teamBName+" won by "+(teamBPoint-teamAPoint)+" point(s) against Team "+teamAName+"\n";
        else
            resultOfGame = "Tie Game\n";

        resultOfGame+="Team "+teamAName+" - "+teamAPoint+" Point(s) \n";
        resultOfGame+="Team "+teamBName+" - "+teamBPoint+" Point(s) \n";
        resultOfGame+="Have a Nice Day !";


        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));//Handled by mails app only
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT,"Game Result !!");
        intent.putExtra(Intent.EXTRA_TEXT,resultOfGame);
        if(intent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(intent);
        }
    }

}