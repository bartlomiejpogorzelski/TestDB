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

    public static final String TABLE_ARTIST_SONG_VIEW="artist_list";
    public static final String CREATE_ARTIST_FOR_SONG_VIEW="CREATE VIEW IF NOT EXISTS " + TABLE_ARTIST_SONG_VIEW + " AS SELECT " +
            ArtistsTable+"."+ArtistsName+", "+AlbumsTable+"."+AlbumsName+" AS " + SongsAlbum+", "+ SongsTable+"."+SongsTrack+", "+
            SongsTable+"."+SongsTitle+ " FROM "+ SongsTable+ " INNER JOIN "+ AlbumsTable+ " ON "+ SongsTable+"."+SongsAlbum+"="+
            AlbumsTable+"."+AlbumsId + " INNER JOIN "+ ArtistsTable+" ON "+ AlbumsTable+"."+AlbumsArtist+"="+ArtistsTable+"."+ArtistsId+
            " ORDER BY "+ ArtistsTable+"."+ArtistsName+", "+AlbumsName+"."+AlbumsName+", "+SongsTable+"."+SongsTrack;

    public static final String QUERRY_VIEW_SONG_INFO="SELECT "+ ArtistsName+", "+SongsAlbum+", "+SongsTrack+
            " FROM " + TABLE_ARTIST_SONG_VIEW+ " WHERE " + SongsTitle+" = ";

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
            conn = DriverManager.getConnection(MusicConnection);
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
    public void querySongsMetaData(){
        String sql ="SELECT * FROM " + SongsTable;

        try{
            conn = DriverManager.getConnection(MusicConnection);
            Statement statement= conn.createStatement();
            ResultSet result= statement.executeQuery(sql);

            ResultSetMetaData metaData = result.getMetaData();
            int numColums = metaData.getColumnCount();
            for(int i=1; i<=numColums; i++){
                System.out.println("Colums"+ i +" in the songs table is names" +metaData.getColumnName(i));
            }

        }
        catch (SQLException e){
            e.getMessage();
        }
    }
    public int getCount(String nameTable){
        String sql = " SELECT COUNT(*) AS count FROM " + nameTable;
        try {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            int count= result.getInt("count");
            System.out.println("liczba: " + count);
            return count;
        } catch (SQLException e) {
            e.getMessage();
            return -1;
        }
    }
    public boolean createViewForSongsArtists(){
        try {
            Statement statement = conn.createStatement();
            statement.execute(CREATE_ARTIST_FOR_SONG_VIEW);
            return true;
        } catch (SQLException e) {
            System.out.println("View failed" + e.getMessage());
            return false;
        }
    }
}


