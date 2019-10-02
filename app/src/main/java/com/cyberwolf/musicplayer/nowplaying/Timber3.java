package com.cyberwolf.musicplayer.nowplaying;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cyberwolf.musicplayer.MusicPlayer;
import com.cyberwolf.musicplayer.R;
import com.cyberwolf.musicplayer.adapters.SlidingQueueAdapter;
import com.cyberwolf.musicplayer.dataloaders.QueueLoader;
import com.cyberwolf.musicplayer.utils.ImageUtils;

import net.steamcrafted.materialiconlib.MaterialDrawableBuilder;

/**
 * Created by naman on 22/02/17.
 */

public class Timber3 extends BaseNowplayingFragment {

    int repeatval,shuffleval;
    ImageView mBlurredArt;
    RecyclerView recyclerView;
    SlidingQueueAdapter adapter;
    TextView shade,song_title,song_artist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_timber3, container, false);

        setMusicStateListener();
        setSongDetails(rootView);
        shade = (TextView) rootView.findViewById(R.id.shade);
        shade.setBackgroundColor(Color.BLACK);
        shade.setAlpha(1.0f);
        shade.animate().alpha(0.2f).setDuration(1500);

        song_title= (TextView) rootView.findViewById(R.id.song_title);
        //song_title.setTranslationX(100);
        //song_title.setAlpha(0.0f);
        //song_title.animate().translationX(0).alpha(1.0f).setDuration(2500);

        song_artist=(TextView)rootView.findViewById(R.id.song_artist);
        //song_artist.setTranslationX(100);
       // song_artist.setAlpha(0.0f);
       // song_artist.animate().translationX(0).alpha(1.0f).setDuration(3000);


        mBlurredArt = (ImageView) rootView.findViewById(R.id.album_art_blurred);

        //mBlurredArt.animate().translationX(100).setDuration(10000).setInterpolator(new OvershootInterpolator());
        recyclerView = (RecyclerView) rootView.findViewById(R.id.queue_recyclerview_horizontal) ;
        initGestures(mBlurredArt);
        setupSlidingQueue();

        return rootView;
    }

    @Override
    public void updateShuffleState() {
        if (shuffle != null && getActivity() != null) {
            MaterialDrawableBuilder builder = MaterialDrawableBuilder.with(getActivity())
                    .setIcon(MaterialDrawableBuilder.IconValue.SHUFFLE)
                    .setSizeDp(30);

            if (MusicPlayer.getShuffleMode() == 0) {
                builder.setColor(Color.WHITE);
            } else builder.setColor(Color.RED);


            shuffle.setImageDrawable(builder.build());
            shuffle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MusicPlayer.cycleShuffle();
                    updateShuffleState();
                    updateRepeatState();
                }
            });
        }
    }

    @Override
    public void updateRepeatState() {
        if (repeat != null && getActivity() != null) {
            MaterialDrawableBuilder builder = MaterialDrawableBuilder.with(getActivity())
                    .setIcon(MaterialDrawableBuilder.IconValue.REPEAT)
                    .setSizeDp(30);

            if (MusicPlayer.getRepeatMode() == 0) {
                builder.setColor(Color.WHITE);
            } else builder.setColor(Color.RED);

            repeat.setImageDrawable(builder.build());
            repeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MusicPlayer.cycleRepeat();

                    updateShuffleState();
                    updateRepeatState();

                }
            });
        }
    }

    @Override
    public void doAlbumArtStuff(Bitmap loadedImage) {
        setBlurredAlbumArt blurredAlbumArt = new setBlurredAlbumArt();
        blurredAlbumArt.execute(loadedImage);
    }

    private void setupSlidingQueue() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new SlidingQueueAdapter((AppCompatActivity) getActivity(), QueueLoader.getQueueSongs(getActivity()));
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(MusicPlayer.getQueuePosition() - 3);
    }


    private class setBlurredAlbumArt extends AsyncTask<Bitmap, Void, Drawable> {

        @Override
        protected Drawable doInBackground(Bitmap... loadedImage) {
            Drawable drawable = null;
            try {
                drawable = ImageUtils.createBlurredImageFromBitmap(loadedImage[0], getActivity(), 12);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return drawable;
        }

        @Override
        protected void onPostExecute(Drawable result) {
            if (result != null) {
                if (mBlurredArt.getDrawable() != null) {
                    final TransitionDrawable td =
                            new TransitionDrawable(new Drawable[]{
                                    mBlurredArt.getDrawable(),
                                    result
                            });
                    mBlurredArt.setImageDrawable(td);
                    td.startTransition(200);

                } else {
                    mBlurredArt.setImageDrawable(result);
                }
            }
        }

        @Override
        protected void onPreExecute() {
        }
    }

}
