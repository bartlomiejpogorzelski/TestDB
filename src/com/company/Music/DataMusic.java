package com.company.Music;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataMusic {

    public static final String MusicDb = "music.db";
    public static final String MusicConnection = "jdbc:sqlite:C:\\Users\\Bartek\\IdeaProjects\\TestDB\\" + MusicDb;

    public static final String SongsTable = "songs";
    public static final String SongsId = "_id";
    public static final String SongsTrack = "track";
    public static final String SongsTitle = "title";
    public static final String SongsAlbum = "album";

    public static final String AlbumsTable = "albums";
    public static final String AlbumsId = "_id";
    public static final String AlbumsName = "name";
    public static final String AlbumsArtist = "artist";

    public static final String ArtistsTable = "artists";
    public static final String ArtistsId = "_id";
    public static final String ArtistsName = "name";
    public static final int INDEX_ARTISTS_ID = 1;
    public static final int INDEX_ARTISTS_NAME = 2;

    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;

    Connection conn;
    public List connection(int number) {
        StringBuilder stringBuilder = new StringBuilder("SELECT * FROM ");
        if (number != ORDER_BY_NONE) {
            stringBuilder.append(ArtistsTable);
            stringBuilder.append(" ORDER BY ");
            stringBuilder.append(ArtistsName);
            stringBuilder.append(" COLLATE NOCASE ");
            if (number == ORDER_BY_ASC) {
                stringBuilder.append("ASC");
            } else {
                stringBuilder.append("DESC");
            }}
            List list = new ArrayList();

            try {
                conn = DriverManager.getConnection(MusicConnection);
                Statement statement = conn.createStatement();
                ResultSet result;
                result = statement.executeQuery(stringBuilder.toString());


                while (result.next()) {
                    Artists artist = new Artists();
                    artist.setId(result.getInt(INDEX_ARTISTS_ID));
                    artist.setName(result.getString(INDEX_ARTISTS_NAME));
                    list.add(artist);

                }
            } catch (SQLException e) {
                System.out.print("error  " + e.getMessage());
            }

            return list;
        }

        List<String> querryAlbumsFromArtists(String stringArtist, int orderBy){
        StringBuilder stringBuilder =new StringBuilder("SELECT ");
        stringBuilder.append(AlbumsTable);
        stringBuilder.append(".");
        stringBuilder.append(AlbumsName);
        stringBuilder.append(" FROM ");
        stringBuilder.append(ArtistsTable);
        stringBuilder.append(" INNER JOIN ");
        stringBuilder.append(AlbumsTable);
        stringBuilder.append(" ON ");
        stringBuilder.append(ArtistsTable);
        stringBuilder.append(".");
        stringBuilder.append(ArtistsId);
        stringBuilder.append(" = ");
        stringBuilder.append(AlbumsTable);
        stringBuilder.append(".");
        stringBuilder.append(AlbumsArtist);
        stringBuilder.append(" WHERE ");
        stringBuilder.append(ArtistsTable);
        stringBuilder.append(".");
        stringBuilder.append(ArtistsName);
        stringBuilder.append(" =\"");
        stringBuilder.append(stringArtist);
        stringBuilder.append("\"");
        stringBuilder.append(" ORDER BY ");
        stringBuilder.append(AlbumsTable);
        stringBuilder.append(".");
        stringBuilder.append(AlbumsName);
        stringBuilder.append(" COLLATE NOCASE ");
        if(orderBy == ORDER_BY_ASC){
            stringBuilder.append("ASC");
        }
        else{
            stringBuilder.append("DESC");
        }
            List<String> albums = new ArrayList<>();
        try{
            conn=DriverManager.getConnection(MusicConnection);
            Statement statement= conn.createStatement();
            ResultSet result = statement.executeQuery(stringBuilder.toString());
            while (result.next()) {
            albums.add(result.getString(1));
            }
            return albums;

        } catch (SQLException e1) {
            System.out.print("errror2" + e1.getMessage());
            return null;
        }


        }

        }


