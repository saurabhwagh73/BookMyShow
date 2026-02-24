package com.scaler.bookmyshow.services;

import com.scaler.bookmyshow.models.Show;
import com.scaler.bookmyshow.models.ShowSeat;
import com.scaler.bookmyshow.models.ShowSeatType;
import com.scaler.bookmyshow.repositories.ShowSeatRepository;
import com.scaler.bookmyshow.repositories.ShowSeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PricingService {
    @Autowired
    private ShowSeatRepository showSeatRepository;
    @Autowired
    private ShowSeatTypeRepository showSeatTypeRepository;
    public int calculatedPrice(List<ShowSeat> showSeats, Show show){
        List<ShowSeatType> allSeatTypes=showSeatTypeRepository.findAllShowSeatTypeByShow(show);
        int amount=0;
        for(ShowSeat showSeat:showSeats){
            for(ShowSeatType showSeatType:allSeatTypes ){
                if(showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType()){
                    amount=showSeatType.getPrice();
                }
            }
        }
        return amount;
    }
}
