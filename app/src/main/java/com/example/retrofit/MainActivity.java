package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.telecom.TelecomManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity {
   // private TextView textView;
    private  JsonPlaceHolder jsonPlaceHolder;
  //  private ImageView imageView;
    private RecyclerView recyclerView;
   // private PersonAdapter adapter;
    //private ArrayList<People> personList;
    private PeopleAdapter peopleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // textView = findViewById(R.id.textViewMain);
        //imageView = findViewById(R.id.ImageViewMain);
        recyclerView = findViewById(R.id.recycle_view_main);
        Gson gson = new GsonBuilder().serializeNulls().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.106//MyDatabase/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);

       // personList = new ArrayList<>();
       // personList.add(new People(1,"Akash","120123",23,"Path"));
       // personList.add(new People(1,"Akash","120123",23,"Path"));
       // personList.add(new People(4,"Abcd",))


       //  getPost();
        //getComment(2);


       // createComment();

         // privateUpdatePost();
        //updateComments();

          getPeopleFromDatabase();

         // builtRecycleView();

    }

    private void builtRecycleView(List<People> peopleList) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        peopleAdapter= new PeopleAdapter(this,peopleList);
        recyclerView.setAdapter(peopleAdapter);

    }

    private void  getPeopleFromDatabase()
    {
        Call<List<People>> call = jsonPlaceHolder.getPeople();
        call.enqueue(new Callback<List<People>>() {
            @Override
            public void onResponse(Call<List<People>> call, Response<List<People>> response) {
                if(!response.isSuccessful())
                {
                    Message.message(getApplicationContext(),"Code: "+response.code());
                }
                List<People> personListTwo = response.body();
                String data="";
                for (People person:personListTwo)
                {
                   data =  data + person.getId()+"\n"+ person.getAge()+"\n"+ person.getRoll()+"\n"+ person.getName()+"\n"+ person.getImagePath()+"\n\n";
                }
                Message.message(getApplicationContext(),data);
                builtRecycleView(personListTwo);
            }

            @Override
            public void onFailure(Call<List<People>> call, Throwable t) {
                Message.message(getApplicationContext(),t.getMessage());

            }
        });
    }
    private void setAnotherImage(String url){
      //  Glide.with(getApplicationContext()).load(url).into(imageView);

    }




    private void updateComments() {
        Comment comment = new Comment(null,3,"Akash","akash.pop.ru@gmail.com","Hello i am Akash");
        Call<Comment> commentCall = jsonPlaceHolder.putComment(3,comment);
        commentCall.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                if(!response.isSuccessful())
                {
                   // textView.setText("Code: "+response.code());
                    return;
                }

                Comment comment = response.body();
                String content= "Id: "+comment.getId()+"\n"+
                        "Post Id: "+comment.getPostId()+"\n"+
                        "Name: "+comment.getName()+"\n"+
                        "Email: "+comment.getEmail()+"\n"+
                        "Body: "+comment.getText()+"\n\n";
               // textView.append(content);
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
               // textView.setText(t.getMessage());

            }
        });

    }

    private void privateUpdatePost() {
       // Post post = new Post(1,"My Quote","I love you");
        //Call<Post> call = jsonPlaceHolder.putPost(1,post);
        Call<Post> call = jsonPlaceHolder.getPostByPathWay(2);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful())
                {
                   // textView.setText("Code: "+response.code());
                    return;
                }
                Post postResponse = response.body();
                String content= "Id: "+postResponse.getId()+"\n"+
                        "User Id: "+postResponse.getUserId()+"\n"+
                        "Title: "+postResponse.getTitle()+"\n"+
                        "Body: "+postResponse.getText()+"\n\n";
                //textView.append(content);

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                //textView.setText(t.getMessage());

            }
        });
    }

/*
    private void getPost()
    {

        Call<List<Post>> call = jsonPlaceHolder.getPostByPathWay(2);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful())
                {
                    textView.setText("Code: "+response.code());
                    return;
                }

                List<Post> posts = response.body();

                for(Post post:posts)
                {
                    String content= "Id: "+post.getId()+"\n"+
                            "User Id: "+post.getUserId()+"\n"+
                            "Title: "+post.getTitle()+"\n"+
                            "Body: "+post.getText()+"\n\n";
                    textView.append(content);
                }


            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }*/


    private void getComment(final int postId)
    {

       // Call<List<Comment>> call = jsonPlaceHolder.getComments(new Integer[]{2,3},null,null);
        Map<String,String> parameters = new HashMap<>();

        parameters.put("_sort","id");
        parameters.put("_order","desc");

         Call<List<Comment>> call = jsonPlaceHolder.getComments(parameters,new Integer[]{1,2,3});
        //Call<List<Comment>> call = jsonPlaceHolder.getComments("comments?postId=1");

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if(!response.isSuccessful())
                {
                   // textView.setText("Code: "+response.code());
                    return;
                }

                List<Comment> comments = response.body();

                for(Comment comment:comments)
                {

                        String content= "Id: "+comment.getId()+"\n"+
                                "Post Id: "+comment.getPostId()+"\n"+
                                "Name: "+comment.getName()+"\n"+
                                "Email: "+comment.getEmail()+"\n"+
                                "Body: "+comment.getText()+"\n\n";
                       // textView.append(content);

                }


            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
               // textView.setText(t.getMessage());
            }
        });
    }
    private void createComment()
    {
        Call<Comment> call = jsonPlaceHolder.createCommnts(5,"amar akash","akash.pop.ru@gmail.com","Very nice song,Love this");
        call.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {

                if(!response.isSuccessful())
                {
                   // textView.setText("Code: "+response.code());
                    return;
                }

               Comment comment = (Comment) response.body();


                    String content= "Id: "+comment.getId()+"\n"+
                            "Post Id: "+comment.getPostId()+"\n"+
                            "Name: "+comment.getName()+"\n"+
                            "Email: "+comment.getEmail()+"\n"+
                            "Body: "+comment.getText()+"\n\n";
                   // textView.append(content);


            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                     //  textView.setText(t.getMessage());
            }
        });
    }
}
