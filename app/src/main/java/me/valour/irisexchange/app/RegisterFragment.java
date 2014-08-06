package me.valour.irisexchange.app;

import android.app.Activity;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;


public class RegisterFragment extends Fragment {

    private RegisterListener regListener;
    private EditText usernameField;
    private EditText password0Field;
    private EditText password1Field;
    private EditText emailField;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        usernameField = (EditText) view.findViewById(R.id.reg_username);
        password0Field = (EditText) view.findViewById(R.id.reg_password0);
        password1Field = (EditText) view.findViewById(R.id.reg_password1);
        emailField = (EditText) view.findViewById(R.id.reg_email);

        Button button = (Button) view.findViewById(R.id.reg_go);
        button.setOnClickListener(regListener);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        regListener = new RegisterListener();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    protected void gotToken(String token){
        (  (MainActivity)getActivity() ).setToken(token);
    }


    protected class RegisterListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {

            String password0 = password0Field.getText().toString();
            String password1 = password1Field.getText().toString();
            if(!password0.equals(password1)){
                Toast toast = Toast.makeText(view.getContext(), getString(R.string.toast_passwords_dont_match), Toast.LENGTH_SHORT);
                toast.show();
                return;
            }
            String username = usernameField.getText().toString();
            String email = emailField.getText().toString();

            if(username.isEmpty()){
                Toast toast = Toast.makeText(view.getContext(), getString(R.string.toast_not_empty,"Username"), Toast.LENGTH_SHORT);
                toast.show();
                return;
            }

            if(email.isEmpty()){
                Toast toast = Toast.makeText(view.getContext(), getString(R.string.toast_not_empty,"E-mail address"), Toast.LENGTH_SHORT);
                toast.show();
                return;
            }

            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                Toast toast = Toast.makeText(view.getContext(), getString(R.string.toast_invalid_email), Toast.LENGTH_SHORT);
                toast.show();
                return;
            }

            JsonObject requestBody = new JsonObject();
            requestBody.addProperty("email",email);
            requestBody.addProperty("username",username);
            requestBody.addProperty("password", password0);
            String url = getString(R.string.base_url)+"/register";
            Log.d("point",url);
            Ion.with(view.getContext())
                    .load(url)
                    .setJsonObjectBody(requestBody)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject jsonObject) {
                            if(jsonObject==null){
                                Log.d("point", "error");
                                return;
                            }
                            String token = jsonObject.get("token").getAsString();
                            gotToken(token);
                        }
                    });
        }
    }

}
