package com.example.retrofit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonPlaceHolder {
    @GET("posts")
    Call<List<Post>> getPosts();
   // @GET("posts/{postId}/comments")
    @GET("posts")
    Call<List<Post>> getPost(@Query("id") int id);
    @GET("comments")
    Call<List<Comment>> getComments(@Query("postId") Integer[] postId,
                                     @Query("_sort") String sort,
                                     @Query("_order") String order
                                     );
    @GET("comments")
    Call<List<Comment>> getComments(@QueryMap Map<String,String> parameters,
                                    @Query("postId") Integer[] postId);
    @GET
    Call<List<Comment>> getComments(@Url String url);
    @FormUrlEncoded
    @POST("comments")
    Call<Comment> createCommnts(@Field("postId") int postId,
                                   @Field("name") String name,
                                   @Field("email") String email,
                                   @Field("body") String body);



    @PUT("comments")
    Call<Comment> putComment(@Query("id") Integer id, @Body Comment comment);

    @GET("posts/{id}")
    Call<Post> getPostByPathWay(@Path("id") Integer id);

    @GET("myphpfile.php")
    Call<List<People>> getPeople();


}
