package com.barmej.notesapp.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.barmej.notesapp.Model.CheckNote;
import com.barmej.notesapp.Model.Items;
import com.barmej.notesapp.Model.NotePhoto;
import com.barmej.notesapp.Model.Notes;
import com.barmej.notesapp.R;

import java.util.ArrayList;

public class RecyclerNoteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Items> mItems;

    public RecyclerNoteAdapter(ArrayList<Items> mItems) {
        this.mItems = mItems;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // 1 - simple note
        if (viewType == 0){
            return new itemNote(

                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note,parent,false)
            );


            // 2 - note check box
        }else if (viewType == 1){

            return new itemNotePhoto(

                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note_photo,parent,false)
            );

        }else{

            return new itemNoteCheck(

                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note_check,parent,false)
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        if (getItemViewType(position) == 0){
            Notes mNotes = (Notes) mItems.get(position).getObject();
            ((itemNote)holder).setItemNote(mNotes);
        }else if(getItemViewType(position) == 1){

            NotePhoto mNotesPhoto = (NotePhoto) mItems.get(position).getObject();
            ((itemNotePhoto)holder).setItemNotePhoto(mNotesPhoto);
        }else{

            CheckNote mNotesCheck = (CheckNote) mItems.get(position).getObject();
            ((itemNoteCheck)holder).setItemNoteCheck(mNotesCheck);
        }


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getType();
    }

    static class itemNote  extends RecyclerView.ViewHolder{
        TextView tv_note_simple;

        itemNote(@NonNull View itemView) {
            super(itemView);
            tv_note_simple = itemView.findViewById(R.id.tv_note_simple);
        }

        void setItemNote(Notes notes){

            tv_note_simple.setText(notes.getNoteBody());





        }

    }



    static class itemNoteCheck  extends RecyclerView.ViewHolder{
        private TextView  tv_note_check;
        private CheckBox checkBox;
        CardView mCard;

        itemNoteCheck(@NonNull final View itemView) {
            super(itemView);
            tv_note_check = itemView.findViewById(R.id.tv_note_check);
            checkBox = itemView.findViewById(R.id.checkBox);
            mCard = itemView.findViewById(R.id.cardViewChecked);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (checkBox.isChecked()){
                       mCard.getBackground().setTint(Color.rgb(76,175,  80));


                    }else{
                        mCard.getBackground().setTint(Color.rgb(220,84,75));

                    }
                }
            });
        }
        void setItemNoteCheck(CheckNote itemNoteCheck){

            tv_note_check.setText(itemNoteCheck.getNoteBody());




        }
    }



    static class itemNotePhoto  extends RecyclerView.ViewHolder{
        private TextView  tv_note_photo;
        private ImageView  image_note;

        public itemNotePhoto(@NonNull View itemView) {
            super(itemView);
            tv_note_photo = itemView.findViewById(R.id.tv_note_photo);
            image_note = itemView.findViewById(R.id.img_note_photo);
        }
        void setItemNotePhoto(NotePhoto itemNotePhoto){


            tv_note_photo.setText(itemNotePhoto.getNoteBody());
            image_note.setImageURI(itemNotePhoto.getNoteImage());

        }
    }







}
