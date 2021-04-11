package com.barmej.notesapp.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.barmej.notesapp.Interface.OnItemClickListener;
import com.barmej.notesapp.Interface.OnItemLongClickListener;
import com.barmej.notesapp.Model.CheckNote;
import com.barmej.notesapp.Model.Items;
import com.barmej.notesapp.Model.NotePhoto;
import com.barmej.notesapp.Model.Notes;
import com.barmej.notesapp.R;

import java.util.ArrayList;

public class RecyclerNoteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Items> mItems;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public RecyclerNoteAdapter(ArrayList<Items> mItems,OnItemClickListener onItemClickListener,OnItemLongClickListener onItemLongClickListener) {
        this.mItems = mItems;
        this.onItemClickListener = onItemClickListener;
        this.onItemLongClickListener = onItemLongClickListener;
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {


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

        //update items
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onClickItem(position);

            }
        });
        //delete items
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onItemLongClickListener.onLongItem(position);
                return false;
            }
        });


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
        CardView cardViewNotes;


        itemNote(@NonNull View itemView) {
            super(itemView);
            tv_note_simple = itemView.findViewById(R.id.tv_note_simple);
            cardViewNotes = itemView.findViewById(R.id.cardViewNotes);
        }

        void setItemNote(Notes notes){

            tv_note_simple.setText(notes.getNoteBodySimple());
            cardViewNotes.getBackground().setTint(notes.getBackgroundCardNoteColor());




        }

    }



    static class itemNoteCheck  extends RecyclerView.ViewHolder{
        private TextView  tv_note_check;
        private CheckBox checkBox;
        CardView cardViewCheckNote;

        itemNoteCheck(@NonNull final View itemView) {
            super(itemView);
            tv_note_check = itemView.findViewById(R.id.tv_note_check);
            checkBox = itemView.findViewById(R.id.checkBox);
            cardViewCheckNote = itemView.findViewById(R.id.cardViewCheckNote);

        }
        void setItemNoteCheck(final CheckNote itemNoteCheck){

            tv_note_check.setText(itemNoteCheck.getNoteBodyCheck());
            cardViewCheckNote.setCardBackgroundColor(itemNoteCheck.getBackgroundCardNoteColor());
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (checkBox.isChecked()){
                        cardViewCheckNote.setCardBackgroundColor(Color.rgb(76,175,  80));
                        tv_note_check.setTextColor(Color.WHITE);
                        checkBox.setTextColor(Color.WHITE);


                    }else{
                        cardViewCheckNote.setCardBackgroundColor(itemNoteCheck.getBackgroundCardNoteColor());
                        tv_note_check.setTextColor(Color.parseColor("#3D3B3B"));
                        checkBox.setTextColor(Color.GRAY);

                    }
                }
            });





        }
    }



    static class itemNotePhoto  extends RecyclerView.ViewHolder{
        private TextView  tv_note_photo;
        private ImageView  image_note;
        private CardView cardViewPhotoNote;

        public itemNotePhoto(@NonNull View itemView) {
            super(itemView);
            tv_note_photo = itemView.findViewById(R.id.tv_note_photo);
            image_note = itemView.findViewById(R.id.img_note_photo);
            cardViewPhotoNote = itemView.findViewById(R.id.cardViewPhotoNote);


        }
        void setItemNotePhoto(NotePhoto itemNotePhoto){


            tv_note_photo.setText(itemNotePhoto.getNoteBodyPhoto());
            image_note.setImageURI(itemNotePhoto.getNoteImage());
            cardViewPhotoNote.setCardBackgroundColor(itemNotePhoto.getBackgroundCardNoteColor());

        }
    }







}
