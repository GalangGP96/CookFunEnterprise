package com.example.asus.cookfun.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.asus.cookfun.Adapter.ResepAdapter;
import com.example.asus.cookfun.HalamanUtama;
import com.example.asus.cookfun.Home;
import com.example.asus.cookfun.Model.GetResep;
import com.example.asus.cookfun.Model.GetUser;
import com.example.asus.cookfun.Model.User;
import com.example.asus.cookfun.R;
import com.example.asus.cookfun.Rest.ApiClient;
import com.example.asus.cookfun.Rest.ApiInterface;
import com.example.asus.cookfun.Session.SessionManagement;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentProfile extends Fragment {
    ImageView logout;
    SessionManagement sessionManagement;
    TextView nama,email;
    CircleImageView fotoProfil;
    String id;
    private List<User> dataUser;
    private ApiInterface mApiInterface;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        logout = (ImageView) view.findViewById(R.id.imgLogout);
        nama = (TextView) view.findViewById(R.id.textNamaUser);
        email = (TextView) view.findViewById(R.id.textEmailUser);
        fotoProfil = (CircleImageView) view.findViewById(R.id.imgFotoUser);

        sessionManagement = new SessionManagement(getContext());

        HashMap<String, String> user=sessionManagement.getUserInformation();
        id = user.get("id");
        nama.setText(user.get("nama"));
        Call<GetUser> getUser = mApiInterface.getUserBy(id);
        getUser.enqueue(new Callback<GetUser>() {
            @Override
            public void onResponse(Call<GetUser> call, Response<GetUser> response) {
                dataUser = response.body().getListDataUser();
                email.setText(dataUser.get(0).getEmail());
                Glide.with(getContext()).asBitmap().load(ApiClient.BASE_USER+dataUser.get(0).getPhoto()).apply(RequestOptions.placeholderOf(R.drawable.logo).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)).into(fotoProfil);
            }

            @Override
            public void onFailure(Call<GetUser> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
        email.setText("email : "+id);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManagement.logoutUser();
                Intent i = new Intent(getContext(),HalamanUtama.class);
                startActivity(i);
                getActivity().finish();
            }
        });

        return view;
    }
}
