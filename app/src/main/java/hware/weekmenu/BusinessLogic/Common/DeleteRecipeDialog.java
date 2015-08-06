package hware.weekmenu.BusinessLogic.Common;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import hware.weekmenu.BusinessLogic.Managers.RecipeManager;
import hware.weekmenu.R;

/**
 * Week Menu dialog manager
 */
public class DeleteRecipeDialog extends DialogFragment
{
    int RecipeId;

    public DeleteRecipeDialog()
    {

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        RecipeId = getArguments().getInt(Environment.Activities.RecipeID);

        builder.setMessage(R.string.DeleteRecipeDialogTilte)
                .setPositiveButton(R.string.DeleteRecipeDialogOk, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        RecipeManager recipeManager = new RecipeManager(getActivity().getBaseContext());
                        recipeManager.DeleteRecipe(RecipeId);
                    }
                })
                .setNegativeButton(R.string.DeleteRecipeDialogCancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}