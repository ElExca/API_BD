package com.escuelita.demo.entities.projections;

public interface SongProjection {

    Long getId();

    String getName();

    String getDuration();

    String getDate();

    String getSong_Url();

    Long getArtist_Id();

    Long getGenre_Id();
}
