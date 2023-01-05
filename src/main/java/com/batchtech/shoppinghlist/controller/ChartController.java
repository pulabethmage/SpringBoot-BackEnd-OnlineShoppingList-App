package com.batchtech.shoppinghlist.controller;

import com.batchtech.shoppinghlist.exceptions.ChartItemNotFoundException;
import com.batchtech.shoppinghlist.model.Chart;
import com.batchtech.shoppinghlist.repository.ChartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chart")
@CrossOrigin("http://localhost:3000")
public class ChartController {

    @Autowired
    private ChartRepository chartRepository;

    @PostMapping("/add")
    Chart newShoppingItem(@RequestBody Chart newShoppingItem){
        return chartRepository.save(newShoppingItem);
    }

    @GetMapping("/all")
    List<Chart> getAllChartItems(){
        return chartRepository.findAll();
    }

    @DeleteMapping("/items/{id}")
    String deleteChartItem(@PathVariable Integer id){
        if(!chartRepository.existsById(id)){
            throw new ChartItemNotFoundException(id);
        }
        chartRepository.deleteById(id);
        return "User with id "+id+" has been Deleted successfully!!";
    }

    @PutMapping("/items/{id}")
    Chart updateChartItem(@RequestBody Chart newChartItem,@PathVariable Integer id){


        return  chartRepository.findById(id)
                .map(chart -> {
                    chart.setPurchased(newChartItem.getPurchased());

                    return  chartRepository.save(chart);
                }).orElseThrow(()->new ChartItemNotFoundException(id));
    }




}
