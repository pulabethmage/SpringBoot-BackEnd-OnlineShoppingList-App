package com.batchtech.shoppinghlist.exceptions;

public class ChartItemNotFoundException extends  RuntimeException{
    public ChartItemNotFoundException(Integer id){
        super("Could Not Found the Chart Item With the ID "+id);
    }
}
