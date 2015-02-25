package com.redsteedstudios.logicalgame.dialogs;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.redsteedstudios.logicalgame.R;

import java.util.ArrayList;
import java.util.List;


public class SwitchAccountDialog  extends DialogFragment{


    private List<String> accountsList;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = new Dialog(getActivity());


        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialog.setContentView(R.layout.switch_account_dialog);

        accountsList=getAccounts();

        ListView accountsListView = (ListView) dialog.findViewById(R.id.accounts_list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, accountsList);

        accountsListView.setAdapter(adapter);

        return dialog;
    }


    private List<String> getAccounts(){
        List<String> result = new ArrayList<String>();

        AccountManager accountManager = AccountManager.get(getActivity().getApplicationContext());
        Account[] accounts = accountManager.getAccounts();
        for ( int i = 0; i < accounts.length; i++ )
        {
            result.add(accounts[i].name);

        }

        return  result;
    }
}
