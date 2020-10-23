package com.example.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int colorResourceId;
    private static MediaPlayer mMediaPlayer;
    private final MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mp){
            releaseMediaPlayer();
        }
    };
    private static AudioManager mAudioManager;
    private static final AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            switch (i){
                case AudioManager.AUDIOFOCUS_LOSS: mMediaPlayer.stop();
                    releaseMediaPlayer();
                    break;
                case AudioManager.AUDIOFOCUS_GAIN: mMediaPlayer.start();
                    break;
                case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT:
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                    break;
                default:break;
            }
        }
    };

    public WordAdapter(Context context, ArrayList<Word> words, int colorId){
        super(context,0,words);
        colorResourceId = colorId;
    }

    /**
     *Provides a view for an AdapterView (ListView, GridView, etc).
     *
     * @param position The AdapterView position that is requesting a view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation
     * @return The view for the position in the AdapterView.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        final Word word = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Lookup view for data population
        TextView mwk = convertView.findViewById(R.id.miwok_number);
        TextView eng = convertView.findViewById(R.id.english_number);
        ImageView img = convertView.findViewById(R.id.word_image);

        // Populate the data into the template view using the data object
        assert word != null;
        mwk.setText(word.getMiwokTranslation());
        eng.setText(word.getEnglishTranslation());
        if (word.getImageResource() != -1) {
            img.setVisibility(View.VISIBLE);
            img.setImageResource(word.getImageResource());
        }else {
            img.setVisibility(View.GONE);
        }

        //Set the theme color for the list item
        View textContainer = convertView.findViewById(R.id.word_text_view);

        //Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), colorResourceId);

        //Set the background color of the text container View
        textContainer.setBackgroundColor(color);

        //Set on click listener for the text container view to play the sound
        textContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 mAudioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
                releaseMediaPlayer();
                int result = mAudioManager.requestAudioFocus(mAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mMediaPlayer = MediaPlayer.create(getContext(), word.getSoundResourceId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    public static void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mAudioFocusChangeListener);
        }
    }
}
