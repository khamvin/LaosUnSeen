package masterung.androidthai.in.th.laosunseen.utility;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import masterung.androidthai.in.th.laosunseen.R;

public class MyAlert {

    private Context context;

    public MyAlert(Context context) {
        this.context = context;
    }

    public void  normalDialog(String titleString, String messageString ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);// code ny h nar t bor hai kod poom kub lang dai me tae hai cick ok u nar jor
        builder.setIcon(R.drawable.ic_action_alert);
        builder.setTitle(titleString);
        builder.setMessage(messageString);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();//code ni h hai popup pit pai thar click ok  

            }
        });


    }

}
