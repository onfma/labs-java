package Map;
import Location.*;
import Road.*;

import java.util.ArrayList;

public class BestRouteProblem
{
    protected ArrayList<Road> bestRouteComponents = new ArrayList<>();
    protected ArrayList<Location> reachableLocations;
    protected double totalLenght;

    /**
     * Puts in the "reachableLocations" all the locations that can be reached
     * from the startLocation from the roads that are in the map.
     */
    protected void findDestinations(Map map, Location startLocation)
    {
        // deplasare catre noile destinatii
        for(Road road : map.roads)
        {
            if(road.getRoadStartLocation() == startLocation)
            {
                boolean isInArray1 = false;
                for(Location l : reachableLocations)
                {
                    if(road.getRoadEndLocation().equals(l))
                    {
                        isInArray1 = true;
                    }
                }
                if(!isInArray1) reachableLocations.add(road.getRoadEndLocation());

                //System.out.println(reachableLocations);
                findDestinations(map, road.getRoadEndLocation());
            }
            if(road.getRoadEndLocation() == startLocation)
            {
                boolean isInArray1 = false;
                for (Location l : reachableLocations) {
                    if (road.getRoadStartLocation().equals(l)) {
                        isInArray1 = true;
                    }
                }
                if (!isInArray1) reachableLocations.add(road.getRoadStartLocation());

                //System.out.println(reachableLocations);
                //findDestinations(map,road.getRoadStartLocation());
            }
        }
    }

    /**
     * Returns FALSE if the 2 locations aren't in the map or
     * if there is no combination of roads between the 2
     */
    protected Boolean verifyProblemInstance(Map map, Location startLocation, Location endLocation)
    {
        // se verifica daca ambele locatii exista in mapa
        boolean ok1 = false;
        boolean ok2 = false;
        for(Location l : map.locations)
        {
            if(startLocation.equals(l)) ok1 = true;
            if(endLocation.equals(l)) ok2 = true;
        }

        // se verifica daca exista macar un drum intre locatii
        boolean ok3 = false;
        reachableLocations = new ArrayList<>();
        reachableLocations.add(startLocation);
        findDestinations(map, startLocation);
        for(Location l : reachableLocations)
        {
            if(endLocation.equals(l)) ok3 = true;
        }

        if(!(ok1 && ok2)) throw new Error("EROARE! Instantele problemei nu sunt corecte.");
        if(!ok3) throw new Error("EROARE! Nu exista un drum la " + startLocation.getLocationName() + " pana la " + endLocation.getLocationName());

        return ok1 && ok2 && ok3;
    }

    /**
     * Class constructor that displays on the screen the
     * best route between Start and Finish locations
     *
     * @param startLocation the start location of the route
     * @param endLocation the end location of the route
     */
    public BestRouteProblem(Map map, Location startLocation, Location endLocation)
    {
        if(verifyProblemInstance(map, startLocation, endLocation))
        {
            // *ar trebui* COD CARE IMI GENEREAZA CEL MAI SCURT DRUM
            System.out.println("Exista macar un drum de la " + startLocation.getLocationName() + " pana la " + endLocation.getLocationName());
        }
    }
}
