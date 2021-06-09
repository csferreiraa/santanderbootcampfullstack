package com.dio.bootcamp.service;

import com.dio.bootcamp.exceptions.BusinessException;
import com.dio.bootcamp.mapper.StockMapper;
import com.dio.bootcamp.model.Stock;
import com.dio.bootcamp.model.dto.StockDTO;
import com.dio.bootcamp.repository.StockRepository;
import com.dio.bootcamp.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockMapper stockMapper;

    @Transactional
    public StockDTO save(StockDTO dto) {

        Optional<Stock> optionalStock = stockRepository.findByNameAndDate(dto.getName(), dto.getDate());

        if(optionalStock.isPresent()){
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
        }
        Stock stock = stockMapper.toEntity(dto);
        stockRepository.save(stock);
        return stockMapper.toDto(stock);
    }
}
