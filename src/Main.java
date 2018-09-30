import java.io.*;
import java.util.*;

public class Main {

    public static final String MY_OBJECT_TXT = "myObject.txt";

    public static void main(String[] args) throws Exception {
        BeerStore beerStore = new BeerStore(); //Store for beers

        BufferedReader commandReaderFromConsole = new BufferedReader(new InputStreamReader(System.in));
        String line;
        String command;
        ArrayList<String> arguments;
        while(true){
            line = commandReaderFromConsole.readLine();
            command = line.split(" ")[0];
            arguments = new ArrayList<>(Arrays.asList(line.split(" ")));
            arguments.remove(0);

            beerStore = handleCommands(beerStore, command);
        }

    }

    private static String getBeerNameFromCommand(String command) throws BeerCeption{
        if(command.split(" ").length == 4){
            return command.split(" ")[1];
        }else{
            throw new BeerCeption("Beer is not specified! Insufficient arguments to command");
        }
    }

    private static String getBeerStyleFromCommand(String command) throws BeerCeption{
        if(command.split(" ").length == 4){
            return command.split(" ")[2];
        }else{
            throw new BeerCeption("Beer is not specified! Insufficient arguments to command");
        }
    }

    private static double getBeerStrengthFromCommand(String command)throws BeerCeption{
        if(command.split(" ").length == 4){
            return Double.parseDouble(command.split(" ")[3]);
        }else{
            throw new BeerCeption("Beer is not specified! Insufficient arguments to command");
        }
    }

    private static BeerStore handleCommands(BeerStore beerStore, String command) throws Exception {
        if(command.equals("add")){
            addBeerToBeerStore(beerStore, command);
        }
        else if(command.equals("list")){
            listBeersFromBeerStore(beerStore, command);
        }
        else if(command.equals("write")){
            serializeBeerStore(beerStore);
        }
        else if(command.equals("load")){
            beerStore = deserializeBeerStore();
        }
        else if(command.equals("delete")){
            beerStore.deleteBeer(command.split(" ")[1]);
        }
        return beerStore;
    }

    private static BeerStore deserializeBeerStore() throws IOException, ClassNotFoundException {
        BeerStore beerStore;
        FileInputStream file = new FileInputStream(MY_OBJECT_TXT);
        ObjectInputStream in = new ObjectInputStream(file);

        beerStore = (BeerStore)in.readObject();

        in.close();
        file.close();
        return beerStore;
    }

    private static void serializeBeerStore(BeerStore beerStore) throws IOException {
        FileOutputStream file = new FileOutputStream(MY_OBJECT_TXT);

        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(beerStore);

        out.close();
        file.close();
    }

    private static void listBeersFromBeerStore(BeerStore beerStore, String command) throws Exception{

        if(beerStore.getBeers().isEmpty()){
            throw new BeerCeption("We are fu**ing out of beer!");
        }

        if(command.split(" ").length == 1){
            for(Beer beer : beerStore.getBeers()){
                System.out.println(beer.toString());
            }
            return;
        }

        if(command.split(" ")[1].equals("name")){
            for(Beer beer: beerStore.getBeersSortedByName()){
                System.out.println(beer.toString());
            }
        }
        else if(command.split(" ")[1].equals("style")){
            for(Beer beer: beerStore.getBeersSortedByType()){
                System.out.println(beer.toString());
            }
        }
        else if(command.split(" ")[1].equals("strength")){
            for(Beer beer: beerStore.getBeersSortedByStrength()){
                System.out.println(beer.toString());
            }
        }
    }

    private static void addBeerToBeerStore(BeerStore beerStore, String command) throws BeerCeption {
        Beer beerToAdd = new Beer(getBeerNameFromCommand(command), getBeerStyleFromCommand(command), getBeerStrengthFromCommand(command));
        beerStore.addBeer(beerToAdd);
        System.out.println("Successfully added: " + beerToAdd.toString());
    }

}
