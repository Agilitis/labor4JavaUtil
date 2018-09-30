import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

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


    public static void main(String[] args) throws Exception {
        BeerStore beerStore = new BeerStore(); //Store for beers

        BufferedReader commandReadeFromConsole = new BufferedReader(new InputStreamReader(System.in));
        String command;

        while(true){
            command = commandReadeFromConsole.readLine();
            if(command.split(" ")[0].equals("add")){
                Beer beerToAdd = new Beer(getBeerNameFromCommand(command), getBeerStyleFromCommand(command), getBeerStrengthFromCommand(command));
                beerStore.addBeer(beerToAdd);
                System.out.println("Successfully added: " + beerToAdd.toString());
            }
            else if(command.split(" ")[0].equals("list")){

                for(Beer beer : beerStore.getBeers()){
                    System.out.println(beer.toString());
                }
            }
        }

    }

}
