package com.barmej.notesapp.ui.Adapter;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {

        return mItems.get(position).getType();

    }

    public void setNote(Object object, int type) {
        if ( object != null) {
            mItems.clear();
            mItems.add(new Items(object,type));
            notifyDataSetChanged();

        }

    }

    static class itemNote extends RecyclerView.ViewHolder {
        ItemNoteBinding binding;
        itemNote(@NonNull ItemNoteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void setItemNote(final Notes notes) {

            binding.setNotes(notes);
            /*
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

            */



        }

    }


    static class itemNoteCheck extends RecyclerView.ViewHolder {
        ItemNoteCheckBinding binding;
        itemNoteCheck(@NonNull final ItemNoteCheckBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        void setItemNoteCheck(final CheckNote itemNoteCheck) {
            binding.setCheckNote(itemNoteCheck);

        }
    }


     class itemNotePhoto extends RecyclerView.ViewHolder {

        ItemNotePhotoBinding binding;
        public itemNotePhoto(@NonNull ItemNotePhotoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        void setItemNotePhoto(NotePhoto notePhoto) {

            binding.setNoteImage(notePhoto);




        }
    }


}
