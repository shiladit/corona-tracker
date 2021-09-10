package com.shiladitya.coronatracker.controllers;

import com.shiladitya.coronatracker.models.LocationStats;
import com.shiladitya.coronatracker.services.CoronaDataTracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CoronaTrackerPresentation {

    @Autowired
    CoronaDataTracker dataTracker;

    @GetMapping("/")
    public String index(Model model)
    {
        List<LocationStats> total_stat = dataTracker.getPerm_stats();
        int total_reported_cases = total_stat.stream().mapToInt(stat -> stat.getTotalCases()).sum();
        model.addAttribute("Tracker", total_stat);
        model.addAttribute("totalReportedCases", total_reported_cases);
        return "index";
    }
}
