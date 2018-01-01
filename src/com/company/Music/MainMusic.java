package com.company.Music;

import java.util.List;

public class MainMusic {
    public static void main(String[] args){

        DataMusic dMusic = new DataMusic();

//        List<Artists> artists = dMusic.connection(dMusic.ORDER_BY_DESC);
//
//        for( Artists artist:artists){
//            System.out.println(artist.getId()+" : "+artist.getName());
//        };

//        List<String> albumsList=dMusic.querryAlbumsFromArtists("Pink Floyd", dMusic.ORDER_BY_DESC);
//
//        for(String albums: albumsList){
//            System.out.println(albums);
//        }
        dMusic.querySongsMetaData();

        dMusic.getCount("songs");
        dMusic.createViewForSongsArtists();
    }
}
