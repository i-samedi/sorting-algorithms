import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;

public class Track<T,K>  
{
    private String id;
    private String cancion;
    private String artist;
    private int popularidad;

    public Track(String id, String cancion, String artist, String popularidad)
    {
        this.id = id;
        this.cancion = cancion;
        this.artist = artist;
        this.popularidad = Integer.parseInt(popularidad);
    }
    
    public String getId(){return id;}
    public String getCancion(){return cancion;}
    public String getArtista(){return artist;}
    public int getPopularidad(){return popularidad;}
    
}