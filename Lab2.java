import java.io.*;
import java.lang.reflect.Array;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;

public class Lab2 
{
    public static void main(String[] args) 
    {
        ArrayList<Track> tracks = new ArrayList<>();
        try
        {
            FileReader fr = new FileReader("C:\\Users\\NashoCode\\Desktop\\lab22\\tracks.csv");
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();

            int count = 0;
            if (count == 0) {
                line = br.readLine();
                count++;
            }

            while(line != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                if(tokens.length >= 9)
                {                
                    tracks.add(new Track(tokens[0],tokens[1],tokens[5],tokens[2]));
                }
                else if(tokens.length == 1)
                {
                    System.out.println(line);
                    line = line.substring(2,line.length());
                    System.out.println(line);
                    String[] token = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                    tracks.add(new Track(token[0],token[1],token[5],token[2]));
                }
                line = br.readLine();  

            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error: " + e);
        }
        catch(IOException e)
        {
            System.out.println("Error: " + e);
        }
       //---------------------------------------------------------------------------------------------------------------------
       
        int opOrden = menuOrden();

        Comparator<Track> cmpCancion= new Comparator<Track>(){
            @Override
            public int compare(Track o1, Track o2) {
                return o1.getCancion().compareTo(o2.getCancion());
            }
        };
        Comparator<Track> cmpArtista= new Comparator<Track>(){
            @Override
            public int compare(Track o1, Track o2) {
                return o1.getArtista().compareTo(o2.getArtista());
            }
        };
        Comparator<Track> cmpPopularidad= new Comparator<Track>(){
            @Override
            public int compare(Track o1, Track o2) {
                if (o1.getPopularidad() > o2.getPopularidad()) {
                    return 1;
                }
                else if(o1.getPopularidad()<o2.getPopularidad()){
                    return -1;
                }
                else{
                    return 0;
                }
            }
        };

        Track[] tracksArray = new Track[tracks.size()]; 
        Track[] tracksArray2 = new Track[tracks.size()];  
        for(int i=0 ; i < tracks.size() ; i++)
        {
            tracksArray[i] = tracks.get(i);
            tracksArray2[i] = tracks.get(i);
        }

        menuMetodo(opOrden, tracksArray, cmpCancion, cmpArtista, cmpPopularidad);
        System.out.println("\n");
        
        BinaryTree<String, Track> tree = new BinaryTree<String, Track>();
        for(int i=0 ; i < tracks.size() ; i++)
        {
            tree.put(tracksArray2[i].getCancion(), tracksArray2[i]);
        }
        menuTree(tree, tracksArray2);
    }

    public static int menuOrden(){
        System.out.println("Escoja el numero del parametro de ordenamiento para los tracks: ");
        System.out.println("  1. Cancion.");
        System.out.println("  2. Artista.");
        System.out.println("  3. Popularidad.");
        Scanner a=new Scanner(System.in);
        int op=a.nextInt();
        if(op == 1 || op == 2 || op == 3)
        {
            return op;
        }
        else
        {
            System.out.println("Opcion invalida, vuelva a ingresar el parametro.");
            System.out.println(" ");
            return menuOrden();
            
        }
    }

    public static void menuMetodo(int menuOrden, Track[] tracksArray, Comparator<Track> cmpCancion, Comparator<Track> cmpArtista, Comparator<Track> cmpPopularidad){
        System.out.println("Escoja el numero del metodo de ordenamiento para los tracks:");
        System.out.println("  1. MergeSort.");
        System.out.println("  2. HeapSort.");
        System.out.println("  3. InsertionSort.");
        Scanner a=new Scanner(System.in);
        int op=a.nextInt();

        if(menuOrden == 1)
        {
            switch(op){
                case 1:
                    long inicio = System.currentTimeMillis();
                    MergeSort<Track> mergeSort=new MergeSort(tracksArray, cmpCancion,0,tracksArray.length);
                    mergeSort.mergeSort(tracksArray, cmpCancion,0,tracksArray.length-1);
                    long fin = System.currentTimeMillis();
                    System.out.println("Tiempo de ejecucion del ordenamiento: " + (fin-inicio) + " milisegundos");
                    break;
                case 2:
                    long inicio1 = System.currentTimeMillis();
                    HeapSort<Track> heapSort=new HeapSort(tracksArray, cmpCancion);
                    heapSort.heapSort();
                    long fin1 = System.currentTimeMillis();
                    System.out.println("Tiempo de ejecucion del ordenamiento: " + (fin1 - inicio1) + " milisegundos");
                    break;
                    
                case 3:
                    long inicio2 = System.currentTimeMillis();
                    InsertionSort<Track> sort=new InsertionSort(tracksArray, cmpCancion);
                    sort.sort();
                    long fin2 = System.currentTimeMillis();
                    System.out.println("Tiempo de ejecucion del ordenamiento: " + (fin2 - inicio2) + " milisegundos");
                    break;
                    
                default:
                    System.out.println("Opcion invalida, intente de nuevo");
                    menuMetodo(menuOrden, tracksArray, cmpCancion, cmpArtista, cmpPopularidad);
                    System.out.println(" ");
            }
        }
        else if(menuOrden == 2)
        {
            switch(op){
                case 1:
                    long inicio = System.currentTimeMillis();
                    MergeSort<Track> mergeSort=new MergeSort(tracksArray, cmpArtista,0,tracksArray.length);
                    mergeSort.mergeSort(tracksArray, cmpArtista,0,tracksArray.length-1);
                    long fin = System.currentTimeMillis();
                    System.out.println("Tiempo de ejecucion del ordenamiento: " + (fin-inicio) + " milisegundos");
                    break;
                case 2:
                    long inicio1 = System.currentTimeMillis();
                    HeapSort<Track> heapSort=new HeapSort(tracksArray, cmpArtista);
                    heapSort.heapSort();
                    long fin1 = System.currentTimeMillis();
                    System.out.println("Tiempo de ejecucion del ordenamiento: " + (fin1 - inicio1) + " milisegundos");
                    break;
                case 3:
                    long inicio2 = System.currentTimeMillis();
                    InsertionSort<Track> sort=new InsertionSort(tracksArray, cmpArtista);
                    sort.sort();
                    long fin2 = System.currentTimeMillis();
                    System.out.println("Tiempo de ejecucion del ordenamiento: " + (fin2 - inicio2) + " milisegundos");
                    break;
                default:
                    System.out.println("Opcion invalida, intente de nuevo");
                    menuMetodo(menuOrden, tracksArray, cmpCancion, cmpArtista, cmpPopularidad);
                    System.out.println(" ");
            }
        }
        else if(menuOrden == 3)
        {
            switch(op){
                case 1:
                    long inicio = System.currentTimeMillis();
                    MergeSort<Track> mergeSort=new MergeSort(tracksArray, cmpPopularidad,0,tracksArray.length);
                    mergeSort.mergeSort(tracksArray, cmpPopularidad,0,tracksArray.length-1);
                    long fin = System.currentTimeMillis();
                    System.out.println("Tiempo de ejecucion del ordenamiento: " + (fin-inicio) + " milisegundos");
                    break;
                case 2:
                    long inicio1 = System.currentTimeMillis();
                    HeapSort<Track> heapSort=new HeapSort(tracksArray, cmpPopularidad);
                    heapSort.heapSort();
                    long fin1 = System.currentTimeMillis();
                    System.out.println("Tiempo de ejecucion del ordenamiento: " + (fin1 - inicio1) + " milisegundos");
                    break;
                case 3:
                    long inicio2 = System.currentTimeMillis();
                    InsertionSort<Track> sort=new InsertionSort(tracksArray, cmpPopularidad);
                    sort.sort();
                    long fin2 = System.currentTimeMillis();
                    System.out.println("Tiempo de ejecucion del ordenamiento: " + (fin2 - inicio2) + " milisegundos");
                    break;
                default:
                    System.out.println("Opcion invalida, intente de nuevo");
                    menuMetodo(menuOrden, tracksArray, cmpCancion, cmpArtista, cmpPopularidad);
                    System.out.println(" ");
            }
        }
        else
        {
            System.out.println("Opcion invalida, intente de nuevo");
            menuMetodo(menuOrden, tracksArray, cmpCancion, cmpArtista, cmpPopularidad);
            System.out.println(" ");
        }
    }

    public static BinaryTree menuTree(BinaryTree<String, Track> tree, Track[] tracksArray2)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println(" 1. Buscar una cancion en el arbol");
        System.out.println(" 2. Insertar una cancion en el arbol");
        System.out.println(" 3. Eliminar una cancion del arbol");
        System.out.println(" 4. Rango de canciones en el arbol");
        System.out.println(" 5. Salir");
        int operation = sc.nextInt();
        if(operation == 1)
        {
            Scanner sc2 = new Scanner(System.in);
            System.out.println("Ingrese el nombre de la cancion a buscar: ");
            String nombre = sc2.next();
            Track track = tree.search(nombre);
            if(track != null)
            {
                System.out.println(" ");
                System.out.println("-> Cancion encontrada: "+ track.getCancion());
                System.out.println("-> Artista: "+ track.getArtista());
                System.out.println("-> Popularidad: "+ track.getPopularidad());
                System.out.println("-> ID: "+ track.getId());
            }
            else
            {
                System.out.println("-> Cancion no encontrada");
            }
            System.out.println(" ");
            menuTree(tree, tracksArray2);
        }
        else if(operation == 2)
        { 
            Scanner sc3 = new Scanner(System.in);
            System.out.println("Ingrese el nombre de la cancion a insertar: ");
            String nombre = sc3.nextLine();
            System.out.println("Ingrese el artista de la cancion a insertar: ");
            String artista = sc3.nextLine();
            System.out.println("Ingrese la popularidad de la cancion a insertar: (INGRESE NUMEROS)");
            String popularidad = sc3.next();
            System.out.println("Ingrese el id de la cancion a insertar: (INGRESE NUMEROS)");
            String id = sc3.next();
            Track track = new Track(id, nombre, artista, popularidad);
            tree.put(nombre, track);
            menuTree(tree, tracksArray2);

        }
        else if(operation == 3)
        {
            Scanner sc4 = new Scanner(System.in);
            System.out.println("Ingrese el nombre de la cancion a eliminar: ");
            String nombre = sc4.next();
            System.out.println(tree.remove(nombre));
            System.out.println(" ");
            menuTree(tree, tracksArray2);
        }
        else if(operation == 4)
        {
            Scanner sc5 = new Scanner(System.in);
            System.out.println("Ingrese el rango de la cancion al inicio: ");
            String rangoInicio = sc5.nextLine();
            System.out.println("Ingrese el rango de la cancion al final: ");
            String rangoFinal = sc5.nextLine();
            Iterable<String> songsIterable = tree.range(rangoInicio, rangoFinal);
            System.out.println("Canciones dentro de rango: \n");
            for(String song : songsIterable)
            {
                System.out.println(song);
            }
            System.out.println(" ");
            menuTree(tree, tracksArray2);
        }
        else if(operation == 5)
        {
            System.out.println(" Gracias por usar el programa");
            System.out.println(" Atte -tiago");
            System.out.println(" ");
        }
        else
        {
            System.out.println("Opcion invalida, intente de nuevo");
            System.out.println(" ");
            menuTree(tree, tracksArray2);
        }
        return tree;
    }
}
