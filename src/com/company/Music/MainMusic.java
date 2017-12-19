package com.company.Music;

import java.util.List;

public class MainMusic {
    public static void main(String[] args){

        DataMusic dMusic = new DataMusic();

        List<Artists> artists = dMusic.connection();

        for( Artists artist:artists){
            System.out.println(artist.getId()+" : "+artist.getName());
        };



    }
}
