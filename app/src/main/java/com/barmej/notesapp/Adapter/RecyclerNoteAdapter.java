package com.barmej.notesapp.Adapter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.barmej.notesapp.Constant;
import com.barmej.notesapp.Interface.OnItemLongClickListener;
import com.barmej.notesapp.Model.CheckNote;
import com.barmej.notesapp.Model.Items;
import com.barmej.notesapp.Model.NotePhoto;
import com.barmej.notesapp.Model.Notes;
import com.barmej.notesapp.R;
import com.barmej.notesapp.UiInterface.UpdateNoteCheckDetails;
import com.barmej.notesapp.UiInterface.UpdateNoteDetails;
import com.barmej.notesapp.UiInterface.UpdateNotePhotoDetails;

import java.util.ArrayList;

public class RecyclerNoteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Items> mItems;
    private OnItemLongClickListener onItemLongClickListener;


    public RecyclerNoteAdapter(ArrayList<Items> mItems, OnItemLongClickListener onItemLongClickListener) {
        this.mItems = mItems;
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // 1 - simple note
        if (viewType == 0) {
            return new itemNote(

                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false)
            );


            // 1 - note photo
        } else if (viewType == 1) {

            return new itemNotePhoto(

                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note_photo, parent, false)
            );
            // 2 - note check box
        } else {

            return new itemNoteCheck(

                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note_check, parent, false)
            );
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {


        if (getItemViewType(position) == 0) {
            Notes mNotes = (Notes) mItems.get(position).getObject();
            ((itemNote) holder).setItemNote(mNotes);
        } else if (getItemViewType(position) == 1) {

            NotePhoto mNotesPhoto = (NotePhoto) mItems.get(position).getObject();
            ((itemNotePhoto) holder).setItemNotePhoto(mNotesPhoto);
        } else {

            CheckNote mNotesCheck = (CheckNote) mItems.get(position).getObject();
            ((itemNoteCheck) holder).setItemNoteCheck(mNotesCheck);
        }

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

    static class itemNote extends RecyclerView.ViewHolder {
        TextView tv_note_simple;
        CardView cardViewNotes;


        itemNote(@NonNull View itemView) {
            super(itemView);
            tv_note_simple = itemView.findViewById(R.id.tv_note_simple);
            cardViewNotes = itemView.findViewById(R.id.cardViewNotes);
        }

        void setItemNote(final Notes notes) {

            tv_note_simple.setText(notes.getNoteBodySimple());
            cardViewNotes.setCardBackgroundColor(notes.getBackgroundCardNoteColor());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intentNoteCheckDetails = new Intent(itemView.getContext(), UpdateNoteDetails.class);

                    intentNoteCheckDetails.putExtra(Constant.EXTRA_TEXT_NOTE, notes.getNoteBodySimple());
                    intentNoteCheckDetails.putExtra(Constant.EXTRA_NOTE_COLOR, notes.getBackgroundCardNoteColor());
                    intentNoteCheckDetails.putExtra(Constant.EXTRA_NOTE_POSITION, getAdapterPosition());
                    itemView.getContext().startActivity(intentNoteCheckDetails);
                }
            });


        }

    }


    static class itemNoteCheck extends RecyclerView.ViewHolder {
        private TextView tv_note_check;
        private CheckBox checkBox;
        CardView cardViewCheckNote;

        itemNoteCheck(@NonNull final View itemView) {
            super(itemView);
            tv_note_check = itemView.findViewById(R.id.tv_note_check);
            checkBox = itemView.findViewById(R.id.checkBox);
            cardViewCheckNote = itemView.findViewById(R.id.cardViewCheckNote);

        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        void setItemNoteCheck(final CheckNote itemNoteCheck) {

            tv_note_check.setText(itemNoteCheck.getNoteBodyCheck());
            cardViewCheckNote.setCardBackgroundColor(itemNoteCheck.getBackgroundCardNoteColor());
            if (itemNoteCheck.isChecked() == true) {

                cardViewCheckNote.getBackground().setTint(Color.rgb(76, 175, 80));
                checkBox.setChecked(true);
                itemNoteCheck.setChecked(true);

            } else {

                cardViewCheckNote.getBackground().setTint(itemNoteCheck.getBackgroundCardNoteColor());
                checkBox.setChecked(false);
                itemNoteCheck.setChecked(false);

            }

            checkBox.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View view) {

                    if (checkBox.isChecked()) {
                        checkBox.setChecked(true);
                        itemNoteCheck.setChecked(true);

                        cardViewCheckNote.getBackground().setTint(Color.rgb(76, 175, 80));


                    } else {
                        checkBox.setChecked(false);
                        cardViewCheckNote.getBackground().setTint(itemNoteCheck.getBackgroundCardNoteColor());
                        itemNoteCheck.setChecked(false);

                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intentNoteCheckDetails = new Intent(itemView.getContext(), UpdateNoteCheckDetails.class);

                    intentNoteCheckDetails.putExtra(Constant.EXTRA_TEXT_CHECK_NOTE, itemNoteCheck.getNoteBodyCheck());
                    intentNoteCheckDetails.putExtra(Constant.EXTRA_IS_CHECK_NOTE, checkBox.isChecked());
                    intentNoteCheckDetails.putExtra(Constant.EXTRA_NOTE_POSITION, getAdapterPosition());
                    intentNoteCheckDetails.putExtra(Constant.EXTRA_NOTE_CHECK_COLOR, cardViewCheckNote.getCardBackgroundColor().getDefaultColor());
                    itemView.getContext().startActivity(intentNoteCheckDetails);
                }
            });


        }
    }


    static class itemNotePhoto extends RecyclerView.ViewHolder {
        private TextView tv_note_photo;
        private ImageView image_note;
        private CardView cardViewPhotoNote;

        public itemNotePhoto(@NonNull View itemView) {
            super(itemView);
            tv_note_photo = itemView.findViewById(R.id.tv_note_photo);
            image_note = itemView.findViewById(R.id.img_note_photo);
            cardViewPhotoNote = itemView.findViewById(R.id.cardViewPhotoNote);


        }

        void setItemNotePhoto(final NotePhoto itemNotePhoto) {


            tv_note_photo.setText(itemNotePhoto.getNoteBodyPhoto());
            image_note.setImageURI(itemNotePhoto.getNoteImage());
            cardViewPhotoNote.setCardBackgroundColor(itemNotePhoto.getBackgroundCardNoteColor());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Intent intentNotePhotoDetails = new Intent(itemView.getContext(), UpdateNotePhotoDetails.class);
                    intentNotePhotoDetails.putExtra(Constant.EXTRA_TEXT_PHOTO, itemNotePhoto.getNoteBodyPhoto());
                    intentNotePhotoDetails.putExtra(Constant.EXTRA_URI_PHOTO, itemNotePhoto.getNoteImage());
                    intentNotePhotoDetails.putExtra(Constant.EXTRA_NOTE_POSITION, getAdapterPosition());
                    intentNotePhotoDetails.putExtra(Constant.EXTRA_NOTE_PHOTO_COLOR, itemNotePhoto.getBackgroundCardNoteColor());


                    itemView.getContext().startActivity(intentNotePhotoDetails);

                }
            });

        }
    }


}
