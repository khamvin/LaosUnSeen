package masterung.androidthai.in.th.laosunseen.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import masterung.androidthai.in.th.laosunseen.MainActivity;
import masterung.androidthai.in.th.laosunseen.R;
import masterung.androidthai.in.th.laosunseen.utility.MyAlert;

public class RegisterFragment extends Fragment {

    //    Explicit
    private Uri uri;
    private ImageView imageView;
    private boolean aBoolean = true;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Create Toolbar
        createToolbar();

//        Photo Controller
        photoController();


    }   // Main Class


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_register, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.itemUpload) {
            uploadProcess(); //Method t sang kuen ma eng Key lut man Click alt+enter Method kor ja sang keun ma eng


            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private void uploadProcess() {
        //control + space
        EditText nameEditText = getView().findViewById(R.id.edtname);
        EditText emailEditText = getView().findViewById(R.id.edtEmail);
        EditText passwordEditText = getView().findViewById(R.id.edtPassword);


//        Get Value From EditText   (0)commance D mun ja copy pai leauy leauy
//                                  (1)Keybod  Option+Commance + enter karn lieng code ton mun sai hai long teow eng

        String nameString = nameEditText.getText().toString().trim();
        String emailString = nameEditText.getText().toString().trim();
        String passwordString = nameEditText.getText().toString().trim();


//        Check Choose Photo
        if (aBoolean) {

//            None Choose Photo
            MyAlert myAlert = new MyAlert(getActivity());
            myAlert.normalDialog("None Choose Photo",
                    "Please Choose Photo");//To nung sue si tao tao mun mar jark karn sai "" leo mun kuen ma eng

//Shift+Clt enter ja dai pik kar eng

        } else if (nameString.isEmpty()|| emailString.isEmpty()|| passwordString.isEmpty()) {

//            Have Space
            MyAlert myAlert = new MyAlert(getActivity());
            myAlert.normalDialog("Have Space", "Please Fill All Every Blank");





        } else {
//            No space


            //alt+enter key : karn sang Method key Rut
            uploadPhotoToFirebase();




        }


    }

    private void uploadPhotoToFirebase() {




    }





    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {

            uri = data.getData();
            aBoolean = false;


            try {
                Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(uri));
                Bitmap bitmap1 = bitmap.createScaledBitmap(bitmap, 400, 300, true);
                imageView.setImageBitmap(bitmap1);


            } catch (Exception e) {
                e.printStackTrace();
            }


        } else {
            Toast.makeText(getActivity(), "Please Chose Photo", Toast.LENGTH_SHORT).show();

        }

    }

    private void photoController() {
        imageView = getView().findViewById(R.id.imvPhoto);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Please Choose App"), 1);

            }
        });
    }

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarRegister);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Register");
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle("Please Choose Photo and Fill All Blank");
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });


        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;
    }
}