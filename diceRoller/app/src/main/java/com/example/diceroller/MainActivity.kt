package com.example.diceroller

import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import java.io.IOException
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private lateinit var backgroundMusic: ImageView

    var flag: Boolean= false

    var mediaPlayer: MediaPlayer? = null
    var btnsound: MediaPlayer? = null
    private var sixSound: MediaPlayer? = null
    private var oneSound: MediaPlayer? = null

    lateinit var diceImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //music play-------->
        backgroundMusic = findViewById(R.id.musicOn)

        backgroundMusic.setOnClickListener {
            if(flag){
                backgroundMusic.setImageResource(R.drawable.sound_on)
                playAudio()
                flag=false
            }
            else{
                backgroundMusic.setImageResource(R.drawable.sound_off)
                pauseAudio()
                flag=true
            }


        }

        //Roll button------->

        val diceButton: Button= findViewById(R.id.btn)
        diceButton.setOnClickListener {
            if (btnsound == null) {
                btnsound = MediaPlayer.create(this, R.raw.button_click)
                btnsound!!.start()
            } else btnsound!!.start()
            rolldice()
        }
        diceImage=findViewById(R.id.dice_image)
    }
    //music----------->
    private fun playAudio(){
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.black_clover)
            mediaPlayer!!.isLooping = true
            mediaPlayer!!.start()
        } else mediaPlayer!!.start()
    }

    private fun pauseAudio(){
        if (mediaPlayer != null && mediaPlayer!!.isPlaying) mediaPlayer!!.pause()
    }

    override fun onStart() {
        super.onStart()
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.black_clover)
            mediaPlayer!!.isLooping = true
            mediaPlayer!!.start()
        } else mediaPlayer!!.start()
    }
    override fun onStop() {
        super.onStop()
        if (mediaPlayer != null) {
            mediaPlayer!!.release()
            mediaPlayer = null
        }
    }
    //Dice Roll---------->
    private fun rolldice(){
        val ranInt:Int = (1..6).random()
        when (ranInt){
            1->{
                if (oneSound == null) {
                    oneSound = MediaPlayer.create(this, R.raw.one_hehe)
                    oneSound!!.start()
                } else oneSound!!.start()
            }
        }
        when (ranInt){
            6->{
                if (sixSound == null) {
                    sixSound = MediaPlayer.create(this, R.raw.six_yee)
                    sixSound!!.start()
                } else sixSound!!.start()
            }
        }

        val drawableResource = when (ranInt){
            1->R.drawable.dice_1
            2->R.drawable.dice_2
            3->R.drawable.dice_3
            4->R.drawable.dice_4
            5->R.drawable.dice_5
            6->R.drawable.dice_6
            else->R.drawable.dice_6
        }
        diceImage.setImageResource(drawableResource)

    }
}