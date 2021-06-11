package com.barmej.notesapp.ui.Adapter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.barmej.notesapp.R;
import com.barmej.notesapp.data.model.CheckNote;
import com.barmej.notesapp.data.model.Items;
import com.barmej.notesapp.data.model.NotePhoto;
import com.barmej.notesapp.data.model.Notes;
import com.barmej.notesapp.databinding.ItemNoteBinding;
import com.barmej.notesapp.databinding.ItemNoteCheckBinding;
import com.barmej.notesapp.databinding.ItemNotePhotoBinding;
import com.barmej.notesapp.ui.Adapter.Listener.OnItemLongClickListener;
import com.barmej.notesapp.ui.activities.Constant;
import com.barmej.notesapp.ui.activities.UpdateNoteCheckDetails;
import com.barmej.notesapp.ui.activities.UpdateNoteDetails;
import com.barmej.notesapp.ui.activities.UpdateNotePhotoDetails;

import java.util.List;


public class RecyclerNoteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

   private List<Items> mItems;
    private OnItemLongClickListener onItemLongClickListener;



    public RecyclerNoteAdapter(List<Items> mItems, OnItemLongClickListener onItemLongClickListener) {
        this.mItems = mItems;
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // 1 - simple note
        if (viewType == 0) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            ItemNoteBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_note,parent,false);
            return new itemNote(binding);

            // 1 - note photo
        } else if (viewType == 1) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            ItemNotePhotoBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_note_photo,parent,false);
            return new itemNotePhoto(binding);
            // 2 - note check box
        } else {

            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            ItemNoteCheckBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_note_check,parent,false);
            return new itemNoteCheck(binding);


        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {


        if (getItemViewType(position) == 0) {
            Notes mNotes = (Notes) mItems.get(position).getObject();
            ((itemNote) holder).setItemNote( mNotes);
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


    public Notes getNotesAt(int position){
        return (Notes) mItems.get(position).getObject();
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
        ItemNoteBinding noteBinding;
        itemNote(@NonNull ItemNoteBinding binding) {
            super(binding.getRoot());
            this.noteBinding = binding;
        }

        void setItemNote(final Notes notes) {

            noteBinding.setNotes(notes);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), UpdateNoteDetails.class);
                    intent.putExtra(Constant.EXTRA_TEXT_NOTE,noteBinding.tvNoteSimple.getText());
                    intent.putExtra(Constant.EXTRA_NOTE_COLOR,notes.getColor());
                    itemView.getContext().startActivity(intent);
                }
            });








        }

    }


    static class itemNoteCheck extends RecyclerView.ViewHolder {
        ItemNoteCheckBinding noteCheckBinding;
        itemNoteCheck(@NonNull final ItemNoteCheckBinding binding) {
            super(binding.getRoot());
            this.noteCheckBinding = binding;
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        void setItemNoteCheck(final CheckNote checkNote) {
            noteCheckBinding.setCheckNote(checkNote);
            noteCheckBinding.checkBox.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View view) {
                    if (noteCheckBinding.checkBox.isChecked()) {
                        checkNote.setChecked(true);
                        noteCheckBinding.cardViewCheckNote.getBackground().setTint(Color.rgb(76, 175, 80));
                        Toast.makeText(itemView.getContext(),"status is checked  : " + checkNote.isChecked(),Toast.LENGTH_SHORT).show();

                    } else {
                        checkNote.setChecked(false);
                        noteCheckBinding.cardViewCheckNote.getBackground().setTint(checkNote.getColor());
                        Toast.makeText(itemView.getContext(),"status is checked  : " + checkNote.isChecked(),Toast.LENGTH_SHORT).show();


                    }
                }
            });


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), UpdateNoteCheckDetails.class);
                    intent.putExtra(Constant.EXTRA_TEXT_CHECK_NOTE,noteCheckBinding.tvNoteCheck.getText());
                    intent.putExtra(Constant.EXTRA_NOTE_COLOR,checkNote.getColor());
                    intent.putExtra(Constant.EXTRA_IS_CHECK_NOTE,checkNote.isChecked());
                    itemView.getContext().startActivity(intent);
                }
            });





        }
    }


     class itemNotePhoto extends RecyclerView.ViewHolder {

        ItemNotePhotoBinding notePhotoBinding;
        public itemNotePhoto(@NonNull ItemNotePhotoBinding binding) {
            super(binding.getRoot());
            this.notePhotoBinding = binding;



        }

        void setItemNotePhoto(final NotePhoto notePhoto) {

            notePhotoBinding.setNoteImage(notePhoto);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), UpdateNotePhotoDetails.class);
                    intent.putExtra(Constant.EXTRA_TEXT_PHOTO,notePhotoBinding.tvNotePhoto.getText().toString());
                    intent.putExtra(Constant.EXTRA_URI_PHOTO,notePhoto.getImage());
                    intent.putExtra(Constant.EXTRA_NOTE_PHOTO_COLOR,notePhoto.getColor());
                    itemView.getContext().startActivity(intent);
                }
            });







        }
    }


}
