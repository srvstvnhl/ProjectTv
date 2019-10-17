package com.example.nihal.imdb;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Person_cast extends AppCompatActivity {

    TextView name;
    TextView biography;
    TextView born;
    ImageView profile;
    ImageView seeAll;
    TextView place;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_cast);



        name=findViewById(R.id.name);
        profile=findViewById(R.id.profile_image);
        born=findViewById(R.id.born);
        seeAll=findViewById(R.id.seeall);
        place=findViewById(R.id.place);
        biography=findViewById(R.id.biography);

        Intent intent1 =getIntent();
       id = intent1.getLongExtra("person_id",0);

        fetchData(id);

        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent data=new Intent(Person_cast.this,Grid_Images_Activity.class);
                data.putExtra("id",id);
                startActivity(data);
            }
        });


    }

    private void fetchData(long id) {



        // retrofit call

       // https://api.themoviedb.org/3/person/6541?api_key=240b752ab9c0cb8011560c2f8338adc1&language=en-US

        Retrofit retrofit = new Retrofit.Builder().
                                  baseUrl("https://api.themoviedb.org/3/person/").
                                  addConverterFactory(GsonConverterFactory.create()).
                                  build();

        Movie_Api movie_api =  retrofit.create(Movie_Api.class);

        Call<Person> call=movie_api.getPersonDetail(id);

        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {


                Person object = response.body();

                if(object!=null)
                {


                   // if(object.getName()!=null)
                    name.setText(object.getName()+"");

                    if(object.getBiography()!=null)
                    biography.setText(object.getBiography());

                    if(object.getBirthday()!=null)
                    born.setText(object.getBirthday());

                    if(object.getPlace_of_birth()!=null)
                    place.setText(object.getPlace_of_birth());

                    if(object.getProfile_path()!=null)
                    Picasso.with(getBaseContext()).load("https://image.tmdb.org/t/p/w300/"+object.getProfile_path()).into(profile);
                }
                else{
                    Toast.makeText(Person_cast.this, "NULL!!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                Toast.makeText(Person_cast.this,"failed to load person detail",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
