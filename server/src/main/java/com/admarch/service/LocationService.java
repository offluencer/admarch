package com.admarch.service;

import com.admarch.model.Location;
import com.admarch.model.Locations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

@Service
public class LocationService {

    @Value("${application.location.path}")
    private String locationPath;

    private String wordDelim = ",";
    private String lineDelim = "\n";

    public void trackLocation(String regNum, String rideId, Locations locations) throws IOException {
        LocalDate currentDate = LocalDate.now();

        File userDir = new File(locationPath+regNum);
        if(!userDir.exists())
            userDir.mkdir();

        // TODO: there should be a better way to combine file path strings
        File locationTrackFile = new File(locationPath+regNum+"/"+currentDate.toString());

        if(!locationTrackFile.exists())
            locationTrackFile.createNewFile();

        StringBuilder stringBuilder = new StringBuilder();

        for(Location location:locations.getLocations()){
            stringBuilder.append(rideId);
            stringBuilder.append(wordDelim);

            stringBuilder.append(location.getLatitude());
            stringBuilder.append(wordDelim);

            stringBuilder.append(location.getLongitude());
            stringBuilder.append(wordDelim);

            stringBuilder.append(location.getTimestamp());
            stringBuilder.append(lineDelim);
        }

        BufferedWriter writer = null;

        try{
            writer = new BufferedWriter(new FileWriter(locationTrackFile, true));
            writer.write(stringBuilder.toString());
        }finally {
            if(writer!=null)
                writer.close();
        }
    }
}
