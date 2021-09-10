package com.shiladitya.coronatracker.services;

import com.shiladitya.coronatracker.models.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.xml.stream.Location;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaDataTracker {

    private String confirm_url = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private List <LocationStats> perm_stats = new ArrayList<>();

    public List<LocationStats> getPerm_stats()
    {
        return perm_stats;
    }

    @PostConstruct
    public void fetchData() throws IOException, InterruptedException
    {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(confirm_url))
                .build();

        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        StringReader in = new StringReader(response.body().toString());
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
        List <LocationStats> temp_stats = new ArrayList<>();

        for (CSVRecord record : records)
        {
            LocationStats location = new LocationStats();
            String region = record.get("Country/Region");
            Integer totalCases = Integer.valueOf(record.get(record.size()-1));
            location.setRegion(region);
            location.setTotalCases(totalCases);
            temp_stats.add(location);
        }
        perm_stats = temp_stats;
    }
}
