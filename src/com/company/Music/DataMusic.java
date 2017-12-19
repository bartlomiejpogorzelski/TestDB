package com.company.Music;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DataMusic {

    public static final String MusicDb= "music.db";
    public static final String MusicConnection="jdbc:sqlite:C:\\Users\\Bartek\\IdeaProjects\\TestDB" + MusicDb;

    public static final String SongsTable="songs";
    public static final String SongsId="_id";
    public static final String SongsTrack="track";
    public static final String SongsTitle="title";
    public static final String SongsAlbum="album";

    public static final String AlbumsTable= "albums";
    public static final String AlbumsId="_id";
    public static final String AlbumsName="name";
    public static final String AlbumsArtist="artist";

    public static final String ArtistsTable= "artists";
    public static final String ArtistsId="_id";
    public static final String ArtistsName="name";


    Connection conn = DriverManager.getConnection(MusicConnection);
    Statement statement= conn.createStatement();

}
