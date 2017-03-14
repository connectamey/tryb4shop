package com.goncalojoaocorreia.cameraruler;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;


public class InputDialog extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        //Inflate layout
        builder.setView(inflater.inflate(R.layout.dialog, null));
        //Set buttons
        builder.setPositiveButton(R.string.btn_OK, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onDialogPositiveClick(InputDialog.this);
            }
        });
        builder.setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onDialogNegativeClick(InputDialog.this);
            }
        });
        return builder.create();
    }


    public interface InputDialogListener{
        void onDialogPositiveClick(DialogFragment dialog);
        void onDialogNegativeClick(DialogFragment dialog);
    }

    InputDialogListener listener;

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try{
            listener = (InputDialogListener) activity;
        }catch(ClassCastException ex){
            throw new ClassCastException(activity.toString()
                    + " must implement InputDialogListener");
        }
    }
}
