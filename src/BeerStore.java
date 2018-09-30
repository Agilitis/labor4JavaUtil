import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BeerStore implements Serializable {
    ArrayList<Beer> beers = new ArrayList<>();

    public BeerStore(){

    }

    public void addBeer(Beer beer){
        beers.add(beer);
    }

    public ArrayList<Beer> getBeers(){
        return beers;
    }

    public ArrayList<Beer> getBeersSortedByName(){
            Collections.sort(beers, Comparator.comparing(Beer::getName));
            return beers;
    }

    public ArrayList<Beer> getBeersSortedByType(){
        Collections.sort(beers, Comparator.comparing(Beer::getStyle));
        return beers;
    }

    public ArrayList<Beer> getBeersSortedByStrength(){
        Collections.sort(beers, Comparator.comparing(Beer::getStyle));
        return beers;
    }


    public void deleteBeer(String name) {
        for(Beer beer: beers){
            if(beer.getName().equals(name)){
                beers.remove(beer);
            }
        }
    }
}
